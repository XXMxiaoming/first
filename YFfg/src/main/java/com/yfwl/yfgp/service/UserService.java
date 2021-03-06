package com.yfwl.yfgp.service;

import java.util.List;











import com.yfwl.yfgp.model.User;

public interface UserService {
	
	/**
	 * 查找user表中有没有该注册过的手机号码
	 * @param phone
	 * @return
	 */
	public boolean checkoutRegisterPhone (String phone);
	
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 */
	public Integer insertUser (User user);
	
	/**
	 * 查找user表有没有相同的用户名
	 * @param userName
	 * @return
	 */
	public boolean checkoutUsername(String userName);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUserInfo(User user);
	
	/**
	 * 校验用户名或手机号
	 * @param loginName
	 * @return
	 */
	public boolean validateLonginName(String loginName);
	
	/**
	 * 校验密码
	 * @param loginName
	 * @return
	 */
	public Integer validatePassword(String phone,String password);
	
	/**
	 * 根据登录名查找user
	 * @param loginName
	 * @return
	 */
	public User selectUserByLoginName(String loginName);
	
	/**
	 * 根据id查找user
	 * @param loginName
	 * @return
	 */
	public User selectUserById(Integer userId);
	
	/**
	 * 根据环信ID查找用户名
	 * @param easemobId
	 * @return
	 */
	public User selectUsernameByeasemobId(String easemobId); 
	
	/**
	 * 更改密码
	 * @param password
	 * @param phone
	 * @return
	 */
	public boolean updatePassword(String password,String phone);
	
	/**
	 * 查看好友列表
	 * @param loginName
	 * @return
	 */
	public List<User> selectFriendList (String loginName);
	
	/**
	 * 查找用户（模糊查询）
	 * @param searchName
	 * @return
	 */
	public List<User> selectUserByLike(String searchName,Integer pageCount);
	
	/**
	 * 更新头像
	 * @param headImage
	 * @param userId
	 * @return
	 */
	public boolean updatHeadImage(String headImage,String userId);
	
	
	/**
	 * 普通修改密码
	 * 
	 * @param password
	 * @param phone
	 * @return
	 */
	public boolean updatePasswordGeneral(String password,Integer userId);
	
	/**
	 * 通过第三方账号查询user
	 * @param thirdAccountId
	 * @return
	 */
	public User selectUserByThirdID(String thirdAccountId);
	
	/**
	 * 第三方登录时新注册一个用户
	 * @param user
	 * @return
	 */
	public Integer insertUserWhenDSFDL(User user);
	
	/**
	 * 第三方登录时更新用户名
	 * @param userName
	 * @param phone
	 * @return
	 */
	public boolean updateUsernameWhenDSFDL(String userName,String phone);
	
	
	public boolean updateUserSexInfo(User user);
	
	
	
	public User selectUserSuper(User user);
	
	
	public Integer updateUserSuper(User user);
	
	
	public List<User> getVUserList(Integer userId, Integer limit);
	
	public List<User> getAllVUserList();
	
	public Integer updateTZtype(String tzType,Integer userId);
	
	public User selectUserByUsername(String userName);
	
	//查询所有真实用户的easemobId
	public List<String> selectUserEasemodId(Integer count);
	
	//统计真实用户的总数
	public Integer getUserCount();
	
	//改版登录注册
	User getUserByUsername(String loginName,String password);
	User getUserByPhone(String loginName,String password);
	
	Integer insertPhone(String phone,Integer userId);
	
	List<User> selectFriendList2(String loginName);
	
	List<User> selectRandSysUseList(Integer start, Integer limit);
	List<User> selectRandHighSysUseList(Integer start, Integer limit);
	
	List<String> getStockAttUser(String stockCode);
	
	String getSYUser();
	
	
	
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 */
	public Integer insertUser2 (User user);
	public User selectUserByIdPwd(User user);
	
	public Integer getmaiId();
	
	public Integer getnextId();
}
