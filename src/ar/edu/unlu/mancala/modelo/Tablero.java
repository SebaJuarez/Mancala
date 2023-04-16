package ar.edu.unlu.mancala.modelo;

import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;

public class Tablero {

	private Agujero[] agujeros;
	private final int CANTIDAD_HABAS = 4;
	private final int LONGUITUD_TABLERO = 14;
	private final int POS_CASAJ1 = 7;
	private final int POS_CASAJ2 = 0;

	public Tablero() {
		agujeros = new Agujero[LONGUITUD_TABLERO];
		this.inicializar();
	}

	private void inicializar() {
		for (int indice = 0; indice < LONGUITUD_TABLERO; indice++)
			if (indice == POS_CASAJ1 || indice == POS_CASAJ2)
				this.agujeros[indice] = new Casa(indice);
			else
				this.agujeros[indice] = new Hoyo(CANTIDAD_HABAS, indice);
	}

	public EstadoTablero mover(int indice, int jugador) {
		// tomo las habas del lugar indicado
		int habas = ((Hoyo) this.agujeros[indice]).tomarHabas();
		// mientras no haya distribuido todas las habas que estaban en el hoyo
		while (habas > 0) {
			// busco el siguiente indice
			indice = this.agujeros[indice].siguienteAgujero(jugador, LONGUITUD_TABLERO);
			// pongo una haba en el agujero
			this.agujeros[indice].ponerHaba();
			// descuento la haba que puse
			habas--;
		}
		Agujero agujeroUltimo = this.agujeros[indice];
		// devuelvo el estado del movimiento realizado
		// si puedo realizar camptura devuelvo que se ah hecho
		if (agujeroUltimo.getJugador() == jugador && agujeroUltimo.getHabas() == 1 && agujeroUltimo instanceof Hoyo) {
			this.capturarHabas(agujeroUltimo.getIndice());
			return EstadoTablero.CAPTURA_REALIZADA;
		} else
			return EstadoTablero.MOVIMIENTO_REALIZADO;
	}

	private void capturarHabas(int indice) {
		((Casa) this.agujeros[this.agujeros[indice].getJugador() == 1 ? POS_CASAJ1 : POS_CASAJ2])
				.ponerHaba(((Hoyo) this.agujeros[(LONGUITUD_TABLERO - indice) % LONGUITUD_TABLERO]).tomarHabas());
	}

	public Agujero[] getAgujeros() {
		return agujeros;
	}

	public int getCANTIDAD_HABAS() {
		return CANTIDAD_HABAS;
	}

	public int getLONGUITUD_TABLERO() {
		return LONGUITUD_TABLERO;
	}

	public int getPOS_CASAJ1() {
		return POS_CASAJ1;
	}

	public int getPOS_CASAJ2() {
		return POS_CASAJ2;
	}

}
