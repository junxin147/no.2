package dao;


/**
 * DAO工厂模式
 * @author Alan
 *
 */
public class DaoFactory {
	public static BackAccoutDao getBackAccoutDaoImpl() 
	{
		return new BackAccoutDaoImpl();
	};

	public static UserDao getUserImpl() 
	{
		return new UserDaoImpl();
	};
	public static FileDao getFileDaoImpl() 
	{
		return new FileDaoImpl();
	};
}
