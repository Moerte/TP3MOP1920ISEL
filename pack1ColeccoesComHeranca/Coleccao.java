package tps.tp3.pack1ColeccoesComHeranca;

import java.util.Arrays;

/**
 * Classe Coleccao, deve conter a descri��o de uma colec��o, com t�tulo, os seus
 * livros, colec��es e editores. Deve utilizar heran�a para guardar os livros e
 * as colec��es num s� array
 */
public class Coleccao extends Obra {
	// prefixo a colocar no in�cio de cada print mais interno que o corrente
	public static final String GENERALPREFIX = "  ";

	// n�mero m�ximo de obras de uma colec��o
	private static int MAXOBRAS = 20;

	// Array de obras, de Livros ou Colecc��e, em que estas devem encontrar-se
	// sempre nos menores �ndices e pela ordem de registo
	private Obra[] obras = new Obra[MAXOBRAS];

	// dever� conter sempre o n�mero de obras na colec��o
	private int numObras = 0;

	// Editores, tem as mesmas condicionantes que array de autores na classe
	// livro
	private String[] editores;

	/**
	 * Construtor; o t�tulo deve ser guardado e validado na clase obra; o array de
	 * editores devem ser pelo menos um e tem as mesmas restri��es que os autores
	 * dos livros;
	 */
	public Coleccao(String titulo, String[] editores) {

		super(titulo);
		if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException("O titulo tem de ter pelo menos um caracter");
		if (!Obra.validarNome(titulo))
			throw new IllegalArgumentException("O nome do t�tulo � inv�lido");

		if (editores == null || editores.length < 1)
			throw new IllegalArgumentException("A cole��o tem de ter pelo menos um editor");
		if (!Obra.validarNomes(editores))
			throw new IllegalArgumentException("O editor tem de ter o nome v�lido");
		if (!Obra.haRepeticoes(editores))
			throw new IllegalArgumentException("O array de editores cont�m editores repetidos");
		for (int i = 0; i < editores.length; i++) {
			editores[i] = Obra.removeExtraSpaces(editores[i]);
		}

		this.editores = Arrays.copyOf(editores, editores.length);
	}

