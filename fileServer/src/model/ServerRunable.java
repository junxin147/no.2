package model;

import java.io.*;
import java.net.*;


public class ServerRunable implements Runnable {
	private Socket sc;
	 
	private BufferedReader bufferedReader;//因为服务器被动需要接受客户端的请求 读
	private BufferedWriter bufferedWriter;
	private Business business;
	private Thread thread;
	private Boolean flag=true;
	public ServerRunable( Socket sc) 
	{
		this.sc = sc;
		try
		{
			System.out.println("业务服务端进入线程");
			business = new Business();
			bufferedReader = new BufferedReader(new InputStreamReader(sc.getInputStream(),"UTF-8"));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream(),"UTF-8"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void run()
	{
		while (flag)//长时间服务
		{
			try
			{
				//接受 业务服务端的 请求
				String recive = bufferedReader.readLine();
				System.out.println(recive);				
            	String msg = business.action(recive);
            	sengMsg(msg);
								
			} catch (IOException e)
			{      
				close();
			}
		}
	}
	
	/**
	 * 文件服务端发送消息给业务服务器？
	 * @param msg
	 */
	public void sengMsg(String msg) 
	{
		try
		{            	
			System.out.println("文件服务端发送："+msg);
			bufferedWriter.write(msg);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e)
		{		
			e.printStackTrace();

        }
		
	}
	public void close() {
		try {
			
		flag = false;		
		bufferedReader.close();		
		bufferedWriter.close();
		System.out.println("服务端安全关闭");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
