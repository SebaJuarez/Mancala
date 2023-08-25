package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public interface Movimiento extends Serializable {
	
	EstadoMovimiento distribuirHabas(Tablero tablero, Hoyo hoyo, Jugador jugadorMueve);
	
	default EstadoMovimiento tomarHabasOpuestas(Tablero tablero, Hoyo hoyo, Jugador jugadorMueve) {
		Agujero hoyoOpuesto = tablero.ladoOpuesto(hoyo).HoyoOpuesto(tablero.getLado(jugadorMueve), hoyo);
		if(hoyoOpuesto.getHabas() > 0 && hoyoOpuesto instanceof Hoyo) {
			tablero.getCasaDeJugador(jugadorMueve).ponerHaba(((Hoyo)hoyoOpuesto).tomarHabas());			
			return EstadoMovimiento.CAPTURA_REALIZADA;
		}
		return EstadoMovimiento.MOVIMIENTO_REALIZADO;
	}
}
