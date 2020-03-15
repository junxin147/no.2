package main;


import java.net.ServerSocket;

import javax.swing.JFrame;
import javax.swing.UIManager;

import control.GameDir;
import view.BusinessView;

public class BusinessClient {

	public static void main(String[] args) {try {
		JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");	
        GameDir gd=new GameDir();
	
	} catch (Exception e) {
		
	}
		
					
	}

}
