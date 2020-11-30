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
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Route extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table_1;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Route frame = new Route();
					//frame.setSize(1920, 1080);
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
	public Route() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 480, 1080);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image busImg = new ImageIcon(this.getClass().getResource("/route.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(busImg));
		lblNewLabel.setBounds(146, 32, 167, 96);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Route Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(165, 131, 200, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Source : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(31, 260, 112, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Destination : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(31, 306, 112, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Distance from Source : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(31, 351, 262, 25);
		panel.add(lblNewLabel_5);
		

		JLabel lblNewLabel_6 = new JLabel("(in km)");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(29, 371, 70, 25);
		panel.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 256, 220, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(227, 306, 220, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(227, 356, 220, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("Done");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(67, 499, 130, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(268, 499, 130, 60);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2 = new JButton("View");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBackground(new Color(0, 139, 139));
		btnNewButton_2.setBounds(360, 11, 89, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 23, 849, 700);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Route ID", "Source", "Destination", "Distance from Source(km)"
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
			pst = con.prepareStatement("Select id,source,destination,distance from route");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel)table_1.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v1 = new Vector();
				for(int i=1; i<=c; i++) {
					v1.add(rs.getString("id"));
					v1.add(rs.getString("source"));
					v1.add(rs.getString("destination"));
					v1.add(rs.getString("distance"));
				}
				d.addRow(v1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			String source = textField_1.getText();
			String destination = textField_2.getText();
			String distance = textField_3.getText();
			try {
				pst = con.prepareStatement("insert into route(source,destination,distance) "
						+ "values(?,?,?)");
				pst.setString(1, source);
				pst.setString(2, destination);
				pst.setString(3, distance);
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Route Added");
			viewData();
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			
		} else if(e.getSource() == btnNewButton_2)
			viewData();
		else if(e.getSource() == btnNewButton_1)
			this.setVisible(false);
	}
}
