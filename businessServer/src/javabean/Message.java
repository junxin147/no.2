package javabean;

import model.GsonUtil;

public class Message
{
	private String user;
	
	public Message()
	{
		super();
	}

	public Message(String user)
	{
		super();
		this.user = user;
	}

	
	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	

	@Override
	public String toString()
	{
		return GsonUtil.getInstance().ObjectToJson(this);
	}
	
	
}
