
public class Historico {
	
	private long id;
	
	private int win;
	
	private int loss;
	
	private int total;
	
	public Historico() {
		super();
		this.win = 0;
		this.loss = 0;
		this.total = 0;
	}
	
	public void adicionarWin(){
		win++;
		atualizarTotal();
	}
	
	public void adicionarLoss(){
		loss++;
		atualizarTotal();
	}
	
	public void atualizarTotal(){
		total = win + loss;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	

}
