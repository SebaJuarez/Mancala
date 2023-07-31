package ar.edu.unlu.mancala.vista.grafica;

import java.util.List;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.vista.AgujeroLectura;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;
import ar.edu.unlu.mancala.vista.consola.EstadosFlujo;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuInicioSesionListener;
import ar.edu.unlu.mancala.vista.grafica.listener.MenuPrincipalListener;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;

import java.rmi.RemoteException;


public class VistaGrafica implements Ivista, MenuInicioSesionListener, MenuPrincipalListener , TableroPartidaListener {

	private MancalaController controlador;
    private String nombreIntento;
    private EstadosFlujo flujoActual = EstadosFlujo.LOG_IN; 
    // Jframes --------------------------------------------------------
    private MenuInicioSesion menuInicioSesion = new MenuInicioSesion();
	private MenuPrincipal menuPrincipal = new MenuPrincipal();
	private TableroPartida tablero = new TableroPartida();
	private SalaDeEspera salaDeEspera = new SalaDeEspera();


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
		switch(flujoActual) {
		case LOG_IN_ENTRADA :
			((FormularioInicioSesion)menuInicioSesion.getFormularioInicioSesion()).mostrarAviso(string);
			break;
		case LOG_IN_CREACION :
			((FormularioCreacionUsuario)menuInicioSesion.getFormularioCreacionUsuario()).mostrarAviso(string);
			break;
		case MOVIMIENTOS :
			tablero.informar(string);
			break;
		case MENU_PRINCIPAL : 
			menuPrincipal.informar(string);
		default:
			break;
		}
	}

	@Override
	public void informar(JugadorLectura jugador, String string) {
		tablero.informar(string + jugador.getNombre());
	}

	@Override
	public void mostrarGanador(JugadorLectura obtenerGanador) {
		
	}

	@Override
	public void mostrarMenuInicioSesion() {
		flujoActual = EstadosFlujo.LOG_IN;
		menuInicioSesion.setListener(this);
		menuInicioSesion.setVisible(true);
	}

	@Override
	public void mostrarEstadisticas() {
		
	}

	@Override
	public void mostrarPartida(TableroLectura tablero, JugadorLectura jugadorMueve) {
		
		if(flujoActual == EstadosFlujo.ESPERA) {
			salaDeEspera.setVisible(false);
		}
		
		if(!this.tablero.isVisible()) {
			this.tablero.setListener(this);
			this.tablero.setVisible(true);
			this.menuPrincipal.setVisible(false);
		}
		this.tablero.actualizarTablero((AgujeroLectura[])tablero.getAgujeros());
		flujoActual = EstadosFlujo.MOVIMIENTOS;
		informar(jugadorMueve, "Le toca al jugador: ");
	}

	@Override
	public void mostrarSalaDeEspera() {
		flujoActual = EstadosFlujo.ESPERA;
		menuPrincipal.setVisible(false);
		salaDeEspera.setVisible(true);
	}

	@Override
	public void mostrarMenuPrincipal() {
		this.menuInicioSesion.terminar();
		this.menuPrincipal.setListener(this);
		this.menuPrincipal.setVisible(true);
	}

	@Override
	public void mostrarReglas() {
		menuPrincipal.mostrarReglas();
	}

	@Override
	public void mostrarTop(List<JugadorLectura> topTen) {
		
	}

	@Override
	public void mostrarPerdedor(JugadorLectura jugador) {
		
	}

	@Override
	public void mostrarEmpate(JugadorLectura jugador) {
		
	}

	@Override
	public String getNombreIntento() {
		return this.nombreIntento;
	}

	// listener de otros JFrame ----------------------------------------------
	@Override
	public void onCloseWindow() {
		// si se cierra la ventana antes de menu principal, entonces se cerraron todas las ventanas
		if(flujoActual != EstadosFlujo.MENU_PRINCIPAL) {
			try {
				controlador.desconectar();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}			
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
	public void solicitarJugadores() {
		try {
			this.tablero.setJugadores(controlador.obtenerJugadoresEnPartida());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}