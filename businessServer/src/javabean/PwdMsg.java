package javabean;

import model.GsonUtil;

public class PwdMsg {
	private String  userAccount ;
	private String  userPwd ;
	private String   newpwd;
	
	public PwdMsg(String userAccount, String userPwd, String newpwd) {
		super();
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.newpwd = newpwd;
	}
	public PwdMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return GsonUtil.getInstance().ObjectToJson(this);
	}
	
}
