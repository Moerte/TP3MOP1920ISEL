package tps.tp3.pack1ColeccoesComHeranca;

public interface ILivro {

	/**
	 * Devolve o número de páginas do livro
	 */
	int getNumPaginas();

	/**
	 * Devolve o preço do livro
	 */
	float getPreco();

	/**
	 * Devolve true se o autor recebido existe como autor do livro. O nome
	 * recebido não contém espaços extra.
	 */
	boolean contemAutor(String autorNome);

	/**
	 * Devolve uma cópia do array de autores do livro
	 */
	String[] getAutores();

	/**
	 * Devolve uma string com a informação do livro (ver outputs desejados)
	 */
	String toString();

	/**
	 * Iguais se equais no contexto de obra e se o objecto recebido for um Livro.
	 * Deve utilizar o método equals de Obra
	 */
	boolean equals(Object l);

}