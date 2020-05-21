package tps.tp3.pack2Festivais;

public interface IFestival {

	/**
	 * Devolve o número de bilhetes do espetaculo
	 * @return número de bilhetes
	 */
	int getNumBilhetes();

	/**
	 * Devolve o numero de atuações do artista
	 * @param artista a verificar (String)~
	 * @return count - número de atuações do artista
	 */
	int numActuacoes(String artista);

	/**
	 * Devolve uma string com a informação do festival
	 * Formato: Festival 'Nome do evento' com 'Número de bilhetes' bilhetes e com os artistas ['os artistas']
	 */
	String toString();

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
	String[] getArtistas();

	/**
	 * Adiciona o evento se a condições forem preenchidas, isto é, se houver espaço no array de eventos e se um dos artista nao tiver mais que dois eventos onde irá atuar
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