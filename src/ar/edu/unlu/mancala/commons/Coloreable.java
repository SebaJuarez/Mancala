package ar.edu.unlu.mancala.commons;

public interface Coloreable {
	static final String NEGRO = "\033[30m";
	static final String ROJO = "\033[31m";
	static final String VERDE = "\033[32m";
	static final String AMARILLO = "\033[33m";
	static final String AZUL = "\033[34m";
	static final String VIOLETA = "\033[35m";
	static final String BLANCO = "\033[37m";
	static final String FONDONEGRO = "\u001B[40m";
	static final String FONDOROJO = "\u001B[41m";
	static final String FONDOVERDE = "\u001B[42m";
	static final String FONDOAMARILLO = "\u001B[43m";
	static final String FONDOAZUL = "\u001B[44m";
	static final String FONDOVIOLETA = "\u001B[45m";
	static final String FONDOBLANCO = "\u001B[47m";
	static final String RESET = "\u001B[0m";
}
