package BusBooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class SignUp extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton2;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		textField_1.setBounds(614, 229, 275, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		textField_2.setBounds(614, 313, 275, 30);
		contentPane.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		passwordField.setBounds(614, 397, 275, 30);
		contentPane.add(passwordField);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		textField_4.setBounds(614, 487, 275, 30);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_5.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		textField_5.setBounds(614, 572, 275, 30);
		contentPane.add(textField_5);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(1133, 31, 201, 70);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton2 = new JButton("");
		btnNewButton2.setBounds(30, 31, 201, 70);
		btnNewButton2.setOpaque(false);
		btnNewButton2.setContentAreaFilled(false);
		btnNewButton2.setBorderPainted(false);
		contentPane.add(btnNewButton2);
		btnNewButton2.addActionListener(this);
		
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("Images\\signupui.jpg"));
		img.setBounds(0, 0, 1372, 751);
		contentPane.add(img);
		
	}
	
	public boolean checkUsername(String username) {
		PreparedStatement ps;
	    ResultSet rs;
	    boolean checkUser = false;
	    try {
	    		ps = con.prepareStatement("SELECT * FROM admin WHERE adminUser = ?");
	            ps.setString(1, username);      
	            rs = ps.executeQuery(); 
	            if(rs.next())
	            {
	                checkUser = true;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
	        }
	         return checkUser;
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
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
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton2) {
			this.setVisible(false);
		} else if(e.getSource() == btnNewButton) {
			String name = textField_1.getText();
			String username = textField_2.getText();
			String pass = String.valueOf(passwordField.getPassword());
			String phone = textField_4.getText();
			String address = textField_5.getText();
			if(username.equals(""))	{
	            JOptionPane.showMessageDialog(null, "Username cannot be empty");
	        } else if(name.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Name cannot be empty");
	        } else if(checkUsername(username))	{
	            JOptionPane.showMessageDialog(null, "Username Already Exist");
	        } else if(phone.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Contact Number cannot be empty");
	        } else if(address.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Address cannot be empty");
	        } else if(pass.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Password cannot be empty");
	        } else {
	        	try {
					pst = con.prepareStatement("insert into admin(name,adminUser,password,phone,address) "
							+ "values(?,?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, username);
					pst.setString(3, pass);
					pst.setString(4, phone);
					pst.setString(5, address);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	JOptionPane.showMessageDialog(this, "Admin Registered. Please Log In to continue");
				this.setVisible(false);
	        }
		}
	}

}
