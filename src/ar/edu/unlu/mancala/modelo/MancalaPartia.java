package ar.edu.unlu.mancala.modelo;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;

public class MancalaPartia {

	private Map<Integer, Jugador> jugadores;
	private Tablero tablero;
	private MoveValidator moveValidator;
	private int turnoActual;

	public MancalaPartia() {
		this.jugadores = new HashMap<Integer, Jugador>(2);
	}

	public EstadoPartida conectarJugador(Jugador jugador) {
		if (this.jugadores.size() == 2)
			return EstadoPartida.PARTIDA_LLENA;

		if (this.jugadores.isEmpty()) {
			jugadores.put(1, jugador);
		} else
			jugadores.put(2, jugador);
		return EstadoPartida.USUARIO_CONECTADO;
	}

	public EstadoPartida iniciarPartida() {
		if (this.jugadores.size() != 2) {
			return EstadoPartida.ESPERANDO_USUARIO;
		}
		this.tablero = new Tablero();
		this.moveValidator = new MoveValidator();
		this.setTurnoActual(((int) Math.random() * 2) + 1);
		// notificar
		return EstadoPartida.COMENZANDO_PARTIDA;
	}

	public EstadoTablero mover(int indice, int jugador) {
		return this.moveValidator.validarMovimiento(tablero, turnoActual, jugador, indice);
	}

	public EstadoPartida termino() {
		boolean ladoVacio = true;
		for (int i = 1; i < tablero.getPOS_CASAJ1(); i++) {
			if (this.tablero.getAgujeros()[i].getHabas() != 0) {
				ladoVacio = false;
				break;
			}
		}
		for (int i = tablero.getPOS_CASAJ1() + 1; i < tablero.getLONGUITUD_TABLERO() - 1; i++) {
			if (this.tablero.getAgujeros()[i].getHabas() != 0) {
				ladoVacio = false;
				break;
			}
		}
		return ladoVacio ? EstadoPartida.PARTIDA_TERMINADA : EstadoPartida.PARTIDA_EN_PROGRESO;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public MoveValidator getMoveValidator() {
		return moveValidator;
	}

	public void setMoveValidator(MoveValidator movValidator) {
		this.moveValidator = movValidator;
	}

	public Map<Integer, Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Map<Integer, Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getTurnoActual() {
		return turnoActual;
	}

	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}

}
