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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Passenger extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultTableModel d2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JComboBox comboBox_1;
	private JComboBox comboBox_1_1;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private int selected;
	private String getseatnumber, getbusnumber, getdepartureDate;
	//private DefaultTableModel d;
	DefaultTableModel d1 = new DefaultTableModel(0,0);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passenger frame = new Passenger();
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
	public Passenger() {
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(500, 0, 849, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Date : ");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(10, 32, 48, 50);
		panel_1.add(lblNewLabel_7);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(68, 48, 121, 20);
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_7_1 = new JLabel("Source : ");
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7_1.setBounds(224, 32, 87, 50);
		panel_1.add(lblNewLabel_7_1);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(298, 48, 200, 22);
		panel_1.add(comboBox_1);
		getSource();
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Destination : ");
		lblNewLabel_7_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7_1_1.setBounds(537, 32, 130, 50);
		panel_1.add(lblNewLabel_7_1_1);
		
		comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(639, 48, 200, 22);
		panel_1.add(comboBox_1_1);
		getDestination();
		
		btnNewButton_2 = new JButton("Show");
		btnNewButton_2.setBounds(750, 83, 89, 23);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("");
		Image busImg = new ImageIcon(this.getClass().getResource("/passengerbooking.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(busImg));
		lblNewLabel.setBounds(146, 32, 167, 96);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bus Booking");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(155, 131, 300, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Name : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(31, 216, 123, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(31, 260, 112, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(31, 306, 112, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Age : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(31, 352, 112, 25);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Contact No : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(31, 398, 123, 25);
		panel.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(227, 216, 222, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(227, 260, 222, 20);
		panel.add(textField_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Rather Not Say"}));
		comboBox.setBounds(227, 305, 105, 22);
		panel.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(227, 357, 222, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(227, 403, 222, 20);
		panel.add(textField_3);
		
		btnNewButton = new JButton("Book");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(78, 629, 130, 60);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(263, 629, 130, 60);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(227, 452, 222, 20);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(227, 499, 222, 20);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(227, 547, 222, 20);
		panel.add(textField_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Bus Number : ");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6_1.setBounds(31, 447, 123, 25);
		panel.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("Seat No : ");
		lblNewLabel_6_2.setForeground(Color.WHITE);
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6_2.setBounds(31, 494, 123, 25);
		panel.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_6_3 = new JLabel("Date : ");
		lblNewLabel_6_3.setForeground(Color.WHITE);
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6_3.setBounds(31, 542, 123, 25);
		panel.add(lblNewLabel_6_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 115, 849, 604);
		contentPane.add(scrollPane);
		//scrollPane.addMouseListener(this);
		
		table_1 = new JTable();
		String header[] = new String[] {
				"Bus Number", "Seat No", "Status", "Passenger", "Gender", "Departure Time", "Arrival Time", "Departure Date", "Arrival Date", "Price"
		};
		d1.setColumnIdentifiers(header);
		table_1.setModel(d1);
		/*table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bus Number", "Seat No", "Passenger", "Gender", "Departure Time", "Arrival Time", "Departure Date", "Arrival Date", "Price"
			}
		));*/
		scrollPane.setViewportView(table_1);
		table_1.addMouseListener(this);
	}
	
	Connection con;
	PreparedStatement pst, pst2, pst3;
	ResultSet rs,rs1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
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
	
	public String getPrice(String busNumber) {
		String ticketPrice="";
		try {
			pst2 = con.prepareStatement("Select distinct price from schedule where busNo = '" +busNumber+"'");
			rs = pst2.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=c; i++) {
					ticketPrice = rs.getString("price");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticketPrice;
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
					comboBox_1_1.addItem(destLoc);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewData() {
		SimpleDateFormat Df = new SimpleDateFormat("yyy-MM-dd");
		String date = Df.format(dateChooser.getDate());
		String source = comboBox_1.getSelectedItem().toString();
		String destination = comboBox_1_1.getSelectedItem().toString();
		try {
			pst = con.prepareStatement("Select schedule.busNo,schedule.seats,passenger.firstName,passenger.custGender,"
					+ "schedule.departureDate,schedule.arrival,schedule.departure,schedule.arrivalDate,schedule.seats,schedule.status,schedule.price from schedule Left JOIN "
					+ "passenger ON schedule.busNo = passenger.busNo AND schedule.seats = passenger.seatNo AND schedule.departureDate = passenger.date  where "
					+ "departureDate = ? and source = ? and destination = ?");
			pst.setString(1, date);
			pst.setString(2, source);
			pst.setString(3, destination);
			rs = pst.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			d1 = (DefaultTableModel)table_1.getModel();
			d1.setRowCount(0);
			int c;
			c = rsd.getColumnCount();
			while(rs.next()) {
				Vector v1 = new Vector();
				for(int i=1; i<=c; i++) {
					v1.add(rs.getString("busNo"));
					v1.add(rs.getString("seats"));
					v1.add(rs.getString("status"));
					v1.add(rs.getString("firstName"));
					v1.add(rs.getString("custGender"));
					v1.add(rs.getString("departure"));
					v1.add(rs.getString("arrival"));
					v1.add(rs.getString("departureDate"));
					v1.add(rs.getString("arrivalDate"));
					v1.add(rs.getString("price"));
				}
				d1.addRow(v1);
				/*d1.addRow(new Object[] {
						"","","","","","","",""
				});*/
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton_2) {
			viewData();
		} else if(e.getSource() == btnNewButton) {
			String fname = textField.getText();
			String lname = textField_1.getText();
			String pgender = comboBox.getSelectedItem().toString();
			String age = textField_2.getText();
			String pr = getPrice(getbusnumber);
			int tickPrice = Integer.parseInt(pr);
			String contact = textField_3.getText();
			try {
				pst3 = con.prepareStatement("insert into passenger(firstName,lastName,custGender,custAge,custPhone,busNo,seatNo,date,price) "
						+ "values(?,?,?,?,?,?,?,?,?)");
				pst3.setString(1, fname);
				pst3.setString(2, lname);
				pst3.setString(3, pgender);
				pst3.setString(4, age);
				pst3.setString(5, contact);
				pst3.setString(6, getbusnumber);
				pst3.setString(7, getseatnumber);
				pst3.setString(8, getdepartureDate);
				pst3.setInt(9, tickPrice);
				pst3.executeUpdate();
				
				pst = con.prepareStatement("update schedule set status = ? where seats = ? and departureDate = ? and busNo = ?");
				pst.setString(1, "Booked");
				pst.setString(2, getseatnumber);
				pst.setString(3, getdepartureDate);
				pst.setString(4, getbusnumber);
				pst.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Passenger Seat Booked");
			viewData();
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");
			textField_6.setText("");
		} else if(e.getSource() == btnNewButton_1) {
			this.setVisible(false);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		d1 = (DefaultTableModel)table_1.getModel();
		selected = table_1.getSelectedRow();
		String status = d1.getValueAt(selected, 2).toString();
		if(!status.equals("Booked")) {
			getseatnumber = d1.getValueAt(selected, 1).toString();
			getbusnumber = d1.getValueAt(selected, 0).toString();
			getdepartureDate = d1.getValueAt(selected, 7).toString();
			textField_4.setText(getbusnumber);
			textField_5.setText(getseatnumber);
			textField_6.setText(getdepartureDate);
		} else {
			JOptionPane.showMessageDialog(this, "Seat already Booked");
		}
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
