import java.util.HashMap;
import java.util.Map;

public class Heroi {

	private long id;
	
	private String nome;
	
	private Map<Long, Historico> mapaVersus;
	
	private Map<Long, Historico> mapaCoop;
	
	private Historico historico;
	
	public Heroi() {
		super();
		this.mapaVersus = new HashMap<>();
		this.mapaCoop = new HashMap<>();
		this.historico = new Historico();
	}

	public Heroi(long id) {
		super();
		this.id = id;
		this.mapaVersus = new HashMap<>();
		this.mapaCoop = new HashMap<>();
		this.historico = new Historico();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<Long, Historico> getMapaVersus() {
		return mapaVersus;
	}

	public void setMapaVersus(Map<Long, Historico> mapaVersus) {
		this.mapaVersus = mapaVersus;
	}

	public Map<Long, Historico> getMapaCoop() {
		return mapaCoop;
	}

	public void setMapaCoop(Map<Long, Historico> mapaCoop) {
		this.mapaCoop = mapaCoop;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	
		
}
