 
import java.net.*;
import java.io.*;

public class StockMiner {

	private Socket s1;

	public StockMiner() {
		try {
			String[] symb = { "AAPL", "IDCC" };
			for (int j = 0; j < symb.length; j++) {
				s1 = new Socket("money.cnn.com", 80);
				// Socket s1=new Socket(
				// "www.google.com",80);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						s1.getInputStream()));
				PrintWriter out = new PrintWriter(s1.getOutputStream(), true);
				out.println("GET /quote/financials/financials.html?symb="
						+ symb[j] + " HTTP/1.0\n\n");
				String line = "";
				while ((line = in.readLine()) != null) {
					if (line.contains("EPS Diluted")) {
						System.out.print(symb[j] + ":");
						int i = line.indexOf("EPS Diluted");
						line = line.substring(i + 39, i + 200);
						System.out.println(line);
					}
				}
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static void main(String[] args) {
		new StockMiner();
	}
}
