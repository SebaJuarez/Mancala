package ar.edu.unlu.mancala.modelo;

import java.util.Collection;
import java.util.List;

public class LadoTablero {
	
	private List<Agujero> agujeros;
	private Jugador jugador;
	
	public void inicializar(int habas) {
		agujeros.stream().forEach(agujero -> {
			if(agujero instanceof Hoyo) {
				agujero.setHabas(habas);
			} else {
				agujero.setHabas(0);
			}
		});
	}
	
	public Hoyo HoyoOpuesto(LadoTablero ladoProviene, Hoyo hoyo) {
		int indiceProviene = ladoProviene.agujeros.indexOf(hoyo);
		int indiceAgujeroOpuesto = agujeros.size() - indiceProviene;
		return (Hoyo) this.agujeros.get(indiceAgujeroOpuesto);
	}
	
	public boolean ladoVacio() {
	    return agujeros.stream()
	            .allMatch(agujero -> {
	                if (agujero instanceof Hoyo) {
	                    return agujero.hayHaba();
	                } else {
	                    return true;
	                }
	            });
	}

	public void juntarEnCasa() {
		Casa casa = getCasa();
		agujeros.forEach(agujero -> {
			if(agujero instanceof Hoyo) {
				casa.setHabas(agujero.getHabas());
			}
		});
	}
	
	public boolean perteneceJugador(Jugador jugador) {
		return this.jugador.equals(jugador);
	}

	public Collection<Agujero> getAgujeros() {
		return agujeros;
	}

	public void setAgujeros(List<Agujero> agujeros) {
		this.agujeros = agujeros;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public Casa getCasa() {
		return (Casa) agujeros.stream()
				.filter(agujero -> agujero instanceof Casa)
				.findFirst()
				.orElse(null);
	}
}
