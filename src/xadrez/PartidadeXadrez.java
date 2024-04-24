package xadrez;


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
 
     private void PosicionaNovaPeca(char coluna, int linha, PecadeXadrez peca) {
    	 tabuleiro.PosicionaPeca(peca, new XadrezPosicao(coluna, linha).paraPosicao());
     }
     private void setupInicial() {
    	 PosicionaNovaPeca('b' ,6 , new Torre(tabuleiro, Cor.BRANCA));
    	 PosicionaNovaPeca('e',8, new Rei(tabuleiro, Cor.BRANCA));
    	 PosicionaNovaPeca('e', 1 ,new Rei(tabuleiro, Cor.BRANCA));    	    
     }
     
     // parei aqui - comecar o video 8
     
}