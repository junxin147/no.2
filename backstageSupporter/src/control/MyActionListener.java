package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



import javabean.BackAccout;
import model.Captcha;
import model.ClientRunnable;
import model.GetMd5;
import view.LoginFrame;

public class MyActionListener implements ActionListener {
    private LoginFrame login;
	private ClientRunnable cr;
	private Captcha mycaptcha;
	public MyActionListener(ClientRunnable cr) {

	this.cr=cr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		mycaptcha=new Captcha();
           switch (e.getActionCommand()) {
		case "sure":
			String accout=login.getAccountTextField().getText().trim();
			String pwd=login.getPwdPasswordField().getText().trim();
			String captcha=login.getCaptchaTextField().getText().trim();
			if (accout.length()>0&&pwd.length()>0&&captcha.length()>0) {
				if (captcha.equals(login.getGetCaptcha().getText())) {
						     String md5Pwd=GetMd5.MD5(pwd);
						     BackAccout backaccout=new BackAccout();
						     backaccout.setAccount(accout);
						     backaccout.setPwd(md5Pwd);
							 cr.sendMsg("login://"+backaccout.toString());	
				}else {
					JOptionPane.showMessageDialog(null, "验证码有误，请重新输入");
				login.getCaptchaTextField().setText(String.valueOf(mycaptcha.StringCaptcha()));				login.getGetCaptcha().setText(String.valueOf(mycaptcha.StringCaptcha()));

				}
			}else {
				JOptionPane.showMessageDialog(null, "账号密码验证码输入不能为空");
				login.getGetCaptcha().setText(String.valueOf(mycaptcha.StringCaptcha()));
			}
			
			break;
		case "refresh":
			login.getGetCaptcha().setText(String.valueOf(mycaptcha.StringCaptcha()));
			break;
		default:
			break;
		}
	}
	public LoginFrame getLogin() {
		return login;
	}
	public void setLogin(LoginFrame login) {
		this.login = login;
	}
	

}
