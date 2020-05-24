package tps.tp3.pack1ColeccoesComHeranca;

import java.util.Arrays;

/**
 * Classe que deverá suportar um livro
 */
public class Livro extends Obra {

	// número de páginas
	private int numPaginas;

	// preço do livro
	private float preco;

	// array de autores, este array não deve ter nulls
	private String[] autores;

	/**
	 * Deve criar um novo livro com os dados recebidos. O número de páginas não
	 * pode ser menor que 1. O preço não pode ser negativo. O array de autores
	 * não deve conter nem nulls e deve conter pelo menos um autor válido. Não
	 * pode haver repetições dos nomes dos autores, considera-se os nomes sem os
	 * espaços extra (ver removeExtraSpaces). Este método deve utilizar os
	 * métodos auxiliares existentes. Em caso de nome inválido deve lançar uma
	 * excepção de IllegalArgumentException com a indicação do erro ocorrido
	 */
	public Livro(String titulo, int numPaginas, float preco, String[] autores) {
	


		// título
		super(titulo);
		
		if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException("O titulo tem de ter pelo menos um caracter");
		if (!Obra.validarNome(titulo))
			throw new IllegalArgumentException("O nome do título é inválido");
		
		// páginas
		if (numPaginas < 1)
			throw new IllegalArgumentException("O nº de páginas não pode ser negativo");
		
		// preço
		if (preco < 0)
			throw new IllegalArgumentException("O preço não pode ser negativo");
		
		// autores 
		if(autores == null || autores.length < 1)
			throw new IllegalArgumentException("Tem de haver pelo menos um autor");
		if(!Obra.validarNomes(autores))
			throw new IllegalArgumentException("O autor tem de ter o nome válido");
		if(!Obra.haRepeticoes(autores))
			throw new IllegalArgumentException("O array de autores contém autores repetidos");
		for (int i = 0; i < autores.length; i++) {
			autores[i] = Obra.removeExtraSpaces(autores[i]);
		}
		
		
		this.numPaginas = numPaginas;
		this.preco = preco;
		this.autores = Arrays.copyOf(autores, autores.length);
	}

	/**
	 * Devolve o número de páginas do livro
	 */
	public int getNumPaginas() {
		
		return this.numPaginas;
	}

	/**
	 * Devolve o preço do livro
	 */
	public float getPreco() {
		
		return this.preco;
	}

	/**
	 * Devolve true se o autor recebido existe como autor do livro. O nome
	 * recebido não contém espaços extra.
	 */
	public boolean contemAutor(String autorNome) {
		
		String[] autoresToCheck = this.getAutores();
		int count = 0;
		for (int i = 0; i < autoresToCheck.length; i++) {
			if (autorNome.equals(autoresToCheck[i]))
				count++;
		}
		return (count > 0) ? true : false;
	}

	/**
	 * Devolve uma cópia do array de autores do livro
	 */
	public String[] getAutores() {
		
		return autores; 
	}

	/**
	 * Devolve uma string com a informação do livro (ver outputs desejados)
	 */
	public  String toString() {
		return super.toString() + Arrays.toString(getAutores());
	}

	/**
	 * Iguais se equais no contexto de obra e se o objecto recebido for um Livro.
	 * Deve utilizar o método equals de Obra
	 */
	public boolean equals(Object l) {
		return (l != null) && this.getTitulo().equalsIgnoreCase(((Obra) l).getTitulo());
	}
	


	/**
	 * main
	 */
	public static void main(String[] args) {

		// constructor e toString
		Livro l = new Livro("Viagem aos Himalaias", 340, 12.3f,
				new String[] { "João Mendonça", "Mário Andrade" });
		System.out.println("Livro -> " + l);
		l.print("");
		l.print("-> ");
		System.out.println();

		// contém autor
		String autorNome = "Mário Andrade";
		System.out.println("Livro com o autor " + autorNome + "? -> " + l.contemAutor(autorNome));
		autorNome = "Mário Zambujal";
		System.out.println("Livro com o autor " + autorNome + "? -> " + l.contemAutor(autorNome));
		System.out.println();

		// equals
		System.out.println("Livro: " + l);
		System.out.println("equals Livro: " + l);
		System.out.println(" -> " + l.equals(l));

		Livro l2 = new Livro("Viagem aos Himalaias", 100, 10.3f,
				new String[] { "Vitor Záspara" });
		System.out.println("Livro: " + l);
		System.out.println("equals Livro: " + l2);
		System.out.println(" -> " + l.equals(l2));
		System.out.println();

		// testes que dão excepção - mostra-se a excepção

		// livro lx1
		System.out.println("Livro lx1: ");
		try {
			Livro lx1 = new Livro("Viagem aos Himalaias", -1, 12.3f,
					new String[] { "João Mendonça", "Mário Andrade" });
			System.out.println("Livro lx1: " + lx1);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		System.out.println();

		// livro lx2
		System.out.println("Livro lx2: ");
		try {
			Livro lx2 = new Livro("Viagem aos Himalaias", 200, -12.3f,
					new String[] { "João Mendonça", "Mário Andrade" });
			System.out.println("Livro lx2: " + lx2);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		System.out.println();

		// livro lx3
		System.out.println("Livro lx3: ");
		try {
			Livro lx3 = new Livro(null, 200, -12.3f,
					new String[] { "João Mendonça", "Mário Andrade" });
			System.out.println("Livro lx3: " + lx3);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		System.out.println();

		// livro lx4
		System.out.println("Livro lx4: ");
		try {
			Livro lx4 = new Livro("Viagem aos Himalaias", 200, 12.3f,
					new String[] { "João Mendonça", "Mário Andrade",
							"João Mendonça" });
			System.out.println("Livro lx4: " + lx4);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}
}
