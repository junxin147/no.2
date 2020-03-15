package model;

import java.util.ArrayList;
import java.util.Date;

import dao.DaoFactory;
import javabean.BackAccout;
import javabean.TB_File;
import javabean.TB_FileMsg;
import javabean.TB_User;
import javabean.TB_UserMsg;
import view.BusinessView;
import javabean.Filetype;
import javabean.Message;

public class Business {
	private Thread thread;
	private Boolean flag;
	private ServerRunable sr;
	private BusinessView mainframe;
	public Business() {
		// TODO Auto-generated constructor stub
	}
	public Business(ServerRunable sr,BusinessView mainframe) {
	this.mainframe=mainframe;
	this.sr=sr;
	}
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public String action(String msg) {
		String str = "";
		// xxxx://username=xxx&password=xx
		String[] arr = msg.split("://");
		switch (arr[0]) {
		case "login":
			str = login(arr[1]);
			break;
		case "next":
			str = next(arr[1]);
			break;
		case "up":
			str = up(arr[1]);
			break;
			
		case "filenext":
			str = filenext(arr[1]);
			break;
		case "fileup":
			str = fileup(arr[1]);
			break;
			
		case "query":
			str = query(arr[1]);
			break;
		case "queryfile":
			str = queryfile(arr[1]);
			break;	
		case "querynext":
			str = querynext(arr[1]);
			break;
		case "queryup":
			str = queryup(arr[1]);
			break;
		case "filequeryup":
			str = filequeryup(arr[1]);
			break;	
		case "filequerynext":
			str = filequerynext(arr[1]);
			break;	
		case "falseStar":
			str = falseStar(arr[1]);
			break;
		case "trueStar":
			str = trueStar(arr[1]);
			break;
		case "falseStarAndFlag=1":
			str = falseStarAndFlag1(arr[1]);
			break;
		case "trueStarAndFlag=1":
			str = trueStarAndFlag1(arr[1]);
			break;
			//主要区分客户端的业务操作处理
			// xxxx://xxxx@@username=xxx&password=xx
		case"skyDiver":
		  SkyBusiness skyBusiness=new SkyBusiness(mainframe);
		  skyBusiness.setSr(sr);
		  str= skyBusiness.action(arr[1]);					
		   break;	
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

			break;
			//cr.sendMsg("queryrefresh://"+tb_User.toString());
		case "queryrefresh":
			str = queryrefresh(arr[1]);
			break;
	}		
		return str;
	}
	
	
	/**
	 * 后台端的刷新方法
	 * @param string
	 * @return
	 */
	private String queryrefresh(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_User tb_ = (TB_User) GsonUtil.getInstance().ObjectFromJson(msg, TB_User.class);		
		// y用户列表集合
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
		int a = arra.size();
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = 1;
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		//文件列表
		ArrayList<TB_File> filearra = DaoFactory.getFileDaoImpl().queryALL();				
		int a1 = filearra.size();
		System.out.println("A=" + a);
		int c1= 5;
		int b1 = (a1 > c1) ? (a1 / c1) + 1 : 1;// b1设置为总页数
		int totalPage1 = b1;// 设置为总页数
		int currentPage1 = 1;
		ArrayList<TB_File> filearra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage1);
		// 在TB_FileMsg设置集合传参，并用JSON通信发送		
		TB_FileMsg tb_FileMsg = new TB_FileMsg();
		tb_FileMsg.setArray(filearra1);
		tb_FileMsg.setTotalPage(totalPage1);
		tb_FileMsg.setCurrentPage(currentPage1);	
		reg = "queryrefresh://" + tb_UserMsg.toString()+"@@"+tb_FileMsg.toString();

		return reg;
	}
	/**
	 * 后台端文件名搜索下一页
	 * @param string
	 * @return
	 */
	private String filequerynext(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_FileMsg tb_FileMsg = (TB_FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_FileMsg.class);

		// 全部获得的用户列表集合
		ArrayList<TB_File> arra = DaoFactory.getFileDaoImpl().queryALL(tb_FileMsg.getFilename());
		int a = arra.size();// 总条数
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = tb_FileMsg.getCurrentPage();
		// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
		ArrayList<TB_File> arra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage, tb_FileMsg.getFilename());
		TB_FileMsg TB_FileMsg1 = new TB_FileMsg();
		TB_FileMsg1.setTotalPage(totalPage);
		TB_FileMsg1.setCurrentPage(currentPage);
		TB_FileMsg1.setArray(arra1);
		reg = "filequerynext://" + TB_FileMsg1.toString();
		return reg;

	}
