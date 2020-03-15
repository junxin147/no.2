package javabean;

import java.util.ArrayList;

import model.GsonUtil;

public class Filetype {
	private ArrayList<TB_File>array;
	private String filepath;
	public ArrayList<TB_File> getArray() {
		return array;
	}

	public Filetype(ArrayList<TB_File> array, String filepath) {
		super();
		this.array = array;
		this.filepath = filepath;
	}

	public void setArray(ArrayList<TB_File> array) {
		this.array = array;
	}

	public Filetype() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Filetype(ArrayList<TB_File> array) {
		super();
		this.array = array;
	}

	@Override
	public String toString() {
		return GsonUtil.getInstance().ObjectToJson(this);
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
