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
import ar.edu.unlu.mancala.vista.CartelAdvertencia;
import ar.edu.unlu.mancala.vista.VistaConsola;

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
	}

	public void comenzarJuego(Jugador j1, Jugador j2) {
		this.j1 = j1;
		this.j2 = j2;
	}

	@Override
	public void update(TableroObservado observado, Object informe) {
		switch ((Informe) informe) {
		case CASILLADEOTROJUGADOR: {
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR, JUEGUE DE NUEVO",
					CartelAdvertencia.ERROR);
			break;
		}
		case SIGUIENTEJUGADOR: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			this.turnoSiguienteJugador(this.turnoJugador);
			vistaConsola.mostrarMensaje("TURNO DEL JUGAODR:  " + this.jugadorOfValue(this.turnoJugador).getNombre(),
					CartelAdvertencia.COMPLETO);
			break;
		}
		case NOHAYHABAS: {
			vistaConsola.mostrarMensaje("LA CASILLA NO CONTIENE HABAS, SELECCIONE OTRA", CartelAdvertencia.ADVERTENCIA);
			break;
		}
		case JUEGADENUEVO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("ULTIMA HABA EN ZONA, JUEGUE DE NUEVO", CartelAdvertencia.COMPLETO);
			break;
		}
		case JUEGOFINALIZADO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("EL JUEGO AH FINALIZADO, EL GANADOR ES...", CartelAdvertencia.COMPLETO);
			break;
		}
		default:
			break;
		}
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
		vistaConsola.mostrarMensaje("Jugador Creado con exito..", CartelAdvertencia.COMPLETO );
	}
	
	public void obtenerJugadores() {
		vistaConsola.mostrarJugadores(this.jugadores);
	}

}
