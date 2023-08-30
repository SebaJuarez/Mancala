package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;
import java.util.List;

public class LadoTablero implements LadoTableroLectura, Serializable {

	private static final long serialVersionUID = 1L;
	private List<Agujero> agujeros;
	private Jugador jugador;

	public void inicializar(int habas) {
		agujeros.stream().forEach(agujero -> {
			if (agujero instanceof Hoyo) {
				agujero.setHabas(habas);
			} else {
				agujero.setHabas(0);
			}
		});
	}

	public Agujero HoyoOpuesto(LadoTablero ladoProviene, Hoyo hoyo) {
		int indiceProviene = ladoProviene.agujeros.indexOf(hoyo);
		// primer -1 por el size , segundo -1 por el desplazamiento del tablero
		int indiceAgujeroOpuesto = agujeros.size() - 1 - indiceProviene - 1;
		return indiceAgujeroOpuesto >= 0 ? agujeros.get(indiceAgujeroOpuesto) : agujeros.get(agujeros.size() - 1);
	}

	public boolean ladoVacio() {
		return agujeros.stream().allMatch(agujero -> {
			if (agujero instanceof Hoyo) {
				return !((Hoyo) agujero).hayHaba();
			} else {
				return true;
			}
		});
	}

	public void juntarEnCasa() {
		Casa casa = obtenerCasa();
		agujeros.forEach(agujero -> {
			if (agujero instanceof Hoyo) {
				casa.ponerHaba(((Hoyo) agujero).tomarHabas());
			}
		});
	}

	public boolean perteneceJugador(Jugador jugador) {
		return this.jugador.equals(jugador);
	}

	@Override
	public List<Agujero> getAgujeros() {
		return agujeros;
	}

	public void setAgujeros(List<Agujero> agujeros) {
		this.agujeros = agujeros;
	}

	@Override
	public Jugador getJugador() {
		return jugador;
	}

	public Hoyo obtenerHoyo(int indice) {
		try {
			Hoyo hoyo = (Hoyo) agujeros.get(indice - 1);
			return hoyo;
		} catch (Exception e) {
			return null;
		}
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Casa obtenerCasa() {
		return (Casa) agujeros.stream()
				.filter(agujero -> agujero instanceof Casa)
				.findFirst()
				.orElse(null);
	}
}
