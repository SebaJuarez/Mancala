package ar.edu.unlu.mancala.vista;

import java.util.List;

import ar.edu.unlu.mancala.modelo.Agujero;

public interface TableroLectura {

	String toString(List<JugadorLectura> jugadores);
    Agujero[] getAgujeros();
}