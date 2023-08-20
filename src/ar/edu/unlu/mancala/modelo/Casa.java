package ar.edu.unlu.mancala.modelo;

public class Casa extends Agujero {

	private static final long serialVersionUID = 1L;

	public Casa() {
		super();
	}
	
	public Casa(int indice) {
		super(0, indice);
		this.jugador = indice == 0 ? 2 : 1;
	}


	public void ponerHaba(int cantidad) {
		this.habas += cantidad;
	}

	@Override
	public int siguienteAgujero(int jugadorActual, int longuitudTablero) {
		return (this.getIndice() + 1) % longuitudTablero;
	}

}
