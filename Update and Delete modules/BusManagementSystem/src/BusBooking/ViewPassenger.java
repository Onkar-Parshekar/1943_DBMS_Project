package BusBooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewPassenger extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_1;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPassenger frame = new ViewPassenger();
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
	public ViewPassenger() {
		Connect();
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(27,28,29));
		panel.setBounds(0, 0, 480, 1080);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image busImg = new ImageIcon(this.getClass().getResource("/viewp.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(busImg));
		lblNewLabel.setBounds(146, 32, 167, 96);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passenger Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(131, 139, 228, 50);
		panel.add(lblNewLabel_1);
		
		btnNewButton_2 = new JButton("View");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBackground(new Color(0, 139, 139));
		btnNewButton_2.setBounds(360, 11, 89, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JLabel lblNewLabel_3 = new JLabel("First Name : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(31, 260, 112, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(31, 306, 112, 14);
		panel.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 256, 220, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(227, 306, 220, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("Search");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(67, 399, 130, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(268, 399, 130, 60);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 23, 849, 700);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr.No", "Firstname", "LastName", "Gender", "Age", "Contact No", "Bus Number", "Date"
			}
		));
		scrollPane.setViewportView(table_1);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
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
			pst = con.prepareStatement("Select custNo,firstName,lastName,custGender,custAge,custPhone,busNo,date from passenger");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel)table_1.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v1 = new Vector();
				for(int i=1; i<=c; i++) {
					v1.add(rs.getString("custNo"));
					v1.add(rs.getString("firstName"));
					v1.add(rs.getString("lastName"));
					v1.add(rs.getString("custGender"));
					v1.add(rs.getString("custAge"));
					v1.add(rs.getString("custPhone"));
					v1.add(rs.getString("busNo"));
					v1.add(rs.getString("date"));
				}
				d.addRow(v1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton_2) {
			viewData();
		} else if(e.getSource() == btnNewButton_1) {
			this.setVisible(false);
		} else if(e.getSource() == btnNewButton) {
			int found=0;
			String fname = textField_1.getText();
			String lname = textField_2.getText();
			try {
				pst = con.prepareStatement("Select custNo,firstName,lastName,custGender,custAge,custPhone,busNo,"
						+ "date from passenger where firstName = ? and lastName = ?");
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
						v1.add(rs.getString("custNo"));
						v1.add(rs.getString("firstName"));
						v1.add(rs.getString("lastName"));
						v1.add(rs.getString("custGender"));
						v1.add(rs.getString("custAge"));
						v1.add(rs.getString("custPhone"));
						v1.add(rs.getString("busNo"));
						v1.add(rs.getString("date"));
					}
					d.addRow(v1);
				}
				if(found==0)
					JOptionPane.showMessageDialog(this, "Passenger Not Found!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			textField_1.setText("");
			textField_2.setText("");
		}
	}

}
