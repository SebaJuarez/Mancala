package ar.edu.unlu.mancala.modelo;
import java.util.List;

public class TableroN {
	
	private List<LadoTablero> ladosDelTablero;
	
	public void agregarLado(LadoTablero ladoTablero) {
		ladosDelTablero.add(ladoTablero);
	}
	
	public LadoTablero ladoOpuesto(Hoyo hoyo) {
		LadoTablero ladoProviene = ladosDelTablero.stream()
				.filter(lado -> lado.getAgujeros().contains(hoyo))
				.findFirst()
				.orElse(null);

		int totalLados = ladosDelTablero.size();
		int indiceLadoProviene = ladosDelTablero.indexOf(ladoProviene);
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
		return getLado(jugador).getCasa();
	}
}
