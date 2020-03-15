package main;

import javax.swing.JFrame;
import javax.swing.UIManager;

import view.FileFrame;

public class fileClient {
  public static void main(String[] args) {
	  try {
		  JFrame.setDefaultLookAndFeelDecorated(true);
	      UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	} catch (Exception e) {
		// TODO: handle exception
	}
	 
	FileFrame file=new FileFrame();
	file.setVisible(true);
}
}
