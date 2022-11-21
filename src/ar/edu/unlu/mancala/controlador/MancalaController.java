package ar.edu.unlu.mancala.controlador;

import java.util.Comparator;
import java.util.LinkedList;
import ar.edu.unlu.mancala.commons.Observer;
import ar.edu.unlu.mancala.commons.TableroObservado;
import ar.edu.unlu.mancala.commons.Vista;
import ar.edu.unlu.mancala.modelo.IJugador;
import ar.edu.unlu.mancala.modelo.Informe;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Partida;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.serializacion.services.AdministradorDeSerializacion;
import ar.edu.unlu.mancala.vista.consola.*;

public class MancalaController implements Observer {

	private Tablero tablero;
	private LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	private Partida partidaActual;
	private Vista vistaConsola;
	private AdministradorDeSerializacion serializadorAdmin = new AdministradorDeSerializacion();

	public MancalaController(Tablero tablero, Vista vistaConsola) {
		this.tablero = tablero;
		this.vistaConsola = vistaConsola;
		if(serializadorAdmin.existeJugadoresFile()){
			this.jugadores = serializadorAdmin.obtenerJugadores();
			Jugador.ids = this.jugadores.getLast().getId() + 1;	
		}
	}

	public void mover(Posicion p) {
		tablero.moverHabas(p, partidaActual.getTurno());
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
		if(jugador1 == jugador2 ) {
			this.vistaConsola.mostrarMensaje("No se admiten jugadores iguales", CartelAdvertencia.ERROR);
			return false;
		}
		partidaActual = new Partida(jugador1,jugador2,tablero.getTablero());
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
		partidaActual.setUltimoEstado((Informe)informe);
		switch ((Informe) informe) {
		case LISTOPARACOMENZAR : {
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje(" TURNO DEL JUGAODR " + partidaActual.getTurno() + ": " + partidaActual.jugadorOfValue(partidaActual.getTurno()).getNombre(),CartelAdvertencia.COMPLETO);
			vistaConsola.movimientos();
		}
		case CASILLADEOTROJUGADOR: {
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR, JUEGUE DE NUEVO",CartelAdvertencia.ERROR);
			vistaConsola.movimientos();
			break;
		}
		case SIGUIENTEJUGADOR: {
			partidaActual.turnoSiguienteJugador(partidaActual.getTurno());
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje(" TURNO DEL JUGAODR " + partidaActual.getTurno() + ": " + partidaActual.jugadorOfValue(partidaActual.getTurno()).getNombre(),CartelAdvertencia.COMPLETO);
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
		case CONTINUARPARTIDA:{
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			vistaConsola.mostrarMensaje("CONTNUA EL JUEGO, TURNO DEL JUGAODR " + partidaActual.getTurno() + ": " + partidaActual.jugadorOfValue(partidaActual.getTurno()).getNombre(),CartelAdvertencia.COMPLETO);
			((Tablero) observado).incNumeroDeRonda();
			tablero.evaluarCondicion();
			vistaConsola.movimientos();
			break;
		}
		case JUEGOFINALIZADO: {
			partidaActual.getJ1().incPartidasJugadas();
			partidaActual.getJ2().incPartidasJugadas();
			Jugador jugador = partidaActual.buscarGanador();
			vistaConsola.mostrarTablero(((Tablero) observado).getTablero());
			guardarJugador();
			guardarPartida();
			vistaConsola.mostrarMensaje("EL JUEGO AH FINALIZADO \nNUMERO DE RONDAS: " + ((Tablero) observado).getNumeroDeRonda(), CartelAdvertencia.COMPLETO);
			vistaConsola.mostrarGanador(jugador,(jugador == partidaActual.getJ1())? 1 : (jugador == partidaActual.getJ2())? 2 : 0);
		}
		default:
			break;
		}
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

	public void guardarJugador() {
		serializadorAdmin.guardar(jugadores);
	}

	public void obtenerJugadores() {
		jugadores.sort(Comparator.comparing(Jugador::getId));
		vistaConsola.mostrarJugadores(this.jugadores);
	}

	public void continuarPartida() {
		if (serializadorAdmin.existePartidaFile()) {
			Partida partida = serializadorAdmin.obtenerPartida();
			if (partida.getUltimoEstado() != Informe.JUEGOFINALIZADO) {
				this.tablero.setTablero(partida.getTablero());
				this.tablero.setNumeroDeRonda(partida.getNumeroDeRonda());
				this.partidaActual = partida;
				actualizarJugadores(partida.getJ1(), partida.getJ2());
				this.update(tablero, Informe.CONTINUARPARTIDA);
			} else
				vistaConsola.mostrarMensaje("LA ULTIMA PARTIDA AH FINALIZADO.", CartelAdvertencia.ADVERTENCIA);
		} else
			vistaConsola.mostrarMensaje("NO HAY REGISTROS DE LA ULTIMA PARTIDA.", CartelAdvertencia.ADVERTENCIA);

	}

	private void actualizarJugadores(Jugador jugador1, Jugador jugador2) {
		this.jugadores.forEach(jugador -> {
			if (jugador.getId() == jugador1.getId()) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador1);
			} else if (jugador.getId() == jugador2.getId()) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador2);
			}
		});
	}

	public void guardarPartida() {
		partidaActual.setNumeroDeRonda(tablero.getNumeroDeRonda());
		serializadorAdmin.guardar(partidaActual);
	}

}
