package tabuleiroJogo;

// Na pratica desvia a mensagem em caso de erro para ser tratada dentro do programa

public class TabuleiroExcecao  extends RuntimeException {
	private static final long serialVersionUID = 1L;
    // Vai apenas repassar a mensagem para o construtir da super classe.
    
	public TabuleiroExcecao(String msg) {
		super(msg);
	}
}
