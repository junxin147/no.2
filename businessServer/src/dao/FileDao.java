package dao;

import java.util.ArrayList;

import javabean.TB_File;
import javabean.TB_User;

public interface FileDao {
	    /**
	     *  //全表查询文件信息
	     * @return
	     */
		public ArrayList<TB_File> queryALL();
		/**
		 *  //全表分页
		 * @param currentPage
		 * @return
		 */
		public ArrayList<TB_File> queryALL(int currentPage);
		/** 
		 * //通过文件名查询查询文件信息
		 * @param filename
		 * @return
		 */
		public ArrayList<TB_File> queryALL(String filename);
		 /** 
		  * //通过文件名查询查询文件信息分页
		  * @param currentPage
		  * @param filename
		  * @return
		  */
	    public ArrayList<TB_File> queryALL(int currentPage,String filename);	
	  /**
	   *  //通过文件名查询查询文件信息
	   * @param filename
	   * @return
	   */
	  		public ArrayList<TB_File> query(String filename);
	  /**
	   * 	//通过文件名s查询精确查询文件信息
	   * @param filename
	   * @param account
	 * @param fatherpath 
	   * @return
	   */
	  		public TB_File queryfi(String filename,String account, String fatherpath);
	    /**
	     *  //添加 文件信信息  注册
	     * @param tb_File
	     * @return
	     */
	  	public boolean addfile(TB_File tb_File);
	    /**
	     *  //全部文件类型信息查询
	     * @param account
	     * @return
	     */
  		public ArrayList<TB_File> queryalltype(String account,String fatherpath);
        /**
         *  //文件后缀名查询文件类型归属
         * @param typename
         * @return
         */
  		public ArrayList<TB_File> querytype(String typename);
        /**
         *   //将新的文件名弄进去到新的文件类型，并且归属于其他类型
         * @param typename
         * @return
         */
	  	public boolean addfileype(String typename);
	/**
	 *  //根据文件文件类型归属查询相对应的所有文件
	 * @param type
	 * @param account
	 * @return
	 */
  		public ArrayList<TB_File> queryfile(String type,String account);
  		/**
  		 *  //根据文件名 删除文件 业务
  		 * @param filename
  		 * @param fatherpath 
  		 * @return
  		 */
		public boolean deleteByfilename(String filename, String fatherpath);
	/**
	 * 	//修改文件的名字操作
	 * @param rename
	 * @param thisfilename
	 * @param backaccount
	 * @return
	 */
		public boolean alterfilename(String rename,String filepath,String thisfilename, String fatherpath);
		/**
		 * 	//模糊查询类父级路径查询操作
		 * @param rename
		 * @param thisfilename
		 * @param backaccount
		 * @return
		 */
			public ArrayList<TB_File>  querybyfathpath(String fatherpath);
		
		/**
		 * 	//修改文件的存放位置操作
		 * @param rename
		 * @param thisfilename
		 * @param backaccount
		 * @return
		 */
			public boolean alterfilepath(String newpath,String newfatherpath,String thisfilename, String fatherpath);
			/**
			 * 	//修改文件夹大小操作
			 * @param rename
			 * @param thisfilename
			 * @param backaccount
			 * @return
			 */
				public boolean alterfilesize(String filesize,String thisfilename);
				/**
				 * 遍历修改文件路径和父级路径
				 * @param filesize
				 * @param thisfilename
				 * @return
				 */
				public boolean alterfilepath(String newpath,String fatherpath,String path);	
				/**
				 * 根据父级路径模糊
				 * @param fatherpath
				 * @return
				 */
				public boolean deleteByfatherpath( String fatherpath) ;

}
