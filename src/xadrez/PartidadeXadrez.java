package xadrez;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

// essa classe e o coracao do jogo, e enla que teremos as regras do jogo
public class PartidadeXadrez {
	
  private int turno;
  private Cor jogadorAtual;
  private Tabuleiro tabuleiro;
  
  public PartidadeXadrez() {
	  tabuleiro = new Tabuleiro(8,8);
	  turno = 1;
	  jogadorAtual = Cor.BRANCA;
	  setupInicial();
	  
  }
  
 public int getTurno() {
	return turno;
}

public Cor getJogadorAtual() {
	return jogadorAtual;
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
 
 
 public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
	 Posicao posicao = posicaoOrigem.paraPosicao();
	 validaPosicaoOrigem(posicao);
	 // retorna os movimentos possiveis dessa posicao
	 return tabuleiro.peca(posicao).movimentosPossiveis();
			 
      }
 
 public PecadeXadrez executaMovimentodeXadrez( PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
	 Posicao origem = posicaoOrigem.paraPosicao();
	 Posicao destino = posicaoDestino.paraPosicao();
	 // antes de fazer tem de validar se na posicao de origem tinha uma peca
	 validaPosicaoOrigem(origem);
	 validaPosicaoDestino(origem,destino);
	 Peca pecaCapturada = movimenta(origem, destino);
	 // troca de jogador
	 trocaTurno();
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
	 if (! tabuleiro.jaTemUmaPeca(posicao)) {
		throw new XadrezExcecao("Nao existe nenhuma peça na posicao de origem");
        }
	 // ai ai, vamos la. Estou pegando a cor da peca, mas ela e ma propriedade de peca de xadrez, peca é mais 
	 // generica entao tem de fazer o downcast para peca de xadrez para poder funcionar
	 // nO caso verifica a cor da peca e se ela for diferente do jogador atual ´pe uma peca do adversario
	 // entao nao pode movê-la
	  if (jogadorAtual != ((PecadeXadrez) tabuleiro.peca(posicao)).getCor()) {
		  throw new XadrezExcecao(" A peça escolhida nao e sua");
	  }
	  
      if (! tabuleiro.peca(posicao).existeUmMovimentoPossivel()) {
    	  throw new XadrezExcecao("Nao existe nenhum movimento possivel para a peca escolhida");
          } 	 
 }

 
 private void validaPosicaoDestino(Posicao origem, Posicao destino) {
	 if ( ! tabuleiro.peca(origem).movimentoPossivel(destino)) {
		throw new  XadrezExcecao("A peça escolhida nao pode se mover para a posicao de destino");
	 }
 }
 
 private void  trocaTurno() {
	 
      turno++;
      // o comando seguinte e um baita de um if. ele chamou de expressao condicional ternaria que funciona assim
      // Se o jogador atual for igual a Cor branca  entao(?) ele vai ser o de cor preta caso contratrio(:) ele vai ser
      // o de cor branca 
      jogadorAtual = (jogadorAtual ==Cor.BRANCA ? Cor.PRETA : Cor.BRANCA);
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