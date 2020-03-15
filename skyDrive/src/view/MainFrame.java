package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import control.CilentWindowListener;
import control.MyActionListener;
import javabean.FileMsg;
import javabean.FileremaneMsg;
import javabean.TB_File;
import model.ClientRunnable;
import model.Data;
import model.MybuttonFile;
import model.MylabelRecord;
import model.fileProgressBar;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private MyActionListener myac;
	private JToggleButton list;
	private JToggleButton mydrive;
	private JButton download;
	private JButton fileinput;
	private JLabel usespace;
	private JButton button_2;
	private JButton music;
	private JButton textbutton;
	private JButton imagebutton;
	private JLabel userName;
	private InformationFrame informationFrame;
	private JToggleButton infor;
	private JPanel inputPanel;
	private JToggleButton inputList;
	private JToggleButton downList;
	private JPanel driverPanel;
	private ClientRunnable cr;
	private JButton createFile;
	private JButton rename;
	private JButton deletefile;
	public static String backaccount;// 登录后的备份账号
	private JPanel panel;
	private JScrollPane scrollPane;
	private JProgressBar progressBar_1;
	private FileMsg fileMsg;
	public static Vector<fileProgressBar> vector = new Vector<fileProgressBar>();
	public static Vector<fileProgressBar> vector1 = new Vector<fileProgressBar>();

	public static Vector<MybuttonFile> fileVector = new Vector<MybuttonFile>();
	public static String thisfilename;// 当前选中的文件名字
	public static String thistype;// 当前选中的文件的文件类型
	public static String labelname;// 设置全局标签页名
	public static String nextlabelname;// 设置全局双击进入的标签页名
	private static String uplabelname;

	private static int headLable = 0;
	public static Vector<MylabelRecord> vectorrecord = new Vector<MylabelRecord>();

	private JTextField textField;
	private String fileName;
	private JScrollPane scrollPane1;
	private JPanel panel1;
	private JLabel lable;
	private JButton mybutton;
	private JPanel MyAllfilePanel;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 * 
	 * @param cr
	 * @param myac
	 */
	public MainFrame(MyActionListener myac, ClientRunnable cr) {
		super("这是一个神奇的网盘");
		this.setIconImage(Data.icon);

		getContentPane().setForeground(Color.GREEN);
		this.cr = cr;
		this.myac = myac;
		addWindowListener(new CilentWindowListener(this, cr));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 765);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(14, 631, 121, 14);
		progressBar.setFont(new Font("方正舒体", Font.PLAIN, 17));
		progressBar.setForeground(Color.yellow);// 设置前景色
		progressBar.setMinimum(0);// 设置最小值
		progressBar.setMaximum(100);// 设置最大值
		progressBar.setStringPainted(true);// 设置显示进度数字
		progressBar.setValue(((20 / 100) * 100));// 设置值
		contentPane.add(progressBar);

		usespace = new JLabel("20M/100M");
		usespace.setBounds(14, 664, 72, 18);
		contentPane.add(usespace);

		mydrive = new JToggleButton("我的网盘", true);
		mydrive.setActionCommand("mydrive");
		mydrive.addActionListener(myac);
		mydrive.setBounds(173, 64, 113, 46);
		contentPane.add(mydrive);

		list = new JToggleButton("传输列表", false);
		list.setActionCommand("inputlist");
		list.addActionListener(myac);

		list.setBounds(314, 64, 113, 46);
		contentPane.add(list);

		driverPanel = new JPanel();
		driverPanel.setBounds(14, 123, 913, 468);
		contentPane.add(driverPanel);
		driverPanel.setLayout(null);
		Data.myimage.setImage(Data.myimage.getImage().getScaledInstance(40, 40, Data.myimage.getImage().SCALE_DEFAULT));
		imagebutton = new JButton("图片", Data.myimage);
		imagebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				TB_File file = new TB_File();
				file.setUserAccount(backaccount);
				file.setFileType(imagebutton.getText());
				cr.sendMsg("skyDiver://sort@@image##" + file.toString());
			}
		});
		imagebutton.setContentAreaFilled(false);

		imagebutton.setBounds(14, 13, 121, 104);
		driverPanel.add(imagebutton);

		Data.mytext.setImage(Data.mytext.getImage().getScaledInstance(40, 40, Data.mytext.getImage().SCALE_DEFAULT));

		textbutton = new JButton("文档", Data.mytext);
		textbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);

				TB_File file = new TB_File();
				file.setUserAccount(backaccount);

				file.setFileType(textbutton.getText());
				cr.sendMsg("skyDiver://sort@@text##" + file.toString());
			}
		});
		textbutton.setContentAreaFilled(false);

		textbutton.setBounds(14, 124, 121, 104);
		driverPanel.add(textbutton);
		Data.mymusic.setImage(Data.mymusic.getImage().getScaledInstance(40, 40, Data.mymusic.getImage().SCALE_DEFAULT));

		music = new JButton("音乐", Data.mymusic);
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);

				TB_File file = new TB_File();
				file.setUserAccount(backaccount);

				file.setFileType(music.getText());
				cr.sendMsg("skyDiver://sort@@music##" + file.toString());
			}
		});
		music.setContentAreaFilled(false);

		music.setBounds(14, 237, 121, 104);
		driverPanel.add(music);

		Data.ohter.setImage(Data.ohter.getImage().getScaledInstance(40, 40, Data.ohter.getImage().SCALE_DEFAULT));
		button_2 = new JButton("其他", Data.ohter);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);

				TB_File file = new TB_File();
				file.setUserAccount(backaccount);

				file.setFileType(button_2.getText());
				cr.sendMsg("skyDiver://sort@@ohter##" + file.toString());
			}
		});
		button_2.setContentAreaFilled(false);
		button_2.setBounds(14, 354, 121, 104);
		driverPanel.add(button_2);

		fileinput = new JButton("上传");
		fileinput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int val = chooser.showOpenDialog(null);

				if (val == JFileChooser.APPROVE_OPTION) {
					if (nextlabelname == null) {
						File f = chooser.getSelectedFile();
						fileMsg = new FileMsg(f.length() + "", f.getName(), f.getAbsolutePath(), backaccount);
						System.out.println(f.getName());
						for (int i = 0; i < Data.mystring.length; i++) {
							if (f.getName().contains(Data.mystring[i])) {
								System.out.println("有违法词语");
								JOptionPane.showMessageDialog(null, "你上传的文件有非法内容，请重新上传");
								return;
							}
						}
						fileMsg.setFatherpath(labelname);
						JOptionPane.showMessageDialog(null, f.getName() + "开始上传");
						cr.sendMsg("skyDiver://LIST@@input##" + fileMsg.toString());
					} else {
						File f = chooser.getSelectedFile();
						fileMsg = new FileMsg(f.length() + "", f.getName(), f.getAbsolutePath(), backaccount);
						System.out.println(f.getName());
						for (int i = 0; i < Data.mystring.length; i++) {
							if (f.getName().contains(Data.mystring[i])) {
								System.out.println("有违法词语");
								JOptionPane.showMessageDialog(null, "你上传的文件有非法内容，请重新上传");
								return;
							}
						}
						fileMsg.setFatherpath(nextlabelname);
						JOptionPane.showMessageDialog(null, f.getName() + "开始上传");
						cr.sendMsg("skyDiver://LIST@@input##" + fileMsg.toString());
					}

				}
				repaint();
			}
		});

		fileinput.setBounds(149, 13, 85, 27);
		driverPanel.add(fileinput);

		download = new JButton("下载");
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisfilename != null) {
					JFileChooser chooser = new JFileChooser(thisfilename);
//					chooser.setSelectedFile(new File(thisfilename));
					chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
					int val = chooser.showSaveDialog(null);
					if (val == JFileChooser.APPROVE_OPTION) {
						if (nextlabelname == null) {
							File f = chooser.getSelectedFile();
							fileMsg = new FileMsg();
							fileMsg.setName(thisfilename);
							fileMsg.setPath(String.valueOf(f));
							fileMsg.setTime(new Date().toLocaleString());
							fileMsg.setUseraccount(backaccount);
							fileMsg.setFatherpath(labelname);
							File toBeRenamed = new File(fileMsg.getPath() + "\\" + fileMsg.getName());
							// 检查要重命名的文件是否存在，是否是文件
							if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
								System.out.println(
										"File does not exist: " + fileMsg.getPath() + "\\" + fileMsg.getName());
								JOptionPane.showMessageDialog(null, "准备下载" + fileMsg.getName());
								cr.sendMsg("skyDiver://LIST@@download##" + fileMsg.toString());
							} else {
								JOptionPane.showMessageDialog(null, "当前文件夹有同名的文件，请重新选择");
							}
						} else {
							File f = chooser.getSelectedFile();
							fileMsg = new FileMsg();
							fileMsg.setName(thisfilename);
							fileMsg.setPath(String.valueOf(f));
							fileMsg.setTime(new Date().toLocaleString());
							fileMsg.setUseraccount(backaccount);
							fileMsg.setFatherpath(nextlabelname);
							File toBeRenamed = new File(fileMsg.getPath() + "\\" + fileMsg.getName());
							// 检查要重命名的文件是否存在，是否是文件
							if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
								System.out.println(
										"File does not exist: " + fileMsg.getPath() + "\\" + fileMsg.getName());
								JOptionPane.showMessageDialog(null, "准备下载" + fileMsg.getName());
								cr.sendMsg("skyDiver://LIST@@download##" + fileMsg.toString());
							} else {
								JOptionPane.showMessageDialog(null, "当前文件夹有同名的文件，请重新选择");
							}
						}
						thisfilename = null;
					}

				}

			}
		});

		download.setBounds(248, 13, 85, 27);
		driverPanel.add(download);

		createFile = new JButton("新建文件夹");
		createFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog(null, "请输入你要建立的文件夹名字").trim();
				System.out.println(inputValue);
				if (inputValue.length() > 0) {
					if (nextlabelname == null) {
						TB_File file = new TB_File();
						file.setFileName(inputValue);
						file.setUserAccount(backaccount);
						file.setFileSize("0");
						file.setFilePath(labelname + "/" + inputValue);
						file.setFileTypeName("folder");
						file.setActTime(new Date().toLocaleString());
						file.setFatherPath(labelname);
						cr.sendMsg("skyDiver://fileact@@newfolder##" + file.toString());
					} else {
						System.out.println("在新的文件夹中插件新的文件夹");
						TB_File file = new TB_File();
						file.setFileName(inputValue);
						file.setUserAccount(backaccount);
						file.setFileSize("0");
						file.setFilePath(nextlabelname + "/" + inputValue);
						file.setFileTypeName("folder");
						file.setActTime(new Date().toLocaleString());
						file.setFatherPath(nextlabelname);
						cr.sendMsg("skyDiver://fileact@@newfolder##" + file.toString());
					}

				}
			}
		});
		createFile.setBounds(347, 13, 107, 27);
		driverPanel.add(createFile);

		rename = new JButton("重命名");
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisfilename != null) {
					String inputValue = JOptionPane.showInputDialog(null, "请选择你要修改的名字").trim();
					System.out.println(inputValue);
					if (inputValue.length() > 0) {
						FileremaneMsg fmsg = new FileremaneMsg();
						String rename;
						if (!thistype.equals("文件夹")) {
							String nameSuffix = thisfilename.substring(thisfilename.lastIndexOf("."));// .后缀名
							rename = inputValue + nameSuffix;
							System.out.println(rename);
						} else {
							rename = inputValue;
						}
						for (int i = 0; i < Data.mystring.length; i++) {
							if (rename.contains(Data.mystring[i])) {
								System.out.println("有违法词语");
								JOptionPane.showMessageDialog(null, "你输入的有违法词缀，请重新输入");
								return;
							}
						}
						if (nextlabelname == null) {
							fmsg.setFiletype(thistype);
							fmsg.setPath(labelname + "/" + thisfilename);
							fmsg.setFathetpath(labelname);
							fmsg.setFilename(thisfilename);
							fmsg.setAccount(backaccount);
							fmsg.setReanme(rename);
							cr.sendMsg("skyDiver://fileact@@rename##" + fmsg.toString());
						} else {
							fmsg.setFiletype(thistype);
							fmsg.setPath(nextlabelname + "/" + thisfilename);
							fmsg.setFathetpath(nextlabelname);
							fmsg.setFilename(thisfilename);
							fmsg.setAccount(backaccount);
							fmsg.setReanme(rename);
							cr.sendMsg("skyDiver://fileact@@rename##" + fmsg.toString());
						}

						thisfilename = null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择一个文件");
				}
			}
		});
		rename.setBounds(468, 13, 85, 27);
		driverPanel.add(rename);

		deletefile = new JButton("删除");
		deletefile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thisfilename != null) {
					if (nextlabelname == null) {
						TB_File file = new TB_File();
						file.setFileName(thisfilename);
						file.setUserAccount(backaccount);
						file.setFileType(thistype);
						file.setFilePath(labelname + "/" + thisfilename);
						file.setFatherPath(labelname);
						cr.sendMsg("skyDiver://fileact@@delete##" + file.toString());
						thisfilename = null;
					} else {
						TB_File file = new TB_File();
						file.setFileName(thisfilename);
						file.setUserAccount(backaccount);
						file.setFileType(thistype);
						file.setFilePath(nextlabelname + "/" + thisfilename);

						file.setFatherPath(nextlabelname);
						cr.sendMsg("skyDiver://fileact@@delete##" + file.toString());
						thisfilename = null;
					}

				} else {
					JOptionPane.showMessageDialog(null, "请选择一个文件");
				}

			}
		});
		deletefile.setBounds(567, 13, 69, 27);
		driverPanel.add(deletefile);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(149, 86, 651, 369);
		driverPanel.add(scrollPane_1);

		MyAllfilePanel = new JPanel();
		scrollPane_1.setViewportView(MyAllfilePanel);
		MyAllfilePanel.setPreferredSize(new Dimension(600, 1000));
		MyAllfilePanel.setBackground(Color.white);
		MyAllfilePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		textField = new JTextField();
		textField.setBounds(684, 14, 86, 24);
		driverPanel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		JButton btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setBounds(784, 13, 85, 27);
		driverPanel.add(btnNewButton_1);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel.setBounds(149, 53, 487, 25);
		driverPanel.add(lblNewLabel);
		lblNewLabel.setPreferredSize(new Dimension(80, 25));

		JButton upFilebutton = new JButton("返回上一级目录");
		upFilebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < vectorrecord.size(); i++) {
					if (i == (headLable - 1)) {
						TB_File tb_File = new TB_File();
						tb_File.setUserAccount(MainFrame.backaccount);
						tb_File.setFatherPath(vectorrecord.get(i).getUppathe());
						cr.sendMsg("skyDiver://fileact@@openfolder##" + tb_File.toString());
						vectorrecord.remove(i);
						headLable = headLable - 1;
					}
				}

			}
		});
		upFilebutton.setBounds(663, 52, 137, 27);
		driverPanel.add(upFilebutton);

		userName = new JLabel("New label");
		userName.setBounds(173, 20, 132, 31);
		contentPane.add(userName);

		infor = new JToggleButton("<HTML><U>个人信息</U></HTML>");
		// 个人信息的动作监听
		infor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationFrame.setVisible(true);

			}
		});
		infor.setFont(new Font("宋体", Font.BOLD, 16));
		infor.setForeground(Color.RED);
		infor.setBorderPainted(false);
		infor.setBounds(690, 13, 113, 27);
		infor.setContentAreaFilled(false);
		contentPane.add(infor);

		inputPanel = new JPanel();
		inputPanel.setBounds(14, 148, 803, 447);
		contentPane.add(inputPanel);
		inputPanel.setLayout(null);

		inputList = new JToggleButton("上传列表");
		inputList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputList.setSelected(true);
				downList.setSelected(false);
				scrollPane.setVisible(true);
				scrollPane1.setVisible(false);
			}
		});

		inputList.setBounds(14, 26, 137, 65);
		inputPanel.add(inputList);

		downList = new JToggleButton("下载列表");
		downList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downList.setSelected(true);
				inputList.setSelected(false);
				scrollPane.setVisible(false);
				scrollPane1.setVisible(true);
			}
		});
		downList.setBounds(14, 143, 137, 65);
		inputPanel.add(downList);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(163, 26, 630, 421);
		inputPanel.add(scrollPane);
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(630, 1000));
		scrollPane.setViewportView(panel);

		scrollPane1 = new JScrollPane();
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(163, 26, 630, 421);
		inputPanel.add(scrollPane1);

		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel1.setBackground(Color.yellow);
		panel1.setPreferredSize(new Dimension(630, 1000));
		scrollPane1.setViewportView(panel1);
		scrollPane1.setVisible(false);
		inputPanel.setVisible(false);
		Data.myfolder
				.setImage(Data.myfolder.getImage().getScaledInstance(30, 30, Data.myfolder.getImage().SCALE_DEFAULT));

	}

