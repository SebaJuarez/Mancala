package ar.edu.unlu.mancala.controlador;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.modelo.IMancalaJugador;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.vista.IVistaJugador;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class JugadorController {

	private String nombreIntento;
	private IMancalaJugador mancala;
	private IVistaJugador vista;
	private Jugador jugador;

	public JugadorController(IVistaJugador vista) {
		this.vista = vista;
	}

	public void agregarJugador(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancala.crearJugador(nombre, contrasenia);
	}

	public void iniciarSesion(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancala.verificarCredenciales(nombre, contrasenia);
	}

	public void actualizar(EstadoPersistencia evento) throws RemoteException {
		switch (evento) {
		case LOGEADO:
			jugadorLogeado();
			break;
		case NOMBRE_EXISTENTE:
			nombreExistente();
			break;
		case GUARDADO_EXITOSO:
			guardadoExitoso();
			break;
		case USUARIO_YA_CONECTADO:
			usuarioYaConectado();
			break;
		case CREDENCIALES_INVALIDAS:
			credencialesInvalidas();
			break;
		default:
			break;
		}
		this.nombreIntento = "";
	}

	private Jugador getJugadorLogeado() throws RemoteException {
		return mancala.getJugador(this.nombreIntento);
	}

	private void jugadorLogeado() throws RemoteException {
		if (this.nombreIntento.equals(vista.getNombreIntento())) {
			this.jugador = getJugadorLogeado();
			vista.mostrarMenuPrincipal();
			vista.informar("Logeado con exito!!");
		}
	}

	private void nombreExistente() {
		if (this.nombreIntento.equals(vista.getNombreIntento()))
			vista.informar("El nombre de usuario ya existe!!, ingrese otro.");
	}

	private void guardadoExitoso() throws RemoteException {
		if (this.nombreIntento.equals(vista.getNombreIntento())) {
			this.jugador = getJugadorLogeado();
			vista.mostrarMenuPrincipal();
			vista.informar("Se creo la cuenta con exito!! No olvide sus credenciales.");
		}
	}

	private void usuarioYaConectado() {
		if (this.nombreIntento.equals(vista.getNombreIntento()))
			vista.informar("La cuenta esta online, no se permite acceso concurrente");
	}

	private void credencialesInvalidas() {
		if (this.nombreIntento.equals(vista.getNombreIntento()))
			vista.informar("Nombre o contrasenia incorrectos, ingrese los datos de nuevo");
	}

	public Jugador getJugador() {
		try {
			jugador = mancala.getJugador(jugador.getNombre());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return this.jugador;
	}

	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.mancala = (IMancalaJugador) modeloRemoto;
	}

	public boolean isLogeado(String nombre) {
		return this.jugador == null ? false : this.jugador.getNombre().equals(nombre);
	}

	public void desconectar(MancalaController mancalaController) throws RemoteException {
		mancala.desconectar(this.jugador, mancalaController);
	}

	public List<JugadorLectura> getTop(int i) throws RemoteException {
		return mancala.getTop(i);
	}

	public String getNombreIntento() {
		return vista.getNombreIntento();
	}

}
