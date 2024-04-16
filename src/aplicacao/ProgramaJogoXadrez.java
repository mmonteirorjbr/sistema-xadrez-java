package aplicacao;

import xadrez.PartidadeXadrez;


public class ProgramaJogoXadrez {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Instancía uma partida de xadrez e lista o tabuleiro na tela
		PartidadeXadrez partidadeXadrez =  new PartidadeXadrez();
		
		// classe UI - User Interface - dentro dessa classe cria um metodo pra imprimir o tabuleiro e ele recebe
		// a matriz de peças
		
        UI.imprimeTabuleiro(partidadeXadrez.getPecas());
		
	}

}
