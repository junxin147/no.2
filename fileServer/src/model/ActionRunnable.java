package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class ActionRunnable implements Runnable {
	private ServerRunable sr;
	private ServerSocket serverSocket;
	private boolean flag=true;
	private Socket sc;
	private Thread t;
	public ActionRunnable() {
		 try {
			serverSocket = new ServerSocket(10001);
						
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
				sr = new  ServerRunable(sc);
				 t = new Thread(sr);
				t.start();
			}
		
		}catch (SocketException e) {
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
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

}
