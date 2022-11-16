package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

public class Partida implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Jugador j1;
	private Jugador j2;
	private int turno = 1;
	private Hoyo[] tablero;
	private int ultimoEstado;
	
	public Partida(Jugador j1, Jugador j2, Hoyo[] tablero) {
		this.j1 = j1;
		this.j2 = j2;
		this.tablero = tablero;
	}

	public Jugador getJ1() {
		return j1;
	}

	public Jugador getJ2() {
		return j2;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno ++;
	}

	public Hoyo[] getTablero() {
		return this.tablero;
	}

	
	public Jugador buscarGanador() {
		if( Informe.values()[this.ultimoEstado] != Informe.JUEGOFINALIZADO) return null;
		
		if(this.tablero[Posicion.CASAJ1.ordinal()].getCantHabas() > this.tablero[Posicion.CASAJ2.ordinal()].getCantHabas() ) {
			this.j1.incPartidasGanadas();
			return this.j1;
		}
		else if(this.tablero[Posicion.CASAJ1.ordinal()].getCantHabas() < this.tablero[Posicion.CASAJ2.ordinal()].getCantHabas()) {
			this.j2.incPartidasGanadas();
			return this.j2;			
		}
		else {
			this.j1.incPartidasEmpatadas();
			this.j2.incPartidasEmpatadas();
			return null;
		}
	}
	
	public Jugador jugadorOfValue(int turno) {
		return (turno == 1)? j1 : j2;
	}
	
	public void turnoSiguienteJugador(int jugadorActual) {
		this.turno = (jugadorActual == 1)? 2 : 1;
	}

	public Informe getUltimoEstado() {
		return Informe.values()[this.ultimoEstado];
	}

	public void setUltimoEstado(Informe ultimoEstado) {
		this.ultimoEstado = ultimoEstado.ordinal();
	}
	
}
