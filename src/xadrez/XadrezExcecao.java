package xadrez;

public class XadrezExcecao extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 // A unica funcao e repassar a mensagem para o contrutor da supler classe
	public XadrezExcecao(String msg) {
		super(msg);
	}
}
