package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unlu.mancala.vista.TableroLectura;

public class Tablero implements TableroLectura, Serializable {

	private static final long serialVersionUID = 1L;
	private List<LadoTablero> ladosDelTablero;

	public Tablero() {
		ladosDelTablero = new LinkedList<>();
	}

	public void agregarLado(LadoTablero ladoTablero) {
		ladosDelTablero.add(ladoTablero);
	}

	public LadoTablero ladoOpuesto(Hoyo hoyo) {
		LadoTablero ladoProviene = ladosDelTablero.stream().filter(lado -> lado.perteneceAgujero(hoyo)).findFirst()
				.orElse(null);

		int totalLados = ladosDelTablero.size();
		int indiceLadoProviene = ladosDelTablero.indexOf(ladoProviene) + 1;

		// (i + n/2) % n i = lado; n = total lados

		int indiceLadoOpuesto = ((indiceLadoProviene + (totalLados / 2)) % totalLados) - 1;

		return indiceLadoOpuesto >= 0 ? ladosDelTablero.get(indiceLadoOpuesto) : ladosDelTablero.get(totalLados - 1);
	}

	public boolean ladoVacio() {
		return ladosDelTablero.stream().anyMatch(lado -> lado.ladoVacio());
	}

	public LadoTablero getLado(Jugador jugador) {
		return ladosDelTablero.stream().filter(lado -> lado.perteneceJugador(jugador)).findFirst().orElse(null);
	}

	public void inicializarLados(int habas) {
		ladosDelTablero.forEach(lado -> lado.inicializar(habas));
	}

	public void asignarJugadorAlLado(Jugador jugador) {
		ladosDelTablero.stream().filter(lado -> !lado.tieneJugador()).findFirst().orElse(null).setJugador(jugador);
	}

	public Casa getCasaDeJugador(Jugador jugador) {
		return getLado(jugador).obtenerCasa();
	}

	public Hoyo getHoyo(Jugador jugador, int indice) {
		return getLado(jugador).obtenerHoyo(indice);
	}

	public List<LadoTablero> getLadosDelTablero() {
		return ladosDelTablero;
	}

	public void juntarHabasLadoNoVacio() {
		ladosDelTablero.stream().filter(lado -> !lado.ladoVacio()).collect(Collectors.toList())
				.forEach(lado -> lado.juntarEnCasa());
	}

	public void tomarHabasOpuestas(Hoyo hoyo, Jugador jugadorMueve) {
		Agujero hoyoOpuesto = hoyoOpuesto(hoyo, jugadorMueve);
		getCasaDeJugador(jugadorMueve).ponerHaba(((Hoyo) hoyoOpuesto).tomarHabas());
	}

	public Agujero hoyoOpuesto(Hoyo hoyo, Jugador jugadorMueve) {
		return ladoOpuesto(hoyo).HoyoOpuesto(getLado(jugadorMueve), hoyo);
	}

}
