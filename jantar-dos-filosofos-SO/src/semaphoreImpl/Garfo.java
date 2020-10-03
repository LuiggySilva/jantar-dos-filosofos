package semaphoreImpl;

import enums.StatusGarfo;
import java.util.concurrent.Semaphore;

public class Garfo {

	private StatusGarfo statusGarfo;
	private Semaphore semaforo;
	
	public Garfo() {
		this.statusGarfo = StatusGarfo.LIVRE;
		this.semaforo = new Semaphore(1);
	}
	
	public Semaphore getSemaforo() {
		return this.semaforo;
	}
	
	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public void setStatusGarfo(StatusGarfo statusGarfo) {
		this.statusGarfo = statusGarfo;
	}
	
	public StatusGarfo getStatusGarfo() {
		return this.statusGarfo;
	}
	
}
