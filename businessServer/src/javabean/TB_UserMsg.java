package javabean;

import java.util.ArrayList;

import model.GsonUtil;


public class TB_UserMsg {
	private ArrayList<TB_User>array;
	private int totalPage;
	private int currentPage ;
	private String userAccount;
	private String updateAccount;
	private String state ;
	public TB_UserMsg() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TB_UserMsg(ArrayList<TB_User> array, int totalPage, int currentPage, String userAccount,
			String updateAccount, String state) {
		super();
		this.array = array;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.userAccount = userAccount;
		this.updateAccount = updateAccount;
		this.state = state;
	}







	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return GsonUtil.getInstance().ObjectToJson(this);
	}




	public ArrayList<TB_User> getArray() {
		return array;
	}

	public void setArray(ArrayList<TB_User> array) {
		this.array = array;
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



	public String getUserAccount() {
		return userAccount;
	}



	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}







	public String getUpdateAccount() {
		return updateAccount;
	}







	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}







	public String getState() {
		return state;
	}







	public void setState(String state) {
		this.state = state;
	}

}
