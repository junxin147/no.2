package model;

import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JProgressBar;

public class MybuttonFile {
	private String fileName;
	private String filetype;
	private JButton mybutton;
	private String filepath;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public JButton getMybutton() {
		return mybutton;
	}
	public void setMybutton(JButton mybutton) {
		this.mybutton = mybutton;
	}
	public MybuttonFile(String fileName, JButton mybutton) {
		super();
		this.fileName = fileName;
		this.mybutton = mybutton;
	}
	public MybuttonFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
