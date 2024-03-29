package ar.edu.unlu.mancala.vista.consola;

public interface Banner {

	final String LOGO = "###############################################################\n"
			+ "#   #     #    #    #     #  #####     #    #          #      #\n"
			+ "#   ##   ##   # #   ##    # #     #   # #   #         # #     #\n"
			+ "#   # # # #  #   #  # #   # #        #   #  #        #   #    #\n"
			+ "#   #  #  # #     # #  #  # #       #     # #       #     #   #\n"
			+ "#   #     # ####### #   # # #       ####### #       #######   #\n"
			+ "#   #     # #     # #    ## #     # #     # #       #     #   #\n"
			+ "#   #     # #     # #     #  #####  #     # ####### #     #   #\n"
			+ "#                                                             #";

	final String DESPEDIDA = "###############################################################\n"
			+ "#                     *                           *           #\n"
			+ "#        *                    *                               #\n"
			+ "#                                        *                *   #\n"
			+ "#    #     #    ######  ### #######  #####   #       ##       #\n"
			+ "#         # #   #     #  #  #     # #     #  #   ###   #   *  #\n"
			+ "#    #   #   #  #     #  #  #     # #        #   ###    #     #\n"
			+ "#    #  #     # #     #  #  #     #  #####   #          #     #\n"
			+ "#    #  ####### #     #  #  #     #       #  #   ###    #     #\n"
			+ "#    #  #     # #     #  #  #     # #     #      ###   #      #\n"
			+ "#    #  #     # ######  ### #######  #####   #    #  ##       #\n"
			+ "#                                                #        *   #\n"
			+ "#          *           *                                      #\n"
			+ "#  *                                *                 *       #\n"
			+ "###############################################################";

	final String jugadorWin = "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║       ¡GANASTE!        ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################";
	final String jugadorLose = "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║      ¡PERDISTE!        ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################";

	final String empate = "###############################################################\n"
			+ "#                              (0 0)                          #\r\n"
			+ "#--------------------╔════oOO═══(_)════oOO════╗---------------#\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#                    ║      ¡EMPATASTE!       ║               #\r\n"
			+ "#                    ║                        ║               #\r\n"
			+ "#--------------------╚════════════════════════╝---------------#\r\n"
			+ "#                             |__|__|                         #\r\n"
			+ "#                              || ||                          #\r\n"
			+ "#                             ooO Ooo                         #\n"
			+ "###############################################################";

	final String reglas = "###############################################################\n"
			+ "#                                                             #\n"
			+ "#       ######  #######  #####  #          #     #####        #\r\n"
			+ "#       #     # #       #     # #         # #   #     #       #\r\n"
			+ "#       #     # #       #       #        #   #  #             #\r\n"
			+ "#       ######  #####   #  #### #       #     #  #####        #\r\n"
			+ "#       #   #   #       #     # #       #######       #       #\r\n"
			+ "#       #    #  #       #     # #       #     # #     #       #\r\n"
			+ "#       #     # #######  #####  ####### #     #  #####        #\n"
			+ "#                                                             #";

	final String esperandoJugador = "###############################################################\n"
			+ "#                        sala de espera                       #\r\n"
			+ "#                                                             #\r\n"
			+ "#                       ESPERANDO JUGADOR                     #\r\n"
			+ "#                                                             #\r\n"
			+ "#                 [ ████████████████/////// ]                 #\r\n"
			+ "#                                                             #\r\n"
			+ "#          ¡Pon a prueba tus habilidades en Mancala!          #\r\n"
			+ "#                                                             #\r\n"
			+ "###############################################################";

	final String TOPTEN = "###############################################################\n"
			+ "#      ########  #######  ########        ##     #####        #\r\n"
			+ "#         ##    ##     ## ##     ##     ####    ##   ##       #\r\n"
			+ "#         ##    ##     ## ##     ##       ##   ##     ##      #\r\n"
			+ "#         ##    ##     ## ########        ##   ##     ##      #\r\n"
			+ "#         ##    ##     ## ##              ##   ##     ##      #\r\n"
			+ "#         ##    ##     ## ##              ##    ##   ##       #\r\n"
			+ "#         ##     #######  ##            ######   #####        #\r\n"
			+ "###############################################################\n";

	final String ESTADISTICA = "###############################################################\r\n"
			+ "# ####  ####  ###   #   ####  #  ###  ### #  ###    #    ###  #\r\n"
			+ "# #    #    #  #   # #  #   #   #   #  #    #   #  # #  #   # #\r\n"
			+ "# #    #       #  #   # #   # # #      #  # #     #   # #     #\r\n"
			+ "# ###   ###    #  #   # #   # #  ###   #  # #     #   #  ###  #\r\n"
			+ "# #         #  #  ##### #   # #     #  #  # #     #####     # #\r\n"
			+ "# #    #    #  #  #   # #   # # #   #  #  # #   # #   # #   # #\r\n"
			+ "# ####  ###    #  #   # ####  #  ###   #  #  ###  #   #  ###  #\r\n"
			+ "###############################################################\n";

}
