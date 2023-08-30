package ar.edu.unlu.mancala.controlador;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.unlu.mancala.modelo.IMancala;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;
import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.vista.Ivista;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class MancalaController implements IControladorRemoto {

	private IMancala mancala;
	private Jugador jugador;
	private Ivista vista;
	private String nombreIntento = "";

	public MancalaController(Ivista vista) {
		this.vista = vista;
	}

	public void mover(int indice) throws RemoteException {
		mancala.mover(indice, this.jugador);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void jugar() throws RemoteException {
		//mancala.iniciarPartida(this.jugador, TipoPartida.PARTIDA_STANDAR);
		//mancala.iniciarPartida(this.jugador, TipoPartida.PARTIDA_4_S_AHORARIO);
		mancala.iniciarPartida(this.jugador,TipoPartida.PARTIDA_MODO_CAPTURA);
	}

	public void setVista(Ivista vista) {
		this.vista = vista;
	}

	public void agregarJugador(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancala.agregarJugador(nombre, contrasenia);
	}

	public void iniciarSesion(String nombre, String contrasenia) throws RemoteException {
		this.nombreIntento = nombre;
		mancala.verificarCredenciales(nombre, contrasenia);
	}

	// recuperado jugadores para el top 10
	public List<JugadorLectura> getJugadoresTop() throws RemoteException {
		return mancala.getTop(10);
	}

	public void desconectar() throws RemoteException {
		mancala.desconectar(this.jugador, this);
	}

	public JugadorLectura getJugador() {
		return (JugadorLectura) this.jugador;
	}

	@Override
	public void actualizar(IObservableRemoto modelo, Object evento) throws RemoteException {
		if (evento instanceof EstadoPartida) {
			Jugador jugadorMueve = mancala.getPartida().getTurnoActual();
			switch ((EstadoPartida) evento) {
			case USUARIO_CONECTADO:
				Jugador ultimoJugador = mancala.getJugadoresEnPartida()
						.get(mancala.getJugadoresEnPartida().size() - 1);
				if (mancala.getJugadoresEnPartida().size() < mancala.getPartida().getParticipantesLimite()
						&& ultimoJugador.equals(this.jugador)) {
					vista.mostrarSalaDeEspera();
				} else if (mancala.getJugadoresEnPartida().size() != mancala.getPartida()
						.getParticipantesLimite() && !ultimoJugador.equals(this.jugador)) {
					vista.informar((JugadorLectura) ultimoJugador, "se creo una partida, en sala de espera está: ");
				}
				break;
			case USUARIO_DESCONECTADO:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					// para tener las ultimas actualizaciones de las estadisticas despues del
					// abandono
					this.jugador = mancala.getJugador(this.jugador);
					if (mancala.getJugadoresEnPartida().size() == 1) {
						vista.mostrarGanador(this.jugador);
						vista.informar("se desconecto su rival. Gana automaticamente!");
					} else {
						vista.mostrarEmpate(this.jugador);
						vista.informar("se desconecto un rival. Empato automaticamente!");
					}
				}
				break;
			case PARTIDA_LLENA:
				vista.informar("no se admiten mas participantes");
				break;
			case ESPERANDO_USUARIO:
				if (mancala.getJugadoresEnPartida().get(0).equals(this.jugador)) {
					vista.informar("esperando usuario... ");
				}
				break;
			case COMENZANDO_PARTIDA:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					vista.mostrarPartida(ordenarLados(mancala.getTablero()), (JugadorLectura) jugadorMueve,
							mancala.getPartida().getTipoPartida());
				}
				break;
			case PARTIDA_EN_PROGRESO:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					vista.mostrarPartida(ordenarLados(mancala.getTablero()), (JugadorLectura) jugadorMueve,
							mancala.getPartida().getTipoPartida());
				}
				break;
			case PARTIDA_TERMINADA:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					// para tener la ultima actualizacion en las estadisticas despues de la
					// finalizacion de la partida
					this.jugador = mancala.getJugador(this.jugador);
					List<Jugador> jugadorGanador = mancala.obtenerGanador();
					vista.mostrarPartida(ordenarLados(mancala.getTablero()), (JugadorLectura) jugadorMueve,
							mancala.getPartida().getTipoPartida());
					// si hay mas de un jugado entonces hay empate
					if (jugadorGanador.size() > 1) {
						if (jugadorGanador.contains(this.jugador)) {
							vista.mostrarEmpate((JugadorLectura) this.jugador);
						} else {
							vista.mostrarPerdedor((JugadorLectura) this.jugador);
						}
					} else {
						if (jugadorGanador.contains(this.jugador)) {
							vista.mostrarGanador((JugadorLectura) this.jugador);
						} else {
							vista.mostrarPerdedor((JugadorLectura) this.jugador);
						}
					}
				}
				break;
			default:
				break;
			}
		}
		if (evento instanceof EstadoMovimiento) {
			switch ((EstadoMovimiento) evento) {
			case MOVIMIENTO_REALIZADO:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
						vista.informar("movimiento realizado!");
					} else {
						vista.informar("su rival movio!");
					}
				}
				break;
			case MOVIMIENTO_INVALIDO_RANGO:
				if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
					vista.informar("Ingreso un indice fuera del rango del tablero");
				}
				break;
			case MOVIMIENTO_INVALIDO_POSICION:
				if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
					vista.informar("Ingreso un agujero invalido!");
				}
				break;
			case CAPTURA_REALIZADA:
				if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
					vista.informar("Excelente, realizo una captura!!");
				} else {
					vista.informar(mancala.getPartida().getUltimoEnMover().getNombre() + " Capturo un agujero!");
				}
				break;
			case TURNO_INVALIDO:
				if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
					vista.informar("No es su turno!!");
				}
				break;
			case MOVIMIENTO_INVALIDO_HABAS:
				if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
					vista.informar("No hay habas para mover en ese indice");
				}
				break;
			case MOVIMIENTO_VALIDO_SIGUE:
				if (mancala.getJugadoresEnPartida().contains(this.jugador)) {
					if (mancala.getPartida().getUltimoEnMover().equals(this.jugador)) {
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
					setJugador(mancala.getJugadoresConectados()
							.get(mancala.getJugadoresConectados().size() - 1));
					vista.mostrarMenuPrincipal();
					vista.informar("Logeado con exito!!");
				}
				this.nombreIntento = "";
				break;
			case NOMBRE_EXISTENTE:
				if (this.nombreIntento.equals(vista.getNombreIntento()))
					vista.informar("El nombre de usuario ya existe!!, ingrese otro.");
				this.nombreIntento = "";
				break;
			case GUARDADO_EXITOSO:
				if (this.nombreIntento.equals(vista.getNombreIntento())) {
					setJugador(mancala.getJugadoresConectados()
							.get(mancala.getJugadoresConectados().size() - 1));
					vista.mostrarMenuPrincipal();
					vista.informar("Se creo la cuenta con exito!! No olvide sus credenciales.");
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
		this.mancala = (IMancala) modeloRemoto;
	}

	private List<LadoTablero> ordenarLados(List<LadoTablero> ladosTablero) {
		Queue<LadoTablero> ladosOrdenados = new LinkedList<LadoTablero>(ladosTablero);
		LadoTablero lado;
		while (!ladosOrdenados.peek().perteneceJugador(this.jugador)) {
			lado = ladosOrdenados.poll();
			ladosOrdenados.add(lado);
		}
		return List.copyOf(ladosOrdenados);
	}
}
