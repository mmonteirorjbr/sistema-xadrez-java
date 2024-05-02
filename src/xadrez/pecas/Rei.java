package xadrez.pecas;

import tabuleiroJogo.Posicao;
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

			// verifica se o rei pode se mover para a posicao
			private boolean podeMover(Posicao posicao) {
			     PecadeXadrez p  = (PecadeXadrez) getTabuleiro().peca(posicao);
			     // verifica se a peca e nula ou  tambem se ele e a uma peca adversaria comparando
			     // a cor da peca adversaria com a cor do rei
			     return p == null ||  p.getCor() != getCor();
			}
			@Override
			public boolean[][] movimentosPossiveis() {
				
				// cria uma matriz temporaria chamada mat quem tem as mesmas dmimensoes do tabuleiro
				// todas posicoes dela nesse moemnto retornam falso como se o rei estivesse preso				
			boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
			
			Posicao p = new Posicao(0,0);
					
					
			// acima
					p.setValores(posicao.getlinha() -1, posicao.getcoluna());
			        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			        	mat[p.getlinha()][p.getcoluna()] = true;
			        }
			// abaixo
					p.setValores(posicao.getlinha() +1, posicao.getcoluna());
			        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			        	mat[p.getlinha()][p.getcoluna()] = true;
			        }
	
        	// esquerda
					p.setValores(posicao.getlinha() , posicao.getcoluna()-1);
			        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			        	mat[p.getlinha()][p.getcoluna()] = true;
			        }
					
        	// direita 
				p.setValores(posicao.getlinha() , posicao.getcoluna()+1);
		        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
		        	mat[p.getlinha()][p.getcoluna()] = true;
		             }  
	        // o rei anda em todas as direcoes entao faz  o movimento em diagnonal tambem
	        	
	        //Noroeste
	        // linha -1 e coluna -1	
			p.setValores(posicao.getlinha()-1,  posicao.getcoluna()-1);
	        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
	        	mat[p.getlinha()][p.getcoluna()] = true;
	        }	
	        
	      //Nordeste
	        // linha -1 e coluna +1	
			p.setValores(posicao.getlinha()-1,  posicao.getcoluna()+1);
	        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
	        	mat[p.getlinha()][p.getcoluna()] = true;
	        }

	        
	      //Sudoeste 
	        // linha +1 e coluna -1	
			p.setValores(posicao.getlinha()+1,  posicao.getcoluna()-1);
	        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
	        	mat[p.getlinha()][p.getcoluna()] = true;
	        }

	      //Sudeste 
	        // linha +1 e coluna +1	
			p.setValores(posicao.getlinha()+1,  posicao.getcoluna()+1);
	        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
	        	mat[p.getlinha()][p.getcoluna()] = true;
	        }
			return mat;
			}
  
}
