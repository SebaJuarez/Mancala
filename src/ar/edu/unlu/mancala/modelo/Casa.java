package ar.edu.unlu.mancala.modelo;

public class Casa extends Agujero {

	public Casa(int indice) {
		super(0, indice);
		this.jugador = indice == 0? 2 : 1;
	}

	public void ponerHaba(int cantidad) {
		this.habas += cantidad;
	}
	
	@Override
	public boolean esCasa() {
		return true;
	}

	@Override
	public boolean esHoyo() {
		return false;
	}

}
