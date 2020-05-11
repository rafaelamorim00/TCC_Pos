import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CriadorPlanilhaHeroImage {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("d:/herois.csv"));
		br.readLine();
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/HeroImage.csv"));
		buffWrite.append("Nome, Imagem\n");
		
		while( br.ready()){
			String linha = br.readLine();
			
			String[] valores = linha.split(",");
			
			buffWrite.append(valores[2] + ", " + "https://api.opendota.com" + valores[8] + "\n");
		}
		
		buffWrite.close();
		br.close();
			
	}
	
}
