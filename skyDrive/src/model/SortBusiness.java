package model;

import javabean.Filetype;
import main.Main;
import view.MainFrame;

public class SortBusiness {

	public void action(String msg, MainFrame main) {
		String[] msgArr = msg.split("@@");
		switch (msgArr[0]) {
		
		case "allfile":
			allfile(msgArr[1], main);
			break;
		case "text":
			text(msgArr[1], main);
			break;
		case "music":
			music(msgArr[1], main);
			break;
		case "ohter":
			ohter(msgArr[1], main);
			break;
		case "image":
			image(msgArr[1], main);
			break;
		}
	
	}

	 /**
	  * 全部类型文件查询
	  * @param msg
	  * @param main
	  */
private void allfile(String msg, MainFrame main) {
	refresh(msg, main);		
	}


/**
 * 文档类型文件查询
 * @param msg
 * @param main
 */
	private void text(String msg, MainFrame main) {
		refresh(msg, main);

	}
/**
 * 音乐类型文件查询
 * @param msg
 * @param main
 */
	private void music(String msg, MainFrame main) {
		refresh(msg, main);

	}
/**
 * 其他类型文件查询
 * @param msg
 * @param main
 */
	private void ohter(String msg, MainFrame main) {
		refresh(msg, main);

	}
/**
 * 图片类型文件查询
 * @param msg
 * @param main
 */
	private void image(String msg, MainFrame main) {
		refresh(msg, main);
	}

	/**
	 * 面板刷新图标
	 * 
	 * @param msg
	 * @param main
	 */
	public void refresh(String msg, MainFrame main) {
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
	}

}