package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

// essa classe e o coracao do jogo, e enla que teremos as regras do jogo
public class PartidadeXadrez {
	
  private int turno;
  private Cor jogadorAtual;
  private Tabuleiro tabuleiro;
  private boolean xeque;
  private boolean xequeMate;
  private PecadeXadrez enPassantVulneravel;
  
  
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

public boolean getXequeMate() {
	return xequeMate;
}

public PecadeXadrez getEnPassantVulneravel() {
	return enPassantVulneravel;
	// essa propriedade ja comeca como falso entao nao precisa inicializa-la
	
	
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
	 
	 PecadeXadrez PecaMovida = (PecadeXadrez)tabuleiro.peca(destino);
	 
	 // agora testa se o oponente esta em xeque
	 xeque = (testeXeque(oponente(jogadorAtual))) ? true : false; 
	 
	 // se a jogada que eu fiz deixou o oponente em cheque mate o jogo vai ter de acabar
	 if (testeXequeMate(oponente(jogadorAtual))) {
		 xequeMate = true;	 
	 }
	 else  {
     	 //  troca de jogador
	     trocaTurno();
	 }
	 
	 // faz a verirficacao para a joga en PAssant. Se apeca for um peao que moveu dias casas
	 // ele sera marcado como  vulneravel
	 
	 // #movimento especial en passant
	 // se a peca movida foi um peao e a diferenca de linhas foi de 2, pra mais ou para menos 
	 if (PecaMovida instanceof Peao && (destino.getlinha() == origem.getlinha() - 2 || destino.getlinha() == origem.getlinha() + 2  )) {
		    enPassantVulneravel = PecaMovida;
	 }
	 else {
		    enPassantVulneravel = null;  // nao tem ninguem vulneravel
	 }
		 
	 
	 return (PecadeXadrez) pecaCapturada; // teve de fazer um downcasting porque a peca capturada era do tipo
	                                      // peca
	 
 }

 
 private Peca movimenta(Posicao origem, Posicao destino) {
	 // retira a peca da posicao de origem
	 PecadeXadrez p = (PecadeXadrez)tabuleiro.removePeca(origem);
	 
	 p.incrementaContaMovimentos();
	 // remove a peca que estaria na posicao de destino, ou a peca a ser " comida"	 
	 Peca pecaCapturada = tabuleiro.removePeca(destino);
	 //coloca a peca da origem no destino
	 tabuleiro.PosicionaPeca(p, destino);
	 
     // quando mover uma peca tem de verificar se esse movimento causo uma captura de peça
	 // nesse caso tem de retirar do tabuleiro e colocar na lista de pecas capturadas
	 if (pecaCapturada != null) {
		 pecasNoTabuleiro.remove(pecaCapturada);
		 pecasCapturadas.add(pecaCapturada);
	 }
	 // #movimento especial Roque da torre pelo lado do rei 
	 // #specialmove castling kingside rook - esse movimento e o Roque pequeno
	 
	 // pra saber se o movimento foi um roque pequeno
	 // ve se e uma isntancia do reio e ve se a posicao de destino  é a posicao de origem +2
	 if (p instanceof Rei && destino.getcoluna() == origem.getcoluna() + 2) {
		 // define uma posicao de origem e destino da torre 
		 Posicao origemT  = new Posicao(origem.getlinha(),origem.getcoluna()+3);
		 Posicao destinoT = new Posicao(origem.getlinha(),origem.getcoluna()+1);
		  // tira a torre da posicao de origem dela 
		 PecadeXadrez torre = (PecadeXadrez)tabuleiro.removePeca(origemT);
		 // e agora coloca ela na posicao de destino
		 tabuleiro.PosicionaPeca(torre, destinoT);
		 // como a torre foi movida aumenta a quantidade de movimentos dela
		 torre.incrementaContaMovimentos();
		 
	 }
	 //pra saber se o movimento foi um roque pequeno
	 // ve se e uma isntancia do reio e ve se a posicao de destino  é a posicao de origem -2
	 if (p instanceof Rei && destino.getcoluna() == origem.getcoluna() - 2) {
		 // define uma posicao de origem e destino da torre 
		 Posicao origemT  = new Posicao(origem.getlinha(),origem.getcoluna()-4);
		 Posicao destinoT = new Posicao(origem.getlinha(),origem.getcoluna() -1);
		  // tira a torre da posicao de origem dela 
		 PecadeXadrez torre = (PecadeXadrez)tabuleiro.removePeca(origemT);
		 // e agora coloca ela na posicao de destino
		 tabuleiro.PosicionaPeca(torre, destinoT);
		 // como a torre foi movida aumenta a quantidade de movimentos dela
		 torre.incrementaContaMovimentos();
		 
	 }
	 
	 /*o en passant foe a regra geral do xadrez,  normalmente quando uma peça para em cima de uma
	 peça adversaria ocorre captura, no caso do en passant a peça vai parar numa casa vazia e
	 ai vamos ter de fazer a captura manuamente
	 vou tentar desenhar
	 
	       x
	       P B
	       
	 ao inves do Branco ficar no lugar do preto ele vai pra posicao X
	 */
	 
	 // * movmento especial en passant
	 // esse movimento e para o pean entao testa se e peao
	 
	 // se o peao andou na diagonal e tem uma peca capturada significa se ele fez uma captura normal
	 // se ele andou na diagonal e nao tem peca capturada significa que foi um en passant porque
	 // o peao normalmente nao pode andar na diagonal 
	 
	 
	if (p instanceof Peao) {
	
		if (origem.getcoluna() != destino.getcoluna() && pecaCapturada == null) {
		   // olhando a posicao. se for peca branca ele sobe entao a peca a ser capturada esta em baixo
		  //	 da posicao vazia. se for a preta ele desce entao a peca a ser capturada esta acima da posicao
		  // vazia
		Posicao  posicaodoPeao;
		if (p.getCor() == Cor.BRANCA) {
			posicaodoPeao = new Posicao(destino.getlinha()+1, destino.getcoluna() );					
		}
		else {
			posicaodoPeao = new Posicao(destino.getlinha()-1, destino.getcoluna() )	;				
		}
		pecaCapturada = tabuleiro.removePeca(posicaodoPeao);
		pecasCapturadas.add(pecaCapturada);
		pecasNoTabuleiro.remove(pecaCapturada);
	    }
	}		 
	 
	 
	 return pecaCapturada;
     }
 
 
 private void desfazerMovimento( Posicao origem, Posicao destino, Peca pecaCapturada) {
	 // vai desfazer todas as opreacoes do metodo movimenta
	 PecadeXadrez p =(PecadeXadrez)tabuleiro.removePeca(destino);
	 p.decrementaContaMovimentos();
	 
	 tabuleiro.PosicionaPeca(p, origem);
	 
	 if (pecaCapturada != null) {
		 tabuleiro.PosicionaPeca(pecaCapturada,  destino);
	    pecasCapturadas.remove(pecaCapturada );
	 pecasNoTabuleiro.add(pecaCapturada);
	 }
	 
	
	 
	 //desfaz o processo de movimento do Roque
	 // pra saber se o movimento foi um roque pequeno
	 // ve se e uma isntancia do reio e ve se a posicao de destino  é a posicao de origem +2
	 if (p instanceof Rei && destino.getcoluna() == origem.getcoluna() + 2) {
		 // define uma posicao de origem e destino da torre 
		 Posicao origemT  = new Posicao(origem.getlinha(),origem.getcoluna()+3);
		 Posicao destinoT = new Posicao(origem.getlinha(),origem.getcoluna()+1);
		  // como ja tinha movido a torre vai tira-la da posicao de destino dela  
		 PecadeXadrez torre = (PecadeXadrez)tabuleiro.removePeca(destinoT);
		 // e agora coloca ela na posicao de destino
		 tabuleiro.PosicionaPeca(torre, destinoT);
		 // como a torre foi movida aumenta a quantidade de movimentos dela
		 torre.decrementaContaMovimentos();
		 
	 }
	 //pra saber se o movimento foi um roque grande
	 // ve se e uma isntancia do rei e ve se a posicao de destino  é a posicao de origem -2
	 if (p instanceof Rei && destino.getcoluna() == origem.getcoluna() - 2) {
		 // define uma posicao de origem e destino da torre 
		 Posicao origemT  = new Posicao(origem.getlinha(),origem.getcoluna()-4);
		 Posicao destinoT = new Posicao(origem.getlinha(),origem.getcoluna() -1);
		  // tira a torre da posicao de destino dela 
		 PecadeXadrez torre = (PecadeXadrez)tabuleiro.removePeca(destinoT);
		 // e agora coloca ela na posicao de origem 
		 tabuleiro.PosicionaPeca(torre, origemT);
		 // como a torre foi movida aumenta a quantidade de movimentos dela
		 torre.incrementaContaMovimentos();
		 
	 }
	 
	 // * movmento especial en passant
	 // esse movimento e para o pean entao testa se e peao
	 
	 // se o peao andou na diagonal e tem uma peca capturada significa se ele fez uma captura normal
	 // se ele andou na diagonal e a peca capturada e a que estava vulneravel significa que foi um en passant porque
	 // o peao normalmente nao pode andar na diagonal 
	 
	// ao desfazer os movimentos do enpassant, a peca capturadaacaba voltando para
	// a posicao de cima e nao apra a posicao ao lado da peca branca, entao ele vai pra la
	 // e manualmente a gente traz ela pra posicao ao lado 
	if (p instanceof Peao) {
	    PecadeXadrez peao = (PecadeXadrez)tabuleiro.removePeca(destino); // tira a peca da posicao de destino
		if (origem.getcoluna() != destino.getcoluna() && pecaCapturada == enPassantVulneravel) {
		   // olhando a posicao. se for peca branca ele sobe entao a peca a ser capturada esta em baixo
		  //	 da posicao vazia. se for a preta ele desce entao a peca a ser capturada esta acima da posicao
		  // vazia. Aqui como as linhas onde o movimento en  passant pode acontecer sao fixas ele
			//decidiu colocar logo o numero da linha ao invez de subir ou descer uma linha na operacao
		Posicao  posicaodoPeao;
		if (p.getCor() == Cor.BRANCA) {
			posicaodoPeao = new Posicao(3, destino.getcoluna() );					
		}
		else {
			posicaodoPeao = new Posicao(4, destino.getcoluna() )	;				
		}
		tabuleiro.PosicionaPeca(peao, posicaodoPeao);
		
		}
	}		 
 }
 
