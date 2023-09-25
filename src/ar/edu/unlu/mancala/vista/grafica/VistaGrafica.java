package ar.edu.unlu.mancala.vista.grafica;

import java.rmi.RemoteException;
import java.util.List;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.consola.EstadosFlujo;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuInicioSesionListener;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuPrincipalListener;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;

public class VistaGrafica
		implements Ivista, MenuInicioSesionListener, MenuPrincipalListener, TableroPartidaListener, VolverListener {

	private MancalaController controlador;
	private String nombreIntento;
	private EstadosFlujo flujoActual = EstadosFlujo.LOG_IN;
	// Jframes --------------------------------------------------------
	private MenuInicioSesion menuInicioSesion = new MenuInicioSesion();
	private MenuPrincipal menuPrincipal;
	private TableroVistaGrafica tablero;
	private SalaDeEspera salaDeEspera;
	private CartelGanador cartelGanador;
	private CartelPerdedor cartelPerdedor;
	private CartelEmpate cartelEmpate;
	private Estadistica estadistica;
	private TopJugadores topJugadores;
	private Reglas reglas;

	@Override
	public void iniciar() {
		mostrarMenuInicioSesion();
	}

	@Override
	public MancalaController getControlador() {
		return this.controlador;
	}

	@Override
	public void setControlador(MancalaController controlador) {
		this.controlador = controlador;
	}

	@Override
	public void informar(String string) {
		switch (flujoActual) {
		case LOG_IN_ENTRADA:
			((FormularioInicioSesion) menuInicioSesion.getFormularioInicioSesion()).mostrarAviso(string);
			break;
		case LOG_IN_CREACION:
			((FormularioCreacionUsuario) menuInicioSesion.getFormularioCreacionUsuario()).mostrarAviso(string);
			break;
		case MOVIMIENTOS:
			tablero.informar(string);
			break;
		case MENU_PRINCIPAL:
			menuPrincipal.informar(string);
		default:
			menuPrincipal.informar(string);
			break;
		}
	}

	@Override
	public void informar(JugadorLectura jugador, String string) {
		informar(string + " " + jugador.getNombre());
	}

	@Override
	public void mostrarMenuInicioSesion() {
		flujoActual = EstadosFlujo.LOG_IN;
		menuInicioSesion.setListener(this);
		menuInicioSesion.setVisible(true);
	}

	@Override
	public void mostrarEstadisticas() {
		if (estadistica == null)
			estadistica = new Estadistica();
		menuPrincipal.setVisible(false);
		try {
			estadistica.mostrarEstadisticas(controlador.getJugador());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		estadistica.setListener(this);
		estadistica.setVisible(true);
	}

	@Override
	public void mostrarPartida(List<LadoTablero> ladosTablero, JugadorLectura jugadorMueve, TipoPartida tipoPartida,
			JugadorLectura yo) {

		if (flujoActual == EstadosFlujo.ESPERA) {
			salaDeEspera.setVisible(false);
		}

		if (tablero == null)
			tablero = new TableroVistaGrafica();

		flujoActual = EstadosFlujo.MOVIMIENTOS;
		if (!this.tablero.isVisible()) {
			tablero.iniciarTablero(tipoPartida);
			this.tablero.setListener(this);
			this.tablero.setVisible(true);
			this.tablero.inicializar();
			this.menuPrincipal.setVisible(false);
		}
		this.tablero.actualizarTablero(ordenarLados(ladosTablero, yo));
		informar(jugadorMueve, "Le toca al jugador: ");
	}

	@Override
	public void mostrarSalaDeEspera() {
		if (salaDeEspera == null)
			salaDeEspera = new SalaDeEspera();
		flujoActual = EstadosFlujo.ESPERA;
		menuPrincipal.setVisible(false);
		salaDeEspera.setListener(this);
		salaDeEspera.setVisible(true);
	}

	@Override
	public void mostrarMenuPrincipal() {
		if (menuPrincipal == null)
			menuPrincipal = new MenuPrincipal();
		flujoActual = EstadosFlujo.MENU_PRINCIPAL;
		this.menuInicioSesion.terminar();
		this.menuPrincipal.setListener(this);
		this.menuPrincipal.setVisible(true);
	}

	@Override
	public void mostrarReglas() {
		if (reglas == null)
			reglas = new Reglas();
		this.menuPrincipal.setVisible(false);
		this.reglas.setListener(this);
		this.reglas.setVisible(true);
	}

	@Override
	public void mostrarTop(List<JugadorLectura> topTen) {
		if (topJugadores == null)
			topJugadores = new TopJugadores();
		menuPrincipal.setVisible(false);
		topJugadores.setListener(this);
		topJugadores.mostrarJugadores(topTen);
		topJugadores.setVisible(true);
	}

	@Override
	public void mostrarGanador(JugadorLectura obtenerGanador) {
		if (cartelGanador == null)
			cartelGanador = new CartelGanador();
		tablero.setVisible(false);
		cartelGanador.setListener(this);
		cartelGanador.setVisible(true);
		cartelGanador.mostrarJugador(obtenerGanador);
	}

	@Override
	public void mostrarPerdedor(JugadorLectura jugador) {
		if (cartelPerdedor == null)
			cartelPerdedor = new CartelPerdedor();
		tablero.setVisible(false);
		cartelPerdedor.setListener(this);
		cartelPerdedor.setVisible(true);
		cartelPerdedor.mostrarJugador(jugador);
	}

	@Override
	public void mostrarEmpate(JugadorLectura jugador) {
		if (cartelEmpate == null)
			cartelEmpate = new CartelEmpate();
		tablero.setVisible(false);
		cartelEmpate.setListener(this);
		cartelEmpate.setVisible(true);
		cartelEmpate.mostrarJugador(jugador);
	}

	@Override
	public String getNombreIntento() {
		return this.nombreIntento;
	}

	// listener de otros JFrame ----------------------------------------------
	@Override
	public void onCloseWindow() {
		if (menuInicioSesion.isVisible()) {
			menuInicioSesion.dispose();
		}
		try {
			controlador.desconectar();
			if (menuPrincipal.isActive()) {
				menuPrincipal.dispose();
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void onCrearButtonClick(String usuario, String contrasenia) {
		nombreIntento = usuario;
		flujoActual = EstadosFlujo.LOG_IN_CREACION;
		try {
			controlador.agregarJugador(usuario, contrasenia);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEntrarButtonClick(String usuario, String contrasenia) {
		nombreIntento = usuario;
		flujoActual = EstadosFlujo.LOG_IN_ENTRADA;
		try {
			controlador.iniciarSesion(usuario, contrasenia);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBuscarPartidaButtonClick() {
		try {
			controlador.jugar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReglamentoButtonClick() {
		mostrarReglas();
	}

	@Override
	public void onSalirButtonClick() {
		onCloseWindow();
	}

	@Override
	public void onMisEstadisticasClick() {
		mostrarEstadisticas();
	}

	@Override
	public void onTopGanadoresButtonClick() {
		try {
			mostrarTop(controlador.getJugadoresTop());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onHoyoButtonClick(String indice) {
		try {
			controlador.mover(Integer.parseInt(indice));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onVolverButtonClick() {
		if (cartelGanador != null && cartelGanador.isVisible()) {
			cartelGanador.setVisible(false);
		}
		if (cartelPerdedor != null && cartelPerdedor.isVisible()) {
			cartelPerdedor.setVisible(false);
		}
		if (cartelEmpate != null && cartelEmpate.isVisible()) {
			cartelEmpate.setVisible(false);
		}
		if (estadistica != null && estadistica.isVisible()) {
			estadistica.setVisible(false);
		}
		if (topJugadores != null && topJugadores.isVisible()) {
			topJugadores.setVisible(false);
		}
		if (reglas != null && reglas.isVisible()) {
			reglas.setVisible(false);
		}
		menuPrincipal.setVisible(true);
	}
}