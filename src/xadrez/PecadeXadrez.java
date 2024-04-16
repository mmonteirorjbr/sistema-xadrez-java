package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Tabuleiro;

public class PecadeXadrez extends Peca {

	  private Cor cor;
	  
    // Ele e derivado do tabuleiro e la tem um construtor entao precisou gerar um
	public PecadeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	 // fez o getters and setters mas nao precisa do setter pq nao e pra mexer
	
	public Cor getCor() {
		return cor;
	}

	
	  
}
