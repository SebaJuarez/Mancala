package ar.edu.unlu.mancala.controlador;

import java.util.LinkedList;

import ar.edu.unlu.mancala.ToDoApp;
import ar.edu.unlu.mancala.commons.Observado;
import ar.edu.unlu.mancala.commons.Observer;
import ar.edu.unlu.mancala.commons.TableroObservado;
import ar.edu.unlu.mancala.modelo.Informe;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.consola.CartelAdvertencia;
import ar.edu.unlu.mancala.vista.consola.VistaConsola;

public class Controlador implements Observer {

	private Tablero tablero;
	LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	private VistaConsola vistaConsola;
	private Jugador j1, j2;
	private int turnoJugador = 1;

	public Controlador(Tablero tablero, VistaConsola vistaConsola) {
		this.tablero = tablero;
		this.vistaConsola = vistaConsola;
	}

	public void mover(Posicion p) {
		tablero.moverHabas(p, this.turnoJugador);
		tablero.evaluarCondicion();
	}

	public boolean comenzarJuego(int j1, int j2) {
		if(buscarJugador(j1) == null ) {
			this.vistaConsola.mostrarMensaje("ID del jugador 1 no existe", CartelAdvertencia.ERROR);
			return false;
		}
		if(buscarJugador(j2) == null ) {
			this.vistaConsola.mostrarMensaje("ID del jugador 2 no existe", CartelAdvertencia.ERROR);
			return false;
		}
		this.j1 = buscarJugador(j1);
		this.j2 = buscarJugador(j2);
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
		case CASILLADEOTROJUGADOR: {
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR, JUEGUE DE NUEVO",
					CartelAdvertencia.ERROR);
			vistaConsola.movimientos();
			break;
		}
		case SIGUIENTEJUGADOR: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			this.turnoSiguienteJugador(this.turnoJugador);
			vistaConsola.mostrarMensaje("TURNO DEL JUGAODR:  " + this.jugadorOfValue(this.turnoJugador).getNombre(),
					CartelAdvertencia.COMPLETO);
			((Tablero) observado).incNumeroDeRonda();
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
			vistaConsola.movimientos();
			break;
		}
		case JUEGOFINALIZADO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("EL JUEGO AH FINALIZADO, EL GANADOR ES...", CartelAdvertencia.COMPLETO);
			vistaConsola.mostrarGanador(buscarGanador());
			break;
		}
		default:
			break;
		}
	}

	private Jugador buscarGanador() {
		if(this.tablero.getTablero()[Posicion.CASAJ1.ordinal()].getCantHabas() > this.tablero.getTablero()[Posicion.CASAJ2.ordinal()].getCantHabas() )
		return this.j1;
		else if(this.tablero.getTablero()[Posicion.CASAJ1.ordinal()].getCantHabas() < this.tablero.getTablero()[Posicion.CASAJ2.ordinal()].getCantHabas())
			return this.j2;
		else return null;
	}

	private void turnoSiguienteJugador(int jugadorActual) {
		if (jugadorActual == 1)
			this.turnoJugador = 2;
		else
			this.turnoJugador = 1;
	}

	private Jugador jugadorOfValue(int turno) {
		if (turno == 1)
			return this.j1;
		else
			return this.j2;
	}

	public void agregarJugador(String nombre) {
		Jugador jugador = new Jugador(nombre);
		jugadores.add(jugador);
		vistaConsola.mostrarMensaje("Jugador Creado con exito..", CartelAdvertencia.COMPLETO);
	}

	public void obtenerJugadores() {
		vistaConsola.mostrarJugadores(this.jugadores);
	}

}
