package tps.tp3.pack2Festivais;

import java.util.Arrays;

/**
 * 
 *
 */
public abstract class Evento {
	
	private String nome;

	public Evento(String nome) {
		if(nome == null||nome.length() == 0) {
			throw new IllegalArgumentException("O nome tem de ter pelo menos um caratere");
		}
		this.nome = nome;
	}
	
	public abstract int getNumBilhetes();
	
	public abstract String[] getArtistas();
	
	public abstract int numActuacoes(String artista);
	
	public String toString() {
		return this.nome+" com "+getNumBilhetes()+ " bilhetes e com os artistas "+Arrays.toString(getArtistas());
	}

}
