package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SentidoAntiHorario implements SentidoMovimiento {

	@Override
	public Queue<LadoTablero> convertirSentidoMovimiento(Tablero tablero, Jugador jugadorMueve) {
		Queue<LadoTablero> ladosTablero = new LinkedList<LadoTablero>(tablero.getLadosDelTablero());
		return ladosTablero;
	}

	@Override
	public List<Agujero> getAgujeros(LadoTablero lado) {
		return lado.getAgujeros();
	}

}
