package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecadeXadrez;

public class Cavalo extends PecadeXadrez{
	
	
	
    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    
	Posicao p = new Posicao(0,0);
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub	
		
	}
	
	public void executaMovimento( int moveLinha, int moveColuna) {
		p.setValores(posicao.getlinha() + moveLinha, posicao.getcoluna()+moveColuna);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
        	mat[p.getlinha()][p.getcoluna()] = true;
        }
	}
	
		@Override
			public String toString() {
				return "C";
			}

			// verifica se o cavalo pode se mover para a posicao
			private boolean podeMover(Posicao posicao) {
			     PecadeXadrez p  = (PecadeXadrez) getTabuleiro().peca(posicao);
			     // verifica se a peca e nula ou  tambem se ele e a uma peca adversaria comparando
			     // a cor da peca adversaria com a cor do rei
			     return p == null ||  p.getCor() != getCor();
			}
			@Override
			public boolean[][] movimentosPossiveis() {
				
		   		
					
				
				
				executaMovimento(-1,-2);
               
			    executaMovimento (-2,-1);
	        	
			    executaMovimento(-2,+1);
	        	
			    executaMovimento(-1,+2);
	        	
			    executaMovimento(+1,+2);
	        	 
			    executaMovimento(+2,+1);
	        	 
			    executaMovimento(+2,-1);
	        	 
			    executaMovimento(+1,-2);

			    
			    return mat;
			}
 
}