	/**
	 * Obtem o n�mero total de p�ginas da colec��o, p�ginas dos livros e das
	 * colec��es
	 */
	public int getNumPaginas() {

		int numPag = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null)
				numPag += this.obras[i].getNumPaginas();
		}
		return numPag;
	}

	/**
	 * As colec��es com mais de 5000 p�ginas nos seus livros directos t�m um
	 * desconto de 20% nesses livros. As colec��es em que o somat�rio de p�ginas das
	 * suas subcolec��es directas seja igual ou superior ao qu�druplo do n� de
	 * p�ginas da sua subcolec��o directa com mais p�ginas dever�o aplicar um
	 * desconto de 10% sobre os pre�os das suas subcolec��es
	 */

	public float getPreco() {

		float preco = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null) {
				float aux = 0;
				if (numObras >= 4 && this.obras[i].getPreco() >= 10.0 && this.obras[i].getNumPaginas() > 200) {
					aux += (this.obras[i].getPreco() - this.obras[i].getPreco() * 20 / 100);
				}
				preco += (aux > 0) ? aux : this.obras[i].getPreco();
			}
		}
		return (float) (Math.round(preco * 100.0) / 100.0);
	}

	/**
	 * Adiciona uma obra � colec��o se puder, se esta n�o for null e a colec��o n�o
	 * ficar com obras com iguais no seu n�vel imediato. Deve utilizar o m�todo
	 * getIndexOfLivro e getIndexOfColeccao
	 */
	public boolean addObra(Obra l1) {

		if (l1 != null && getIndexOfObra(l1.getTitulo()) == -1) {
			for (int i = 0; i < this.obras.length; i++) {
				if (this.obras[i] == null) {
					this.obras[i] = l1;
					this.numObras++;
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Devolve o index no array de obras onde estiver a obra com o nome pretendido.
	 * Devolve -1 caso n�o o encontre
	 */
	private int getIndexOfObra(String titulo) {

		int index = -1;
		for (int i = 0; i < getNumLivros(); i++) {
			if (this.obras[i] != null) {
				if (titulo.equals(this.obras[i].getTitulo()))
					index = i;
			}
		}
		return index;
	}

	/**
	 * Remove do array a obra com o t�tulo igual ao t�tulo recebido. Devolve a obra
	 * removida ou null caso n�o tenha encontrado a obra. Deve-se utilizar o m�todo
	 * getIndexOfLivro. Recorda-se que as obras ocupam sempre os menores �ndices, ou
	 * seja, n�o pode haver nulls entre elas.
	 */
	public Obra remObra(String titulo) {

		int index = getIndexOfObra(titulo);
		Obra toRemove = (index == -1) ? null : this.obras[index];
		if (index > -1) {
			Obra[] newLivros = new Obra[this.obras.length - 1];
			this.numObras--;
			for (int i = 0, k = 0; i < this.obras.length; i++) {
				if (i == index)
					continue;
				newLivros[k++] = this.obras[i];

			}

			this.obras = Arrays.copyOf(newLivros, newLivros.length);
		}
		return toRemove;
	}

	/**
	 * Remove todas as obras (livros ou colec��es) dentro da obra corrente, que
	 * tenham um t�tulo igual ou t�tulo recebido. Devolve true se removeu pelo menos
	 * uma obra, ou false caso n�o tenha trealizado qualquer remo��o. Deve utilizar
	 * os m�todos remObra e remAllObra.
	 */
	public boolean remAllObra(String titulo) {
		// TODO
		return false;
	}

	/**
	 * Devolve o n� de obras de uma pessoa. Cada colec��o deve contabilizar-se como
	 * uma obra para os editores.
	 */
	public int getNumObrasFromPerson(String autorEditor) {

		int countLivros = 0;

		int eLength = editores.length;
		int oLength = obras.length;

		for (int i = 0; i < eLength; i++) {
			if (editores[i].contains(autorEditor))
				countLivros += 1;
		}

		for (int i = 0; i < oLength; i++) {
			if (obras[i] != null && obras[i] instanceof Coleccao) {
				Coleccao c = (Coleccao) obras[i];
				countLivros += c.getNumObrasFromPerson(autorEditor);
			} else if (obras[i] != null && !(obras[i] instanceof Coleccao)) {
				Livro l = (Livro) obras[i];
				int autores = l.getAutores().length;
				for (int j = 0; j < autores; j++) {
					if (l.getAutores()[j].contains(autorEditor)) {
						countLivros += 1;
					}
				}
			}
		}
		return countLivros;
	}

	/**
	 * Deve devolver um novo array, sem repeti��es, com os livros de que o autor
	 * recebido � autor. O array devolvido n�o deve conter repeti��es, para excluir
	 * as repeti��es devem utilizar o m�todo mergeWithoutRepetitions
	 */
	public Livro[] getLivrosComoAutor(String autorNome) {
		int count = 0;
		for (int i = 0; i < this.obras.length; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof Livro) {
				if (((Livro)this.obras[i]).contemAutor(autorNome))
					count++;
			}
		}
		Livro[] newList = new Livro[count];
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof Livro) {
				if (((Livro)this.obras[i]).contemAutor(autorNome))
					newList[i] = (Livro) obras[i];
			}
		}
		return newList;
	}

	/**
	 * Deve devolver um array, sem nulls, com todos os autores e editores existentes
	 * na colec��o. O resultado n�o deve conter repeti��es. Deve utilizar o m�todo
	 * mergeWithoutRepetitions
	 */
	public String[] getAutoresEditores() {
		
		String [] aux1 = editores;

		for(int i=0; i<obras.length; i++) {
			if(obras[i]!=null && !(obras[i] instanceof Coleccao)) {
				Livro l = (Livro) obras[i];
				aux1 = mergeWithoutRepetitions(aux1, l.getAutores());
			}
			else if(obras[i]!=null && (obras[i] instanceof Coleccao)){
				Coleccao c = (Coleccao) obras[i];
				aux1 = mergeWithoutRepetitions(aux1, c.editores);
				aux1 = mergeWithoutRepetitions(aux1, c.getAutoresEditores());
			}
		}
		return aux1;
	}

	/**
	 * M�todo que recebendo dois arrays sem repeti��es devolve um novo array com
	 * todos os elementos dos arrays recebidos mas sem repeti��es
	 */
	private static String[] mergeWithoutRepetitions(String[] a1, String[] a2) {

		String[] test = new String[a1.length + a2.length];
		int current = 0;
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != null)
				test[current++] = a1[i];
		}
		for (int j = 0; j < a2.length; j++) {
			boolean isRepition = false;
			if (a2[j] != null) {
				for (int k = 0; k < current; k++) {
					if (a2[j].equalsIgnoreCase(test[k])) {
						isRepition = true;
						break;
					}
				}
				if (isRepition) {
					continue;
				} else {
					test[current++] = a2[j];
				}
			} else {
				continue;
			}
		}
		String[] result = new String[current];
		for (int i = 0; i < current; i++) {
			result[i] = test[i];
		}
		return result;
	}

	/**
	 * M�todo id�ntico ao m�todo anterior mas agora com arrays de livros
	 */
	private static Livro[] mergeWithoutRepetitions(Livro[] a1, Livro[] a2) {

		Livro[] test = new Livro[a1.length + a2.length];
		int current = 0;
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != null)
				test[current++] = a1[i];
		}
		for (int j = 0; j < a2.length; j++) {
			boolean isRepition = false;
			if (a2[j] != null) {
				for (int k = 0; k < current; k++) {
					if ((a2[j]).equals(test[k])) {
						isRepition = true;
						break;
					}
				}
				if (isRepition) {
					continue;
				} else {
					test[current++] = a2[j];
				}
			} else {
				continue;
			}
		}
		Livro[] result = new Livro[current];
		for (int i = 0; i < current; i++) {
			result[i] = test[i];
		}
		return result;
	}

	/**
	 * Devolve o n� de livros dentro da colec��o
	 */
	public int getNumLivros() {
		int numLivros = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] instanceof Livro)
				numLivros++;
			if(this.obras[i] instanceof Coleccao) 
				numLivros += ((Coleccao)this.obras[i]).getNumLivros();
		}
		return numLivros;
	}

	/**
	 * Devolve o n� de colec��es dentro da colec��o
	 */
	public int getNumColeccoes() {
		
		/*
		 * if (numColeccoes == 0) return 0;
		 * 
		 * // para conter o valor m�nimo dos stocks de todos os produtos int minStock =
		 * 0;
		 * 
		 * // inicializar o minStock com o stock do primeiro produto int i = 0; for (; i
		 * < coleccoes.length; i++) { if (coleccoes[i] != null) { minStock =
		 * coleccoes[i].getNumColeccoes(); i++; break; } }
		 * 
		 * // percorrer o array at� se ter consultado os nProdutos produtos for (int
		 * nprods = 1; i < coleccoes.length && nprods < numColeccoes; i++) { if
		 * (coleccoes[i] != null) { // se este produto tiver um stock inferior ao
		 * m�nimo, coloc�-lo // como m�nimo if (coleccoes[i].getNumColeccoes() <
		 * minStock) minStock = coleccoes[i].getNumColeccoes(); } } return minStock;
		 */
//		int total = 0;
//		for (int i = 0, nprods = 0; i < coleccoes.length && nprods < numColeccoes; i++) {
//			if (obras[i] != null) {
//
//				if (coleccoes[i] instanceof Coleccao) {
//					// fazer o cast para produto composto
//					Coleccao pc = (Coleccao) obras[i];
//					// obter o n�mero de produtos compostos dentro desse produto
//					// composto, contar com ele mesmo
//					total += 1 + pc.getNumColeccoes();
//				}
//
//			}
//		}
//		return total;
		// return 0;
		int numColecoes = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] instanceof Coleccao)
				numColecoes++;
		}
		return numColecoes;

	}

	/**
	 * Devolve a profundidada de m�xima de uma colec��o em termos de colecc�es
	 * dentro de colecc��es: uma colec��o c1 com uma colec��o c2 dentro, c1 deve
	 * devolver 2 e c2 deve devolver 1, independentemente do n�mero do conte�do de
	 * cada uma.
	 */
	public int getProfundidade() {

		int total = 0;
		total += 1;
		for (int i = 0; i < obras.length; i++) {
			if (obras[i] != null && obras[i] instanceof Coleccao) {
				Coleccao c = (Coleccao) obras[i];
				total += c.getProfundidade();
			}
		}
		return total;
	}

	/**
	 * Duas colec��es s�o iguais se tiverem o mesmo t�tulo e a mesma lista de
	 * editores. Deve utilizar o equals da classe Obra. Para verificar verificar se
	 * os editores s�o os mesmos devem utilizar o m�todo mergeWithoutRepetitions
	 */
	public boolean equals(Object c) {
		String[] test = mergeWithoutRepetitions(this.editores, ((Coleccao) c).editores);
		// return (c != null) && getTitulo().equalsIgnoreCase(c.getTitulo())
		// && (test.length == this.editores.length && test.length == c.editores.length);
		return super.equals(c)
				&& (test.length == this.editores.length && test.length == ((Coleccao) c).editores.length);
	}

	/**
	 * Deve devolver uma string compat�vel com os outputs desejados
	 */
	public String toString() {

		return super.toString() + Arrays.toString(getAutoresEditores()) + ", com " + getNumLivros() + " livros, com "
				+ getNumColeccoes() + " cole��es e com profundidade m�xima de " + getProfundidade();
	}

	/**
	 * Mostra uma colec��o segundo os outputs desejados. Deve utilizar o m�todo
	 * print da classe Obra.
	 */
	public void print(String prefix) {
		System.out.println(prefix + this);
		for (int j = 0; j < this.obras.length; j++) {
			if (this.obras[j] != null && this.obras[j] instanceof Livro)
				((Livro) this.obras[j]).print(" " + prefix);
		}
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof Coleccao)
				((Coleccao) this.obras[i]).print(" " + prefix);
		}
	}

	/**
	 * main
	 */
	public static void main(String[] args) {
		Livro l1 = new Livro("Viagem aos Himalaias", 340, 12.3f, new String[] { "Jo�o Mendon�a", "M�rio Andrade" });
		Livro l2 = new Livro("Viagem aos Pirin�us", 270, 11.5f, new String[] { "Jo�o Mendon�a", "J�lio Pomar" });

		Coleccao c1 = new Coleccao("Primavera", new String[] { "Jo�o Mendon�a", "Manuel Alfazema" });

		boolean res;

		res = c1.addObra(l1);
		res = c1.addObra(l2);
		System.out.println("c1 -> " + c1);
		c1.print("");
		System.out.println();

		// adicionar um livro com nome de outro j� existente
		res = c1.addObra(l2);
		System.out.println("adi��o novamente de Viagem aos Pirin�us a c1 -> " + res);
		System.out.println("c1 -> " + c1);
		System.out.println();

		// Outra colec��o
		Livro l21 = new Livro("Viagem aos Himalaias 2", 340, 12.3f, new String[] { "Jo�o Mendon�a", "M�rio Andrade" });
		Livro l22 = new Livro("Viagem aos Pirin�us 2", 270, 11.5f, new String[] { "Jo�o Mendon�a", "J�lio Pomar" });

		Coleccao cx2 = new Coleccao("Outono", new String[] { "Jo�o Mendon�a", "Manuel Antunes" });
		cx2.addObra(l21);
		cx2.addObra(l22);
		System.out.println("cx2 -> " + cx2);
		cx2.print("");
		System.out.println();

		// adicion�-la a c1
		c1.addObra(cx2);
		System.out.println("c1 ap�s adi��o da colec��o cx2 -> " + c1);
		c1.print("");
		System.out.println();

		// get editores autores
		String[] ae = c1.getAutoresEditores();
		System.out.println("Autores editores of c1 -> " + Arrays.toString(ae));
		System.out.println();

		// getNumObrasFromPerson
		String nome = "Jo�o Mendon�a";
		int n = c1.getNumObrasFromPerson(nome);
		System.out.println("N� de obras de " + nome + " -> " + n);
		System.out.println();

		// getLivrosComoAutor
		nome = "Jo�o Mendon�a";
		Livro[] livros = c1.getLivrosComoAutor(nome);
		System.out.println("Livros de " + nome + " -> " + Arrays.toString(livros));
		System.out.println();
		System.out.println();

		// testes aos m�todos getNumLivros, getNumColeccoes e getProfundidade
		c1.print("");
		System.out.println("N� de livros na colec��o " + c1.getTitulo() + " -> " + c1.getNumLivros());

		System.out.println("N� de colec��es dentro da colec��o " + c1.getTitulo() + " -> " + c1.getNumColeccoes());

		System.out.println("Profundidade da colec��o " + c1.getTitulo() + " -> " + c1.getProfundidade());
		System.out.println("Profundidade da colec��o " + cx2.getTitulo() + " -> " + cx2.getProfundidade());
		System.out.println();

		// rem livro
		String nomeLivro = "Viagem aos Himalaias";
		Obra l = c1.remObra(nomeLivro);
		System.out.println("Remo��o de " + nomeLivro + " -> " + l);
		c1.print("");

	}
}