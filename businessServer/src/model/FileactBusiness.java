package model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import dao.DaoFactory;
import javabean.FileremaneMsg;
import javabean.Filetype;
import javabean.TB_File;
import javabean.TB_User;

public class FileactBusiness {

	private boolean flag;
	private Socket sc;

	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("##");
		switch (arr[0]) {
		case "delete":
			str = delete(arr[1]);
			break;
		// "skyDiver://fileact@@rename##" + fmsg.toString()
		case "rename":
			str = rename(arr[1]);
			break;
		// "skyDiver://fileact@@newfolder##" + file.toString()
		case "newfolder":
			str = newfolder(arr[1]);
			break;
		// "skyDiver://fileact@@openfolder##" + tb_File.toString()
		case "openfolder":
			str = openfolder(arr[1]);
			break;
//			"skyDiver://fileact@@move##" + tb_File.toString()	
		case "move":
			str = move(arr[1]);
			break;

		}
		return str;
	}

	/**
	 * 移动文件
	 * 
	 * @param string
	 * @return
	 */
	private String move(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);

		TB_File thisFile = DaoFactory.getFileDaoImpl().queryfi(tb_File.getFileName(), tb_File.getUserAccount(),
				tb_File.getFatherPath());
		TB_File newFile = DaoFactory.getFileDaoImpl().queryfi(tb_File.getFileName(), tb_File.getUserAccount(),
				tb_File.getNewfatherPath());
		if (newFile != null) {
			res = "Fileact://move@@false";
			return res;
		}
		TB_File Fileitem = DaoFactory.getFileDaoImpl().queryfi(tb_File.getFileitme(), tb_File.getUserAccount(),
				tb_File.getFatherPath());

		tb_File.setFileSize(
				String.valueOf(Integer.valueOf(thisFile.getFileSize()) + Integer.valueOf(Fileitem.getFileSize())));
		flag = DaoFactory.getFileDaoImpl().alterfilepath(tb_File.getNewfilePath(), tb_File.getNewfatherPath(),
				tb_File.getFileName(), tb_File.getFatherPath());
		if (flag = true) {
			DaoFactory.getFileDaoImpl().alterfilesize(tb_File.getFileSize(), tb_File.getNewfatherPath());
			try {
				sc = new Socket("127.0.0.1", 10001);
				FileRunnable fr = new FileRunnable(sc);
				Thread t1 = new Thread(fr);
				t1.start();
				System.out.println(sc);
				fr.sendMsg("move://" + tb_File.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res = "Fileact://move@@" + tb_File.toString();

		}

		return res;

	}

	/**
	 * 打开文件夹
	 * 
	 * @param string
	 * @return
	 */
	private String openfolder(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		ArrayList<TB_File> typelist = DaoFactory.getFileDaoImpl().queryalltype(tb_File.getUserAccount(),
				tb_File.getFatherPath());
		Filetype filetype = new Filetype();
		filetype.setArray(typelist);
		filetype.setFilepath(tb_File.getFatherPath());

		System.out.println(filetype.getFilepath());
		res = "Fileact://openfolder@@" + filetype.toString();
		return res;

	}

	/**
	 * 新建文件夹
	 * 
	 * @param string
	 * @return
	 */
	private String newfolder(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		
		TB_File newFile  	=DaoFactory.getFileDaoImpl().queryfi(tb_File.getFileName(), tb_File.getUserAccount(),
				tb_File.getFatherPath());		
		if (newFile!=null) {
			res = "Fileact://newfolder@@false";
			return res;
		}else {
			DaoFactory.getFileDaoImpl().addfile(tb_File);
			try {
				sc = new Socket("127.0.0.1", 10001);
				FileRunnable fr = new FileRunnable(sc);
				Thread t1 = new Thread(fr);
				t1.start();
				System.out.println(sc);
				fr.sendMsg("newfolder://" + tb_File.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<TB_File> typelist = DaoFactory.getFileDaoImpl().queryalltype(tb_File.getUserAccount(),
					tb_File.getFatherPath());
			Filetype filetype = new Filetype();
			filetype.setArray(typelist);

			res = "Fileact://newfolder@@" + filetype.toString();
			return res;
		}
		
	}

	/**
	 * 修改名字
	 * 
	 * @param msg
	 * @return
	 */
	private String rename(String msg) {
		System.out.println(msg);
		String res = "";
		FileremaneMsg fmsg = (FileremaneMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileremaneMsg.class);
		// 修改了文件或者文件的名，以及路径和父级路径
		String updatefilpath = fmsg.getFathetpath() + "/" + fmsg.getReanme();
		flag = DaoFactory.getFileDaoImpl().alterfilename(fmsg.getReanme(), updatefilpath, fmsg.getFilename(),
				fmsg.getFathetpath());
		if (flag = true) {

			if (fmsg.getFiletype().equals("文件夹")) {
				// 查询修改的文件夹名，名下的所有文件的相关信息，并进行遍历循环进行修改
				ArrayList<TB_File> arrylis = DaoFactory.getFileDaoImpl().querybyfathpath(fmsg.getPath());
				System.out.println(arrylis.size() + "+类似父级路径的还有这么多条");
				if (arrylis.size() > 0) {
					for (int i = 0; i < arrylis.size(); i++) {
						if (fmsg.getPath().length() == arrylis.get(i).getFatherPath().length()) {
							String[] filepathMsg = arrylis.get(i).getFilePath().split(fmsg.getPath());
							String newpath = updatefilpath + filepathMsg[1];
							boolean flag1 = DaoFactory.getFileDaoImpl().alterfilepath(newpath, updatefilpath,
									arrylis.get(i).getFilePath());
							if (flag1 = true) {
								System.out.println("我完美修改了");
							}
						} else {
							String[] filepathMsg = arrylis.get(i).getFilePath().split(fmsg.getPath());
							String[] fatherpatnMsg = arrylis.get(i).getFatherPath().split(fmsg.getPath());
							String newpath = updatefilpath + filepathMsg[1];
							String newfatherpath = updatefilpath + fatherpatnMsg[1];
							boolean flag1 = DaoFactory.getFileDaoImpl().alterfilepath(newpath, newfatherpath,
									arrylis.get(i).getFilePath());

							if (flag1 = true) {
								System.out.println("我完美修改了");
							}

						}

					}
				}
			}

			try {
				sc = new Socket("127.0.0.1", 10001);
				FileRunnable fr = new FileRunnable(sc);
				Thread t1 = new Thread(fr);
				t1.start();
				System.out.println(sc);
				fr.sendMsg("rename://" + fmsg.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			res = "Fileact://rename@@" + fmsg.toString();
		}
		return res;
	}

	/**
	 * 删除文件操作
	 * 
	 * @param msg
	 * @return
	 */

	private String delete(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);

//		if (!tb_File.getFileType().equals("文件夹")) {
		// 经过删除文件后的user表已使用空间的变化
		TB_File tbfile = DaoFactory.getFileDaoImpl().queryfi(tb_File.getFileName(), tb_File.getUserAccount(),
				tb_File.getFatherPath());
		System.out.println(tbfile);
		System.out.println(tbfile.getFileSize());
		if (tbfile.getFileSize()!="0") {		
			double a = (Double.valueOf(tbfile.getFileSize())) / (1024 * 1024);
			TB_User user = DaoFactory.getUserImpl().queryByAccount(tb_File.getUserAccount());
			;
			double s = user.getUserUsedspace() - a;
			DaoFactory.getUserImpl().alterUserUsedSpace(s, tb_File.getUserAccount());
			}
			TB_User user2 = DaoFactory.getUserImpl().queryByAccount(tb_File.getUserAccount());
			System.out.println(user2.getUserUsedspace() + " 经过删除后的剩下的已使用空间 ");	
		
		
	
		// 删除FILE表的数据
		flag = DaoFactory.getFileDaoImpl().deleteByfilename(tb_File.getFileName(), tb_File.getFatherPath());
		if (flag == true) {
			System.out.println("数据库删除了这个" + tb_File.getFileName());
			try {
				sc = new Socket("127.0.0.1", 10001);
				FileRunnable fr = new FileRunnable(sc);
				Thread t1 = new Thread(fr);
				t1.start();
				System.out.println(sc);
				fr.sendMsg("delete://" + tb_File.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tb_File.getFileType().equals("文件夹")) {
			ArrayList<TB_File> arrylis = DaoFactory.getFileDaoImpl().querybyfathpath(tb_File.getFilePath());
			System.out.println(arrylis.size() + "+类似父级路径的还有这么多条");
			if (arrylis.size()>0) {
			flag=	DaoFactory.getFileDaoImpl().deleteByfatherpath(tb_File.getFilePath());	 
			if (flag=true) {
				System.out.println("我已经删除子文件了");
			}
			
			}
		}
		
		
		res = "Fileact://delete@@" + tb_File.toString() + "##" + user2.toString();
//		}else {
//			//删除FILE表的数据
//					flag = DaoFactory.getFileDaoImpl().deleteByfilename(tb_File.getFileName(),tb_File.getFatherPath());
//					if (flag==true) {
//						System.out.println("数据库删除了这个"+tb_File.getFileName());
//						try {
//						sc = new Socket("127.0.0.1", 10001);		
//						FileRunnable fr = new FileRunnable(sc);
//						Thread t1 = new Thread(fr);
//						t1.start();
//						System.out.println(sc);
//						fr.sendMsg("delete://"+tb_File.toString());
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}  
////			res = "Fileact://delete@@"+tb_File.toString();
//			return res;
//		}

		return res;
	}

}
