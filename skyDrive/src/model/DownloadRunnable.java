package model;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

import javabean.FileMsg;
import view.MainFrame;

public class DownloadRunnable implements Runnable {

	private String msg;
	private MainFrame main;
	private FileMsg fileMsg;
	private File file;
	private Double filesize;
	private byte[] arr;
	private Socket s;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private double count;


	public DownloadRunnable(Socket s, String msg, MainFrame main) {
		super();
		this.msg  = msg;
		this.main =main;
		fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msg, FileMsg.class);
		file = new File(fileMsg.getPath()+"\\"+fileMsg.getName());
		main.setFileName(fileMsg.getName());
		main.MyDownProgressBar();
		for (int i = 0; i < MainFrame.vector1.size(); i++) {
			main.getPanel1().add(MainFrame.vector1.get(i).getLable());
			main.getPanel1().add(MainFrame.vector1.get(i).getJpro());							
		}
		filesize=Double.valueOf(fileMsg.getSize());
		arr = new byte[512];
		this.s = s;
		try
		{
			bis = new BufferedInputStream(s.getInputStream());
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
	public void run() {
		System.out.println("客户端 开始接受 文件内容");

		System.out.println(count < Long.valueOf(fileMsg.getSize()));
		try {
			while (count < Long.valueOf(fileMsg.getSize())) {

				int i;

				i = bis.read(arr);

//				System.out.println("i = "+i);

				bos.write(arr, 0, i);
				bos.flush();

				count += i;
//				System.out.println("count = "+count);
				int x;
				x = (int) ((count / filesize) * 100);
				for (int j = 0; j < MainFrame.vector1.size(); j++) {
					if (MainFrame.vector1.get(j).getFileName().equals(file.getName())) {
						MainFrame.vector1.get(j).getJpro().setValue((int) x);
					}
				}			
				main.repaint();
			}
//			main.deleteProgressBar();
//			main.repaint();
			JOptionPane.showMessageDialog(null, file.getName() + "下载完成");

			bis.close();
			bos.close();
			System.out.println("客户端已下载完成关闭 流" + s.isClosed());

			System.out.println("file download over ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
