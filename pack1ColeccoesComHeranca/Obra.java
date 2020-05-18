package tps.tp3.pack1ColeccoesComHeranca;

public abstract class Obra {

	private String titulo;

	/**
	 * Constructor
	 */
	public Obra(String titulo) {
		if (titulo == null || titulo.length() == 0)
			throw new IllegalArgumentException("O titulo tem de ter pelo menos um caracter");
		if (!validarNome(titulo))
			throw new IllegalArgumentException("O nome do título é inválido");
		this.titulo = titulo;
	}

	/**
	 * Devolve o título da obra
	 */
	public String getTitulo() {

		 return this.titulo;
	}

	/**
	 * Devolve o número de páginas da obra
	 */
	public abstract int getNumPaginas();

	/**
	 * Devolve o preço da obra
	 */
	public abstract float getPreco();

	/**
	 * Deve devolver true se o array conter apenas nomes válidos. Cada nome deve ser
	 * validado pelo método validarNome
	 */
	public static boolean validarNomes(String[] nomes) {
		// TODO
		int tamanhoString = nomes.length; //o sistema sabe quantos nomes diferentes há para fazer o ciclo for
		
		for (int i = 0; i < tamanhoString; i ++) {  //ciclo for para validar todos os nomes
			validarNome(nomes[i]);
			
			if (validarNome(nomes[i]) == false) {	//se o um dos nomes não for válido, retorna false
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Um nome válido se não for null e conter pelo menos uma letra
	 * (Character.isLetter) e só conter letras e espaços (Character.isWhitespace)
	 */
	public static boolean validarNome(String nome) {
		// TODO
		int tamanhoString = nome.length(); //o sistema sabe quantos caracteres há na string para fazer o ciclo for
		
		for(int i = 0; i < tamanhoString;) { //ciclo for para validar todos os caractes
			char c = nome.charAt(i);
			if(Character.isLetter(c) || Character.isWhitespace(c) ||  Character.isDigit(c)) { //se o caracter for uma letra ou um espaço, o ciclo for passa ao caracter seguinte

			}
			else {										//se nao for uma letra ou um espaço, retorna false
		
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * Recebe um nome já previamente validado, ou seja só com letras ou espaços.
	 * Deve devolver o mesmo nome mas sem espaços (utilizar trim e
	 * Character.isWhitespace) no início nem no fim e só com um espaço ' ' entre
	 * cada nome. Deve utilizar um StringBuilder para ir contendo o nome já
	 * corrigido
	 */
	public static String removeExtraSpaces(String nome) {
		
		StringBuilder strNome = new StringBuilder();
		char[] chars = nome.trim().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i])
					|| (!Character.isWhitespace(chars[i - 1]) && Character.isWhitespace(chars[i]))) {
				strNome.append(chars[i]);
			}
		}
		nome = strNome.toString();
		return nome;
	}

	/**
	 * Método que verifica se há elementos repetidos. O array recebido não contém
	 * nulls.
	 */
	public static boolean haRepeticoes(String[] elems) {
		
		int tamanhoString = elems.length; //o sistema sabe quantos nomes diferentes há para fazer o ciclo for
		if (tamanhoString > 1) {
			for (int i = 0; i < tamanhoString; i++) {  //ciclo for para validar todos os nomes
				String nome1 = elems[i];
				for (int j = i+1; j < tamanhoString - i; j++) {
					String nome2 = elems[j];
					if (nome1 == nome2) {	//se o um dos nomes não for válido, retorna false
					return false;
					}
				}
				 
			}
		}
		return true;
	}

	/**
	 * Devolve uma string com a informação da obra (ver outputs desejados e método
	 * toString de Livro)
	 */
	public String toString() {
		return getTitulo() + ", " + getNumPaginas()+ "p, " + getPreco() + "€, ";
		
	}

	/**
	 * Deve mostrar na consola a informação da obra (toString) precedida do prefixo
	 * recebido
	 */
	public void print(String prefix) {
		System.out.println(prefix + this);
	}

	/**
	 * O Object recebido é igual, se não for null, se for uma obra e se tiver o
	 * mesmo título que o título da obra corrente
	 */
	public boolean equals(Object l) {
		return (l != null) && this.getTitulo().equalsIgnoreCase(((Obra) l).getTitulo());
		
	}

}