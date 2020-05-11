import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CriadorPlanilhaAtributosPrincipais {
	
	private static int STR = 1;
	private static int AGI = 2;
	private static int INT = 3;
	
	private static Map<Integer, Map<Integer, Map<Integer, Historico>>> mapa;

	public static void main(String[] args) throws IOException {

		mapa = new HashMap<>();
		getMapa();
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/dadosPartidasAtributos.csv"));
		
		String[] cabecalho = {"Str", "Agi", "Int", "Win", "Loss", "Total", "Win Rate", "Loss Rate"};
		
        buffWrite.append(String.join(",", cabecalho) + "\n");

        for (Integer str : mapa.keySet()){
        	for (Integer agi : mapa.get(str).keySet()){
        		for (Integer inte : mapa.get(str).get(agi).keySet()){
        			
        			float win = mapa.get(str).get(agi).get(inte).getWin();
            		float loss = mapa.get(str).get(agi).get(inte).getLoss();
            		float total = mapa.get(str).get(agi).get(inte).getTotal();
            		float winRate = (win * 100) / total;
            		float lossRate = 100 - winRate;
        	
            		String[] linha = new String[]{
                			String.valueOf(str),
                			String.valueOf(agi),
                			String.valueOf(inte),
                			DadosUtils.getValorInteiro(win),
                			DadosUtils.getValorInteiro(loss),
                			DadosUtils.getValorInteiro(total),
                			DadosUtils.getValorCasasDecimais(winRate, 1),
                			DadosUtils.getValorCasasDecimais(lossRate, 1)
            		};
            		
            		buffWrite.append(String.join(",", linha) + "\n");
            	}
        	}
        }

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
			
			adicionar(str, agi, inte, false);
			
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
			
			adicionar(str, agi, inte, true);
			
		}
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
	
	private static void adicionar(int str, int agi, int inte, boolean win){
		
		if (!mapa.containsKey(str))
			mapa.put(str, new HashMap<>());
		
		if (!mapa.get(str).containsKey(agi))
			mapa.get(str).put(agi, new HashMap<>());
		
		if (!mapa.get(str).get(agi).containsKey(inte))
			mapa.get(str).get(agi).put(inte, new Historico());
		
		if (win)
			mapa.get(str).get(agi).get(inte).adicionarWin();
		else
			mapa.get(str).get(agi).get(inte).adicionarLoss();		
		
	}

}
