package dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;
/**
 * 连接数据库单例模式
 * @author Alan
 *
 */
public class ConnectionUtil {
	private static ConnectionUtil util;
	private Connection con;
	private Properties properties;
	
	private ConnectionUtil() {		
		try {
			// 创建配置文件工具对象
			properties=new Properties();
			// 用工具加载并解析 config.properties 配置文件
			properties.load(new FileInputStream("database/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  /**
       *懒汉式单例模式
   * @return
   */
	public static synchronized ConnectionUtil getInstance(){
		if (util==null) {
			util=new ConnectionUtil();
		}
		return util;
	}
	/**
	     * 创建连接方式
	 * @return
	 */
	public Connection getConnection() {
		if (con==null) {
			try {
				Class.forName(properties.getProperty("driverClass"));
				con=DriverManager.getConnection(properties.getProperty("driverUrl"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return con;
	}
	/**
	 * 设置关闭方式
	 * @param stm
	 * @param pps
	 * @param con
	 */
	public void closscon(Statement stm,PreparedStatement pps,Connection con) {
		try {
		if (stm!=null) {			
			stm.close();			
			stm=null;
		}
		if (pps!=null) {
			pps.close();
			pps=null;
		}
		if (con!=null) {
			con.close();
			con=null;
			this.con =null;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
