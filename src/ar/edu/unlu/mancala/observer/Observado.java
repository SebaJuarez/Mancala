package ar.edu.unlu.mancala.observer;

public interface Observado {
	void agregarObservador(Observer o);

	void notificarObservers(Object informe);
}
