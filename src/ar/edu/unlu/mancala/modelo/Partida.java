package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import ar.edu.unlu.mancala.modelo.estados.movimiento.EstadoMovimiento;
import ar.edu.unlu.mancala.modelo.estados.partida.EstadoPartida;

public class Partida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean partidaTerminada = true;
	private Queue<Jugador> jugadores;
	private Jugador turnoActual;
	private int participantesLimite;
	private Movimiento estrategiaMovimiento ;
	private TableroBuilder constructorTablero;
	private Tablero tablero;
	private TipoPartida tipoPartida;
	
	public Partida(TipoPartida tipoPartida) {
		this.tipoPartida = tipoPartida;
		partidaTerminada = false;
		constructorTablero = new TableroBuilder();
		crearTablero(tipoPartida);
	}
	
	private void crearTablero(TipoPartida tipoPartida) {
		TableroConfig tableroConfig = new TableroConfig();
		switch(tipoPartida) {
		case PARTIDA_STANDAR : {
			this.participantesLimite = 2;
			tableroConfig.setCantLados(participantesLimite);
			tableroConfig.setCantAgujerosPorLado(7);
			tableroConfig.setCantHabasIniciales(4);
			tableroConfig.setPosCasaPorLado(7);
			estrategiaMovimiento = new MovimientoStandar();
			//estrategiaMovimiento = new MovimientoSentidoHorario();
			break;
		}
		case PARTIDA_4v4_HORARIO : {
			this.participantesLimite = 4;
			tableroConfig.setCantLados(participantesLimite);
			tableroConfig.setCantAgujerosPorLado(5);
			tableroConfig.setCantHabasIniciales(2);
			tableroConfig.setPosCasaPorLado(1);
			estrategiaMovimiento = new MovimientoSentidoHorario();
			//estrategiaMovimiento = new MovimientoStandar();
			break;
		}
		}
		constructorTablero.setConfiguracionTablero(tableroConfig);
		tablero = constructorTablero.build();
		jugadores = new LinkedList<Jugador>();
	}
	
	public EstadoPartida agregarJugador(Jugador jugador) {
		if(jugadores.size() < participantesLimite) {
			jugadores.add(jugador);
			tablero.asignarJugadorAlLado(jugador);
			return EstadoPartida.USUARIO_CONECTADO;
		} else {
			return EstadoPartida.PARTIDA_LLENA;
		}
	}
	
	public EstadoPartida listoParaComezar(){
		if(jugadores.size() == participantesLimite) {
			asignarTurno();
			return EstadoPartida.COMENZANDO_PARTIDA;
		} else {
			return EstadoPartida.ESPERANDO_USUARIO;
		}
	}
	
	private void asignarTurno() {
		var jugadoresMezclados = new LinkedList<Jugador>(jugadores);
		Collections.shuffle(jugadoresMezclados);
		jugadores.clear();
		jugadores.addAll(jugadoresMezclados);
		turnoActual = jugadores.poll();
		jugadores.add(turnoActual);
	}

	public EstadoMovimiento mover(Jugador jugador, int indice) {
		if(!jugador.equals(turnoActual)){
			return EstadoMovimiento.TURNO_INVALIDO;
		}
		Hoyo hoyo = tablero.getHoyo(jugador, indice);
		if(hoyo == null) {
			return EstadoMovimiento.MOVIMIENTO_INVALIDO_POSICION;
		} else if (!hoyo.hayHaba()) {
			return EstadoMovimiento.MOVIMIENTO_INVALIDO_HABAS;
		}
		EstadoMovimiento estadoMovimiento = distribuirHabas(hoyo,jugador);
		if (EstadoMovimiento.MOVIMIENTO_VALIDO_SIGUE != estadoMovimiento) {
			this.cambiarTurno();
		}
		return estadoMovimiento;
	}
			
	
	private EstadoMovimiento distribuirHabas(Hoyo hoyo, Jugador jugador) {
		return estrategiaMovimiento.distribuirHabas(tablero,hoyo,jugador);
	}

	public List<Jugador> obtenerGanador() {
		
		var jugadorPuntos = new HashMap<Jugador,Integer>();
		
		jugadores.forEach(jugadorn -> {
			jugadorPuntos.put(jugadorn, tablero.getCasaDeJugador(jugadorn).getHabas());
		});
		
	    int maxPuntos = jugadorPuntos.values().stream()
	            .max((p1,p2) -> p1.compareTo(p2))
	            .orElse(0);

	    // voy a obtener una lista de los jugadores que tengan el mayor puntaje
	    // en caso de ser partida = 2 jugadores, entonces habra sido un empate entre ellos
	    // en caso de ser partida > 2 jugadores, entonces el que no está en la lista perdio y los que estan empataron
	    // en cualquier caso si solo hay 1 entonces ganó
	    return jugadorPuntos.entrySet().stream()
	            .filter(entry -> entry.getValue() == maxPuntos)
	            .map(Map.Entry::getKey)
	            .collect(Collectors.toList());
	}
	
	public EstadoPartida termino() {
		if(tablero.ladoVacio()) {
			this.partidaTerminada = true;
			tablero.juntarHabasLadoVacio();
			return EstadoPartida.PARTIDA_TERMINADA;
		} else {
			return EstadoPartida.PARTIDA_EN_PROGRESO;
		}
	}
	
	private void cambiarTurno() {
		turnoActual = jugadores.poll();
		jugadores.add(turnoActual);
	}
	
	public EstadoPartida desconectarJugador(Jugador jugadorDesconectado){
		jugadores.remove(jugadorDesconectado);
		return EstadoPartida.USUARIO_DESCONECTADO;
	}

	public boolean isPartidaTerminada() {
		return partidaTerminada;
	}
	
	public List<Jugador> getJugadores() {
		return List.copyOf(jugadores);
	}

	public void setPartidaTerminada(boolean partidaTerminada) {
		this.partidaTerminada = partidaTerminada;
	}
	
	public int getParticipantesLimite() {
		return participantesLimite;
	}
	
	public Jugador getTurnoActual() {
		return turnoActual;
	}

	public TipoPartida getTipoPartida() {
		return tipoPartida;
	}

	public Tablero getTablero() {
		return this.tablero;
	}

}
