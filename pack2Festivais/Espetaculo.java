package tps.tp3.pack2Festivais;

/**
 *  TODO - Comentar o código
 *
 */
public class Espetaculo extends Evento {
	
	private int nArtistas = 10;
	
	private String[] artistas = new String[nArtistas];
	
	private int numBilhetes;
	
	private String localidade;

	public Espetaculo(String nome, String localidade, int numBilhetes) {
		super(nome);
		if(localidade == null||localidade.length() == 0) {
			throw new IllegalArgumentException("A localidade deve ter pelo menos um caratere");
		}
		this.localidade = localidade;
		if(numBilhetes < 0) {
			throw new IllegalArgumentException("O número de bilhetes tem  de ser um número positivo");
		}
		this.numBilhetes = numBilhetes;
		
	}
	
	@Override
	public int numActuacoes(String artista) {
		String[] artistSearch = getArtistas();
		int count = 0;
		for (int i = 0; i < artistSearch.length; i++) {
			if(artistSearch[i].equalsIgnoreCase(artista)) {
				count++;
			}
		}
		return count;
	}
	
	public boolean addArtista(String artista) {
		
		if(artista == null) return false;
		for (int i = 0; i < this.artistas.length; i++) {
			if(this.artistas[i] == null) {
				this.artistas[i] = artista;
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNumBilhetes() {
		return this.numBilhetes;
	}

	@Override
	public String[] getArtistas() {
		int count = 0;
		for (int i = 0; i < this.artistas.length; i++) {
			if(this.artistas[i] != null) count++;
		}
		String[] newArr = new String[count];
		for (int i = 0; i < this.artistas.length; i++) {
			if(this.artistas[i] != null) newArr[i] = this.artistas[i];
		}
		return newArr;
	}
	
	public String toString() {
		return super.toString() + " em " + this.localidade;
	}

	
	public static void main(String[] args) {
		
		Espetaculo E1 = new Espetaculo("A Vitoria dos infelizes 22", "Seixal", 22);
		E1.addArtista("Nuno Oliveira");
		
		System.out.println("Espetaculo E1: "+ E1);
		System.out.println("Numero de Atuações: "+ E1.numActuacoes("Nuno Oliveira"));

	}
	
}
