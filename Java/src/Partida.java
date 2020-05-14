
public class Partida {
	
	int id;
	
	private int[] vencedores;
	
	private int[] perdedores;

	
	
	public Partida() {
		super();
		vencedores = new int[5];
		perdedores = new int[5];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getVencedores() {
		return vencedores;
	}

	public void setVencedores(int[] vencedores) {
		this.vencedores = vencedores;
	}

	public int[] getPerdedores() {
		return perdedores;
	}

	public void setPerdedores(int[] perdedores) {
		this.perdedores = perdedores;
	}
	
	

}
