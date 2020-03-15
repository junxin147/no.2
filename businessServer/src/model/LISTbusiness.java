package model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import org.omg.CORBA.DATA_CONVERSION;

import dao.DaoFactory;
import javabean.FileMsg;
import javabean.Filetype;
import javabean.PwdMsg;
import javabean.TB_File;
import javabean.TB_User;
import view.BusinessView;

public class LISTbusiness {
	
	private BusinessView mainframe;
	public LISTbusiness(BusinessView mainframe) {
	this.	mainframe=mainframe;
		// TODO Auto-generated constructor stub
	}
	private boolean flag;
	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("##");
		switch (arr[0]) {
		case "input":
			str = input(arr[1]);
			break;
			//"skyDiver://LIST@@download##" + fileMsg.toString()	
		case "download":
			str = download(arr[1]);
			break;	
		}
		
		return str;
	}
/**
 * 检测数据库是否存在，进行发消息给文件服务器开启下载通道，也通知客户端准备下载
 * @param string
 * @return
 */
	private String download(String msg) {
		System.out.println(msg);
		String reg = "";
		FileMsg fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);
        TB_File tb_File=DaoFactory.getFileDaoImpl().queryfi(fileMsg.getName(),fileMsg.getUseraccount(),fileMsg.getFatherpath());
       if (tb_File!=null) {
    	   
    	   fileMsg.setSize(tb_File.getFileSize());
    	   System.out.println(fileMsg.getSize()+"获得的大小");
    	// 设置连接socket并创建线程
			Socket sc;
			reg="download://success@@"+fileMsg.toString();
			try {
				sc = new Socket("127.0.0.1", 10001);
			
			FileRunnable fr = new FileRunnable(sc);
			Thread t1 = new Thread(fr);
			t1.start();
			System.out.println(sc);
			mainframe.getTextArea1().append("用户"+fileMsg.getUseraccount()+"正在下载"+fileMsg.getName());
			mainframe.getTextArea1().append("\r\n");
			fr.sendMsg("opendownload://"+fileMsg.toString());
			System.out.println(sc);
			return reg;      

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }else {
			reg="download://false";

	}
		
		
		return reg;
		
	}
/**
 * 查询是否数据库存在，进行发消息给文件服务器开启通道，也通知服务器准备上传
 * @param msg
 * @return
 */
	private String input(String msg) {
		System.out.println(msg);
		String reg = "";
		FileMsg fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);
        TB_File tb_File=DaoFactory.getFileDaoImpl().queryfi(fileMsg.getName(),fileMsg.getUseraccount(),fileMsg.getFatherpath());
//		ArrayList<TB_File> arrlist = DaoFactory.getFileDaoImpl().queryALL(fileMsg.getName(),fileMsg.getUseraccount());
		TB_User user = DaoFactory.getUserImpl().queryByAccount(fileMsg.getUseraccount());
		double freespace = (user.getSpace()-user.getUserUsedspace())*1024*1024;
		if (Double.valueOf(fileMsg.getSize())<freespace) {
		ArrayList<TB_File> tbFile=DaoFactory.getFileDaoImpl().query(fileMsg.getName());		
			if (tbFile.size()>0) {
				for (int i = 0; i <tbFile.size(); i++) {
					if (fileMsg.getSize().equals(tbFile.get(i).getFileSize())	) {
						TB_File filetb=new TB_File();
						filetb.setFileName(fileMsg.getName());
						filetb.setUserAccount(fileMsg.getUseraccount());
						filetb.setFilePath( fileMsg.getFatherpath() + "/" + fileMsg.getName());
						filetb.setFileSize(fileMsg.getSize());
						filetb.setFatherPath( fileMsg.getFatherpath());
						String nameSuffix2 = fileMsg.getName().substring(fileMsg.getName().lastIndexOf(".") + 1);
						System.out.println(nameSuffix2);// txt
						filetb.setFileTypeName(nameSuffix2);
						filetb.setActTime(new Date().toLocaleString());
						
						// 进行添加业务操作数据库业务处理
						flag = DaoFactory.getFileDaoImpl().addfile(filetb);
						filetb.setOldfilePath(tbFile.get(i).getFilePath());
						// 设置连接socket并创建线程s
						Socket sc;
						try {
							sc = new Socket("127.0.0.1", 10001);
						
						FileRunnable fr = new FileRunnable(sc);
						Thread t1 = new Thread(fr);
						t1.start();
						fr.sendMsg("flypass://"+filetb.toString());
						System.out.println(sc);
						mainframe.getTextArea1().setEditable(true);
						mainframe.getTextArea1().append("用户"+filetb.getUserAccount()+"的网盘端因为后台有类似文件，已经存了"+
								filetb.getFileName());
						mainframe.getTextArea1().append("\r\n");
						mainframe.getTextArea1().setEditable(false);
						 ArrayList<TB_File> typelist1 = DaoFactory.getFileDaoImpl().queryalltype(filetb.getUserAccount(),filetb.getFatherPath());
							Filetype filetype1 = new Filetype();
							filetype1.setArray(typelist1);
						reg = "input://flypass@@"+filetype1.toString();
						return reg;
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
			try {
				if (tb_File == null) {
					
					// 设置连接socket并创建线程s
					Socket sc;
					reg="input://success@@"+fileMsg.toString();
					sc = new Socket("127.0.0.1", 10001);
					FileRunnable fr = new FileRunnable(sc);
					Thread t1 = new Thread(fr);
					t1.start();
					System.out.println(sc);
					fr.sendMsg("open://"+fileMsg.toString());
					System.out.println(sc);
					return reg;

				} else {
					reg = "input://false";
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			reg = "input://spacefalse";
		}
		
		return reg;
	}
}
