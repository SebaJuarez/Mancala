package ar.edu.unlu.mancala.commons;

public interface Observado {
	//void notificarObservers();
	void agregarObservador(Observer o);
	void notificarObservers(Object informe);
}
