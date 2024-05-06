package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidadeXadrez;
import xadrez.PecadeXadrez;
import xadrez.PosicaoXadrez;

// chama UI  de User Interface
public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// essa 1a parte ele pegou do endereco acima. e eu puxei pronta do github
	// sao codigos especiais das cores para imprimir no console
	

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	
	
	// O codigo abaixo implementa a limpeza de tela 
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
		public static void clearScreen() {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}	
	public static PosicaoXadrez LeposicaoXadrez(Scanner sc) {
	    try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}
	    catch (RuntimeException e ) {
	    	throw new InputMismatchException("Erro lendo posicao de xadrez. Valoreas validos sao de a1 ate h8.");
	    }
	}    
	public static void imprimePartida(PartidadeXadrez partidadeXadrez, List<PecadeXadrez> capturadas ) {   
		 // IMprimir a partida significa prmeiro imprimir o tabuleiro
		imprimeTabuleiro(partidadeXadrez.getPecas());	
		System.out.println();
		imprimePecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno : "+ partidadeXadrez.getTurno());
		System.out.println(" Esperando Jogador: " + partidadeXadrez.getJogadorAtual()   );
		if (partidadeXadrez.getXeque()) {
			System.out.println("XEQUE!!!");
					
		}
			
	}
	
	public static void imprimeTabuleiro(PecadeXadrez[][] pecas) {
		// imprime o tabuleiro
		// ele usa pecas.length em linhas e colunas pq esta considerando que a matriz e
		// quadrada

		for (int i = 0; i < pecas.length; i++) {

			// Comeca o vetor com 0 e o tabuleiro comeca com o 8 vai fazer 8-i e vir em
			// ordem decrescente
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j],false);
			}
			System.out.println(); // troca de linha na matriz

		}
		System.out.println("  a b c d e f g h ");

	}

   // sao duas versoes de imprime o tabuleiro. Uma que so mostra o tabuleiro e as peças que esta cima
	// e essa que mostra tudo e pinta as posicoes onde o movimento e possivel
	
	public static void imprimeTabuleiro(PecadeXadrez[][] pecas, boolean[][] movimentosPossiveis) {

		for (int i = 0; i < pecas.length; i++) {

			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j],movimentosPossiveis[i][j]); // como e so pra imprmir o tabuleiro sem os movimentos possiveis
			}                                   // ele manda falso direto  
			System.out.println(); // troca de linha na matriz

		}
		System.out.println("  a b c d e f g h ");

	}

	
	
	
	// metodo pra imprimir uma peça
	// ele vai usar o terminal do git bash que tem fundo preto entao nao da pra usar cor preta nas peças
	// ele vai simbolizar o preto usando o amarelo
	
	private static void imprimePeca(PecadeXadrez peca, boolean fundo) {
		if (fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);			
		}
		if (peca == null) {   // usa O ANSI_RESET para limpar ainformacao da cor
			System.out.print("-"+ ANSI_RESET );
		} else {
			   if (peca.getCor() == Cor.BRANCA) {
	                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
	            }
	            else {
	                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
	            }
		}
		System.out.print(" "); // imprime um espaco em branco para as pecas nao ficarem grudadas

	}

	private static void imprimePecasCapturadas(List<PecadeXadrez> capturadas ) {
		// faz um filtro na lista pegando aqueles que tem a cor branca
		List<PecadeXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCA ).collect(Collectors.toList());
		List<PecadeXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETA ).collect(Collectors.toList()); 
		System.out.println("Pecas Capturadas:");		
		System.out.print("Brancas:");
		// troca a cor para cor branca  para listar as pecas com a cor branca
		System.out.print(ANSI_WHITE);
		// Lista o array inteiro de pecas brancas capturadas
		System.out.println(Arrays.toString(brancas.toArray()));
		// Reseta a cor da impressao
		System.out.print(ANSI_RESET);
	
		System.out.print("Pretas:");
		// troca a cor para cor preta  para listar as pecas com a cor preta(que aqui e amarela) 
		System.out.print(ANSI_YELLOW);
		// Lista o array inteiro de pecas pretas capturadas
		System.out.println(Arrays.toString(pretas.toArray()));
		// Reseta a cor da impressao
		System.out.print(ANSI_RESET);
	
		
	}
}

// parei no video 18 aos 03:50Hs
