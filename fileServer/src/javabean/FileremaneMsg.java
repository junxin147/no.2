package javabean;

import model.GsonUtil;

public class FileremaneMsg {
private String filename;
private String reanme;
private String account;
private String path;
private String fathetpath;
private String filetype;
public FileremaneMsg() {
	super();
	// TODO Auto-generated constructor stub
}

public FileremaneMsg(String filename, String reanme, String account, String path, String fathetpath, String filetype) {
	super();
	this.filename = filename;
	this.reanme = reanme;
	this.account = account;
	this.path = path;
	this.fathetpath = fathetpath;
	this.filetype = filetype;
}

public FileremaneMsg(String filename, String reanme, String account, String fathetpath) {
	super();
	this.filename = filename;
	this.reanme = reanme;
	this.account = account;
	this.fathetpath = fathetpath;
}

public FileremaneMsg(String filename, String reanme, String account) {
	super();
	this.filename = filename;
	this.reanme = reanme;
	this.account = account;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getReanme() {
	return reanme;
}
public void setReanme(String reanme) {
	this.reanme = reanme;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
@Override
public String toString() {
	return GsonUtil.getInstance().ObjectToJson(this);
}
public String getFathetpath() {
	return fathetpath;
}
public void setFathetpath(String fathetpath) {
	this.fathetpath = fathetpath;
}

public String getFiletype() {
	return filetype;
}

public void setFiletype(String filetype) {
	this.filetype = filetype;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}


}
