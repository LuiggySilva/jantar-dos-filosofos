package monitorImpl;

import enums.StatusGarfo;

public class Garfo {

	private StatusGarfo statusGarfo;
	
	public Garfo() {
		this.statusGarfo = StatusGarfo.LIVRE;
	}

	public void setStatusGarfo(StatusGarfo statusGarfo) {
		this.statusGarfo = statusGarfo;
	}
	
	public StatusGarfo getStatusGarfo() {
		return this.statusGarfo;
	}
	
}
