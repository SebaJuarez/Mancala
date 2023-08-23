package ar.edu.unlu.mancala.modelo;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public interface Movimiento {
	
	EstadoMovimiento distribuirHabas(TableroN tablero, Hoyo hoyo, Jugador jugadorMueve);
	
	default EstadoMovimiento tomarHabasOpuestas(TableroN tablero, Hoyo hoyo, Jugador jugadorMueve) {
		tablero.getCasaDeJugador(jugadorMueve)
		.ponerHaba(tablero.ladoOpuesto(hoyo)
				.HoyoOpuesto(tablero.getLado(jugadorMueve), hoyo)
				.tomarHabas());
		return EstadoMovimiento.CAPTURA_REALIZADA;
	}
}
