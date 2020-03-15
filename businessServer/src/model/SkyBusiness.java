package model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.function.BiFunction;

import dao.DaoFactory;
import javabean.Filetype;
import javabean.Message;
import javabean.TB_File;
import javabean.TB_User;
import javabean.TB_UserMsg;
import view.BusinessView;

/**
 * 主要是客户端的业务处理
 * 
 * @author Alan
 *
 */
public class SkyBusiness {

	private boolean flag;
	private Thread thread;
	private String levelName;
	private  ServerRunable sr;
	private BusinessView mainframe;
	public SkyBusiness(BusinessView mainframe) {
		this.setMainframe(mainframe);
		// TODO Auto-generated constructor stub
	}

	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("@@");
		switch (arr[0]) {
		case "reg":
			str = reg(arr[1]);
			break;
		case "login":
			str = log(arr[1]);
			break;
		case "regfileMsg":
			str = regfileMsg(arr[1]);
			break;
		case "update":
			Update update = new Update();
			str = update.action(arr[1]);
			break;
		case "LIST":
			LISTbusiness listBusiness = new LISTbusiness(mainframe);
			str = listBusiness.action(arr[1]);
			break;
		case "sort":
			SortBusiness sortBusiness = new SortBusiness();
			str = sortBusiness.action(arr[1]);
			break;
		case "fileact":
			FileactBusiness fileactBusiness = new FileactBusiness();
			str = fileactBusiness.action(arr[1]);
			break;
		//	"skyDiver://close@@"+m.toString()
		case "close":
			str="";
		    //1、Gson解析 出user是谁
			//2、找到user对相应线程		
			Message m = (Message) GsonUtil.getInstance().ObjectFromJson(arr[1], Message.class);						
			ServerRunable sr = Data.hashMap.get(m.getUser());
			//第二次握手 发回客户端 
			sr.sengMsg("close://success");	
			try {
				thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//第三次握手  自己关闭
			sr.close();
			 Data.hashMap.remove(m.getUser());
			 if (onLineUser.getInstance().hashMap.size()>0) {
				 onLineUser.getInstance().hashMap.remove(m.getUser());
				//业务服务器日志发送
					mainframe.getTextArea1().setEditable(true);
					mainframe.getTextArea1().append("用户"+m.getUser()+"下线了");
					mainframe.getTextArea1().append("\r\n");
					mainframe.getTextArea1().append("当前在线用户有"+onLineUser.getInstance().hashMap.size()+"个");
					mainframe.getTextArea1().append("\r\n");
					mainframe.getTextArea1().setEditable(false);
			}
			break;
		}
		return str;
	}
/**
 * 文件信息记录数据库
 * @param msg
 * @return
 */
	private String regfileMsg(String msg) {
		System.out.println(msg);
		String res = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		// 进行添加业务操作数据库业务处理
		flag = DaoFactory.getFileDaoImpl().addfile(tb_File);
		ArrayList<TB_File> filetype = DaoFactory.getFileDaoImpl().querytype(tb_File.getFileTypeName());
		mainframe.getTextArea1().setEditable(true);
		mainframe.getTextArea1().append("用户"+tb_File.getUserAccount()+"上传了文件"+tb_File.getFileName());
		mainframe.getTextArea1().append("\r\n");
	
		mainframe.getTextArea1().setEditable(false);
		if (filetype.size() == 0) {
			System.out.println("新的文件名后缀，我将其添加到文件类型表中的其他");
			DaoFactory.getFileDaoImpl().addfileype(tb_File.getFileTypeName());
		}
		
		
		// 修改积分情况，并且一定要反馈给客户端
		//修改已使用的空间
		// 先查询目前账号 本身的积分
	    TB_User user = DaoFactory.getUserImpl().queryByAccount(tb_File.getUserAccount());
	    double a=(Double.valueOf(tb_File.getFileSize() ))/(1024*1024);	
	    double s =a+user.getUserUsedspace();
        DaoFactory.getUserImpl().alterUserUsedSpace(s, tb_File.getUserAccount());
		int updateScore = user.getUserScore() + 1;
		boolean flag1 = DaoFactory.getUserImpl().alterUserScore(updateScore, tb_File.getUserAccount());
		// 查询下修改后的积分情况
		TB_User user1 = DaoFactory.getUserImpl().queryByAccount(tb_File.getUserAccount());
        if (user1.getUserScore()>=4&&user1.getUserScore()<7) {
			levelName="银牌";
			DaoFactory.getUserImpl().alterUserLevel(levelName, tb_File.getUserAccount());		
		}else if (user1.getUserScore()>=7) {
			levelName="金牌";
			DaoFactory.getUserImpl().alterUserLevel(levelName, tb_File.getUserAccount());
		}       
		TB_User user2= DaoFactory.getUserImpl().queryByAccount(tb_File.getUserAccount());
        System.out.println(user2.getUserScore()+"还有等级哦"+user2.getUserLevel()+"还有使用空间"+user2.getUserUsedspace()+
        		"能用的总空间"+user2.getSpace());
        ArrayList<TB_File> typelist1 = DaoFactory.getFileDaoImpl().queryalltype(tb_File.getUserAccount(),tb_File.getFatherPath());
		Filetype filetype1 = new Filetype();
		filetype1.setArray(typelist1);
        
		res="refresh://"+user2.toString()+"@@"+filetype1.toString() ;
		return res;
	}
/**
 *  进行登录业务处理操作
 * @param msg
 * @return
 */
	public String log(String msg) {
		System.out.println(msg);
		String res = "";
		TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msg, TB_User.class);
		// 进行登录业务处理操作
		TB_User user = DaoFactory.getUserImpl().queryByAccount(tb_User.getUserAccount());
		if (user != null) {
			if (tb_User.getUserPwd().equals(user.getUserPwd())) {
				if (user.getUserStage().equals("启用")) {
					for (String alreadylog :onLineUser.getInstance().hashMap.keySet()) {
						if (tb_User.getUserAccount().equals(alreadylog)) {
							res ="login://refalse";
							return res ;
						}
				}	
					
					Data.hashMap.put(tb_User.getUserAccount(), sr);
					onLineUser.getInstance().addUser(tb_User.getUserAccount(), thread);
					//业务服务器日志发送
					mainframe.getTextArea1().setEditable(true);
					mainframe.getTextArea1().append("用户"+tb_User.getUserAccount()+"上线了");
					mainframe.getTextArea1().append("\r\n");
					mainframe.getTextArea1().append("当前在线用户有"+onLineUser.getInstance().hashMap.size()+"个");
					mainframe.getTextArea1().append("\r\n");
					mainframe.getTextArea1().setEditable(false);
					ArrayList<TB_File> typelist = DaoFactory.getFileDaoImpl().queryalltype(tb_User.getUserAccount(),"file/"+tb_User.getUserAccount());
		             	
					Filetype filetype = new Filetype();
					filetype.setArray(typelist);
					res = "login://success@@" + user.toString() + "@@" + filetype.toString();
				} else {
					res = "login://forbidden";
				}
			} else {
				res = "login://false";
			}
		} else {
			res = "login://false";
		}
		return res;

	}

	/**
	 * 注册业务操作并回传给客户端
	 * 
	 * @param msg
	 * @return
	 */
	public String reg(String msg) {

		System.out.println(msg);
		String res = "";
		TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msg, TB_User.class);
		// 进行添加用户数据库业务处理

		flag = DaoFactory.getUserImpl().addUser(tb_User);
		if (flag == true) {
			Socket sc;
			try {
				sc = new Socket("127.0.0.1", 10001);
				FileRunnable fr = new FileRunnable(sc);
				Thread t1 = new Thread(fr);
				t1.start();
				System.out.println(sc);
				fr.sendMsg("createfile://" + tb_User.toString());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			res = "reg://success";
		} else {
			res = "reg://false";
		}
		return res;
	}

	public ServerRunable getSr() {
		return sr;
	}

	public void setSr(ServerRunable sr) {
		this.sr = sr;
	}

	public BusinessView getMainframe() {
		return mainframe;
	}

	public void setMainframe(BusinessView mainframe) {
		this.mainframe = mainframe;
	}

}
