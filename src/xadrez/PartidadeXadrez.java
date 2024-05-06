package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  private boolean xeque;
  
  
  // Tinha feito essa lista como pecaDeXadrez mas achou melhor usar a classe mais
  // generica peca
  private List<Peca> pecasNoTabuleiro = new ArrayList<>();	
  private List<Peca> pecasCapturadas  = new ArrayList<>();	
			  
  public PartidadeXadrez() {
	  tabuleiro = new Tabuleiro(8,8);
	  turno = 1;
	  jogadorAtual = Cor.BRANCA;
	  setupInicial();
	  // poderia inicializar o xeque aqui , mas qualquer variavel boolean ele ja inicializa como falso
	  // entao nao precisa
	  // xeque =  false;
			  
  }
  
 public int getTurno() {
	return turno;
}

public Cor getJogadorAtual() {
	return jogadorAtual;
}

public boolean getXeque() {
	return xeque;
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
	 
	 // depos de executar o movimento, testa se o movimento deixou o jogador em xeque
	 if (testeXeque(jogadorAtual)) {
		 desfazerMovimento(origem, destino, pecaCapturada);
		 throw new XadrezExcecao("Voce nao pode se colocar em xeque");
	 }
	 // agora testa se o oponente esta em xeque
	 xeque = (testeXeque(oponente(jogadorAtual))) ? true : false; 
	 
	 
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
	 
     // quando mover uma peca tem de verificar se esse movimento causo uma captura de peça
	 // nesse caso tem de retirar do tabuleiro e colocar na lista de pecas capturadas
	 if (pecaCapturada != null) {
		 pecasNoTabuleiro.remove(pecaCapturada);
		 pecasCapturadas.add(pecaCapturada);
	 }
	 
	 return pecaCapturada;	 
 }
 
 private void desfazerMovimento( Posicao origem, Posicao destino, Peca pecaCapturada) {
	 // vai desfazer todas as opreacoes do metodo movimenta
	 Peca p = tabuleiro.removePeca(destino);
	 tabuleiro.PosicionaPeca(p, origem);
	 
	 if (pecaCapturada != null) {
		 tabuleiro.PosicionaPeca(pecaCapturada,  destino);
	    pecasCapturadas.remove(pecaCapturada );
	 pecasNoTabuleiro.add(pecaCapturada);
	 }
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

 private Cor oponente(Cor cor) {
	// devolve o oponente de uma cor .se a cor for branca devovle preto e vice versa

	 return (cor == Cor.BRANCA) ? Cor.PRETA  : Cor.BRANCA;
 }
 
 
 private PecadeXadrez rei(Cor cor) {
   // varre o tabuleiro procurando o rei da cor passada por parametro
   // usado para verificar o xeque
 
   // faz a filtragem de forma que toda peça x, a cor dessa peca seja igual a cor que foi passada como parametro
   //o problema é que a lista e de peca, e peca nao tem cor, pecadeXadrez e que tem cor, entao tem de fazer um downcast 
 
   List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecadeXadrez)x).getCor() == cor).collect(Collectors.toList());
   //para peca p na lista...
  for (Peca p : list) {
	  // Se a peca p for uma instancia de rei...
	  if ( p instanceof Rei ) {
		  // retorna a peca p mas fazendo um downcasting para pecadeXadrez
		  return (PecadeXadrez)p;
		  
	  }
  } // o erro abaixo nunca e pra acontecer, por isso ela nao esta sendo tratada no programa prncipal. E so pq o java deu falta
    // do tratamento de excecao aqui
  throw new IllegalStateException(" Nao existe nenhhum rei de cor "+ cor + " no tabuleiro");
 }

 private boolean testeXeque( Cor cor) {
     
     // percorre todas as pecas adversarias e ve , para cada uma das pecas se tem um movimento possivel que caia na posicao do rei
     Posicao posicaodoRei = rei(cor).getPosicaoXadrez().paraPosicao();
     List<Peca> pecasOponentes =  pecasNoTabuleiro.stream().filter(x -> ((PecadeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
     //para cada peca p na lista de pecas oponentes...
     for (Peca p : pecasOponentes) {
    	 // testa se tem movimento possivel da peca p que leve ate a posicao do rei
    	 // monta uma matriz de movimentos possiveis da peca
    	 boolean[][] mat = p.movimentosPossiveis();
    	 // se nessa matriz a posicao for igual a posicao do rei(true) entao o rei esta em cheque
    	 if (mat[posicaodoRei.getlinha()][posicaodoRei.getcoluna()]) {
    		 return true;
    	 }
     }
     // se fechar o for entao o rei nao esta em cheque
     return false;
     
     
 }
 private void PosicionaNovaPeca(char coluna, int linha, PecadeXadrez peca) {
		tabuleiro.PosicionaPeca(peca, new  PosicaoXadrez(coluna, linha).paraPosicao());
		
		// sem q instanciar uma nova peca tem de colocar essa peca na lista de pecas no tabuleito
		pecasNoTabuleiro.add(peca);
		
		
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