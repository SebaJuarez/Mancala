package ar.edu.unlu.mancala.commons;

public interface Observado {
	void agregarObservador(Observer o);
	void notificarObservers(Object informe);
}