/**
 * 后台端文件名搜索上一页
 * @param string
 * @return
 */
	private String filequeryup(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_FileMsg tb_FileMsg = (TB_FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_FileMsg.class);

		// 全部获得的用户列表集合
		ArrayList<TB_File> arra = DaoFactory.getFileDaoImpl().queryALL(tb_FileMsg.getFilename());
		int a = arra.size();// 总条数
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = tb_FileMsg.getCurrentPage();
		// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
		ArrayList<TB_File> arra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage, tb_FileMsg.getFilename());
		TB_FileMsg TB_FileMsg1 = new TB_FileMsg();
		TB_FileMsg1.setTotalPage(totalPage);
		TB_FileMsg1.setCurrentPage(currentPage);
		TB_FileMsg1.setArray(arra1);
		reg = "filequeryup://" + TB_FileMsg1.toString();
		return reg;
	}
/**
 * 后台端文件名搜索
 * @param msg
 * @return
 */
	private String queryfile(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msg, TB_File.class);
		ArrayList<TB_File> arra = DaoFactory.getFileDaoImpl().queryALL(tb_File.getFileName());
		int a = arra.size();// 总条数
		int c = 5;// 每页条数
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = 1;// 设置初始页面为1
		ArrayList<TB_File> arra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage, tb_File.getFileName());
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_FileMsg tb_FileMsg = new TB_FileMsg();
		tb_FileMsg.setTotalPage(totalPage);
		tb_FileMsg.setCurrentPage(currentPage);
		tb_FileMsg.setArray(arra1);
		reg = "queryfile://" + tb_FileMsg.toString();
		return reg;
	}

	/**
	 * 文件列表上一页查询
	 * @param string
	 * @return
	 */
	private String fileup(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_FileMsg tb_File = (TB_FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_FileMsg.class);

		//文件列表
		ArrayList<TB_File> filearra = DaoFactory.getFileDaoImpl().queryALL();				
		int a1 = filearra.size();
		int c1= 5;
		int b1 = (a1 > c1) ? (a1 / c1) + 1 : 1;// b1设置为总页数
		int totalPage1 = b1;// 设置为总页数
		int currentPage1 = tb_File.getCurrentPage();
		ArrayList<TB_File> filearra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage1);
		// 在TB_FileMsg设置集合传参，并用JSON通信发送		
		TB_FileMsg tb_FileMsg = new TB_FileMsg();
		tb_FileMsg.setArray(filearra1);
		tb_FileMsg.setTotalPage(totalPage1);
		tb_FileMsg.setCurrentPage(currentPage1);
					
		reg = "fileup://" + tb_FileMsg.toString();

		return reg;
	}
