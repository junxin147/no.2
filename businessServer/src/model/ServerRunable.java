package model;

import java.io.*;
import java.net.*;

import control.MyActionListener;
import view.BusinessView;

public class ServerRunable implements Runnable {
	private Socket sc;
	 
	private BufferedReader bufferedReader;//因为服务器被动需要接受客户端的请求 读
	private BufferedWriter bufferedWriter;
	private Business business;
	private Thread thread;
	private Boolean flag=true;
	private BusinessView mainframe;
	public ServerRunable( Socket sc,BusinessView mainframe) 
	{   this.mainframe=mainframe;
		this.sc = sc;
		try
		{
			System.out.println("客户端进入线程");
			business = new Business(this,mainframe);
			business.setThread(thread);
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
				//接受 客户端的 请求
				String recive = bufferedReader.readLine();
				if (recive!=null) {
					String msg = business.action(recive);
	                  if (!msg.equals("")) {
	                		sengMsg(msg);	
					}            	
	            	
				}
            	
	
				
								
			} catch (IOException e)
			{      
				close();

			}
		}
	}
	
	
	public void sengMsg(String msg) 
	{
		
			try
			{            	
				System.out.println("服务端发送："+msg);
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


	

	public Thread getThread()
	{
		return thread;
	}


	public void setThread(Thread thread)
	{
		this.thread = thread;
		business.setThread(thread);
	}

	
	public BusinessView getMainframe() {
		return mainframe;
	}

	public void setMainframe(BusinessView mainframe) {
		this.mainframe = mainframe;
	}
	

}
