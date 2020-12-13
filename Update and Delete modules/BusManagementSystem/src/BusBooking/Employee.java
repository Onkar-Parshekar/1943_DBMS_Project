package BusBooking;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class Employee extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JDateChooser dateChooser;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private int selected;
	private String fname, lname, dob, gender, phno, city, state, lic, role;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Employee() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(27,28,29));
		panel.setBounds(0, 0, 480, 1080);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(27,28,29));
		panel_1.setBounds(500, 0, 849, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel empLabel = new JLabel("");
		Image empImg = new ImageIcon(this.getClass().getResource("/emp.png")).getImage();
		empLabel.setIcon(new ImageIcon(empImg));
		empLabel.setBounds(146, 32, 167, 96);
		panel.add(empLabel);
		
		JLabel lblNewLabel = new JLabel("Employee Details");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(132, 131, 200, 50);
		panel.add(lblNewLabel);
		
		JLabel empNameLabel = new JLabel("First Name : ");
		empNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel.setForeground(Color.WHITE);
		empNameLabel.setBounds(31, 192, 116, 17);
		panel.add(empNameLabel);
		
		JLabel empNameLabel_1 = new JLabel("Date of Birth : ");
		empNameLabel_1.setForeground(Color.WHITE);
		empNameLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_1.setBounds(31, 289, 136, 21);
		panel.add(empNameLabel_1);
		
		JLabel empNameLabel_2 = new JLabel("Gender : ");
		empNameLabel_2.setForeground(Color.WHITE);
		empNameLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_2.setBounds(31, 341, 86, 17);
		panel.add(empNameLabel_2);
		
		JLabel empNameLabel_3 = new JLabel("Contact No : ");
		empNameLabel_3.setForeground(Color.WHITE);
		empNameLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_3.setBounds(31, 391, 116, 17);
		panel.add(empNameLabel_3);
		
		JLabel empNameLabel_4 = new JLabel("City : ");
		empNameLabel_4.setForeground(Color.WHITE);
		empNameLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_4.setBounds(31, 437, 66, 17);
		panel.add(empNameLabel_4);
		
		JLabel empNameLabel_5 = new JLabel("State : ");
		empNameLabel_5.setForeground(Color.WHITE);
		empNameLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_5.setBounds(31, 484, 66, 17);
		panel.add(empNameLabel_5);
		
		JLabel empNameLabel_6 = new JLabel("License No : ");
		empNameLabel_6.setForeground(Color.WHITE);
		empNameLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_6.setBounds(31, 534, 146, 17);
		panel.add(empNameLabel_6);
		
		JLabel empNameLabel_7 = new JLabel("Role/Designation : ");
		empNameLabel_7.setForeground(Color.WHITE);
		empNameLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_7.setBounds(31, 579, 166, 21);
		panel.add(empNameLabel_7);
		
		JLabel lblNewLabel_7 = new JLabel("First Name : ");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(20, 32, 100, 50);
		panel_1.add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		textField_5.setBounds(118, 49, 180, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Last Name : ");
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7_1.setBounds(358, 32, 100, 50);
		panel_1.add(lblNewLabel_7_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(456, 49, 180, 20);
		panel_1.add(textField_6);
		
		textField = new JTextField();
		textField.setBounds(227, 192, 222, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(227, 392, 222, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(227, 438, 222, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(227, 485, 222, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(227, 535, 222, 20);
		panel.add(textField_4);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(227, 290, 222, 20);
		panel.add(dateChooser);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Rather Not Say"}));
		comboBox.setBounds(227, 341, 105, 22);
		panel.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Driver", "Conductor"}));
		comboBox_1.setBounds(227, 581, 105, 22);
		panel.add(comboBox_1);
		
		btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setBounds(750, 83, 89, 23);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		btnNewButton = new JButton("Done");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(78, 635, 130, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(264, 635, 130, 60);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2 = new JButton("View");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBackground(new Color(0, 139, 139));
		btnNewButton_2.setBounds(360, 11, 89, 23);
		panel.add(btnNewButton_2);
		
		empNameLabel_8 = new JLabel("Last Name : ");
		empNameLabel_8.setForeground(Color.WHITE);
		empNameLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empNameLabel_8.setBounds(31, 240, 116, 17);
		panel.add(empNameLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(227, 241, 222, 20);
		panel.add(textField_7);
		btnNewButton_2.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 115, 849, 580); //500, 23, 849, 700
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"First Name", "Last Name", "Date of Birth", "Gender", "Phone No", "City", "State", "License No", "Role"
			}
		));
		scrollPane.setViewportView(table_1);
		table_1.addMouseListener(this);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	//private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel empNameLabel_8;
	private JTextField textField_7;
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking", "root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewData() {
		try {
			pst = con.prepareStatement("Select firstname,lastname,dob,gender,phoneNo,city,state,licenseNo,role from employee");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel)table_1.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v1 = new Vector();
				for(int i=1; i<=c; i++) {
					v1.add(rs.getString("firstname"));
					v1.add(rs.getString("lastname"));
					v1.add(rs.getString("dob"));
					v1.add(rs.getString("gender"));
					v1.add(rs.getString("phoneNo"));
					v1.add(rs.getString("city"));
					v1.add(rs.getString("state"));
					v1.add(rs.getString("licenseNo"));
					v1.add(rs.getString("role"));
				}
				d.addRow(v1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public int searchLicense(String licNo) {
		int found=0;
		try {
			pst = con.prepareStatement("Select licenseNo from employee where licenseNo = ?");
			pst.setString(1, licNo);
			rs = pst.executeQuery();
			while(rs.next()) {
				found=1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return found;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnNewButton) {
			String empName = textField.getText();
			String emplname = textField_7.getText();
			SimpleDateFormat Df = new SimpleDateFormat("yyy-MM-dd");
			String empDob = Df.format(dateChooser.getDate());
			String empGender = comboBox.getSelectedItem().toString();
			String empPhone = textField_1.getText();
			String empCity = textField_2.getText();
			String empState = textField_3.getText();
			String empLicense = textField_4.getText();
			String empRole = comboBox_1.getSelectedItem().toString();
			int flag = searchLicense(empLicense);
			if(flag==0) {
			try {
				pst = con.prepareStatement("insert into employee(firstname,lastname,dob,gender,phoneNo,city,state,licenseNo,role) "
						+ "values(?,?,?,?,?,?,?,?,?)");
				pst.setString(1, empName);
				pst.setString(2, emplname);
				pst.setString(3, empDob);
				pst.setString(4, empGender);
				pst.setString(5, empPhone);
				pst.setString(6, empCity);
				pst.setString(7, empState);
				pst.setString(8, empLicense);
				pst.setString(9, empRole);
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Employee Added");
			viewData();
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_7.setText("");
			dateChooser.setCalendar(null);
			} else {
				int result = JOptionPane.showConfirmDialog(this, "Employee already exists. Do you want to Update its details?","Employee",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	try {
								pst = con.prepareStatement("update employee set firstname = ?, lastname = ?, dob = ?, gender = ?, phoneNo = ?, city = ?, state = ?, role = ? where licenseNo = ?");
								pst.setString(1, empName);
								pst.setString(2, emplname);
								pst.setString(3, empDob);
								pst.setString(4, empGender);
								pst.setString(5, empPhone);
								pst.setString(6, empCity);
								pst.setString(7, empState);
								pst.setString(8, empRole);
								pst.setString(9, empLicense);
								pst.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			            	textField.setText("");
			    			textField_1.setText("");
			    			textField_2.setText("");
			    			textField_3.setText("");
			    			textField_4.setText("");
			    			dateChooser.setCalendar(null);
			    			textField_7.setText("");
			    			viewData();
			            	JOptionPane.showMessageDialog(this, "Employee Details Updated");
			            }else if (result == JOptionPane.NO_OPTION){
			            	textField.setText("");
			    			textField_1.setText("");
			    			textField_2.setText("");
			    			textField_3.setText("");
			    			textField_4.setText("");
			    			dateChooser.setCalendar(null);
			    			textField_7.setText("");
			            }else {
			            	textField.setText("");
			    			textField_1.setText("");
			    			textField_2.setText("");
			    			textField_3.setText("");
			    			textField_4.setText("");
			    			textField_7.setText("");
			    			dateChooser.setCalendar(null);
			            }
			            flag=0;
			}
			textField_5.setText("");
			textField_6.setText("");
			
		} else if(e.getSource() == btnNewButton_2) {
			viewData();
			textField_5.setText("");
			textField_6.setText("");
			textField.setText("");
			dateChooser.setCalendar(null);
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_7.setText("");
		}
		else if(e.getSource() == btnNewButton_1)
			this.setVisible(false);
		else if(e.getSource() == btnNewButton_3) {
			int found=0;
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_7.setText("");
			dateChooser.setCalendar(null);
			String fname = textField_5.getText();
			String lname = textField_6.getText();
			try {
				pst = con.prepareStatement("Select firstname,lastname,dob,gender,phoneNo,city,state,licenseNo,role from employee where firstname = ? and lastname = ?");
				pst.setString(1, fname);
				pst.setString(2, lname);
				rs = pst.executeQuery();
				ResultSetMetaData rsd = rs.getMetaData();
				int c;
				c = rsd.getColumnCount();
				DefaultTableModel d = (DefaultTableModel)table_1.getModel();
				d.setRowCount(0);
				while(rs.next()) {
					found=1;
					Vector v1 = new Vector();
					for(int i=1; i<=c; i++) {
						v1.add(rs.getString("firstname"));
						v1.add(rs.getString("lastname"));
						v1.add(rs.getString("dob"));
						v1.add(rs.getString("gender"));
						v1.add(rs.getString("phoneNo"));
						v1.add(rs.getString("city"));
						v1.add(rs.getString("state"));
						v1.add(rs.getString("licenseNo"));
						v1.add(rs.getString("role"));
					}
					d.addRow(v1);
				}
				if(found==0)
					JOptionPane.showMessageDialog(this, "Employee Not Found!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selected = table_1.getSelectedRow();
		fname = table_1.getValueAt(selected, 0).toString();
		lname = table_1.getValueAt(selected, 1).toString();
		dob = table_1.getValueAt(selected, 2).toString();
		gender = table_1.getValueAt(selected, 3).toString();
		phno = table_1.getValueAt(selected, 4).toString();
		city = table_1.getValueAt(selected, 5).toString();
		state = table_1.getValueAt(selected, 6).toString();
		lic = table_1.getValueAt(selected, 7).toString();
		role = table_1.getValueAt(selected, 8).toString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date bdate=null;
		try {
				bdate = format.parse(dob);
		} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		dateChooser.setDate(bdate);
		textField.setText(fname);
		textField_1.setText(phno);
		textField_2.setText(city);
		textField_3.setText(state);
		textField_4.setText(lic);
		textField_7.setText(lname);
		comboBox.setSelectedItem(gender);
		comboBox_1.setSelectedItem(role);
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
