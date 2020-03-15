package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import control.CilentWindowListener;
import control.MyActionListener;
import javabean.TB_File;
import javabean.TB_FileMsg;
import javabean.TB_User;
import javabean.TB_UserMsg;
import model.ClientRunnable;

import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class UserInformationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel,userAccount;
	private JButton query;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton trueStar;
	private JButton falseStar;
	private JButton up;
	private JButton next;
	private JLabel currentNumbe;
	private JLabel totalNumber;
	private JLabel label_1;
	private JTabbedPane tabbedPane;
	private MyTableModel table_1;
	private JScrollPane scrollPane_1;
	private MyActionListener myac;
	private Object[][] cellData;
	private ArrayList<TB_User>array;
	private ClientRunnable cr;
	private MyTableModel tablemodel ;
	private int flag;//0为全表查询的标签，1为条件模糊搜索的标签
	private Object[][] cellData1;
	private JTable table1;
	private ArrayList<TB_File> array1;
	private static String backupAccount;//条件搜索的备份账号
 	private static String backupfile;//条件搜索的备份文件名账号
	private static String updateAccount ;//点击某行的获得的想要修改用户账号
	private int mark;//0为用户管理的标记，1为文件管理的标签
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button;
	private JButton button_1;
	private JTextField filetextField_1;
	private Component filename;
	private JButton filebutton_2;
	private int flag1;
	/**
	 * Create the frame.
	 * @param array 
	 * @param cr 
	 * @param myac 
	 */
	public UserInformationFrame(ArrayList<TB_User> array, ArrayList<TB_File> array1,ClientRunnable cr) {
		super("网盘后台管理系统");
		addWindowListener(new CilentWindowListener(this,cr));
         this.array=array;
         this.array1=array1;
         this.cr=cr;
         cr.setInfor(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 

		textField = new JTextField();
		textField.setBounds(78, 76, 174, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("网盘后台管理系统");
		lblNewLabel.setBounds(182, 13, 251, 41);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		userAccount = new JLabel("用户名：");
		userAccount.setBounds(14, 79, 72, 18);
		contentPane.add(userAccount);
		
		query = new JButton("查询");
		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mark==0) {
					if (textField.getText().length()>0) {//有输入才有搜索
						flag=1;//条件搜索的标签
						String query=textField.getText();
						backupAccount=query;//存入本类静态属性的备份账号里面
						TB_User tb_User=new TB_User();
						tb_User.setUserAccount(query);
						cr.sendMsg("query://"+tb_User.toString());
						up.setEnabled(true);
						next.setEnabled(true);		
					}else {//输入框为空，则不进行搜索，给用户提示相关信息
						JOptionPane.showMessageDialog(null, "你没有输入搜索条件，不能进行搜索");
					}
				} 
				
				
			}
		});
		query.setBounds(266, 75, 78, 27);
		contentPane.add(query);
		
		trueStar = new JButton("启用");
		trueStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag==0) { 
					TB_UserMsg tb_UserMsg=new TB_UserMsg();
					tb_UserMsg.setUpdateAccount(updateAccount);
					tb_UserMsg.setState(trueStar.getText());
					tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText()));
					tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));
					cr.sendMsg("trueStar://"+tb_UserMsg.toString());
			        updateAccount=null;
				}else if (flag==1) {
					TB_UserMsg tb_UserMsg=new TB_UserMsg();
					tb_UserMsg.setUserAccount(backupAccount);
					tb_UserMsg.setUpdateAccount(updateAccount);
					tb_UserMsg.setState(trueStar.getText());
					tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText()));
					tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));	
					cr.sendMsg("trueStarAndFlag=1://"+tb_UserMsg.toString());
					updateAccount=null;

				}
				
			}
		});
		trueStar.setBounds(358, 75, 85, 27);
		contentPane.add(trueStar);
		
		falseStar = new JButton("禁用");
		falseStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag==0) { 
					TB_UserMsg tb_UserMsg=new TB_UserMsg();
					tb_UserMsg.setUpdateAccount(updateAccount);
					tb_UserMsg.setState(falseStar.getText());
					tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText()));
					tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));
					cr.sendMsg("falseStar://"+tb_UserMsg.toString());
			        updateAccount=null;
				}else if (flag==1) {
					TB_UserMsg tb_UserMsg=new TB_UserMsg();
					tb_UserMsg.setUserAccount(backupAccount);
					tb_UserMsg.setUpdateAccount(updateAccount);
					tb_UserMsg.setState(falseStar.getText());
					tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText()));
					tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));
					cr.sendMsg("falseStarAndFlag=1://"+tb_UserMsg.toString());
			        updateAccount=null;
				}
				
				
				
			}
		});
		falseStar.setBounds(457, 75, 78, 27);
		contentPane.add(falseStar);
		
		up = new JButton("上一页");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag==0) {
					if (Integer.valueOf(currentNumbe.getText())>1) {
						next.setEnabled(true);             
						TB_UserMsg tb_UserMsg=new TB_UserMsg();
						tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText())-1);
						tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));				
						cr.sendMsg("up://"+tb_UserMsg.toString());
					}else {
						up.setEnabled(false);
					}	
				}else if (flag==1) {
		
					if (Integer.valueOf(currentNumbe.getText())>1) {
						next.setEnabled(true);             
						TB_UserMsg tb_UserMsg=new TB_UserMsg();
						tb_UserMsg.setUserAccount(backupAccount);
						tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText())-1);
						tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));				
						cr.sendMsg("queryup://"+tb_UserMsg.toString());
					}else {
						up.setEnabled(false);
					}	
				}
				
				
			}
		});
		up.setBounds(50, 303, 113, 27);
		contentPane.add(up);
		
		
		next = new JButton("下一页");	
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (flag==0) {
						if (Integer.valueOf(currentNumbe.getText())<(Integer.valueOf(totalNumber.getText()))) {
							up.setEnabled(true);
							TB_UserMsg tb_UserMsg=new TB_UserMsg();
							tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText())+1);
							tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));				
							cr.sendMsg("next://"+tb_UserMsg.toString());
						}else {
							next.setEnabled(false);
						}
						}else if (flag==1) {
							if (Integer.valueOf(currentNumbe.getText())<(Integer.valueOf(totalNumber.getText()))) {
								up.setEnabled(true);
								TB_UserMsg tb_UserMsg=new TB_UserMsg();
								tb_UserMsg.setUserAccount(backupAccount);
								tb_UserMsg.setCurrentPage(Integer.valueOf(currentNumbe.getText())+1);
								tb_UserMsg.setTotalPage(Integer.valueOf(totalNumber.getText()));				
								cr.sendMsg("querynext://"+tb_UserMsg.toString());
							}else {
								next.setEnabled(false);
							}
				}           		
			}
		});
		next.setBounds(358, 303, 113, 27);
		contentPane.add(next);
		
		currentNumbe = new JLabel("1");
		currentNumbe.setBounds(215, 307, 26, 18);
		contentPane.add(currentNumbe);
		
		totalNumber = new JLabel("1");
		totalNumber.setBounds(299, 307, 26, 18);
		contentPane.add(totalNumber);
		
		label_1 = new JLabel("/");
		label_1.setBounds(255, 307, 19, 18);
		contentPane.add(label_1);
		
	     tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(14, 113, 485, 174);
		
		contentPane.add(tabbedPane);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("用户管理", null, scrollPane, null);
