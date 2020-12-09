package tmpQ1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StringVersuche extends JFrame {

	private JPanel contentPane;
	private JTextField tfEingabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringVersuche frame = new StringVersuche();
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
	public StringVersuche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 114);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfEingabe = new JTextField();
		tfEingabe.setBounds(10, 11, 414, 20);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		JButton btnAlleXsindBoese = new JButton("eXorzismus");
		btnAlleXsindBoese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wegMitDenHaesslichenXen();
			}
		});
		btnAlleXsindBoese.setBounds(10, 42, 414, 23);
		contentPane.add(btnAlleXsindBoese);
	}

	protected void wegMitDenHaesslichenXen() {
		// TODO Auto-generated method stub
		String eingabe = tfEingabe.getText();
		String ergebnis = "";
		for (int i = 0; i < eingabe.length(); i++) {
			char c = eingabe.charAt(i);
			if (Character.toUpperCase(c) != 'X') {
				ergebnis = ergebnis + c;
			} else {
				ergebnis += "ks";
			}
		}
		JOptionPane.showMessageDialog(this, ergebnis);
	}
}
