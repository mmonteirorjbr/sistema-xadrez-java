package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cor;
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

	public static PosicaoXadrez LeposicaoXadrez(Scanner sc) {
	    try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}
	    catch (RuntimeException e ) {
	    	throw new InputMismatchException("Erro lendo posicao de xadrez. Valoreas validos sao de a1 até h8.");
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
				imprimePeca(pecas[i][j]);
			}
			System.out.println(); // troca de linha na matriz

		}
		System.out.println("  a b c d e f g h ");

	}

	// metodo pra imprimir uma peça
	// ele vai usar o terminal do git bash que tem fundo preto entao nao da pra usar cor preta nas peças
	// ele vai simbolizar o preto usando o amarelo
	
	private static void imprimePeca(PecadeXadrez peca) {
		if (peca == null) {
			System.out.print("-");
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

}
