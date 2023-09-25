package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public class MovimientoStandar implements Movimiento {

	private static final long serialVersionUID = 1L;

	@Override
	public EstadoMovimiento distribuirHabas(Partida partida, Hoyo hoyo, Jugador jugadorMueve) {
		// covierto el sentido del tablero en sentido horario pasandolos a una cola
		Queue<LadoTablero> ladosTablero = convertirSentidoAntiHorario(partida.getTablero(), jugadorMueve);
		// agarro todas las habas del hoyo
		int habasAMover = hoyo.tomarHabas();
		Agujero agujeroActual = hoyo;
		// preparo el indice siguiente del hoyo que quiero mover
		int indice = ladosTablero.peek().getAgujeros().indexOf(agujeroActual) + 1;
		LadoTablero ladoFrente = ladosTablero.peek();
		// las distribuyo hasta quedarme sin habas
		while (habasAMover > 0) {
			ladoFrente = ladosTablero.poll();
			List<Agujero> agujeros = ladoFrente.getAgujeros();
			// mientras este en rango y haya habas
			while (indice <= agujeros.size() - 1  && habasAMover > 0) {
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
				// paso al siguiente indice y repito
				indice++;
			}
			// seteo el indice en 0 de nuevo
			indice = 0;
			// pongo el final de cola el lado que ya recorrí
			ladosTablero.add(ladoFrente);
			// termino con este while para ir al principal y cambiar al lado siguiente
			}
		if (partida.tomarHabasOpuestas(jugadorMueve, agujeroActual, ladoFrente)) {
			return EstadoMovimiento.CAPTURA_REALIZADA;
		} else if (partida.puedeSeguirJugando(jugadorMueve, agujeroActual, ladoFrente)) {
			return EstadoMovimiento.MOVIMIENTO_VALIDO_SIGUE;
		}
		return EstadoMovimiento.MOVIMIENTO_REALIZADO;
	}

	private Queue<LadoTablero> convertirSentidoAntiHorario(Tablero tablero, Jugador jugadorMueve) {
		// transformo los lados en una cola
		Queue<LadoTablero> ladosTablero = new LinkedList<LadoTablero>(tablero.getLadosDelTablero());
		// obtengo el lado del jugador que mueve
		LadoTablero ladoQueMueve = tablero.getLado(jugadorMueve);
		// pongo el lado del jugador que mueve en frente de la cola
		ponerLadoJugadorAlFrente(ladosTablero, ladoQueMueve);

		return ladosTablero;
	}

}
