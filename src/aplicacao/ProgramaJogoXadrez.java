package aplicacao;

import java.util.Scanner;

import xadrez.PartidadeXadrez;
import xadrez.PecadeXadrez;
import xadrez.PosicaoXadrez;


public class ProgramaJogoXadrez {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		// Instancía uma partida de xadrez e lista o tabuleiro na tela
		PartidadeXadrez partidadeXadrez =  new PartidadeXadrez();
		
		// classe UI - User Interface - dentro dessa classe cria um metodo pra imprimir o tabuleiro e ele recebe
		// a matriz de peças
		while (true ) {
			// Le a origem
            UI.imprimeTabuleiro(partidadeXadrez.getPecas());
            System.out.println();
            System.out.print("Origem: " );
            PosicaoXadrez origem = UI.LeposicaoXadrez(sc);
            
            //Le o destino
            System.out.println();
            System.out.print("Destino: " );
            PosicaoXadrez destino = UI.LeposicaoXadrez(sc);
            
            PecadeXadrez pecaCapturada = partidadeXadrez.executaMovimentodeXadrez(origem,  destino);
		}   
		// parei aqui, video 11  00:17:00 mins problema ao fazer o commit no git hub.
		// acertar antes de continuar
		
	}

}
