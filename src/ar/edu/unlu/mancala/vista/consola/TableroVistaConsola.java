package ar.edu.unlu.mancala.vista.consola;

import java.util.List;

import ar.edu.unlu.mancala.modelo.Agujero;
import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.TipoPartida;

public class TableroVistaConsola {

	public String mostrarTablero(List<LadoTablero> ladosTablero, TipoPartida tipoPartida) {
		String tablero = "";
		switch (tipoPartida) {
		case PARTIDA_STANDAR:
			tablero = mostrarTableroStandar(ladosTablero);
			break;
		case PARTIDA_4_S_AHORARIO:
			tablero = mostrarTablero4Lados(ladosTablero);
			break;
		case PARTIDA_MODO_CAPTURA:
			tablero = mostrarTableroStandar(ladosTablero);
		}

		return tablero;
	}

	private String mostrarTableroStandar(List<LadoTablero> ladosTablero) {
		String tablero = "************************************************\n";
		tablero += "*              <<   TABLERO   >>               *\n";
		tablero += "*----------------------------------------------*\n";
		tablero += "*              ZONA DE : " + ladosTablero.get(1).getJugador().getNombre() + "\n";
		tablero += "*                                              *\n";
		tablero += "*   |   |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(5).getHabas() + " |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(4).getHabas() + " |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(3).getHabas() + " |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(2).getHabas() + " |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(1).getHabas() + " |";
		tablero += "| " + ladosTablero.get(1).getAgujeros().get(0).getHabas() + " |";
		tablero += "|   |   *";
		tablero += "\n";
		tablero += "*   | " + ladosTablero.get(1).getAgujeros().get(6).getHabas() + " ||";
		tablero += "----------------------------";
		tablero += "|| " + ladosTablero.get(0).getAgujeros().get(6).getHabas() + " |   *";
		tablero += "\n";
		tablero += "*   |   |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(0).getHabas() + " |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(1).getHabas() + " |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(2).getHabas() + " |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(3).getHabas() + " |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(4).getHabas() + " |";
		tablero += "| " + ladosTablero.get(0).getAgujeros().get(5).getHabas() + " |";
		tablero += "|   |   *";
		tablero += "\n";
		tablero += "*          ↑    ↑    ↑    ↑    ↑    ↑          *\n";
		tablero += "*          1    2    3    4    5    6          *\n";
		tablero += "*        (ingrese el indice para mover)        *\n";
		tablero += "*              ZONA DE : " + ladosTablero.get(0).getJugador().getNombre() + "\n";
		tablero += "************************************************";
		return tablero;
	}
	
	private static String mostrarTablero4Lados(List<LadoTablero> ladosTablero) {
	    String tablero = "";
	    List<Agujero> lado1A = ladosTablero.get(0).getAgujeros();
	    List<Agujero> lado2A = ladosTablero.get(1).getAgujeros();
	    List<Agujero> lado3A = ladosTablero.get(2).getAgujeros();
	    List<Agujero> lado4A = ladosTablero.get(3).getAgujeros();
	    
	    tablero = "************************************************\r\n"
	            + "*              <<   TABLERO   >>               *\r\n"
	            + "*----------------------------------------------*\r\n"
	            + "* Lado 3:" + ladosTablero.get(2).getJugador().getNombre()
	            + "       Lado 4:" + ladosTablero.get(3).getJugador().getNombre()+ "\n"
	            + "*       ________________________________       *\r\n"
	            + "*      |             LADO 3             |      *\r\n"
	            + "*      | L " 
	            + "| "+lado3A.get(3).getHabas()+" || "+lado3A.get(2).getHabas()+" || "+lado3A.get(1).getHabas()+" || "+lado3A.get(0).getHabas()+" |"
	            + "| "+lado2A.get(3).getHabas()+" |" + "| L |      *\r\n"
	            + "*      | A " + "| "+lado4A.get(0).getHabas()+" |" + "               " 
	            + "| "+lado2A.get(2).getHabas()+" |" + "| A |      *\r\n"
	            + "*      | D " + "| "+lado4A.get(1).getHabas()+" |" + "               " 
	            + "| "+lado2A.get(1).getHabas()+" |" + "| D |      *\r\n"
	            + "*      | O " + "| "+lado4A.get(2).getHabas()+" |" + "               " 
	            + "| "+lado2A.get(0).getHabas()+" |" + "| 0 |      *\r\n"
	            + "*      | 4 " + "| "+lado4A.get(3).getHabas()+" |" + 
	            "| "+lado1A.get(0).getHabas()+" || "+lado1A.get(1).getHabas()+" || "+lado1A.get(2).getHabas()+" || "+lado1A.get(3).getHabas()+" |" + "| 2 |      *\r\n"
	            + "*      |          ↑    ↑    ↑           |      *\r\n"
	            + "*      |__________1____2____3___________|      *\r\n"
	            + "*                   LADO 1                     *\r\n"
	            + "*        (ingrese el indice para mover)        *\r\n"
	            + "*  Lado 1: " + ladosTablero.get(0).getJugador().getNombre()
	            + "     Lado 2:" + ladosTablero.get(1).getJugador().getNombre()+ "\n"
	            + "************************************************";

	    return tablero;
	}

}
