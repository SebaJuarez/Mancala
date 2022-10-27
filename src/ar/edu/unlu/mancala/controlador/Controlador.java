package ar.edu.unlu.mancala.controlador;

import ar.edu.unlu.mancala.Mancala;
import ar.edu.unlu.mancala.modelo.Informe;
import ar.edu.unlu.mancala.modelo.Posicion;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.VistaConsola;

public class Controlador {

	private Tablero tablero;
	private VistaConsola vistaConsola;
	
	
	public Controlador(Tablero tablero, VistaConsola vistaConsola) {
		this.tablero = tablero;
		this.vistaConsola = vistaConsola;
	}

	
	public void mover(Posicion p, int jugadorActual) {
		Informe info = tablero.moverHabas(p, jugadorActual);
		switch(info) {
		case SIGUIENTEJUGADOR :{
			vistaConsola.mostrarMensaje("TURNO DEL SIGUIENTE JUGADOR");
			break;
		}
		case CASILLADEOTROJUGADOR :{
			vistaConsola.mostrarMensaje("LA CASILLA PERTENECE A OTRO JUGADOR");
			break;
		}
		case NOHAYHABAS :{
			vistaConsola.mostrarMensaje("NO HAY HABAS PARA MOVER, SELECCIONE OTRO HOYO");
			break;
		}
		case JUEGADENUEVO :{
			vistaConsola.mostrarMensaje("ULTIMA HABA EN CASA DE JUGADOR, MUEVE DE NUEVO");
			 break;
		}
		case JUEGOFINALIZADO :{
			vistaConsola.mostrarMensaje("JUEGO FINALIZADO, RECUENTO DE HABAS...");
			break;
		}
		default:
			break;
		}
	}

	
}
