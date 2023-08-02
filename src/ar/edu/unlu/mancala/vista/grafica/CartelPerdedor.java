package ar.edu.unlu.mancala.vista.grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.VolverListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class CartelPerdedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VolverListener listener;
	private JLabel lblPerdidas;
	private JLabel lblEmpatadas;
	private JLabel lblGanadas;

	public CartelPerdedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblGanadas = new JLabel("");
		lblGanadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadas.setForeground(Color.WHITE);
		lblGanadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGanadas.setBounds(287, 159, 150, 27);
		contentPane.add(lblGanadas);

		lblEmpatadas = new JLabel("");
		lblEmpatadas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmpatadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpatadas.setForeground(Color.WHITE);
		lblEmpatadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmpatadas.setBounds(287, 325, 150, 27);
		contentPane.add(lblEmpatadas);

		lblPerdidas = new JLabel("");
		lblPerdidas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerdidas.setForeground(Color.RED);
		lblPerdidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerdidas.setBounds(287, 240, 150, 27);
		contentPane.add(lblPerdidas);

		JButton btnSalir = new JButton("VOLVER");
		btnSalir.setOpaque(false);
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(614, 10, 162, 33);
		contentPane.add(btnSalir);

		JLabel lblFondoPerdedor = new JLabel("");
		lblFondoPerdedor.setBounds(0, 0, 790, 470);
		contentPane.add(lblFondoPerdedor);

		lblFondoPerdedor.setIcon(new ImageIcon(getClass().getResource("/fondo_perdedor.png")));

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onVolverButtonClick();
			}
		});

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
