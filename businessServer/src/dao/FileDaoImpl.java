package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabean.BackAccout;
import javabean.TB_File;
import javabean.TB_User;



public class FileDaoImpl implements FileDao {
	private static ArrayList<TB_File> list = new ArrayList<TB_File>();

	private String sql;
	private Connection conn;
	private PreparedStatement pps; // 执行sql语句对象

	private boolean flag;
	public FileDaoImpl() {
		conn = ConnectionUtil.getInstance().getConnection();
	}
	@Override
	public ArrayList<TB_File> queryALL() {
		list.clear();
		TB_File tb_File=null;    
		try {
    		String  sql = "  select * from file  ";
	 	    	pps = conn.prepareStatement(sql); 
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
	 	    		tb_File=new TB_File();
	 	    		tb_File.setID(re.getInt("ID"));
	 	    		tb_File.setUserAccount(re.getString("userAccount"));
	 	    		tb_File.setFileName(re.getString("fileName"));
	 	    		tb_File.setFilePath(re.getString("filePath"));
	 	    		tb_File.setFileSize(re.getString("fileSize"));
	 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
	 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 	    		list.add(tb_File);	  
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
	public ArrayList<TB_File> queryALL(int currentPage) {
		  TB_File tb_File=null;    
      	try {
	    	if (currentPage>1) {
				int s=5*(currentPage-1);//s表示LIMIT从哪个点开始查询
				if (s<list.size()) {
					sql = "select * from file order by ID  limit "+(s)
							 +",5  ";
				  System.out.println(sql);
				}			  
				}else {
					sql = "select * from file order by ID  limit "
							 +" 5 ";
				}
	    	 list.clear();//初始化，重新建立集合
	    	 pps = conn.prepareStatement(sql); 
	         System.out.println(sql);
	         ResultSet  re=pps.executeQuery();
	         while (re.next()) {
	        	 tb_File=new TB_File();
	        	 tb_File.setID(re.getInt("ID"));
	 	    		tb_File.setUserAccount(re.getString("userAccount"));
	 	    		tb_File.setFileName(re.getString("fileName"));
	 	    		tb_File.setFilePath(re.getString("filePath"));
	 	    		tb_File.setFileSize(re.getString("fileSize"));
	 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
	 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 	    		list.add(tb_File);	  	  
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
	public ArrayList<TB_File> queryALL(String filename) {
		list.clear();
		TB_File tb_File=null;    
		try {
    		String  sql = "  select * from file  where filename like ?  ";
	 	    	pps = conn.prepareStatement(sql); 
	 	        pps.setString(1, "%"+filename+"%");		
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
		        	 tb_File=new TB_File();
		        	 tb_File.setID(re.getInt("ID"));
		 	    		tb_File.setUserAccount(re.getString("userAccount"));
		 	    		tb_File.setFileName(re.getString("fileName"));
		 	    		tb_File.setFilePath(re.getString("filePath"));
		 	    		tb_File.setFileSize(re.getString("fileSize"));
		 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
		 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 	    		list.add(tb_File);	  	  
	 	    	  
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
	public ArrayList<TB_File> queryALL(int currentPage, String filename) {
		TB_File tb_File = null;
		try {
			if (currentPage > 1) {
				int s = 5 * (currentPage - 1);// s表示LIMIT从哪个点开始查询
				if (s < list.size()) {
					sql = " select * from file  where filename like ? " + " order by ID limit " + (s) + ",5   ";
				}
			} else {
				sql = " select * from file  where filename like ? " + " order by ID  limit " + " 5  ";
			}
			list.clear();// 初始化，重新建立集合
			pps = conn.prepareStatement(sql);
			System.out.println(sql);
			pps.setString(1, "%" + filename + "%");

			ResultSet re = pps.executeQuery();
			while (re.next()) {
				tb_File = new TB_File();
				tb_File.setID(re.getInt("ID"));
 	    		tb_File.setUserAccount(re.getString("userAccount"));
 	    		tb_File.setFileName(re.getString("fileName"));
 	    		tb_File.setFilePath(re.getString("filePath"));
 	    		tb_File.setFileSize(re.getString("fileSize"));
 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
 	    		tb_File.setFatherPath(re.getString("fatherPath"));
				list.add(tb_File);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
		return list;
	}
	@Override
	public boolean addfile(TB_File tb_File) {
		try
		{
			flag = false;
			sql = "insert into file (userAccount,fileName,filePath,fileSize,fileTypeName,actTime,fatherPath) " + 
					"  values  (?,?,?,?,?,?,?)  ";
			pps = conn.prepareStatement(sql);

			pps.setString(1, tb_File.getUserAccount());
			pps.setString(2, tb_File.getFileName());
			pps.setString(3, tb_File.getFilePath());
			pps.setString(4, tb_File.getFileSize());
			pps.setString(5, tb_File.getFileTypeName());
			pps.setString(6, tb_File.getActTime());
			pps.setString(7, tb_File.getFatherPath());

			int i;		
				 i = pps.executeUpdate();
			if (i > 0)
			{
				System.out.println("dao添加了");
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
	public ArrayList<TB_File> query(String filename) {
		list.clear();
		TB_File tb_File=null;    
		try {
    		String  sql = "  select * from file  where filename = ?  ";
	 	    	pps = conn.prepareStatement(sql); 
	 	        pps.setString(1, filename);		
	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
		        	 tb_File=new TB_File();
		        	 tb_File.setID(re.getInt("ID"));
		 	    		tb_File.setUserAccount(re.getString("userAccount"));
		 	    		tb_File.setFileName(re.getString("fileName"));
		 	    		tb_File.setFilePath(re.getString("filePath"));
		 	    		tb_File.setFileSize(re.getString("fileSize"));
		 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
		 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 	    		list.add(tb_File);	  	  	 	    	  
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
	public ArrayList<TB_File> queryalltype(String account,String fatherpath) {
		list.clear();
		TB_File tb_File=null;    
		try {
    		String  sql = "  select fileName,fileType,filePath,fatherPath from file,fileType"
    				+ "  where file.fileTypeName= fileType  .fileTypeName  "
    				+ " and userAccount=?  "
    				+ " and fatherPath=? ";
	 	    	pps = conn.prepareStatement(sql); 
				pps.setString(1, account);
				pps.setString(2, fatherpath);

	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
	 	    		tb_File=new TB_File();
	 	    		tb_File.setFilePath(re.getString("filePath"));
	 	    		tb_File.setFileName(re.getString("fileName"));
	 	    		tb_File.setFileType(re.getString("fileType"));
	 	    		tb_File.setFatherPath(re.getString("fatherPath"));

	 	    		list.add(tb_File);	  
	 			}	 	 	    		 	  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionUtil.getInstance().closscon(null, pps, conn);
			}
		System.out.println("list"+list);
		return list;
	}
	@Override
	public ArrayList<TB_File> querytype(String typename) {
		list.clear();
		TB_File tb_File = null;
		try
		{
			tb_File = new TB_File();
			sql = "select * from fileType" + 
					"   where fileTypeName=? ";
			pps = conn.prepareStatement(sql);
			pps.setString(1, typename);
			ResultSet re = pps.executeQuery();
			
			while (re.next())
			{
				tb_File=new TB_File();
				tb_File.setID(re.getLong("ID"));
 	    		tb_File.setFileType(re.getString("fileType"));
 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));		
 	    		list.add(tb_File);	  

			}
	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}

		return list;
	}
	@Override
	public boolean addfileype(String typename) {
		try
		{
			flag = false;
			sql = "insert into fileType (fileType,fileTypeName) " + 
					"  values  ('其他',?)  ";
			pps = conn.prepareStatement(sql);			
			pps.setString(1, typename);
			int i;		
				 i = pps.executeUpdate();
			if (i > 0)
			{
				System.out.println("dao添加了");
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
	public ArrayList<TB_File> queryfile(String type,String account) {
		list.clear();
		TB_File tb_File = null;
		try
		{
			tb_File = new TB_File();
			sql = " select fileName,fileType from file,fileType  " + 
					"   where file.fileTypeName= fileType  .fileTypeName " + 
					"   and fileType=?"
					+ "  and userAccount=? ";
			pps = conn.prepareStatement(sql);
			pps.setString(1, type);
			pps.setString(2, account);

			
			ResultSet re = pps.executeQuery();			
			while (re.next())
			{
				tb_File=new TB_File();
 	    		tb_File.setFileName(re.getString("fileName"));
 	    		tb_File.setFileType(re.getString("fileType"));		
 	    		list.add(tb_File);	  
			}
	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}

		return list;
	}
	@Override
	public boolean deleteByfilename(String filename, String fatherpath) {
		try
		{
			flag = false;
		
		sql = "delete from file where fileName = ?"
				+ "and fatherPath =? ";

		pps = conn.prepareStatement(sql);
		pps.setString(1, filename);
		pps.setString(2, fatherpath);
		int i = pps.executeUpdate();
		if (i > 0)
		{
			flag = true;
		}	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
	
		return flag;
	}
	@Override
	public TB_File queryfi(String filename,String account,String fatherpath) {
		TB_File tb_File=null;    
		try {
    		String  sql = "  select * from file  where filename = ? and userAccount=?"
    				+ " and fatherPath=?  ";
	 	    	pps = conn.prepareStatement(sql); 
	 	        pps.setString(1, filename);	
	 	        pps.setString(2, account);
	 	        pps.setString(3, fatherpath);

	 	    	ResultSet re=pps.executeQuery();
	 	    	while (re.next()) {
		        	 tb_File=new TB_File();
		        	 tb_File.setID(re.getInt("ID"));
		 	    		tb_File.setUserAccount(re.getString("userAccount"));
		 	    		tb_File.setFileName(re.getString("fileName"));
		 	    		tb_File.setFilePath(re.getString("filePath"));
		 	    		tb_File.setFileSize(re.getString("fileSize"));
		 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
		 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 			}	  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionUtil.getInstance().closscon(null, pps, conn);
			}

		return tb_File;
	}
	@Override
	public boolean alterfilename(String rename,String filepath,String thisfilename, String fatherpath) {
		try
		{
			flag = false;
			sql = "update file set fileName =? ,  filePath= ? "
					+ "where fileName=? and  fatherPath = ? ";
           System.out.println(sql);
			pps = conn.prepareStatement(sql);
			pps.setString(1,rename);			
			pps.setString(2,filepath);
			pps.setString(3,thisfilename);
			pps.setString(4,fatherpath);
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
	public boolean alterfilepath(String newpath, String newfatherpath, String thisfilename, String fatherpath) {
		try
		{
			flag = false;
			sql = "update file set filePath =? ,  fatherPath= ? "
					+ "where fileName=? and  fatherPath = ? ";
            System.out.println(sql);
			pps = conn.prepareStatement(sql);
			pps.setString(1,newpath);			
			pps.setString(2,newfatherpath);
			pps.setString(3,thisfilename);
			pps.setString(4,fatherpath);
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
	public boolean alterfilesize(String filesize, String path) {
		try
		{
			flag = false;
			sql = "update file set fileSize =?   "
					+ "where filePath=?  ";
            System.out.println(sql);
			pps = conn.prepareStatement(sql);
			pps.setString(1,filesize);			
			pps.setString(2,path);
			int i = pps.executeUpdate();
			if (i > 0)
			{    System.out.println("我想我 是有改了");
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
	public ArrayList<TB_File> querybyfathpath( String fatherpath) {
		list.clear();
		TB_File tb_File=null;    
		try {
    		String  sql = "  select * from file  where fatherPath like ? ";
	 	    	pps = conn.prepareStatement(sql); 
				pps.setString(1,fatherpath+"%");			

	 	    	ResultSet re=pps.executeQuery();

	 	    	while (re.next()) {
	 	    		tb_File=new TB_File();
	 	    		tb_File.setID(re.getInt("ID"));
	 	    		tb_File.setUserAccount(re.getString("userAccount"));
	 	    		tb_File.setFileName(re.getString("fileName"));
	 	    		tb_File.setFilePath(re.getString("filePath"));
	 	    		tb_File.setFileSize(re.getString("fileSize"));
	 	    		tb_File.setFileTypeName(re.getString("fileTypeName"));
	 	    		tb_File.setFatherPath(re.getString("fatherPath"));
	 	    		list.add(tb_File);	  
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
	public boolean alterfilepath(String newpath, String fatherpath, String path) {
		try
		{
			flag = false;
			sql = "update file set filePath =?  ,fatherPath=?  "
					+ "where filePath=?  ";
            System.out.println(sql);
			pps = conn.prepareStatement(sql);
			pps.setString(1,newpath);			
			pps.setString(2,fatherpath);
			pps.setString(3,path);

			int i = pps.executeUpdate();
			if (i > 0)
			{    
				System.out.println("我想我 是有改了");
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
	public boolean deleteByfatherpath(String fatherpath) {
		try
		{
			flag = false;
		
		sql = "delete from file where  fatherPath like   ? ";

		pps = conn.prepareStatement(sql);
		pps.setString(1, fatherpath+"%");
		int i = pps.executeUpdate();
		if (i > 0)
		{
			flag = true;
		}	
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally {
			ConnectionUtil.getInstance().closscon(null, pps, conn);
		}
	
		return flag;
	}

	
}