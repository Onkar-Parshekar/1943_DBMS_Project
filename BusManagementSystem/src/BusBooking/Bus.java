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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Bus extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
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
					Bus frame = new Bus();
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
	public Bus() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 480, 1080);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		Image busImg = new ImageIcon(this.getClass().getResource("/bus.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(busImg));
		lblNewLabel.setBounds(146, 32, 167, 96);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bus Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(165, 131, 200, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bus Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(31, 216, 123, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Bus Name : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(31, 260, 112, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("No of Seats : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(31, 306, 112, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Bus Type : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(31, 350, 112, 25);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Date of Entry : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(31, 396, 123, 25);
		panel.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(227, 216, 220, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 256, 220, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(227, 306, 220, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A/C Sleeper", "A/C Seater", "Non A/C Sleeper", "Non A/C Seater", "Non A/C Seater/Sleeper", "A/C Seater/Sleeper"}));
		comboBox.setBounds(227, 350, 117, 22);
		panel.add(comboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(227, 401, 220, 20);
		panel.add(dateChooser);
		
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
				"Bus Number", "Bus Name", "Number of Seats", "Bus Type", "Date of Entry"
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
			pst = con.prepareStatement("Select busNo,busName,NoOfSeats,busType,entryDate from buses");
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel)table_1.getModel();
			d.setRowCount(0);
			while(rs.next()) {
				Vector v1 = new Vector();
				for(int i=1; i<=c; i++) {
					v1.add(rs.getString("busNo"));
					v1.add(rs.getString("busName"));
					v1.add(rs.getString("NoOfSeats"));
					v1.add(rs.getString("busType"));
					v1.add(rs.getString("entryDate"));
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
			String BusNo = textField.getText();
			String BusName = textField_1.getText();
			String NumberOfSeats = textField_2.getText();
			String BusType = comboBox.getSelectedItem().toString();
			SimpleDateFormat Df = new SimpleDateFormat("yyy-MM-dd");
			String EntryDate = Df.format(dateChooser.getDate());
			
			
			try {
				pst = con.prepareStatement("insert into buses(busNo,busName,NoOfSeats,busType,entryDate) "
						+ "values(?,?,?,?,?)");
				pst.setString(1, BusNo);
				pst.setString(2, BusName);
				pst.setString(3, NumberOfSeats);
				pst.setString(4, BusType);
				pst.setString(5, EntryDate);
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Bus Added");
			viewData();
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			
		} else if(e.getSource() == btnNewButton_2)
			viewData();
		else if(e.getSource() == btnNewButton_1)
			this.setVisible(false);
	}
}
