package monitorImpl;

import monitorImpl.Filosofo;
import monitorImpl.Garfos;

public class Mesa {
	public static void main(String[] args) {
		
		final int qtdFilosofos = 5;
		
		Garfos g = new Garfos(qtdFilosofos);

		for (int i = 1; i <= qtdFilosofos; i++) {
			new Filosofo(i, qtdFilosofos, g);
		}
		
	}
}
