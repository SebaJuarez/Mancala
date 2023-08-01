package ar.edu.unlu.mancala.vista.consola;

public enum OpcionesInicioSesion implements Banner{
	
	NULO(""),
	INICIAR_SESION("1) Iniciar sesion"),
	CREAR_CUENTA("2) Crear cuenta"),
	SALIR("3) Salir");
	
	public final String label;

	private OpcionesInicioSesion(String label) {
		this.label = label;
	}
	
	public static String valueOf(int opcion) {
		return OpcionesInicioSesion.values()[opcion].label;
	}
	
	public static String mostrarOpcionesInicioSesion() {
		String menu = "";
		menu = LOGO + "\n";
		menu = menu + "#   *                           *         *                *  # \n";
		menu = menu + "#      *           " + valueOf(1) + "         *   *     *      # \n";
		menu = menu + "#   *          *   " + valueOf(2) + "     *                 *    # \n";
		menu = menu + "#     *            " + valueOf(3) + "                     *          *  #\n";
		menu = menu + "#   *               *          *    *              *          # \n";
		menu = menu + "############################################################### \n";
		return menu;
	}
	
}
