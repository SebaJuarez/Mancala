package ar.edu.unlu.mancala.modelo;

public class Hoyo extends Agujero {

	public Hoyo(int habas, int indice) {
		super(habas,indice);
	}
	
	public int tomarHabas() {
		int habas = this.getHabas();
		this.setHabas(0);
		return habas;
	}
	
	@Override
	public boolean esCasa() {
		return false;
	}
	@Override
	public boolean esHoyo() {
		return true;
	}
	
}
