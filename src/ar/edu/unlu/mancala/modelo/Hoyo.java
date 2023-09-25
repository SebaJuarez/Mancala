package ar.edu.unlu.mancala.modelo;

public class Hoyo extends Agujero {

	private static final long serialVersionUID = 1L;

	public boolean hayHaba() {
		return habas > 0;
	}

	public int tomarHabas() {
		int habas = this.getHabas();
		this.setHabas(0);
		return habas;
	}

	@Override
	protected boolean isHoyo() {
		return true;
	}

	@Override
	protected boolean isCasa() {
		return false;
	}
}
