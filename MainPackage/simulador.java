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
		car[] Lista = new car[20];
		
		System.out.println("Deseja abrir um arquivo j� existente? Digite 1");
		
		int resposta = Integer.parseInt(scan.nextLine());
		
		if(resposta == 1) {
			try {
				Lista = ler();
				for(int i = 0; i < 20; i++) {
					if (Lista[i] != null) {
							car.qc +=1;
					}
				}
			}catch(Exception ex) {
				System.out.print(ex.toString());
			}
			
		}
		
		
		
		System.out.println("A corrida est� prestes a come�ar!! Confira abaixo");
		
		// Est� ser� nosso loop principal. � muito comum aplica��es e jogos rodarem em um loop.
		while(state) {
			
			// Primeiro, mostramos tudo que temos na nossa pista. O m�todo imprimir todos imprime todos os objetos carro da lista.
			imprimirTodos(Lista);
			
			// Esta � nossa mensagem para que possamos interagir com o usu�rio
			System.out.println("Deseja continuar? digite 0. Deseja sair do loop? Digite 1. Deseja remover um ve�culo? Digite 2. \nDeseja incluir um ve�culo? Digite 3."
					+ "Deseja salvar a corrida? Digita 4."
					+ "\n");
			
			// Utilizamos o m�todo nextLine que l� a pr�xima linha inserida no console pelo usu�rio.
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
						int ri = r.nextInt(car.qc);
						
						Lista = pagarIpva(ri,Lista);
						
						for(int i = 0; i < car.qc; i++) {
							Lista = abastecer(i, Lista, 0 + (1.0 -0)* r.nextDouble());
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
						System.out.println(ToString(ex));
					}
					
				case 4: 
					gravar(Lista);
					break;
			}
		}
	}
		
	private static char[] ToString(Exception ex) {
		// TODO Auto-generated method stub
		return null;
	}

	// A fun��o incluir insere um objeto car na Lista de carros, utilizando a vari�vel aux para indicar
	// a ultima posi��o vazia da lista.
	private static car[] incluir(int aux, car[] Lista) {
		
		if(simulador.aux < 20) {
			Lista[aux] = new car();
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
	private static car[] remover(int pos, car[] Lista) {
		
		try{
			if (Lista[pos-1] == null || pos > Lista.length ) { 
            System.out.println("Nenhuma remo��o pode ser executada.");
            return Lista;
			} 
		
			car[] copia = new car[20];
			
			System.arraycopy(Lista, 0, copia, 0, pos-1);
			System.arraycopy(Lista, pos, copia, pos-1, 19-simulador.aux);
			
			simulador.aux -=1;
			car.qc -= 1;
	
			return copia;
		}catch(Exception ex) {
			System.out.println("Nenhuma remo��o pode ser executada.");
			return Lista;
		}
		
	}
	
	// A fun��o abastecer aumenta o atributo combust�vel pelo valor passado na vari�vel combustivel.
	private static car[] abastecer(int aux, car[] Lista, double combustivel) {
		
		Lista[aux].combustivel += combustivel;
		return Lista;
		
	}
	
	// O m�todo CalibrarUm muda o atributo isCalib de uma roda definida pela vari�vel aux.
	// Neste caso estamos randomizando uma das 4 rodas do carro na posi��o aux.
	private static car[] calibrarUm(int aux, car[] Lista) {
		
		Random r = new Random();
		
		Lista[aux].rodas[r.nextInt(4)].calib = true;
		return Lista;
	}
	
	// A fun��o calibraTodos muda o atributo isCalib de todas as rodas de um carro para True.
	private static car[] calibrarTodos(car[] Lista) {
		
		for(int i = 0; i < car.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].calib = true;
			}
		}
		
		return Lista;
		
	}
	
	// A fun��o esvaziarUm muda o atributo isCalib para False de um pneu aleat�rio de um carro na posi��o Aux.
	// Neste trabalho esta fun��o n�o foi utilizada nenhuma vez.
	private static car[] esvaziarUm(int aux, car[] Lista) {
		
		
		Random r = new Random();
		
		Lista[aux].rodas[r.nextInt(4)].calib = false;
		return Lista;
		
		
	}
	
	// A fun��o esvaziarTodos muda o atriuto isCalib de todas as rodas de um carro na posi��o aux.
	// Esta fun��o n�o � utilizada nenhuma vez durante o trabalho.
	private static car[] esvaziarTodos(int aux, car[] Lista) {
		
		for(int i = 0; i < car.qc ; i++ ) {
			for(int k = 0; k < 4; k++) {
				Lista[i].rodas[k].calib = false;
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
	private static car[] movimentarUm(int pos, car[] Lista) {
		
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
		if(Lista[pos].combustivel >= 0.55){
			
			for(int i = 0; i < 4; i++) {
				Lista[pos].desenho[i] = "  " + Lista[pos].desenho[i];
			}
		}
		
		return Lista;
	}
	
	// A fun��o movimntarTodos utilizada fun��o movimentarUm para que possamos executa-las em toda nossa lista de carros.
	// Para isso, checaremos a quantidade de carros utilizando a vari�vel car.qc (Quantidade de Carros)
	private static car[] movimentarTodos(car[] Lista) {
		
		for(int i = 0; i < car.qc; i++) {
			Lista = movimentarUm(i, Lista);
		}
		return Lista;
	}
	
	
	// A fun��o ImprimirUm imprime a matriz do atributo desenho do carro em quest�o no console.
	// A matriz � composta de 4 linhas de string que compoem o desenho, por isso nosso loop ter� 4 execu��es.
	private static void imprimirUm(int aux, car[] Lista) {
		for(int i = 0; i <4; i ++) {
			System.out.print(Lista[aux].desenho[i]);
		}
	}
	
	// A fun��o imprimirTodos utiliza a fun��o imprimirUm para imprimir todos os carros no console.
	// Para isso, utilizamos a vari�vel car.qc para saber quantos carros existem na lista.
	private static void imprimirTodos(car[] Lista) {
		
		
		for(int i = 0; i < car.qc; i++) {
			if(Lista[i] != null) {
				if(Lista[i].id != -1) {
					imprimirUm(i, Lista);
				}
			}
		}
		
		
	}
	
	// A fun��o pagarIpva modifica o atributo ipva para True de um carro definido pela vari�vel id.
	private static car[] pagarIpva (int id, car[] Lista){
		Lista[id].ipva = true;
		return Lista;
	}
	
	// A fun��o gravar cria um arquivo Carros.dat e grava os elementos do nosso array de carros neste arquivo.
	private static void gravar(car[] Lista) {
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
	private static car[] ler() {
		File arquivo = new File("Carros.dat");
		car[] Lista;
		try {
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream oin = new ObjectInputStream(fis);
			
			Lista = (car[]) oin.readObject();
			
			oin.close();
			fis.close();
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
