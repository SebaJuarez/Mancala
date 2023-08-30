package ar.edu.unlu.mancala.vista;

public interface JugadorLectura {

	String getNombre();

	int getPerdidas();

	int getEmpatadas();

	int getGanadas();

	double winRate();

	double loseRate();

	double drawnRate();
}
