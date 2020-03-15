package model;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

public class fileProgressBar {
private String fileName;
private JProgressBar Jpro;
private JLabel lable;

public JLabel getLable() {
	return lable;
}
public void setLable(JLabel lable) {
	this.lable = lable;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public JProgressBar getJpro() {
	return Jpro;
}
public void setJpro(JProgressBar jpro) {
	Jpro = jpro;
}
public fileProgressBar() {
	super();
	// TODO Auto-generated constructor stub
}
public fileProgressBar(String fileName, JProgressBar jpro, JLabel lable) {
	super();
	this.fileName = fileName;
	Jpro = jpro;
	this.lable = lable;
}



}
