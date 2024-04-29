package tabuleiroJogo;

public class Tabuleiro {

	 private int linhas;
	 private int colunas;
	 private Peca[][] pecas;
	 
	public Tabuleiro(int linhas, int colunas) {
		if (linhas< 1 ||  colunas < 1) {
		 throw new  TabuleiroExcecao( "Erro criando tabuleiro. E necessario que exista pelo menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	// nao tem setlinhas nem setcolunas porque nao pode alterar o formato do tabuleiro depois de iniciado
	
	public int getLinhas() {
		return linhas;
	}


	public int getColunas() {
		return colunas;
	}

	 
	// retorna a peça pela linha e coluna
	public Peca peca(int linha,int coluna) {
	 if  (! existePosicao( linha, coluna)) {
		  throw new TabuleiroExcecao("Essa Posicao nao faz parte do tabuleiro");
	 }
	 return pecas[linha][coluna];
	}
	
	// retorna a peça pela posicao que ja vem com a linha e a coluna
	public Peca peca(Posicao posicao) {
		 if  (! existePosicao( posicao)) {
			  throw new TabuleiroExcecao("Essa Posicao nao faz parte do tabuleiro");
		 }
			 
		 return pecas[posicao.getlinha()][posicao.getcoluna()];
		}

	  public void PosicionaPeca(Peca peca, Posicao posicao) {	  
		  
		 // Antes de posicionar a peça, verifica se a posicao ja nao esta ocupada
		 if  ( jaTemUmaPeca(posicao)) {
			 throw new TabuleiroExcecao("Ja existe uma peça na posicao " + posicao);
		 }
		  // atribui na matriz de pecas a posicao que foi recebida
		  pecas[posicao.getlinha()][posicao.getcoluna()]= peca;
		  
		  // determina a posicao pelo que foi recebido. O ponto importante e que ele consegue
		  // acessar aqui porque posicao foi definido como protected no tabuleiro e como ela e do mesmo pacote
		  // da pra acessa-la livremente aqui
		  
		  peca.posicao = posicao;
	  }

	  public Peca removePeca(Posicao posicao) {
		  // Para ler o cabeçalho do metodo:
		  // Um metodo publico que retorna um valor do tipo peca e o parametro é uma posicao do tipo posicao

		  if (!existePosicao(posicao)){
               throw new TabuleiroExcecao("Essa posicao nao  existe no tabuleiro");
		 }
	         // se nao tiver peca na posicao , retorna nulo
		  
		    if (peca(posicao) == null) {
		    	return null;
		    }
		    // variavel aux do tipo peca recebe a peca que estiver na posicao
		    Peca aux = peca(posicao);
		    
		    // juro que nao entendi bem esse bloco e a necessidade de ter a variavel auxiliar. era so 
		    // retornar nulo. 
		    aux.posicao = null;
		    pecas[posicao.getlinha()][posicao.getcoluna()] = null;
		    return aux;
		    
	  }
	  
	  
	  
	  //ele fez dos dois jeitos porque vai ter um momento em que sera mais facil testar pela linha e 
	  // coluna do que pela posicao
	  private boolean existePosicao(int linha, int coluna) {
		// uma posicao existe quando ela esta dentro do tabuleiro
		// se nao atender a todas as condicoes vai retornar falso		  
		 return linha >=0 && linha < linhas && coluna >=0   && coluna < colunas;
	  }
	  
	  public boolean existePosicao( Posicao posicao ) { 
	     return existePosicao(posicao.getlinha(), posicao.getcoluna());
	  }   
	  
	  public boolean jaTemUmaPeca( Posicao posicao) {
			 if  (! existePosicao( posicao)) {
				  throw new TabuleiroExcecao("Essa Posicao nao faz parte do tabuleiro");
			 }
		
	        return peca(posicao) != null;
      }
}