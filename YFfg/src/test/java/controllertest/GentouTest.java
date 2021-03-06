package controllertest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import sun.misc.BASE64Encoder;

import com.yfwl.yfgp.posttestutils.ControllerTest;
import com.yfwl.yfgp.utils.MD5Util;
import com.yfwl.yfgp.utils.SortByMap;

public class GentouTest extends ControllerTest {
////	// 获取注册验证码
////	@Test
////	public void test1() {
////		String url = host + "gentou/AllMethod.do";
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("mobile", "15220138720");
////		map.put("act", "get_register_verify_code");
////		System.out.println((doPost(url, map)));
////	}
//////
//////	// 手机注册码验证
//////	@Test
//////	public void test4() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("mobile", "15220138713");
//////		map.put("act", "check_verify_code");
//////		map.put("verify", "590684");
//////		System.out.println((doPost(url, map)));
//////	}
//////
//////	// 注册
////	@Test
////	public void test5() {
////		String url = host + "user2/insertUser2.do";
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("userName", "g规的时间");
//////		map.put("user_pwd", new BASE64Encoder()
//////		.encode("fhdijshfhdskf".getBytes()));
////		map.put("user_pwd", "fdsafdafdasf");
////		map.put("mobile", "15220138720");
////		map.put("verify", "641574");
////		System.out.println((doPost(url, map)));
////		// A8164A4CD956403D94BB1CBEFE2E727F2017070610152336925CA9
////		// 7261F7F7EAA54C49997C9A4AE18654032017070616190136925CA9
////	}
//////
//////	// 修改密码验证码
//////	@Test
//////	public void test6() {
//////		String url = host + "user/sendSmsUpdatePassword.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("mobile", "15220138713");
//////		// map.put("act", "get_re_pwd_verify_code");
//////		System.out.println((doPost(url, map)));
//////
//////	}
//////
//////	// 修改密码
//////	@Test
//////	public void test7() {
//////		String url = host + "user/updatePassword.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("user_pwd", "zzx1234567");
//////		map.put("mobile", "15220138713");
//////		map.put("verify", "590684");
//////		System.out.println((doPost(url, map)));
//////	}
//////
//////	@Test
//////	public void test2() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("fage_id", "45");
//////		map.put("token", "32");
//////		map.put("act", "get_bank_list");
//////		System.out.println(doPost(url, map));
//////	}
//////
//////	@Test
//////	public void test3() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("fage_id", "45");
//////		map.put("mobile", "15220138713");
//////		map.put("user_pwd", "xxm12345");
//////		map.put("verify", "635904");
//////		map.put("act", "register");
//////		System.out.println(doPost(url, map));
//////	}
//////
//////	// 查询地区列表
//////	@Test
//////	public void test103() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("fage_id", "42557");
//////		map.put("act", "get_region_list");
//////		map.put("pid", "3");
//////		map.put("token",
//////				"52038586FE734DB88C7E1C16381B3A582017072117311036925CA9");
//////		System.out.println(doPost(url, map));
//////	}
//////
//////	// 实名验证并绑定银行卡
//////	@Test
//////	public void test104() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("fage_id", "81");
//////		map.put("act", "bind_identification_and_bank");
//////		map.put("type_identification", "credit_identificationscanning");
//////		map.put("real_name", "谢晓明");
//////		map.put("idno", "43138199309020876");
//////		map.put("region_lv1", "1");
//////		map.put("region_lv2", "2");
//////		map.put("region_lv3", "3");
//////		map.put("region_lv4", "4");
//////		map.put("bankzone", "哈哈哈");
//////		map.put("bankcard", "464132131545465");
//////		System.out.println(doPost(url, map));
//////	}
//////
////	// 产品详情
////	@Test
////	public void test105() {
////		String url = host + "gentou/AllMethod.do";
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("deal_id", "1321");
////		map.put("act", "deal");
////		System.out.println(doPost(url, map));
////	}
//////
//////	
//////	// 申购列表
//////	@Test
//////	public void test106() {
//////		String url = host + "gentou/AllMethod.do";
//////		HashMap<String, String> map = new HashMap<String, String>();
//////		map.put("act", "get_load_list");
//////		map.put("deal_id", "1310");
//////		System.out.println(doPost(url, map));
//////	}
//////
//////	
	// 产品列表
	@Test
	public void test107() {
		String url = host + "gentou/AllMethod.do";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("deal_type", "0");
		map.put("act", "deals");
		map.put("deal_status", "4");
		map.put("p", "1");
		//map.put("token", "sds");
		System.out.println(doPost(url, map));
	}
////
////	
////	
////	// 产品详情
////	@Test
////	public void test108() {
////		String url = host + "gentou/AllMethod.do";
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("act", "deal");
////		map.put("deal_id", "1321");
////		System.out.println(doPost(url, map));
////	}
////
////	
////	//我的账单
////	@Test
////	public void test109() {
////		String url = host + "gentou/AllMethod.do";
////		HashMap<String, String> map = new HashMap<String, String>();
////		map.put("act", "bill");
////		map.put("fage_id", "81");
////		map.put("type_title", "100");
////		map.put("p", "1");
////		map.put("token", "30AA76D76E5845598D25DFF0CA1B8E792017071810034736925CA9");
////		System.out.println(doPost(url, map));
////	}
////
////	
////	//投标
////		@Test
////		public void testhah(){
////			String url=host+"gentou/AllMethod.do";
////			HashMap<String, String> map = new HashMap<String, String>();
////			map.put("act", "dobid");
////			map.put("fage_id", "81");
////			map.put("deal_id", "100");
////			map.put("bid_money", "1");
////			map.put("bid_paypassword", "789456");
////			map.put("token", "EB86A4AC5B4E40BE8A39453F66DD72E02017072018162636925CA9");
////			System.out.println(doPost(url, map));
////		}
////		
		@Test
		public void yao(){
			String url=host+"gentou/AllMethod.do";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("act", "user_info");
			map.put("fage_id", "1");
			map.put("token", "D5ED71ADA8AF4FC98492DD4241305E8B2017082414502636925CA9");
			System.out.println(doPost(url, map));
		}
////		//产品详情
////		@Test
////		public void gdgks(){
////			String url=host+"gentou/AllMethod.do";
////			HashMap<String, String> map = new HashMap<String, String>();
////			map.put("act", "deal");
////			map.put("deal_id", "1326");
////			//map.put("token", "CC06C8CBA1EB4E1E854348E49BDAB5C02017072518163036925CA9");
////			System.out.println(doPost(url, map));
////		}
////		//{"msg":"产品详情查询成功","data":{"deal":{"into_rate":null,"run_time":0,"deal_type":null,"repay_time":null,"new_rate":0,"min_loan_money":null,"manage_m":"收益的%","open_line":null,"lead_money":null,"start_time":null,"enddate":null,"rate":null,"transactions_type":"股票","name":null,"running_time":null,"id":null,"all_load_amount":0,"load_money":null},"running_time":17379,"load_count":0,"remain_time":-17379},"status":0}
////		//{"msg":"产品详情查询成功","data":{"deal":{"into_rate":null,"run_time":0,"deal_type":null,"repay_time":null,"new_rate":0,"min_loan_money":null,"manage_m":"收益的%","open_line":null,"lead_money":null,"start_time":null,"enddate":null,"rate":null,"transactions_type":"股票","name":null,"running_time":null,"id":null,"all_load_amount":0,"load_money":null},"running_time":17379,"load_count":0,"remain_time":-17379},"status":0}
////		//{"msg":"产品详情查询成功","data":{"history_deal":null,"deal":{"into_rate":"0.00","run_time":1503505800,"deal_type":"0","repay_time":"3","new_rate":0,"min_loan_money":"100.00","manage_m":"收益的10.00%","open_line":"0.80","lead_money":"1000.00","start_time":"1500913800","enddate":"30","rate":"10.00","transactions_type":"商品","name":"募集中L","running_time":null,"id":"1325","all_load_amount":1100,"load_money":100,"deal_status":1},"history_hs300":null,"running_time":-22,"load_count":2,"remain_time":25,"history_total":[],"history_date":null},"status":0}
//////查询用户银行
////		@Test
////		public void gdg(){
////			String url=host+"gentou/AllMethod.do";
////			HashMap<String, String> map = new HashMap<String, String>();
////			map.put("act", "get_user_bank");
////			map.put("fage_id", "81");
////			map.put("token", "C30991E925B3462DA240BF98E3582BF62017080111062336925CA9");
////			//map.put("token", "CC06C8CBA1EB4E1E854348E49BDAB5C02017072518163036925CA9");
////			System.out.println(doPost(url, map));
////		}	
//		
//	@Test
//	public void test118(){
//		String url = host + "gentou/AllMethod.do";
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("mobile", "15220138720");
//		map.put("act", "get_register_verify_code");
//		System.out.println((doPost(url, map)));
//	}
//	
//	//支付密码验证码
//	@Test
//	public void test119(){
//		String url = host + "gentou/AllMethod.do";
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("fage_id", "81");
//		map.put("act", "get_paypwd_verify_code");
//		System.out.println((doPost(url, map)));
//	}
//	
}
