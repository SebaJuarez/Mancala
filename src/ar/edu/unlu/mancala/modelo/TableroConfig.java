package ar.edu.unlu.mancala.modelo;

import java.io.Serializable;

public class TableroConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cantHabasIniciales;
	private int cantLados;
	private int cantAgujerosPorLado;
	private int posCasaPorLado;

	public int getCantHabasIniciales() {
		return cantHabasIniciales;
	}

	public void setCantHabasIniciales(int cantHabasIniciales) {
		this.cantHabasIniciales = cantHabasIniciales;
	}

	public int getCantLados() {
		return cantLados;
	}

	public void setCantLados(int cantLados) {
		this.cantLados = cantLados;
	}

	public int getCantAgujerosPorLado() {
		return cantAgujerosPorLado;
	}

	public void setCantAgujerosPorLado(int cantAgujerosPorLado) {
		this.cantAgujerosPorLado = cantAgujerosPorLado;
	}

	public int getPosCasaPorLado() {
		return posCasaPorLado;
	}

	public void setPosCasaPorLado(int posCasaPorLado) {
		this.posCasaPorLado = posCasaPorLado;
	}
}