//		//用户管理列表
		tablemodel = new MyTableModel();
		String[] name = { "ID", "用户名", "昵称", "用户等级", "已使用空间", "空间大小", "积分","注册时间","状态" };
     	flag=0;
     	mark=0;
		tabledata(); 
		tablemodel.setDataVector(cellData, name);	  	
		table = new JTable();
		table.setModel(tablemodel);
		table.getTableHeader().setReorderingAllowed(false);//禁止拖拽
		
		scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent e) {
        		System.out.println("mouseClicked()"); 
        		System.out.println("總行數row numbers is :"+table.getRowCount());//获取表格的总行数
        	 //获取鼠标点击的行的位置（及行数）
        		Point mousepoint;
        		mousepoint =e.getPoint();
        	int	ROW=table.rowAtPoint(mousepoint)+1;
        	System.out.println("點擊了第"+ROW+"行");
        	updateAccount = table.getValueAt(ROW-1,1).toString();
        	System.out.println(updateAccount);
        	}
});

						
		scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("文件管理", null, scrollPane_1, null);
		//文件管理列表
		table_1 = new MyTableModel();
		String[] name1 = { "ID", "上传用户", "文件名", "文件路径", "文件大小", "文件类型", "最后修改时间" };
     	setFlag1(0);
		tabledata1(); 
		table_1.setDataVector(cellData1, name1);	  	
		table1 = new JTable();
		table1.setModel(table_1);
		table1.getTableHeader().setReorderingAllowed(false);//禁止拖拽
		scrollPane_1.setViewportView(table1);
		
		 label = new JLabel("0");
		label.setBounds(215, 312, 26, 18);
		contentPane.add(label);
		label.setVisible(false);
		 label_2 = new JLabel("/");
		label_2.setBounds(255, 312, 19, 18);
		contentPane.add(label_2);
		label_2.setVisible(false);
		 label_3 = new JLabel("0");
		label_3.setBounds(299, 312, 26, 18);
		contentPane.add(label_3);
		label_3.setVisible(false);
		 button = new JButton("上一页");
		button.setBounds(50, 303, 113, 27);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				
 				if (flag1==0) {
 					if (Integer.valueOf(label.getText())>1) {
 						button_1.setEnabled(true);             
 						TB_FileMsg tb_FileMsg=new TB_FileMsg();
 						tb_FileMsg.setCurrentPage(Integer.valueOf(label.getText())-1);
 						tb_FileMsg.setTotalPage(Integer.valueOf(label_3.getText()));				
 						cr.sendMsg("fileup://"+tb_FileMsg.toString());
 					}else {
 						button.setEnabled(false);
 					}	
				}
 				else if (flag1==1) {
					if (Integer.valueOf(label.getText())>1) {
 						button_1.setEnabled(true);             
						TB_FileMsg tb_FileMsg=new TB_FileMsg();
						tb_FileMsg.setFilename(backupfile);
						tb_FileMsg.setCurrentPage(Integer.valueOf(label.getText())-1);
						tb_FileMsg.setTotalPage(Integer.valueOf(label_3.getText()));				
						cr.sendMsg("filequeryup://"+tb_FileMsg.toString());				
					}else {
 						button.setEnabled(false);
					}		
 				
 			}}
 			});
		button.setVisible(false);
		 button_1 = new JButton("下一页");
		button_1.setBounds(358, 303, 113, 27);
		contentPane.add(button_1);
		
		 filename = new JLabel("文件名：");
		filename.setBounds(14, 79, 72, 18);
		contentPane.add(filename);
		filename.setVisible(false);
		filetextField_1 = new JTextField();
		filetextField_1.setColumns(10);
		filetextField_1.setBounds(78, 76, 174, 24);
		contentPane.add(filetextField_1);
		filetextField_1.setVisible(false);
		 filebutton_2 = new JButton("查询");
		 filebutton_2.addActionListener(new ActionListener() {
	

			public void actionPerformed(ActionEvent e) {		 		
		 		if (mark==1) {
					if (filetextField_1.getText().length()>0) {//有输入才有搜索
						flag1=1;//条件搜索的标签
						String queryfile=filetextField_1.getText();
						backupfile=queryfile;//备份搜素的文件名里面
						TB_File tb_File=new TB_File();
						tb_File.setFileName(queryfile);
						cr.sendMsg("queryfile://"+tb_File.toString());
						up.setEnabled(true);
						next.setEnabled(true);		
					}else {//输入框为空，则不进行搜索，给用户提示相关信息
						JOptionPane.showMessageDialog(null, "你没有输入搜索条件，不能进行搜索");
					}
				}
		 		
		 	}
		 });
		filebutton_2.setBounds(266, 73, 78, 27);
		contentPane.add(filebutton_2);
		
		JButton refresh = new JButton("刷新");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				TB_User tb_User=new TB_User();
					cr.sendMsg("queryrefresh://"+tb_User.toString());
					up.setEnabled(true);
					next.setEnabled(true);					
			}
		});
		refresh.setBounds(422, 35, 113, 27);
		contentPane.add(refresh);
		filebutton_2.setVisible(false);
		
		 button_1.setVisible(false);
         button_1.addActionListener(new ActionListener() {
        	
 			public void actionPerformed(ActionEvent e) {
 				if (flag1==0) {
 					if (Integer.valueOf(label.getText())<(Integer.valueOf(label_3.getText()))) {
						button.setEnabled(true);
						TB_FileMsg tb_FileMsg=new TB_FileMsg();
						tb_FileMsg.setCurrentPage(Integer.valueOf(label.getText())+1);
						tb_FileMsg.setTotalPage(Integer.valueOf(label_3.getText()));				
						cr.sendMsg("filenext://"+tb_FileMsg.toString());
					}else {
						button_1.setEnabled(false);
			}
					
				}else if (flag1==1) {
					if (Integer.valueOf(label.getText())<(Integer.valueOf(label_3.getText()))) {
						button.setEnabled(true);
						TB_FileMsg tb_FileMsg=new TB_FileMsg();
						tb_FileMsg.setFilename(backupfile);
						tb_FileMsg.setCurrentPage(Integer.valueOf(label.getText())+1);
						tb_FileMsg.setTotalPage(Integer.valueOf(label_3.getText()));				
						cr.sendMsg("filequerynext://"+tb_FileMsg.toString());				
					}else {
						button_1.setEnabled(false);
					}				
					
				}}  
 			});

		 tabbedPane.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					if (((JTabbedPane) e.getSource()).getSelectedIndex() == 0) {
						mark = 0;
                        userAccount.setVisible(true);
						up.setVisible(true);
						next.setVisible(true);
						totalNumber.setVisible(true);
						label_1.setVisible(true);
						currentNumbe.setVisible(true);
						label.setVisible(false);
						label_2.setVisible(false);
						label_3.setVisible(false);
						button.setVisible(false);
						button_1.setVisible(false);							
							filename.setVisible(false);						
							filetextField_1.setVisible(false);							
							filebutton_2.setVisible(false);
							textField.setVisible(true);
							query.setVisible(true);
					} else if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1) {
						
						mark = 1;
						textField.setVisible(false);
						query.setVisible(false);
                        userAccount.setVisible(false);
						up.setVisible(false);
						next.setVisible(false);
						totalNumber.setVisible(false);
						label_1.setVisible(false);
						currentNumbe.setVisible(false);
						label.setVisible(true);
						label_2.setVisible(true);
						label_3.setVisible(true);
						button.setVisible(true);
						button_1.setVisible(true);
						filename.setVisible(true);						
						filetextField_1.setVisible(true);						
						filebutton_2.setVisible(true);
					}
				}
			});
		 
	}
	/**
	 * 文件管理插入数据
	 */
     public void tabledata1() {
		
		if (array1.size() != 0) {
			cellData1 = new Object[array1.size()][7];
			for (int i = 0; i < array1.size(); i++) {
				cellData1[i][0] = array1.get(i).getID();
				cellData1[i][1] = array1.get(i).getUserAccount();
				cellData1[i][2] = array1.get(i).getFileName();
				cellData1[i][3] = array1.get(i).getFilePath();
				cellData1[i][4] = array1.get(i).getFileSize();
				cellData1[i][5] = array1.get(i).getFileTypeName();
				cellData1[i][6] = array1.get(i).getActTime();				
			}
			for (int i = 0; i < cellData1.length; i++)
			{
				table_1.addRow(cellData1[i]);
			}
			}
		
	}
 /**
  * 用户管理插入数据
  */
	public void tabledata() {
		
		if (array.size() != 0) {
			cellData = new Object[array.size()][9];
			for (int i = 0; i < array.size(); i++) {
				cellData[i][0] = array.get(i).getID();
				cellData[i][1] = array.get(i).getUserAccount();
				cellData[i][2] = array.get(i).getUserName();
				cellData[i][3] = array.get(i).getUserLevel();
				cellData[i][4] = array.get(i).getUserUsedspace();
				cellData[i][5] = array.get(i).getSpace();
				cellData[i][6] = array.get(i).getUserScore();
				cellData[i][7] = array.get(i).getUserRegTime();
				cellData[i][8] = array.get(i).getUserStage();
				
			}
			for (int i = 0; i < cellData.length; i++)
			{
				tablemodel.addRow(cellData[i]);
			}
			}
		
	}
	
	
	
	public ArrayList<TB_File> getArray1() {
	return array1;
}
public void setArray1(ArrayList<TB_File> array1) {
	this.array1 = array1;
}

