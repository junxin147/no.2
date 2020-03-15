package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javabean.Message;
import model.ClientRunnable;
import view.MainFrame;


public class CilentWindowListener extends WindowAdapter
{
	private MainFrame frame;
	private ClientRunnable clientRunnable;
	
	
	
	
	public CilentWindowListener(MainFrame frame,ClientRunnable clientRunnable)
	{
		super();
		this.setFrame(frame);
	this.clientRunnable = clientRunnable;
		
	}




	@Override
	public void windowClosing(WindowEvent e)
	{
		super.windowClosing(e);
		//1、禁止窗口关闭  需要jframe
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		//2、发消息给服务器   需要ClientRunnable
		Message m  = new Message(MainFrame.backaccount);
		clientRunnable.sendMsg("skyDiver://close@@"+m.toString());
			
	}




	public MainFrame getFrame() {
		return frame;
	}




	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}




	public ClientRunnable getClientRunnable() {
		return clientRunnable;
	}




	public void setClientRunnable(ClientRunnable clientRunnable) {
		this.clientRunnable = clientRunnable;
	}




}
