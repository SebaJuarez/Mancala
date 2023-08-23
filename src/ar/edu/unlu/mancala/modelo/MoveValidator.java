package ar.edu.unlu.mancala.modelo;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public class MoveValidator {

	public EstadoMovimiento validarMovimiento(Tablero tablero, int turnoActual, int jugador, int indice) {

		if (!enTurno(turnoActual, jugador)) {
			return EstadoMovimiento.TURNO_INVALIDO;
		}

		if (!enRango(indice, tablero)) {
			return EstadoMovimiento.MOVIMIENTO_INVALIDO_RANGO;
		}

		if (!enPosicion(tablero, jugador, indice)) {
			return EstadoMovimiento.MOVIMIENTO_INVALIDO_POSICION;
		}

		if (!hayHabas(tablero, indice)) {
			return EstadoMovimiento.MOVIMIENTO_INVALIDO_HABAS;
		}

		return EstadoMovimiento.MOVIMIENTO_VALIDO;
	}

	private boolean hayHabas(Tablero tablero, int indice) {
		return (tablero.getAgujeros()[indice].getHabas() != 0);
	}

	private boolean enPosicion(Tablero tablero, int jugador, int indice) {
		return (tablero.getAgujeros()[indice].getJugador() == jugador && tablero.getAgujeros()[indice] instanceof Hoyo);
	}

	private boolean enRango(int indice, Tablero tablero) {
		return (indice >= 0 && indice < tablero.getLONGUITUD_TABLERO());
	}

	private boolean enTurno(int turno, int jugador) {
		return (turno == jugador);
	}

}
