package tps.tp3.pack2Festivais;

public interface IEspetaculo {

	/**
	 * Devolve o numero de atuações do artista
	 * @param artista a verificar (String)~
	 * @return count - número de atuações do artista
	 */
	int numActuacoes(String artista);

	/**
	 * Adiciona o artista ao evento e devolve true se as condições forem preenchidas
	 * @param artista a adicionar (String)
	 * @return True se for adicionado, False caso contrario
	 */
	boolean addArtista(String artista);

	/**
	 * Devolve o número de bilhetes do espetaculo
	 * @return número de bilhetes
	 */
	int getNumBilhetes();

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
	String[] getArtistas();

	/**
	 * Devolve uma string com a informação do espetaculo
	 * Formato: 'Nome do evento' com 'Número de bilhetes' bilhetes e com os artistas ['os artistas'] em 'local do espetaculo'
	 */
	String toString();

}