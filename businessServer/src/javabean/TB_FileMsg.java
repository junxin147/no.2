package javabean;

import java.util.ArrayList;

import model.GsonUtil;

public class TB_FileMsg {
	private ArrayList<TB_File>array;
	private int totalPage;
	private int currentPage ;
	private String filename;
	public TB_FileMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TB_FileMsg(ArrayList<TB_File> array, int totalPage, int currentPage, String filename) {
		super();
		this.array = array;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.filename = filename;
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



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public ArrayList<TB_File> getArray() {
		return array;
	}
	public void setArray(ArrayList<TB_File> array) {
		this.array = array;
	}
	
	@Override
	public String toString() {
		return GsonUtil.getInstance().ObjectToJson(this);
	}
	
	
	
}
