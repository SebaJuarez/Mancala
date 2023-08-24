package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public interface Movimiento extends Serializable {
	
	EstadoMovimiento distribuirHabas(Tablero tablero, Hoyo hoyo, Jugador jugadorMueve);
	
	default EstadoMovimiento tomarHabasOpuestas(Tablero tablero, Hoyo hoyo, Jugador jugadorMueve) {
		tablero.getCasaDeJugador(jugadorMueve)
		.ponerHaba(tablero.ladoOpuesto(hoyo)
				.HoyoOpuesto(tablero.getLado(jugadorMueve), hoyo)
				.tomarHabas());
		return EstadoMovimiento.CAPTURA_REALIZADA;
	}
}