public int getMark() {
	return mark;
}
public void setMark(int mark) {
	this.mark = mark;
}
public JButton getButton() {
	return button;
}
public void setButton(JButton button) {
	this.button = button;
}
public JButton getButton_1() {
	return button_1;
}
public void setButton_1(JButton button_1) {
	this.button_1 = button_1;
}
	public JLabel getLabel() {
	return label;
}
public void setLabel(JLabel label) {
	this.label = label;
}
public JLabel getLabel_2() {
	return label_2;
}
public void setLabel_2(JLabel label_2) {
	this.label_2 = label_2;
}
public JLabel getLabel_3() {
	return label_3;
}
public void setLabel_3(JLabel label_3) {
	this.label_3 = label_3;
}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}
	public JLabel getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(JLabel userAccount) {
		this.userAccount = userAccount;
	}
	public JButton getQuery() {
		return query;
	}
	public void setQuery(JButton query) {
		this.query = query;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public JButton getTrueStar() {
		return trueStar;
	}
	public void setTrueStar(JButton trueStar) {
		this.trueStar = trueStar;
	}
	public JButton getFalseStar() {
		return falseStar;
	}
	public void setFalseStar(JButton falseStar) {
		this.falseStar = falseStar;
	}
	public JButton getUp() {
		return up;
	}
	public void setUp(JButton up) {
		this.up = up;
	}
	public JButton getNext() {
		return next;
	}
	public void setNext(JButton next) {
		this.next = next;
	}
	public JLabel getCurrentNumbe() {
		return currentNumbe;
	}
	public void setCurrentNumbe(JLabel currentNumbe) {
		this.currentNumbe = currentNumbe;
	}
	public JLabel getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(JLabel totalNumber) {
		this.totalNumber = totalNumber;
	}
	public JLabel getLabel_1() {
		return label_1;
	}
	public void setLabel_1(JLabel label_1) {
		this.label_1 = label_1;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	public MyTableModel getTable_1() {
		return table_1;
	}
	public void setTable_1(MyTableModel table_1) {
		this.table_1 = table_1;
	}
	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}
	public void setScrollPane_1(JScrollPane scrollPane_1) {
		this.scrollPane_1 = scrollPane_1;
	}
	public MyActionListener getMyac() {
		return myac;
	}
	public void setMyac(MyActionListener myac) {
		this.myac = myac;
	}
	public Object[][] getCellData() {
		return cellData;
	}
	public void setCellData(Object[][] cellData) {
		this.cellData = cellData;
	}
	public ArrayList<TB_User> getArray() {
		return array;
	}
	public void setArray(ArrayList<TB_User> array) {
		this.array = array;
	}
	public ClientRunnable getCr() {
		return cr;
	}
	public void setCr(ClientRunnable cr) {
		this.cr = cr;
	}
	public MyTableModel getTablemodel() {
		return tablemodel;
	}
	public void setTablemodel(MyTableModel tablemodel) {
		this.tablemodel = tablemodel;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Object[][] getCellData1() {
		return cellData1;
	}
	public void setCellData1(Object[][] cellData1) {
		this.cellData1 = cellData1;
	}
	public JTable getTable1() {
		return table1;
	}
	public void setTable1(JTable table1) {
		this.table1 = table1;
	}
	public static String getBackupAccount() {
		return backupAccount;
	}
	public static void setBackupAccount(String backupAccount) {
		UserInformationFrame.backupAccount = backupAccount;
	}
	public static String getUpdateAccount() {
		return updateAccount;
	}
	public static void setUpdateAccount(String updateAccount) {
		UserInformationFrame.updateAccount = updateAccount;
	}
	public int getFlag1() {
		return flag1;
	}
	public void setFlag1(int flag1) {
		this.flag1 = flag1;
	}
}
