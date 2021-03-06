package com.yfwl.yfgp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yfwl.yfgp.utils.PropertiesUtils;


@Controller
@RequestMapping("/updateOnline")
public class UpdateOnlineController {
	
	/**
	 * 在线升级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateOnline", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> updateOnline(HttpServletRequest request,
			HttpServletResponse response){
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> datamap = new HashMap<String, Object>();
		
		String stockDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "stocklist.txt";
		String secidDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "secid.txt";
		String holidayDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "holiday.txt";
		datamap.put("stockDownloadUrl", stockDownloadUrl);
		datamap.put("secidDownloadUrl", secidDownloadUrl);
		datamap.put("holidayDownloadUrl", holidayDownloadUrl);
		
		URL url = null;
		/** http连接 */
		HttpURLConnection httpConn = null;
		/**//** 输入流 */
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL("http://120.76.192.128:30555/apk/yfStock.properties");
			in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			String str = null;
			while ((str = in.readLine()) != null) {
				//sb.append(str).append("\n");
				String[] s1 = str.split(" = ");
				datamap.put(s1[0], s1[1]);
			}
			
			String appName = (String) datamap.get("appName");
			String appDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl")+appName;
			datamap.put("appDownloadUrl", appDownloadUrl);
			
			datamap.remove("appName");
			datamap.remove("secid");
			datamap.remove("stocklist");
			datamap.remove("holiday");
			
			map.put("data", datamap);
			map.put("status", 0);
			map.put("msg", "操作成功");
			
		} catch (Exception ex) {

		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return map;
		
		
		/*Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		String versionCode = PropertiesUtils.getAppVersionProperties().getProperty("versionCode");
		String versionName = PropertiesUtils.getAppVersionProperties().getProperty("versionName");
		String update = PropertiesUtils.getAppVersionProperties().getProperty("update");
		String appName = PropertiesUtils.getAppVersionProperties().getProperty("appName");		
		String appDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl")+appName;
		String stockVersion = PropertiesUtils.getAppVersionProperties().getProperty("stockVersion");
		String secidVersion = PropertiesUtils.getAppVersionProperties().getProperty("secidVersion");
		String holidayVersion = PropertiesUtils.getAppVersionProperties().getProperty("holidayVersion");
		String iosVersion = PropertiesUtils.getAppVersionProperties().getProperty("iosVersion");
		String stockDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "stocklist.txt";
		String secidDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "secid.txt";
		String holidayDownloadUrl = PropertiesUtils.getProperties().getProperty("AppDownloadUrl") + "holiday.txt";
		dataMap.put("versionCode", versionCode);
		dataMap.put("versionName", versionName);
		dataMap.put("update", update);
		dataMap.put("appDownloadUrl", appDownloadUrl);
		dataMap.put("stockVersion", stockVersion);
		dataMap.put("secidVersion", secidVersion);
		dataMap.put("holidayVersion", holidayVersion);
		dataMap.put("iosVersion", iosVersion);
		dataMap.put("stockDownloadUrl", stockDownloadUrl);
		dataMap.put("secidDownloadUrl", secidDownloadUrl);
		dataMap.put("holidayDownloadUrl", holidayDownloadUrl);
		map.put("status", 0);
		map.put("message", "操作成功");
		map.put("data", dataMap);
		return map;*/
	}
	
}
