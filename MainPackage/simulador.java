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

// Nossa classe simulador é a classe que contem a nossa função main que será executada.
public class simulador {
	
	// Função main
	public static void main(String[] args) {
			
		//Chamamos a função menu para exibir as funções que o usuário pode utilizar daqui pra frente.
		menu();
		
	}
	static int aux =0;
	
	// Função Menu do Usuário.
	private static void menu() {
		
		// Inicializamos o objeto do tipo Scanner para poder ler os inputs do usuário posteriormente.
		Scanner scan = new Scanner(System.in);
		
		// Inicializamos nosso objeto Random na variável r. Utilizaremos várias randomizações ao percorrer do programa.
		// Isto é feito para tornar o programa da corrida mais dinâmico.
		Random r = new Random();
		
		// A variável State será uma variável para controlar a execução do nosso loop em que roda a corrida.
		// Neste caso, ela inicia em true para que possamos dar início logo em seguida.
		boolean state = true;
		
		// A variável aux serve para auxiliar na contagem da quantidade de carros que de fato estão inseridos na lista.
		// Também serve para nos dar um índice para inserir um carro na lista.
		
		// Aqui criamos nosso array de tamanho 20, que será do tipo car. Isso significa que só será possível ter um total de 20 objetos do tipo car.
		Veiculo[] Lista = new Veiculo[20];
		
		System.out.println("-------------------------------------------------------------------\n"
				+ "Deseja abrir um arquivo já existente? Digite 1 \nSe não deseja abrir um arquivo, digite "
				+ "qualquer outro número.\n-------------------------------------------------------------------\n \n");
		
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
				+ "A corrida está prestes a começar!! \n"
				+ "A pista é impressa a cada iteração do loop.\n"
				+ "É possível interagir utilizando o menu do usuário que também é impresso todo loop.\n"
				+ "É possível que um carro comece sem combustível e não se movimente durante as primeiras iterações.\n"
				+ "Quanto mais carros forem inseridos, mais interessante fica o programa c:\n"
				+ "Confira abaixo.\n------------------------------------------------------------------------------------------------\n");
		
