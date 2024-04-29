package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecadeXadrez;

public class Torre extends PecadeXadrez {
    // defnir o cabecalho acima o proprio programa reclama da falta do construtor e ai
	// e so aceitar que ele crie o construtor que ja gera o codigo abaixo
	//o construtor vai simplesmnete passar a chamada para a super classe
	
	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}
	
	// Basicamente ao acessar a torre define que vai colocar um T na posicao
		// definida no tabuleiro
		@Override
		public String toString() {
			return "T";
		}
	
		@Override
		public boolean[][] movimentosPossiveis() {
			
			// cria uma matriz temporaria chamada mat quem tem as mesmas dmimensoes do tabuleiro
			// todas posicoes dela nesse moemnto retornam falso como se o rei estivesse preso
			
			
		    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];		                                                          
			return mat;
		}

}
