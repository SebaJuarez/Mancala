package ar.edu.unlu.mancala.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.observer.Observado;
import ar.edu.unlu.mancala.observer.Observer;

public class MancalaPartida implements Observado{

	private Map<Integer, Jugador> jugadores;
	private Tablero tablero;
	private MoveValidator moveValidator;
	private int turnoActual;
	private boolean partidaTerminada;
	private LinkedList<Observer> observadores;

	public MancalaPartida() {
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

	public EstadoTablero mover(int indice, Jugador jugador) {
		// valido el movimiento 
		EstadoTablero estado = this.moveValidator.validarMovimiento(tablero, turnoActual, obtenerClaveDeJugador(jugador), indice);
		// si el movimiento es valido entonces cambio el turno
		if (estado == EstadoTablero.MOVIMIENTO_VALIDO) {
			tablero.mover(indice, obtenerClaveDeJugador(jugador));
			this.cambiarTurno();
		}
		return estado;
	}

	public EstadoPartida termino() {
		boolean ladoVacio = true;
		for (int i = 1; i < tablero.getPOS_CASAJ1(); i++) {
			if (this.tablero.getAgujeros()[i].getHabas() != 0) {
				ladoVacio = false;
				break;
			}
		}
		if(!ladoVacio) {
			for (int i = tablero.getPOS_CASAJ1() + 1; i < tablero.getLONGUITUD_TABLERO() - 1; i++) {
				if (this.tablero.getAgujeros()[i].getHabas() != 0) {
					ladoVacio = false;
					break;
				}
			}			
		}
		
		this.setPartidaTerminada(ladoVacio);
		return ladoVacio ? EstadoPartida.PARTIDA_TERMINADA : EstadoPartida.PARTIDA_EN_PROGRESO;
	}

	public int obtenerClaveDeJugador(Jugador jugador) {
	    return jugadores.entrySet()
	        .stream()
	        .filter(entry -> entry.getValue().equals(jugador))
	        .map(Map.Entry::getKey)
	        .findFirst()
	        .orElse(-1);
	}
	
	public Jugador obtenerGanador() {
		Agujero casaJ1 = tablero.getAgujeros()[tablero.getPOS_CASAJ1()];
		Agujero casaJ2 = tablero.getAgujeros()[tablero.getPOS_CASAJ2()];
		if (casaJ1.getHabas() > casaJ2.getHabas()) {
		    return jugadores.get(1);
		} else if (casaJ1.getHabas() < casaJ2.getHabas()) {
		    return jugadores.get(2);
		} else {
		    return null;
		}
	}
	
	public void cambiarTurno() {
		this.turnoActual = (this.turnoActual == 1) ? 2 : 1;
	}
	
	// getters y setters
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


	public boolean isPartidaTerminada() {
		return partidaTerminada;
	}

	public void setPartidaTerminada(boolean partidaTerminada) {
		this.partidaTerminada = partidaTerminada;
	}

	// metodos del observer
	@Override
	public void agregarObservador(Observer observer) {
		this.observadores.add(observer);
	}
	
	@Override
	public void notificarObservers(Object informe) {
		this.observadores.forEach((observer) -> observer.update(this,informe));		
	}
}
