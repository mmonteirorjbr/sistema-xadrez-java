package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecadeXadrez;

public class Rainha extends PecadeXadrez {
    // defnir o cabecalho acima o proprio programa reclama da falta do construtor e ai
	// e so aceitar que ele crie o construtor que ja gera o codigo abaixo
	//o construtor vai simplesmnete passar a chamada para a super classe
	
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}
	
	// Basicamente ao acessar a torre define que vai colocar um T na posicao
		// definida no tabuleiro
		@Override
		public String toString() {
			return "R";
		}
	
		@Override
		public boolean[][] movimentosPossiveis() {
			
			// cria uma matriz temporaria chamada mat quem tem as mesmas dmimensoes do tabuleiro
			// todas posicoes dela nesse moemnto retornam falso como se o rei estivesse preso			
		    boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		    
		    // inicia a variavel p para armazenar a posicao
		    Posicao p = new Posicao(0,0);
		    
		    // PARA CIMA  
		    
		    // nesse momento estou fazendo uma anotacao em que para andar numa linha pra cima ele
		    // diminui a linha porque as pecas brancas estao na parte de baixo, ou seja, nas ultimas linhas
		    
		    // depois d emontar o "para cima" ele usa o mesmo codigo para os outros movimentos aumentando ou 
		    //diminuindo 1 na posica. Eu teria feito um metodo chamado movimento passando esse +1 , -1 ou zero. 
		    
		    
		    
		    
		    // registrando tambem que posicao esta definida na classe peca 
		    p.setValores(posicao.getlinha()- 1, posicao.getcoluna());
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setlinha(p.getlinha()  -1);
		    	
		    	
		    }
		    // agora se existe a posicao e tem uma peca adversaria la tambem considera como verdadeira 
		    // porque eu possi ir prala e "comer" a peça
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    			
		    }
		    
		    
		    //PARA A  ESQUERDA
		    
		    //  a linha sera a mesma e a coluna diminui de 1
		    // copiei o conteudo do PARA CIMA sem os comentarios , e so reler cada item com calma
		    
		    
		    p.setValores(posicao.getlinha(), posicao.getcoluna() -1);
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setcoluna(p.getcoluna()  -1);
		    }
		    
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    			
		    }
		    
             //PARA A DIREITA 
		    
		    //  a linha sera a mesma e a coluna aumenta  de 1
		    // copiei o conteudo do PARA CIMA sem os comentarios , e so reler cada item com calma
		    
		    
		    p.setValores(posicao.getlinha(), posicao.getcoluna() +1);
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setcoluna(p.getcoluna()  +1);
		    }
		    
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    			
		    }
		    
		    
            //  PARA BAIXO  
		    
		    
		    p.setValores(posicao.getlinha()  +1, posicao.getcoluna());
		   
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setlinha(p.getlinha()  +1);		    	
		    }
		    
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    }
		 
		    // NOROESTE
		    
		    p.setValores(posicao.getlinha()- 1, posicao.getcoluna()-1);
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setValores(p.getlinha()-1, p.getcoluna()-1);
		    
		    }
		    // agora se existe a posicao e tem uma peca adversaria la tambem considera como verdadeira 
		    // porque eu possi ir prala e "comer" a peça
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    }
		    
		    
		    //Nordeste 
		    
		   
		    
		    p.setValores(posicao.getlinha()-1, posicao.getcoluna() +1);
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setValores(p.getlinha()-1, p.getcoluna()+1);
		    }    
		    
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;			    			
		    }
		    
             //sudeste
		      
		    
		    p.setValores(posicao.getlinha()+1, posicao.getcoluna() +1);
		    
		    // enquanto existe a posicao e nao tem uma peca la, define a posicao na matriz como verdadeira
		    
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setValores(p.getlinha()+1, p.getcoluna()+1);
		    }
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    			
		    }
		    
		    
            //  sudoeste   
		    
		    
		    p.setValores(posicao.getlinha()  +1, posicao.getcoluna()-1);
		   
		    while (getTabuleiro().existePosicao(p) && !getTabuleiro().jaTemUmaPeca(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    	p.setValores(p.getlinha()+1, p.getcoluna()-1);
		    }
		
		    if (getTabuleiro().existePosicao(p) && existePecaAdversaria(p)) {
		    	mat[p.getlinha()][p.getcoluna()]=true;
		    }
		    

		    
			return mat;
		}

}