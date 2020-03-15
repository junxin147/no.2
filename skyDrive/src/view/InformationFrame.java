package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MyActionListener;
import javabean.TB_User;
import model.ClientRunnable;
import model.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nametextField;
	private JLabel regtime;
	private JLabel score;
	private JLabel space;
	private JLabel used;
	private JLabel userLevel;
	private JLabel account;
	private MyActionListener myac;
	private ClientRunnable cr;
	private PwdFrame pwd;
	
	/**
	 * Create the frame.
	 * @param myac 
	 * @param cr 
	 */
	public InformationFrame(MyActionListener myac, ClientRunnable cr) {
		super("个人信息");
		this.setIconImage(Data.icon);
		this.cr=cr;
		this.myac=myac;
     	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		setBounds(100, 100, 370, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		JLabel label = new JLabel("账号：");
		label.setFont(new Font("宋体", Font.BOLD, 16));
		label.setBounds(50, 42, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("昵称：");
		label_1.setFont(new Font("宋体", Font.BOLD, 16));
		label_1.setBounds(50, 82, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("用户等级：");
		label_2.setFont(new Font("宋体", Font.BOLD, 16));
		label_2.setBounds(14, 129, 108, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("已用空间:");
		label_3.setFont(new Font("宋体", Font.BOLD, 16));
		label_3.setBounds(14, 177, 108, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("积分：");
		label_4.setFont(new Font("宋体", Font.BOLD, 16));
		label_4.setBounds(50, 281, 72, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("注册时间：");
		label_5.setFont(new Font("宋体", Font.BOLD, 16));
		label_5.setBounds(14, 327, 108, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("总空间:");
		label_6.setFont(new Font("宋体", Font.BOLD, 16));
		label_6.setBounds(30, 224, 92, 18);
		contentPane.add(label_6);
		
		nametextField = new JTextField();
		nametextField.setBounds(104, 82, 150, 24);
		contentPane.add(nametextField);
		nametextField.setColumns(10);
		
		 account = new JLabel("New label");
		account.setBounds(104, 43, 72, 18);
		contentPane.add(account);
		
		 userLevel = new JLabel("New label");
		userLevel.setBounds(104, 130, 72, 18);
		contentPane.add(userLevel);
		
		 used = new JLabel("New label");
		used.setBounds(104, 178, 125, 18);
		contentPane.add(used);
		
		 space = new JLabel("New label");
		space.setBounds(104, 225, 72, 18);
		contentPane.add(space);
		
		 score = new JLabel("New label");
		score.setBounds(104, 282, 72, 18);
		contentPane.add(score);
		
		 regtime = new JLabel("New label");
		regtime.setBounds(104, 328, 200, 18);
		contentPane.add(regtime);
		
		JButton btnNewButton = new JButton("保存并确认");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			String update=nametextField.getText();		
					TB_User tb_User=new TB_User();
					tb_User.setUserAccount(MainFrame.backaccount);
					tb_User.setUserName(update);
					cr.sendMsg("skyDiver://update@@username##"+tb_User.toString());
				
			}
		});
		btnNewButton.setBounds(50, 375, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("修改密码");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pwd.setVisible(true);
				pwd.setAlwaysOnTop(true);
				pwd.setResizable(false);
			}
		});
		
		button.setBounds(191, 375, 113, 27);
		contentPane.add(button);
	}

	public PwdFrame getPwd() {
		return pwd;
	}

	public void setPwd(PwdFrame pwd) {
		this.pwd = pwd;
	}

	public JTextField getNametextField() {
		return nametextField;
	}

	public void setNametextField(JTextField nametextField) {
		this.nametextField = nametextField;
	}

	public JLabel getRegtime() {
		return regtime;
	}

	public void setRegtime(JLabel regtime) {
		this.regtime = regtime;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}

	public JLabel getSpace() {
		return space;
	}

	public void setSpace(JLabel space) {
		this.space = space;
	}

	public JLabel getUsed() {
		return used;
	}

	public void setUsed(JLabel used) {
		this.used = used;
	}

	public JLabel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(JLabel userLevel) {
		this.userLevel = userLevel;
	}

	public JLabel getAccount() {
		return account;
	}

	public void setAccount(JLabel account) {
		this.account = account;
	}
}
