package model;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import view.LoginFrame;
import view.UserInformationFrame;

public class ClientRunnable implements Runnable {
    private Socket sc;
    private BufferedReader br;
    private BufferedWriter bw;
	private boolean flag;
	private UserInformationFrame infor;
	private LoginFrame login;
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
				
        		BusinessFactory.getInstance(this).Action(getMsg);
				
				
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
	
	
	public void clientClose()
	{
		
		try
		{
			
            flag=false; 
			br.close();
			bw.close();			
			System.out.println("客户端安全关闭");						
			
//			login.dispose();
//			infor.dispose();
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

	public UserInformationFrame getInfor() {
		return infor;
	}

	public void setInfor(UserInformationFrame infor) {
		this.infor = infor;
	}

	public boolean isFlag() {
		return flag;
	}

	public boolean setFlag(boolean flag) {
		this.flag = flag;
		return flag;
	}

	public LoginFrame getLogin() {
		return login;
	}

	public void setLogin(LoginFrame login) {
		this.login = login;
	}

}
