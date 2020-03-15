package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import control.MyActionListener;
import model.Captcha;
import model.ClientRunnable;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField accountTextField;
	private JPasswordField pwdPasswordField;
	private JTextField captchaTextField;
	private JLabel getCaptcha ;
	private JButton sure, refresh  ;
	private Captcha mycaptcha;
	private MyActionListener myac;
	/**
	 * Create the frame.
	 * @param myac 
	 */
	public LoginFrame(MyActionListener myac) {		
		super("网盘后台管理系统");
		this.myac=myac;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 

		JLabel account = new JLabel("账号：");
		account.setFont(new Font("宋体", Font.BOLD, 15));
		account.setBounds(58, 34, 72, 18);
		contentPane.add(account);
		
		JLabel pwd = new JLabel("密码：");
		pwd.setFont(new Font("宋体", Font.BOLD, 15));
		pwd.setBounds(58, 88, 72, 18);
		contentPane.add(pwd);
		
		accountTextField = new JTextField();
		accountTextField.setBounds(127, 31, 207, 24);
		contentPane.add(accountTextField);
		accountTextField.setColumns(10);
		accountTextField.setText("admin123");
		
		pwdPasswordField = new JPasswordField();
		pwdPasswordField.setBounds(127, 85, 207, 24);
		contentPane.add(pwdPasswordField);
		pwdPasswordField.setColumns(10);
		pwdPasswordField.setText("admin123");

		JLabel captcha = new JLabel("验证码：");
		captcha.setFont(new Font("宋体", Font.BOLD, 15));

		captcha.setBounds(58, 141, 72, 18);
		contentPane.add(captcha);
		
		captchaTextField = new JTextField();
		captchaTextField.setBounds(123, 138, 65, 24);
		contentPane.add(captchaTextField);
		captchaTextField.setColumns(10);
		
		mycaptcha =new Captcha();
		getCaptcha = new JLabel("New label");
		getCaptcha.setText(String.valueOf(mycaptcha.StringCaptcha()));
		getCaptcha.setBounds(202, 141, 72, 18);
		contentPane.add(getCaptcha);
	     
		sure = new JButton("登录");
	    sure.setFont(new Font("宋体", Font.BOLD, 15));
		sure.setBounds(123, 187, 113, 27);
		contentPane.add(sure);
		sure.setActionCommand("sure");
		sure.addActionListener(myac);
		
		 refresh = new JButton("刷新");
		refresh.setFont(new Font("宋体", Font.BOLD, 15));
		refresh.setActionCommand("refresh");
		refresh.addActionListener(myac);
		
		refresh.setBounds(288, 137, 46, 27);
		contentPane.add(refresh);
	}
	
	public JTextField getAccountTextField() {
		return accountTextField;
	}
	public void setAccountTextField(JTextField accountTextField) {
		this.accountTextField = accountTextField;
	}
	
	public JPasswordField getPwdPasswordField() {
		return pwdPasswordField;
	}
	public void setPwdPasswordField(JPasswordField pwdPasswordField) {
		this.pwdPasswordField = pwdPasswordField;
	}
	public JTextField getCaptchaTextField() {
		return captchaTextField;
	}
	public void setCaptchaTextField(JTextField captchaTextField) {
		this.captchaTextField = captchaTextField;
	}
	public JLabel getGetCaptcha() {
		return getCaptcha;
	}
	public void setGetCaptcha(JLabel getCaptcha) {
		this.getCaptcha = getCaptcha;
	}
	public JButton getSure() {
		return sure;
	}
	public void setSure(JButton sure) {
		this.sure = sure;
	}
	public JButton getRefresh() {
		return refresh;
	}
	public void setRefresh(JButton refresh) {
		this.refresh = refresh;
	}
	public Captcha getMycaptcha() {
		return mycaptcha;
	}
	public void setMycaptcha(Captcha mycaptcha) {
		this.mycaptcha = mycaptcha;
	}
	public MyActionListener getMyac() {
		return myac;
	}
	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}
	
}
