package javabean;

import java.util.Date;

import model.GsonUtil;

public class FileMsg {
	private String size;
	private String name;
	private String time;
	private String path;
	private String fatherpath;
	private String useraccount;
	
	public FileMsg()
	{
		super();
	}



	public FileMsg(String size, String name, String time, String path, String fatherpath, String useraccount) {
		super();
		this.size = size;
		this.name = name;
		this.time = time;
		this.path = path;
		this.fatherpath = fatherpath;
		this.useraccount = useraccount;
	}



	public FileMsg(String size, String name, String path, String useraccount)
	{
		super();
		this.size = size;
		this.name = name;
		this.time = new Date().toLocaleString();
		this.path = path;
		this.useraccount=useraccount;
	}



	public String getUseraccount() {
		return useraccount;
	}



	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}



	public String getSize()
	{
		return size;
	}



	public void setSize(String size)
	{
		this.size = size;
	}



	public String getName()
	{
		return name;
	}



	public void setName(String name)
	{
		this.name = name;
	}



	public String getTime()
	{
		return time;
	}



	public void setTime(String time)
	{
		this.time = time;
	}



	public String getPath()
	{
		return path;
	}



	public void setPath(String path)
	{
		this.path = path;
	}
	
	@Override
	public String toString()
	{
		return GsonUtil.getInstance().ObjectToJson(this);
	}



	public String getFatherpath() {
		return fatherpath;
	}



	public void setFatherpath(String fatherpath) {
		this.fatherpath = fatherpath;
	}
}
