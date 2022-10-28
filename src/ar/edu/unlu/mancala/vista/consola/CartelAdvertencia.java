package ar.edu.unlu.mancala.vista.consola;

import ar.edu.unlu.mancala.commons.Colores;

public enum CartelAdvertencia implements Colores {
	ERROR (ROJO+FONDONEGRO),
    ADVERTENCIA (AMARILLO+FONDONEGRO),
    COMPLETO (VERDE+FONDONEGRO),
	NORMAL (BLANCO+FONDONEGRO);
    
    public final String color;
	
	private CartelAdvertencia(String color) {
        this.color = color;
    }
	
	public static void mostrarMensaje(String mensaje , CartelAdvertencia advertencia) {
		System.out.println(advertencia.color + mensaje + RESET);
	}
}
