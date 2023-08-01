package ar.edu.unlu.mancala.vista.grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class CartelGanador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VolverListener listener;
	private JLabel lblPerdidas;
	private JLabel lblEmpatadas;
	private JLabel lblGanadas;

	public CartelGanador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("VOLVER");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onVolverButtonClick();
			}
		});
		
		lblPerdidas = new JLabel("");
		lblPerdidas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidas.setForeground(Color.WHITE);
		lblPerdidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerdidas.setBounds(287, 240, 150, 27);
		contentPane.add(lblPerdidas);
		
		lblEmpatadas = new JLabel("");
		lblEmpatadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmpatadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpatadas.setForeground(Color.WHITE);
		lblEmpatadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmpatadas.setBounds(287, 325, 150, 27);
		contentPane.add(lblEmpatadas);
		
		lblGanadas = new JLabel("");
		lblGanadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGanadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadas.setForeground(Color.GREEN);
		lblGanadas.setBounds(287, 159, 150, 27);
		contentPane.add(lblGanadas);
		btnSalir.setOpaque(false);
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(614, 10, 162, 33);
		contentPane.add(btnSalir);
		
		JLabel lblFondoGanador = new JLabel("");
		lblFondoGanador.setBounds(0, 0, 790, 470);
		contentPane.add(lblFondoGanador);
		
		lblFondoGanador.setIcon(new ImageIcon(getClass().getResource("/fondo_ganador.png")));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		
		});
	}

	public VolverListener getListener() {
		return listener;
	}

	public void setListener(VolverListener listener) {
		this.listener = listener;
	}
	
	public void mostrarJugador(JugadorLectura jugador) {
		this.lblEmpatadas.setText("EMPATADAS : " + Integer.toString(jugador.getEmpatadas()));
		this.lblPerdidas.setText("PERDIDAS : " + Integer.toString(jugador.getPerdidas()));
		this.lblGanadas.setText("GANADAS : " + Integer.toString(jugador.getGanadas()));
	}
}
