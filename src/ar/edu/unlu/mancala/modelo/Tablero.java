package ar.edu.unlu.mancala.modelo;

public class Tablero {
	
	private Agujero[] agujeros;
	private final int CANTIDAD_HABAS = 4;
	
	public Tablero() {
		agujeros = new Agujero[14];
		this.inicializar();
	}

	private void inicializar() {
		for (int pos = 0; pos < 14; pos++) {
			if (pos == 0 || pos == 13) {
				this.agujeros[pos] = new Casa(pos);
			} else {
				this.agujeros[pos] = new Hoyo(CANTIDAD_HABAS,pos);
			}					
		}
	}

	public Agujero[] getAgujeros() {
		return agujeros;
	}
	
}
