package ar.edu.unlu.mancala.modelo;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlu.mancala.vista.TableroLectura;

public class Tablero implements TableroLectura,Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<LadoTablero> ladosDelTablero;
	
	public Tablero() {
		ladosDelTablero = new LinkedList<>();
	}

	public void agregarLado(LadoTablero ladoTablero) {
		ladosDelTablero.add(ladoTablero);
	}
	
	public LadoTablero ladoOpuesto(Hoyo hoyo) {
		LadoTableroLectura ladoProviene = ladosDelTablero.stream()
				.filter(lado -> lado.getAgujeros().contains(hoyo))
				.findFirst()
				.orElse(null);

		int totalLados = ladosDelTablero.size();
		int indiceLadoProviene = ladosDelTablero.indexOf(ladoProviene);
		// todos los tableros deben ser par, ya que no tendrian opuesto de otra forma
		int distanciaHaciaOpuesto = totalLados / 2;
		int indiceLadoOpuesto = (indiceLadoProviene + distanciaHaciaOpuesto) % totalLados;
		return ladosDelTablero.get(indiceLadoOpuesto);
	}
	
	public boolean ladoVacio() {
		return ladosDelTablero.stream()
				.anyMatch(lado -> lado.ladoVacio());
	}
	
	public LadoTablero getLado(Jugador jugador) {
		return ladosDelTablero.stream()
				.filter(lado -> lado.getJugador().equals(jugador))
				.findFirst()
				.orElse(null);
	}
	
	public void inicializarLados(int habas) {
		ladosDelTablero.forEach(lado -> lado.inicializar(habas));
	}
	
	public void asignarJugadorAlLado(Jugador jugador) {
		ladosDelTablero.stream()
		.filter(lado -> lado.getJugador() == null)
		.findFirst()
		.orElse(null)
		.setJugador(jugador);
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
	
	public void juntarHabasLadoVacio() {
		ladosDelTablero.stream()
		.filter(lado -> lado.ladoVacio())
		.findFirst()
		.orElse(null)
		.juntarEnCasa();
	}
}
