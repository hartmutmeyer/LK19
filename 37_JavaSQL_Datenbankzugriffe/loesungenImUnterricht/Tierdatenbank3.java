import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Tierdatenbank3 extends JFrame {

	private JPanel contentPane;
	private JTextField tfTierID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tierdatenbank3 frame = new Tierdatenbank3();
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
	public Tierdatenbank3() {
		createGUI();
	}

	private void createGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 96);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTierID = new JLabel("Tiernummer:");
		lblTierID.setBounds(10, 11, 93, 14);
		contentPane.add(lblTierID);

		tfTierID = new JTextField();
		tfTierID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tierLoeschen();
			}
		});
		tfTierID.setBounds(113, 8, 86, 20);
		contentPane.add(tfTierID);
		tfTierID.setColumns(10);

		JButton btnTierLoeschen = new JButton("Tier Löschen");
		btnTierLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tierLoeschen();
			}
		});
		btnTierLoeschen.setBounds(228, 7, 123, 23);
		contentPane.add(btnTierLoeschen);
	}

	protected void tierLoeschen() {
		String tierID = tfTierID.getText();
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/haustier" + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
				"root", "root"); Statement stmt = conn.createStatement()) {
			String sql = "DELETE FROM tier WHERE tier_id = " + tierID;
			System.out.println("tierLoeschen(): " + sql);
			int ergebnis = stmt.executeUpdate(sql);
			if (ergebnis == 1) {
				System.out.println("ERFOLG");
			} else {
				System.out.println("Das Tier gibt es nicht");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
