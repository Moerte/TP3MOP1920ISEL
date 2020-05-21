package tps.tp3.pack2Festivais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO - Comentar o código TODO - Acabar os metodos TODO - Fazer os testes do
 * main
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
			if (eventos[i] != null)
				count += eventos[i].getNumBilhetes();
		}
		return count;
	}

	@Override
	public int numActuacoes(String artista) {
		int count = 0;
		for (int i = 0; i < this.eventos.length; i++) {
			if (eventos[i] != null) {
				String[] artistas = eventos[i].getArtistas();
				for (int j = 0; j < artistas.length; j++) {
					if (artistas[j].equalsIgnoreCase(artista))
						count++;
				}
			}
		}
		return count;
	}

	public String toString() {
		return "Festival " + super.toString();
	}

	@Override
	public String[] getArtistas() {
		List<String> artistList = new ArrayList<String>();
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] != null) {
				String[] artistsArr = eventos[i].getArtistas();
				for (int j = 0; j < artistsArr.length; j++) {

					if (!artistList.contains(artistsArr[j])) {
						artistList.add(artistsArr[j]);
					}
				}
			}
		}

		String[] newArrayOfArtists = new String[artistList.size()];
		newArrayOfArtists = artistList.toArray(newArrayOfArtists);
		return newArrayOfArtists;
	}

	private int getDeepFestival() {
		// TODO
		return 0;
	}

	public boolean addEvento(Evento evento) {
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] == null) {
				eventos[i] = evento;
				return true;
			}
		}
		return false;
	}

	public boolean delEvento(String nomeEvento) {
		for (int i = 0; i < eventos.length; i++) {
			if (nomeEvento.equalsIgnoreCase(eventos[i].getNome())) {
				eventos[i] = null;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// Criar um espetaculo
		Espetaculo E1 = new Espetaculo("Os Melhores Momentos 2020", "Seixal", 22);
		E1.addArtista("Nuno Oliveira");
		E1.addArtista("Mónica Oliveira");
		System.out.println("Espetaculo 1: " + E1);
		
		// Criar outro espetaculo
		Espetaculo E2 = new Espetaculo("Cenas Fantasticas", "Arrentela", 33);
		E2.addArtista("Nuno Oliveira");
		E2.addArtista("José Marques");
		System.out.println("Espetaculo 2: " + E2);
		
		// Criar outro espetaculo
		Espetaculo E3 = new Espetaculo("Ola Ola", "Fogueteiro", 20);
		E3.addArtista("Mónica Oliveira");
		E3.addArtista("José Marques");
		E3.addArtista("João Pires");
		System.out.println("Espetaculo 3: " + E3);
		
		// Criar um Festival
		System.out.println();
		Festival F1 = new Festival("Loucura Total");
		
		// Adicionar dois Espetaculos
		F1.addEvento(E1);
		F1.addEvento(E2);
		System.out.println("1º Festival 1: " + F1);
		
		// Adicionar um terceiro evento como teste sao getArtists()
		F1.addEvento(E3);
		System.out.println("2º Festival 1: " + F1);
		
		// Apagar um espetaculo
		System.out.println();
		F1.delEvento("Ola Ola");
		System.out.println("Evento 'Ola Ola' apagado: " + F1);
		// Número de atuações
		System.out.println();
		System.out.println("Número de atuações de Nuno Oliveira: " + F1.numActuacoes("Nuno Oliveira"));
	}

}
