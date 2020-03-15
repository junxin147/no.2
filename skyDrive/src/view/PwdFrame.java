package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javabean.PwdMsg;
import javabean.TB_User;
import model.ClientRunnable;
import model.Data;
import model.GetMd5;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PwdFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private ClientRunnable cr;
	private String newPwd;
	private String reNewPwd;
	private String inputPwd;
	private static  String pwd;
	
	/**
	 * Create the frame.
	 * @param cr 
	 */
	public PwdFrame(ClientRunnable cr) {
		super("密码修改");
		this.setIconImage(Data.icon);

		this.cr=cr;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原密码：");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel.setBounds(50, 53, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("新密码：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_1.setBounds(50, 115, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("确认密码：");
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setBounds(33, 168, 89, 18);
		contentPane.add(label);
		
		JButton button = new JButton("确认保存");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputPwd=passwordField.getText().trim();
				newPwd=passwordField_1.getText().trim();
				reNewPwd=passwordField_2.getText().trim();
				String md5InputPwd = GetMd5.MD5(inputPwd);
				if (md5InputPwd.equals(pwd)) {
					 if (newPwd.equals(reNewPwd)) {
							if (newPwd.length()>6) {
								String md5reNewPwd = GetMd5.MD5(reNewPwd);
								PwdMsg pwdMsg=new PwdMsg();
								pwdMsg.setUserAccount(MainFrame.backaccount);
								pwdMsg.setUserPwd(pwd);
								pwdMsg.setNewpwd(md5reNewPwd);
								pwd=md5reNewPwd;//发送的同时把静态pwd重新赋值下
								cr.sendMsg("skyDiver://update@@userpwd##"+pwdMsg.toString());	
							}else {
								JOptionPane.showMessageDialog(null, "新密码的长度要大于6位");
							}
						}else {
							JOptionPane.showMessageDialog(null, "修改的密码不一致，请重新确认");
						}
				}else {
					JOptionPane.showMessageDialog(null, "原密码输入有误");
				}
		
				
				
				
				
			}
		});
		button.setBounds(72, 228, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(209, 228, 113, 27);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 50, 186, 24);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(136, 112, 186, 24);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(136, 165, 186, 24);
		contentPane.add(passwordField_2);
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		PwdFrame.pwd = pwd;
	}
}
