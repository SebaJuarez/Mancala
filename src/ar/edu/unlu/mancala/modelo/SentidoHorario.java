package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SentidoHorario implements SentidoMovimiento {

	@Override
	public Queue<LadoTablero> convertirSentidoMovimiento(Tablero tablero, Jugador jugadorMueve) {
		// paso los lados a una pila para darlos vuelta
		Queue<LadoTablero> lados = new LinkedList<LadoTablero>(tablero.getLadosDelTablero());
		Stack<LadoTablero> pila = new Stack<LadoTablero>();
		while (!lados.isEmpty()) {
			LadoTablero lado = lados.poll();
			pila.push(lado);
		}
		// seteo la cola en sentido horario
		while (!pila.isEmpty()) {
			lados.add(pila.pop());
		}
		return lados;
	}

	@Override
	public List<Agujero> getAgujeros(LadoTablero lado) {
		List<Agujero> agujeros = new LinkedList<Agujero>();
		Stack<Agujero> pila = new Stack<Agujero>();
		for (Agujero a : lado.getAgujeros()) {
			pila.push(a);
		}
		while (!pila.isEmpty()) {
			agujeros.add(pila.pop());
		}
		return agujeros;
	}
}