//	Data.mytext.setImage(Data.mytext.getImage().getScaledInstance(30, 30, Data.mytext.getImage().SCALE_DEFAULT));
//	Data.myimage.setImage(Data.myimage.getImage().getScaledInstance(30, 30, Data.myimage.getImage().SCALE_DEFAULT));
//	Data.mymusic.setImage(Data.mymusic.getImage().getScaledInstance(30, 30, Data.mymusic.getImage().SCALE_DEFAULT));
//	Data.ohter.setImage(Data.ohter.getImage().getScaledInstance(30, 30, Data.ohter.getImage().SCALE_DEFAULT));
	/**
	 * 文件图标的生成
	 * 
	 * @param filepath
	 */
	public void Myfilebutton(String dataname, String type, String filepath) {
		mybutton = new JButton();
		mybutton.setText("<html>" + dataname + "<html>");

		switch (type) {
		case "文档":
			mybutton.setIcon(Data.mytext);
			break;
		case "音乐":
			mybutton.setIcon(Data.mymusic);
			break;
		case "图片":
			mybutton.setIcon(Data.myimage);
			break;
		case "其他":
			mybutton.setIcon(Data.ohter);
			break;
		case "文件夹":
			mybutton.setIcon(Data.myfolder);
			break;
		}
		mybutton.setVerticalTextPosition(JButton.BOTTOM);
		mybutton.setHorizontalTextPosition(JButton.CENTER);
		mybutton.setPreferredSize(new Dimension(100, 100));
		mybutton.setContentAreaFilled(false);

		MybuttonFile buttonfile = new MybuttonFile();
		buttonfile.setMybutton(mybutton);
		buttonfile.setFileName(dataname);
		buttonfile.setFiletype(type);
		buttonfile.setFilepath(filepath);
		fileVector.add(buttonfile);
		mybutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thisfilename = dataname;
				thistype = type;
				System.out.println(dataname + "我点到是这个文件的名字并且类型是" + type + "还有？" + filepath);
			}
		});

		mybutton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if ((e.getButton() == MouseEvent.BUTTON1)) {

					if (e.getClickCount() == 1) {
						System.out.println("鼠标的时间" + dataname);
					} else if (e.getClickCount() == 2) {
						System.out.println("鼠标的时间*2" + dataname);
						if (type.equals("文件夹")) {
							TB_File tb_File = new TB_File();

							tb_File.setUserAccount(MainFrame.backaccount);
							nextlabelname = filepath;
							uplabelname = filepath.split("/" + dataname)[0];
							tb_File.setFatherPath(MainFrame.nextlabelname);
							cr.sendMsg("skyDiver://fileact@@openfolder##" + tb_File.toString());
							MylabelRecord currentDirectory = new MylabelRecord();

							currentDirectory.setNextpath(nextlabelname);
							currentDirectory.setUppathe(uplabelname);

							headLable = headLable + 1;
							vectorrecord.add(currentDirectory);
						}
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				if (!type.equals("文件夹")) {
					if (e.isMetaDown()) {
//		              showPopupMenu(e.getComponent(),e.getX(), e.getY());				
						// 鼠标释放
						// 如果是鼠标右键，则显示弹出菜单
						// 创建 弹出菜单 对象
						JPopupMenu popupMenu = new JPopupMenu();
						// 创建 一级菜单
						JMenu editMenu = new JMenu("移动");
						popupMenu.add(editMenu);
						for (int i = 0; i < fileVector.size(); i++) {
							if (fileVector.get(i).getFiletype().equals("文件夹")) {
								if (fileVector.get(i).getFileName() != dataname) {
									JMenuItem jMenuItem = new JMenuItem(fileVector.get(i).getFilepath());
									editMenu.add(jMenuItem);
									String fileitem = fileVector.get(i).getFileName();
									jMenuItem.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											System.out.println(jMenuItem.getText() + "被点击了");
											if (nextlabelname == null) {
												TB_File tb_File = new TB_File();
												tb_File.setFileitme(fileitem);
												tb_File.setFileName(dataname);
												tb_File.setUserAccount(backaccount);
												tb_File.setNewfilePath(jMenuItem.getText() + "/" + dataname);
												tb_File.setFilePath(labelname + "/" + dataname);
												System.out.println(tb_File.getFilePath());
												tb_File.setActTime(new Date().toLocaleString());
												tb_File.setNewfatherPath(jMenuItem.getText());
												tb_File.setFatherPath(labelname);
												System.out.println(tb_File.getFatherPath());
												cr.sendMsg("skyDiver://fileact@@move##" + tb_File.toString());
											} else {
												TB_File tb_File = new TB_File();
												tb_File.setFileitme(fileitem);
												tb_File.setFileName(dataname);
												tb_File.setUserAccount(backaccount);
												tb_File.setNewfilePath(jMenuItem.getText() + "/" + dataname);
												tb_File.setFilePath(nextlabelname + "/" + dataname);
												System.out.println(tb_File.getFilePath());
												tb_File.setActTime(new Date().toLocaleString());
												tb_File.setNewfatherPath(jMenuItem.getText());
												tb_File.setFatherPath(nextlabelname);
												System.out.println(tb_File.getFatherPath());
												cr.sendMsg("skyDiver://fileact@@move##" + tb_File.toString());
											}

										}
									});
								}

							}
						}
						// 在指定位置显示弹出菜单
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}

				}
			}

		});

	}

	/**
	 * 文件图标的移除
	 */
	public void deleteMyfilebutton() {
		for (int i = 0; i < fileVector.size(); i++) {
			MyAllfilePanel.remove(fileVector.get(i).getMybutton());
			fileVector.remove(i);
		}

	}

	/**
	 * 去除标签的方法
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}

	/**
	 * 下载列表进度条的生成
	 */
	public void MyDownProgressBar() {
		lable = new JLabel();
		lable.setPreferredSize(new Dimension(180, 40));
		progressBar_1 = new JProgressBar();
		progressBar_1.setPreferredSize(new Dimension(400, 40));
		progressBar_1.setFont(new Font("方正舒体", Font.PLAIN, 17));
		progressBar_1.setForeground(Color.green);// 设置前景色
		progressBar_1.setMinimum(0);// 设置最小值
		progressBar_1.setMaximum(100);// 设置最大值
		progressBar_1.setStringPainted(true);// 设置显示进度数字
		progressBar_1.setValue(0);
		fileProgressBar filepro = new fileProgressBar();
		lable.setText("<html>" + fileName + "</html>");
		filepro.setFileName(fileName);
		filepro.setJpro(progressBar_1);
		filepro.setLable(lable);
		vector1.add(filepro);
	}

	/**
	 * 上传列表进度条的生成
	 */
	public void MyProgressBar() {
		lable = new JLabel();
		lable.setPreferredSize(new Dimension(180, 40));
		progressBar_1 = new JProgressBar();
		progressBar_1.setPreferredSize(new Dimension(400, 40));
		progressBar_1.setFont(new Font("方正舒体", Font.PLAIN, 17));
		progressBar_1.setForeground(Color.green);// 设置前景色
		progressBar_1.setMinimum(0);// 设置最小值
		progressBar_1.setMaximum(100);// 设置最大值
		progressBar_1.setStringPainted(true);// 设置显示进度数字
		progressBar_1.setValue(0);
		fileProgressBar filepro = new fileProgressBar();
		lable.setText("<html>" + fileName + "</html>");
		filepro.setFileName(fileName);
		filepro.setJpro(progressBar_1);
		filepro.setLable(lable);
		vector.add(filepro);
	}

	/**
	 * 删除上传进度条，当100的时候
	 */
	public void deleteProgressBar() {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).getJpro().getValue() == 100) {
				panel.remove(vector.get(i).getLable());
				panel.remove(vector.get(i).getJpro());
				vector.remove(i);
			}
		}

	}

	public JPanel getMyAllfilePanel() {
		return MyAllfilePanel;
	}

	public void setMyAllfilePanel(JPanel myAllfilePanel) {
		MyAllfilePanel = myAllfilePanel;
	}

	public JButton getMusic() {
		return music;
	}

	public void setMusic(JButton music) {
		this.music = music;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public MyActionListener getMyac() {
		return myac;
	}

	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}

	public JToggleButton getList() {
		return list;
	}

	public void setList(JToggleButton list) {
		this.list = list;
	}

	public JToggleButton getMydrive() {
		return mydrive;
	}

	public void setMydrive(JToggleButton mydrive) {
		this.mydrive = mydrive;
	}

	public JButton getDownload() {
		return download;
	}

	public void setDownload(JButton download) {
		this.download = download;
	}

	public JButton getFileinput() {
		return fileinput;
	}

	public void setFileinput(JButton fileinput) {
		this.fileinput = fileinput;
	}

	public JLabel getUsespace() {
		return usespace;
	}

	public void setUsespace(JLabel usespace) {
		this.usespace = usespace;
	}

	public JButton getButton_2() {
		return button_2;
	}

	public void setButton_2(JButton button_2) {
		this.button_2 = button_2;
	}

	public JButton getFilebutton() {
		return music;
	}

	public void setFilebutton(JButton filebutton) {
		this.music = filebutton;
	}

	public JButton getTextbutton() {
		return textbutton;
	}

	public void setTextbutton(JButton textbutton) {
		this.textbutton = textbutton;
	}

	public JButton getImagebutton() {
		return imagebutton;
	}

	public void setImagebutton(JButton imagebutton) {
		this.imagebutton = imagebutton;
	}

	public JLabel getUserName() {
		return userName;
	}

	public void setUserName(JLabel userName) {
		this.userName = userName;
	}

	public InformationFrame getInformationFrame() {
		return informationFrame;
	}

	public void setInformationFrame(InformationFrame informationFrame) {
		this.informationFrame = informationFrame;
	}

	public JToggleButton getInfor() {
		return infor;
	}

	public void setInfor(JToggleButton infor) {
		this.infor = infor;
	}

	public JPanel getInputPanel() {
		return inputPanel;
	}

	public void setInputPanel(JPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public JToggleButton getInputList() {
		return inputList;
	}

	public void setInputList(JToggleButton inputList) {
		this.inputList = inputList;
	}

	public JToggleButton getDownList() {
		return downList;
	}

	public void setDownList(JToggleButton downList) {
		this.downList = downList;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public void setScrollPane1(JScrollPane scrollPane1) {
		this.scrollPane1 = scrollPane1;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getDriverPanel() {
		return driverPanel;
	}

	public void setDriverPanel(JPanel driverPanel) {
		this.driverPanel = driverPanel;
	}

	public ClientRunnable getCr() {
		return cr;
	}

	public void setCr(ClientRunnable cr) {
		this.cr = cr;
	}

	public JButton getCreateFile() {
		return createFile;
	}

	public void setCreateFile(JButton createFile) {
		this.createFile = createFile;
	}

	public JButton getRename() {
		return rename;
	}

	public void setRename(JButton rename) {
		this.rename = rename;
	}

	public JButton getDeletefile() {
		return deletefile;
	}

	public void setDeletefile(JButton deletefile) {
		this.deletefile = deletefile;
	}

	public static String getBackaccount() {
		return backaccount;
	}

	public static void setBackaccount(String backaccount) {
		MainFrame.backaccount = backaccount;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JProgressBar getProgressBar_1() {
		return progressBar_1;
	}

	public void setProgressBar_1(JProgressBar progressBar_1) {
		this.progressBar_1 = progressBar_1;
	}

	public FileMsg getFileMsg() {
		return fileMsg;
	}

	public void setFileMsg(FileMsg fileMsg) {
		this.fileMsg = fileMsg;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
