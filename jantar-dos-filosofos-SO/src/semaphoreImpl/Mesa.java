package semaphoreImpl;

import semaphoreImpl.Filosofo;
import semaphoreImpl.Garfos;

public class Mesa {
	public static void main(String[] args) {

		final int qtdFilosofos = 5;
		
		new Garfos(qtdFilosofos);

		for (int i = 1; i <= qtdFilosofos; i++) {
			new Filosofo(i, qtdFilosofos);
		}
	}	
}
