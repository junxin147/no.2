package javabean;

import model.GsonUtil;

public class TB_File {
	private long ID;
	private String  userAccount ;
	private String  fileName ;
	private String  filePath ;
	private String fileSize ;
	private String fileTypeName ;
	private String  actTime ;
	private String  fatherPath ;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getActTime() {
		return actTime;
	}
	public void setActTime(String actTime) {
		this.actTime = actTime;
	}
	public String getFatherPath() {
		return fatherPath;
	}
	public void setFatherPath(String fatherPath) {
		this.fatherPath = fatherPath;
	}
	
	public TB_File(long iD, String userAccount, String fileName, String filePath, String fileSize, String fileTypeName,
			String actTime, String fatherPath) {
		super();
		ID = iD;
		this.userAccount = userAccount;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fileTypeName = fileTypeName;
		this.actTime = actTime;
		this.fatherPath = fatherPath;
	}
	public TB_File() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return GsonUtil.getInstance().ObjectToJson(this);
	}
	public String getFileTypeName() {
		return fileTypeName;
	}
	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	
	
	
}
