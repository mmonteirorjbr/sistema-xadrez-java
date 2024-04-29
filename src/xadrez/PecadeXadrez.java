package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Tabuleiro;

// peca de xadrez nao era abstrata mas como ela extende Peca que e abstrata entao
// ela tambem precisa ser abstrata
public abstract class PecadeXadrez extends Peca {

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
