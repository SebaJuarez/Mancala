package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public class MovimientoStandar implements Movimiento {

	@Override
	public EstadoMovimiento distribuirHabas(Tablero tablero, Hoyo hoyo, Jugador jugadorMueve) {
		// covierto el sentido del tablero en sentido horario pasandolos a una cola
		Queue<LadoTablero> ladosTablero = convertirSentidoHorario(tablero, jugadorMueve);
		// agarro todas las habas del hoyo
		int habasAMover = hoyo.tomarHabas();
		Agujero agujeroActual = hoyo;
		// preparo el indice siguiente del hoyo que quiero mover
		int indice = ladosTablero.peek().getAgujeros().indexOf(agujeroActual) + 1;
		// las distribuyo hasta quedarme sin habas
		while (habasAMover > 0) {
			LadoTablero ladoFrente = ladosTablero.poll();
			List<Agujero> agujeros = ladoFrente.getAgujeros();
			// si hay habas para distribuir
			while (habasAMover > 0) {
				// si el indice esta dentro del rango de la cantidad de agujeros
				if (indice <= agujeros.size() - 1) {
					// recupero el agujero del indice determinado
					agujeroActual = agujeros.get(indice);
					// si el agujero es una casa
					if (agujeroActual instanceof Casa) {
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
				} else {
					// seteo el indice en 0 de nuevo
					indice = 0;
					// pongo el final de cola el lado que ya recorrí
					ladosTablero.add(ladoFrente);
					// termino con este while para ir al principal y cambiar al lado siguiente
					break;
				}
				if (habasAMover == 0 && agujeroActual.getHabas() == 1 && ladoFrente.perteneceJugador(jugadorMueve)
						&& !(agujeroActual instanceof Casa)) {
					return tomarHabasOpuestas(tablero, (Hoyo) agujeroActual, jugadorMueve);
				} else if (habasAMover == 0 && ladoFrente.perteneceJugador(jugadorMueve)
						&& agujeroActual instanceof Casa) {
					return EstadoMovimiento.MOVIMIENTO_VALIDO_SIGUE;
				}
			}
		}
		return EstadoMovimiento.MOVIMIENTO_REALIZADO;
	}

	private Queue<LadoTablero> convertirSentidoHorario(Tablero tablero, Jugador jugadorMueve) {
		// transformo los lados en una cola
		Queue<LadoTablero> ladosTablero = new LinkedList<LadoTablero>(tablero.getLadosDelTablero());
		// obtengo el lado del jugador que mueve
		LadoTableroLectura ladoQueMueve = tablero.getLado(jugadorMueve);
		// pongo el lado del jugador que mueve en frente de la cola
		while (!(ladosTablero.peek() == ladoQueMueve)) {
			LadoTablero ladoFrente = ladosTablero.poll();
			ladosTablero.add(ladoFrente);
		}
		return ladosTablero;
	}
}
