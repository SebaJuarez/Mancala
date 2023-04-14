package ar.edu.unlu.mancala.modelo;

import ar.edu.unlu.mancala.estados.tablero.EstadoPartida;

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
		for (int pos = 0; pos < LONGUITUD_TABLERO; pos++)
			if (pos == POS_CASAJ1 || pos == POS_CASAJ2)
				this.agujeros[pos] = new Casa(pos);
			else
				this.agujeros[pos] = new Hoyo(CANTIDAD_HABAS, pos);
	}

	public Agujero[] getAgujeros() {
		return agujeros;
	}

	public EstadoPartida mover(int indice, int jugador) {
		// tomo las habas del lugar indicado
		int habas = ((Hoyo) this.agujeros[indice]).tomarHabas();
		// mientras no haya distribuido todas las habas que estaban en el hoyo
		while (habas > 0) {
			// busco el siguiente indice
			indice = this.siguienteHoyo(this.agujeros[indice], jugador);
			// pongo una haba en el agujero
			this.agujeros[indice].ponerHaba();
			// descuento la haba que puse
			habas--;
		}

		Agujero agujeroUltimo = this.agujeros[indice];
		// devuelvo el estado del movimiento realizado
		// si puedo realizar camptura devuelvo que se ah hecho
		if (agujeroUltimo.getJugador() == jugador && agujeroUltimo.getHabas() == 1
				&& agujeroUltimo instanceof Hoyo) {
			this.capturarHabas(agujeroUltimo.getIndice());
			return EstadoPartida.CAPTURA_REALIZADA;
		} else
			return EstadoPartida.MOVIMIENTO_REALIZADO;
	}

	private void capturarHabas(int indice) {
		System.out.println("indice " + indice + " indice nuevo " + (LONGUITUD_TABLERO - indice) % LONGUITUD_TABLERO);
		((Casa) this.agujeros[this.agujeros[indice].getJugador() == 1 ? POS_CASAJ1 : POS_CASAJ2])
				.ponerHaba(((Hoyo) this.agujeros[(LONGUITUD_TABLERO - indice) % LONGUITUD_TABLERO]).tomarHabas());
	}

	private int siguienteHoyo(Agujero agujero, int jugador) {
		int indiceNuevo = agujero.getIndice();
		// si el indice cae en la casa de mi rival, entonces me salteo la posicion y
		// avanzo 2
		if (jugador != agujero.getJugador() && agujero instanceof Casa)
			indiceNuevo = +2;
		else
			// en cualquier otro caso avanzo una posicion
			indiceNuevo++;

		return indiceNuevo % LONGUITUD_TABLERO;
	}

}
