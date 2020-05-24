package tps.tp3.pack1ColeccoesComHeranca;

import java.util.Arrays;

/**
 * Classe Coleccao, deve conter a descrição de uma colecção, com título, os seus
 * livros, colecções e editores. Deve utilizar herança para guardar os livros e
 * as colecções num só array
 */
public class Coleccao extends Obra implements IColeccao {
	// número máximo de obras de uma colecção
	private static int MAXOBRAS = 20;

	// Array de obras, de Livros ou Coleccçõe, em que estas devem encontrar-se
	// sempre nos menores índices e pela ordem de registo
	private Obra[] obras = new Obra[MAXOBRAS];

	// deverá conter sempre o número de obras na colecção
	private int numObras = 0;

	// Editores, tem as mesmas condicionantes que array de autores na classe
	// livro
	private String[] editores;

	/**
	 * Construtor; o título deve ser guardado e validado na clase obra; o array de
	 * editores devem ser pelo menos um e tem as mesmas restrições que os autores
	 * dos livros;
	 */
	public Coleccao(String titulo, String[] editores) {

		super(titulo);
		if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException("O titulo tem de ter pelo menos um caracter");
		if (!Obra.validarNome(titulo))
			throw new IllegalArgumentException("O nome do título é inválido");

		if (editores == null || editores.length < 1)
			throw new IllegalArgumentException("A coleção tem de ter pelo menos um editor");
		if (!Obra.validarNomes(editores))
			throw new IllegalArgumentException("O editor tem de ter o nome válido");
		if (!Obra.haRepeticoes(editores))
			throw new IllegalArgumentException("O array de editores contém editores repetidos");
		for (int i = 0; i < editores.length; i++) {
			editores[i] = Obra.removeExtraSpaces(editores[i]);
		}

		this.editores = Arrays.copyOf(editores, editores.length);
	}

