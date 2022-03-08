package tmpQ2.rechentrainer.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {

	private Socket s;
	private InputStreamReader in;
	private OutputStreamWriter out;
	private int zeichen;
	private char c;
	private int loesung;

	public ClientThread(Socket s) {
		this.s = s;
		try {
			in = new InputStreamReader(s.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// Name vom Client empfangen
			String name = "";
			while ((zeichen = in.read()) != '$') {
				c = (char) zeichen;
				name += c;
			}
			System.out.println("Name empfangen: " + name);

			// 5 Aufgaben stellen
			for (int i = 0; i < 5; i++) {
				aufgabeStellen();
				loesungUeberprufen();
			}
			
			// Gratulation / Kondulation
			erfolgMelden();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void erfolgMelden() throws IOException {
		out.write("%Glückwunsch, du hast alle fünf Aufgaben gelöst!$");
		out.flush();	
	}

	private void loesungUeberprufen() throws IOException {
		String antwort = "";
		int angeblicheLoesung = -1;
		while (angeblicheLoesung != loesung) {
			antwort = "";
			while ((zeichen = in.read()) != '$') {
				c = (char) zeichen;
				antwort += c;
			}
			System.out.println("Antwort empfangen: " + antwort);
			angeblicheLoesung = Integer.parseInt(antwort);

			if (angeblicheLoesung != loesung) {
				out.write("%Falsche Antwort. Probier es noch einmal.$");
				out.flush();
			}
		}
	}

	private void aufgabeStellen() throws IOException {
		Random zufall;
		int zufallszahl;
		zufall = new Random();
		int zahl1 = zufall.nextInt(8) + 2;
		int zahl2 = zufall.nextInt(90) + 10;
		loesung = zahl1 * zahl2;
		out.write("?" + zahl1 + " * " + zahl2 + "$");
		out.flush();
	}

}
