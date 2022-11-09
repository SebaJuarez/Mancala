package ar.edu.unlu.mancala.controlador;

import java.util.Comparator;
import java.util.LinkedList;
import ar.edu.unlu.mancala.commons.Observer;
import ar.edu.unlu.mancala.commons.TableroObservado;
import ar.edu.unlu.mancala.modelo.Informe;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.consola.*;

public class MancalaController implements Observer {

	private Tablero tablero;
	LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	private VistaConsola vistaConsola;
	private Jugador j1, j2;
	private int turnoJugador = 1;

	public MancalaController(Tablero tablero, VistaConsola vistaConsola) {
		this.tablero = tablero;
		this.vistaConsola = vistaConsola;
	}

	public void mover(Posicion p) {
		tablero.moverHabas(p, this.turnoJugador);
	}

	public boolean comenzarJuego(int j1, int j2) {
		Jugador jugador1 = buscarJugador(j1);
		Jugador jugador2 = buscarJugador(j2);
		if(jugador1 == null ) {
			this.vistaConsola.mostrarMensaje("ID del jugador 1 no existe", CartelAdvertencia.ERROR);
			return false;
		}
		if(jugador2 == null ) {
			this.vistaConsola.mostrarMensaje("ID del jugador 2 no existe", CartelAdvertencia.ERROR);
			return false;
		}
		this.j1 = jugador1;
		this.j2 = jugador2;
		this.tablero.inicializarFichas();
		return true;
	}
	
	private Jugador buscarJugador(int id) {
		for(Jugador jugador : this.jugadores) {
			if(jugador.getId() == id) return jugador;
		}
		return null;
	}

	@Override
	public void update(TableroObservado observado, Object informe) {
		switch ((Informe) informe) {
		case LISTOPARACOMENZAR : {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje(" TURNO DEL JUGAODR " + this.turnoJugador + ": " + this.jugadorOfValue(this.turnoJugador).getNombre(),CartelAdvertencia.COMPLETO);
			vistaConsola.movimientos();
		}
		case CASILLADEOTROJUGADOR: {
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR, JUEGUE DE NUEVO",CartelAdvertencia.ERROR);
			vistaConsola.movimientos();
			break;
		}
		case SIGUIENTEJUGADOR: {
			this.turnoSiguienteJugador(this.turnoJugador);
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje(" TURNO DEL JUGAODR " + this.turnoJugador + ": " + this.jugadorOfValue(this.turnoJugador).getNombre(),CartelAdvertencia.COMPLETO);
			((Tablero) observado).incNumeroDeRonda();
			tablero.evaluarCondicion();
			vistaConsola.movimientos();
			break;
		}
		case NOHAYHABAS: {
			vistaConsola.mostrarMensaje("LA CASILLA NO CONTIENE HABAS, SELECCIONE OTRA", CartelAdvertencia.ADVERTENCIA);
			vistaConsola.movimientos();
			break;
		}
		case JUEGADENUEVO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("ULTIMA HABA EN ZONA, JUEGUE DE NUEVO", CartelAdvertencia.COMPLETO);
			((Tablero) observado).incNumeroDeRonda();
			tablero.evaluarCondicion();
			vistaConsola.movimientos();
			break;
		}
		case JUEGOFINALIZADO: {
			this.j1.incPartidasJugadas();
			this.j2.incPartidasJugadas();
			Jugador jugador = buscarGanador();
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("EL JUEGO AH FINALIZADO \nNUMERO DE RONDAS: " + ((Tablero) observado).getNumeroDeRonda(), CartelAdvertencia.COMPLETO);
			vistaConsola.mostrarGanador(jugador,(jugador == this.j1)? 1 : (jugador == this.j2)? 2 : 0);
		}
		default:
			break;
		}
	}

	private Jugador buscarGanador() {
		if(this.tablero.getTablero()[Posicion.CASAJ1.ordinal()].getCantHabas() > this.tablero.getTablero()[Posicion.CASAJ2.ordinal()].getCantHabas() ) {
			this.j1.incPartidasGanadas();
			return this.j1;
		}
		else if(this.tablero.getTablero()[Posicion.CASAJ1.ordinal()].getCantHabas() < this.tablero.getTablero()[Posicion.CASAJ2.ordinal()].getCantHabas()) {
			this.j2.incPartidasGanadas();
			return this.j2;			
		}
		else {
			this.j1.incPartidasEmpatadas();
			this.j2.incPartidasEmpatadas();
			return null;
		}
	}

	private void turnoSiguienteJugador(int jugadorActual) {
		this.turnoJugador = (jugadorActual == 1)? 2 : 1;
	}

	private Jugador jugadorOfValue(int turno) {
		return (turno == 1)? j1 : j2;
	}

	
	public void topGanadores() {
		jugadores.sort(Comparator.comparing(Jugador::getPartidasGanadas).reversed());
		vistaConsola.mostrarJugadores(this.jugadores);
	}
	
	public void agregarJugador(String nombre) {
		Jugador jugador = new Jugador(nombre);
		jugadores.add(jugador);
		vistaConsola.mostrarMensaje("Jugador Creado con exito..", CartelAdvertencia.COMPLETO);
	}

	public void obtenerJugadores() {
		jugadores.sort(Comparator.comparing(Jugador::getId));
		vistaConsola.mostrarJugadores(this.jugadores);
	}
	

}
