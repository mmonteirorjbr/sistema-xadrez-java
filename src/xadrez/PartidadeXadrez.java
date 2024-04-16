package xadrez;

import tabuleiroJogo.Tabuleiro;

// essa classe e o coracao do jogo, e enla que teremos as regras do jogo
public class PartidadeXadrez {
	
  private Tabuleiro tabuleiro;
  
  public PartidadeXadrez() {
	  tabuleiro = new Tabuleiro(8,8);
	  
  }
 public  PecadeXadrez[][] getPecas() {
	 
	 PecadeXadrez[][] mat = new PecadeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	 //vai percorrer o tabuleiro e para cada peca no tabuleiro vai fazer um downcast) para PecadeXadrez
	 
	 for (int i=0; i<tabuleiro.getLinhas(); i++) {
		 for (int j=0; j<tabuleiro.getColunas(); i++)
		mat[i][j] = (PecadeXadrez) tabuleiro.peca(i,j);
	 }
	 return mat;
 }
}