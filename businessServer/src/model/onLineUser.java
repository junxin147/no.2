package model;

import java.net.Socket;
import java.util.HashMap;

public class onLineUser
{
	public HashMap<String, Thread> hashMap;
	private static onLineUser lineUser;

	private  onLineUser()
	{
		hashMap = new HashMap<>();
	}

	public static synchronized onLineUser getInstance()
	{
		if (lineUser == null)
		{
			lineUser = new onLineUser();
		}
		return lineUser;
	}
	public synchronized void addUser(String string, Thread thread)
	{
		hashMap.put(string, thread);
	}
	public synchronized Thread getUserSocket(String account)
	{
			Thread t = hashMap.get(account);
			return t;
		
	}
}
