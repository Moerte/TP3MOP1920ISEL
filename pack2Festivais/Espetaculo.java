package tps.tp3.pack2Festivais;

import java.util.Arrays;

/**
 * Class Espetaculo que estende a class Evento
 */
public class Espetaculo extends Evento implements IEspetaculo {
	// N�mero maximo de artistas possiveis por espetaculo
	private int nArtistas = 10;
	// Array de artistas com tamanho de 10 artistas
	private String[] artistas = new String[nArtistas];
	// N�mero de bilhetes do espetaculo
	private int numBilhetes;
	// Local onde ser� realizado o espetaculo
	private String localidade;

	/**
	 * Contrutor da class que recebe o nome do espetaculo, o local do espetaculo e o n�mero de bilhetes do espetaculo e os valida
	 * @param nome do espetaculo (String)
	 * @param localidade do espetaculo (String)
	 * @param numBilhetes (Inteiro)
	 */
	public Espetaculo(String nome, String localidade, int numBilhetes) {
		super(nome);
		if (localidade == null || localidade.length() == 0) {
			throw new IllegalArgumentException("A localidade deve ter pelo menos um caratere");
		}
		this.localidade = localidade;
		if (numBilhetes < 0) {
			throw new IllegalArgumentException("O n�mero de bilhetes tem  de ser um n�mero positivo");
		}
		this.numBilhetes = numBilhetes;

	}

	/**
	 * Devolve o numero de atua��es do artista
	 * @param artista a verificar (String)~
	 * @return count - n�mero de atua��es do artista
	 */
	@Override
	public int numActuacoes(String artista) {
		String[] artistSearch = getArtistas();
		int count = 0;
		for (int i = 0; i < artistSearch.length; i++) {
			if (artistSearch[i].equalsIgnoreCase(artista)) {
				count++;
			}
		}
		return count;
	}
	/**
	 * Adiciona o artista ao evento e devolve true se as condi��es forem preenchidas
	 * @param artista a adicionar (String)
	 * @return True se for adicionado, False caso contrario
	 */
	@Override
	public boolean addArtista(String artista) {
		if (artista == null)
			return false;
		for (int i = 0; i < this.artistas.length; i++) {
			if (this.artistas[i] != null) {
				if (this.artistas[i].equalsIgnoreCase(artista))
					return false;
			}
			if (this.artistas[i] == null) {
				this.artistas[i] = artista;
				return true;
			}
		}
		return false;
	}
	/**
	 * Devolve o n�mero de bilhetes do espetaculo
	 * @return n�mero de bilhetes
	 */
	@Override
	public int getNumBilhetes() {
		return this.numBilhetes;
	}

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
	@Override
	public String[] getArtistas() {
		int count = 0;
		for (int i = 0; i < this.artistas.length; i++) {
			if (this.artistas[i] != null)
				count++;
		}
		String[] newArr = new String[count];
		for (int i = 0; i < this.artistas.length; i++) {
			if (this.artistas[i] != null)
				newArr[i] = this.artistas[i];
		}
		return newArr;
	}
	
	/**
	 * Devolve uma string com a informa��o do espetaculo
	 * Formato: 'Nome do evento' com 'N�mero de bilhetes' bilhetes e com os artistas ['os artistas'] em 'local do espetaculo'
	 */
	@Override
	public String toString() {
		return super.toString() + " em " + this.localidade;
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Criar o Espetaculo
		IEspetaculo E1 = new Espetaculo("A Vitoria dos infelizes 22", "Seixal", 22);
		// Adicionar artistas
		E1.addArtista("Nuno Oliveira");
		E1.addArtista("Eduardo Marques");
		System.out.println("Espetaculo E1: " + E1);
		// N�mero de atua��es de Nuno Oliveira
		System.out.println("Numero de Atua��es: " + E1.numActuacoes("Nuno Oliveira"));
		// Os artistas do espetaculo
		System.out.println("Quem s�o os artistas: " + Arrays.toString(E1.getArtistas()));
		// Tentativa de adi��o de um artista repetido
		System.out.println("Adi��o de um artista repetido: "+E1.addArtista("Nuno Oliveira"));
	}

}
