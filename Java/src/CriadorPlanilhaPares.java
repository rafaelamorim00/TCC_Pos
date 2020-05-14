import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriadorPlanilhaPares {
	
	static private Map<Long,Heroi> mapaHerois;

	public static void main(String[] args) throws IOException {

		Map<Long, String> nomes = DadosUtils.getNomes();
		nomes.keySet();
		
		iniciarMapa(nomes.keySet());
		
		popularMapas();
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("d:/dadosPartidas.txt"));
		
		String[] cabecalho = {	"Nome A", "Nome B", 
								"Versus Win", "Versus Loss", "Versus Total", "Versus Win Rate", "Versus Loss Rate",
								"Coop Win", "Coop Loss", "Coop Total", "Coop Win Rate", "Coop Loss Rate"};
		
        buffWrite.append(String.join(",", cabecalho) + "\n");

        for (Long idA : nomes.keySet()){
        	for (Long idB : nomes.keySet()){
        		
        		if (idA != idB){
        			
        			float v_win = mapaHerois.get(idA).getMapaVersus().get(idB).getWin();
            		float v_loss = mapaHerois.get(idA).getMapaVersus().get(idB).getLoss();
            		float v_total = mapaHerois.get(idA).getMapaVersus().get(idB).getTotal();
            		float v_winRate = (v_win * 100) / v_total;
            		float v_lossRate = 100 - v_winRate;
            		
            		float c_win = mapaHerois.get(idA).getMapaCoop().get(idB).getWin();
            		float c_loss = mapaHerois.get(idA).getMapaCoop().get(idB).getLoss();
            		float c_total = mapaHerois.get(idA).getMapaCoop().get(idB).getTotal();
            		float c_winRate = (c_win * 100) / c_total;
            		float c_lossRate = 100 - c_winRate;
        		
            		String[] linha = new String[]{
                			nomes.get(idA),
                			nomes.get(idB),
                			DadosUtils.getValorInteiro(v_win),
                			DadosUtils.getValorInteiro(v_loss),
                			DadosUtils.getValorInteiro(v_total),
                			DadosUtils.getValorCasasDecimais(v_winRate, 1),
                			DadosUtils.getValorCasasDecimais(v_lossRate, 1),
                			DadosUtils.getValorInteiro(c_win),
                			DadosUtils.getValorInteiro(c_loss),
                			DadosUtils.getValorInteiro(c_total),
                			DadosUtils.getValorCasasDecimais(c_winRate, 1),
                			DadosUtils.getValorCasasDecimais(c_lossRate, 1)
                		};

                		buffWrite.append(String.join(",", linha) + "\n");
            		
        		}
        	}
        }

        buffWrite.close();
        
        
	}
	
	public static void iniciarMapa(Set<Long> ids){
		
		mapaHerois = new HashMap<>();
		
		for (Long idA : ids){
			for (Long idB : ids){
				if (idA != idB){
					
					if (!mapaHerois.containsKey(idA))
						mapaHerois.put(idA, new Heroi());
					
					mapaHerois.get(idA).getMapaVersus().put(idB, new Historico());
					mapaHerois.get(idA).getMapaCoop().put(idB, new Historico());
					
				}
			}
		}
		
	}
	
	public static void popularMapas() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("d:/picks_data.csv"));
		br.readLine();
		
		while( br.ready()){
			String linha = br.readLine();
			
			String[] ids = linha.split(",");
			
			
			for (int i = 0; i < 5; i++){
				for (int j = 5; j < 10; j++){
					Long idA = new Long(ids[i]);
					Long idB = new Long(ids[j]);
					
					if (!mapaHerois.containsKey(idA))
						mapaHerois.put(idA, new Heroi(idA));
					
					if (!mapaHerois.containsKey(idB))
						mapaHerois.put(idB, new Heroi(idB));
					
					if (!mapaHerois.get(idA).getMapaVersus().containsKey(idB))
						mapaHerois.get(idA).getMapaVersus().put(idB, new Historico());
					
					if (!mapaHerois.get(idB).getMapaVersus().containsKey(idA))
						mapaHerois.get(idB).getMapaVersus().put(idA, new Historico());
					
					mapaHerois.get(idA).getMapaVersus().get(idB).adicionarWin();
					mapaHerois.get(idB).getMapaVersus().get(idA).adicionarLoss();
					
				}
			}
			
			for (int i = 0; i < 5; i++){
				for (int j = 0; j < 5; j++){
					if (i != j){
						Long idA = new Long(ids[i]);
						Long idB = new Long(ids[j]);
						
						if (!mapaHerois.containsKey(idA))
							mapaHerois.put(idA, new Heroi(idA));
						
						if (!mapaHerois.containsKey(idB))
							mapaHerois.put(idB, new Heroi(idB));
						
						if (!mapaHerois.get(idA).getMapaCoop().containsKey(idB))
							mapaHerois.get(idA).getMapaCoop().put(idB, new Historico());
						
						if (!mapaHerois.get(idB).getMapaCoop().containsKey(idA))
							mapaHerois.get(idB).getMapaCoop().put(idA, new Historico());
						
						mapaHerois.get(idA).getMapaCoop().get(idB).adicionarWin();
					}
				}
			}
			
			for (int i = 5; i < 10; i++){
				for (int j = 5; j < 10; j++){
					if (i != j){
						Long idA = new Long(ids[i]);
						Long idB = new Long(ids[j]);
						
						if (!mapaHerois.containsKey(idA))
							mapaHerois.put(idA, new Heroi(idA));
						
						if (!mapaHerois.containsKey(idB))
							mapaHerois.put(idB, new Heroi(idB));
						
						if (!mapaHerois.get(idA).getMapaCoop().containsKey(idB))
							mapaHerois.get(idA).getMapaCoop().put(idB, new Historico());
						
						if (!mapaHerois.get(idB).getMapaCoop().containsKey(idA))
							mapaHerois.get(idB).getMapaCoop().put(idA, new Historico());
						
						mapaHerois.get(idA).getMapaCoop().get(idB).adicionarLoss();
					}
				}
			}
			
		}

		br.close();
	}
	
	
}
