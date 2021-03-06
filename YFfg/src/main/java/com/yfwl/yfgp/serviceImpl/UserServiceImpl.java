package com.yfwl.yfgp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfwl.yfgp.dao.UserMapper;
import com.yfwl.yfgp.model.User;
import com.yfwl.yfgp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean checkoutRegisterPhone(String phone) {
		// TODO Auto-generated method stub
		boolean isHaven = false;
		Integer phoneCount = userMapper.checkoutRegisterPhone(phone);		
		if(phoneCount > 0 ){
			isHaven = true;
		}
		return isHaven;
	}

	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(user);
	}

	@Override
	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		boolean updateOk = false;
		Integer updateVal = userMapper.updateUserInfo(user);
		if(updateVal > 0 ){
			updateOk = true;
		}
		return updateOk;
	}

	@Override
	public boolean checkoutUsername(String userName) {
		// TODO Auto-generated method stub
		boolean isHaven = false;
		Integer checkVal = userMapper.checkoutUsername(userName);
		if(checkVal > 0 ){
			isHaven = true;
		}
		return isHaven;
	}


	@Override
	public boolean validateLonginName(String loginName) {
		// TODO Auto-generated method stub
		boolean isHaven = false;
		Integer checkVal = userMapper.validateLonginName(loginName);
		if(checkVal > 0){
			isHaven = true;
		}
		return isHaven;
	}

	@Override
	public Integer validatePassword(String phone,String password) {
		// TODO Auto-generated method stub
		return userMapper.validatePassword(phone,password);
	}

	@Override
	public User selectUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByLoginName(loginName);
	}

	@Override
	public User selectUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(userId);
	}

	@Override
	public User selectUsernameByeasemobId(String easemobId) {
		// TODO Auto-generated method stub
		return userMapper.selectUsernameByeasemobId(easemobId);
	}

	@Override
	public boolean updatePassword(String password, String phone) {
		// TODO Auto-generated method stub
		boolean isUpdateOk = false;
		Integer updateVal = userMapper.updatePassword(password, phone);
		if(updateVal > 0){
			isUpdateOk = true;
		}
		return isUpdateOk;
	}

	@Override
	public List<User> selectFriendList(String loginName) {
		// TODO Auto-generated method stub
		return userMapper.selectFriendList(loginName);
	}
	
	@Override
	public List<User> selectUserByLike(String searchName,Integer pageCount) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByLike(searchName,pageCount);
	}

	@Override
	public boolean updatHeadImage(String headImage, String userId) {
		// TODO Auto-generated method stub
		boolean isUpdateOk = false;
		Integer updateVal = userMapper.updatHeadImage(headImage, userId);
		if(updateVal > 0){
			isUpdateOk = true;
		}
		return isUpdateOk;
	}


	@Override
	public boolean updatePasswordGeneral(String password, Integer userId) {
		// TODO Auto-generated method stub
		boolean isUpdateOk = false;
		Integer updateVal = userMapper.updatePasswordGeneral(password, userId);
		if(updateVal > 0){
			isUpdateOk = true;
		}
		return isUpdateOk;
	}

	@Override
	public User selectUserByThirdID(String thirdAccountId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByThirdID(thirdAccountId);
	}

	@Override
	public Integer insertUserWhenDSFDL(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertUserWhenDSFDL(user);
	}

	@Override
	public boolean updateUsernameWhenDSFDL(String userName, String phone) {
		// TODO Auto-generated method stub
		boolean isUpdateOk = false;
		Integer updateVal = userMapper.updateUsernameWhenDSFDL(userName, phone);
		if(updateVal >0){
			isUpdateOk = true;
		}
		return isUpdateOk;
	}

	@Override
	public boolean updateUserSexInfo(User user) {
		// TODO Auto-generated method stub
		boolean isupdate = false;
		Integer var = userMapper.updateUserSexInfo(user);
		if(var > 0) {
			isupdate = true;
		}
		return isupdate;
	}

	@Override
	public User selectUserSuper(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectUserSuper(user);
	}

	@Override
	public Integer updateUserSuper(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUserSuper(user);
	}

	@Override
	public List<User> getVUserList(Integer userId, Integer limit) {
		/*
		 *  随机获取表中满足条件（未关注且是大V的用户）的一条记录
		 */
		List list = userMapper.getVUserList(userId);
		User user = (User)list.get(0);
		// 获取该条记录的用户id
		int userId_ = user.getUserId();
		user.setUserId(userId);
		user.setAmCount(userId_);
		// 查看该条记录在第几行
		Integer i = userMapper.getLocation(user);
		// 获取表中总记录条数
		Integer totalRecode = userMapper.getTotalCountRecode(userId);
		if(i < (totalRecode-(limit-2))){// 不是倒数前三条数据
			// 取第i条记录及其后面的三条记录
			user.setAmCount(i-1);
		}else{// 是倒数前三条数据
			// 取第i条记录及其前面的三条记录
			user.setAmCount(i-limit);
		}
		user.setAttent(limit);
		List<User> list_ = userMapper.getLimitVUser(user);
		return list_;
	}

	@Override
	public Integer updateTZtype(String tzType, Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.updateTZtype(tzType, userId);
	}

	@Override
	public User selectUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUsername(userName);
	}

	@Override
	public User getUserByUsername(String loginName, String password) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUsername(loginName, password);
	}

	@Override
	public User getUserByPhone(String loginName, String password) {
		// TODO Auto-generated method stub
		return userMapper.getUserByPhone(loginName, password);
	}

	@Override
	public Integer insertPhone(String phone, Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.insertPhone(phone, userId);
	}

	@Override
	public List<User> selectFriendList2(String loginName) {
		// TODO Auto-generated method stub
		return userMapper.selectFriendList2(loginName);
	}

	@Override
	public List<User> getAllVUserList() {
		// TODO Auto-generated method stub
		return userMapper.getAllVUserList();
	}

	@Override
	public List<User> selectRandSysUseList(Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return userMapper.selectRandSysUseList(start, limit);
	}

	@Override
	public List<User> selectRandHighSysUseList(Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return userMapper.selectRandHighSysUseList(start, limit);
	}
	
	//查询所有用户的easemobId
	@Override
	public List<String> selectUserEasemodId(Integer count) {
		return userMapper.selectUserEasemodId(count);
	}

	//统计所有真实用户的总数
		@Override
		public Integer getUserCount() {
			return userMapper.getUserCount();
		}

		@Override
		public List<String> getStockAttUser(String stockCode) {
			// TODO Auto-generated method stub
			return userMapper.getStockAttUser(stockCode);
		}

		@Override
		public String getSYUser() {
			// TODO Auto-generated method stub
			return userMapper.getSYUser();
		}
		
		@Override
		public Integer insertUser2(User user) {
			// TODO Auto-generated method stub
			return userMapper.insertUser(user);
		}

		@Override
		public User selectUserByIdPwd(User user) {
			// TODO Auto-generated method stub
			return userMapper.selectUserByIdPwd(user);
		}

		@Override
		public Integer getmaiId() {
			// TODO Auto-generated method stub
			return userMapper.getmaiId();
		}

		@Override
		public Integer getnextId() {
			// TODO Auto-generated method stub
			return userMapper.getnextId();
		}

		
}
