package model;

import java.util.ArrayList;

import dao.DaoFactory;
import javabean.FileMsg;
import javabean.Filetype;
import javabean.TB_File;

public class SortBusiness {

	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("##");
		switch (arr[0]) {
		case "image":
			str = image(arr[1]);
			break;
		case "text":
			str = text(arr[1]);
			break;
		case "music":
			str = music(arr[1]);
			break;
		case "ohter":
			str = ohter(arr[1]);
			break;
			
		case "allfile":
			str = allfile(arr[1]);
			break;
		}
		return str;
	}
	/**
	 * 全文件查询
	 * @param msg
	 * @return
	 */
	private String allfile(String msg) {
		System.out.println("全文件查询");
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);

		ArrayList<TB_File> typelist = DaoFactory.getFileDaoImpl().queryalltype(tb_File.getUserAccount(),tb_File.getFatherPath());
		Filetype filetype=new Filetype();
		filetype .setArray(typelist);
		res = "sort://allfile@@"+filetype.toString();
		return res;
	}
	/**
	 * 客户端其他类型文件查询
	 * @param string
	 * @return
	 */
private String ohter(String msg) {
	System.out.println(msg);
	String res = "";
	TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
	// 进行查询数据库业务处理
	 ArrayList<TB_File> filelist = DaoFactory.getFileDaoImpl().queryfile(tb_File.getFileType(),tb_File.getUserAccount());
	System.out.println(filelist.size());
    Filetype filetype=new Filetype();
    filetype.setArray(filelist);
     res="sort://ohter@@"+filetype.toString();
	return res;
	}
/**
 * 客户端音乐类型文件查询
 * @param string
 * @return
 */
private String music(String msg) {
	System.out.println(msg);
	String res = "";
	TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
	// 进行查询数据库业务处理
	 ArrayList<TB_File> filelist = DaoFactory.getFileDaoImpl().queryfile(tb_File.getFileType(),tb_File.getUserAccount());
	System.out.println(filelist.size());
    Filetype filetype=new Filetype();
    filetype.setArray(filelist);
     res="sort://music@@"+filetype.toString();
	return res;
	}
/**
 * 客户端文档类型文件查询
 * @param string
 * @return
 */
private String text(String msg) {
	System.out.println(msg);
	String res = "";
	TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
	// 进行查询数据库业务处理
	 ArrayList<TB_File> filelist = DaoFactory.getFileDaoImpl().queryfile(tb_File.getFileType(),tb_File.getUserAccount());
	System.out.println(filelist.size());
    Filetype filetype=new Filetype();
    filetype.setArray(filelist);
     res="sort://text@@"+filetype.toString();
	return res;
	}
/**
 * 客户端图片文档查询
 * @param msg
 * @return
 */
	private String image(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		// 进行查询数据库业务处理
		 ArrayList<TB_File> filelist = DaoFactory.getFileDaoImpl().queryfile(tb_File.getFileType(),tb_File.getUserAccount());
		System.out.println(filelist.size());
        Filetype filetype=new Filetype();
        filetype.setArray(filelist);
         res="sort://image@@"+filetype.toString();
		return res;
	}

}
