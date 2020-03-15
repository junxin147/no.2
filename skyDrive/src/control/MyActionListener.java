package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

import javabean.TB_File;
import javabean.TB_User;
import model.Captcha;
import model.ClientRunnable;
import model.GetMd5;
import view.InformationFrame;
import view.LoginFrame;
import view.MainFrame;
import view.RegFrame;
import view.PwdFrame;

public class MyActionListener implements ActionListener {

	private ClientRunnable cr;
	private LoginFrame login;
	private RegFrame reg;
	private String account;
	private String pwd;
    private Captcha myCaptcha=new Captcha();
    private String md5Pwd;	
    private MainFrame main;
    private InformationFrame informationFrame;
    private PwdFrame pwdFrame;
        
    public MyActionListener(ClientRunnable cr) {
		this.cr = cr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "starreg":
			login.setVisible(true);
			reg.setVisible(true);
			break;
			
	    case "login":
			account=login.getAccount().getText().trim();
			pwd=login.getPwd().getText().trim();
			String captchaInput=login.getCaptchaText().getText().trim();
			if (account.length()>0&&pwd.length()>0&&captchaInput.length()>0) {
				if (captchaInput.equals(login.getGetcaptcha().getText())) {
					String md5Pwd=GetMd5.MD5(pwd);
					TB_User tb_User=new TB_User();
					tb_User.setUserAccount(account);
					tb_User.setUserPwd(md5Pwd);
					tb_User.setUserRegTime(new Date().toLocaleString());
					 cr.sendMsg("skyDiver://login@@"+tb_User.toString());
						
				}else {
					JOptionPane.showMessageDialog(null, " 验证码有误请重新输入");
					login.getGetcaptcha().setText(String.valueOf(myCaptcha.StringCaptcha()));
				}
			}else {
				JOptionPane.showMessageDialog(null, "不能为空，请输入");
				login.getGetcaptcha().setText(String.valueOf(myCaptcha.StringCaptcha()));
			}
	    	
			break;
          case "reg":
        	  account=reg.getAccount().getText().trim();
        	  String name=reg.getNametext().getText().trim();
        	  pwd=reg.getPwd().getText().trim();
        	  String repwd=reg.getRepwd().getText().trim();
        	  
				if (account.length()>0&&pwd.length()>0&&name.length()>0
						&&	repwd.length()>0) {
				if (pwd.equals(repwd)) {
					if (pwd.length()>6) {
						 md5Pwd=GetMd5.MD5(pwd);
						TB_User tb_User=new TB_User();
						tb_User.setUserAccount(account);
						tb_User.setUserName(name);
						tb_User.setUserPwd(md5Pwd);
						tb_User.setUserRegTime(new Date().toLocaleString());
					   cr.sendMsg("skyDiver://reg@@"+tb_User.toString());
					}else {
						JOptionPane.showMessageDialog(null, "密码长度要大于6位数");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "密码不一致哦");
				}
			}else {
				JOptionPane.showMessageDialog(null, "都不能为空值");

			}
			break;
          case"mydrive":
		     TB_File tb_File=new TB_File();
		     main.nextlabelname=null;
		     tb_File.setUserAccount(MainFrame.backaccount);
		     tb_File.setFatherPath(MainFrame.labelname);
		     main.getLblNewLabel().setText(MainFrame.labelname);
		      cr.sendMsg("skyDiver://sort@@allfile##"+tb_File.toString());
			     main.getLblNewLabel().setVisible(true);
        	  //界面切换
        	  main.getDriverPanel().setVisible(true);        	  
        	  main.getInputPanel().setVisible(false);
        	  //按钮的的状态情况
        	  main.getMydrive().setSelected(true);
        	  main.getList().setSelected(false);
        	  main.getInputList().setSelected(false);
        	  main.getDownList().setSelected(false);
  			break;
          case"inputlist":
        	  //界面切换
        	  main.getScrollPane1().setVisible(false);
        	  main. getScrollPane().setVisible(true);
              main.getDriverPanel().setVisible(false);
        	  main.getInputPanel().setVisible(true);
        	  //按钮的的状态情况
        	  main.getMydrive().setSelected(false);
        	  main.getList().setSelected(true);
        	  main.getInputList().setSelected(true);
        	  main.getDownList().setSelected(false);
        	  break;	
			
			
		}
	}

	public ClientRunnable getCr() {
		return cr;
	}

	public void setCr(ClientRunnable cr) {
		this.cr = cr;
	}

	public LoginFrame getLogin() {
		return login;
	}

	public void setLogin(LoginFrame login) {
		this.login = login;
	}

	public RegFrame getReg() {
		return reg;
	}

	public void setReg(RegFrame reg) {
		this.reg = reg;
	}

	public MainFrame getMain() {
		return main;
	}

	public void setMain(MainFrame main) {
		this.main = main;
	}

	public InformationFrame getInformationFrame() {
		return informationFrame;
	}

	public void setInformationFrame(InformationFrame informationFrame) {
		this.informationFrame = informationFrame;
	}

	public PwdFrame getPwdFrame() {
		return pwdFrame;
	}

	public void setPwdFrame(PwdFrame pwdFrame) {
		this.pwdFrame = pwdFrame;
	}

}
