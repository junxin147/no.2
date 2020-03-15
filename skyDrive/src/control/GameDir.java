package control;

import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.BusinessFactory;
import model.ClientRunnable;
import model.Data;
import view.LoginFrame;
import view.MainFrame;
import view.PwdFrame;
import view.RegFrame;
import view.InformationFrame;

public class GameDir {
	private MyActionListener myac;
	private MainFrame main;
	private InformationFrame informationFrame;
	private Socket sc;

	public GameDir() {
		try {
			   //设置外观
     	 JFrame.setDefaultLookAndFeelDecorated(true);
          UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");	
          //设置连接socket并创建线程
//          String a="fuckyou";
//        for (int i = 0; i < Data.mystring.length; i++) {
//			if (a.contains(Data.mystring[i])) {
//				System.out.println("有违法词语");
//				
//			}
        	
//		}
 
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
          RegFrame regFrame=new RegFrame(myac);
          myac.setReg(regFrame);
          myframe.setVisible(true);
         
          main=new MainFrame(myac,cr);
          myac.setMain(main);
         
          
          informationFrame=new InformationFrame(myac,cr);
          myac.setInformationFrame(informationFrame);
          main.setInformationFrame(informationFrame);
          BusinessFactory.getInstance(main).setMain(main);
          BusinessFactory.getInstance(main).setLogin(myframe);
          BusinessFactory.getInstance(main).setReg(regFrame);
          BusinessFactory.getInstance(main).setInformationFrame(informationFrame);          
          PwdFrame pwd=new PwdFrame(cr);
          informationFrame.setPwd(pwd);
          BusinessFactory.getInstance(main).setPwd(pwd);
          
          BusinessFactory.getInstance(main).setCr(cr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
}
