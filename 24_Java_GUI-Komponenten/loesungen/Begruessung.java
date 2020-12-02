import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Begruessung extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JLabel lblBegruessung;
	private JButton btnAuswerten;
	private JCheckBox cbWeiblich;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Begruessung frame = new Begruessung();
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
	public Begruessung() {
		createGUI();
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 46, 14);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(76, 8, 86, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		cbWeiblich = new JCheckBox("weiblich");
		cbWeiblich.setBounds(182, 7, 97, 23);
		contentPane.add(cbWeiblich);
		
		btnAuswerten = new JButton("Auswerten");
		btnAuswerten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hallo();
			}

		});
		btnAuswerten.setBounds(296, 7, 129, 23);
		contentPane.add(btnAuswerten);
		
		lblBegruessung = new JLabel("");
		lblBegruessung.setBounds(10, 53, 209, 14);
		contentPane.add(lblBegruessung);
	}

	protected void hallo() {
		if (cbWeiblich.isSelected()) {
			lblBegruessung.setText("Guten Tag Frau " + tfName.getText() + "!");
		} else {
			lblBegruessung.setText("Guten Tag Herr " + tfName.getText() + "!");
		}
	}


}
