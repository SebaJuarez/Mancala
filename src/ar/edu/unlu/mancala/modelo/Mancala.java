package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.partida.jugador.EstadoJugadorPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.security.Encriptador;
import ar.edu.unlu.mancala.serializacion.services.JugadorService;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Mancala extends ObservableRemoto implements IMancala {

	private Partida partida;
	private final JugadorService service;
	private List<Jugador> jugadoresConectados = new LinkedList<Jugador>();

	public Mancala(JugadorService service) throws RemoteException {
		this.service = service;
	}

	@Override
	public void iniciarPartida(Jugador jugador, TipoPartida tipoPartida) throws RemoteException {
		if (partida == null || partida.isPartidaTerminada()) {
			partida = new Partida(tipoPartida);
		}
		conectarJugadorAPartida(jugador);
	}

	private void conectarJugadorAPartida(Jugador jugador) throws RemoteException {
		EstadoPartida estadoActual = partida.agregarJugador(jugador);
		switch (estadoActual) {
		case USUARIO_CONECTADO:
			notificarObservadores(estadoActual);
			notificarObservadores(partida.listoParaComezar());
			break;
		case PARTIDA_LLENA:
			notificarObservadores(estadoActual);
			break;
		default:
			;
		}
	}

	@Override
	public void mover(int indice, Jugador jugador) throws RemoteException {
		EstadoMovimiento estadoMov = partida.mover(jugador, indice);
		notificarObservadores(estadoMov);
		if (!partida.isMovimientoInvalido(estadoMov)) {
			EstadoPartida estadoPartida = partida.termino();
			if (estadoPartida == EstadoPartida.PARTIDA_TERMINADA) {
				actualizarJugadores(partida.getJugadores());
			}
			notificarObservadores(estadoPartida);
		}
	}

	@Override
	public List<Jugador> obtenerGanador() throws RemoteException {
		return partida.obtenerGanador();
	}

	@Override
	public void actualizarJugadores(List<Jugador> jugadores) throws RemoteException {
		List<Jugador> ganador = obtenerGanador();
		List<Jugador> perdedor = jugadores.stream().filter(jugador -> !ganador.contains(jugador))
				.collect(Collectors.toList());
		// si hay mas de un ganador entonces fueron empates, si hay uno solo entonces
		// ganó

		if (ganador.size() > 1) {
			ganador.forEach(jugador -> jugador.incEmpatadas());
		} else {
			ganador.forEach(jugador -> jugador.incGanadas());
		}
		perdedor.forEach(jugador -> jugador.incPerdidas());

		// guardo la actualizacion de perdedores y ganadores
		ganador.addAll(perdedor);
		guardarJugadores(ganador);
	}

	// metodos de persistencia de jugadores

	@Override
	public void verificarCredenciales(String nombre, String contrasenia) throws RemoteException {
		Jugador jugadorConectado = service.obtenerJugadores().stream()
				.filter(jugador -> jugador.getNombre().equals(nombre)
						&& Encriptador.verificarContrasenia(contrasenia, jugador.getContrasenia()))
				.findFirst().orElse(null);
		if (jugadorConectado == null) {
			notificarObservadores(EstadoPersistencia.CREDENCIALES_INVALIDAS);
		} else {
			// Si el jugador ingresó bien la contraseña y username, y no está conectada otra
			// persona desde su cuenta,
			if (!jugadoresConectados.stream()
					.anyMatch(jugador -> jugador.getNombre().equals(jugadorConectado.getNombre()))) {
				// lo guardamos en jugadores conectados y notificamos
				jugadoresConectados.add(jugadorConectado);
				notificarObservadores(EstadoPersistencia.LOGEADO);
			} else {
				// notificamos intento de acceso concurrente
				notificarObservadores(EstadoPersistencia.USUARIO_YA_CONECTADO);
			}
		}

	}

	@Override
	public void crearJugador(String nombre, String contrasenia) throws RemoteException {
		if (service.obtenerJugadorPorNombre(nombre) != null) {
			notificarObservadores(EstadoPersistencia.NOMBRE_EXISTENTE);
		} else {
			Jugador jugador = new Jugador();
			jugador.setNombre(nombre);
			jugador.setContrasenia(Encriptador.encriptarContrasenia(contrasenia));
			jugador.setEmpatadas(0);
			jugador.setGanadas(0);
			jugador.setPerdidas(0);
			service.guardar(jugador);
			jugadoresConectados.add(jugador);
			notificarObservadores(EstadoPersistencia.GUARDADO_EXITOSO);
		}
	}

	private void guardarJugadores(List<Jugador> jugadores) throws RemoteException {
		List<Jugador> jugadoresN = service.obtenerJugadores();

		jugadoresN.replaceAll(jugadorViejo -> jugadores.stream()
				.filter(jugadorNuevo -> jugadorNuevo.equals(jugadorViejo)).findFirst().orElse(jugadorViejo));

		this.service.guardar(jugadoresN); // Guardar la lista actualizada
	}

	@Override
	public void desconectar(Jugador desconectado, IControladorRemoto controlador) throws RemoteException {
		this.jugadoresConectados.remove(desconectado);
		removerObservador(controlador);
		// si el jugador estaba en partida y se deconecta
		if (partida != null)
			if (isJugadorEnPartida(desconectado) && !partida.isPartidaTerminada() && isPartidaLLena()) {
				partida.setPartidaTerminada(true);
				partida.desconectarJugador(desconectado);
				// obtengo los conectados en partida
				List<Jugador> conectados = new LinkedList<Jugador>(partida.getJugadores());
				// si hay un solo conectado, entonces gano, si hay mas entonces empataron
				if (conectados.size() > 1) {
					conectados.forEach(conectado -> conectado.incEmpatadas());
				} else {
					conectados.forEach(conectado -> conectado.incGanadas());
				}
				// el desconectado perdio automaticamente
				desconectado.incPerdidas();

				// actualizo los desc y los conectados
				conectados.add(desconectado);
				guardarJugadores(conectados);
				notificarObservadores(EstadoPartida.USUARIO_DESCONECTADO);
				// si el jugador estaba en la sala de espera y se desconecta
			} else if (isJugadorEnPartida(desconectado) && !isPartidaLLena() && !partida.isPartidaTerminada()) {
				partida.desconectarJugador(desconectado);
			}
	}

	@Override
	public List<JugadorLectura> getTop(int limite) throws RemoteException {
		List<Jugador> jugadores = service.obtenerJugadores();
		return jugadores.stream().map(j -> (JugadorLectura) j).sorted((j1, j2) -> {
			// comparo por ganadas de manera descendente
			int comparacionPorGanadas = Integer.compare(j2.getGanadas(), j1.getGanadas());
			if (comparacionPorGanadas != 0) {
				// Si el numero de ganadas es diferente, retorna la comparacion por ganadas
				return comparacionPorGanadas;
			} else {
				// Si el numero de ganadas es igual, compara por partidas perdidas de manera
				// descendente
				int comparacionPorPerdidas = Integer.compare(j1.getPerdidas(), j2.getPerdidas());
				if (comparacionPorPerdidas != 0) {
					// Si el número de perdidas es diferente, retorna la comparación por perdidas
					return comparacionPorPerdidas;
				} else {
					// Si el número de perdidas también es igual, compara por partidas empatadas
					// descendente
					return Integer.compare(j1.getEmpatadas(), j2.getEmpatadas());
				}
			}
		}).limit(limite).collect(Collectors.toList());
	}

	@Override
	public Jugador turnoActual() throws RemoteException {
		return partida.getTurnoActual();
	}

	@Override
	public boolean isPartidaLLena() throws RemoteException {
		return partida.partidaLlena();
	}

	@Override
	public Jugador ultimoJugadorEnPartida() throws RemoteException {
		return partida.ultimoJugadorConectado();
	}

	@Override
	public Jugador getJugador(String jugador) throws RemoteException {
		return service.obtenerJugadorPorNombre(jugador);
	}

	@Override
	public List<LadoTablero> getTablero() throws RemoteException {
		return partida.getTablero().getLadosDelTablero();
	}

	@Override
	public boolean isJugadorUltimoEnMover(Jugador jugador) throws RemoteException {
		return partida.isJugadorUltimoEnMover(jugador);
	}

	@Override
	public boolean isJugadorEnPartida(Jugador jugador) throws RemoteException {
		return partida.isJugadorEnPartida(jugador);
	}

	@Override
	public String getUltimoEnMover() throws RemoteException {
		return partida.getUltimoEnMover().getNombre();
	}

	@Override
	public TipoPartida tipoPartidaComenzada() throws RemoteException {
		return partida.getTipoPartida();
	}

	@Override
	public EstadoJugadorPartida estadoJugadorPartida(Jugador jugador, EstadoPartida estado) throws RemoteException {

		if (estado == EstadoPartida.PARTIDA_TERMINADA) {
			List<Jugador> jugadorGanador = obtenerGanador();

			if (!jugadorGanador.contains(jugador)) {
				return EstadoJugadorPartida.PERDIO;
			}

			if (jugadorGanador.size() > 1) {
				return EstadoJugadorPartida.EMPATO;
			} else {
				return EstadoJugadorPartida.GANO;
			}
		} else {
			if (partida.getJugadores().size() == 1) {
				return EstadoJugadorPartida.GANO;
			} else {
				return EstadoJugadorPartida.EMPATO;
			}
		}
	}
}
