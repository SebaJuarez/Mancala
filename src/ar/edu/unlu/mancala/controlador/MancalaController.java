package ar.edu.unlu.mancala.controlador;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.observer.Observer;
import ar.edu.unlu.mancala.vista.consola.VistaConsola;

public class MancalaController implements Observer {

	private MancalaPartida mancalaPartida;
	private Jugador jugador;
	private VistaConsola vista ;
	
	
	@Override
	public void update(Object modelo, Object evento) {
		
		if (evento instanceof EstadoPartida){
			switch((EstadoPartida) evento) {
			case USUARIO_CONECTADO:
				vista.mostrarTablero((Tablero)modelo);
				vista.informar((Jugador)modelo , "se conecto");
				break;
			case USUARIO_DESCONECTADO:
				vista.informar("el usuario se desconecto");
				break;
			case PARTIDA_LLENA:
				vista.informar("no se admiten mas participantes");
				break;
			case ESPERANDO_USUARIO:
				vista.informar("esperando usuario");
				break;
			case COMENZANDO_PARTIDA:
				vista.informar((Jugador) modelo,"la partida comenzo, mueve :");
				break;
			case PARTIDA_TERMINADA:
				vista.informar((Jugador) modelo, "el ganador es..");
				break;
			case PARTIDA_EN_PROGRESO:
				vista.informar((Jugador)modelo, "le toca a ");
				break;
			default:
				break;
			}
		}
		if (evento instanceof EstadoTablero){
			switch((EstadoTablero) evento) {
			case MOVIMIENTO_VALIDO:
				vista.mostrarTablero((Tablero)modelo);
				break;
			case MOVIMIENTO_INVALIDO_RANGO:
				vista.informar("Ingreso un indice fuera del rango del tablero");
				break;
			case MOVIMIENTO_INVALIDO_POSICION:
				vista.informar("ingreso un indice que no le pertenece");
				break;
			case CAPTURA_REALIZADA:
				vista.informar("excelente, realizo una captura!!");
				vista.mostrarTablero((Tablero)modelo);
				break;
			case TURNO_INVALIDO:
				vista.informar("no es su turno");
				break;
			case MOVIMIENTO_INVALIDO_HABAS:
				vista.informar("no hay habas para mover en ese indice");
				break;
			default:
				break;
			}
		}
	}
	

	public void mover(int indice) {
		mancalaPartida.mover(indice, this.jugador);
	}
	
	public void setJugador(Jugador jugador) {
		mancalaPartida.conectarJugador(jugador);
	}
}
