package aplicacao;

import xadrez.PecadeXadrez;

// chama UI  de User Interface
public class UI {

	public static void imprimeTabuleiro(PecadeXadrez[][] pecas ) {
		// imprime o tabuleiro
		// ele usa pecas.length em linhas e colunas pq esta considerando que a matriz e quadrada
		
		for (int i=0; i<pecas.length; i++) {
			
			// Comeca o vetor com 0 e o tabuleiro comeca com o 8 vai fazer 8-i e vir em ordem decrescente
			System.out.print((8-i)+ " ");
			for (int j=0; j<pecas.length; j++) {
				imprimePeca(pecas[i][j]);					
			}
			System.out.println(); // troca de linha na matriz
			
		}
		System.out.println("  a b c d e f g h ");
			
	}
	
	// metodo pra imprimir uma peça
	private static void imprimePeca(PecadeXadrez peca) {
		if (peca == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" " ); // imprime um espaco em branco para as pecas nao ficarem grudadas
		
	}
	
}
