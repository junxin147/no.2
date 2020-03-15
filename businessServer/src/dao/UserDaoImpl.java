package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javabean.BackAccout;
import javabean.TB_User;

public class UserDaoImpl implements UserDao {

	private boolean flag;
	private String sql;
	private Connection conn;
	private PreparedStatement pps;
	
	public UserDaoImpl() {
		conn = ConnectionUtil.getInstance().getConnection();
	}
	@Override
	public boolean addUser(TB_User user) {
		try
		{
			flag = false;
			sql = "insert into user (userAccount,userPwd,userName,userRegTime) " + 
					"  values  (?,?,?,?)  ";
			pps = conn.prepareStatement(sql);

			pps.setString(1, user.getUserAccount());
			pps.setString(2, user.getUserPwd());
			pps.setString(3, user.getUserName());
			pps.setString(4, user.getUserRegTime());
			int i;
			try {
				 i = pps.executeUpdate();

			} catch (SQLException e) {
				i=0;
			}
			if (i > 0)
			{
				flag = true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
		return flag;
	}



	@Override
	public TB_User queryByAccount(String account) {
		TB_User tb_User = null;
		try
		{
			  sql = "select user.ID ,userAccount,userPwd,userName, user.userLevel,userUsedspace, "
    				+ "userLevel.space,userScore ,userRegTime , userStage  " + 
    				"from user,userLevel " + 
    				"where user.userlevel=userLevel.userlevel "+
    				" and userAccount = ?   ";
	 	    	pps = conn.prepareStatement(sql); 
	 	        pps.setString(1, account);		
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
	 	    		tb_User=new TB_User();
	 	    		tb_User.setID(re.getInt("ID"));
	 	    		tb_User.setUserAccount(re.getString("userAccount"));
	 	    		tb_User.setUserPwd(re.getString("userPwd"));
	 	    		tb_User.setUserName(re.getString("userName"));
	 	    		tb_User.setUserLevel(re.getString("userLevel"));
	 	    		tb_User.setUserUsedspace(re.getDouble("userUsedspace"));
	 	    		tb_User.setSpace(re.getInt("space"));
	 	    		tb_User.setUserScore(re.getInt("userScore"));
	 	    		tb_User.setUserRegTime(re.getString("userRegTime"));
	 	    		tb_User.setUserStage(re.getString("userStage"));
	 			}	 
	 	    	
	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}

		return tb_User;
	}
	
	@Override
	public boolean deleteByid(int uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterPassword(String newpwd,String account,String pwd) {
		try
		{
			flag = false;
			sql = "update user set userPwd =? where userAccount=? and  userPwd = ? ";

			pps = conn.prepareStatement(sql);
			pps.setString(1,newpwd);
			pps.setString(2,account);
			pps.setString(3,pwd);
			int i = pps.executeUpdate();
			if (i > 0)
			{
				flag = true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
		return flag;
	}

	@Override
	public boolean alterUserMsg(String username,String account) {
		try
		{
			flag = false;
			sql = "update user set userName =? where userAccount=? ";

			pps = conn.prepareStatement(sql);
			pps.setString(1,username);
			pps.setString(2,account);
			int i = pps.executeUpdate();
			if (i > 0)
			{
				flag = true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
		return flag;
	}
	@Override
	public boolean alterUserScore(int updatescore, String account) {
		try
		{
		flag = false;
		sql = "update user set userScore =? where userAccount=? ";
		pps = conn.prepareStatement(sql);
		pps.setInt(1,updatescore);
		pps.setString(2,account);
		int i = pps.executeUpdate();
		if (i > 0)
		{
			flag = true;
		}

	} catch (SQLException e)
	{
		e.printStackTrace();
	} finally
	{
		ConnectionUtil.getInstance().closscon(null, pps, conn);
	}
	return flag;
	}
	@Override
	public boolean alterUserLevel(String levelname,String account) {
		try
		{
		flag = false;
		sql = "update user set userLevel =? where userAccount=? ";
		pps = conn.prepareStatement(sql);
		pps.setString(1,levelname);
		pps.setString(2,account);
		int i = pps.executeUpdate();
		if (i > 0)
		{
			flag = true;
		}

	} catch (SQLException e)
	{
		e.printStackTrace();
	} finally
	{
		ConnectionUtil.getInstance().closscon(null, pps, conn);
	}
	return flag;
	}
	@Override
	public boolean alterUserUsedSpace(double UsedSpace,String account) {
		try
		{
		flag = false;
		sql = "update user set userUsedspace =? where userAccount=? ";
		pps = conn.prepareStatement(sql);
		pps.setDouble(1,UsedSpace);
		pps.setString(2,account);
		int i = pps.executeUpdate();
		if (i > 0)
		{
			flag = true;
		}

	} catch (SQLException e)
	{
		e.printStackTrace();
	} finally
	{
		ConnectionUtil.getInstance().closscon(null, pps, conn);
	}
	return flag;
	}
}
