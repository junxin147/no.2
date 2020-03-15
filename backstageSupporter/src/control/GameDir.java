package control;

import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.BusinessFactory;
import model.ClientRunnable;
import view.LoginFrame;

public class GameDir {
	private MyActionListener myac;
	private Socket sc;

	public GameDir() {
		try {
			   //设置外观
     	 JFrame.setDefaultLookAndFeelDecorated(true);
          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");	
          //设置连接socket并创建线程
          try {
               sc=new Socket("127.0.0.1",10000);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "服务器离线，等待服务器开启");
			System.exit(0);
		}
          ClientRunnable cr=new ClientRunnable(sc);
          Thread t=new Thread(cr);
          t.start();
           System.out.println(sc);
  		  myac=new MyActionListener(cr);
          LoginFrame  myframe = new LoginFrame(myac);
          myac.setLogin(myframe);
          BusinessFactory.getInstance(cr).setLogin(myframe);
          myframe.setVisible(true);
          cr.setLogin(myframe);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	}

