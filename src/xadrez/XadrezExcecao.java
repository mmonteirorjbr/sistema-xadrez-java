package xadrez;

import tabuleiroJogo.TabuleiroExcecao;

public class XadrezExcecao extends  TabuleiroExcecao {

//	public class XadrezExcecao extends  RuntimeException {
	// antes era RunTimeException mas como toda excecao de xadrez tb e uma excecao de tabuleiro 
    // ai para ficar mais facil de entender ele trocou
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 // A unica funcao e repassar a mensagem para o contrutor da supler classe
	public XadrezExcecao(String msg) {
		super(msg);
	}
}
