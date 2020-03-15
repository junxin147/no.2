package model;

import javax.swing.JOptionPane;

import javabean.FileremaneMsg;
import javabean.Filetype;
import javabean.TB_File;
import javabean.TB_User;
import view.MainFrame;

public class FileactBusiness {

	public void action(String msg, MainFrame main) {
		String[] msgArr = msg.split("@@");
         switch (msgArr[0]) {
		case "delete":
			delete(msgArr[1], main);
			break;
			//res = "Fileact://rename@@"+fmsg.toString();
		case "rename":
			rename(msgArr[1], main);
			break;
			//		res="Fileact://newfolder@@"+tb_File.toString();
		case "newfolder":
			newfolder(msgArr[1], main);
			break;
			//res = "Fileact://openfolder@@"+filetype.toString()
		case "openfolder":
			openfolder(msgArr[1], main);
			break;
		//	"Fileact://move@@"+filetype.toString();
		case "move":
			move(msgArr[1], main);
			break;
				}
		
	}
	/**
	 * 移动文件业务处理
	 * @param msg
	 * @param main
	 */
	private void move(String msg, MainFrame main) {
		String[] msgArr = msg.split("##");
		if (msgArr[0].equals("false")) {
			JOptionPane.showMessageDialog(null, "你的目标文件夹已经有类似文件了");
		}else {
			TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msgArr[0], TB_File.class);	
			
			JOptionPane.showMessageDialog(null, tb_File.getFileName()+"文件已经移动了");

			for (int i = 0; i < MainFrame.fileVector.size(); i++) {
				System.out.println(MainFrame.fileVector.get(i).getFileName()+"集合的名字");
				if (tb_File.getFileName().equals(MainFrame.fileVector.get(i).getFileName())) {
					System.out.println(" 客户端做好删除了");
					main.getMyAllfilePanel().remove(MainFrame.fileVector.get(i).getMybutton());	
					MainFrame.fileVector.remove(i);
					main.validate();
				}			
			}
			main.repaint();
		}
	
	}
	/**
	 * 打开文件夹
	 * @param string
	 * @param main
	 */
	private void openfolder(String msg, MainFrame main) {
		MainFrame.fileVector.clear();
		main.getMyAllfilePanel().removeAll();
		main.repaint();
		// 插入文件图标进去
		Filetype filetype = (Filetype) GsonUtil.getInstance().ObjectFromJson(msg, Filetype.class);
		System.out.println(filetype.getArray());
		for (int i = 0; i < filetype.getArray().size(); i++) {
			main.Myfilebutton(filetype.getArray().get(i).getFileName(), filetype.getArray().get(i).getFileType(),
					filetype.getArray().get(i).getFilePath());
			main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
			main.validate();
		}
		main.getLblNewLabel().setText(filetype.getFilepath());
		main.repaint();

		
	}
		
	
	/**
	 * 创建文件夹
	 * @param string
	 * @param main
	 */
private void newfolder(String msg, MainFrame main) {
	if (msg.equals("false")) {
		JOptionPane.showMessageDialog(null, "文件夹创建失败，有同名的文件或文件夹");

	}else {
		JOptionPane.showMessageDialog(null, "文件夹创建成功");
		MainFrame.fileVector.clear();
		main.getMyAllfilePanel().removeAll();
		main.repaint();
		Filetype filetype = (Filetype) GsonUtil.getInstance().ObjectFromJson(msg, Filetype.class);
		System.out.println(filetype.getArray());
		for (int i = 0; i < filetype.getArray().size(); i++) {
			main.Myfilebutton(filetype.getArray().get(i).getFileName(), filetype.getArray().get(i).getFileType(),
					filetype.getArray().get(i).getFilePath());
			main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
			main.validate();
		}
				
	}
	
	
	
	}
/**
 * 重命名客户端的文件名字
 * @param string
 * @param main
 */
private void rename(String Msg, MainFrame main) {
	FileremaneMsg fmsg = (FileremaneMsg) GsonUtil.getInstance().ObjectFromJson(Msg, FileremaneMsg.class);
	for (int i = 0; i < MainFrame.fileVector.size(); i++) {
		System.out.println(MainFrame.fileVector.get(i).getFileName()+"集合的名字");
		
		if (fmsg.getFilename().equals(MainFrame.fileVector.get(i).getFileName())) {
			System.out.println(" 客户端做好重命名了");
			main.getMyAllfilePanel().remove(MainFrame.fileVector.get(i).getMybutton());	
			MainFrame.fileVector.remove(i);
			main.validate();
		}			
	}	

		main.Myfilebutton(fmsg.getReanme(), fmsg.getFiletype(),fmsg.getFathetpath()+"/"
			+	fmsg.getReanme());
		for (int i = 0; i < MainFrame.fileVector.size(); i++) {
			System.out.println("有进来哦");
		main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
		main.validate();
	}
	
	}

/**
 * s删除文件图标
 * @param msg
 * @param main
 */
	private void delete(String msg, MainFrame main) {
		String[] msgArr = msg.split("##");
		JOptionPane.showMessageDialog(null, "已经删除了");
		TB_File tb_File = (TB_File) GsonUtil.getInstance().ObjectFromJson(msgArr[0], TB_File.class);		
		for (int i = 0; i < MainFrame.fileVector.size(); i++) {
			System.out.println(MainFrame.fileVector.get(i).getFileName()+"集合的名字");
			if (tb_File.getFileName().equals(MainFrame.fileVector.get(i).getFileName())) {
				System.out.println(" 客户端做好删除了");
				main.getMyAllfilePanel().remove(MainFrame.fileVector.get(i).getMybutton());	
				MainFrame.fileVector.remove(i);
				main.validate();
			}			
		}
		main.repaint();
			TB_User tb_User=(TB_User)GsonUtil.getInstance().ObjectFromJson(msgArr[1], TB_User.class);	
			   main.getUserName().setText("欢迎用户："+tb_User.getUserName());
			   main.getUsespace().setText(((int)(tb_User.getUserUsedspace()) ) +"M/"+tb_User.getSpace()+"M");
	            double used=(double)(tb_User.getUserUsedspace())/(double)(tb_User.getSpace());
	            main.getProgressBar().setValue((int)(used*100));
	           main.repaint();
	
	}	
	}


