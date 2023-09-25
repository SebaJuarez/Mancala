package ar.edu.unlu.mancala.modelo;

public class Casa extends Agujero {

	private static final long serialVersionUID = 1L;

	public void ponerHaba(int cantidad) {
		this.habas += cantidad;
	}

	@Override
	protected boolean isHoyo() {
		return false;
	}

	@Override
	protected boolean isCasa() {
		return true;
	}
}
