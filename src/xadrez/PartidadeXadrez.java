package xadrez;

import tabuleiroJogo.Peca;
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
 
 public PecadeXadrez executaMovimentodeXadrez( PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
	 Posicao origem = posicaoOrigem.paraPosicao();
	 Posicao destino = posicaoDestino.paraPosicao();
	 // antes de fazer tem de validar se na posicao de origem tinha uma peca
	 validaPosicaoOrigem(origem);
	 Peca pecaCapturada = movimenta(origem, destino);
	 return (PecadeXadrez) pecaCapturada; // teve de fazer um downcasting porque a peca capturada era do tipo
	                                      // peca
	 
 }
 
 private Peca movimenta(Posicao origem, Posicao destino) {
	 // retira a peca da posicao de origem
	 Peca p = tabuleiro.removePeca(origem);
	 // remove a peca que etaria na posicao de destino, ou a peca a ser " comida"	 
	 Peca pecaCapturada = tabuleiro.removePeca(destino);
	 //coloca a peca da origem no destino
	 tabuleiro.PosicionaPeca(p, destino);
	 return pecaCapturada;
	 
	 
	 
 }
 
 private void validaPosicaoOrigem(Posicao posicao) { 
	 if (! tabuleiro.jaTemUmaPeca(posicao))
		throw new XadrezExcecao("Nao existe nenhuma peça na posicao de origem");
	 
 }
 private void PosicionaNovaPeca(char coluna, int linha, PecadeXadrez peca) {
		tabuleiro.PosicionaPeca(peca, new  PosicaoXadrez(coluna, linha).paraPosicao());
       }
 
     private void setupInicial() {
    	    PosicionaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
    	    PosicionaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
            PosicionaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
            PosicionaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
            PosicionaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
            PosicionaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

            PosicionaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
            PosicionaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
            PosicionaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
            PosicionaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
            PosicionaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
            PosicionaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
    	 
    	 
     }
     

     
}