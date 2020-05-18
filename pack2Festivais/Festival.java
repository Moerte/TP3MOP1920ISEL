package tps.tp3.pack2Festivais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
		List<String> artistList = new ArrayList<String>();
		for (int i = 0; i < eventos.length; i++) {
			if(eventos[i] != null) {
				if(!artistList.contains(eventos[i].getArtistas())) {
					artistList.add(eventos[i].getArtistas());
				}
			}
		}
		return null;
	}

	private int getDeepFestival() {
		//TODO
		return 0;
	}

	public boolean addEvento(Evento evento) {
		for (int i = 0; i < eventos.length; i++) {
			if(eventos[i] == null) eventos[i] = evento;
			return true;
		}
		return false;
	}
	
	public boolean delEvento(String nomeEvento) {
		for (int i = 0; i < eventos.length; i++) {
			if(nomeEvento.equalsIgnoreCase((Evento)Espetaculo[i].getTitulo()) eventos[i] = null;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		
		Espetaculo E1 = new Espetaculo("Os Melhores Momentos 2020", "Seixal", 22);
		
		

	}

}
