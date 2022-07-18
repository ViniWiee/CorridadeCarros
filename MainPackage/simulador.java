package MainPackage;

// Bibliotecas que utilizaremos para ler e gravar arquivos.
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

// Bibliotecas que utilizaremos para randomizar e pegar o input no console.
import java.util.Scanner;
import java.util.Random;

// Nossa classe simulador � a classe que contem a nossa fun��o main que ser� executada.
public class simulador {
	
	// Fun��o main
	public static void main(String[] args) {
			
		//Chamamos a fun��o menu para exibir as fun��es que o usu�rio pode utilizar daqui pra frente.
		menu();
		
	}
	static int aux =0;
	
	// Fun��o Menu do Usu�rio.
	private static void menu() {
		
		// Inicializamos o objeto do tipo Scanner para poder ler os inputs do usu�rio posteriormente.
		Scanner scan = new Scanner(System.in);
		
		// Inicializamos nosso objeto Random na vari�vel r. Utilizaremos v�rias randomiza��es ao percorrer do programa.
		// Isto � feito para tornar o programa da corrida mais din�mico.
		Random r = new Random();
		
		// A vari�vel State ser� uma vari�vel para controlar a execu��o do nosso loop em que roda a corrida.
		// Neste caso, ela inicia em true para que possamos dar in�cio logo em seguida.
		boolean state = true;
		
		// A vari�vel aux serve para auxiliar na contagem da quantidade de carros que de fato est�o inseridos na lista.
		// Tamb�m serve para nos dar um �ndice para inserir um carro na lista.
		
		// Aqui criamos nosso array de tamanho 20, que ser� do tipo car. Isso significa que s� ser� poss�vel ter um total de 20 objetos do tipo car.
		Veiculo[] Lista = new Veiculo[20];
		
		System.out.println("-------------------------------------------------------------------\n"
				+ "Deseja abrir um arquivo j� existente? Digite 1 \nSe n�o deseja abrir um arquivo, digite "
				+ "qualquer outro n�mero.\n-------------------------------------------------------------------\n \n");
		
		int resposta = Integer.parseInt(scan.nextLine());
		
		if(resposta == 1) {
			try {
				Lista = ler();
				for(int i = 0; i < 20; i++) {
					if (Lista[i] != null) {
							Veiculo.qc +=1;
					}
				}
			}catch(Exception ex) {
				System.out.print(ex.toString());
			}
			
		}
		
		
		
		System.out.println("------------------------------------------------------------------------------------------------\n"
				+ "A corrida est� prestes a come�ar!! \n"
				+ "A pista � impressa a cada itera��o do loop.\n"
				+ "� poss�vel interagir utilizando o menu do usu�rio que tamb�m � impresso todo loop.\n"
				+ "� poss�vel que um carro comece sem combust�vel e n�o se movimente durante as primeiras itera��es.\n"
				+ "Quanto mais carros forem inseridos, mais interessante fica o programa c:\n"
				+ "Confira abaixo.\n------------------------------------------------------------------------------------------------\n");
		
		// Est� ser� nosso loop principal. � muito comum aplica��es e jogos rodarem em um loop.
		while(state) {
			
			// Primeiro, mostramos tudo que temos na nossa pista. O m�todo imprimir todos imprime todos os objetos carro da lista.
			imprimirTodos(Lista);
			if(simulador.aux == 0) {
				System.out.println("-------------------------------\n A pista est� vazia no momento.\n------------------------------- \n");
			}
			// Esta � nossa mensagem para que possamos interagir com o usu�rio
			System.out.println("Digite 0 para que a corrida siga.\n"
					+ "Digite 1 para finalizar a execu��o do programa. \n"
					+ "Digite 2 se deseja remover um dos ve�culos da pista. \n"
					+ "Digite 3 se deseja incluir um ve�culo na pista. Ele ser� inserido no final. \n"
					+ "Digite 4 se deseja salvar a corrida em seu estado atual.\n"
					+ "Digite 5 se deseja imprimir os dados de um carro em espec�fico");
			
			// Utilizamos o m�todo nextLine que l� a pr�xima linha inserida no console pelo usu�rio.
			try{
				String response = scan.nextLine();
			
				// Utilizamos a fun��o parseInt para mudar nossa resposta do tipo String para Inteiro. Isso facilita a utiliza��o do Switch.
				int resp = Integer.parseInt(response);
				
				
				// Esta � a estrutura switch que vai nos ajudar a tomar a decis�o correta para cada intera��o do usu�rio.
				switch(resp) {
				
					// Caso 0, sabaremos que o usu�rio deseja simplesmente continuar sem mudar absolutamente nada. Iremos simplesmente movimentar
					// todos os carros da lista, pagar um ipva aleat�rio, abastecer um carro aleat�rio e calibrar um pneu aleat�rio.
				
					case 0:
						try {
							Lista = movimentarTodos(Lista);
							int ri = r.nextInt(Veiculo.qc);
							
							Lista = pagarIpva(ri,Lista);
							
							for(int i = 0; i < Veiculo.qc; i++) {
								Lista = abastecer(i, Lista, 0 + (0.55)* r.nextDouble());
								Lista = calibrarUm(i, Lista);
								}
						}catch(Exception ex) {
							
						}
						break;
					
					// Caso 1, sairemos do loop e fecharemos o objeto Scan.
					case 1:
						state = false;
						sair(scan);
						break;
						
					// Caso 2, removeremos o carro na posi��o dada pelo usu�rio e diminuiremos a vari�vel aux que nos diz quantos carros est�o na pista.
					case 2:
						System.out.println("Digite a posi��o do carro que deseja remover.");
						String pos = scan.nextLine();
						Lista = remover(Integer.parseInt(pos), Lista);
						break;
					// Caso 3, incluiremos um carro no final da pista.
					case 3:
						try {
							Lista = incluir(simulador.aux, Lista);
							break;
						}catch (Exception ex){
							System.out.println(ex.toString());
						}
						
					// Caso 4, gravamos o atual estado da corrida em um arquivo .dat
					case 4: 
						gravar(Lista);
						break;
						
					// Caso 5, mostraremos as informa��es de um determinado carro em uma posi��o da pista.
					case 5:
						System.out.println("Digite a posi��o do carro que deseja ver as informa��es.");
						String res = scan.nextLine();
						informar(Integer.parseInt(res) -1, Lista);
						break;
					default:
						System.out.print("N�o foi poss�vel entender o input. \n");
				}
			}catch(Exception ex) {
				System.out.print("N�o foi poss�vel entender o input.\n");
			}
		}
	}


