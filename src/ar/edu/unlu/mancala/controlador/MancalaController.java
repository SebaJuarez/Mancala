package ar.edu.unlu.mancala.controlador;

import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.observer.Observer;

public class MancalaController implements Observer {

	private MancalaPartida mancalaPartida;
	private Jugador jugador;
	
	
	@Override
	public void update(Object modelo, Object evento) {
		
		if (evento instanceof EstadoPartida){
			switch((EstadoPartida) evento) {
			case USUARIO_CONECTADO:
				break;
			case USUARIO_DESCONECTADO:
				break;
			case PARTIDA_LLENA:
				break;
			case ESPERANDO_USUARIO:
				break;
			case COMENZANDO_PARTIDA:
				break;
			case PARTIDA_TERMINADA:
				break;
			case PARTIDA_EN_PROGRESO:
				break;
			default:
				break;
			}
		}
		if (evento instanceof EstadoTablero){
			switch((EstadoTablero) evento) {
			case MOVIMIENTO_VALIDO:
				break;
			case MOVIMIENTO_INVALIDO_RANGO:
				break;
			case MOVIMIENTO_INVALIDO_POSICION:
				break;
			case CAPTURA_REALIZADA:
				break;
			case TURNO_INVALIDO:
				break;
			case MOVIMIENTO_INVALIDO_HABAS:
				break;
			default:
				break;
			}
		}
	}
	

	public void mover(int indice) {
		mancalaPartida.mover(indice, this.jugador);
	}
	
}
