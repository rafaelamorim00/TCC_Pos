import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MLPreparaBaseAmbos {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("d:/picks_data.csv"));
		br.readLine();
	
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/ambosML.csv"));
		
		String[] cabecalho = {"L1", "L2", "L3", "L4", "L5", "W1", "W2", "W3", "W4", "W5"};
		
		buffWrite.append(String.join(",", cabecalho) + "\n");
		
		while( br.ready()){
			
			String linha = br.readLine();
			String[] ids = linha.split(",");
						
			List<String[]> combinacoes = getCombinacoes(ids);
			
			for (String[] c : combinacoes)
				buffWrite.append(String.join(",", c) + "\n");
		}
	}
	
	private static List<String[]> getCombinacoes(String[] ids){
		
		List<String[]> combinacoes = new ArrayList<>();
				
		for (int i = 0; i < 5; i++){
			System.out.println();
			for (int a = 0; a < 4; a++){
				System.out.println();
				String next = ids[a+1];
				String aux = ids[a];
				ids[a] = next;
				ids[a+1] = aux;
				
//				combinacoes.add(ids.clone());
				
				for (int i2 = 5; i2 < 10; i2++){
					System.out.println();
					for (int a2 = 5; a2 < 9; a2++){
						
						String next2 = ids[a2+1];
						String aux2 = ids[a2];
						ids[a2] = next2;
						ids[a2+1] = aux2;
						
						combinacoes.add(ids.clone());
					}
				}
			}
		}
		
		
		
//		List<String[]> combinacoes = new ArrayList<>();
//		
//		for (String[] p : perdedores){
//			System.out.println(p[0] + " - " + p[1] + " - " + p[2] + " - " + p[3] + " - " + p[4]);
//			for (String[] v : vencedores){
//				String[] c = {p[0], p[1], p[2], p[3], p[4], v[0], v[1], v[2], v[3], v[4]};
//				combinacoes.add(c);
//			}
//		}
		
		return combinacoes;		
	}
	
}

	
