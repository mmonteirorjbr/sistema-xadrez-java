package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecadeXadrez;
import xadrez.PecadeXadrez;

public class Peao extends PecadeXadrez {

	
	public Peao(Tabuleiro tabuleiro, Cor cor) {
	  super(tabuleiro,cor);
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
	}
	else {	// agora fas para as ptreas, a diferenca e na direcao das linhas que tem de somar e nao diminui
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
		
	    Posicao p2 =  new Posicao(posicao.getlinha()-1,  posicao.getcoluna());
	    
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
	}
 return mat;
}
// informa que e pra imprimir o "P" de peao
@Override
public String toString() {
    return "P";
}    
}
