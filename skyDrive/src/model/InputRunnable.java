package model;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

import javabean.FileMsg;
import view.MainFrame;

public class InputRunnable implements Runnable {
	private Socket socket;
	private BufferedInputStream bis;//读本地文档
	private BufferedOutputStream bos;//写出到网络
	private byte[]arr;//读取
	
	private File file;
	private double count;
	private double filesize;
	private MainFrame frame;
	private String msg;
	private Thread t2;

	public InputRunnable(Socket socket,MainFrame frame,String msg)
	{
		
		super();
		this.frame  = frame;
		this.msg=msg;
		arr = new byte[512];
		this.socket = socket;
		FileMsg fileMsg=(FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);
		file  = new File(fileMsg.getPath());
		frame.setFileName(fileMsg.getName());
		frame.MyProgressBar();
		for (int i = 0; i < MainFrame.vector.size(); i++) {
			frame.getPanel().add(MainFrame.vector.get(i).getLable());
			frame.getPanel().add(MainFrame.vector.get(i).getJpro());							
		}
		frame. repaint();		
		filesize = Double.valueOf(fileMsg.getSize());
		System.out.println("文件是否存在"+file.exists());
		System.out.println("文件长度："+file.length());
		try
		{
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(socket.getOutputStream());
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{	
		int i = 0;
		try
		{
			
			while ((i = bis.read(arr))!=-1)
			{
				
				bos.write(arr, 0, i);
				bos.flush();				
				count+=i;
	            double x = (double)(count/filesize)*100;
                for (int j = 0; j < MainFrame.vector.size(); j++) {			
					if (MainFrame.vector.get(j).getFileName().equals(file.getName())) {
						MainFrame.vector.get(j).getJpro().setValue((int)x);
					}
				}	
				}								
				frame.repaint();	
				frame.deleteProgressBar();
				frame.repaint();
				JOptionPane.showMessageDialog(null, file.getName()+"上传完成");

         
			bis.close();
			bos.close();
			System.out.println("客户端已传输完成关闭 流"+socket.isClosed());
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
}
