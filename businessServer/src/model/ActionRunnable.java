package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import control.MyActionListener;
import view.BusinessView;

public class ActionRunnable implements Runnable {
	private ServerRunable sr;
	private ServerSocket serverSocket;
	private boolean flag=true;
	private MyActionListener myac;
	private Thread t;
	private Socket sc;
	private	BusinessView mainframe;
	public ActionRunnable(MyActionListener myac,BusinessView mainframe) {
		this.myac=myac; 
		this.mainframe=mainframe;
		try {
			serverSocket = new ServerSocket(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {		
		try
		{			 			 
			while (flag)			
			{   		
			     System.out.println("服务器开启");
			
			 sc= serverSocket.accept();
             sr= new ServerRunable(sc,mainframe);
			
				t = new Thread(sr);
				sr.setThread(t);	
				t.start();
			}
		
		}catch (SocketException e) {
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public Socket getSc() {
		return sc;
	}
	public void setSc(Socket sc) {
		this.sc = sc;
	}
	public Thread getT() {
		return t;
	}
	public void setT(Thread t) {
		this.t = t;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ServerRunable getSr() {
		return sr;
	}
	public void setSr(ServerRunable sr) {
		this.sr = sr;
	}
	public MyActionListener getMyac() {
		return myac;
	}
	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}
	public BusinessView getMainframe() {
		return mainframe;
	}
	public void setMainframe(BusinessView mainframe) {
		this.mainframe = mainframe;
	}

}
