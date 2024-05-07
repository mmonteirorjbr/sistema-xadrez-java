package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

// peca de xadrez nao era abstrata mas como ela extende Peca que e abstrata entao
// ela tambem precisa ser abstrata
public abstract class PecadeXadrez extends Peca {

	  private Cor cor;
	  private int contaMovimentos; // Ele ja inicializa com 0
	  
    // Ele e derivado do tabuleiro e la tem um construtor entao precisou gerar um
	public PecadeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	 // fez o getters and setters mas nao precisa do setter pq nao e pra mexer
	
	public Cor getCor() {
		return cor;
	}

	public int getContaMovimentos() {
		return contaMovimentos;
	}
	public void incrementaContaMovimentos() {
		contaMovimentos++;
	}
	
	public void decrementaContaMovimentos() {
		contaMovimentos--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		// converter posicao para posicaoxadrez
		return PosicaoXadrez.daPosicao(posicao);
	}

	 protected boolean existePecaAdversaria(Posicao posicao) {
	
	  // a variavel p que tem o formato Pecadexadrez recebe a posicao do tabuleiro que
	 // e convertida para o formato PecadeXadrez.
	  PecadeXadrez p = (PecadeXadrez) getTabuleiro().peca(posicao);
	 // agora ve se a peca e uma peca adversaria. Nesse caso a peça sera nula ou
	 // 
	 return p !=null && p.getCor() != cor;
	 }
	 
	 
}
