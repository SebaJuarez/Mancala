package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;

public class Movimiento {

	private SentidoMovimiento sentido;

	public Movimiento(SentidoMovimiento sentido) {
		this.sentido = sentido;
	}

	public EstadoMovimiento distribuirHabas(Partida partida, Hoyo hoyo, Jugador jugadorMueve) {
		// covierto el sentido del tablero en sentido horario pasandolos a una cola
		Queue<LadoTablero> ladosTablero = sentido.convertirSentidoMovimiento(partida.getTablero(), jugadorMueve);
		// pongo el lado a mover al frente
		ponerLadoJugadorAlFrente(ladosTablero, partida.getLado(jugadorMueve));

		int habas = hoyo.tomarHabas();
		boolean primerVuelta = true;
		LadoTablero ladoActual = null;
		Agujero agujeroActual = hoyo;
		while (habas > 0) {
			// obtengo el lado del principio de la cola
			ladoActual = ladosTablero.poll();
			// obtengo los agujeros del lado en el sentido a recorrer
			List<Agujero> agujeros = sentido.getAgujeros(ladoActual);
			// si es la primer vuelta, tengo que recorrer desde el siguiente al hoyo que se
			// movio
			if (primerVuelta) {
				agujeros = primerVuelta(agujeros, hoyo);
				primerVuelta = false;
			}
			for (Agujero agujero : agujeros) {
				if (habas > 0) {
					if (partida.puedePonerHaba(agujero, jugadorMueve, ladoActual)) {
						agujero.ponerHaba();
						habas--;
					}
					agujeroActual = agujero;
				} else {
					// si no hay mas habas dejo de iterar
					break;
				}
			}
			// pongo el lado al final de la cola
			ladosTablero.add(ladoActual);
		}
		if (partida.puedeTomarHabas(jugadorMueve, agujeroActual, ladoActual)) {
			int habasTomadas = partida.getTablero().tomarHabasOpuestas((Hoyo)agujeroActual, jugadorMueve);
			partida.getTablero().getCasaDeJugador(jugadorMueve).ponerHaba(habasTomadas);
			return EstadoMovimiento.CAPTURA_REALIZADA;
		} else if (partida.puedeSeguirJugando(jugadorMueve, agujeroActual, ladoActual)) {
			return EstadoMovimiento.MOVIMIENTO_VALIDO_SIGUE;
		}
		return EstadoMovimiento.MOVIMIENTO_REALIZADO;
	}

	private List<Agujero> primerVuelta(List<Agujero> agujerosPrimeraVuelta, Hoyo hoyoAMover) {
		List<Agujero> agujeros = new LinkedList<Agujero>();
		boolean encontrado = false;
		for (Agujero agujero : agujerosPrimeraVuelta) {
			if (encontrado) {
				agujeros.add(agujero);
			}
			if (hoyoAMover == agujero) {
				encontrado = true;
			}
		}
		return agujeros;
	}

	private void ponerLadoJugadorAlFrente(Queue<LadoTablero> ladosTablero, LadoTablero ladoQueMueve) {
		// pongo el lado del jugador que mueve en frente de la cola
		while (!(ladosTablero.peek() == ladoQueMueve)) {
			LadoTablero ladoFrente = ladosTablero.poll();
			ladosTablero.add(ladoFrente);
		}
	}

	public void setSentido(SentidoMovimiento sentido) {
		this.sentido = sentido;
	}
}
