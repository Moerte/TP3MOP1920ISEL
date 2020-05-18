package tps.tp3.pack2Festivais;


/**
 * TODO - Comentar o código
 * TODO - Acabar os metodos
 * TODO - Fazer os testes do main
 */

public class Festival extends Evento {

	private int numEventos = 20;
	
	private Evento[] eventos = new Evento[numEventos];

	public Festival(String nome) {
		super(nome);

	}

	@Override
	public int getNumBilhetes() {
		int count = 0;
		for (int i = 0; i < this.eventos.length; i++) {
			count += eventos[i].getNumBilhetes();
		}
		return count;
	}

	@Override
	public int numActuacoes(String artista) {
		int count = 0;
		for (int i = 0; i < this.eventos.length; i++) {
			String[] artistas = eventos[i].getArtistas();
			for (int j = 0; j < artistas.length; j++) {
				if(artistas[j].equalsIgnoreCase(artista))count++;
			}
		}
		return count;
	}

	public String toString() {
		return "Festival "+ super.toString();
	}

	@Override
	public String[] getArtistas() {
		//TODO
		return null;
	}

	private int getDeepFestival() {
		//TODO
		return 0;
	}

	public boolean addEvento(Evento evento) {
		//TODO
		return false;
	}
	
	public boolean delEvento(String nomeEvento) {
		//TODO  
		return false;
	}

	public static void main(String[] args) {

	}

}
