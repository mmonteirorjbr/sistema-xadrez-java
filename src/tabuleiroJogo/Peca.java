package tabuleiroJogo;

public abstract class Peca {
	
	// Esse item estara protegido(protected) porque a posicao e a posdicao de matriz e nao a posicao
	// da peça no xadrz, e so pra nao misturar
	
    protected Posicao posicao;
    
    //tambem precisa associar a peça com o tabuleiroJogo de forma a que a peca saoba onde ela esta
    private Tabuleiro tabuleiro;

     // So fez com o tabuleiro e nao com a peça porque a peça inicialment e criada como nula 
    
    
	public Peca(Tabuleiro tabuleiro) {
		super();
		this.tabuleiro = tabuleiro;
		// nao precisava colocar como nulo pq o java ja coloca assim por definicao. e so pra reforcar		
		posicao = null;
	}

	 // usei a geracao de getters and setters mas apaguei o set do tabuleiro pq o valor nao pode ser alterado
	 //e ele nao pode ficar publico tem de ser protected pra nao ser acessado pela camada de cima
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}


	 public abstract boolean[][] movimentosPossiveis();
    //valida um movimento para ver se ele é possivel (valido)
	 
	 public boolean movimentoPossivel(Posicao posicao) {
		 // nesse ponto observar que  metodo concreto utilzia  o metodo abstrato
		 // isso e um exemplo de hook metods ou metodo gancho porque faz um gancho com a sub classe
		 // na verdade ele esta chamando uma implementacao de uma subclasse concreta da classe peca
		 
		 return movimentosPossiveis()[posicao.getlinha()][posicao.getcoluna()];
	 }
	 
    public boolean existeUmMovimentoPossivel() {
      //verifica se a peça nao esta "presa" . Varre a matriz para
      // ver se existe alguma posicao que seja verdadeira
    	boolean[][] mat = movimentosPossiveis();
    	// percorre a matriz pra ver se tem algum aposicao que seja verdadeira
    	// usa mat.length no i e no j pq presume que a matriz do tabuleiro seja sempre
    	//quadrada
    	
    	for (int i=0; i<mat.length; i++) {
    		for (int j=0; j<mat.length; j++) {
    			// se ao instanciar a posicao na matriz retornar verdadeiro vai retornar true   
    			if (mat[i][j]) {
    				return true;
    			}			
    			
    		}
    				
    	}
    	return false;  // Se varrer tudo e nao achar um verdadeiro retorna false
    }
}
