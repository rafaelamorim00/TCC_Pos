import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriadorPlanilhaIndividual {

	public static void main(String[] args) throws IOException {

		Map<Long, Heroi> mapaHerois = getEstatisticasHerois();
		Map<Long, String> nomes = DadosUtils.getNomes();
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/dadosHeroi.csv"));
		
		String[] cabecalho = {"Nome", "W", "L", "Total", "Win Rate", "Loss Rate"};
		
        buffWrite.append(String.join(",", cabecalho) + "\n");

        for (Long idA : mapaHerois.keySet()){
        		
    		float win = mapaHerois.get(idA).getHistorico().getWin();
    		float loss = mapaHerois.get(idA).getHistorico().getLoss();
    		float total = mapaHerois.get(idA).getHistorico().getTotal();
    		float winRate = (win * 100) / total;
    		float lossRate = 100 - winRate;
    		
    		String[] linha = new String[]{
    			nomes.get(idA),
    			DadosUtils.getValorInteiro(win),
    			DadosUtils.getValorInteiro(loss),
    			DadosUtils.getValorInteiro(total),
    			DadosUtils.getValorCasasDecimais(winRate, 1),
    			DadosUtils.getValorCasasDecimais(lossRate, 1)
    		};

    		buffWrite.append(String.join(",", linha) + "\n");
    	
        }

        buffWrite.close();
        
        
	}
	
	public static Map<Long, Heroi> getEstatisticasHerois() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("d:/picks_data.csv"));
		br.readLine();
		
		Map<Long,Heroi> mapaHerois = new HashMap<>();
		
		while( br.ready()){
			String linha = br.readLine();
			String[] ids = linha.split(",");
			
			for (int i = 0; i < 5; i++){
				Long id = new Long(ids[i]);
				if (!mapaHerois.containsKey(id))
					mapaHerois.put(id, new Heroi(id));
				
				mapaHerois.get(id).getHistorico().adicionarWin();
			}
			
			for (int i = 5; i < 10; i++){
				Long id = new Long(ids[i]);
				if (!mapaHerois.containsKey(id))
					mapaHerois.put(id, new Heroi(id));
				
				mapaHerois.get(id).getHistorico().adicionarLoss();
			}
			
		}
		
		return mapaHerois;
	}
	

}
