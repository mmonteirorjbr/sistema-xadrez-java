package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidadeXadrez;
import xadrez.PecadeXadrez;

public class Rei  extends PecadeXadrez{
	
	private PartidadeXadrez partidadeXadrez;
	
	// essa matriz foi declarada no metodo  Movimentos possiveis mas eu quis criar 
	// o metodo para fazer os movimentos e entao ela precisava ser declarada de forma mais
	// global. Ai ela passou pra ca, para o comeco da classe
	// fiz o mesmo com a variavel p 
		
	
	// cria uma matriz temporaria chamada mat quem tem as mesmas dmimensoes do tabuleiro
	// todas posicoes dela nesse moemnto retornam falso como se o rei estivesse preso
	
	
    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    
	Posicao p = new Posicao(0,0);
	
	public Rei(Tabuleiro tabuleiro, Cor cor , PartidadeXadrez partidadeXadrez) {
		super(tabuleiro, cor);
		this.partidadeXadrez = partidadeXadrez;
		
		// TODO Auto-generated constructor stub	
		
	}
	
	public void executaMovimento( int moveLinha, int moveColuna) {
		p.setValores(posicao.getlinha() + moveLinha, posicao.getcoluna()+moveColuna);
        if (getTabuleiro().existePosicao(p) && podeMover(p)) {
        	mat[p.getlinha()][p.getcoluna()] = true;
        }
	}
	
	// Basicamente ao acessar a torre define que vai colocar um T na posicao
			// definida no tabuleiro
			@Override
			public String toString() {
				return "R";
			}

			// verifica se o rei pode se mover para a posicao
			private boolean podeMover(Posicao posicao) {
			     PecadeXadrez p  = (PecadeXadrez)getTabuleiro().peca(posicao);
			     // verifica se a peca e nula ou  tambem se ele e a uma peca adversaria comparando
			     // a cor da peca adversaria com a cor do rei
			     return p == null ||  p.getCor() != getCor();
			}
			
			// nesse momento estamos implementando um movimento chamado Roque que eu nao sei 
			// explicar direito, vou tentar fazer isso nas anotacoes			
			private boolean testeRoqueNaTorre(Posicao posicao) {
				// pega a peca da posicao atual do tabuleiro
				PecadeXadrez p = (PecadeXadrez)getTabuleiro().peca(posicao);
				
				// se a posicao da peca nao e nula, se a peca for uma torre , se a cor da peca for
				//igual a cor do rei e ve se ainda nao teve movimento dela 
				return p!=null && p instanceof Torre && p.getCor()== getCor()&& p.getContaMovimentos()==0;
				// se retornar verdadeiro significa que temos uam torre e ela esta apta para
				// a jogada Roque
				
				
			}
			
			@Override
			public boolean[][] movimentosPossiveis() {
				
		    		
					
			//O bloco abaixo foi feito no curso , acho que como uma implementacao didatica. mas eu 
			// quis testar isso sem repetir tanto o codigo incluindo um novo metido em que so mudava
			// a operacao de linha e coluna. vamos ver se consigo fazer direito
			
			/*
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
			  */
				
			/// substitui todo o bloco acima pelo seguinte:
				
				// acima
				executaMovimento(-1,0);
               // abaixo
			    executaMovimento(+1,0);
	        	// esquerda
			    executaMovimento(0,-1);
	        	// direita
			    executaMovimento(0,+1);
	        	// Noroeste
			    executaMovimento(-1,-1);
	        	// Nordeste 
			    executaMovimento(-1,+1);
	        	// Sudoeste 
			    executaMovimento(+1,-1);
	        	// Sudeste 
			    executaMovimento(+1,+1);

			
			  //#specialmove castling o que significa
			  // movimento especial do Roque
			    
			   // verifica se o movimento e zero e se a partida nao esta em xeque		    
			   if  (getContaMovimentos()== 0  && !partidadeXadrez.getXeque()) {
			        // verifica pelo lado do "Roque pequeno"(que tem 2 posicoes), ou "Roque pelo lado do rei"
				   // #specialmove castling Kingside rook
				   // #movimento espcial de Roque , rei pelo lado da torre(Roque pequeno)
				   
				   // a posicao da torre estara 3 colunas a direita do rei
				   Posicao posT1 = new Posicao(posicao.getlinha(), posicao.getcoluna()+3);
				   if (testeRoqueNaTorre(posT1)) {
					   // esse p1 sera a 1a casa da direita e o p2 a 2a casa
					   Posicao p1 = new Posicao(posicao.getlinha(), posicao.getcoluna()+1);
					   Posicao p2 = new Posicao(posicao.getlinha(), posicao.getcoluna()+2);
					   // verifica se as posicoes estao vazias
					   if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					      mat[posicao.getlinha()][posicao.getcoluna()+2] =  true;
					      
					   }
			   
				   }
				    // verifica pelo lado do "Roque grande"(que tem 3 posicoes), ou "Roque pelo lado da rainha"
				   
				   // #specialmove castling Queenside rook
				   // #movimento espcial de Roque , rei pelo lado da rainha(Roque grande)
				   
				   // a posicao da rainha estara 4 colunas a esquerda do rei
				   Posicao posT2 = new Posicao(posicao.getlinha(), posicao.getcoluna() -4);
				   if (testeRoqueNaTorre(posT2)) {
					   // esse p1 sera a 1a casa da direita e o p2 a 2a casa
					   Posicao p1 = new Posicao(posicao.getlinha(), posicao.getcoluna()-1);
					   Posicao p2 = new Posicao(posicao.getlinha(), posicao.getcoluna()-2);
					   Posicao p3 = new Posicao(posicao.getlinha(), posicao.getcoluna()-3);
					   // verifica se as posicoes estao vazias
					   if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					      mat[posicao.getlinha()][posicao.getcoluna() -2] =  true;
					      
					   }
			   
				   }
			   }   
				   
				   
			    
			    return mat;
			}
            	
}