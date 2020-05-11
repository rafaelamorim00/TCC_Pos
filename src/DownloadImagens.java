import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImagens {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("d:/herois.csv"));
		br.readLine();
		
		while( br.ready()){
			String linha = br.readLine();
			
			String[] valores = linha.split(",");
		
			String stringUrl = "https://api.opendota.com" + valores[8];
			
			URLConnection uc;
			URL url = new URL(stringUrl);
			
			uc = url.openConnection();
			uc.connect();
			uc = url.openConnection();
			uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			InputStream is = uc.getInputStream();
					
			String fileName = url.getFile();
			String destName = "d:/images/" + valores[0] + ".png";
			System.out.println(destName);
		 
			OutputStream os = new FileOutputStream(destName);
		 
			byte[] b = new byte[2048];
			int length;
		 
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
		 
			is.close();
			os.close();
		}

	}

}
