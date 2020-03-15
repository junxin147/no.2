package javabean;

import model.GsonUtil;

public class TB_User {
	private int ID;
	private String  userAccount ;
	private String  userPwd ;
	private String  userName ;
	private String  userLevel ;
	private double  userUsedspace  ;
	private int space;
	private int  userScore  ;
    private String  userRegTime ;
    private String  userStage ;
	public TB_User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public TB_User(int iD, String userAccount, String userPwd, String userName, String userLevel, double userUsedspace,
			int space, int userScore, String userRegTime, String userStage) {
		super();
		ID = iD;
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userLevel = userLevel;
		this.userUsedspace = userUsedspace;
		this.space = space;
		this.userScore = userScore;
		this.userRegTime = userRegTime;
		this.userStage = userStage;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	public double getUserUsedspace() {
		return userUsedspace;
	}

	public void setUserUsedspace(double userUsedspace) {
		this.userUsedspace = userUsedspace;
	}

	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	public String getUserRegTime() {
		return userRegTime;
	}
	public void setUserRegTime(String userRegTime) {
		this.userRegTime = userRegTime;
	}
	public String getUserStage() {
		return userStage;
	}
	public void setUserStage(String userStage) {
		this.userStage = userStage;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return GsonUtil.getInstance().ObjectToJson(this);
	}
    
    
    
    
}
