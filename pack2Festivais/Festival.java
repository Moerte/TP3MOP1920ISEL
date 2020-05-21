package tps.tp3.pack2Festivais;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Festival que estende a class Evento
 */
public class Festival extends Evento implements IFestival {
	// Número máximo de eventos
	private int numEventos = 20;
	// Array de eventos
	private IEvento[] eventos = new IEvento[numEventos];
	
	/**
	 * Contrutor da class Festival que recebe o nome do festival e o valida na class Evento
	 * @param nome
	 */
	public Festival(String nome) {
		super(nome);

	}
	
	/**
	 * Devolve o número de bilhetes do espetaculo
	 * @return número de bilhetes
	 */
	@Override
	public int getNumBilhetes() {
		int count = 0;
		for (int i = 0; i < this.eventos.length; i++) {
			if (eventos[i] != null)
				count += eventos[i].getNumBilhetes();
		}
		return count;
	}

	/**
	 * Devolve o numero de atuações do artista
	 * @param artista a verificar (String)~
	 * @return count - número de atuações do artista
	 */
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

	/**
	 * Devolve uma string com a informação do festival
	 * Formato: Festival 'Nome do evento' com 'Número de bilhetes' bilhetes e com os artistas ['os artistas']
	 */
	@Override
	public String toString() {
		return "Festival " + super.toString();
	}

	/**
	 * Devolve os artistas do espetaculo num array sem nulls
	 * @return array sem nulls
	 */
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

	/**
	 * Devolve a profundidade do festival
	 * @return número de festivais de profundidade
	 */
	private int getDeepFestival() {
		int numFestivais = 0;
		for (int i = 0; i < eventos.length; i++) {
			if(eventos[i] != null && eventos[i] instanceof Festival) {
				numFestivais++;
				Festival f = (Festival) eventos[i];
				numFestivais += f.getDeepFestival();
			}
		}
		return numFestivais;
	}

	/**
	 * Adiciona o evento se a condições forem preenchidas, isto é, se houver espaço no array de eventos e se um dos artista nao tiver mais que dois eventos onde irá atuar
	 * @param evento a adicionar
	 * @return True se o evento for adicionado, False caso contrario
	 */
	@Override
	public boolean addEvento(IEvento evento) {
		
		String[] artistas = evento.getArtistas();
		for (int i = 0; i < artistas.length; i++) {
			if(numActuacoes(artistas[i]) == 2) return false;
		}
		for (int i = 0; i < eventos.length; i++) {
			if (eventos[i] == null) {
				eventos[i] = evento;
				return true;
			}
		}
		return false;
	}

	/**
	 * Apaga o evento com o titulo inserido e devolve true se este for apagado
	 * @param nomeEvento a apagar
	 * @return True se o evento for apagado, False caso contrario
	 */
	@Override
	public boolean delEvento(String nomeEvento) {
		for (int i = 0; i < eventos.length; i++) {
			if (nomeEvento.equalsIgnoreCase(eventos[i].getNome())) {
				eventos[i] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		// Criar um espetaculo
		Espetaculo E1 = new Espetaculo("Os Melhores Momentos 2020", "Seixal", 22);
		E1.addArtista("Nuno Oliveira");
		E1.addArtista("Joana Oliveira");
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
		
		// Criar outro espetaculo
		Espetaculo E4 = new Espetaculo("Quim Barreiros Live", "Paio Pires", 100);
		E4.addArtista("José Marques");
		E4.addArtista("Nuno Oliveira");
		System.out.println("Espetaculo 4: " + E4);
		
		// Criar um Festival
		System.out.println();
		Festival F1 = new Festival("Loucura Total");
		
		// Adicionar dois Espetaculos
		F1.addEvento(E1);
		F1.addEvento(E2);
		System.out.println("Festival 1: " + F1);
		
		// Adicionar um terceiro espetaculo com um artista repetido
		F1.addEvento(E3);
		System.out.println("Festival com artista repetido: " + F1);
		
		// Apagar um espetaculo
		System.out.println();
		F1.delEvento("Ola Ola");
		System.out.println("Evento 'Ola Ola' apagado: " + F1);
		
		// Número de atuações
		System.out.println();
		System.out.println("Número de atuações de Nuno Oliveira: " + F1.numActuacoes("Nuno Oliveira"));
		
		// Tentar adicionar um evento em que o um dos artistas ja tenha 2 eventos
		System.out.println();
		System.out.println("Adicionar um espetaculo onde o artista Nuno Oliveira ja tem mais de dois eventos: "+ F1.addEvento(E4));
		System.out.println("Número de atuações de Nuno Oliveira: " + F1.numActuacoes("Nuno Oliveira"));
		
		// Profundidade do Festival 1
		System.out.println();
		Festival F2 = new Festival("Sub Loucura Total");
		F2.addEvento(E3);
		F1.addEvento(F2);
		System.out.println("Festival com mais um festival dentro dele: "+ F1);
		System.out.println("Profundidade do Festival: "+ F1.getDeepFestival());
		
		// Profundidade do Festival 2
		System.out.println();
		Festival F3 = new Festival("Sub da Sub Loucura Total");
		F2.addEvento(F3);
		System.out.println("Festival 2: "+F2);
		System.out.println("Profundidade do Festival: "+ F2.getDeepFestival());
	}

}
