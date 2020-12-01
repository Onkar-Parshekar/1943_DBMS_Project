package BusBooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class HomePage extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setUndecorated(true);
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
	public HomePage() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		textField.setBounds(614, 342, 335, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		passwordField.setBounds(614, 425, 335, 33);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(578, 525, 199, 67);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(1165, 29, 180, 66);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(34, 29, 49, 49);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);

		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("Images\\loginui.jpg"));
		img.setBounds(0, 0, 1372, 751);
		contentPane.add(img);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnNewButton_1;
	private JPasswordField passwordField;
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
		if(e.getSource() == btnNewButton_1) {
			SignUp s = new SignUp();
			s.setExtendedState(JFrame.MAXIMIZED_BOTH);
			s.setUndecorated(true);
			s.setVisible(true);
		} else if(e.getSource() == btnNewButton) {
			String username = textField.getText();
			String pass = String.valueOf(passwordField.getPassword());
			if(username.equals(""))	{
	            JOptionPane.showMessageDialog(null, "Username cannot be empty");
	        } else if(pass.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Password cannot be empty");
	        } else {
	        	try {
					pst = con.prepareStatement("SELECT * FROM admin WHERE adminUser = ? and password = ?");
					pst.setString(1, username);  
					pst.setString(2, pass);
		            rs = pst.executeQuery(); 
		            if(rs.next())
		            {
		            	textField.setText("");
		            	passwordField.setText("");
		            	Dashboard d = new Dashboard();
		    			d.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    			d.setUndecorated(true);
		    			d.setVisible(true);
		            } else {
		            	JOptionPane.showMessageDialog(this, "Invalid Username and Password");
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		} else if(e.getSource() == btnNewButton_2) {
			this.setVisible(false);
		}
	}
}
