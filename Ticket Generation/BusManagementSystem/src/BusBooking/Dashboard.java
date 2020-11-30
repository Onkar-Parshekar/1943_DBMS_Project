package BusBooking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Dashboard extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("");
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(30, 20, 89, 79);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(74, 181, 306, 244);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(514, 181, 306, 244);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(974, 169, 306, 256);
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(286, 461, 306, 244);
		btnNewButton_4.setOpaque(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);
		
		btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(776, 461, 306, 244);
		btnNewButton_5.setOpaque(false);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setBorderPainted(false);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.addActionListener(this);
		
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("Images\\dashboard.jpg"));
		img.setBounds(0, 0, 1372, 751);
		contentPane.add(img);
		
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
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			this.setVisible(false);
		} else if(e.getSource() == btnNewButton_1) {
			Bus b = new Bus();
			b.setExtendedState(JFrame.MAXIMIZED_BOTH);
			b.setUndecorated(true);
			b.setVisible(true);
		} else if(e.getSource() == btnNewButton_2) {
			Employee emp = new Employee();
			emp.setExtendedState(JFrame.MAXIMIZED_BOTH);
			emp.setUndecorated(true);
			emp.setVisible(true);
		} else if(e.getSource() == btnNewButton_3) {
			Route r = new Route();
			r.setExtendedState(JFrame.MAXIMIZED_BOTH);
			r.setUndecorated(true);
			r.setVisible(true);
		} else if(e.getSource() == btnNewButton_4) {
			DailySchedule ds = new DailySchedule();
			ds.setExtendedState(JFrame.MAXIMIZED_BOTH);
			ds.setUndecorated(true);
			ds.setVisible(true);
		} else if(e.getSource() == btnNewButton_5) {
			Passenger p = new Passenger();
			p.setExtendedState(JFrame.MAXIMIZED_BOTH);
			p.setUndecorated(true);
			p.setVisible(true);
		}
	}

}