	// A fun��o incluir insere um objeto car na Lista de carros, utilizando a vari�vel aux para indicar
	// a ultima posi��o vazia da lista.
	private static Veiculo[] incluir(int aux, Veiculo[] Lista) {
		
		if(simulador.aux < 20) {
			Lista[aux] = new Veiculo();
			simulador.aux += 1;
			return Lista;
		}else {
			System.out.println("N�o foi poss�vel inserir o ve�culo pois a pista de 20 lugares est� cheia.");
			return Lista;
		}
		
		
	}
	
	// O m�todo remover remove um carro em dada posi��o da lista.
	// Exemplo: Se o usus�rio quiser remover o carro da primeira posi��o, o carro na posi��o Lista[0] ser� removido.
	// Isso � feito a partir da c�pia de duas listas resultando em uma terceira lista que n�o possui o elemento em quest�o.
	private static Veiculo[] remover(int pos, Veiculo[] Lista) {
		
		try{
			if (Lista[pos-1] == null || pos > Lista.length ) { 
            System.out.println("Nenhuma remo��o pode ser executada.");
            return Lista;
			} 
		
			Veiculo[] copia = new Veiculo[20];
			
			System.arraycopy(Lista, 0, copia, 0, pos-1);
			System.arraycopy(Lista, pos, copia, pos-1, 19-simulador.aux);
			
			simulador.aux -=1;
			Veiculo.qc -= 1;
	
			return copia;
		}catch(Exception ex) {
			System.out.println("Nenhuma remo��o pode ser executada.");
			return Lista;
		}
		
	}
	
	// A fun��o abastecer aumenta o atributo combust�vel pelo valor passado na vari�vel combustivel.
	private static Veiculo[] abastecer(int aux, Veiculo[] Lista, double combustivel) {

		Lista[aux].setCombustivel(Lista[aux].getCombustivel() + combustivel);
		return Lista;
		
	}
	
	// O m�todo CalibrarUm muda o atributo isCalib de uma roda definida pela vari�vel aux.
	// Neste caso estamos randomizando uma das 4 rodas do carro na posi��o aux.
	private static Veiculo[] calibrarUm(int aux, Veiculo[] Lista) {
		
		for(int i = 0; i < 4 ;i++) {
			Lista[aux].rodas[i].setCalib(true);
		}
		return Lista;
	}
	
