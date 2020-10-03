package semaphoreImpl;

import enums.StatusGarfo;
import javafx.util.Pair;

public class Garfos {

	private static Garfo[] garfos;
	
	public Garfos(int qtd) {
		Garfos.garfos = new Garfo[qtd];
		for (int i = 0; i < Garfos.garfos.length; i++) {
			Garfos.garfos[i] = new Garfo();
		}
	}
	
	private static Pair<Integer, Integer> getGarfoDiretaEsquerda(int idFilosofo, int qtdFilosofos) {
		int indexGarfoD, indexGarfoE = -1;
		
		if(idFilosofo == 1) {
			indexGarfoD = qtdFilosofos - 1;
		}
		else {
			indexGarfoD = idFilosofo - 2;
		}
		indexGarfoE = idFilosofo - 1;
		
		return new Pair<Integer, Integer>(indexGarfoE, indexGarfoD);
	}
	
	public static boolean getGarfos(int idFilosofo, int qtdFilosofos) {
		Pair<Integer, Integer> indexGarfos = Garfos.getGarfoDiretaEsquerda(idFilosofo, qtdFilosofos);
		
		Garfo garfoD = Garfos.garfos[indexGarfos.getValue()]; 
		try {
			garfoD.getSemaforo().acquire();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Não pode pegar o garfo da direita 
		if(garfoD.getStatusGarfo() == StatusGarfo.OCUPADO) {
			garfoD.getSemaforo().release();
			return false;
		}
		else {
			garfoD.setStatusGarfo(StatusGarfo.OCUPADO);
			garfoD.getSemaforo().release();
		}
		
		
		Garfo garfoE = Garfos.garfos[indexGarfos.getKey()]; 
		try {
			garfoE.getSemaforo().acquire();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Não pode pegar o garfo da esquerda 
		if(garfoE.getStatusGarfo() == StatusGarfo.OCUPADO) {
			garfoE.getSemaforo().release();	
			// Solta o garfo da direita
			try {
				garfoD.getSemaforo().acquire();
				garfoD.setStatusGarfo(StatusGarfo.LIVRE);
				garfoD.getSemaforo().release();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return false;
		}
		else {
			garfoE.setStatusGarfo(StatusGarfo.OCUPADO);
			garfoE.getSemaforo().release();
		}
		
		
		return true;
	}
	
	public static void liberarGarfos(int idFilosofo, int qtdFilosofos) {
		Pair<Integer, Integer> indexGarfos = Garfos.getGarfoDiretaEsquerda(idFilosofo, qtdFilosofos);
		
		Garfos.garfos[indexGarfos.getValue()].setStatusGarfo(StatusGarfo.LIVRE);
		Garfos.garfos[indexGarfos.getKey()].setStatusGarfo(StatusGarfo.LIVRE);
	}
}
