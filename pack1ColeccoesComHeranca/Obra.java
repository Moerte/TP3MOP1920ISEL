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
			throw new IllegalArgumentException("O nome do t�tulo � inv�lido");
		this.titulo = titulo;
	}

	/**
	 * Devolve o t�tulo da obra
	 */
	public String getTitulo() {

		 return this.titulo;
	}

	/**
	 * Devolve o n�mero de p�ginas da obra
	 */
	public abstract int getNumPaginas();

	/**
	 * Devolve o pre�o da obra
	 */
	public abstract float getPreco();

	/**
	 * Deve devolver true se o array conter apenas nomes v�lidos. Cada nome deve ser
	 * validado pelo m�todo validarNome
	 */
	public static boolean validarNomes(String[] nomes) {
		// TODO
		int tamanhoString = nomes.length; //o sistema sabe quantos nomes diferentes h� para fazer o ciclo for
		
		for (int i = 0; i < tamanhoString; i ++) {  //ciclo for para validar todos os nomes
			validarNome(nomes[i]);
			
			if (validarNome(nomes[i]) == false) {	//se o um dos nomes n�o for v�lido, retorna false
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Um nome v�lido se n�o for null e conter pelo menos uma letra
	 * (Character.isLetter) e s� conter letras e espa�os (Character.isWhitespace)
	 */
	public static boolean validarNome(String nome) {
		// TODO
		int tamanhoString = nome.length(); //o sistema sabe quantos caracteres h� na string para fazer o ciclo for
		
		for(int i = 0; i < tamanhoString;) { //ciclo for para validar todos os caractes
			char c = nome.charAt(i);
			if(Character.isLetter(c) || Character.isWhitespace(c) ||  Character.isDigit(c)) { //se o caracter for uma letra ou um espa�o, o ciclo for passa ao caracter seguinte

			}
			else {										//se nao for uma letra ou um espa�o, retorna false
		
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * Recebe um nome j� previamente validado, ou seja s� com letras ou espa�os.
	 * Deve devolver o mesmo nome mas sem espa�os (utilizar trim e
	 * Character.isWhitespace) no in�cio nem no fim e s� com um espa�o ' ' entre
	 * cada nome. Deve utilizar um StringBuilder para ir contendo o nome j�
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
	 * M�todo que verifica se h� elementos repetidos. O array recebido n�o cont�m
	 * nulls.
	 */
	public static boolean haRepeticoes(String[] elems) {
		
		int tamanhoString = elems.length; //o sistema sabe quantos nomes diferentes h� para fazer o ciclo for
		if (tamanhoString > 1) {
			for (int i = 0; i < tamanhoString; i++) {  //ciclo for para validar todos os nomes
				String nome1 = elems[i];
				for (int j = i+1; j < tamanhoString - i; j++) {
					String nome2 = elems[j];
					if (nome1 == nome2) {	//se o um dos nomes n�o for v�lido, retorna false
					return false;
					}
				}
				 
			}
		}
		return true;
	}

	/**
	 * Devolve uma string com a informa��o da obra (ver outputs desejados e m�todo
	 * toString de Livro)
	 */
	public String toString() {
		return getTitulo() + ", " + getNumPaginas()+ "p, " + getPreco() + "�, ";
		
	}

	/**
	 * Deve mostrar na consola a informa��o da obra (toString) precedida do prefixo
	 * recebido
	 */
	public void print(String prefix) {
		System.out.println(prefix + this);
	}

	/**
	 * O Object recebido � igual, se n�o for null, se for uma obra e se tiver o
	 * mesmo t�tulo que o t�tulo da obra corrente
	 */
	public boolean equals(Object l) {
		return (l != null) && this.getTitulo().equalsIgnoreCase(((Obra) l).getTitulo());
		
	}

}