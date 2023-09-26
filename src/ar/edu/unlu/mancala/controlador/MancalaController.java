package ar.edu.unlu.mancala.controlador;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.vista.IVistaJugador;
import ar.edu.unlu.mancala.vista.IVistaPartida;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class MancalaController implements IControladorRemoto {

	private JugadorController jugadorController;
	private PartidaController partidaController;
	private String nombreIntento = "";

	public MancalaController(Ivista vista) {
		this.jugadorController = new JugadorController((IVistaJugador) vista);
		this.partidaController = new PartidaController((IVistaPartida) vista);
	}

	public void mover(int indice) throws RemoteException {
		partidaController.mover(indice);
	}

	public void jugar() throws RemoteException {
		// partidaController.iniciarPartida(TipoPartida.PARTIDA_STANDAR);
		partidaController.iniciarPartida(TipoPartida.PARTIDA_4_S_AHORARIO);
		// partidaController.iniciarPartida(TipoPartida.PARTIDA_MODO_CAPTURA);
	}

	public void agregarJugador(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		jugadorController.agregarJugador(nombre, contrasenia);
	}

	public void iniciarSesion(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		jugadorController.iniciarSesion(nombre, contrasenia);
	}

	public List<JugadorLectura> getJugadoresTop() throws RemoteException {
		return jugadorController.getTop(10);
	}

	public void desconectar() throws RemoteException {
		jugadorController.desconectar(this);
	}

	public JugadorLectura getJugador() throws RemoteException {
		return jugadorController.getJugador();
	}

	@Override
	public void actualizar(IObservableRemoto modelo, Object evento) throws RemoteException {
		if (evento instanceof EstadoPartida) {
			partidaController.actualizar((EstadoPartida) evento);
		}
		if (evento instanceof EstadoMovimiento) {
			partidaController.actualizar((EstadoMovimiento) evento);
		}
		if (evento instanceof EstadoPersistencia) {
			if (this.nombreIntento.equals(jugadorController.getNombreIntento())) {
				jugadorController.actualizar((EstadoPersistencia) evento);
				if (jugadorController.isLogeado(this.nombreIntento)) {
					this.partidaController.setJugador(jugadorController.getJugador());
				}
			}
		}
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		jugadorController.setModeloRemoto(modeloRemoto);
		partidaController.setModeloRemoto(modeloRemoto);
	}

}
