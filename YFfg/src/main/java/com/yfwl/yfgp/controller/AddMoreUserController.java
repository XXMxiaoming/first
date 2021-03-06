package com.yfwl.yfgp.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yfwl.yfgp.easemodrest.demo.EasemobIMUsers;
import com.yfwl.yfgp.easemodrest.method.EasemobIMUsersMethod;
import com.yfwl.yfgp.model.AccessToken;
import com.yfwl.yfgp.model.Account;
import com.yfwl.yfgp.model.Identity;
import com.yfwl.yfgp.model.Integral;
import com.yfwl.yfgp.model.Token;
import com.yfwl.yfgp.model.User;
import com.yfwl.yfgp.service.AccountService;
import com.yfwl.yfgp.service.FutureService;
import com.yfwl.yfgp.service.IntegralService;
import com.yfwl.yfgp.service.TokenService;
import com.yfwl.yfgp.service.UserService;
import com.yfwl.yfgp.service.ValidateCodeService;
import com.yfwl.yfgp.utils.GetHSTokenUtils;
import com.yfwl.yfgp.utils.JacksonUtils;
import com.yfwl.yfgp.utils.RandomStringUtil;

@Controller
@RequestMapping("/user2")
public class AddMoreUserController {
	

	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private IntegralService integralService;
	@Autowired
	private FutureService futureService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EasemobIMUsers.class);
	
	@RequestMapping(value = "/insertUser", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> insertPhone(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String userName = RandomStringUtil.getRandomName();
		String password = RandomStringUtil.getRandomPassword();
		String phone = RandomStringUtil.getRandomPhone();

		User user = new User();

		Map<String, Object> map = new HashMap<String, Object>();

		boolean isHaven = userService.checkoutUsername(userName);
		if (isHaven == true) {
			map.put("status", 4);// 该用户名已经存在
			map.put("msg", "该用户名已经存在");
			map.put("data", "");
		} else {
			user.setUserName(userName);
			user.setPassword(password);
			user.setPhone(phone);
			String easemobId = RandomStringUtil.getRandomCharNum();// 随机生成一个环信ID
			user.setEasemobId(easemobId);
			String easemobPassword = RandomStringUtil.getRandomCharNum();// 随机生成一个环信密码
			user.setEasemobPassword(easemobPassword);
			userService.insertUser(user);
			Integer userId = user.getUserId();
			String accountId = "";
			String integralId = "";
			if(null == futureService.getIdentity(userId)) {
				Identity identity = new Identity();
				identity.setUserId(userId);
				futureService.setIdentity(identity);
			}
			if(null != accountService.getAccount(userId) && null != accountService.getAccount(userId).getAccountId()) {
				accountId = accountService.getAccount(userId).getAccountId() + "";
			}
			else {
				Account account = new Account();
				account.setUserId(userId);
				account.setPassword("");
				int hasInit = accountService.initAccount(account);
				accountId = accountService.getAccount(userId).getAccountId() + "";
			}
			if(null != integralService.getIntegral(userId) && null != integralService.getIntegral(userId).getIntegralId()){
				integralId = integralService.getIntegral(userId).getIntegralId() + "";
			}
			else
			{
				Integral account = new Integral();
				account.setUserId(userId);
				int hasInit = integralService.initIntegral(account);
				integralId = integralService.getIntegral(userId).getIntegralId() + "";
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("userId", userId);
			dataMap.put("userName", userName);
			dataMap.put("easemobId", easemobId);
			dataMap.put("easemobPassword", easemobPassword);
			dataMap.put("accountId", accountId);
			dataMap.put("integralId", integralId);

			/**
			 * 注册IM用户[单个]
			 */
			ObjectNode datanode = JsonNodeFactory.instance.objectNode();
			datanode.put("username", easemobId);
			datanode.put("password", easemobPassword);
			datanode.put("nickname", userName);
			ObjectNode createNewIMUserSingleNode = EasemobIMUsersMethod
					.createNewIMUserSingle(datanode);
			if (null != createNewIMUserSingleNode) {
				LOGGER.info("注册IM用户[单个]: "
						+ createNewIMUserSingleNode.toString());
			}

			/**
			 * 注册完直接登录了，返回获取到的新token
			 */
			String url = "http://sandbox.hscloud.cn/oauth2/oauth2/token";
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("grant_type", "client_credentials");
			paramMap.put("open_id", userId.toString());
			String tokenResult = GetHSTokenUtils.sendPost(url, paramMap,
					GetHSTokenUtils.CHARSET, GetHSTokenUtils.CHARSET, null,
					GetHSTokenUtils.BASIC, "获取令牌");

			// 新的token
			AccessToken accesstoken = JacksonUtils.json2Object(tokenResult,
					AccessToken.class);

			Token token = new Token();

			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND,
					Integer.parseInt(accesstoken.getExpires_in()));

			Date expiresTime = c.getTime();// 计算出过期时间

			token.setExpiresTime(expiresTime);
			token.setAccessToken(accesstoken.getAccess_token());
			token.setTokenType(accesstoken.getToken_type());
			token.setRefreshToken(accesstoken.getRefresh_token());
			token.setExpiresIn(accesstoken.getExpires_in());
			token.setUserId(userId);

			tokenService.insertTokenLoginOn(token);

			dataMap.put("token", accesstoken.getAccess_token());
			dataMap.put("tokenType", accesstoken.getToken_type());
			dataMap.put("expiresTime", expiresTime);

			map.put("status", 0);// 注册成功
			map.put("msg", "注册成功");
			map.put("data", dataMap);

		}
		return map;
	}
}
