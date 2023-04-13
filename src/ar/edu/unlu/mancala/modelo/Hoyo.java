package ar.edu.unlu.mancala.modelo;

public class Hoyo extends Agujero {

	public Hoyo(int habas, int indice) {
		super(habas,indice);
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
