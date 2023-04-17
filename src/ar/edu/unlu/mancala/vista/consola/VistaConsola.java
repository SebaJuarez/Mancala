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

public class VistaConsola extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField campoTexto;
    private JTextArea pantalla;
    private MancalaController controlador;

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
        campoTexto.setFont(new Font("Consolas", Font.PLAIN, 12));
        campoTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona ENTER en el campo de texto, se agrega el comando a la pantalla
                String entrada = campoTexto.getText();
                pantalla.append("$ " + entrada + "\n");
                campoTexto.setText("");
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
        JPanel panelComandos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelComandos.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.GRAY)
        ));
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
        mostrarMenuInicio();
    }


	private void mostrarMenuInicio() {
		OpcionesMenuInicioConsola opcion = OpcionesMenuInicioConsola.NULO;
			OpcionesMenuInicioConsola.mostrarOpcionesMenuInicio(this.pantalla);
			OpcionesMenuInicioConsola.getOpcion(Integer.parseInt(campoTexto.getText()));
			switch (opcion) {
			case REGISTRAR_JUGADOR:
				//registroJugador();
				break;
			case COMENZAR_PARTIDA:
				//comenzarPartida();
				break;
			case CONTINUAR_PARTIDA:
				//controlador.continuarPartida();
				break;
			case LISTAR_JUGADORES:
				//controlador.obtenerJugadores();
				break;
			case TOP_GANADORES:
				//controlador.topGanadores();
				break;
			case SALIR:
				//Banner.mostrarDespedida();
				//controlador.guardarJugador();
				break;
			default:
				break;
			}
	}


	private void comenzarPartida() {
		// TODO Auto-generated method stub
		
	}


	private void registroJugador() {
		// TODO Auto-generated method stub
		
	}

}

