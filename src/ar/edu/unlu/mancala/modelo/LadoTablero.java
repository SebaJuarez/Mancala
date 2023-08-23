package ar.edu.unlu.mancala.modelo;

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
	                    return !agujero.hayHaba();
	                } else {
	                    return true;
	                }
	            });
	}

	public void juntarEnCasa() {
		Casa casa = obtenerCasa();
		agujeros.forEach(agujero -> {
			if(agujero instanceof Hoyo) {
				casa.ponerHaba(((Hoyo) agujero).tomarHabas());
			}
		});
	}
	
	public boolean perteneceJugador(Jugador jugador) {
		return this.jugador.equals(jugador);
	}

	public List<Agujero> getAgujeros() {
		return agujeros;
	}

	public void setAgujeros(List<Agujero> agujeros) {
		this.agujeros = agujeros;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Hoyo obtenerHoyo(int indice){
		try {
			Hoyo hoyo = (Hoyo) agujeros.get(indice-1);
			return hoyo;
		} catch(Exception e) {
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
