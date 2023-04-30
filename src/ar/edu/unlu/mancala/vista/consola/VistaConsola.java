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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.controlador.MancalaController;
import ar.edu.unlu.mancala.modelo.Jugador;
import ar.edu.unlu.mancala.modelo.Tablero;

public class VistaConsola extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField campoTexto;
    private JTextArea pantalla;
    private MancalaController controlador;
    private EstadosFlujo estadoFlujo = EstadosFlujo.LOGIN;
    private String textoActual ;
    private JPanel panelComandos;


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
        campoTexto.setFont(new Font("Consolas", Font.PLAIN, 12));
        campoTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona ENTER en el campo de texto, se agrega el comando a la pantalla
            	
            	
            	switch(estadoFlujo) {
            	case LOGIN :
            		textoActual = nextLine();
            		Jugador jugador = new Jugador();
            		jugador.setNombre(textoActual);
            		controlador.setJugador(jugador);
            		estadoFlujo = EstadosFlujo.MOVIMIENTOS;
            		formularioMovimientos();
            		break;
            	case MOVIMIENTOS :
            		controlador.mover(Integer.parseInt(nextLine()));
            		;
            		break;
            	}
            	
            	
                String entrada = campoTexto.getText();
                pantalla.append("$ " + entrada + "\n");
                campoTexto.setText("");
            }

			private void formularioMovimientos() {
				println("ingrese su nombre");				
			}

			private String nextLine() {
				return campoTexto.getText();
			}
        });
        
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
        
        // accion que sucede cuando se cierra una ventana (una vista)
        this.addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Coloca aquí el código que quieras ejecutar antes de cerrar el JFrame
                System.out.println("Cerrando ventana...");
            }});
        
        // Creación del panel para el campo de texto y el botón
        panelComandos = new JPanel();
        panelComandos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelComandos.add(campoTexto);
        panelComandos.add(botonEnviar);
        
        // Agregamos los componentes a la ventana
        getContentPane().add(scrollPantalla, BorderLayout.CENTER);
        getContentPane().add(panelComandos, BorderLayout.SOUTH);
    }


    public void iniciar() {
        // Mostramos la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        formularioUsuario();
        //mostrarMenuInicio();
    }

    private void println(String texto) {
    	pantalla.append(texto + "\n");
    }
    
	private void comenzarPartida() {
		
	}
	public MancalaController getControlador() {
		return controlador;
	}
	
	
	public void setControlador(MancalaController controlador) {
		this.controlador = controlador;
	}

	private void formularioUsuario() {
		println("$ ingrese su nombre");
	}

	public void mostrarTablero(Tablero modelo) {
		println(modelo.toString());
	}

	public void informar(String string) {
		println(string);
	}

	public void informar(Jugador modelo, String string) {
		println(string + modelo.getNombre());
	}

}

