package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Fileinput {
	private fileRunnable fr;
	public void UploadActive(String msg) {
		try {
			ServerSocket	serverSocket = new ServerSocket(10002);					
				
		System.out.println("文件传输服务器开启");
		Socket sc= serverSocket.accept();
		System.out.println("网盘端进来了");
		fr = new  fileRunnable(sc,msg);
		Thread t = new Thread(fr);
		t.start();
		System.out.println("走完了，就走我的");		
		serverSocket.close();
       System.out.println(serverSocket.isClosed());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
