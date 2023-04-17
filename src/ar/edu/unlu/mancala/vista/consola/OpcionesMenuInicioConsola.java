package ar.edu.unlu.mancala.vista.consola;

import javax.swing.JTextArea;

public enum OpcionesMenuInicioConsola {
	NULO(0,""),
    REGISTRAR_JUGADOR (1,"1) Registrar jugador nuevo"),
    COMENZAR_PARTIDA (2,"2) Comenzar partida nueva"),
    CONTINUAR_PARTIDA (3,"3) Continuar partida"),
    LISTAR_JUGADORES (4,"4) Lista de jugadores"),
    TOP_GANADORES (5,"5) Top ganadores"),
    SALIR (6,"6) Salir");

    public final String label;
    private int valor;

    private OpcionesMenuInicioConsola(int i, String label) {
        this.label = label;
        this.valor = i;
    }
    
    public static OpcionesMenuInicioConsola getOpcion(int valor) {
        for (OpcionesMenuInicioConsola opcion : OpcionesMenuInicioConsola.values()) {
            if (opcion.valor == valor) {
                return opcion;
            }
        }
        return OpcionesMenuInicioConsola.NULO;
    }

    public static String valueOf(int opcion) {
        return OpcionesMenuInicioConsola.values()[opcion].label;
    }

    
    public static void mostrarOpcionesMenuInicio(JTextArea pantalla) {
    	
    	pantalla.append("$ \n");
    	pantalla.append("$ #   *                           *         *                *  #\n");
    	pantalla.append("$ #      *            "+valueOf(1)+"                #\n");
    	pantalla.append("$ #   *          *    "+valueOf(2)+"     *           #\n");
    	pantalla.append("$ #            *      "+valueOf(3)+"                      #\n");
    	pantalla.append("$ #            *      "+valueOf(4)+"                     #\n");
    	pantalla.append("$ #            *      "+valueOf(5)+"                          #\n");
    	pantalla.append("$ #     *             "+valueOf(6)+"                     *          * #\n");
    	pantalla.append("$ #   *               *          *    *              *          #\n");
    	pantalla.append("$ ############################################################### \n");
    }
    
    
}