	// A fun��o calibraTodos muda o atributo isCalib de todas as rodas de um carro para True.
	private static Veiculo[] calibrarTodos(Veiculo[] Lista) {
		
		for(int i = 0; i < Veiculo.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].setCalib(true);
			}
		}
		
		return Lista;
		
	}
	
	// A fun��o esvaziarUm muda o atributo isCalib para False de um pneu aleat�rio de um carro na posi��o Aux.
	// Neste trabalho esta fun��o n�o foi utilizada nenhuma vez.
	private static Veiculo[] esvaziarUm(int aux, Veiculo[] Lista) {
		
		
		Random r = new Random();
		
		Lista[aux].rodas[r.nextInt(4)].setCalib(false);
		return Lista;
		
		
	}
	
	// A fun��o esvaziarTodos muda o atriuto isCalib de todas as rodas de um carro na posi��o aux.
	// Esta fun��o n�o � utilizada nenhuma vez durante o trabalho.
	private static Veiculo[] esvaziarTodos(int aux, Veiculo[] Lista) {
		
		for(int i = 0; i < Veiculo.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].setCalib(false);
			}
		}
		
		return Lista;
		
	}
	
	// A fun��o movimentarUm aumenta dois espa�os na string da matriz do desenho do carro, fazendo com que ele se mova
	// na pista. Para isso, primeiro verificamos se o carro existe dentro da lista. Depois verificamos se o ipva dele
	// foi pago. Ainda depois disso, verificamos se todos os pneus do respectivo carro est�o calibrados e verificamos
	// se ele possui no m�nimo 0.55 L de combust�vel para que possa andar.
	// Caso alguma dessas condi��es n�o for atendida, nada ser� feito e retornaremos a lista exatamente como ela estava
	// antes de entrar no m�todo.
	private static Veiculo[] movimentarUm(int pos, Veiculo[] Lista) {
		
		if(Lista[pos] == null) {
			return Lista;
		}
		
		
		if(Lista[pos].ipva == false) {
			return Lista;
		}
		
		
		for(int i = 0; i <4; i++) {
			if(Lista[pos].rodas[i].isCalib() == false ) {
				return Lista;
			}
		}
		if(Lista[pos].getCombustivel() >= 0.55){
			Lista[pos].setDist(Lista[pos].getDist() + 1);
			Lista[pos].setCombustivel(Lista[pos].getCombustivel() - 0.55);
		}
		
		return Lista;
	}
	
	// A fun��o movimntarTodos utilizada fun��o movimentarUm para que possamos executa-las em toda nossa lista de carros.
	// Para isso, checaremos a quantidade de carros utilizando a vari�vel car.qc (Quantidade de Carros)
	private static Veiculo[] movimentarTodos(Veiculo[] Lista) {
		
		for(int i = 0; i < Veiculo.qc; i++) {
			Lista = movimentarUm(i, Lista);
		}
		return Lista;
	}
	
	private static void informar(int pos, Veiculo[] Lista) {
		try {
		System.out.print("------------------------------------------\n"
				+ "ID: " +Lista[pos].getId() +"\n"
				+ "Seu ipva foi pago?:" + Lista[pos].isIpva() + "\n"
				+ "Seu combustivel � de :" + Lista[pos].getCombustivel() + "\n"
				+ "Suas rodas est�o todas calibradas?:" + Lista[pos].isCalibAll()
				+ "\n------------------------------------------\n"
				);
		}catch (Exception ex){
			System.out.print("As informa��es n�o puderam ser informadas.");
		}
	}
	// A fun��o ImprimirUm imprime a matriz do atributo desenho do carro em quest�o no console.
	// A matriz � composta de 4 linhas de string que compoem o desenho, por isso nosso loop ter� 4 execu��es.
	private static void imprimirUm(int aux, Veiculo[] Lista) {
		Lista[aux].imprimeDesenho();
	}
	
	// A fun��o imprimirTodos utiliza a fun��o imprimirUm para imprimir todos os carros no console.
	// Para isso, utilizamos a vari�vel car.qc para saber quantos carros existem na lista.
	private static void imprimirTodos(Veiculo[] Lista) {
		
		
		for(int i = 0; i < Veiculo.qc; i++) {
			if(Lista[i] != null) {
				if(Lista[i].id != -1) {
					imprimirUm(i,Lista);
				}
			}
		}
		
		
	}
	
	// A fun��o pagarIpva modifica o atributo ipva para True de um carro definido pela vari�vel id.
	private static Veiculo[] pagarIpva (int id, Veiculo[] Lista){
		Lista[id].setIpva(true);
		return Lista;
	}
	
	// A fun��o gravar cria um arquivo Carros.dat e grava os elementos do nosso array de carros neste arquivo.
	private static void gravar(Veiculo[] Lista) {
		File arquivo = new File("Carros.dat");
		try {
			FileOutputStream fous = new FileOutputStream(arquivo);
			ObjectOutputStream oos = new ObjectOutputStream(fous);
			
			oos.writeObject(Lista);
			
			oos.flush();
			oos.close();
			
		} catch (Exception ex){
			
		}
	}
	
	// A fun��o ler abre um arquivo Carros.dat e copia seu conte�do para um array de carros chamado Lista. 
	// Logo em seguida retornamos esse array para utiliza-lo em nosso programa.
	private static Veiculo[] ler() {
		File arquivo = new File("Carros.dat");
		Veiculo[] Lista;
		try {
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream oin = new ObjectInputStream(fis);
			
			Lista = (Veiculo[]) oin.readObject();
			
			oin.close();
			fis.close();
			
			for (int i = 0; i <20;i++) {
				if(Lista[i] != null) {
					simulador.aux +=1;
				}
			}
			return Lista;
			
		} catch(Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
		
	}
	
	// A fun��o sair � utilizada para fechar um objeto do tipo scanner que utilizamos para ler os inputs do usu�rio.
	private static void sair(Scanner s) {
		s.close();
	}
}
