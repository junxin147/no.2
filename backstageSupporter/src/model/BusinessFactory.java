package model;

import javax.swing.JOptionPane;

import javabean.TB_FileMsg;
import javabean.TB_UserMsg;
import view.LoginFrame;
import view.UserInformationFrame;

public class BusinessFactory {
		private static BusinessFactory factory;
		private LoginFrame login;
		private UserInformationFrame infor;
		private ClientRunnable cr;
		private  BusinessFactory(ClientRunnable cr)
		{
			this.cr=cr;
			
		}
		
		
		public synchronized static  BusinessFactory getInstance(ClientRunnable cr) 
		{
			if (factory == null)
			{
				factory = new BusinessFactory(cr);
			}
			return factory;
		}
		
		public  void Action(String msg) {
			String [] msgArr = msg.split("://");
           switch (msgArr[0]) {
		case "login":
			login(msgArr[1]);
			break;
		case "next":
			next(msgArr[1]);
			break;
		case "up":
			up(msgArr[1]);
			break;
		case "filenext":
			filenext(msgArr[1]);
			break;
		case "fileup":
			fileup(msgArr[1]);
			break;
		case "filequerynext":
			filequerynext(msgArr[1]);
			break;
		case "filequeryup":
			filequeryup(msgArr[1]);
			break;	
		case "querynext":
			querynext(msgArr[1]);
			break;
		case "queryup":
			queryup(msgArr[1]);
			break;
		case "query":
			query(msgArr[1]);
			break;
		case "falseStar":
			falseStar(msgArr[1]);
			break;
		case "trueStar":
			trueStar(msgArr[1]);
			break;	
		case "falseStarAndFlag=1":
			falseStarAndFlag1(msgArr[1]);
			break;
		case "trueStarAndFlag=1":
		   trueStarAndFlag1(msgArr[1]);
			break;	
		case "queryfile":
			queryfile(msgArr[1]);
				break;	
				//reg = "queryrefresh://" + tb_UserMsg.toString()+"@@"+tb_FileMsg.toString();
		case "queryrefresh":
			queryrefresh(msgArr[1]);
				break;	
				
				
        case "close":
			if (msgArr[1].equals("success"))
			{
				//第四次握手
				cr.clientClose();
				//关闭窗口
			}
			break;
		//	ar.getSr().sengMsg("closesever://serverclosesuccess");	
     case "closesever":
		    
			if (msgArr[1].equals("serverclosesuccess"))
			{
				cr.Closeall();
				//关闭窗口
			}
			
			break;
		}
		}
		
		
		/**
		 * 界面刷新业务
		 * @param string
		 */
		private void queryrefresh(String msg) {
			String [] msgArr = msg.split("@@");
							
			insertfiledata(msgArr[1]);
			insertTabledata(msgArr[0]);
		}


