package dao;

import javabean.TB_User;

public interface UserDao {
	   //添加用户  注册
		public boolean addUser(TB_User user);
	
		//通过账号查密码，还有其他的信息
		public TB_User queryByAccount(String account);
		//根据ID 删除用户 业务
		public boolean deleteByid(int uid);
		//修改密码操作
		public boolean alterPassword(String newpwd,String account,String pwd);
		//修改个人信息
		public boolean alterUserMsg(String username,String account);
		//修改个人积分
		public boolean alterUserScore(int updatescore,String account);
		//修改个人等级
		public boolean alterUserLevel(String levelname,String account);
		//修改已使用空间的
		public boolean alterUserUsedSpace(double UsedSpace,String account);


}
