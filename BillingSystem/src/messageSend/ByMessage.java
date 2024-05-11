package messageSend;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ByMessage {
	public String ByMessage1(){
		try {
			// Construct data
			String apiKey = "apikey=" + "3sE3CUIAcUQ-uHLlpFETH1Wwu0sS7SEP28XXGHUMko";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "Home Tech Design";
			String numbers = "&numbers=" + "919760493277";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	public static void main(String arf[]){
		ByMessage msg=new ByMessage();
		String a=msg.ByMessage1();
		System.out.println("\n"+a);
	}
}
