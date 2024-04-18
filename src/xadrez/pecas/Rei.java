package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecadeXadrez;

public class Rei  extends PecadeXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub	
		
	}
	
	// Basicamente ao acessar a torre define que vai colocar um T na posicao
			// definida no tabuleiro
			@Override
			public String toString() {
				return "R";
			}
  
}
