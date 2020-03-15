package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ActionRunnable;
import model.Data;
import model.ServerRunable;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FileFrame extends JFrame {

	private JPanel contentPane;
	private ActionRunnable ar;
	private Thread t;

	/**
	 * Create the frame.
	 */
	public FileFrame() {
		super("文件服务器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		JButton btnNewButton = new JButton("开启服务器");
		btnNewButton.setEnabled(true);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		btnNewButton.setBounds(144, 54, 141, 55);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("关闭服务器");
		
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(144, 147, 141, 55);
		contentPane.add(button);
		button.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				ar=new ActionRunnable();
	            t=new Thread(ar);
	            t.start();
	            System.out.println(ar.getServerSocket());
	    		btnNewButton.setEnabled(false);
	    		button.setEnabled(true);
		
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//、告诉所有线程都要关闭		
				if (Data.hashMap.size()>0) {
					System.out.println("我应该没进来");
					for (String i : Data.hashMap.keySet()) {
						ServerRunable sr = Data.hashMap.get(i);
						//第二次握手 发回客户端 
						ar.getSr().sengMsg("closefilesever://serverclosesuccess");	
					}	
				     
						ar.getSc().close();
						ar.getSr().close();		
				}


				ar.getServerSocket().close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnNewButton.setEnabled(true);
	    		button.setEnabled(false);
			}
		});
	}

}
