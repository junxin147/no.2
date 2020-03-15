package model;

public class MylabelRecord {
private String filename;
private String nextpath;
private String uppathe;
public MylabelRecord() {
	super();
	// TODO Auto-generated constructor stub
}
public MylabelRecord(String filename, String nextpath) {
	super();
	this.filename = filename;
	this.nextpath = nextpath;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getNextpath() {
	return nextpath;
}
public void setNextpath(String nextpath) {
	this.nextpath = nextpath;
}
public String getUppathe() {
	return uppathe;
}
public void setUppathe(String uppathe) {
	this.uppathe = uppathe;
}

}
