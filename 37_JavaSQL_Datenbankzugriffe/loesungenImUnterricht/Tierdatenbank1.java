import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Tierdatenbank1 extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> tiere = new DefaultListModel<String>();
	private JList<String> listTiere = new JList<String>(tiere);
	private Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Tierdatenbank1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tierdatenbank1() {
		createGUI();
		try {
			dbConnect();
			listeDerTiereAnzeigen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Fehler beim Datenbankzugriff");
		}
	}

	private void dbConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/haustier" + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
				"root", "root");
		stmt = conn.createStatement();
		System.out.println("Datenbankverbindung ergestellt");
	}

	private void listeDerTiereAnzeigen() throws SQLException {
		String sql = "SELECT * FROM tier, besitzer, beziehung " 
				+ "WHERE tier_id = beziehung_tier_id "
				+ "AND besitzer_id = beziehung_besitzer_id "
				+ "AND lebendig = 'ja' "
				+ "ORDER BY nachname";

		System.out.println("listeDerTiereAnzeigen(): " + sql);
		ResultSet rs = stmt.executeQuery(sql);
		tiere.addElement("TEST");
		while (rs.next()) {
			System.out.println(rs.getString("nachname") + ", " + rs.getString("vorname") + ": " + rs.getString("name")
			+ ", " + rs.getString("tierart") + ", " + rs.getString("geschlecht"));
			tiere.addElement(rs.getString("nachname") + ", " + rs.getString("vorname") + ": " + rs.getString("name")
			+ ", " + rs.getString("tierart") + ", " + rs.getString("geschlecht"));
		}
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Liste aller lebendigen Tiere und ihrer Besitzer:");
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 414, 216);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(listTiere);
		setVisible(true);
	}
}
