package model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JOptionPane;

import javabean.FileMsg;
import javabean.Filetype;
import javabean.TB_File;
import javabean.TB_User;
import view.LoginFrame;
import view.MainFrame;
import view.PwdFrame;
import view.RegFrame;
import view.InformationFrame;

public class BusinessFactory {
	private static BusinessFactory factory;
	private LoginFrame login;
	private RegFrame reg;
	private Captcha myCaptcha = new Captcha();
	private MainFrame main;
	private InformationFrame informationFrame;
	private PwdFrame pwd;
	private MainFrame frame;
	private Thread t1;
	private ClientRunnable cr;

	private BusinessFactory(MainFrame frame) {
		this.frame = frame;
	}

	public synchronized static BusinessFactory getInstance(MainFrame frame) {
		if (factory == null) {
			factory = new BusinessFactory(frame);
		}
		return factory;
	}

	public void Action(String msg) {
		String[] msgArr = msg.split("://");
		switch (msgArr[0]) {
		case "reg":
			reg(msgArr[1]);
			break;
		case "login":
			login(msgArr[1]);
			break;
		case "updateusername":
			updateusername(msgArr[1]);
			break;
		case "updateuserpwd":
			updateuserpwd(msgArr[1]);
			break;
		case "input":
			input(msgArr[1]);
			break;
		case "download":
			download(msgArr[1]);
			break;
		case "refresh":
			refresh(msgArr[1]);
			break;
//			"sort://image@@"+filetype.toString()
		case "sort":
			SortBusiness sort = new SortBusiness();
			sort.action(msgArr[1], main);
			break;
		// res = "Fileact://delete@@"+tb_File.toString();
		case "Fileact":
			FileactBusiness Fileact = new FileactBusiness();
			Fileact.action(msgArr[1], main);
			break;
		case "close":

			if (msgArr[1].equals("success")) {
				// 第四次握手
				cr.clientClose();
				
			}
			break;
		// ar.getSr().sengMsg("closesever://serverclosesuccess");
		case "closesever":

			if (msgArr[1].equals("serverclosesuccess")) {
				cr.Closeall();
				// 关闭窗口
			}
            
			break;
		}
	}

