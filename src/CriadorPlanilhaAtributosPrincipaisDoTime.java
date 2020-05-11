import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CriadorPlanilhaAtributosPrincipaisDoTime {
	
	private static int STR = 1;
	private static int AGI = 2;
	private static int INT = 3;
	
	private static Map<Integer, Historico> mapa;

	public static void main(String[] args) throws IOException {

		mapa = new HashMap<>();
		mapa.put(STR, new Historico());
		mapa.put(AGI, new Historico());
		mapa.put(INT, new Historico());
		getMapa();
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/dadosPartidasAtributosTime.csv"));
		
		String[] cabecalho = {"Atributo", "Win", "Loss", "Total", "Win Rate", "Loss Rate"};
		
        buffWrite.append(String.join(",", cabecalho) + "\n");

		float str_win = mapa.get(STR).getWin();
		float str_loss = mapa.get(STR).getLoss();
		float str_total = mapa.get(STR).getTotal();
		float str_winRate = (str_win * 100) / str_total;
		float str_lossRate = 100 - str_winRate;
		
		float agi_win = mapa.get(AGI).getWin();
		float agi_loss = mapa.get(AGI).getLoss();
		float agi_total = mapa.get(AGI).getTotal();
		float agi_winRate = (agi_win * 100) / agi_total;
		float agi_lossRate = 100 - agi_winRate;
		
		float int_win = mapa.get(INT).getWin();
		float int_loss = mapa.get(INT).getLoss();
		float int_total = mapa.get(INT).getTotal();
		float int_winRate = (int_win * 100) / int_total;
		float int_lossRate = 100 - int_winRate;
        	
		String[] linhaStr = new String[]{
    			"STR",
    			DadosUtils.getValorInteiro(str_win),
    			DadosUtils.getValorInteiro(str_loss),
    			DadosUtils.getValorInteiro(str_total),
    			DadosUtils.getValorCasasDecimais(str_winRate, 1),
    			DadosUtils.getValorCasasDecimais(str_lossRate, 1)
		};
		
		String[] linhaAgi = new String[]{
    			"AGI",
    			DadosUtils.getValorInteiro(agi_win),
    			DadosUtils.getValorInteiro(agi_loss),
    			DadosUtils.getValorInteiro(agi_total),
    			DadosUtils.getValorCasasDecimais(agi_winRate, 1),
    			DadosUtils.getValorCasasDecimais(agi_lossRate, 1)
		};
		
		String[] linhaInt = new String[]{
    			"INT",
    			DadosUtils.getValorInteiro(int_win),
    			DadosUtils.getValorInteiro(int_loss),
    			DadosUtils.getValorInteiro(int_total),
    			DadosUtils.getValorCasasDecimais(int_winRate, 1),
    			DadosUtils.getValorCasasDecimais(int_lossRate, 1)
		};
            		
		buffWrite.append(String.join(",", linhaStr) + "\n");
		buffWrite.append(String.join(",", linhaAgi) + "\n");
		buffWrite.append(String.join(",", linhaInt) + "\n");
        
        buffWrite.close();
                
	}
	
	public static void getMapa() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("d:/picks_data.csv"));
		br.readLine();
		
		 
		Map<Integer, Integer> atributos = getAtributosPrincipais();
		
		while( br.ready()){
			String linha = br.readLine();
			String[] ids = linha.split(",");
			
			int str = 0, agi = 0, inte = 0;
			
			for (int i = 0; i < 5; i++){
				Integer id = new Integer(ids[i]);
				if (atributos.get(id) == STR)
					str++;
				else if (atributos.get(id) == AGI)
					agi++;
				else if (atributos.get(id) == INT)
					inte++;
			}
			
			int maior = getMaior(str, agi, inte);
			
			if (maior > 0){
				if (maior == str)
					mapa.get(STR).adicionarLoss();
				else if (maior == agi)
					mapa.get(AGI).adicionarLoss();
				else if (maior == inte)
					mapa.get(INT).adicionarLoss();
			}
			
			str = 0;
			agi = 0;
			inte = 0;
			
			for (int i = 5; i < 10; i++){
				Integer id = new Integer(ids[i]);
				if (atributos.get(id) == STR)
					str++;
				else if (atributos.get(id) == AGI)
					agi++;
				else if (atributos.get(id) == INT)
					inte++;
			}
			
			maior = getMaior(str, agi, inte);
			
			if (maior > 0){
				if (maior == str)
					mapa.get(STR).adicionarWin();
				else if (maior == agi)
					mapa.get(AGI).adicionarWin();
				else if (maior == inte)
					mapa.get(INT).adicionarWin();
			}
			
		}
	}
	
	private static int getMaior(int a, int b, int c){
		
		if (a > b){
			if (a > c)
				return a;
			else if (a < c)
				return c;
		} else {
			if (b > c)
				return b;
			else if (b < c)
				return b;
		}
			
		return 0;
	}
	
	private static Map<Integer, Integer> getAtributosPrincipais() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("d:/herois.csv"));
		br.readLine();
		
		Map<Integer, Integer> atributos = new HashMap<>();
		
		while( br.ready()){
			String linha = br.readLine();
			
			String[] valores = linha.split(",");
			
			String att = valores[3];
			
			int numAtt = 0;
			if (att.equals("str"))
				numAtt = STR;
			else if (att.equals("agi"))
				numAtt = AGI;
			else if (att.equals("int"))
				numAtt = INT;
			
			atributos.put(new Integer(valores[0]), numAtt);
		}
		
		br.close();
		
		return atributos;
	}

}
