package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MyActionListener;
import model.Data;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegFrame extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JTextField nametext;
	private JPasswordField pwd;
	private JPasswordField repwd;
	private MyActionListener myac;



	/**
	 * Create the frame.
	 * @param myac 
	 */
	public RegFrame(MyActionListener myac) {
		super("注册");
		this.setIconImage(Data.icon);

		this.myac=myac;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		JLabel label = new JLabel("账号：");
		label.setFont(new Font("楷体", Font.BOLD, 15));
		label.setBounds(40, 33, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("楷体", Font.BOLD, 15));
		label_1.setBounds(39, 125, 48, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("确认密码：");
		label_2.setFont(new Font("楷体", Font.BOLD, 15));
		label_2.setBounds(14, 168, 88, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("名字：");
		label_3.setFont(new Font("楷体", Font.BOLD, 15));
		label_3.setBounds(40, 74, 72, 18);
		contentPane.add(label_3);
		
		account = new JTextField();
		account.setBounds(105, 30, 131, 24);
		contentPane.add(account);
		account.setColumns(10);
		
		nametext = new JTextField();
		nametext.setColumns(10);
		nametext.setBounds(105, 71, 131, 24);
		contentPane.add(nametext);
		
		pwd = new JPasswordField();
		pwd.setBounds(105, 122, 131, 24);
		contentPane.add(pwd);
		
		repwd = new JPasswordField();
		repwd.setBounds(105, 165, 131, 24);
		contentPane.add(repwd);
		
		JButton reg = new JButton("确认注册");
		reg.setActionCommand("reg");
		reg.addActionListener(myac);
			
		reg.setFont(new Font("宋体", Font.BOLD, 15));
		reg.setBounds(105, 216, 113, 27);
		contentPane.add(reg);
	}



	public JTextField getAccount() {
		return account;
	}



	public void setAccount(JTextField account) {
		this.account = account;
	}



	public JTextField getNametext() {
		return nametext;
	}



	public void setNametext(JTextField nametext) {
		this.nametext = nametext;
	}



	public JPasswordField getPwd() {
		return pwd;
	}



	public void setPwd(JPasswordField pwd) {
		this.pwd = pwd;
	}



	public JPasswordField getRepwd() {
		return repwd;
	}



	public void setRepwd(JPasswordField repwd) {
		this.repwd = repwd;
	}



	public MyActionListener getMyac() {
		return myac;
	}



	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}

}
