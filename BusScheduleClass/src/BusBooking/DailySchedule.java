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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public class DailySchedule extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailySchedule frame = new DailySchedule();
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
	public DailySchedule() {
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
		Image busImg = new ImageIcon(this.getClass().getResource("/schedule.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(busImg));
		lblNewLabel.setBounds(146, 32, 167, 96);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bus Schedule Details");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(115, 131, 300, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bus Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(31, 216, 123, 14);
		panel.add(lblNewLabel_2);
		
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
		
		JLabel lblNewLabel_5 = new JLabel("Driver : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(31, 352, 112, 25);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Conductor : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(31, 398, 123, 25);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Departure Time : ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(31, 444, 193, 25);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Arrival Time : ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(31, 490, 193, 25);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Departure Date : ");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(31, 536, 193, 25);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Arrival Date : ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(31, 582, 193, 25);
		panel.add(lblNewLabel_10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(221, 215, 208, 22);
		panel.add(comboBox);
		getBusNo();
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(221, 259, 208, 22);
		panel.add(comboBox_1);
		getSource();
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(221, 305, 208, 22);
		panel.add(comboBox_2);
		getDestination();
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(221, 356, 208, 22);
		panel.add(comboBox_3);
		getDriver();
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(221, 402, 208, 22);
		panel.add(comboBox_4);
		getConductor();
		
		btnNewButton = new JButton("Done");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(83, 634, 130, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(263, 634, 130, 60);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("View");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBackground(new Color(0, 139, 139));
		btnNewButton_2.setBounds(360, 11, 89, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(221, 541, 208, 20);
		panel.add(dateChooser);
		
		textField = new JTextField();
		textField.setBounds(221, 449, 208, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 495, 208, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(221, 587, 208, 20);
		panel.add(dateChooser_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 23, 849, 700);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bus Number", "Source", "Destination", "Driver", "Conductor", "Departure Date", "Departure Time", "Arrival Time", "Arrival Date"
			}
		));
		scrollPane.setViewportView(table_1);
		
	}
	
	Connection con;
	PreparedStatement pst, pst2;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;
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
			pst = con.prepareStatement("Select busNo,source,destination,driver,conductor,departureDate,arrival,departure,arrivalDate from schedule");
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
					v1.add(rs.getString("source"));
					v1.add(rs.getString("destination"));
					v1.add(rs.getString("driver"));
					v1.add(rs.getString("conductor"));
					v1.add(rs.getString("departureDate"));
					v1.add(rs.getString("departure"));
					v1.add(rs.getString("arrival"));
					v1.add(rs.getString("arrivalDate"));
				}
				d.addRow(v1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/*public String getSeats(String busNumber) {
		String seats="";
		try {
			pst2 = con.prepareStatement("Select NoOfSeats from buses where busNo = '" +busNumber+"'");
			rs = pst2.executeQuery();
			rs.next();
			seats = rs.getString("NoOfSeats");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seats;
	}*/
	
	public void getBusNo() {
		String BusNumber1;
		try {
			pst2 = con.prepareStatement("Select busNo from buses");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					BusNumber1 = rs.getString("busNo");
					comboBox.addItem(BusNumber1);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getSource() {
		String sourceLoc;
		try {
			pst2 = con.prepareStatement("Select distinct source from route");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					sourceLoc = rs.getString("source");
					comboBox_1.addItem(sourceLoc);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDestination() {
		String destLoc;
		try {
			pst2 = con.prepareStatement("Select distinct destination from route");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					destLoc = rs.getString("destination");
					comboBox_2.addItem(destLoc);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDriver() {
		String driver1;
		try {
			pst2 = con.prepareStatement("Select name from employee where role = 'Driver'");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					driver1 = rs.getString("name");
					comboBox_3.addItem(driver1);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getConductor() {
		String conductor1;
		try {
			pst2 = con.prepareStatement("Select name from employee where role = 'Conductor'");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					conductor1 = rs.getString("name");
					comboBox_4.addItem(conductor1);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			String busNum = comboBox.getSelectedItem().toString();
			String source = comboBox_1.getSelectedItem().toString();
			String destination = comboBox_2.getSelectedItem().toString();
			String driver = comboBox_3.getSelectedItem().toString();
			String conductor = comboBox_4.getSelectedItem().toString();
			String departure = textField.getText();
			String arrival = textField_1.getText();
			SimpleDateFormat Df = new SimpleDateFormat("yyy-MM-dd");
			String date = Df.format(dateChooser.getDate());
			String date2 = Df.format(dateChooser_1.getDate());
			try {
				pst = con.prepareStatement("insert into schedule(busNo,source,destination,driver,conductor,departureDate,arrival,departure,arrivalDate) "
						+ "values(?,?,?,?,?,?,?,?,?)");
				pst.setString(1, busNum);
				pst.setString(2, source);
				pst.setString(3, destination);
				pst.setString(4, driver);
				pst.setString(5, conductor);
				pst.setString(6, date);
				pst.setString(7, arrival);
				pst.setString(8, departure);
				pst.setString(9, date2);
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Bus Added to Schedule");
			viewData();
			
		} else if(e.getSource() == btnNewButton_2)
			viewData();
	}
}
