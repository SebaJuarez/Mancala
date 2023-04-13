package ar.edu.unlu.mancala.modelo;

public class Casa extends Agujero {

	public Casa(int indice) {
		super(0, indice);
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
