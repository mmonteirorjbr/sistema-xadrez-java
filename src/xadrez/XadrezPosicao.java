package xadrez;

import tabuleiroJogo.Posicao;

public class XadrezPosicao {
  private char coluna;
  private int linha;
  
  
public XadrezPosicao(char coluna, int linha) {
	// testa a poicao validando entre linha e coluna
	if (coluna < 'a' || coluna > 'h'  || linha <1 || linha > 8  ) {
		throw  new  XadrezExcecao(" Erro instanciando a Posicao do Xadrez. Valores validos vao de a1 a h8");
	}
		this.coluna = coluna;
		this.linha = linha;
	
    }


public char getColuna() {
	return coluna;
}


public int getLinha() {
	return linha;
}
  
protected Posicao paraPosicao() {
	// numa matriz como dao tabuleiro em que o 8 e o comedo do vetor e comeca com 0
	// a formula é tirar a posicao do numero 8 = 8-8 = 0 que e a 1a linha e por ai vai
	// coluna como e com letras, segue o mesmo principo mas usando o 'a' como pono base
		
	return new Posicao( 8 - linha, coluna - 'a' );
	
}
  
// dada um aposicao na matriz ele tem de converter para uma posicao no xadrez
protected static XadrezPosicao daPosicao(Posicao posicao) {
	// precisa fazer a conversao para char pq a conversao nao e automatica nesse caso
	// entao e feito um cast
	return new XadrezPosicao((char) ( 'a' - posicao.getcoluna()), 8 - posicao.getlinha());
}

@Override
public String toString() {
	// precisa do caracter vazio no inicio pro compilador saber que e uma concatenacao de char
	return ""+coluna + linha;
}
}
