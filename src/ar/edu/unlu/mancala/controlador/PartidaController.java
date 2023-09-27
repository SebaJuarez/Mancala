package ar.edu.unlu.mancala.controlador;

import java.rmi.RemoteException;

import ar.edu.unlu.mancala.modelo.IMancala;
import ar.edu.unlu.mancala.modelo.IMancalaPartida;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.partida.jugador.EstadoJugadorPartida;
import ar.edu.unlu.mancala.vista.IVistaPartida;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class PartidaController {

	private IMancalaPartida mancala;
	private IVistaPartida vista;
	private Jugador jugador;

	public PartidaController(IVistaPartida vista) {
		this.vista = vista;
	}

	public void actualizar(EstadoPartida evento) throws RemoteException {
		Jugador jugadorMueve = mancala.turnoActual();
		switch (evento) {
		case USUARIO_CONECTADO:
			usuarioConectado();
			break;
		case USUARIO_DESCONECTADO:
			usuarioDesconectado();
			break;
		case PARTIDA_LLENA:
			partidaLlena();
			break;
		case ESPERANDO_USUARIO:
			esperandoUsuario();
			break;
		case COMENZANDO_PARTIDA:
			mostrandoPartida(jugadorMueve);
			break;
		case PARTIDA_EN_PROGRESO:
			mostrandoPartida(jugadorMueve);
			break;
		case PARTIDA_TERMINADA:
			partidaTerminada(jugadorMueve);
			break;
		default:
			break;
		}
	}

	public void actualizar(EstadoMovimiento evento) throws RemoteException {
		switch (evento) {
		case MOVIMIENTO_REALIZADO:
			movimientoRealizado();
			break;
		case MOVIMIENTO_INVALIDO_RANGO:
			movimientoInvalidoRango();
			break;
		case MOVIMIENTO_INVALIDO_POSICION:
			movimientoInvalidoPosicion();
			break;
		case CAPTURA_REALIZADA:
			capturaRealiazada();
			break;
		case TURNO_INVALIDO:
			turnoInvalido();
			break;
		case MOVIMIENTO_INVALIDO_HABAS:
			movimientoInvalidoHabas();
			break;
		case MOVIMIENTO_VALIDO_SIGUE:
			movimientoValidoSigueJugando();
			break;
		default:
			break;
		}
	}

	private void movimientoValidoSigueJugando() throws RemoteException {
		if (mancala.isJugadorEnPartida(this.jugador)) {
			if (mancala.isJugadorUltimoEnMover(this.jugador)) {
				vista.informar("Genial!, ultima haba en casa");
			} else {
				vista.informar("Su rival puso la ultima haba en casa!");
			}
		}
	}

	private void movimientoInvalidoHabas() throws RemoteException {
		if (mancala.isJugadorUltimoEnMover(this.jugador)) {
			vista.informar("No hay habas para mover en ese indice");
		}
	}

	private void turnoInvalido() throws RemoteException {
		if (mancala.isJugadorUltimoEnMover(this.jugador)) {
			vista.informar("No es su turno!!");
		}
	}

	private void capturaRealiazada() throws RemoteException {
		if (mancala.isJugadorUltimoEnMover(this.jugador)) {
			vista.informar("Excelente, realizo una captura!!");
		} else {
			vista.informar(mancala.getUltimoEnMover() + " Capturo un agujero!");
		}
	}

	private void movimientoInvalidoPosicion() throws RemoteException {
		if (mancala.isJugadorUltimoEnMover(this.jugador)) {
			vista.informar("Ingreso un agujero invalido!");
		}
	}

	private void movimientoInvalidoRango() throws RemoteException {
		if (mancala.isJugadorUltimoEnMover(this.jugador)) {
			vista.informar("Ingreso un indice fuera del rango del tablero");
		}
	}

	private void movimientoRealizado() throws RemoteException {
		if (mancala.isJugadorEnPartida(this.jugador)) {
			if (mancala.isJugadorUltimoEnMover(this.jugador)) {
				vista.informar("movimiento realizado!");
			} else {
				vista.informar("su rival movio!");
			}
		}
	}

	private void partidaTerminada(Jugador jugadorMueve) throws RemoteException {
		if (mancala.isJugadorEnPartida(this.jugador)) {
			this.jugador = mancala.getJugador(this.jugador.getNombre());
			vista.mostrarPartida(mancala.getTablero(), (JugadorLectura) jugadorMueve, mancala.tipoPartidaComenzada(),
					this.jugador);
			EstadoJugadorPartida estado = mancala.estadoJugadorPartida(this.jugador, EstadoPartida.PARTIDA_TERMINADA);
			vista.mostrarEstadoJugadorPartida(estado, (JugadorLectura) this.jugador);

		}
	}

	private void mostrandoPartida(Jugador jugadorMueve) throws RemoteException {
		if (mancala.isJugadorEnPartida(this.jugador)) {
			vista.mostrarPartida(mancala.getTablero(), (JugadorLectura) jugadorMueve, mancala.tipoPartidaComenzada(),
					this.jugador);
		}
	}

	private void esperandoUsuario() throws RemoteException {
		if (mancala.ultimoJugadorEnPartida().equals(this.jugador)) {
			vista.informar("esperando usuario... ");
		}
	}

	private void partidaLlena() throws RemoteException {
		if (!mancala.isJugadorEnPartida(this.jugador))
			vista.informar("no se admiten mas participantes");
	}

	private void usuarioDesconectado() throws RemoteException {
		if (mancala.isJugadorEnPartida(this.jugador)) {
			this.jugador = mancala.getJugador(this.jugador.getNombre());
			EstadoJugadorPartida estado = mancala.estadoJugadorPartida(this.jugador,
					EstadoPartida.USUARIO_DESCONECTADO);
			vista.mostrarEstadoJugadorPartida(estado, (JugadorLectura) this.jugador);
		}
	}

	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.mancala = (IMancala) modeloRemoto;
	}

	private void usuarioConectado() throws RemoteException {
		Jugador ultimoJugador = mancala.ultimoJugadorEnPartida();
		if (!mancala.isPartidaLLena() && ultimoJugador.equals(this.jugador)) {
			vista.mostrarSalaDeEspera();
		} else if (!mancala.isPartidaLLena() && !ultimoJugador.equals(this.jugador)) {
			vista.informar((JugadorLectura) ultimoJugador, "se creo una partida, en sala de espera está: ");
		}
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void iniciarPartida(TipoPartida partidaModoCaptura) throws RemoteException {
		mancala.iniciarPartida(this.jugador, partidaModoCaptura);
	}

	public void mover(int indice) throws RemoteException {
		mancala.mover(indice, this.jugador);
	}

}