	/**
	 * Obtem o número total de páginas da colecção, páginas dos livros e das
	 * colecções
	 */
	@Override
	public int getNumPaginas() {

		int numPag = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null)
				numPag += this.obras[i].getNumPaginas();
		}
		return numPag;
	}

	/**
	 * As colecções com mais de 5000 páginas nos seus livros directos têm um
	 * desconto de 20% nesses livros. As colecções em que o somatório de páginas das
	 * suas subcolecções directas seja igual ou superior ao quádruplo do nº de
	 * páginas da sua subcolecção directa com mais páginas deverão aplicar um
	 * desconto de 10% sobre os preços das suas subcolecções
	 */

	@Override
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
	 * Adiciona uma obra à colecção se puder, se esta não for null e a colecção não
	 * ficar com obras com iguais no seu nível imediato. Deve utilizar o método
	 * getIndexOfLivro e getIndexOfColeccao
	 */
	@Override
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
	 * Devolve -1 caso não o encontre
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
	 * Remove do array a obra com o título igual ao título recebido. Devolve a obra
	 * removida ou null caso não tenha encontrado a obra. Deve-se utilizar o método
	 * getIndexOfLivro. Recorda-se que as obras ocupam sempre os menores índices, ou
	 * seja, não pode haver nulls entre elas.
	 */
	@Override
	public IObra remObra(String titulo) {

		int index = getIndexOfObra(titulo);
		IObra toRemove = (index == -1) ? null : this.obras[index];
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
	 * Remove todas as obras (livros ou colecções) dentro da obra corrente, que
	 * tenham um título igual ou título recebido. Devolve true se removeu pelo menos
	 * uma obra, ou false caso não tenha trealizado qualquer remoção. Deve utilizar
	 * os métodos remObra e remAllObra.
	 */
	@Override
	public boolean remAllObra(String titulo) {

		int n = numObras;
		int oLength = obras.length;
		for(int i = 0; i < oLength ; i++) {
			remObra(titulo);
		}
		if (numObras != n) {
			return true;
		}
		return false;
	}

	/**
	 * Devolve o nº de obras de uma pessoa. Cada colecção deve contabilizar-se como
	 * uma obra para os editores.
	 */
	@Override
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
				IColeccao c = (IColeccao) obras[i];
				countLivros += c.getNumObrasFromPerson(autorEditor);
			} else if (obras[i] != null && !(obras[i] instanceof Coleccao)) {
				ILivro l = (ILivro) obras[i];
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
	 * Deve devolver um novo array, sem repetições, com os livros de que o autor
	 * recebido é autor. O array devolvido não deve conter repetições, para excluir
	 * as repetições devem utilizar o método mergeWithoutRepetitions
	 */
	@Override
	public ILivro[] getLivrosComoAutor(String autorNome) {
		int count = 0;
		for (int i = 0; i < this.obras.length; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof ILivro) {
				if (((ILivro)this.obras[i]).contemAutor(autorNome))
					count++;
			}
		}
		ILivro[] newList = new ILivro[count];
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof ILivro) {
				if (((ILivro)this.obras[i]).contemAutor(autorNome))
					newList[i] = (ILivro) obras[i];
			}
		}
		return newList;
	}

	/**
	 * Deve devolver um array, sem nulls, com todos os autores e editores existentes
	 * na colecção. O resultado não deve conter repetições. Deve utilizar o método
	 * mergeWithoutRepetitions
	 */
	@Override
	public String[] getAutoresEditores() {
		
		String [] aux1 = editores;

		for(int i=0; i<obras.length; i++) {
			if(obras[i]!=null && !(obras[i] instanceof Coleccao)) {
				ILivro l = (ILivro) obras[i];
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
	 * Método que recebendo dois arrays sem repetições devolve um novo array com
	 * todos os elementos dos arrays recebidos mas sem repetições
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
	 * Método idêntico ao método anterior mas agora com arrays de livros
	 */
	private static ILivro[] mergeWithoutRepetitions(ILivro[] a1, ILivro[] a2) {

		ILivro[] test = new ILivro[a1.length + a2.length];
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
		ILivro[] result = new ILivro[current];
		for (int i = 0; i < current; i++) {
			result[i] = test[i];
		}
		return result;
	}

	/**
	 * Devolve o nº de livros dentro da colecção
	 */
	@Override
	public int getNumLivros() {
		int numLivros = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] instanceof ILivro)
				numLivros++;
			if(this.obras[i] instanceof Coleccao) 
				numLivros += ((IColeccao)this.obras[i]).getNumLivros();
		}
		return numLivros;
	}

	/**
	 * Devolve o nº de colecções dentro da colecção
	 */
	@Override
	public int getNumColeccoes() {
		
		int numColecoes = 0;
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] instanceof Coleccao)
				numColecoes++;
		}
		return numColecoes;

	}

	/**
	 * Devolve a profundidada de máxima de uma colecção em termos de coleccões
	 * dentro de coleccções: uma colecção c1 com uma colecção c2 dentro, c1 deve
	 * devolver 2 e c2 deve devolver 1, independentemente do número do conteúdo de
	 * cada uma.
	 */
	@Override
	public int getProfundidade() {

		int total = 0;
		total += 1;
		for (int i = 0; i < obras.length; i++) {
			if (obras[i] != null && obras[i] instanceof Coleccao) {
				IColeccao c = (IColeccao) obras[i];
				total += c.getProfundidade();
			}
		}
		return total;
	}

	/**
	 * Duas colecções são iguais se tiverem o mesmo título e a mesma lista de
	 * editores. Deve utilizar o equals da classe Obra. Para verificar verificar se
	 * os editores são os mesmos devem utilizar o método mergeWithoutRepetitions
	 */
	@Override
	public boolean equals(Object c) {
		String[] test = mergeWithoutRepetitions(this.editores, ((Coleccao) c).editores);
		return super.equals(c) && (test.length == this.editores.length && test.length == ((Coleccao) c).editores.length);
	}

	/**
	 * Deve devolver uma string compatível com os outputs desejados
	 */
	@Override
	public String toString() {

		return super.toString() + Arrays.toString(getAutoresEditores()) + ", com " + getNumLivros() + " livros, com "
				+ getNumColeccoes() + " coleções e com profundidade máxima de " + getProfundidade();
	}

	/**
	 * Mostra uma colecção segundo os outputs desejados. Deve utilizar o método
	 * print da classe Obra.
	 */
	@Override
	public void print(String prefix) {
		System.out.println(prefix + this);
		for (int j = 0; j < this.obras.length; j++) {
			if (this.obras[j] != null && this.obras[j] instanceof ILivro)
				((Livro) this.obras[j]).print(" " + prefix);
		}
		for (int i = 0; i < numObras; i++) {
			if (this.obras[i] != null && this.obras[i] instanceof Coleccao)
				((IColeccao) this.obras[i]).print(" " + prefix);
		}
	}

	/**
	 * main
	 */
	public static void main(String[] args) {
		Livro l1 = new Livro("Viagem aos Himalaias", 340, 12.3f, new String[] { "João Mendonça", "Mário Andrade" });
		Livro l2 = new Livro("Viagem aos Pirinéus", 270, 11.5f, new String[] { "João Mendonça", "Júlio Pomar" });

		Coleccao c1 = new Coleccao("Primavera", new String[] { "João Mendonça", "Manuel Alfazema" });

		boolean res;

		res = c1.addObra(l1);
		res = c1.addObra(l2);
		System.out.println("c1 -> " + c1);
		c1.print("");
		System.out.println();

		// adicionar um livro com nome de outro já existente
		res = c1.addObra(l2);
		System.out.println("adição novamente de Viagem aos Pirinéus a c1 -> " + res);
		System.out.println("c1 -> " + c1);
		System.out.println();

		// Outra colecção
		Livro l21 = new Livro("Viagem aos Himalaias 2", 340, 12.3f, new String[] { "João Mendonça", "Mário Andrade" });
		Livro l22 = new Livro("Viagem aos Pirinéus 2", 270, 11.5f, new String[] { "João Mendonça", "Júlio Pomar" });

		Coleccao cx2 = new Coleccao("Outono", new String[] { "João Mendonça", "Manuel Antunes" });
		cx2.addObra(l21);
		cx2.addObra(l22);
		System.out.println("cx2 -> " + cx2);
		cx2.print("");
		System.out.println();

		// adicioná-la a c1
		c1.addObra(cx2);
		System.out.println("c1 após adição da colecção cx2 -> " + c1);
		c1.print("");
		System.out.println();

		// get editores autores
		String[] ae = c1.getAutoresEditores();
		System.out.println("Autores editores of c1 -> " + Arrays.toString(ae));
		System.out.println();

		// getNumObrasFromPerson
		String nome = "João Mendonça";
		int n = c1.getNumObrasFromPerson(nome);
		System.out.println("Nº de obras de " + nome + " -> " + n);
		System.out.println();

		// getLivrosComoAutor
		nome = "João Mendonça";
		ILivro[] livros = c1.getLivrosComoAutor(nome);
		System.out.println("Livros de " + nome + " -> " + Arrays.toString(livros));
		System.out.println();
		System.out.println();

		// testes aos métodos getNumLivros, getNumColeccoes e getProfundidade
		c1.print("");
		System.out.println("Nº de livros na colecção " + c1.getTitulo() + " -> " + c1.getNumLivros());

		System.out.println("Nº de colecções dentro da colecção " + c1.getTitulo() + " -> " + c1.getNumColeccoes());

		System.out.println("Profundidade da colecção " + c1.getTitulo() + " -> " + c1.getProfundidade());
		System.out.println("Profundidade da colecção " + cx2.getTitulo() + " -> " + cx2.getProfundidade());
		System.out.println();

		// rem livro
		String nomeLivro = "Viagem aos Himalaias";
		IObra l = c1.remObra(nomeLivro);
		System.out.println("Remoção de " + nomeLivro + " -> " + l);
		c1.print("");

		// teste ao mergeWithoutRepetitions com livros
		System.out.println();
		ILivro book1 = new Livro("Codex 632", 33, 22.2f, new String[] {"José Rodrigues dos Santos"});
		ILivro book2 = new Livro("Eu Robot", 33, 22.2f, new String[] {"Isaac Asimov"});
		ILivro book3 = new Livro("Fundação", 33, 22.2f, new String[] {"Isaac Asimov"});
		ILivro[] bookArr = {book1, book2};
		ILivro[] bookArr2 = {book2, book3};	
		ILivro[] mergedBooks = mergeWithoutRepetitions(bookArr, bookArr2);
		System.out.println("Livros de bookArr: "+ Arrays.toString(bookArr));
		System.out.println("Livros de bookArr2: "+ Arrays.toString(bookArr2));
		System.out.println("Livros fundidos: "+ Arrays.toString(mergedBooks));
	}
}