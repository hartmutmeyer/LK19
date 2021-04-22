import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.awt.event.ActionEvent;

public class CaesarHalbgruppeB extends JFrame {

	private JPanel contentPane;
	private JTextField tfKlartextDatei;
	private JTextField tfVerschluesselteDatei;
	private JTextField tfSchluessel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaesarHalbgruppeB frame = new CaesarHalbgruppeB();
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
	public CaesarHalbgruppeB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblKlartextDatei = new JLabel("Klartext-Datei:");
		lblKlartextDatei.setBounds(10, 11, 137, 14);
		contentPane.add(lblKlartextDatei);

		JLabel lblVerschluesselteDatei = new JLabel("Verschluesselte Datei:");
		lblVerschluesselteDatei.setBounds(10, 46, 137, 14);
		contentPane.add(lblVerschluesselteDatei);

		JLabel lblSchluessel = new JLabel("Schluessel:");
		lblSchluessel.setBounds(10, 84, 137, 14);
		contentPane.add(lblSchluessel);

		tfKlartextDatei = new JTextField();
		tfKlartextDatei.setText("klartext.txt");
		tfKlartextDatei.setBounds(157, 8, 328, 20);
		contentPane.add(tfKlartextDatei);
		tfKlartextDatei.setColumns(10);

		tfVerschluesselteDatei = new JTextField();
		tfVerschluesselteDatei.setText("verschluesselt.txt");
		tfVerschluesselteDatei.setBounds(157, 43, 328, 20);
		contentPane.add(tfVerschluesselteDatei);
		tfVerschluesselteDatei.setColumns(10);

		tfSchluessel = new JTextField();
		tfSchluessel.setBounds(157, 81, 129, 20);
		contentPane.add(tfSchluessel);
		tfSchluessel.setColumns(10);

		JButton btnVerschluesseln = new JButton("Verschluesseln");
		btnVerschluesseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verschluesseln();
			}
		});
		btnVerschluesseln.setBounds(505, 7, 135, 23);
		contentPane.add(btnVerschluesseln);

		JButton btnEntschluesseln = new JButton("Entschluesseln");
		btnEntschluesseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entschluesseln();
			}
		});
		btnEntschluesseln.setBounds(502, 42, 138, 23);
		contentPane.add(btnEntschluesseln);
	}

	protected void verschluesseln() {
		URL urlKlartext = getClass().getResource(tfKlartextDatei.getText()); // import java.net.URL

		URL urlVerschluesselt = getClass().getResource(tfVerschluesselteDatei.getText()); // import java.net.URL

		int schluessel = Integer.parseInt(tfSchluessel.getText());

		try (InputStream fileIn = new FileInputStream(URLDecoder.decode(urlKlartext.getFile(), "UTF-8"));
				InputStreamReader in = new InputStreamReader(fileIn, "UTF-8");
				OutputStream fileOut = new FileOutputStream(URLDecoder.decode(urlVerschluesselt.getFile(), "UTF-8"));
				OutputStreamWriter out = new OutputStreamWriter(fileOut, "UTF-8")) {
			int zeichen;
			char c;
			while ((zeichen = in.read()) != -1) {
				c = Character.toLowerCase((char) zeichen);
				System.out.print(c);
				out.write(c + schluessel);
			}
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	protected void entschluesseln() {
		URL urlKlartext = getClass().getResource(tfKlartextDatei.getText()); // import java.net.URL

		URL urlVerschluesselt = getClass().getResource(tfVerschluesselteDatei.getText()); // import java.net.URL

		int schluessel = Integer.parseInt(tfSchluessel.getText());

		try (InputStream fileIn = new FileInputStream(URLDecoder.decode(urlVerschluesselt.getFile(), "UTF-8"));
				InputStreamReader in = new InputStreamReader(fileIn, "UTF-8");
				OutputStream fileOut = new FileOutputStream(URLDecoder.decode(urlKlartext.getFile(), "UTF-8"));
				OutputStreamWriter out = new OutputStreamWriter(fileOut, "UTF-8")) {
			int zeichen;
			char c;
			while ((zeichen = in.read()) != -1) {
				c = Character.toLowerCase((char) zeichen);
				System.out.print(c);
				out.write(c - schluessel);
			}
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
