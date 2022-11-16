package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import ar.edu.unlu.mancala.commons.*;

public class Tablero implements TableroObservado, Serializable {

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

	private static final long serialVersionUID = 1L;
	private final int cantHabas = 4;
	private Hoyo[] tablero = new Hoyo[14];
	private int numeroDeRonda;
	private LinkedList<Observer> observadores = new LinkedList<Observer>();

	public Tablero() {
		this.inicializarFichas();
	}

// metodo que inicializa el tablero para comenzar el juego
	public void inicializarFichas() {
		// recorro el tablero desde la casa1 hasta la posicion L
		for (int pos = Posicion.CASAJ2.ordinal(); pos <= Posicion.L.ordinal(); pos++) {
			this.tablero[pos] = new Hoyo(Posicion.getPosicionPorValor(pos));
			if (!(Posicion.compararPosicionConOrdinal(Posicion.CASAJ2, pos) || (Posicion.compararPosicionConOrdinal(Posicion.CASAJ1, pos))))
				this.tablero[pos].ponerHaba(cantHabas);
		}
		this.numeroDeRonda = 0;
		this.notificarObservers(Informe.LISTOPARACOMENZAR);
	}

// mover las habas segun la posicion indicada
	public void moverHabas(Posicion p, int jugadorActual) {
		// si el jugador quiere mover una fila que no es suya
		if (!this.enRango(jugadorActual, p)) {
			this.notificarObservers(Informe.CASILLADEOTROJUGADOR);
		}
		Posicion pos = p;
		// tomo las habas de la posicion y dejo en 0 el hoyo
		int habas = this.tablero[pos.ordinal()].tomarHabas();

		// si no hay habas en esa posicion entonces no se puede mover
		if (habas == 0) 
			this.notificarObservers(Informe.NOHAYHABAS);

		// voy dejando una en cada posicion siguiente del tablero
		while (habas != 0) {
			pos = this.tablero[pos.ordinal()].siguienteHoyo();
			if (jugadorActual == 1 && pos == Posicion.CASAJ2)
				pos = this.tablero[pos.ordinal()].siguienteHoyo();
			if (jugadorActual == 2 && pos == Posicion.CASAJ1) 
				pos = this.tablero[pos.ordinal()].siguienteHoyo();
			this.tablero[pos.ordinal()].ponerHaba();
			habas--;
		}
		// si la en la ultima posicion quedo una sola haba, entonces van a la casa del
		// jugador todas las del lado contrario
		if (tomarHabasOpuestas(pos, jugadorActual)) {
			if (jugadorActual == 1)
				tablero[Posicion.CASAJ1.ordinal()].ponerHaba(this.tablero[Posicion.contrario(pos).ordinal()].tomarHabas());
			else
				tablero[Posicion.CASAJ2.ordinal()].ponerHaba(this.tablero[Posicion.contrario(pos).ordinal()].tomarHabas());			
		}
	
		// si llego hasta aca entonces se completo el turno
		this.incNumeroDeRonda();
		// si la ultima haba cae en la casa del jugador entones le corresponde jugar de nuevo
		if (jugarDeNuevo(jugadorActual, pos)) {
			this.notificarObservers(Informe.JUEGADENUEVO);
		}
		this.notificarObservers(Informe.SIGUIENTEJUGADOR);
	}

	public boolean enRango(int jugador, Posicion pos) {
		if(jugador == 1) 
			return (pos.ordinal() >= Posicion.A.ordinal() && pos.ordinal() <= Posicion.F.ordinal());
		else
			return (pos.ordinal() >= Posicion.G.ordinal() && pos.ordinal() <= Posicion.L.ordinal());
	}

	private boolean tomarHabasOpuestas(Posicion pos , int jugadorActual){
		return ((this.tablero[pos.ordinal()].getCantHabas() == 1 && this.tablero[Posicion.contrario(pos).ordinal()].getCantHabas() > 0) && enRango(jugadorActual, pos));
	}
	
	private boolean jugarDeNuevo(int jugador, Posicion pos) {
		return (jugador == 1) ? pos == Posicion.CASAJ1 : pos == Posicion.CASAJ2;
	}

	public Hoyo[] getTablero() {
		return this.tablero;
	}

	public void incNumeroDeRonda() {
		this.numeroDeRonda++;
	}

	public int getCantHabas() {
		return cantHabas;
	}

	public int getNumeroDeRonda() {
		return this.numeroDeRonda;
	}

	public void evaluarCondicion() {
		if (!jugador1PuedeMover() || !jugador2PuedeMover()) {
			this.juntarHabasSobrantes();
			this.notificarObservers(Informe.JUEGOFINALIZADO);
		}
	}

	private boolean jugador1PuedeMover(){
		for (int i = Posicion.A.ordinal(); i <= Posicion.F.ordinal(); i++) {
			if (tablero[i].getCantHabas() > 0)
				return true;
		}
		return false;
	}
	
	private boolean jugador2PuedeMover(){
		for (int i = Posicion.G.ordinal(); i <= Posicion.L.ordinal(); i++) {
			if (tablero[i].getCantHabas() > 0)
				return true;
		}
		return false;
	}
	
	private void juntarHabasSobrantes() {
		for (int pos = Posicion.CASAJ2.ordinal(); pos <= Posicion.L.ordinal(); pos++) {
			if(enRango(1,Posicion.getPosicionPorValor(pos)))
				tablero[Posicion.CASAJ1.ordinal()].ponerHaba(tablero[pos].tomarHabas());
			else if (enRango(2,Posicion.getPosicionPorValor(pos)))
				tablero[Posicion.CASAJ2.ordinal()].ponerHaba(tablero[pos].tomarHabas());
		}
	}
	
	public void setTablero(Hoyo[] tablero) {
		this.tablero = tablero;
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