		// Está será nosso loop principal. É muito comum aplicações e jogos rodarem em um loop.
		while(state) {
			
			// Primeiro, mostramos tudo que temos na nossa pista. O método imprimir todos imprime todos os objetos carro da lista.
			imprimirTodos(Lista);
			if(simulador.aux == 0) {
				System.out.println("-------------------------------\n A pista está vazia no momento.\n------------------------------- \n");
			}
			// Esta é nossa mensagem para que possamos interagir com o usuário
			System.out.println("Digite 0 para que a corrida siga.\n"
					+ "Digite 1 para finalizar a execução do programa. \n"
					+ "Digite 2 se deseja remover um dos veículos da pista. \n"
					+ "Digite 3 se deseja incluir um veículo na pista. Ele será inserido no final. \n"
					+ "Digite 4 se deseja salvar a corrida em seu estado atual.\n"
					+ "Digite 5 se deseja imprimir os dados de um carro em específico");
			
			// Utilizamos o método nextLine que lê a próxima linha inserida no console pelo usuário.
			try{
				String response = scan.nextLine();
			
				// Utilizamos a função parseInt para mudar nossa resposta do tipo String para Inteiro. Isso facilita a utilização do Switch.
				int resp = Integer.parseInt(response);
				
				
				// Esta é a estrutura switch que vai nos ajudar a tomar a decisão correta para cada interação do usuário.
				switch(resp) {
				
					// Caso 0, sabaremos que o usuário deseja simplesmente continuar sem mudar absolutamente nada. Iremos simplesmente movimentar
					// todos os carros da lista, pagar um ipva aleatório, abastecer um carro aleatório e calibrar um pneu aleatório.
				
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
						
					// Caso 2, removeremos o carro na posição dada pelo usuário e diminuiremos a variável aux que nos diz quantos carros estão na pista.
					case 2:
						System.out.println("Digite a posição do carro que deseja remover.");
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
						
					// Caso 5, mostraremos as informações de um determinado carro em uma posição da pista.
					case 5:
						System.out.println("Digite a posição do carro que deseja ver as informações.");
						String res = scan.nextLine();
						informar(Integer.parseInt(res) -1, Lista);
						break;
					default:
						System.out.print("Não foi possível entender o input. \n");
				}
			}catch(Exception ex) {
				System.out.print("Não foi possível entender o input.\n");
			}
		}
	}


	// A função incluir insere um objeto car na Lista de carros, utilizando a variável aux para indicar
	// a ultima posição vazia da lista.
	private static Veiculo[] incluir(int aux, Veiculo[] Lista) {
		
		if(simulador.aux < 20) {
			Lista[aux] = new Veiculo();
			simulador.aux += 1;
			return Lista;
		}else {
			System.out.println("Não foi possível inserir o veículo pois a pista de 20 lugares está cheia.");
			return Lista;
		}
		
		
	}
	
	// O método remover remove um carro em dada posição da lista.
	// Exemplo: Se o ususário quiser remover o carro da primeira posição, o carro na posição Lista[0] será removido.
	// Isso é feito a partir da cópia de duas listas resultando em uma terceira lista que não possui o elemento em questão.
	private static Veiculo[] remover(int pos, Veiculo[] Lista) {
		
		try{
			if (Lista[pos-1] == null || pos > Lista.length ) { 
            System.out.println("Nenhuma remoção pode ser executada.");
            return Lista;
			} 
		
			Veiculo[] copia = new Veiculo[20];
			
			System.arraycopy(Lista, 0, copia, 0, pos-1);
			System.arraycopy(Lista, pos, copia, pos-1, 19-simulador.aux);
			
			simulador.aux -=1;
			Veiculo.qc -= 1;
	
			return copia;
		}catch(Exception ex) {
			System.out.println("Nenhuma remoção pode ser executada.");
			return Lista;
		}
		
	}
	
	// A função abastecer aumenta o atributo combustível pelo valor passado na variável combustivel.
	private static Veiculo[] abastecer(int aux, Veiculo[] Lista, double combustivel) {

		Lista[aux].setCombustivel(Lista[aux].getCombustivel() + combustivel);
		return Lista;
		
	}
	
	// O método CalibrarUm muda o atributo isCalib de uma roda definida pela variável aux.
	// Neste caso estamos randomizando uma das 4 rodas do carro na posição aux.
	private static Veiculo[] calibrarUm(int aux, Veiculo[] Lista) {
		
		for(int i = 0; i < 4 ;i++) {
			Lista[aux].rodas[i].setCalib(true);
		}
		return Lista;
	}
	
	// A função calibraTodos muda o atributo isCalib de todas as rodas de um carro para True.
	private static Veiculo[] calibrarTodos(Veiculo[] Lista) {
		
		for(int i = 0; i < Veiculo.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].setCalib(true);
			}
		}
		
		return Lista;
		
	}
	
	// A função esvaziarUm muda o atributo isCalib para False de um pneu aleatório de um carro na posição Aux.
	// Neste trabalho esta função não foi utilizada nenhuma vez.
	private static Veiculo[] esvaziarUm(int aux, Veiculo[] Lista) {
		
		
		Random r = new Random();
		
		Lista[aux].rodas[r.nextInt(4)].setCalib(false);
		return Lista;
		
		
	}
	
	// A função esvaziarTodos muda o atriuto isCalib de todas as rodas de um carro na posição aux.
	// Esta função não é utilizada nenhuma vez durante o trabalho.
	private static Veiculo[] esvaziarTodos(int aux, Veiculo[] Lista) {
		
		for(int i = 0; i < Veiculo.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].setCalib(false);
			}
		}
		
		return Lista;
		
	}
	
	// A função movimentarUm aumenta dois espaços na string da matriz do desenho do carro, fazendo com que ele se mova
	// na pista. Para isso, primeiro verificamos se o carro existe dentro da lista. Depois verificamos se o ipva dele
	// foi pago. Ainda depois disso, verificamos se todos os pneus do respectivo carro estão calibrados e verificamos
	// se ele possui no mínimo 0.55 L de combustível para que possa andar.
	// Caso alguma dessas condições não for atendida, nada será feito e retornaremos a lista exatamente como ela estava
	// antes de entrar no método.
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
	
	// A função movimntarTodos utilizada função movimentarUm para que possamos executa-las em toda nossa lista de carros.
	// Para isso, checaremos a quantidade de carros utilizando a variável car.qc (Quantidade de Carros)
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
				+ "Seu combustivel é de :" + Lista[pos].getCombustivel() + "\n"
				+ "Suas rodas estão todas calibradas?:" + Lista[pos].isCalibAll()
				+ "\n------------------------------------------\n"
				);
		}catch (Exception ex){
			System.out.print("As informações não puderam ser informadas.");
		}
	}
	// A função ImprimirUm imprime a matriz do atributo desenho do carro em questão no console.
	// A matriz é composta de 4 linhas de string que compoem o desenho, por isso nosso loop terá 4 execuções.
	private static void imprimirUm(int aux, Veiculo[] Lista) {
		Lista[aux].imprimeDesenho();
	}
	
	// A função imprimirTodos utiliza a função imprimirUm para imprimir todos os carros no console.
	// Para isso, utilizamos a variável car.qc para saber quantos carros existem na lista.
	private static void imprimirTodos(Veiculo[] Lista) {
		
		
		for(int i = 0; i < Veiculo.qc; i++) {
			if(Lista[i] != null) {
				if(Lista[i].id != -1) {
					imprimirUm(i,Lista);
				}
			}
		}
		
		
	}
	
	// A função pagarIpva modifica o atributo ipva para True de um carro definido pela variável id.
	private static Veiculo[] pagarIpva (int id, Veiculo[] Lista){
		Lista[id].setIpva(true);
		return Lista;
	}
	
	// A função gravar cria um arquivo Carros.dat e grava os elementos do nosso array de carros neste arquivo.
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
	
	// A função ler abre um arquivo Carros.dat e copia seu conteúdo para um array de carros chamado Lista. 
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
	
	// A função sair é utilizada para fechar um objeto do tipo scanner que utilizamos para ler os inputs do usuário.
	private static void sair(Scanner s) {
		s.close();
	}
}
