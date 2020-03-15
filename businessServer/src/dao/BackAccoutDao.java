package dao;

import java.util.ArrayList;
import java.util.List;

import javabean.BackAccout;
import javabean.TB_User;

public interface BackAccoutDao{
	//通过账号查询后台管理员账号密码
	public BackAccout queryByAccount(String account);
	//全表查询用户信息
	public ArrayList<TB_User> queryALL();
     //全表分页
	public ArrayList<TB_User> queryALL(int currentPage);
	//通过账号查询查询用户信息
	public ArrayList<TB_User> queryALL(String account);
	//通过账号查询查询用户信息分页
    public ArrayList<TB_User> queryALL(int currentPage,String account);
    //通过账号修改用户的状态是否禁用或者启用
  	public boolean alterUserState(String state,String wheres);
}
