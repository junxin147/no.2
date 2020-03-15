package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Filedown {

	public void downActive(String msg) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(10003);
			Socket sc = ss.accept();
			FiledownRunnable fr = new FiledownRunnable(sc,msg);
			Thread t = new Thread(fr);
			t.start();
			System.out.println("走完了，就走我的");		
			ss.close();
	       System.out.println(ss.isClosed());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
