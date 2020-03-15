package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javabean.FileMsg;
import javabean.FileremaneMsg;
import javabean.TB_File;
import javabean.TB_User;

public class Business {

	private boolean flag = true;
	private fileRunnable fr;
	private ServerSocket serverSocket;
	private File f;
	private String res;

	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("://");

		File file;
		switch (arr[0]) {
		case "open":
			Fileinput f2 = new Fileinput();
			f2.UploadActive(arr[1]);
			break;
		// "opendownload://"+fileMsg.toString()
		case "opendownload":
			Filedown fd = new Filedown();
			fd.downActive(arr[1]);
			break;
		case "createfile":
			TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(arr[1], TB_User.class);
			f = new File("file/" + tb_User.getUserAccount());
			if (!f.exists()) {
				f.mkdirs();
			}
			break;
		case "delete":
			TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(arr[1], TB_File.class);
			file = new File(tb_File.getFatherPath() + "/" + tb_File.getFileName());
			deleteFile(file);
				
			break;
		// "rename://"+fmsg.toString()
		case "rename":
			FileremaneMsg fmsg = (FileremaneMsg) GsonUtil.getInstance().ObjectFromJson(arr[1], FileremaneMsg.class);
			String toBeRenamed = fmsg.getFathetpath() + "/" + fmsg.getFilename();
			String newFilename = fmsg.getFathetpath() + "/" + fmsg.getReanme();
			renameFile(toBeRenamed, newFilename);
			break;
		// newfolder://"+tb_File.toString()
		case "newfolder":
			str = newfolder(arr[1]);

			break;
		// fr.sendMsg("move://"+tb_File.toString());
		case "move":
			str = move(arr[1]);
			break;
		case "flypass":
			str = flypass(arr[1]);
			break;

		}
	return str;

	}
	/**
	 * 秒传功能
	 * @param string
	 * @return
	 */
private String flypass(String msg) {
	res = "";
	TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
	byte[] arr = new byte[1024];
	File fileOld = new File(tb_File.getOldfilePath());  
	File fileNew = new File(tb_File.getFilePath());  
	if(fileOld.exists()){  
	    try {  
	        FileInputStream fis = new FileInputStream(fileOld);  
	        FileOutputStream fos = new FileOutputStream(fileNew);  
	        int read = 0;  
	        while ((read = fis.read(arr)) != -1) {  
	            fos.write(arr,0,read);  
	            fos.flush();  
	        }  
	        fos.close();  
	        fis.close();  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
	}
	return res;

}



/**
 * 删除文件以及多级文件夹的方法
 * @param file
 */
	private void deleteFile(File file) {
		if (file.exists()) {//判断文件是否存在
			if (file.isFile()) {//判断是否是文件
			file.delete();//删除文件
			} else if (file.isDirectory()) {//否则如果它是一个目录
			File[] files = file.listFiles();//声明目录下所有的文件 files[];
			for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
			deleteFile(files[i]);//把每个文件用这个方法进行迭代
			}
			file.delete();//删除文件夹
			}
			} else {
			System.out.println("所删除的文件不存在");
			}		
	}

	/**
	 * 移动文件
	 * 
	 * @param string
	 * @return
	 */
	private String move(String msg) {
		res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		try {
			File file = new File(tb_File.getFilePath()); // 源文件
			if (file.renameTo(new File(tb_File.getNewfilePath())))// 源文件移动至目标文件目录
			{
				System.out.println("输出移动成功");// 输出移动成功
			} else {
				System.out.println("输出移动失败");// 输出移动失败
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 新建文件夹
	 * 
	 * @param string
	 * @return
	 */
	private String newfolder(String msg) {
		res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		f = new File(tb_File.getFilePath());
		System.out.println(f);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("新建 了");
			f.mkdirs();
		}
		return res;
	}

	/**
	 * 修改文件呢名
	 * 
	 * @param file
	 * @param toFile
	 */
	public void renameFile(String file, String toFile) {

		File toBeRenamed = new File(file);
		// 检查要重命名的文件是否存在，
		if (!toBeRenamed.exists()) {
			System.out.println("文件不存在: " + file);
			return;
		}
		File newFile = new File(toFile);
		// 修改文件名
		if (toBeRenamed.renameTo(newFile)) {
			System.out.println("文件已重新命名了.");
		} else {
			System.out.println("重命名出错了");
		}

	}

}
