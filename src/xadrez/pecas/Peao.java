package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidadeXadrez;
import xadrez.PecadeXadrez;

public class Peao extends PecadeXadrez {
  // isso eu nao entendi muito bem, mas ele precisa que seja passada a partidaXadrez para
	// que haja uma asosciacao entre os objetos 
	private  PartidadeXadrez partidadeXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidadeXadrez partidadeXadrez ) {
	  super(tabuleiro,cor);
	  this.partidadeXadrez = partidadeXadrez;
}
@Override
public boolean[][] movimentosPossiveis(){
	boolean[][] mat = new boolean[getTabuleiro().getLinhas()] [getTabuleiro().getColunas()];
	
	Posicao p = new Posicao(0,0);
	
	// se a cor for branca esta trablhando com o peao branco	
	if ( getCor() == Cor.BRANCA) {
		// nesse ponto a regra e de andar rpa cima, (diminuindo a linha)
		// ve se o peao pode ir para essa posicao
		p.setValores(posicao.getlinha()-1,  posicao.getcoluna());
		// se a pósicao existrie nao tem peca na posicao entao o peao pode ir pra la
		
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
			
		}
		// a regra diz que no 1o movimento do peao ele pode andar duas cass pra frente. nos outros
		// movimentos apenas uma casa
		p.setValores(posicao.getlinha()-2,  posicao.getcoluna());
		// para andar duas casas a 1a casa tambem tem de estar livre entao vai fazer o teste dela aqui tb
		
	    Posicao p2 =  new Posicao(posicao.getlinha()-1,  posicao.getcoluna());
	    
	    //valida as duas posicoes se for andar duas casas 
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().jaTemUmaPeca(p2) &&  getContaMovimentos()== 0) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
		
		}	
		// testa agora as casas nas diagonais
		p.setValores(posicao.getlinha()-1, posicao.getcoluna()-1);
		if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
		}  
		p.setValores(posicao.getlinha()-1, posicao.getcoluna()+1);
		if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
			
		}	
		
		// movimento especial en passant. Ele so vale quand acontece na linha 5
		// que e a linha 3 da matriz
		if (posicao.getlinha() == 3) {
			Posicao esquerda = new Posicao(posicao.getlinha(),posicao.getcoluna()-1) ;
			// verifica se a posicao da esquerda existe, se tem uma peca oponente na posicao,
			// e se a peça q esta la e a peca q esta vulneravel a tomar o en passant
			
			if (getTabuleiro().existePosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidadeXadrez.getEnPassantVulneravel()) {
				// se atender a todas as condicoes vai dizer que o peao pode capturar a peça q esta na posicao left
				// mas ele nao vai mover pra posicao dela, ela vai pra posicao da linha logo acima
				   mat[esquerda.getlinha()-1][esquerda.getcoluna()] = true;
			}			   

			
			Posicao direita  = new Posicao(posicao.getlinha(),posicao.getcoluna()+1) ;
			// verifica se a posicao da esquerda existe, se tem uma peca oponente na posicao,
			// e se a peça q esta la e a peca q esta vulneravel a tomar o en passant
			
			if  (getTabuleiro().existePosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidadeXadrez.getEnPassantVulneravel() ){
				// se atender a todas as condicoes vai dizer que o peao pode capturar a peça q esta na posicao left
				// mas ele nao vai mover pra posicao dela, ela vai pra posicao da linha logo acima
				   mat[direita.getlinha()-1][direita.getcoluna()] = true;
			}			   
			
			
		}
		
		
	}
	else {	// agora fas para as petras, a diferenca e na direcao das linhas que tem de somar e nao diminui
		// nesse ponto a regra e de andar rpa cima, (diminuindo a linha)
		// ve se o peao pode ir para essa posicao
		p.setValores(posicao.getlinha() +1,  posicao.getcoluna());
		// se a pósicao existrie nao tem peca na posicao entao o peao pode ir pra la
		
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
			
		}
		// a regra diz que no 1o movimento do peao ele pode andar duas cass pra frente. nos outros
		// movimentos apenas uma casa
		p.setValores(posicao.getlinha()+2,  posicao.getcoluna());
		// para andar duas casas a 1a casa tambem tem de estar livre entao vai fazer o teste dela aqui tb
		
	    Posicao p2 =  new Posicao(posicao.getlinha()+1,  posicao.getcoluna());
	    
	    //valida as duas posicoes se for andar duas casas 
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().jaTemUmaPeca(p2) &&  getContaMovimentos()== 0) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
		
		}	
		// testa agora as casas nas diagonais
		p.setValores(posicao.getlinha()+1, posicao.getcoluna()-1);
		if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
		}  
		p.setValores(posicao.getlinha()+1, posicao.getcoluna()+1);
		if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
			// se esta tudo ok marca na matriz
			mat[p.getlinha()][p.getcoluna()] = true;
			
		}	
		
		// movimento especial en passant das pecas pretas . Ele so vale quando acontece na linha 4
		// que e a linha 4 da matriz. 
		if (posicao.getlinha() == 4) {
			Posicao esquerda = new Posicao(posicao.getlinha(),posicao.getcoluna()-1) ;
			// verifica se a posicao da esquerda existe, se tem uma peca oponente na posicao,
			// e se a peça q esta la e a peca q esta vulneravel a tomar o en passant
			
			if (getTabuleiro().existePosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidadeXadrez.getEnPassantVulneravel()) {
				// se atender a todas as condicoes vai dizer que o peao pode capturar a peça q esta na posicao left
				// mas ele nao vai mover pra posicao dela, ela vai pra posicao da linha logo abaixo
				   mat[esquerda.getlinha()+1][esquerda.getcoluna()] = true;
			}			   

			
			Posicao direita  = new Posicao(posicao.getlinha(),posicao.getcoluna()+1) ;
			// verifica se a posicao da esquerda existe, se tem uma peca oponente na posicao,
			// e se a peça q esta la e a peca q esta vulneravel a tomar o en passant
			
			if  (getTabuleiro().existePosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidadeXadrez.getEnPassantVulneravel() ){
				// se atender a todas as condicoes vai dizer que o peao pode capturar a peça q esta na posicao left
				// mas ele nao vai mover pra posicao dela, ela vai pra posicao da linha logo abaixo
				   mat[direita.getlinha()+1][esquerda.getcoluna()] = true;
			}			   
			
			
		}

	}
 return mat;
}
// informa que e pra imprimir o "P" de peao
@Override
public String toString() {
    return "P";
}    
}
