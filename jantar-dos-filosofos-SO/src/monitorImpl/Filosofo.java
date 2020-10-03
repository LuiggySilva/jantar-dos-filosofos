package monitorImpl;

import enums.StatusFilosofo;
import monitorImpl.Garfos;

public class Filosofo implements Runnable{

	private int id;
	private StatusFilosofo statusFilosofo;
	private int qtdFilosofos;
	private Garfos garfos;
	
	public Filosofo(int id, int qtdFilosofos, Garfos garfos) {
		this.id = id;
		this.qtdFilosofos = qtdFilosofos;
		this.garfos = garfos;
		new Thread((Runnable) this, "Filosofo").start();
	}
	
	public void setStatusFilosofo(StatusFilosofo statusFilosofo) {
		this.statusFilosofo = statusFilosofo;
	}
	
	public StatusFilosofo getStatusFilosofo() {
		return this.statusFilosofo;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "-------------------------------------------------------" + 
	           "\nID FILOSOFO: " + this.id + " | STATUS FILOSOFO: " + this.statusFilosofo;
	}
	
	private void tentaComer() {
		try {
			this.garfos.getGarfos(this.id, this.qtdFilosofos);
			this.setStatusFilosofo(StatusFilosofo.COMENDO);
			System.out.println(this.toString());
			Thread.sleep(this.getRandomNumber(2000, 3000));
			this.paraDeComer();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void ficaComFome() {
		try {
			this.setStatusFilosofo(StatusFilosofo.COM_FOME);
			System.out.println(this.toString());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void paraDeComer() {
		this.garfos.liberarGarfos(this.id, this.qtdFilosofos);
	}
	
	private void pensar() {
		try {
			this.setStatusFilosofo(StatusFilosofo.PENSANDO);
			System.out.println(this.toString());
			Thread.sleep(this.getRandomNumber(1000, 1500));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}	
	
	@Override
	public void run() {
		while (true) {
			// Filosofo fica com fome por 1s
			this.ficaComFome();
			// Tenta comer, se conseguir fica entre 2s e 3s comendo
			this.tentaComer();
			// Filosofo fica pensando entre 1s e 1.5s
			this.pensar();
		}	
	}

}
