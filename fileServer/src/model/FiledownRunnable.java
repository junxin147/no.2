package model;

import java.io.*;
import java.net.Socket;

import javabean.FileMsg;

public class FiledownRunnable implements Runnable {

	private Socket sc;
	private byte[] arr;
	private File file;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public FiledownRunnable(Socket sc, String msg) {
		super();
		arr = new byte[512];
		this.sc = sc;
		FileMsg fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);

		file  = new File(fileMsg.getFatherpath()+"/"+fileMsg.getName());
//        setFileMsg(new FileMsg(file.length()+"", file.getName(), file.getAbsolutePath()));
		System.out.println("文件是否存在"+file.exists());
		System.out.println("文件长度："+file.length());
		try
		{
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(sc.getOutputStream());
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
			}
			bis.close();
			bos.close();
			System.out.println("服务端已传输完成关闭 流"+sc.isClosed());
		} catch (IOException e)
		{
			e.printStackTrace();
		} 


}
	
	
}
