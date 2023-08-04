package ar.edu.unlu.mancala.controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.mancala.modelo.IMancalaPartida;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class MancalaController implements IControladorRemoto {

	private IMancalaPartida mancalaPartida;
	private Jugador jugador;
	private Ivista vista;
	private String nombreIntento = "";

	public MancalaController(Ivista vista) {
		this.vista = vista;
	}

	public void mover(int indice) throws RemoteException {
		mancalaPartida.mover(indice, this.jugador);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void jugar() throws RemoteException {
		mancalaPartida.conectarJugador(this.jugador);
	}

	public void setVista(Ivista vista) {
		this.vista = vista;
	}

	public void agregarJugador(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancalaPartida.agregarJugador(nombre, contrasenia);
	}

	public void iniciarSesion(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancalaPartida.verificarCredenciales(nombre, contrasenia);
	}

	// recuperado jugadores para el top 10
	public List<JugadorLectura> getJugadoresTop() throws RemoteException {
		return mancalaPartida.getTop(10);
	}

	public void desconectar() throws RemoteException {
		mancalaPartida.desconectar(this.jugador, this);
	}

	public JugadorLectura getJugador() {
		return (JugadorLectura) this.jugador;
	}

	@Override
	public void actualizar(IObservableRemoto modelo, Object evento) throws RemoteException {
		if (evento instanceof EstadoPartida) {
			Jugador jugadorMueve = mancalaPartida.getJugadoresEnJuego().get(mancalaPartida.getTurnoActual());
			switch ((EstadoPartida) evento) {
			case USUARIO_CONECTADO:
				Jugador ultimoJugador = mancalaPartida.getJugadoresEnJuego()
						.get(mancalaPartida.getJugadoresEnJuego().size());
				if (mancalaPartida.getJugadoresEnJuego().size() == 1 && ultimoJugador.equals(this.jugador)) {
					vista.mostrarSalaDeEspera();
				} else if (mancalaPartida.getJugadoresEnJuego().size() != 2 && !ultimoJugador.equals(this.jugador)) {
					vista.informar((JugadorLectura) ultimoJugador, "se creo una partida, en sala de espera está: ");
				}
				break;
			case USUARIO_DESCONECTADO:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					// para tener las ultimas actualizaciones de las estadisticas despues del
					// abandono
					this.jugador = mancalaPartida.getJugador(this.jugador);
					vista.informar("se desconecto su rival. Gana automaticamente!");
					vista.mostrarGanador((JugadorLectura) jugador);
				}
				break;
			case PARTIDA_LLENA:
				vista.informar("no se admiten mas participantes");
				break;
			case ESPERANDO_USUARIO:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("esperando usuario... ");
				}
				break;
			case COMENZANDO_PARTIDA:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					vista.mostrarPartida((TableroLectura) mancalaPartida.getTablero(), (JugadorLectura) jugadorMueve);
				}
				break;
			case PARTIDA_EN_PROGRESO:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					vista.mostrarPartida((TableroLectura) mancalaPartida.getTablero(), (JugadorLectura) jugadorMueve);
				}
				break;
			case PARTIDA_TERMINADA:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					// para tener la ultima actualizacion en las estadisticas despues de la
					// finalizacion de la partida
					this.jugador = mancalaPartida.getJugador(this.jugador);
					Jugador jugadorGanador = mancalaPartida.obtenerGanador();
					vista.mostrarPartida((TableroLectura) mancalaPartida.getTablero(), (JugadorLectura) jugadorMueve);
					if (jugadorGanador == null) {
						vista.mostrarEmpate((JugadorLectura) this.jugador);
					} else if (jugadorGanador.equals(this.jugador)) {
						vista.mostrarGanador((JugadorLectura) this.jugador);
					} else {
						vista.mostrarPerdedor((JugadorLectura) this.jugador);
					}
				}
				break;
			default:
				break;
			}
		}
		if (evento instanceof EstadoTablero) {
			switch ((EstadoTablero) evento) {
			case MOVIMIENTO_REALIZADO:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
						vista.informar("movimiento realizado!");
					} else {
						vista.informar("su rival movio!");
					}
				}
				break;
			case MOVIMIENTO_INVALIDO_RANGO:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("Ingreso un indice fuera del rango del tablero");
				}
				break;
			case MOVIMIENTO_INVALIDO_POSICION:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("ingreso un indice que no le pertenece");
				}
				break;
			case CAPTURA_REALIZADA:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("excelente, realizo una captura!!");
				} else {
					vista.informar("capuraron uno de sus agujeros!");
				}
				break;
			case TURNO_INVALIDO:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("no es su turno!!");
				}
				break;
			case MOVIMIENTO_INVALIDO_HABAS:
				if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
					vista.informar("no hay habas para mover en ese indice");
				}
				break;
			case MOVIMIENTO_VALIDO_SIGUE:
				if (mancalaPartida.getJugadoresEnJuego().containsValue(this.jugador)) {
					if (mancalaPartida.getUltimoEnMover().equals(this.jugador)) {
						vista.informar("Genial!, ultima haba en casa");
					} else {
						vista.informar("Su rival puso la ultima haba en casa!");
					}
				}
			default:
				break;
			}
		}
		if (evento instanceof EstadoPersistencia) {
			switch ((EstadoPersistencia) evento) {
			case LOGEADO:
				if (this.nombreIntento.equals(vista.getNombreIntento())) {
					setJugador(mancalaPartida.getJugadoresConectados()
							.get(mancalaPartida.getJugadoresConectados().size() - 1));
					vista.mostrarMenuPrincipal();
					vista.informar("logeado con exito!!");
				}
				this.nombreIntento = "";
				break;
			case NOMBRE_EXISTENTE:
				if (this.nombreIntento.equals(vista.getNombreIntento()))
					vista.informar("el nombre de usuario ya existe!!, ingrese otro.");
				this.nombreIntento = "";
				break;
			case GUARDADO_EXITOSO:
				if (this.nombreIntento.equals(vista.getNombreIntento())) {
					setJugador(mancalaPartida.getJugadoresConectados()
							.get(mancalaPartida.getJugadoresConectados().size() - 1));
					vista.mostrarMenuPrincipal();
					vista.informar("se creo la cuenta con exito!! No olvide sus credenciales.");
				}
				this.nombreIntento = "";
				break;
			case USUARIO_YA_CONECTADO:
				if (this.nombreIntento.equals(vista.getNombreIntento()))
					vista.informar("La cuenta esta online, no se permite acceso concurrente");
				this.nombreIntento = "";
				break;
			case CREDENCIALES_INVALIDAS:
				if (this.nombreIntento.equals(vista.getNombreIntento()))
					vista.informar("Nombre o contrasenia incorrectos, ingrese los datos de nuevo");
				this.nombreIntento = "";
				break;
			default:
				break;
			}
		}
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.mancalaPartida = (IMancalaPartida) modeloRemoto;
	}

	public List<JugadorLectura> obtenerJugadoresEnPartida() throws RemoteException {
		return new ArrayList<>(this.mancalaPartida.getJugadoresEnJuego().values());
	}
}
