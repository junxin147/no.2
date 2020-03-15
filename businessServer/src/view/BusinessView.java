package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MyActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BusinessView extends JFrame {

	private JPanel contentPane;
	private JButton open;
	private	JButton close;
	private JTabbedPane tabbedPane;
	private	JScrollPane scrollPane ;
	private	JTextArea textArea;
	private JTabbedPane tabbedPane1;
	private JTextArea textArea1;
	private MyActionListener myac;

	/**
	 * Create the frame.
	 * @param myac 
	 */
	public BusinessView(MyActionListener myac) {
		super("业务服务器");
		this.myac=myac;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 

		 open = new JButton("开启服务器");
		 open.setEnabled(true);
		open.setBounds(279, 55, 113, 27);
		contentPane.add(open);
		open.setActionCommand("star");
		open.addActionListener(myac);
		
		
		 close = new JButton("关闭服务器");
		 close.setEnabled(false);
		 close.setActionCommand("close");
		 close.addActionListener(myac);
		close.setBounds(158, 533, 113, 27);
		contentPane.add(close);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(25, 91, 393, 413);
		contentPane.add(tabbedPane);
		
		 
		
		tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane1.setBounds(25, 91, 393, 413);
		contentPane.add(tabbedPane1);
		
		 scrollPane = new JScrollPane();
		tabbedPane.addTab("在线用户数", null, scrollPane, null);
		
		 textArea1 = new JTextArea(10,10);
		scrollPane.setViewportView(textArea1);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		
//		textArea1.append("我是追加的内容");
//		textArea1.append("\r\n");
//		textArea1.append("我是追加的内容");
//		textArea1.append("\r\n");
	}

	
	//***********************get和set方法区*************************
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JButton getOpen() {
		return open;
	}


	public void setOpen(JButton open) {
		this.open = open;
	}


	public JButton getClose() {
		return close;
	}


	public void setClose(JButton close) {
		this.close = close;
	}


	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JTabbedPane getTabbedPane1() {
		return tabbedPane1;
	}

	public void setTabbedPane1(JTabbedPane tabbedPane1) {
		this.tabbedPane1 = tabbedPane1;
	}

	public JTextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(JTextArea textArea1) {
		this.textArea1 = textArea1;
	}

	public MyActionListener getMyac() {
		return myac;
	}

	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}
	
	
}
