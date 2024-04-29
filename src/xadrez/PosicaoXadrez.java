package xadrez;

import tabuleiroJogo.Posicao;

public class PosicaoXadrez {
		
		private char coluna;
		private int linha;
		
		public PosicaoXadrez(char coluna, int linha) {
			if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
				throw new XadrezExcecao("Erro instanciando PosicaoXadrez. Valores validos sao de a1 ate h8.");
			}
			this.coluna = coluna;
			this.linha = linha;
		}

		public char getcoluna() {
			return coluna;
		}

		public int getlinha() {
			return linha;
		}

		protected Posicao paraPosicao() {
			return new Posicao(8 - linha, coluna - 'a');
		}
		
		protected static PosicaoXadrez daPosicao(Posicao posicao) {
			return new PosicaoXadrez((char)('a' + posicao.getcoluna()), 8 - posicao.getlinha());
		}
		
		@Override
		public String toString() {
			return "" + coluna + linha;
		}
	}

