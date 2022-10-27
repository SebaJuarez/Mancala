package ar.edu.unlu.mancala.controlador;

import ar.edu.unlu.mancala.Mancala;
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
	private VistaConsola vistaConsola;
	private Jugador j1, j2;
	private int turnoJugador;

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
		this.turnoJugador = 1;
	}

	public void update(TableroObservado observado, Object informe) {
		switch ((Informe) informe) {
		case CASILLADEOTROJUGADOR: {
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR, JUEGUE DE NUEVO",CartelAdvertencia.ERROR);
			break;
		}
		case SIGUIENTEJUGADOR: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			this.turnoSiguienteJugador(this.turnoJugador);
			vistaConsola.mostrarMensaje("TURNO DEL SIGUIENTE JUGADOR ", CartelAdvertencia.COMPLETO);
			break;
		}
		case NOHAYHABAS: {
			vistaConsola.mostrarMensaje("LA CASILLA NO CONTIENE HABAS, SELECCIONE OTRA", CartelAdvertencia.ADVERTENCIA);
		}
		case JUEGADENUEVO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("ULTIMA HABA EN ZONA, JUEGUE DE NUEVO", CartelAdvertencia.COMPLETO);
		}
		case JUEGOFINALIZADO: {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("EL JUEGO AH FINALIZADO, EL GANADOR ES...", CartelAdvertencia.COMPLETO);
		}
		default:
			break;
		}
	}

	@Override
	public void update(Observado observado, Object informe) {

	}
	
	private void turnoSiguienteJugador(int jugadorActual) {
		if(jugadorActual == 1) this.turnoJugador = 2; else this.turnoJugador = 1;
	}

}
