package tps.tp3.pack2Festivais;

public interface IEspetaculo {

	/**
	 * Devolve o numero de atua��es do artista
	 * @param artista a verificar (String)~
	 * @return count - n�mero de atua��es do artista
	 */
	int numActuacoes(String artista);

	/**
	 * Adiciona o artista ao evento e devolve true se as condi��es forem preenchidas
	 * @param artista a adicionar (String)
	 * @return True se for adicionado, False caso contrario
	 */
	boolean addArtista(String artista);

	/**
	 * Devolve o n�mero de bilhetes do espetaculo
	 * @return n�mero de bilhetes
	 */
	int getNumBilhetes();

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
	String[] getArtistas();

	/**
	 * Devolve uma string com a informa��o do espetaculo
	 * Formato: 'Nome do evento' com 'N�mero de bilhetes' bilhetes e com os artistas ['os artistas'] em 'local do espetaculo'
	 */
	String toString();

}