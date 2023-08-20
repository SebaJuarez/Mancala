package ar.edu.unlu.mancala.modelo;

public class Hoyo extends Agujero {

	private static final long serialVersionUID = 1L;
	private boolean antesDeCasa = false;

	public Hoyo(int habas, int indice) {
		super(habas, indice);
	}

	public Hoyo() {
		super();
	}

	public boolean getAntesDeCasa() {
		return this.antesDeCasa;
	}

	public void setAntesDeCasa(boolean valor) {
		this.antesDeCasa = valor;
	}

	public int tomarHabas() {
		int habas = this.getHabas();
		this.setHabas(0);
		return habas;
	}

	@Override
	public int siguienteAgujero(int jugador, int longuitudTablero) {
		return antesDeCasa && jugador != this.getJugador() ? (this.getIndice() + 2) % longuitudTablero
				: (this.getIndice() + 1) % longuitudTablero;
	}

}
