package ar.edu.unlu.mancala.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;
import ar.edu.unlu.mancala.modelo.estados.persistencia.EstadoPersistencia;
import ar.edu.unlu.mancala.modelo.estados.tablero.EstadoTablero;
import ar.edu.unlu.mancala.observer.Observado;
import ar.edu.unlu.mancala.observer.Observer;
import ar.edu.unlu.mancala.security.Encriptador;
import ar.edu.unlu.mancala.serializacion.services.JugadorService;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.TableroLectura;

public class MancalaPartida implements Observado{

	private Map<Integer, Jugador> jugadoresEnJuego =  new HashMap<Integer, Jugador>(2);
	private Tablero tablero;
	private MoveValidator moveValidator;
	private int turnoActual;
	private boolean partidaTerminada;
	private Jugador ultimoEnMover;
	private LinkedList<Observer> observadores = new LinkedList<Observer>();
	private List<Jugador> jugadores;
	private final JugadorService service;
	
	//-----
	private List<Jugador> jugadoresConectados  = new LinkedList<Jugador>();

	public MancalaPartida(JugadorService service) {
		this.service = service;
		this.jugadores = service.obtenerJugadores();
	}

	public void conectarJugador(Jugador jugador) {
		if(partidaTerminada = true && this.jugadoresEnJuego.size() == 2) {
			this.jugadoresEnJuego = new HashMap<Integer, Jugador>(2);
		}
		
		if (this.jugadoresEnJuego.size() == 2)
			notificarObservers(EstadoPartida.PARTIDA_LLENA);
		else if (this.jugadoresEnJuego.isEmpty()) {
			jugadoresEnJuego.put(1, jugador);
			notificarObservers(EstadoPartida.USUARIO_CONECTADO);
		} else {
			jugadoresEnJuego.put(2, jugador);	
			notificarObservers(EstadoPartida.USUARIO_CONECTADO);
			iniciarPartida();
		}
	}

	public void iniciarPartida() {
		this.tablero = new Tablero();
		this.moveValidator = new MoveValidator();
		this.partidaTerminada = false;
		this.setTurnoActual(((int) Math.random() * 2) + 1);
		notificarObservers(EstadoPartida.COMENZANDO_PARTIDA);
	}

	public void mover(int indice, Jugador jugador) {
		this.ultimoEnMover = jugador;
		// si no estan todos los jugadores entonces no puedo mover
		if (this.jugadoresEnJuego.size() != 2) {
			notificarObservers(EstadoPartida.ESPERANDO_USUARIO);
		} else {
			// valido el movimiento 
			EstadoTablero estado = this.moveValidator.validarMovimiento(tablero, turnoActual, obtenerClaveDeJugador(jugador), indice);
			// si el movimiento es valido entonces cambio el turno
			if (estado == EstadoTablero.MOVIMIENTO_VALIDO) {
				EstadoTablero movimientoEstado = tablero.mover(indice, obtenerClaveDeJugador(jugador));
				notificarObservers(movimientoEstado);
				if( EstadoTablero.MOVIMIENTO_VALIDO_SIGUE != movimientoEstado) {
					this.cambiarTurno();					
				}
				termino();
			} else {
				notificarObservers(estado);				
			}
		}
	}

	public void termino() {
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

	public int obtenerClaveDeJugador(Jugador jugador) {
	    return jugadoresEnJuego.entrySet()
	        .stream()
	        .filter(entry -> entry.getValue().equals(jugador))
	        .map(Map.Entry::getKey)
	        .findFirst()
	        .orElse(-1);
	}
	
	public Jugador obtenerGanador() {
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
	
	public void cambiarTurno() {
		this.turnoActual = (this.turnoActual == 1) ? 2 : 1;
	}
	

	public void setPartidaTerminada(boolean partidaTerminada) {
		this.partidaTerminada = partidaTerminada;
		if (partidaTerminada) {
			   notificarObservers(EstadoPartida.PARTIDA_TERMINADA);
			} else {
			   notificarObservers(EstadoPartida.PARTIDA_EN_PROGRESO);
			}
	}

	// metodos de persistencia de jugadores
	
	public void verificarCredenciales(String nombre, String contrasenia) {
	    Jugador jugadorConectado = getJugadores().stream()
	            .filter(jugador -> jugador.getNombre().equals(nombre) && Encriptador.verificarContrasenia(contrasenia, jugador.getContrasenia()))
	            .findFirst()
	            .orElse(null);
	    
	    if(jugadorConectado == null) {
	    	notificarObservers(EstadoPersistencia.CREDENCIALES_INVALIDAS);
	    } else {
	    	// Si el jugador ingresó bien la contraseña y username, y no está conectada otra persona desde su cuenta,
	    	if (!jugadoresConectados.stream().anyMatch(jugador -> jugador.getNombre().equals(jugadorConectado.getNombre()))) {
	    		// lo guardamos en jugadores conectados y notificamos
	    		jugadoresConectados.add(jugadorConectado);
	    		notificarObservers(EstadoPersistencia.LOGEADO);
	    	} else {
	    		// notificamos intento de acceso concurrente
	    		notificarObservers(EstadoPersistencia.USUARIO_YA_CONECTADO);
	    	}	    	
	    }
	    
	}
	
	public void agregarJugador(String nombre, String contrasenia) {
		if(service.obtenerJugadorPorNombre(nombre) != null) {
			notificarObservers(EstadoPersistencia.NOMBRE_EXISTENTE);
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
			notificarObservers(EstadoPersistencia.GUARDADO_EXITOSO);			
		}
    }

	public void actualizarJugadores(Jugador jugador1, Jugador jugador2) {
		this.jugadores.forEach(jugador -> {
			if (jugador.getNombre().equals(jugador1.getNombre())) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador1);
			} else if (jugador.getNombre().equals(jugador2.getNombre())) {
				this.jugadores.set(jugadores.indexOf(jugador), jugador2);
			}
		});
		this.service.guardar(this.jugadores);
	}
	
	public List<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	public List<Jugador> getJugadoresConectados(){
		return this.jugadoresConectados;
	}
	
	// metodos del observer
	@Override
	public void agregarObservador(Observer observer) {
		this.observadores.add(observer);
	}
	
	@Override
	public void notificarObservers(Object informe) {
		this.observadores.forEach((observer) -> observer.update(this,informe));		
	}
	
	// getters y setters
		public TableroLectura getTablero() {
			return tablero;
		}

		public void setTablero(Tablero tablero) {
			this.tablero = tablero;
		}

		public MoveValidator getMoveValidator() {
			return moveValidator;
		}

		public void setMoveValidator(MoveValidator movValidator) {
			this.moveValidator = movValidator;
		}

		public Map<Integer, Jugador> getJugadoresEnJuego() {
			return jugadoresEnJuego;
		}

		public void setJugadoresEnJuego(Map<Integer, Jugador> jugadores) {
			this.jugadoresEnJuego = jugadores;
		}

		public int getTurnoActual() {
			return turnoActual;
		}

		public void setTurnoActual(int turnoActual) {
			this.turnoActual = turnoActual;
		}


		public boolean isPartidaTerminada() {
			return partidaTerminada;
		}
		
		public Jugador getUltimoEnMover() {
			return this.ultimoEnMover;
		}

		public void desconectar(Jugador jugador) {
			this.jugadoresConectados.remove(jugador);
		}

		public List<JugadorLectura> getTop(int limite) {
			return this.jugadores.stream()
					.map(j -> (JugadorLectura)j)
					.sorted(Comparator.comparing(JugadorLectura::getGanadas).reversed())
					.limit(limite)
					.collect(Collectors.toList());
		}
}
