package xadrez;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

// essa classe e o coracao do jogo, e enla que teremos as regras do jogo
public class PartidadeXadrez {
	
  private Tabuleiro tabuleiro;
  
  public PartidadeXadrez() {
	  tabuleiro = new Tabuleiro(8,8);
	  setupInicial();
	  
  }
 public  PecadeXadrez[][] getPecas() {
	 
	 PecadeXadrez[][] mat = new PecadeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	 //vai percorrer o tabuleiro e para cada peca no tabuleiro vai fazer um downcast) para PecadeXadrez
	 for (int i=0; i<tabuleiro.getLinhas() ; i++) {
		 
		 for (int j=0; j<tabuleiro.getColunas(); j++) 
		mat[i][j] = (PecadeXadrez) tabuleiro.peca(i,j);
	 }
	 return mat;
	 
	 // 
 }
 
     private void setupInicial() {
    	 tabuleiro.PosicionaPeca(new Torre(tabuleiro, Cor.BRANCA), new Posicao(2,1));
    	 tabuleiro.PosicionaPeca(new Rei(tabuleiro, Cor.PRETA), new Posicao(0,4));
    	 tabuleiro.PosicionaPeca(new Rei(tabuleiro, Cor.BRANCA), new Posicao(7,4));
    	    
     }
}