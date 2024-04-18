package tabuleiroJogo;

public class Tabuleiro {

	 private int linhas;
	 private int colunas;
	 private Peca[][] pecas;
	 
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	 
	// retorna a peça pela linha e coluna
	public Peca peca(int linha,int coluna) {
	 
	 return pecas[linha][coluna];
	}
	
	// retorna a peça pela posicao que ja vem com a linha e a coluna
	public Peca peca(Posicao posicao) {
		 
		 return pecas[posicao.getlinha()][posicao.getcoluna()];
		}

	  public void PosicionaPeca(Peca peca, Posicao posicao) {
	    // atribui na matriz de pecas a posicao que foi recebida
		  pecas[posicao.getlinha()][posicao.getcoluna()]= peca;
		  
		  // determina a posicao pelo que foi recebido. O ponto importante e que ele consegue
		  // acessar aqui porque posicao foi definido como protected no tabuleiro e como ela e do mesmo pacote
		  // da pra acessa-la livremente aqui
		  
		  peca.posicao = posicao;
	  }
	  
}
