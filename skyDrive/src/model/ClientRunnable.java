package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import view.MainFrame;

public class ClientRunnable implements Runnable {
	 private Socket sc;
	    private BufferedReader br;
	    private BufferedWriter bw;
		private MainFrame frame;
		private boolean flag;
		public ClientRunnable(Socket sc) {
			super();
			this.setSc(sc);
			try {
				br=new BufferedReader(new InputStreamReader(sc.getInputStream(),"UTF-8"));
	            bw=new BufferedWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (flag=true)//长期等待服务器反馈
			{
				try
				{
					String getMsg = br.readLine();//读取服务器反馈信息
					System.out.println("服务器返回："+getMsg);
					
	        		BusinessFactory.getInstance(frame).Action(getMsg);
					
					
				} catch (IOException e)
				{
					Closeall();
				}
			}
		}
		
		public void sendMsg(String Msg) {
			try {
				bw.write(Msg);
				bw.newLine();
				bw.flush();
	             System.out.println("客户端发送"+Msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		/**
		 *服务器关闭情况，客户端也要要进行关闭
		 */
		public void Closeall()
		{
			
			try
			{
	            flag=false; 
				br.close();
				bw.close();		
				JOptionPane.showMessageDialog(null, "服务器关闭了");
				System.out.println("由于服务器关闭，所以客户端安全关闭");						 			
				System.exit(0);
				
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

		public void clientClose() {
			try
			{
	            flag=false; 
				br.close();
				bw.close();		
				System.out.println("客户端安全关闭");						 			
				System.exit(0);
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}

}
