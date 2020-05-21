package tps.tp3.pack2Festivais;

public interface IFestival {

	/**
	 * Devolve o n�mero de bilhetes do espetaculo
	 * @return n�mero de bilhetes
	 */
	int getNumBilhetes();

	/**
	 * Devolve o numero de atua��es do artista
	 * @param artista a verificar (String)~
	 * @return count - n�mero de atua��es do artista
	 */
	int numActuacoes(String artista);

	/**
	 * Devolve uma string com a informa��o do festival
	 * Formato: Festival 'Nome do evento' com 'N�mero de bilhetes' bilhetes e com os artistas ['os artistas']
	 */
	String toString();

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
	String[] getArtistas();

	/**
	 * Adiciona o evento se a condi��es forem preenchidas, isto �, se houver espa�o no array de eventos e se um dos artista nao tiver mais que dois eventos onde ir� atuar
	 * @param evento a adicionar
	 * @return True se o evento for adicionado, False caso contrario
	 */
	boolean addEvento(IEvento evento);

	/**
	 * Apaga o evento com o titulo inserido e devolve true se este for apagado
	 * @param nomeEvento a apagar
	 * @return True se o evento for apagado, False caso contrario
	 */
	boolean delEvento(String nomeEvento);

}