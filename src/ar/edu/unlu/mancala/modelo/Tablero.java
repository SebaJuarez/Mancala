package ar.edu.unlu.mancala.modelo;

import java.util.LinkedList;
import ar.edu.unlu.mancala.commons.Observado;
import ar.edu.unlu.mancala.commons.Observer;
import ar.edu.unlu.mancala.commons.TableroObservado;

public class Tablero implements TableroObservado {

	/*     
   concepto del tablero con sus respectivas posiciones

	ordinal            13   12   11  10    9    8
		               ^    ^    ^    ^    ^    ^
   alfabetica          L    K    J    I    H    G
				|   || 4 || 4 || 4 || 4 || 4 || 4 ||   |
			 0 >| 0 ||----------------------------|| 0 |< 7
				|   || 4 || 4 || 4 || 4 || 4 || 4 ||   |
   alfabetica  casaj2  A    B    C    D    E    F  casaj1
		               ^    ^    ^    ^    ^    ^
	ordinal            1    2    3    4    5    6         
	 */
	
	private int cantHabas;
	private Hoyo[] tablero = new Hoyo[14];
	private int numeroDeRonda = 0;
	private LinkedList<Observer> observadores = new LinkedList<Observer>();
	private int jugadorActual = 1;

	public Tablero(int cantHabas) {
		this.cantHabas = cantHabas;
		this.inicializarFichas();
	}

// metodo que inicializa el tablero para comenzar el juego
	public void inicializarFichas() {
		// recorro el tablero desde la casa1 hasta la posicion L
		for (int pos = Posicion.CASAJ2.ordinal(); pos <= Posicion.L.ordinal(); pos++) {
			this.tablero[pos] = new Hoyo(Posicion.getPosicionPorValor(pos));
			if (!(Posicion.compararPosicionConOrdinal(Posicion.CASAJ2, pos)
					|| (Posicion.compararPosicionConOrdinal(Posicion.CASAJ1, pos))))
				this.tablero[pos].ponerHaba(cantHabas);
		}
	}

// mover las habas segun la posicion indicada
	public Informe moverHabas(Posicion p, int jugadorActual) {
		// si el turno del jugador es diferente 1 o 2 entonces existe un error
		if (jugadorActual < 1 || jugadorActual > 2)
			return Informe.JUGADORFUERADEPARAMETRO;
		// si el jugador quiere mover una fila que no es suya
		if (!this.enRango(jugadorActual, p))
			return Informe.CASILLADEOTROJUGADOR;
		Posicion pos = p;
		// tomo las habas de la posicion y dejo en 0 la cuenca
		int habas = this.tablero[pos.ordinal()].tomarHabas();

		// si no hay habas en esa posicion entonces no se puede mover
		if (habas == 0)
			return Informe.NOHAYHABAS;

		// voy dejando una en cada posicion siguiente del tablero
		while (habas != 0) {
			pos = this.tablero[pos.ordinal()].siguienteHoyo();
			if (jugadorActual == 1 && pos == Posicion.CASAJ2) {
				pos = this.tablero[pos.ordinal()].siguienteHoyo();
			}
			if (jugadorActual == 2 && pos == Posicion.CASAJ1) {
				pos = this.tablero[pos.ordinal()].siguienteHoyo();
			}
			this.tablero[pos.ordinal()].ponerHaba();
			habas--;
		}

		// si la en la ultima posicion quedo una sola haba, entonces van a la casa del
		// jugador todas las del lado contrario + esa misma
		if (this.tablero[pos.ordinal()].getCantHabas() == 1) {
			if (jugadorActual == 1)
				if (this.enRango(jugadorActual, pos))
					this.tablero[Posicion.CASAJ1.ordinal()]
							.ponerHaba(this.tablero[Posicion.contrario(pos).ordinal()].tomarHabas()
									+ this.tablero[pos.ordinal()].tomarHabas());
				else if (jugadorActual == 2)
					if (this.enRango(jugadorActual, pos))
						this.tablero[Posicion.CASAJ2.ordinal()]
								.ponerHaba(this.tablero[Posicion.contrario(pos).ordinal()].tomarHabas()
										+ this.tablero[pos.ordinal()].tomarHabas());
		}

		// si llego hasta aca entonces se completo el turno
		this.incNumeroDeRonda();
		// si la ultima haba cae en la casa del jugador entones le corresponde jugar de
		// nuevo
		if (this.jugarDeNuevo(jugadorActual, pos))
			return Informe.JUEGADENUEVO;

		this.jugadorActual = siguienteJugador(this.jugadorActual);
		return Informe.SIGUIENTEJUGADOR;
	}

	private int siguienteJugador(int jugadorActual) {
		if (jugadorActual == 1)
			return 2;
		else
			return 1;
	}

	public boolean enRango(int jugador, Posicion pos) {
		return (jugador == 1) ? pos.ordinal() >= Posicion.A.ordinal() && pos.ordinal() <= Posicion.F.ordinal()
				: pos.ordinal() >= Posicion.G.ordinal() && pos.ordinal() <= Posicion.L.ordinal();
	}

	private boolean jugarDeNuevo(int jugador, Posicion pos) {
		return (jugador == 1) ? pos == Posicion.CASAJ1 : pos == Posicion.CASAJ2;
	}

	public Hoyo[] getTablero() {
		return this.tablero;
	}

	private void incNumeroDeRonda() {
		this.numeroDeRonda++;
	}

	public int getCantHabas() {
		return cantHabas;
	}

	public int getNumeroDeRonda() {
		return this.numeroDeRonda;
	}

	public int getJugadorActual() {
		return this.jugadorActual;
	}

	// MVC-Observer zone
	@Override
	public void agregarObservador(Observer observer) {
		this.observadores.add(observer);
	}

	@Override
	public void notificarObservers(Object informe) {
		this.observadores.forEach((observer) -> observer.update(this, informe));
	}



}
