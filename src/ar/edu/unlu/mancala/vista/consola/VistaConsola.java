package ar.edu.unlu.mancala.vista.consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Tablero;
import ar.edu.unlu.mancala.vista.Ivista;

public class VistaConsola extends JFrame implements Ivista {

    private static final long serialVersionUID = 1L;
    private JTextField campoTexto;
    private JTextArea pantalla;
    private MancalaController controlador;
    private EstadosFlujo estadoFlujo = EstadosFlujo.MENU_INICIO;
    private JPanel panelComandos;
    private boolean esperandoTecla = false;

	public VistaConsola() {
        // Configuración de la ventana principal
        setTitle("Consola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        
        // Creación de la pantalla de la consola
        pantalla = new JTextArea();
        pantalla.setEditable(false);
        pantalla.setBackground(Color.BLACK);
        pantalla.setForeground(Color.WHITE);
        pantalla.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scrollPantalla = new JScrollPane(pantalla);
        scrollPantalla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Creación del campo de texto para escribir los comandos
        campoTexto = new JTextField(50);
        campoTexto.setToolTipText("");
        campoTexto.hasFocus();
        campoTexto.setFont(new Font("Consolas", Font.PLAIN, 12));
        campoTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona ENTER en el campo de texto, se agrega el comando a la pantalla
                String entrada = campoTexto.getText();
                println(entrada);
                campoTexto.setText("");
            	switch(estadoFlujo) {
            	case MENU_INICIO : 
            		if(!esperandoTecla) {
        				opcionesMenuInicio(entrada);        			
            		} else {
            			esperandoTecla = false;
            			menuInicio();
            		}
            		break;
            	case MENU_PRINCIPAL : 
            		if(!esperandoTecla) {
        				opcionesPrincipal(entrada);        			
            		} else {
            			esperandoTecla = false;
            			menuInicio();
            		}
            		break;
            	case LOGIN :
            		Jugador jugador = new Jugador();
            		jugador.setNombre(entrada);
            		controlador.setJugador(jugador);
            		estadoFlujo = EstadosFlujo.MOVIMIENTOS;
            		break;
            	case MOVIMIENTOS :
            		mover(entrada);
            		break;
				default:
					break;
            	}
            }
        });
        
        /*
        // Creación del botón para enviar los comandos
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Consolas", Font.PLAIN, 12));
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona el botón, se agrega el comando a la pantalla
                String entrada = campoTexto.getText();
                pantalla.append("$ " + entrada + "\n");
                campoTexto.setText("");
            }
        });
        */
        
        // accion que sucede cuando se cierra una ventana (una vista)
        this.addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Coloca aquí el código que quieras ejecutar antes de cerrar el JFrame
                System.out.println("Cerrando ventana...");
            }});
        
        // Creación del panel para el campo de texto y el botón
        panelComandos = new JPanel();
        panelComandos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelComandos.add(campoTexto);
        //panelComandos.add(botonEnviar);
        
        // Agregamos los componentes a la ventana
        getContentPane().add(scrollPantalla, BorderLayout.CENTER);
        getContentPane().add(panelComandos, BorderLayout.SOUTH);
    }

    // menu inicio ---------------------------------------------------------------
    public void menuInicio() {
    	println(OpcionesMenuInicioConsola.mostrarOpcionesMenuPrincipal());
	}

	private void opcionesMenuInicio(String entrada) {
		switch(entrada) {
		case "1" :
			println(Reglamento.mostrarReglas());
			println("ingrese cualquier tecla para volver al menu");
			esperandoTecla = true;
			break;
		case "2" : 
			estadoFlujo = EstadosFlujo.MENU_PRINCIPAL; 
			println(OpcionesMenuPrincipalConsola.mostrarOpcionesMenuPrincipal());
			break;
		default :
			println("ingrese una opcion correcta !");    
			break;
		}
	}
	//----------------------------------------------------------------------------
	
	// mwnu principal ------------------------------------------------------------
	private void opcionesPrincipal(String entrada) {
		switch(entrada) {
		case "1":
			break;
		case "2" :
			controlador.setJugador(new Jugador());
			break;
		case "3" :
			break;
		default :
			println("ingrese una opcion valida!");
			break;
		}
	}
	//----------------------------------------------------------------------------

	// movimiento ---------------------------------------------------------------
	private void mover(String entrada) {
		int pos = -1;
		try {
			pos = Integer.parseInt(entrada);
			controlador.mover(pos);
		} catch (NumberFormatException e) {
			println("ingrese un numero!");
		}
	}
	//---------------------------------------------------------------------------
	
	// reemplazo el println -----------------------------------------------------
	private void println(String texto) {
    	pantalla.append("$ " + texto + "\n");
    	pantalla.setCaretPosition(pantalla.getDocument().getLength());
    }
	//---------------------------------------------------------------------------
  
	// metodos de la interfaz Ivista ---------------------------------------------
	@Override
	public void iniciar() {
		// Mostramos la ventana
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		menuInicio();
		//formularioUsuario();
		//mostrarMenuInicio();
	}
	
	private void formularioUsuario() {
		println("ingrese su nombre");
	}

	@Override
	public void mostrarTablero(Tablero tablero) {
		println(tablero.toString());
	}

	@Override
	public void informar(String string) {
		println(string);
	}

	@Override
	public void informar(Jugador modelo, String string) {
		println(string + modelo.getNombre());
	}
	
    private void formularioMovimientos() {
    	println("ingrese el inidice para mover");				
    }

	@Override
	public void mostrarGanador(Jugador obtenerGanador) {
		//aca voy a operar el ganador para mostrar en pantalla la partida
	}
	
	@Override
	public void mostrarSalaDeEspera() {
		estadoFlujo = EstadosFlujo.MOVIMIENTOS;
		println(Banner.esperandoJugador);
	}
	//---------------------------------------------------------------------------

	// Setters y Getters
	@Override
	public MancalaController getControlador() {
		return controlador;
	}
	
	
	@Override
	public void setControlador(MancalaController controlador) {
		this.controlador = controlador;
	}
	//---------------------------------------------------------------------------

}

