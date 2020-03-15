package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.MyActionListener;
import model.Captcha;
import model.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JTextField captchaText;
	private JPasswordField pwd;
	private	JLabel getcaptcha;
	private Captcha mycaptcha=new Captcha();
	private JButton starreg;
	private MyActionListener myac;
	


	/**
	 * Create the frame.
	 * @param myac 
	 */
	public LoginFrame(MyActionListener myac) {
		super("登录");
		this.setIconImage(Data.icon);

		this.myac=myac;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		JLabel label = new JLabel("账号：");
		label.setFont(new Font("楷体", Font.BOLD, 15));
		label.setBounds(120, 97, 48, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("楷体", Font.BOLD, 15));
		label_1.setBounds(120, 141, 57, 18);
		contentPane.add(label_1);
		
		account = new JTextField();
		account.setColumns(10);
		account.setBounds(192, 94, 151, 24);
		contentPane.add(account);
		
		JLabel label_2 = new JLabel("验证码：");
		label_2.setFont(new Font("楷体", Font.BOLD, 15));
		label_2.setBounds(120, 176, 64, 18);
		contentPane.add(label_2);
		
		captchaText = new JTextField();
		captchaText.setBounds(192, 173, 86, 24);
		contentPane.add(captchaText);
		captchaText.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(191, 138, 152, 24);
		contentPane.add(pwd);
		
		getcaptcha = new JLabel("New label");
		getcaptcha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getcaptcha.setText(String.valueOf(mycaptcha.StringCaptcha()));
			}
		});
		getcaptcha.setForeground(Color.blue);
		getcaptcha.setFont(new Font("楷体", Font.BOLD, 17));
		getcaptcha.setText(String.valueOf(mycaptcha.StringCaptcha()));

		getcaptcha.setOpaque(true);
		getcaptcha.setBackground(Color.white);
		getcaptcha.setBounds(305, 170, 48, 29);
		contentPane.add(getcaptcha);
		
		JButton login = new JButton("登录");
		login.setActionCommand("login");
		login.addActionListener(myac);
		
		login.setBounds(240, 210, 103, 27);
		contentPane.add(login);
		
		starreg = new JButton("注册");
		starreg.setActionCommand("starreg");
		starreg.addActionListener(myac);
		starreg.setBounds(123, 210, 103, 27);
		contentPane.add(starreg);
	}
	public JTextField getAccount() {
		return account;
	}

	public void setAccount(JTextField account) {
		this.account = account;
	}

	public JTextField getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(JTextField captchaText) {
		this.captchaText = captchaText;
	}

	public JPasswordField getPwd() {
		return pwd;
	}

	public void setPwd(JPasswordField pwd) {
		this.pwd = pwd;
	}
	public JLabel getGetcaptcha() {
		return getcaptcha;
	}
	public void setGetcaptcha(JLabel getcaptcha) {
		this.getcaptcha = getcaptcha;
	}
}
