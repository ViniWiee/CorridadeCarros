package MainPackage;
import java.util.Random;
import java.io.Serializable;

public class car implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	// Atributos do carro.
	static int qc = 0;
	int id;
	final int qtrodas = 4;
	int dist;
	
	boolean ipva;
	boolean venda;
	
	
	roda rodas[] = {new roda(),new roda(),new roda(),new roda()}; // array de objetos roda
	
	double combustivel;
	
	String desenho[] = {"    ____\n"," __/  |_ \\_\n","|  _     _``-.  \n","'-(_)---(_)--'\n\n\n"};
	
	
	// Método construtor do objeto carro
	public car(boolean ipva, roda[] rodas, double combustivel, boolean venda, int dist) {
		Random r = new Random();
		this.dist = dist;
		this.ipva = r.nextBoolean();
		this.rodas = rodas;
		this.venda = r.nextBoolean();
		
		// verificando se o carro vai começar com 3.5l de combustivel
		if(ipva == true && venda == true) { 
			this.combustivel = 3.5;
		}
		
		qc++;
		this.id = qc;
	}
	
	
	// Método construtor do objeto carro, porém, sem a necessidade de passar argumentos para criar o objeto.
	// Aqui fazemos uma sobrecarga do método construtor.
	public car() {
		qc++;
		this.id = qc;
	}


	
	// Getter and Setters da classe car.
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDist() {
		return dist;
	}


	public void setDist(int dist) {
		this.dist = dist;
	}


	public boolean isIpva() {
		return ipva;
	}


	public void setIpva(boolean ipva) {
		this.ipva = ipva;
	}


	public boolean isVenda() {
		return venda;
	}


	public void setVenda(boolean venda) {
		this.venda = venda;
	}


	public double getCombustivel() {
		return combustivel;
	}


	public void setCombustivel(double combustivel) {
		this.combustivel = combustivel;
	}


}
