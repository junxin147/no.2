package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.print.attribute.standard.Severity;

import javabean.Message;
import model.ActionRunnable;
import model.Data;
import model.GsonUtil;
import model.ServerRunable;
import model.onLineUser;
import view.BusinessView;

public class MyActionListener implements ActionListener {
     private BusinessView Businessframe;
     private  Thread t;
     private  ActionRunnable  ar;
     public MyActionListener() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "star":
			ar = new ActionRunnable(this, Businessframe);
			t = new Thread(ar);
			t.start();
			System.out.println(ar.getServerSocket());
			Businessframe.getOpen().setEnabled(false);
			Businessframe.getClose().setEnabled(true);
			Businessframe.repaint();
			break;
		case "close":
			try {
				// 、告诉所有线程都要关闭
				if (Data.hashMap.size() > 0) {
					System.out.println(Data.hashMap.size());
					System.out.println("我应该没进来");
					for (String i : Data.hashMap.keySet()) {
						ServerRunable sr = Data.hashMap.get(i);
						// 发回客户端 告诉服务器关闭了
						sr.sengMsg("closesever://serverclosesuccess");
						Data.hashMap.remove(i);
					}
					if ( onLineUser.getInstance().hashMap.size()>0) {
						for (String account : onLineUser.getInstance().hashMap.keySet()) {
							onLineUser.getInstance().hashMap.remove(account);
						}
					}
					ar.getSc().close();
					ar.getSr().close();
					// 业务服务器日志发送
					Businessframe.getTextArea1().setEditable(true);
					Businessframe.getTextArea1()
							.append("服务器关闭，所有客户端都退出\r\n当前在线用户有" + onLineUser.getInstance().hashMap.size() + "个");
					Businessframe.getTextArea1().append("\r\n");
					Businessframe.getTextArea1().setEditable(false);
				}
				ar.getServerSocket().close();
				t.interrupt();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Businessframe.getOpen().setEnabled(true);
			Businessframe.getClose().setEnabled(false);
			break;
		}

	}
	public BusinessView getBusinessframe() {
		return Businessframe;
	}
	public void setBusinessframe(BusinessView businessframe) {
		Businessframe = businessframe;
	}
	

}
