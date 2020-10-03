package monitorImpl;

import enums.StatusGarfo;
import javafx.util.Pair;
import monitorImpl.Garfo;
import monitorImpl.Garfos;

public class Garfos {

	private Garfo[] garfos;
	
	public Garfos(int qtd) {
		this.garfos = new Garfo[qtd];
		for (int i = 0; i < this.garfos.length; i++) {
			this.garfos[i] = new Garfo();
		}
	}
	
	private Pair<Integer, Integer> getGarfoDiretaEsquerda(int idFilosofo, int qtdFilosofos) {
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
	
	synchronized public void getGarfos(int idFilosofo, int qtdFilosofos) throws InterruptedException {
		Pair<Integer, Integer> indexGarfos = this.getGarfoDiretaEsquerda(idFilosofo, qtdFilosofos);

		
		Garfo garfoD = this.garfos[indexGarfos.getValue()]; 
		Garfo garfoE = this.garfos[indexGarfos.getKey()]; 

		// Não pode pegar o garfo da direita -> fica esperando
		if(garfoD.getStatusGarfo() == StatusGarfo.OCUPADO) {
			wait();
		}
		// Não pode pegar o garfo da esquerda -> fica esperando
		if(garfoE.getStatusGarfo() == StatusGarfo.OCUPADO) {
			wait();
		}
		
		garfoD.setStatusGarfo(StatusGarfo.OCUPADO);
		garfoE.setStatusGarfo(StatusGarfo.OCUPADO);
		
	}
	
	synchronized public void liberarGarfos(int idFilosofo, int qtdFilosofos) {
		Pair<Integer, Integer> indexGarfos = this.getGarfoDiretaEsquerda(idFilosofo, qtdFilosofos);
		
		this.garfos[indexGarfos.getValue()].setStatusGarfo(StatusGarfo.LIVRE);
		this.garfos[indexGarfos.getKey()].setStatusGarfo(StatusGarfo.LIVRE);
		
		notify();
	}
}