/**
 * 文件列表下一页查询
 * @param string
 * @return
 */
	private String filenext(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_FileMsg tb_File = (TB_FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_FileMsg.class);

		//文件列表
		ArrayList<TB_File> filearra = DaoFactory.getFileDaoImpl().queryALL();				
		int a1 = filearra.size();
		int c1= 5;
		int b1 = (a1 > c1) ? (a1 / c1) + 1 : 1;// b1设置为总页数
		int totalPage1 = b1;// 设置为总页数
		int currentPage1 = tb_File.getCurrentPage();
		ArrayList<TB_File> filearra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage1);
		// 在TB_FileMsg设置集合传参，并用JSON通信发送		
		TB_FileMsg tb_FileMsg = new TB_FileMsg();
		tb_FileMsg.setArray(filearra1);
		tb_FileMsg.setTotalPage(totalPage1);
		tb_FileMsg.setCurrentPage(currentPage1);
					
		reg = "filenext://" + tb_FileMsg.toString();

		return reg;
	}

	/**
	   * 在有条件搜索情况下，修改数据，更新数据但不改变当前的页数，做启用业务
	 * @param string
	 * @return
	 */
	public String trueStarAndFlag1(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);
		// 进行修改数据库业务处理
		flag = DaoFactory.getBackAccoutDaoImpl().alterUserState(TB_UserMsg.getState(), TB_UserMsg.getUpdateAccount());
		if (flag==true) {
			ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL(TB_UserMsg.getUserAccount());
			int a = arra.size();
			int c = 5;
			int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
			int totalPage = b;// 设置为总页数
			int currentPage = TB_UserMsg.getCurrentPage();
			// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
			ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage, TB_UserMsg.getUserAccount());
			// 在TB_UserMsg设置集合传参，并用JSON通信发送
			TB_UserMsg tb_UserMsg = new TB_UserMsg();
			tb_UserMsg.setTotalPage(totalPage);
			tb_UserMsg.setCurrentPage(currentPage);
			tb_UserMsg.setArray(arra1);
			reg = "trueStarAndFlag=1://" + tb_UserMsg.toString();
		}else {
			reg = "trueStarAndFlag=1://false";

		}
		return reg;
	}
	/**
	   * 在有条件搜索情况下，修改数据，更新数据但不改变当前的页数，做禁用业务
	 * @param string
	 * @return
	 */	
    public String falseStarAndFlag1(String msg) {
    	System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);
		// 进行修改数据库业务处理
		flag = DaoFactory.getBackAccoutDaoImpl().alterUserState(TB_UserMsg.getState(), TB_UserMsg.getUpdateAccount());
		if (flag==true) {
			ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL(TB_UserMsg.getUserAccount());
			int a = arra.size();
			int c = 5;
			int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
			int totalPage = b;// 设置为总页数
			int currentPage = TB_UserMsg.getCurrentPage();
			// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
			ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage, TB_UserMsg.getUserAccount());
			// 在TB_UserMsg设置集合传参，并用JSON通信发送
			TB_UserMsg tb_UserMsg = new TB_UserMsg();
			tb_UserMsg.setTotalPage(totalPage);
			tb_UserMsg.setCurrentPage(currentPage);
			tb_UserMsg.setArray(arra1);
			reg = "trueStarAndFlag=1://" + tb_UserMsg.toString();
		}else {
			reg = "trueStarAndFlag=1://false";

		}
		return reg;
	}

	/**
              * 全表查询情况下启用业务处理
     * @param msg
     * @return
     */
	public String trueStar(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);

		// 进行修改数据库业务处理
		flag = DaoFactory.getBackAccoutDaoImpl().alterUserState(TB_UserMsg.getState(), TB_UserMsg.getUpdateAccount());
		if (flag == true) {
			// 传递新的数据库全表查询结果，并分页处理
			ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
			int a = arra.size();
			int c = 5;
			int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
			int totalPage = b;// 后台端设置为总页数
			int currentPage = TB_UserMsg.getCurrentPage();// 后台端设置当前页数
			ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
			// 在TB_UserMsg设置集合传参，并用JSON通信发送
			TB_UserMsg tb_UserMsg = new TB_UserMsg();
			tb_UserMsg.setTotalPage(totalPage);
			tb_UserMsg.setCurrentPage(currentPage);
			tb_UserMsg.setArray(arra1);
			reg = "trueStar://" + tb_UserMsg.toString();
			return reg;
		} else {
			reg = "trueStar://false";
			return reg;
		}
	}

	/**
	 * 全表查询情况下禁用业务处理
	 * 
	 * @param msg
	 * @return
	 */
	public String falseStar(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);

		// 进行修改数据库业务处理
		flag = DaoFactory.getBackAccoutDaoImpl().alterUserState(TB_UserMsg.getState(), TB_UserMsg.getUpdateAccount());
		if (flag == true) {
			// 传递新的数据库全表查询结果，并分页处理
			ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
			int a = arra.size();
			int c = 5;
			int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
			int totalPage = b;// 后台端设置为总页数
			int currentPage = TB_UserMsg.getCurrentPage();// 后台端设置当前页数
			ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
			// 在TB_UserMsg设置集合传参，并用JSON通信发送
			TB_UserMsg tb_UserMsg = new TB_UserMsg();
			tb_UserMsg.setTotalPage(totalPage);
			tb_UserMsg.setCurrentPage(currentPage);
			tb_UserMsg.setArray(arra1);
			reg = "falseStar://" + tb_UserMsg.toString();
			return reg;
		} else {
			reg = "falseStar://false";
			return reg;
		}

	}

	/**
	 * 后台端带有条件的搜索上一页
	 * 
	 * @param msg
	 * @return
	 */
	public String queryup(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);
		// 全部获得的用户列表集合
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL(TB_UserMsg.getUserAccount());
		int a = arra.size();
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = TB_UserMsg.getCurrentPage();
		// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage, TB_UserMsg.getUserAccount());
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		reg = "queryup://" + tb_UserMsg.toString();

		return reg;
	}

	/**
	 * 后台端带有条件搜索的下一页业务处理
	 * 
	 * @param msg
	 * @return
	 */
	public String querynext(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);

		// 全部获得的用户列表集合
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL(TB_UserMsg.getUserAccount());
		int a = arra.size();
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = TB_UserMsg.getCurrentPage();
		// 对拿到的用户列表进行分组，根据对应页数搜索对应的用户列表
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage, TB_UserMsg.getUserAccount());
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		reg = "querynext://" + tb_UserMsg.toString();

		return reg;
	}

	/**
	 * 后台操作端条件搜索查询
	 * 
	 * @param msg
	 * @return
	 */
	public String query(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msg, TB_User.class);
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL(tb_User.getUserAccount());
		int a = arra.size();// 总条数
		int c = 5;// 每页条数
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = 1;// 设置初始页面为1
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage, tb_User.getUserAccount());
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		reg = "query://" + tb_UserMsg.toString();
		return reg;
	}

	/**
	 * . 客户端全表上一页业务处理
	 * 
	 * @param msg
	 * @return
	 */
	public String up(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);

		// y用户列表集合
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
		int a = arra.size();
		System.out.println("A=" + a);
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = TB_UserMsg.getCurrentPage();
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		reg = "next://" + tb_UserMsg.toString();

		return reg;
	}

	/**
	 * 登录业务
	 * @param msg
	 * @return
	 */
	public String login(String msg) {
		System.out.println(msg);
		String reg = "";
		BackAccout backAccount = (BackAccout) GsonUtil.getInstance().ObjectFromJson(msg, BackAccout.class);
		BackAccout Account = DaoFactory.getBackAccoutDaoImpl().queryByAccount(backAccount.getAccount());
		if (Account != null) {
			if (backAccount.getPwd().equals(Account.getPwd())) {
				for (String alreadylog : Data.hashMap.keySet()) {
					if (backAccount.getAccount().equals(alreadylog)) {
						reg = "login://refalse";
						return reg;
					}
			}	
				
	         
					Data.hashMap.put(backAccount.getAccount(), sr);
					// y用户列表集合
					ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
					int a = arra.size();
					int c = 5;
					int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
					int totalPage = b;// 设置为总页数
					int currentPage = 1;
					ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
					// 在TB_UserMsg设置集合传参，并用JSON通信发送
					TB_UserMsg tb_UserMsg = new TB_UserMsg();
					tb_UserMsg.setTotalPage(totalPage);
					tb_UserMsg.setCurrentPage(currentPage);
					tb_UserMsg.setArray(arra1);
					//文件列表
					ArrayList<TB_File> filearra = DaoFactory.getFileDaoImpl().queryALL();				
					int a1 = filearra.size();
					System.out.println("A=" + a);
					int c1= 5;
					int b1 = (a1 > c1) ? (a1 / c1) + 1 : 1;// b1设置为总页数
					int totalPage1 = b1;// 设置为总页数
					int currentPage1 = 1;
					ArrayList<TB_File> filearra1 = DaoFactory.getFileDaoImpl().queryALL(currentPage1);
					// 在TB_FileMsg设置集合传参，并用JSON通信发送		
					TB_FileMsg tb_FileMsg = new TB_FileMsg();
					tb_FileMsg.setArray(filearra1);
					tb_FileMsg.setTotalPage(totalPage1);
					tb_FileMsg.setCurrentPage(currentPage1);	
					reg = "login://" + tb_UserMsg.toString()+"@@"+tb_FileMsg.toString();			
			} else {
				reg = "login://false";
			}
		} else {
			reg = "login://false";
		}

		return reg;

	}

	/**
	 * 客户端全表查询下一页业务处理
	 * 
	 * @param msg
	 * @return
	 */
	public String next(String msg) {

		System.out.println(msg);
		String reg = "";
		TB_UserMsg TB_UserMsg = (TB_UserMsg) GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);

		// y用户列表集合
		ArrayList<TB_User> arra = DaoFactory.getBackAccoutDaoImpl().queryALL();
		int a = arra.size();
		System.out.println("A=" + a);
		int c = 5;
		int b = (a > c) ? (a / c) + 1 : 1;// b设置为总页数
		int totalPage = b;// 设置为总页数
		int currentPage = TB_UserMsg.getCurrentPage();
		ArrayList<TB_User> arra1 = DaoFactory.getBackAccoutDaoImpl().queryALL(currentPage);
		// 在TB_UserMsg设置集合传参，并用JSON通信发送
		TB_UserMsg tb_UserMsg = new TB_UserMsg();
		tb_UserMsg.setTotalPage(totalPage);
		tb_UserMsg.setCurrentPage(currentPage);
		tb_UserMsg.setArray(arra1);
		reg = "next://" + tb_UserMsg.toString();

		return reg;

	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public ServerRunable getSr() {
		return sr;
	}
	public void setSr(ServerRunable sr) {
		this.sr = sr;
	}

}
