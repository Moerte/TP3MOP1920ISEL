package tps.tp3.pack2Festivais;

import java.util.Arrays;

/**
 * Class abstrata com os metodos abstratos 
 * getNumBilhetes, getArtistas, numActuacoes
 * e com os metodos getNome e toString
 */
public abstract class Evento implements IEvento {
	// Nome do evento
	private String nome;
	
	/**
	 * Construtor que recebe e valida o nome do evento
	 * @param nome do evento
	 */
	public Evento(String nome) {
		if(nome == null||nome.length() == 0) {
			throw new IllegalArgumentException("O nome tem de ter pelo menos um caratere");
		}
		this.nome = nome;
	}
	
	/**
	 * Devolve o nome do evento
	 * @return nome do evento já validado
	 */
	@Override
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Devolve uma string com a informação do evento
	 * Formato: 'Nome do evento' com 'Número de bilhetes' bilhetes e com os artistas ['os artistas']
	 */
	@Override
	public String toString() {
		return getNome()+" com "+getNumBilhetes()+ " bilhetes e com os artistas "+Arrays.toString(getArtistas());
	}

}
