package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public class MovimientoSentidoHorario implements Movimiento {

	private static final long serialVersionUID = 1L;

	@Override
	public EstadoMovimiento distribuirHabas(Partida partida, Hoyo hoyo, Jugador jugadorMueve) {

		// convierto en sentido horario la cola que por naturaleza representa el sentido
		// antihorario
		Queue<LadoTablero> ladosTablero = convertirSentidoHorario(partida.getTablero(), jugadorMueve);
		// agarro todas las habas del hoyo
		int habasAMover = hoyo.tomarHabas();
		Agujero agujeroActual = hoyo;
		// preparo el indice siguiente del hoyo que quiero mover
		int indice = ladosTablero.peek().getAgujeros().indexOf(agujeroActual) - 1;
		LadoTablero ladoFrente = ladosTablero.peek();
		// las distribuyo hasta quedarme sin habas
		while (habasAMover > 0) {
			ladoFrente = ladosTablero.poll();
			List<Agujero> agujeros = ladoFrente.getAgujeros();
			// si el indice esta dentro del rango de la cantidad de agujeros
			while (indice >= 0 && habasAMover > 0) {
				// recupero el agujero del indice determinado
				agujeroActual = agujeros.get(indice);
				// si el agujero es una casa
				if (agujeroActual.isCasa()) {
					// si la casa le pertenece al jugador que esta moviendo
					if (ladoFrente.perteneceJugador(jugadorMueve)) {
						// pongo una haba
						agujeroActual.ponerHaba();
						habasAMover--;
					}
				} else {
					// si es un hoyo entonces pongo la haba sin importar a quien le pertenece
					agujeroActual.ponerHaba();
					habasAMover--;
				}
				// paso al siguiente indice
				indice--;
			} 
			// seteo el indice en en el ultimo agujero
			indice = partida.getLado(jugadorMueve).getAgujeros().size() - 1;
			// pongo al final de cola el lado que ya recorrí
			ladosTablero.add(ladoFrente);
		}	
		if (partida.tomarHabasOpuestas(jugadorMueve, agujeroActual, ladoFrente)) {
			return EstadoMovimiento.CAPTURA_REALIZADA;
		} else if (partida.puedeSeguirJugando(jugadorMueve, agujeroActual, ladoFrente)) {
			return EstadoMovimiento.MOVIMIENTO_VALIDO_SIGUE;
		}
		return EstadoMovimiento.MOVIMIENTO_REALIZADO;
	}

	private Queue<LadoTablero> convertirSentidoHorario(Tablero tablero, Jugador jugadorMueve) {

		// transformo los lados en una cola
		Queue<LadoTablero> ladosTablero = new LinkedList<LadoTablero>(tablero.getLadosDelTablero());

		// utilizo una pila para dar vuelta el sentido de los lados
		Stack<LadoTablero> pila = new Stack<LadoTablero>();

		// obtengo el lado del jugador que mueve
		LadoTablero ladoQueMueve = tablero.getLado(jugadorMueve);

		ponerLadoJugadorAlFrente(ladosTablero, ladoQueMueve);

		setearSentidoHorario(ladosTablero, pila, ladoQueMueve);

		return ladosTablero;
	}

	private void setearSentidoHorario(Queue<LadoTablero> ladosTablero, Stack<LadoTablero> pila,
			LadoTablero ladoQueMueve) {
		// paso los lados a una pila para darlos vuelta
		while (!ladosTablero.isEmpty()) {
			LadoTablero lado = ladosTablero.poll();
			if (lado != ladoQueMueve) {
				pila.push(lado);
			}
		}
		// pongo el lado que mueve al tope de la pila
		pila.push(ladoQueMueve);

		// seteo la cola en sentido horario
		while (!pila.isEmpty()) {
			ladosTablero.add(pila.pop());
		}
	}
}
