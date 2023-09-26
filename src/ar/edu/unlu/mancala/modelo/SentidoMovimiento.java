package ar.edu.unlu.mancala.modelo;

import java.util.List;
import java.util.Queue;

public interface SentidoMovimiento {

	Queue<LadoTablero> convertirSentidoMovimiento(Tablero tablero, Jugador jugadorMueve);

	List<Agujero> getAgujeros(LadoTablero lado);

}
