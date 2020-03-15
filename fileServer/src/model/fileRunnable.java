package model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javabean.FileMsg;

public class fileRunnable implements Runnable {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private byte[]arr;//读取
	private long count=0;//计算文件长度
	private String msg;
	private FileMsg fileMsg;
	private File file;
	
	public fileRunnable(Socket socket,String msg)
	{
		
		super();
		this.msg  = msg;
		  
		fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);
		 file=new File(fileMsg.getFatherpath()+"/"+fileMsg.getName());
		arr = new byte[512];
		this.socket = socket;
		try
		{
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(file));
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
		System.out.println("服务器 开始接受 文件内容");
		
		System.out.println(count < Long.valueOf(fileMsg.getSize()));
		while (count < Long.valueOf(fileMsg.getSize()))
		{
			try
			{
				
				int i = bis.read(arr);
				System.out.println("i = "+i);
				
				bos.write(arr, 0, i);
				bos.flush();
				
				count+=i;
				System.out.println("count = "+count);		
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			bis.close();
			bos.close();
			System.out.println("服务器已传输完成关闭 流"+socket.isClosed());
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}		
		System.out.println("file upload over ");
	}

}
