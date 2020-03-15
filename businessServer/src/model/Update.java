package model;

import dao.DaoFactory;
import javabean.PwdMsg;
import javabean.TB_User;
import javabean.TB_UserMsg;
/**
 * 修改名字和密码业务
 * @author Alan
 *
 */
public class Update {

	private boolean flag;
/**
 * 业务处理
 * @param msg
 * @return
 */
	public String action(String msg) {
		String str = "";
		String[] arr = msg.split("##");
		switch (arr[0]) {
		case "username":
			str=updateusername(arr[1]);
			break;
			
		case "userpwd":
			str=updateuserpwd(arr[1]);
			break;
		default:
			break;
		}
	
		return str;
	}
/**
 * 修改密码的业务
 * @param msg
 * @return
 */
	private String updateuserpwd(String msg) {
		System.out.println(msg);
		String reg = "";
		PwdMsg pwdMsg = (PwdMsg) GsonUtil.getInstance().ObjectFromJson(msg, PwdMsg.class);
		// 进行修改数据库业务处理
		flag = DaoFactory.getUserImpl().alterPassword(pwdMsg.getNewpwd(),pwdMsg.getUserAccount()
				,pwdMsg.getUserPwd());
		if (flag==true) {
			reg="updateuserpwd://success";
		}if (flag==false) {
			reg="updateuserpwd://false";
		}
		return reg;
	}

	/**
	 * 修改名字的业务
	 * @param msg
	 * @return
	 */
	private String updateusername(String msg) {
		System.out.println(msg);
		String reg = "";
		TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msg, TB_User.class);
		// 进行修改数据库业务处理
		flag = DaoFactory.getUserImpl().alterUserMsg(tb_User.getUserName(), tb_User.getUserAccount());
		if (flag==true) {
			TB_User user=DaoFactory.getUserImpl().queryByAccount(tb_User.getUserAccount());
			reg="updateusername://success@@"+user.toString();
		}
		if (flag==false) {
			reg="updateusername://false";
		}
		return reg;
	}

}
