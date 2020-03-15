package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FileRunnable implements Runnable {
       private Socket sc;
	    private BufferedReader br;
	    private BufferedWriter bw;
		private Business business;
		private boolean flag=true;
		public FileRunnable(Socket sc) {
			super();
			this.setSc(sc);
			business=new Business();
			try {
				br=new BufferedReader(new InputStreamReader(sc.getInputStream(),"UTF-8"));
	            bw=new BufferedWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"));
			} catch (IOException e) {
				close();
			}
		}

		@Override
		public void run() {
			while (flag=true) {
				try
				{
					String getMsg = br.readLine();//读取文件服务器反馈信息
					System.out.println("文件服务器返回："+getMsg);			
					String msg = business.action(getMsg);	            	
                       
				} catch (IOException e)
				{
					close();
				}
			}
				
				}
		
		/**
		 * 对文件服务器发送相关信息业务
		 * @param Msg
		 */
		public void sendMsg(String Msg) {
			try {
				bw.write(Msg);
				bw.newLine();
				bw.flush();
	             System.out.println("业务客户端发送"+Msg);

			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
          
		/**
		 * 关闭socket和读写，线程
		 */
		public void close() {
			
			try
			{
				
				flag = false;
				br.close();
				bw.close();
				
				System.out.println("客户端跟文件服务器的链接安全关闭");
			} catch (IOException e)
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
	

}