		/**
		 * 文件模糊搜索上一页
		 * @param msg
		 */
private void filequeryup(String msg) {
    insertfiledata(msg);						
			
		}

/**
 * 文件模糊搜索下一页
 * @param msg
 */
private void filequerynext(String msg) {
    insertfiledata(msg);						
			
		}


/**
 * 文件模糊搜索		
 * @param string
 */
private void queryfile(String msg) {
    insertfiledata(msg);						
			
		}


/**
 * 文件列表上一页
 * @param msg
 */
		private void fileup(String msg) {
            insertfiledata(msg);						
		}

/**
 * 文件列表下一页
 * @param msg
 */
		private void filenext(String msg) {
             insertfiledata(msg);			
		}

/**
 * 条件搜索下启用修改业务
 * @param msg
 */
		public void trueStarAndFlag1(String msg) {
			if (msg.equals("false")) {
				System.out.println("不用再修改了");
			}
			else {
				insertTabledata(msg);			
		}
		}

/**
 *条件搜索下 禁用修改业务
 * @param msg
 */
		public void falseStarAndFlag1(String msg) {
			if (msg.equals("false")) {
				System.out.println("不用再修改了");
			}
			else {
				insertTabledata(msg);			
		}
		}

/**
 * 全表下启用业务
 * @param msg
 */
		public void trueStar(String msg) {
			if (msg.equals("false")) {
				System.out.println("不用再修改了");
			}
			else {
				insertTabledata(msg);			
		}
		}

/**
 * 全表下禁用业务
 * @param msg
 */
		public void falseStar(String msg) {
			if (msg.equals("false")) {
				System.out.println("不用再修改了");
			}
			else {
				insertTabledata(msg);			
		}
		}

/**
 * 在条件下上一页查询
 * @param msg
 */
		public void queryup(String msg) {
			insertTabledata(msg);	
			
		}

/**
 * 在条件下下一页查询
 * @param msg
 */
		public void querynext(String msg) {
			insertTabledata(msg);		
			
		}

/**
 * 登录业务
 * @param msg
 */
		public void login(String msg) {
			if (msg.equals("false")) {
				JOptionPane.showMessageDialog(null, "账号或密码错误");
				login.getGetCaptcha().setText(String.valueOf(login.getMycaptcha().StringCaptcha()));

			}else if (msg.equals("refalse")) {
				JOptionPane.showMessageDialog(null, "该账号已经登录了，不能重复登录");
				login.getGetCaptcha().setText(String.valueOf(login.getMycaptcha().StringCaptcha()));
			}else {
				String [] msgArr = msg.split("@@");
				TB_UserMsg tb_UserMsg=(TB_UserMsg)GsonUtil.getInstance().ObjectFromJson(msgArr[0], TB_UserMsg.class);				
				TB_FileMsg tb_FileMsg=(TB_FileMsg)GsonUtil.getInstance().ObjectFromJson(msgArr[1], TB_FileMsg.class);				
				login.dispose();
				infor=new UserInformationFrame(tb_UserMsg.getArray(),tb_FileMsg.getArray(), cr);    
				infor.getTotalNumber().setText(String.valueOf(tb_UserMsg.getTotalPage()));
				infor.getCurrentNumbe().setText(String.valueOf(tb_UserMsg.getCurrentPage()));
				infor.getLabel_3().setText(String.valueOf(tb_FileMsg.getTotalPage()));
				infor.getLabel().setText(String.valueOf(tb_FileMsg.getCurrentPage()));
				infor.setVisible(true);
			}
			
		}
/**
 * 用户全表查询下，下一页
 * @param msg
 */
		public void next(String msg) {
			insertTabledata(msg);					
		}
		/**
		 * 全表下，上一页业务
		 * @param msg
		 */
		public void up(String msg) {
			insertTabledata(msg);			
		}
		/**
		 * 查询业务
		 * @param msg
		 */
		public void query(String msg) {
			insertTabledata(msg);	
		}
		public void insertfiledata(String msg) {
			TB_FileMsg tb_FileMsg=(TB_FileMsg)GsonUtil.getInstance().ObjectFromJson(msg, TB_FileMsg.class);				
			infor.getLabel_3().setText(String.valueOf(tb_FileMsg.getTotalPage()));
			infor.getLabel().setText(String.valueOf(tb_FileMsg.getCurrentPage()));
			infor.setArray1(tb_FileMsg.getArray());
			infor.getTable_1().setRowCount(0);
			infor.tabledata1();	
		}
		
		
			public void insertTabledata(String msg) {
			TB_UserMsg tb_UserMsg=(TB_UserMsg)GsonUtil.getInstance().ObjectFromJson(msg, TB_UserMsg.class);				
			infor.getTotalNumber().setText(String.valueOf(tb_UserMsg.getTotalPage()));
			infor.getCurrentNumbe().setText(String.valueOf(tb_UserMsg.getCurrentPage()));
			infor.setArray(tb_UserMsg.getArray());
			infor.getTablemodel().setRowCount(0);
			infor.tabledata();	
		}
		
		public LoginFrame getLogin() {
			return login;
		}

		public void setLogin(LoginFrame login) {
			this.login = login;
		}

		public UserInformationFrame getInfor() {
			return infor;
		}

		public void setInfor(UserInformationFrame infor) {
			this.infor = infor;
		}
		
		
}
