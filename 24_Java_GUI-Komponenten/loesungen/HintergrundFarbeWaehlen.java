import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HintergrundFarbeWaehlen extends JFrame {

	private JPanel contentPane;
	private JTextField tfRot;
	private JTextField tfGruen;
	private JTextField tfBlau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HintergrundFarbeWaehlen frame = new HintergrundFarbeWaehlen();
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
	public HintergrundFarbeWaehlen() {
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 124);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRot = new JLabel("Rot");
		lblRot.setBounds(10, 11, 46, 14);
		contentPane.add(lblRot);
		
		tfRot = new JTextField();
		tfRot.setBounds(66, 8, 86, 20);
		contentPane.add(tfRot);
		tfRot.setColumns(10);
		
		JLabel lblGruen = new JLabel("Grün");
		lblGruen.setBounds(183, 11, 46, 14);
		contentPane.add(lblGruen);
		
		tfGruen = new JTextField();
		tfGruen.setBounds(239, 8, 86, 20);
		contentPane.add(tfGruen);
		tfGruen.setColumns(10);
		
		JLabel lblBlau = new JLabel("Blau");
		lblBlau.setBounds(367, 11, 46, 14);
		contentPane.add(lblBlau);
		
		tfBlau = new JTextField();
		tfBlau.setBounds(423, 8, 86, 20);
		contentPane.add(tfBlau);
		tfBlau.setColumns(10);
		
		JButton btnFarbenMischen = new JButton("Mix It Baby!");
		btnFarbenMischen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mixItBaby();
			}
		});
		btnFarbenMischen.setBounds(208, 47, 140, 23);
		contentPane.add(btnFarbenMischen);	
	}

	protected void mixItBaby() {
		int r = Integer.parseInt(tfRot.getText());
		int g = Integer.parseInt(tfGruen.getText());
		int b = Integer.parseInt(tfBlau.getText());
		contentPane.setBackground(new Color(r, g, b));
	}
}
