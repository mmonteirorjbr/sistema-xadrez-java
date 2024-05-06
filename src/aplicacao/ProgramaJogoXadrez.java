package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidadeXadrez;
import xadrez.PecadeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezExcecao;


public class ProgramaJogoXadrez {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		// Instancía uma partida de xadrez e lista o tabuleiro na tela
		PartidadeXadrez partidadeXadrez =  new PartidadeXadrez();
		
		// declara uma  lista de pecas capturadas aqui para poder ser publica em todo  o programa
		List<PecadeXadrez> capturadas  = new ArrayList<>();
		
		
		
		// classe UI - User Interface - dentro dessa classe cria um metodo pra imprimir o tabuleiro e ele recebe
		// a matriz de peças
		while (true ) {
			try {
				//limpa a tela antes de comecar
				UI.clearScreen(); 
				// Le a origem
	            UI.imprimePartida(partidadeXadrez, capturadas);
	            System.out.println();
	            System.out.print("Origem: " );
	            PosicaoXadrez origem = UI.LeposicaoXadrez(sc); 
	            
	            // a ideia e ver quais sao os movimentos possiveis e imprimir a matriz com essas posicoes pintadas
	            // para o usuario escolher o proximo movimento
	            //define a matriz movimentospossiveis para imprimir
	            boolean[][] movimentosPossiveis = partidadeXadrez.movimentosPossiveis(origem);
	            UI.clearScreen();
	            UI.imprimeTabuleiro(partidadeXadrez.getPecas(), movimentosPossiveis);
	            
	            
	            //Le o destino
	            System.out.println();
	            System.out.print("Destino: " );
	            PosicaoXadrez destino = UI.LeposicaoXadrez(sc);
	            
	            PecadeXadrez pecaCapturada = partidadeXadrez.executaMovimentodeXadrez(origem,  destino);
	            // Se apos executar o movuimento de xadrez tiver como resultado uma peca capturada
	            // ela sera incluida na lista d epecas capturadas 
	            if (pecaCapturada != null ) { 
	            	capturadas.add(pecaCapturada);
	            	
	            }
			}   
		   catch (XadrezExcecao e) {
			   System.out.println(e.getMessage());
			   sc.nextLine();			   
		   }
		   catch (InputMismatchException e) {
			   System.out.println(e.getMessage());
			   sc.nextLine();				   
		   }

		}
	}

}
