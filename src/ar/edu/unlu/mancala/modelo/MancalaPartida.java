package ar.edu.unlu.mancala.modelo;

import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.security.Encriptador;
import ar.edu.unlu.mancala.serializacion.services.JugadorService;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class MancalaPartida extends ObservableRemoto implements IMancalaPartida {

	private Map<Integer, Jugador> jugadoresEnJuego =  new HashMap<Integer, Jugador>(2);
	private Tablero tablero;
	private MoveValidator moveValidator;
	private int turnoActual;
	private boolean partidaTerminada;
	private Jugador ultimoEnMover;
	//private LinkedList<Observer> observadores = new LinkedList<Observer>();
	private List<Jugador> jugadores;
	private final JugadorService service;
	
	//-----
	private List<Jugador> jugadoresConectados  = new LinkedList<Jugador>();

	public MancalaPartida(JugadorService service) throws RemoteException{
		this.service = service;
		this.jugadores = service.obtenerJugadores();
	}

	@Override
	public void conectarJugador(Jugador jugador) throws RemoteException {
		if(partidaTerminada = true && this.jugadoresEnJuego.size() == 2) {
			this.jugadoresEnJuego = new HashMap<Integer, Jugador>(2);
		}
		
		if (this.jugadoresEnJuego.size() == 2)
			notificarObservadores(EstadoPartida.PARTIDA_LLENA);
		else if (this.jugadoresEnJuego.isEmpty()) {
			jugadoresEnJuego.put(1, jugador);
			notificarObservadores(EstadoPartida.USUARIO_CONECTADO);
		} else {
			jugadoresEnJuego.put(2, jugador);	
			notificarObservadores(EstadoPartida.USUARIO_CONECTADO);
			iniciarPartida();
		}
	}

	@Override
	public void iniciarPartida() throws RemoteException {
		this.tablero = new Tablero();
		this.moveValidator = new MoveValidator();
		this.partidaTerminada = false;
		this.setTurnoActual(((int) Math.random() * 2) + 1);
		notificarObservadores(EstadoPartida.COMENZANDO_PARTIDA);
	}

	@Override
	public void mover(int indice, Jugador jugador) throws RemoteException {
		this.ultimoEnMover = jugador;
		// si no estan todos los jugadores entonces no puedo mover
		if (this.jugadoresEnJuego.size() != 2) {
			notificarObservadores(EstadoPartida.ESPERANDO_USUARIO);
		} else {
			// valido el movimiento 
			EstadoTablero estado = this.moveValidator.validarMovimiento(tablero, turnoActual, obtenerClaveDeJugador(jugador), indice);
			// si el movimiento es valido entonces cambio el turno
			if (estado == EstadoTablero.MOVIMIENTO_VALIDO) {
				EstadoTablero movimientoEstado = tablero.mover(indice, obtenerClaveDeJugador(jugador));
				notificarObservadores(movimientoEstado);
				if( EstadoTablero.MOVIMIENTO_VALIDO_SIGUE != movimientoEstado) {
					this.cambiarTurno();					
				}
				termino();
			} else {
				notificarObservadores(estado);				
			}
		}
	}

	@Override
	public void termino() throws RemoteException {
		boolean ladoVacio = true;
		for (int i = 1; i < tablero.getPOS_CASAJ1(); i++) {
			if (this.tablero.getAgujeros()[i].getHabas() != 0) {
				ladoVacio = false;
				break;
			}
		}
		if(!ladoVacio) {
			for (int i = tablero.getPOS_CASAJ1() + 1; i < tablero.getLONGUITUD_TABLERO() - 1; i++) {
				if (this.tablero.getAgujeros()[i].getHabas() != 0) {
					ladoVacio = false;
					break;
				}
			}			
		}
		this.setPartidaTerminada(ladoVacio);
	}

	@Override
	public int obtenerClaveDeJugador(Jugador jugador) throws RemoteException{
	    return jugadoresEnJuego.entrySet()
	        .stream()
	        .filter(entry -> entry.getValue().equals(jugador))
	        .map(Map.Entry::getKey)
	        .findFirst()
	        .orElse(-1);
	}
	
	@Override
	public Jugador obtenerGanador() throws RemoteException{
		Agujero casaJ1 = tablero.getAgujeros()[tablero.getPOS_CASAJ1()];
		Agujero casaJ2 = tablero.getAgujeros()[tablero.getPOS_CASAJ2()];
		Jugador jugador1 = jugadoresEnJuego.get(1);
		Jugador jugador2 = jugadoresEnJuego.get(2);
		actualizarJugadores(jugador1, jugador2);
		if (casaJ1.getHabas() > casaJ2.getHabas()) {
			jugador1.setGanadas(jugador1.getGanadas() + 1);
			jugador2.setPerdidas(jugador2.getPerdidas() + 1);
		    return jugador1;
		} else if (casaJ1.getHabas() < casaJ2.getHabas()) {
			jugador1.setPerdidas(jugador1.getPerdidas() + 1);
			jugador2.setGanadas(jugador2.getGanadas() + 1);
		    return jugador2;
		} else {
			jugador1.setEmpatadas(jugador1.getEmpatadas() + 1);
			jugador2.setEmpatadas(jugador2.getEmpatadas() + 1);
		    return null;
		}
	}
	
	@Override
	public void cambiarTurno() throws RemoteException{
		this.turnoActual = (this.turnoActual == 1) ? 2 : 1;
	}
	

	@Override
	public void setPartidaTerminada(boolean partidaTerminada) throws RemoteException {
		this.partidaTerminada = partidaTerminada;
		if (partidaTerminada) {
			notificarObservadores(EstadoPartida.PARTIDA_TERMINADA);
			} else {
				notificarObservadores(EstadoPartida.PARTIDA_EN_PROGRESO);
			}
	}

	// metodos de persistencia de jugadores
	
	@Override
	public void verificarCredenciales(String nombre, String contrasenia) throws RemoteException {
	    Jugador jugadorConectado = getJugadores().stream()
	            .filter(jugador -> jugador.getNombre().equals(nombre) && Encriptador.verificarContrasenia(contrasenia, jugador.getContrasenia()))
	            .findFirst()
	            .orElse(null);
	    
	    if(jugadorConectado == null) {
	    	notificarObservadores(EstadoPersistencia.CREDENCIALES_INVALIDAS);
	    } else {
	    	// Si el jugador ingresó bien la contraseña y username, y no está conectada otra persona desde su cuenta,
	    	if (!jugadoresConectados.stream().anyMatch(jugador -> jugador.getNombre().equals(jugadorConectado.getNombre()))) {
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
	public void agregarJugador(String nombre, String contrasenia) throws RemoteException {
		if(service.obtenerJugadorPorNombre(nombre) != null) {
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
			this.jugadores = service.obtenerJugadores();
			notificarObservadores(EstadoPersistencia.GUARDADO_EXITOSO);			
		}
    }

	@Override
	public void actualizarJugadores(Jugador jugador1, Jugador jugador2) throws RemoteException{
		this.jugadores.forEach(jugador -> {
			if (jugador.getNombre().equals(jugador1.getNombre())) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador1);
			} else if (jugador.getNombre().equals(jugador2.getNombre())) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador2);
			}
		});
		this.service.guardar(this.jugadores);
	}
	
	@Override
	public List<Jugador> getJugadores() throws RemoteException{
		return this.jugadores;
	}
	
	@Override
	public List<Jugador> getJugadoresConectados()throws RemoteException{
		return this.jugadoresConectados;
	}
	
	// metodos del observer
	/*
	@Override
	public void agregarObservador(Observer observer) {
		this.observadores.add(observer);
	}*/
	
	/*@Override
	public void notificarObservers(Object informe) {
		this.observadores.forEach((observer) -> observer.update(this,informe));		
	}*/
	
	// getters y setters
		@Override
		public TableroLectura getTablero() throws RemoteException{
			return tablero;
		}

		@Override
		public void setTablero(Tablero tablero)throws RemoteException {
			this.tablero = tablero;
		}

		@Override
		public MoveValidator getMoveValidator()throws RemoteException {
			return moveValidator;
		}

		@Override
		public void setMoveValidator(MoveValidator movValidator)throws RemoteException {
			this.moveValidator = movValidator;
		}

		@Override
		public Map<Integer, Jugador> getJugadoresEnJuego()throws RemoteException {
			return jugadoresEnJuego;
		}

		@Override
		public void setJugadoresEnJuego(Map<Integer, Jugador> jugadores) throws RemoteException{
			this.jugadoresEnJuego = jugadores;
		}

		@Override
		public int getTurnoActual() throws RemoteException{
			return turnoActual;
		}

		@Override
		public void setTurnoActual(int turnoActual) throws RemoteException{
			this.turnoActual = turnoActual;
		}


		@Override
		public boolean isPartidaTerminada() throws RemoteException{
			return partidaTerminada;
		}
		
		@Override
		public Jugador getUltimoEnMover() throws RemoteException{
			return this.ultimoEnMover;
		}

		@Override
		public void desconectar(Jugador jugador) throws RemoteException {
			this.jugadoresConectados.remove(jugador);
		}

		@Override
		public List<JugadorLectura> getTop(int limite) throws RemoteException{
			return this.jugadores.stream()
					.map(j -> (JugadorLectura)j)
					.sorted(Comparator.comparing(JugadorLectura::getGanadas).reversed())
					.limit(limite)
					.collect(Collectors.toList());
		}
}