	/**
	 * 检测本地文件夹是否存在类似文件，然后进行下载
	 * 
	 * @param string
	 */
	private void download(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");

		if (msgArr[0].equals("success")) {
			FileMsg fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msgArr[1], FileMsg.class);
			Socket s;
			try {
				s = new Socket("127.0.0.1", 10003);
				DownloadRunnable down = new DownloadRunnable(s, msgArr[1], main);
				Thread t = new Thread(down);
				t.start();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 上传完成后 刷新个人信息
	 * 
	 * @param string
	 */
	private void refresh(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");
		TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msgArr[0], TB_User.class);
		// 设置主面板相关信息
		main.getUserName().setText("欢迎用户：" + tb_User.getUserName());
		main.getUsespace().setText(((int) (tb_User.getUserUsedspace())) + "M/" + tb_User.getSpace() + "M");
		double used = (double) (tb_User.getUserUsedspace()) / (double) (tb_User.getSpace());
		main.getProgressBar().setValue((int) (used * 100));
		// 设置个人面板主信息
		informationFrame.getAccount().setText(tb_User.getUserAccount());
		informationFrame.getNametextField().setText(tb_User.getUserName());
		informationFrame.getUserLevel().setText(tb_User.getUserLevel());
		informationFrame.getUsed().setText(String.valueOf(tb_User.getUserUsedspace()) + "M");
		informationFrame.getSpace().setText(String.valueOf(tb_User.getSpace()));
		informationFrame.getScore().setText(String.valueOf(tb_User.getUserScore()));
		informationFrame.getRegtime().setText(tb_User.getUserRegTime());
		main.repaint();
		// 插入文件图标进去
		MainFrame.fileVector.clear();
		main.getMyAllfilePanel().removeAll();
		main.repaint();
		Filetype filetype = (Filetype) GsonUtil.getInstance().ObjectFromJson(msgArr[1], Filetype.class);
		System.out.println(filetype.getArray());
		for (int i = 0; i < filetype.getArray().size(); i++) {
			main.Myfilebutton(filetype.getArray().get(i).getFileName(), filetype.getArray().get(i).getFileType(),
					filetype.getArray().get(i).getFilePath());
			main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
			main.validate();
		}

	}

	/**
	 * 上传通道开启，传输完成开始给业务服务器添加
	 * 
	 * @param msg
	 */
	private void input(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");

		if (msgArr[0].equals("success")) {

			Socket s;

			try {
				s = new Socket("127.0.0.1", 10002);
				System.out.println(s);
				InputRunnable up = new InputRunnable(s, frame, msgArr[1]);
				Thread t = new Thread(up);
				t.start();
				FileMsg fileMsg = (FileMsg) GsonUtil.getInstance().ObjectFromJson(msgArr[1], FileMsg.class);
				TB_File file = new TB_File();
				file.setFileName(fileMsg.getName());
				file.setUserAccount(fileMsg.getUseraccount());
				file.setFilePath(fileMsg.getFatherpath() + "/" + fileMsg.getName());
				file.setFileSize(fileMsg.getSize());
				file.setFatherPath(fileMsg.getFatherpath());
				String nameSuffix2 = fileMsg.getName().substring(fileMsg.getName().lastIndexOf(".") + 1);
				System.out.println(nameSuffix2);// txt
				file.setFileTypeName(nameSuffix2);
				file.setActTime(new Date().toLocaleString());
				cr.sendMsg("skyDiver://regfileMsg@@" + file.toString());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (msgArr[0].equals("false")) {
			JOptionPane.showMessageDialog(null, "你的网盘已经存在该文件了");
		} else if (msgArr[0].equals("spacefalse")) {
			JOptionPane.showMessageDialog(null, "你的网盘空间没办法上传这么大的文件");
		} else if (msgArr[0].equals("flypass")) {
			JOptionPane.showMessageDialog(null, "服务器存在类似类似文件，已秒传到你的网盘");
			MainFrame.fileVector.clear();
			main.getMyAllfilePanel().removeAll();
			main.repaint();
			Filetype filetype = (Filetype) GsonUtil.getInstance().ObjectFromJson(msgArr[1], Filetype.class);
			System.out.println(filetype.getArray());
			for (int i = 0; i < filetype.getArray().size(); i++) {
				main.Myfilebutton(filetype.getArray().get(i).getFileName(), filetype.getArray().get(i).getFileType(),
						filetype.getArray().get(i).getFilePath());
				main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
				main.validate();
			}

		}

	}

	/**
	 * 修改密码业务
	 * 
	 * @param msg
	 */
	public void updateuserpwd(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");
		if (msgArr[0].equals("success")) {
			JOptionPane.showMessageDialog(null, "已成功保存");
			pwd.dispose();
		} else if (msgArr[0].equals("false")) {
			pwd.dispose();
		}
	}

	/**
	 * 修改用户名字业务
	 * 
	 * @param msg
	 */
	public void updateusername(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");
		if (msgArr[0].equals("success")) {
			informationFrame.dispose();
			TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msgArr[1], TB_User.class);
			informationFrame.getNametextField().setText(tb_User.getUserName());
			main.getUserName().setText("欢迎用户：" + tb_User.getUserName());
			main.repaint();
			JOptionPane.showMessageDialog(null, "已成功保存");
		} else if (msgArr[0].equals("false")) {
			informationFrame.dispose();
		}
	}

	/**
	 * 登录业务
	 * 
	 * @param msg
	 */
	public void login(String msg) {
		System.out.println(msg);
		String[] msgArr = msg.split("@@");
		if (msgArr[0].equals("success")) {

			TB_User tb_User = (TB_User) GsonUtil.getInstance().ObjectFromJson(msgArr[1], TB_User.class);
			// 设置主面板相关信息
			main.getUserName().setText("欢迎用户：" + tb_User.getUserName());
			main.getUsespace().setText(((int) (tb_User.getUserUsedspace())) + "M/" + tb_User.getSpace() + "M");
			double used = (double) (tb_User.getUserUsedspace()) / (double) (tb_User.getSpace());
			main.getProgressBar().setValue((int) (used * 100));
			// 设置个人面板主信息
			informationFrame.getAccount().setText(tb_User.getUserAccount());
			informationFrame.getNametextField().setText(tb_User.getUserName());
			informationFrame.getUserLevel().setText(tb_User.getUserLevel());
			informationFrame.getUsed().setText(String.valueOf(tb_User.getUserUsedspace()));
			informationFrame.getSpace().setText(String.valueOf(tb_User.getSpace()));
			informationFrame.getScore().setText(String.valueOf(tb_User.getUserScore()));
			informationFrame.getRegtime().setText(tb_User.getUserRegTime());
			// 插入文件图标进去
			Filetype filetype = (Filetype) GsonUtil.getInstance().ObjectFromJson(msgArr[2], Filetype.class);
			System.out.println(filetype.getArray());
			for (int i = 0; i < filetype.getArray().size(); i++) {
				main.Myfilebutton(filetype.getArray().get(i).getFileName(), filetype.getArray().get(i).getFileType(),
						filetype.getArray().get(i).getFilePath());
				main.getMyAllfilePanel().add(MainFrame.fileVector.get(i).getMybutton());
			}
//		   main.getTabbedPane().setTitleAt(0, tb_User.getUserAccount());
			main.getLblNewLabel().setText("file/" + tb_User.getUserAccount());
			MainFrame.labelname = "file/" + tb_User.getUserAccount();
			main.repaint();

			login.setVisible(false);
			login.dispose();
			main.setVisible(true);

			// 设置登录后全局备份账号
			MainFrame.backaccount = tb_User.getUserAccount();
			// 设置修改密码窗口，静态pwd的值
			pwd.setPwd(tb_User.getUserPwd());
		} else if (msgArr[0].equals("false")) {
			JOptionPane.showMessageDialog(null, "账号或密码错误");
			login.getGetcaptcha().setText(String.valueOf(myCaptcha.StringCaptcha()));
		} else if (msgArr[0].equals("forbidden")) {
			JOptionPane.showMessageDialog(null, "你的账号已被禁用，请联系管理员");
			login.getGetcaptcha().setText(String.valueOf(myCaptcha.StringCaptcha()));
		}else if (msgArr[0].equals("refalse") ){
			JOptionPane.showMessageDialog(null, "该账号已经登录了，不能重复登录");
			login.getGetcaptcha().setText(String.valueOf(myCaptcha.StringCaptcha()));
		}

	}

	/**
	 * 注册业务
	 * 
	 * @param msg
	 */
	public void reg(String msg) {
		System.out.println(msg);
		if (msg.equals("success")) {
			reg.setVisible(false);
			login.setVisible(true);
		} else if (msg.equals("false")) {
			JOptionPane.showMessageDialog(null, "已存在相同账号了，请重新注册");
		}

	}

	public RegFrame getReg() {
		return reg;
	}

	public void setReg(RegFrame reg) {
		this.reg = reg;
	}

	public LoginFrame getLogin() {
		return login;
	}

	public MainFrame getMain() {
		return main;
	}

	public void setMain(MainFrame main) {
		this.main = main;
	}

	public void setLogin(LoginFrame login) {
		this.login = login;
	}

	public InformationFrame getInformationFrame() {
		return informationFrame;
	}

	public void setInformationFrame(InformationFrame informationFrame) {
		this.informationFrame = informationFrame;
	}

	public PwdFrame getPwd() {
		return pwd;
	}

	public void setPwd(PwdFrame pwd) {
		this.pwd = pwd;
	}

	public ClientRunnable getCr() {
		return cr;
	}

	public void setCr(ClientRunnable cr) {
		this.cr = cr;
	}

}
