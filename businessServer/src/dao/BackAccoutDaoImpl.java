package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.BackAccout;
import javabean.TB_User;

public class BackAccoutDaoImpl implements BackAccoutDao {
	private static ArrayList<TB_User> list = new ArrayList<TB_User>();

	private String sql;
	private Connection conn;
	private PreparedStatement pps; // 执行sql语句对象

	private boolean flag;
	public BackAccoutDaoImpl() {
		conn = ConnectionUtil.getInstance().getConnection();
	}
	@Override
	public BackAccout queryByAccount(String account) {

		BackAccout backAccount = null;
		try
		{
			
			sql = "select * from backuser where account = ?";
			pps = conn.prepareStatement(sql);
			pps.setString(1, account);
			
			
			ResultSet rs = pps.executeQuery();
			
			while (rs.next())
			{
				backAccount = new BackAccout();
				backAccount.setID(rs.getInt("ID"));
				backAccount.setAccount(rs.getString("account"));
				backAccount.setPwd(rs.getString("pwd"));				
			}
	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}

		return backAccount;
	}
	@Override
	public ArrayList<TB_User> queryALL( ) {
		list.clear();
        TB_User tb_user=null;    
		try {
    		String  sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
    				"from user,userLevel " + 
    				"where user.userlevel=userLevel.userlevel order by user.ID ";
	 	    	pps = conn.prepareStatement(sql); 
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
	 	    		tb_user=new TB_User();
	 	    		tb_user.setID(re.getInt("ID"));
	 	    		tb_user.setUserAccount(re.getString("userAccount"));
	 	    		tb_user.setUserName(re.getString("userName"));
	 	    		tb_user.setUserLevel(re.getString("userLevel"));
	 	    		tb_user.setUserUsedspace(re.getInt("userUsedspace"));
	 	    		tb_user.setSpace(re.getInt("space"));
	 	    		tb_user.setUserScore(re.getInt("userScore"));
	 	    		tb_user.setUserRegTime(re.getString("userRegTime"));
	 	    		tb_user.setUserStage(re.getString("userStage"));
	 	    		list.add(tb_user);	  
	 			}	 
	 	    	
	 	  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionUtil.getInstance().closscon(null, pps, conn);
			}
		return list;
	}
	@Override
	public ArrayList<TB_User> queryALL(int currentPage) {
            TB_User tb_user=null;    
         	try {
	    	if (currentPage>1) {
				int s=5*(currentPage-1);//s表示LIMIT从哪个点开始查询
				if (s<list.size()) {
					sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
		    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
		    				"from user,userLevel " + 
		    				"where user.userlevel=userLevel.userlevel "+" order by user.ID  limit "+(s)
							 +",5  ";
				  System.out.println(sql);
				}			  
				}else {
					sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
		    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
		    				"from user,userLevel " + 
		    				"where user.userlevel=userLevel.userlevel "+"order by user.ID  limit "
							 +" 5 ";
				}
	    	 list.clear();//初始化，重新建立集合
	    	 pps = conn.prepareStatement(sql); 
	         System.out.println(sql);
	         ResultSet  re=pps.executeQuery();
	         while (re.next()) {
	    		tb_user=new TB_User();
	    		tb_user.setID(re.getInt("ID"));
	    		tb_user.setUserAccount(re.getString("userAccount"));
	    		tb_user.setUserName(re.getString("userName"));
	    		tb_user.setUserLevel(re.getString("userLevel"));
	    		tb_user.setUserUsedspace(re.getInt("userUsedspace"));
	    		tb_user.setSpace(re.getInt("space"));
	    		tb_user.setUserScore(re.getInt("userScore"));
 	    		tb_user.setUserRegTime(re.getString("userRegTime"));

	    		tb_user.setUserStage(re.getString("userStage"));
	    		list.add(tb_user);	  
	         }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		ConnectionUtil.getInstance().closscon(null, pps, conn);
	}
return list;
	}
	@Override
	public ArrayList<TB_User> queryALL(String account) {
		list.clear();
        TB_User tb_user=null;    
		try {
    		String  sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
    				"from user,userLevel " + 
    				"where user.userlevel=userLevel.userlevel "+
    				" and userAccount like ?  order by user.ID ";
	 	    	pps = conn.prepareStatement(sql); 
	 	        pps.setString(1, "%"+account+"%");		
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
	 	    		tb_user=new TB_User();
	 	    		tb_user.setID(re.getInt("ID"));
	 	    		tb_user.setUserAccount(re.getString("userAccount"));
	 	    		tb_user.setUserName(re.getString("userName"));
	 	    		tb_user.setUserLevel(re.getString("userLevel"));
	 	    		tb_user.setUserUsedspace(re.getInt("userUsedspace"));
	 	    		tb_user.setSpace(re.getInt("space"));
	 	    		tb_user.setUserScore(re.getInt("userScore"));
	 	    		tb_user.setUserRegTime(re.getString("userRegTime"));
	 	    		tb_user.setUserStage(re.getString("userStage"));
	 	    		list.add(tb_user);	  
	 			}	 
	 	    	
	 	  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionUtil.getInstance().closscon(null, pps, conn);
			}
		return list;
	}
	@Override
	public ArrayList<TB_User> queryALL(int currentPage, String account) {
		 TB_User tb_user=null;    
      	try {
	    	if (currentPage>1) {
				int s=5*(currentPage-1);//s表示LIMIT从哪个点开始查询
				if (s<list.size()) {
					sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
		    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
		    				"from user,userLevel " + 
		    				"where user.userlevel=userLevel.userlevel "
		    				+ " and userAccount like ? "+" order by user.ID limit "+(s)
							 +",5   ";
				}			  
				}else {
					sql = "select user.ID ,userAccount,userName, user.userLevel,userUsedspace, "
		    				+ "userLevel.space,userScore,userRegTime,userStage  " + 
		    				"from user,userLevel " + 
		    				"where user.userlevel=userLevel.userlevel "
		    				+ " and userAccount like ? "+" order by user.ID  limit "
							 +" 5  ";
				}
	    	 list.clear();//初始化，重新建立集合
	    	 pps = conn.prepareStatement(sql); 
	         System.out.println(sql);
	 	      pps.setString(1, "%"+account+"%");		

	         ResultSet  re=pps.executeQuery();
	         while (re.next()) {
	    		tb_user=new TB_User();
	    		tb_user.setID(re.getInt("ID"));
	    		tb_user.setUserAccount(re.getString("userAccount"));
	    		tb_user.setUserName(re.getString("userName"));
	    		tb_user.setUserLevel(re.getString("userLevel"));
	    		tb_user.setUserUsedspace(re.getInt("userUsedspace"));
	    		tb_user.setSpace(re.getInt("space"));
	    		tb_user.setUserScore(re.getInt("userScore"));
 	    		tb_user.setUserRegTime(re.getString("userRegTime"));

	    		tb_user.setUserStage(re.getString("userStage"));
	    		list.add(tb_user);	  
	         }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		ConnectionUtil.getInstance().closscon(null, pps, conn);
	}
          return list;
	}
	@Override
	public boolean alterUserState(String state,String wheres) {
	     
		try
		{
			flag = false;
			sql = "update user set userStage =? where userAccount=? ";

			pps = conn.prepareStatement(sql);
			pps.setString(1,state);
			pps.setString(2,wheres);

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
