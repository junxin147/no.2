package javabean;

import model.GsonUtil;

public class BackAccout {
	private long ID;
	private String account;
	private String pwd;
	private int totalPage;
	private int currentPage ;
	public BackAccout() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BackAccout(long iD, String account, String pwd, int totalPage, int currentPage) {
		super();
		ID = iD;
		this.account = account;
		this.pwd = pwd;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return GsonUtil.getInstance().ObjectToJson(this);
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	

}
