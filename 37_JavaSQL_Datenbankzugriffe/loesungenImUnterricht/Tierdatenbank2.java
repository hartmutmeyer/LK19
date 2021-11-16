import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Tierdatenbank2 extends JFrame {
	private static final int WIDTH = 450;
	private static final int HEIGHT = 200;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfTierart;
	private JTextField tfGeschlecht;
	private JTextField tfGeburtstag;
	private JTextField tfTodestag;
	private JButton btnVor;
	private JButton btnZurueck;
	private int maxTierId, tierId = 1;
	private Statement stmt;
	private ResultSet rs;

	public Tierdatenbank2() {
		createGUI();
		dbConnectAndQuery();
		vor();
	}

	private void dbConnectAndQuery() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/haustier" + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
					"root", "root");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM tier ORDER BY tier_id";
			System.out.println("erstenDatensatzAnzeigen(): " + sql);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void datensatzAnzeigen() {
		try {
			tfId.setText(rs.getString("tier_id"));
			tfName.setText(rs.getString("name"));
			tfTierart.setText(rs.getString("tierart"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void vor() {
		try {
			if (rs.next()) {
				datensatzAnzeigen();
				btnZurueck.setEnabled(true);
			} 
			if (rs.isFirst()) {
				btnZurueck.setEnabled(false);
			}
			if (rs.isLast()) {
				btnVor.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void zurueck() {
		try {
			if (rs.previous()) {
				//System.out.println("Nächsten Datensatz gefunden");
				datensatzAnzeigen();
				btnVor.setEnabled(true);
			} 
			if (rs.isFirst()) {
				btnZurueck.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createGUI() {
		setTitle("Haustier-Datenbank: Aufgabe 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 450, 214);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("id:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(54, 14, 27, 15);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setHorizontalAlignment(SwingConstants.RIGHT);
		tfId.setBounds(89, 12, 114, 19);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(253, 14, 44, 15);
		contentPane.add(lblName);

		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setHorizontalAlignment(SwingConstants.RIGHT);
		tfName.setBounds(302, 12, 114, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);

		JLabel lblTierart = new JLabel("Tierart:");
		lblTierart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTierart.setBounds(26, 39, 55, 15);
		contentPane.add(lblTierart);

		tfTierart = new JTextField();
		tfTierart.setEditable(false);
		tfTierart.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTierart.setBounds(89, 37, 114, 19);
		contentPane.add(tfTierart);
		tfTierart.setColumns(10);

		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		lblGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeschlecht.setBounds(218, 39, 79, 15);
		contentPane.add(lblGeschlecht);

		tfGeschlecht = new JTextField();
		tfGeschlecht.setEditable(false);
		tfGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGeschlecht.setBounds(302, 37, 114, 19);
		contentPane.add(tfGeschlecht);
		tfGeschlecht.setColumns(10);

		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		lblGeburtstag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeburtstag.setBounds(2, 66, 79, 15);
		contentPane.add(lblGeburtstag);

		tfGeburtstag = new JTextField();
		tfGeburtstag.setEditable(false);
		tfGeburtstag.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGeburtstag.setBounds(89, 64, 114, 19);
		contentPane.add(tfGeburtstag);
		tfGeburtstag.setColumns(10);

		JLabel lblTodestag = new JLabel("Todestag:");
		lblTodestag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTodestag.setBounds(218, 66, 79, 15);
		contentPane.add(lblTodestag);

		tfTodestag = new JTextField();
		tfTodestag.setEditable(false);
		tfTodestag.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTodestag.setBounds(302, 64, 114, 19);
		contentPane.add(tfTodestag);
		tfTodestag.setColumns(10);

		btnVor = new JButton("vor");
		btnVor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vor();
			}
		});
		btnVor.setBounds(105, 136, 98, 25);
		contentPane.add(btnVor);

		btnZurueck = new JButton("zurück");
		btnZurueck.setEnabled(false);
		btnZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zurueck();
			}
		});
		btnZurueck.setBounds(231, 136, 98, 25);
		contentPane.add(btnZurueck);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Tierdatenbank2 frame = new Tierdatenbank2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
