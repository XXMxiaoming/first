package com.yfwl.yfgp.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.mapping.Environment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easemob.server.method.SendMessageMethods;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
import com.yfwl.yfgp.model.OwnStock;
import com.yfwl.yfgp.model.StockXml;
import com.yfwl.yfgp.model.Stockinfo;
import com.yfwl.yfgp.model.Stocksend;
import com.yfwl.yfgp.service.OwnStockService;
import com.yfwl.yfgp.service.StockInfoService;
import com.yfwl.yfgp.service.StocksendService;
import com.yfwl.yfgp.service.UserService;
import com.yfwl.yfgp.utils.AccountUtil;
import com.yfwl.yfgp.utils.StockUpDownUtils;

@Component
public class SendStockUpDownMsgSchedule {
	@Autowired
	StocksendService stocksendService;
	@Autowired
	UserService userService;
	@Autowired
	StockInfoService stockInfoService;
	@Autowired
	OwnStockService ownStockService;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

	public void bindSchedule() {
		//sendMsg();
		sendMsg3();
	
		
	}

	public void sendMsg() {

		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
		Date datenow = new Date();
		String dateString = dateSdf.format(datenow);
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		int week = cl.get(Calendar.DAY_OF_WEEK) - 1;
		// 周一到周五执行
		if (!(week == 6 || week == 0 || AccountUtil.HOLIDAY_STRING
				.contains(dateString))) {
			List<StockXml> list = StockUpDownUtils.analyzeUpDownXml();
			if (list.size() > 0) {
				for (StockXml stockXml : list) {
					String stockName = stockXml.getZqjc();
					// String stockCjj = stockXml.getDay_Cjj();
					String upDown = stockXml.getUp_Down();
					// String info = stockXml.getInfo();
					String stockCode = stockXml.getZqdm();
					String operate = stockXml.getOperate();
					String market;
					Stockinfo stockinfo = null;
					try {
						stockinfo = stockInfoService.getStock(stockCode);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (stockinfo != null && !stockinfo.equals("")) {
						Integer marketNum = stockinfo.getMarket();

						if (marketNum == 1) {
							market = ".SS";
						} else {
							market = ".SZ";
						}

						List<String> easemobIdList = userService
								.getStockAttUser(stockCode);
						if (easemobIdList.size() > 0) {
							String targetType = "users";
							String from = "lbh3zyi";
							String stock = " $" + stockName + "(" + stockCode
									+ market + ")$";
							ObjectNode ext = factory.objectNode();
							String msg = "您关注的" + stock + "，已出现中期" + upDown
									+ "信号，请及时" + operate + "。";
							SendMessageMethods.sendTxtMsg(targetType,
									easemobIdList, from, ext, msg);

						}
					} else {

					}
				}
			}
		}

	}

	
	public void sendMsg3() {
		
		Stocksend stocksend=null;
		XingeApp push = new XingeApp(XingeApp.IOS_ID,
				XingeApp.IOS_MYKEY);
		XingeApp push2 = new XingeApp(XingeApp.ANDRIOD_MAX_ID, XingeApp.ANDRIOD_MYKEY);
		//昨天日期
		Calendar ac = Calendar.getInstance();
		ac.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd")
				.format(ac.getTime());
		//今天日期
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
		Date datenow = new Date();
		String dateString = dateSdf.format(datenow);
		
		//判断今天是星期几
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		int week = cl.get(Calendar.DAY_OF_WEEK) - 1;
		
		//推送给信鸽的时间
		SimpleDateFormat xgsdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String xgtime = xgsdf.format(datenow);
		
		// 周一到周五执行
		if (!(week == 6 || week == 0 || AccountUtil.HOLIDAY_STRING
				.contains(dateString))) {
			List<Stockinfo> stockinfoList = stockInfoService.getAllStockinfo();
			Integer date = null;
			String text = null;
			Integer market=null;
			String stockid=null;
			for (Stockinfo stockinfo : stockinfoList) {
				date = stockinfo.getDate();
				if (date != null) {
					if (stockinfo.getDate().toString().equals(yesterday)) {
						int bspoint = stockinfo.getBspoint();
						market=stockinfo.getMarket();
						if (market != null && market == 2) {
							stockid = stockinfo.getStockid() + ".SZ";
						}
						else if (market != null && market == 1) {
							stockid = stockinfo.getStockid() + ".SS";
						}else{
							continue;
						}
						
						String name =stockinfo.getName();
						List<OwnStock> OwnStockList = ownStockService
								.getOwnStock2(stockid);
						if(OwnStockList != null &&!OwnStockList.isEmpty()){
							String StockCode = null;
							String  userid =null;
							for (OwnStock ownStock : OwnStockList) {
								userid=ownStock.getUserId().toString();
								if (!userid.equals("")) {
									StockCode = ownStock.getStockCode();
//									if(StockCode.toString().contains("000001.SS")){
//										continue;
//									}
									stocksend=new Stocksend();
									stocksend.setUserid(ownStock.getUserId());
									stocksend.setStockid(stockid);
									stocksend.setBspoint(bspoint);
									stocksend.setName(name);
								//	stocksend.setStatus(0);
									stocksend.setStockCode(StockCode);
									stocksend.setTime(xgtime);
									stocksend.setType("1");
									Integer count = stocksendService
											.insertStocksend(stocksend);
									Integer id=stocksend.getId();
									
									MessageIOS iosMess = new MessageIOS();
									Map<String, Object> map=new HashMap<String, Object>();
									map.put("stockid", stockid);//股票的stockid 如 000023
									map.put("stockCode", StockCode);//股票的stock_code 如 000023.SZ
									map.put("bspoint", bspoint);//bspoint 1为买点，2为卖点。
									map.put("type", "1");//1表示自选股
									map.put("time", xgtime);
									map.put("name", name);
									map.put("id", id);
									iosMess.setAlert("您有一条新消息");
									iosMess.setCustom(map);
									push.pushSingleAccount(0, userid, iosMess, XingeApp.IOSENV_PROD);
								
									
									Message mess=new Message();
									mess.setTitle("自选股");
									mess.setStyle(new Style(0,1,1,0,0));
									mess.setType(Message.TYPE_NOTIFICATION);
									mess.setContent("您有一条新消息");
									ClickAction action=new ClickAction();
									action.setActivity("com.yfnetwork.yffg.xgpush.PushMsgActivity");
									mess.setAction(action);
									Map<String, Object> map2=new HashMap<String, Object>();
									mess.setCustom(map);
									push2.pushSingleAccount(0, userid, mess);
								}else{
									continue;
								}			
							}

						}
						
					} else {
						continue;
					}
				} else {
					continue;
				}

			}

		} else {

		}

	}
	
	
}