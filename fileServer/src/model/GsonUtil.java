package model;

import com.google.gson.Gson;

public class GsonUtil
{
	private Gson gson;
	private  static GsonUtil util;
	
	private GsonUtil() 
	{
		gson = new Gson();
	}
	
	public synchronized static GsonUtil getInstance() 
	{
		if (util == null)
		{
			util = new GsonUtil();
		}
		return util;
	}
	
	
	
	public String ObjectToJson (Object ob) 
	{
		return gson.toJson(ob);
	}
	
	public Object ObjectFromJson (String json,Class cla) 
	{
		return gson.fromJson(json, cla);
	}
}
