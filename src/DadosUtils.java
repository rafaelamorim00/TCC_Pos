import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DadosUtils {
	
	public static Map<Long,String> getNomes() throws IOException{
			
		BufferedReader br = new BufferedReader(new FileReader("d:/herois.csv"));
		br.readLine();
		
		Map<Long,String> nomes = new HashMap<>();
		
		while( br.ready()){
			String linha = br.readLine();
			
			String[] valores = linha.split(",");
			
			nomes.put(new Long(valores[0]), valores[2]);
		}
		
		br.close();
		
		return nomes;
	}
	
	public static String getValorInteiro(float x){
		String str = String.valueOf(x);
		return str.substring(0,str.indexOf("."));
	}
	
	public static String getValorCasasDecimais(float x, int casasDecimais){
		String str = String.valueOf(x);
		return str.substring(0,str.indexOf(".")+casasDecimais+1);
	}

}
