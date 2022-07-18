package MainPackage;
import java.util.Random;
import java.io.Serializable;

public class Veiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	// Atributos do carro.
	static int qc = 0;
	int id;
	final int qtrodas = 4;
	int dist;
	
	boolean ipva;
	boolean venda;
	
	
	roda rodas[] = {new roda(),new roda(),new roda(),new roda()}; // array de objetos roda
	
	double combustivel = 3.5;
	
	String desenho[] = {"    ____\n"," __/  |_ \\_\n","|  _     _``-.  \n","'-(_)---(_)--'\n\n\n"};
	
	
	// Método construtor do objeto carro
	public Veiculo(boolean ipva, roda[] rodas, boolean venda, int dist) {
		Random r = new Random();
		this.dist = dist;
		this.ipva = r.nextBoolean();
		this.rodas = rodas;
		this.venda = r.nextBoolean();
		qc++;
		this.id = qc;
	}
	
	
	// Método construtor do objeto carro, porém, sem a necessidade de passar argumentos para criar o objeto.
	// Aqui fazemos uma sobrecarga do método construtor.
	public Veiculo() {
		
		Random r = new Random();
		this.ipva = r.nextBoolean();
		this.venda = r.nextBoolean();
		qc++;
		this.id = qc;
	}


	// Getter and Setters da classe car.
	public int getId() {
		return id;
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
	
	public void imprimeDesenho() {
		String aux = "";
		for(int i = 0; i < dist;i++) {
			aux = "    " + aux;
		}
		for(int i = 0; i < 4; i++) {
			System.out.print(aux + desenho[i]);
		}
	}
	public boolean isCalibAll() {
		for(int i =0; i<4; i++) {
			if(rodas[i].isCalib() == false) {
				return false;
			}
		}	
		return true;
	}

}
