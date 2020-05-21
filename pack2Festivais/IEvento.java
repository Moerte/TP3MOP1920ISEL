package tps.tp3.pack2Festivais;

public interface IEvento {

	/**
	 * Devolve o nome do evento
	 * @return nome do evento já validado
	 */
	String getNome();

	int getNumBilhetes();

	String[] getArtistas();

	int numActuacoes(String artista);

	/**
	 * Devolve uma string com a informação do evento
	 */
	String toString();

}