 private void validaPosicaoOrigem(Posicao posicao) { 
	 if (! tabuleiro.jaTemUmaPeca(posicao)) {
		throw new XadrezExcecao("Nao existe nenhuma peça na posicao de origem");
        }
	 // ai ai, vamos la. Estou pegando a cor da peca, mas ela e uma propriedade de peca de xadrez, peca é mais 
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
 
 private boolean testeXequeMate( Cor cor) {
	 // pra ser xeque mate ja tem de ser xeque
	 
	 if (!testeXeque(cor)) {
		 return false;
	 }
	 List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecadeXadrez)x).getCor() == cor).collect(Collectors.toList());
	 for (Peca p : list) {
		 // para cada peca pega os movimentos possiveis dela
		 // e vai percorrendo as posicoes do tabuleiro
		 
		 boolean[][] mat =  p.movimentosPossiveis();
		 for (int i=0; i < tabuleiro.getLinhas();i++ ) 
		      for (int j=0; j< tabuleiro.getColunas(); j++) {
		    	  // ver seo movimento possivel tira a peça do xeque
		    	  if  (mat[i][j]) {
		    		  //nao da pra simplesmente puxar a posicao pq ela e de outro metodo entao tem de pegar pelo get e fazer o downcast		    		  
		    		  //Posicao origem = p.posicao;
		    		  Posicao origem = ((PecadeXadrez)p).getPosicaoXadrez().paraPosicao();
		    		  Posicao destino = new Posicao(i,j);
		    		  // agora movimenta a peca da origem para o destino
		    		  Peca pecaCapturada = movimenta(origem, destino);
		    		  boolean testeXeque = testeXeque(cor);
		    		  // ele apenas testa, no final tem de desfazer o movimento e voltar tudo como estava		    		  
		    		  desfazerMovimento(origem, destino, pecaCapturada);
		    		  // se o movimento nao esta em cheque significa que o movimento tirou o rei do cheque
		    		  // entao retorna false porque nao esta em cheque mate
		    		  if (!testeXeque) {
		    			  return false;
		    			  
		    		  }
		    	  }
		      }
	 
	 }
	 return true;
 }
 
 private void PosicionaNovaPeca(char coluna, int linha, PecadeXadrez peca) {
		tabuleiro.PosicionaPeca(peca, new  PosicaoXadrez(coluna, linha).paraPosicao());
		
		// sem q instanciar uma nova peca tem de colocar essa peca na lista de pecas no tabuleiro
		pecasNoTabuleiro.add(peca);
		   	
		
       }
 
     private void setupInicial() {
    	    // Tabuleiro inicial
    	 
    	   /* 
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
    	 */
    	 
         PosicionaNovaPeca('a', 1, new Torre(tabuleiro,  Cor.BRANCA));
         PosicionaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
         PosicionaNovaPeca('c', 1, new Bispo(tabuleiro,  Cor.BRANCA));
         PosicionaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
         
         // o rei pede para passar a partida como parametro. entao ele passa apenas o "this"
         // que ja se entende que seja a propria partidadeXadrez em que a gente esta 
 	     PosicionaNovaPeca('e', 1, new Rei(  tabuleiro,  Cor.BRANCA, this));
 	     
 	     PosicionaNovaPeca('f', 1, new Bispo(tabuleiro,  Cor.BRANCA));
 	     PosicionaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
         PosicionaNovaPeca('h', 1, new Torre(tabuleiro,  Cor.BRANCA));
         PosicionaNovaPeca('a', 2, new Peao (tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('b', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('c', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
 	     PosicionaNovaPeca('d', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('e', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('f', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('g', 2, new Peao( tabuleiro,  Cor.BRANCA,this));
         PosicionaNovaPeca('h', 2, new Peao( tabuleiro,  Cor.BRANCA,this)); 

         PosicionaNovaPeca('a', 8, new Torre(tabuleiro,  Cor.PRETA));
         PosicionaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
         PosicionaNovaPeca('c', 8, new Bispo(tabuleiro,  Cor.PRETA));
         PosicionaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
 	     PosicionaNovaPeca('e', 8, new Rei(  tabuleiro,  Cor.PRETA, this));
 	     PosicionaNovaPeca('f', 8, new Bispo(tabuleiro,  Cor.PRETA));
 	     PosicionaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
         PosicionaNovaPeca('h', 8, new Torre(tabuleiro,  Cor.PRETA));
         PosicionaNovaPeca('a', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('b', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('c', 7, new Peao( tabuleiro,  Cor.PRETA,this));
 	     PosicionaNovaPeca('d', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('e', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('f', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('g', 7, new Peao( tabuleiro,  Cor.PRETA,this));
         PosicionaNovaPeca('h', 7, new Peao( tabuleiro,  Cor.PRETA,this));

    	 
     }
     

}