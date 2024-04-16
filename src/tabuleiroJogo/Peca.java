package tabuleiroJogo;

public class Peca {
	
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


    
    
    
}
