package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public interface Movimiento extends Serializable {

	EstadoMovimiento distribuirHabas(Partida partida, Hoyo hoyo, Jugador jugadorMueve);

	default void ponerLadoJugadorAlFrente(Queue<LadoTablero> ladosTablero, LadoTablero ladoQueMueve) {
		// pongo el lado del jugador que mueve en frente de la cola
		while (!(ladosTablero.peek() == ladoQueMueve)) {
			LadoTablero ladoFrente = ladosTablero.poll();
			ladosTablero.add(ladoFrente);
		}
	}
}
