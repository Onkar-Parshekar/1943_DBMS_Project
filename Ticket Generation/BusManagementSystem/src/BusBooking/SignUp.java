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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class SignUp extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton2;

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
		
		textField = new JTextField();
		textField.setBounds(321, 222, 275, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(321, 285, 275, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(321, 353, 275, 30);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(321, 417, 275, 30);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(321, 484, 275, 30);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(321, 551, 275, 30);
		contentPane.add(textField_5);
		
		btnNewButton = new JButton("SIGN UP");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(840, 593, 163, 60);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton2 = new JButton("Cancel");
		btnNewButton2.setForeground(Color.WHITE);
		btnNewButton2.setBackground(Color.ORANGE);
		btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton2.setBounds(1040, 593, 163, 60);
		contentPane.add(btnNewButton2);
		btnNewButton2.addActionListener(this);
		
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("Images\\signup.jpg"));
		img.setBounds(0, 0, 1372, 751);
		contentPane.add(img);
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
			this.setVisible(false);
		}
	}

}
