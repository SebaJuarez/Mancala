package ar.edu.unlu.mancala.vista.grafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.modelo.Agujero;
import ar.edu.unlu.mancala.modelo.LadoTablero;
import ar.edu.unlu.mancala.modelo.LadoTableroLectura;
import ar.edu.unlu.mancala.vista.AgujeroLectura;
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;

public class TableroPartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private TableroPartidaListener listener;

	private JPanel contentPane;
	private List<JButton> hoyos;
	private JTextArea textController;
	private JLabel lblJ2Name;
	private JLabel lblJ1Name;
	private JLabel lblJ2Zona;
	private JLabel lblJ1Zona;

	public TableroPartida() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblJ2Zona = new JLabel("");
		lblJ2Zona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJ2Zona.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ2Zona.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ2Zona.setForeground(new Color(128, 0, 0));
		lblJ2Zona.setBounds(218, 170, 381, 23);
		contentPane.add(lblJ2Zona);

		lblJ1Zona = new JLabel("");
		lblJ1Zona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblJ1Zona.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ1Zona.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ1Zona.setForeground(new Color(128, 0, 0));
		lblJ1Zona.setBounds(238, 406, 361, 23);
		contentPane.add(lblJ1Zona);

		lblJ2Name = new JLabel("");
		lblJ2Name.setBounds(50, 54, 114, 13);
		lblJ2Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ2Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ2Name.setForeground(Color.WHITE);
		contentPane.add(lblJ2Name);

		lblJ1Name = new JLabel("");
		lblJ1Name.setBounds(638, 54, 114, 13);
		lblJ1Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ1Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ1Name.setForeground(Color.WHITE);
		contentPane.add(lblJ1Name);

		textController = new JTextArea();
		textController.setAlignmentY(1.0f);
		textController.setBorder(null);
		textController.setBounds(1, 1, 379, 43);
		textController.setEditable(false);
		textController.setForeground(Color.BLACK);
		contentPane.add(textController);

		JButton casaJ2 = new JButton("0");
		casaJ2.setBounds(68, 255, 78, 79);
		casaJ2.setOpaque(false);
		casaJ2.setForeground(Color.WHITE);
		casaJ2.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ2.setContentAreaFilled(false);
		casaJ2.setBorderPainted(false);
		contentPane.add(casaJ2);

		JButton casaJ1 = new JButton("0");
		casaJ1.setBounds(654, 255, 78, 79);
		casaJ1.setOpaque(false);
		casaJ1.setForeground(Color.WHITE);
		casaJ1.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ1.setContentAreaFilled(false);
		casaJ1.setBorderPainted(false);
		contentPane.add(casaJ1);

		JButton hoyo12 = new JButton("4");
		hoyo12.setBounds(155, 198, 78, 79);
		hoyo12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo12.setOpaque(false);
		hoyo12.setForeground(Color.WHITE);
		hoyo12.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo12.setContentAreaFilled(false);
		hoyo12.setBorderPainted(false);
		contentPane.add(hoyo12);

		JButton hoyo8 = new JButton("4");
		hoyo8.setBounds(485, 198, 78, 79);
		hoyo8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo8.setOpaque(false);
		hoyo8.setForeground(Color.WHITE);
		hoyo8.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo8.setContentAreaFilled(false);
		hoyo8.setBorderPainted(false);
		contentPane.add(hoyo8);

		JButton hoyo11 = new JButton("4");
		hoyo11.setBounds(238, 198, 78, 79);
		hoyo11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo11.setOpaque(false);
		hoyo11.setForeground(Color.WHITE);
		hoyo11.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo11.setContentAreaFilled(false);
		hoyo11.setBorderPainted(false);
		contentPane.add(hoyo11);

		JButton hoyo7 = new JButton("4");
		hoyo7.setBounds(566, 198, 78, 79);
		hoyo7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo7.setOpaque(false);
		hoyo7.setForeground(Color.WHITE);
		hoyo7.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo7.setContentAreaFilled(false);
		hoyo7.setBorderPainted(false);
		contentPane.add(hoyo7);

		JButton hoyo9 = new JButton("4");
		hoyo9.setBounds(402, 198, 78, 79);
		hoyo9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo9.setOpaque(false);
		hoyo9.setForeground(Color.WHITE);
		hoyo9.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo9.setContentAreaFilled(false);
		hoyo9.setBorderPainted(false);
		contentPane.add(hoyo9);

		JButton hoyo10 = new JButton("4");
		hoyo10.setBounds(319, 198, 78, 79);
		hoyo10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo10.setOpaque(false);
		hoyo10.setForeground(Color.WHITE);
		hoyo10.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo10.setContentAreaFilled(false);
		hoyo10.setBorderPainted(false);
		contentPane.add(hoyo10);

		JButton hoyo6 = new JButton("4");
		hoyo6.setBounds(566, 317, 78, 79);
		hoyo6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo6.setOpaque(false);
		hoyo6.setForeground(Color.WHITE);
		hoyo6.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo6.setContentAreaFilled(false);
		hoyo6.setBorderPainted(false);
		contentPane.add(hoyo6);

		JButton hoyo4 = new JButton("4");
		hoyo4.setBounds(402, 317, 78, 79);
		hoyo4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo4.setOpaque(false);
		hoyo4.setForeground(Color.WHITE);
		hoyo4.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo4.setContentAreaFilled(false);
		hoyo4.setBorderPainted(false);
		contentPane.add(hoyo4);

		JButton hoyo5 = new JButton("4");
		hoyo5.setBounds(485, 317, 78, 79);
		hoyo5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo5.setOpaque(false);
		hoyo5.setForeground(Color.WHITE);
		hoyo5.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo5.setContentAreaFilled(false);
		hoyo5.setBorderPainted(false);
		contentPane.add(hoyo5);

		JButton hoyo3 = new JButton("4");
		hoyo3.setBounds(319, 317, 78, 79);
		hoyo3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo3.setOpaque(false);
		hoyo3.setForeground(Color.WHITE);
		hoyo3.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo3.setContentAreaFilled(false);
		hoyo3.setBorderPainted(false);
		contentPane.add(hoyo3);

		JButton hoyo2 = new JButton("4");
		hoyo2.setBounds(238, 317, 78, 79);
		hoyo2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo2.setOpaque(false);
		hoyo2.setForeground(Color.WHITE);
		hoyo2.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo2.setContentAreaFilled(false);
		hoyo2.setBorderPainted(false);
		contentPane.add(hoyo2);

		JButton hoyo1 = new JButton("4");
		hoyo1.setBounds(155, 317, 78, 79);
		hoyo1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo1.setOpaque(false);
		hoyo1.setForeground(Color.WHITE);
		hoyo1.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo1.setContentAreaFilled(false);
		hoyo1.setBorderPainted(false);
		contentPane.add(hoyo1);

		JScrollPane scrollPane = new JScrollPane(textController);
		scrollPane.setAlignmentY(1.0f);
		scrollPane.setBounds(218, 90, 381, 45);
		contentPane.add(scrollPane);

		hoyos = List.of( hoyo1, hoyo2, hoyo3, hoyo4, hoyo5, hoyo6, casaJ1, hoyo7, hoyo8, hoyo9, hoyo10, hoyo11,
				hoyo12,casaJ2);

		for (int i = 0; i < hoyos.size(); i++) {
			hoyos.get(i).setName(Integer.toString(i+1));
		}

		hoyos.forEach(boton -> {
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.onHoyoButtonClick(boton.getName());
				}
			});
		});

		JLabel lblTableroFondo = new JLabel("");
		lblTableroFondo.setBounds(0, 0, 790, 470);
		lblTableroFondo.setIcon(new ImageIcon(getClass().getResource("/tablero_mancala_partida.png")));
		contentPane.add(lblTableroFondo);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});
	}

	public void informar(String texto) {
		this.textController.append(texto + "\n");
		int posicionFinal = this.textController.getDocument().getLength();
		int desplazamiento = 1;
		int posicionVisible = Math.max(0, posicionFinal - desplazamiento);
		this.textController.setCaretPosition(posicionVisible);
	}

	public void actualizarTablero(List<LadoTablero> ladosTablero) {
		int i = 0;
		List<JugadorLectura> jugadores = new LinkedList<JugadorLectura>();
		for(LadoTablero lado : ladosTablero ) {
			List<Agujero> agujeros = lado.getAgujeros();
			for(Agujero agujero : agujeros) {
				hoyos.get(i).setText(Integer.toString(agujero.getHabas()));
				i ++;
			}
			jugadores.add(lado.getJugador());
		}
		
		setJugadores(jugadores);
		
	}

	public TableroPartidaListener getListener() {
		return listener;
	}

	public void setListener(TableroPartidaListener listener) {
		this.listener = listener;
	}

	public void setJugadores(List<JugadorLectura> jugadoresEnPartida) {
		this.textController.setText("");
		this.lblJ1Name.setText(jugadoresEnPartida.get(0).getNombre());
		this.lblJ2Name.setText(jugadoresEnPartida.get(1).getNombre());
		this.lblJ1Zona.setText("ZONA DE : " + jugadoresEnPartida.get(0).getNombre());
		this.lblJ2Zona.setText("ZONA DE : " + jugadoresEnPartida.get(1).getNombre());
	}
}
