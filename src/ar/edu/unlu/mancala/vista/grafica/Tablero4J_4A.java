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
import ar.edu.unlu.mancala.vista.JugadorLectura;
import ar.edu.unlu.mancala.vista.grafica.listener.TableroPartidaListener;

public class Tablero4J_4A extends JFrame implements ITablero {

	private static final long serialVersionUID = 1L;
	private TableroPartidaListener listener;

	private JPanel contentPane;
	private List<JButton> hoyos;
	private JTextArea textController;
	private JLabel lblJ1Name;
	private JLabel lblJ3Name;
	private JLabel lblJ4Name;
	private JLabel lblJ2Name;

	public Tablero4J_4A() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblJ1Name = new JLabel("");
		lblJ1Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ1Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ1Name.setForeground(Color.WHITE);
		lblJ1Name.setBounds(56, 144, 190, 27);
		contentPane.add(lblJ1Name);

		lblJ4Name = new JLabel("");
		lblJ4Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ4Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ4Name.setForeground(Color.WHITE);
		lblJ4Name.setBounds(54, 339, 190, 27);
		contentPane.add(lblJ4Name);

		lblJ3Name = new JLabel("");
		lblJ3Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ3Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ3Name.setForeground(Color.WHITE);
		lblJ3Name.setBounds(55, 275, 190, 27);
		contentPane.add(lblJ3Name);

		JButton hoyo15 = new JButton("4");
		hoyo15.setOpaque(false);
		hoyo15.setForeground(Color.WHITE);
		hoyo15.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo15.setContentAreaFilled(false);
		hoyo15.setBorderPainted(false);
		hoyo15.setBounds(313, 270, 78, 79);
		contentPane.add(hoyo15);

		JButton hoyo14 = new JButton("4");
		hoyo14.setOpaque(false);
		hoyo14.setForeground(Color.WHITE);
		hoyo14.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo14.setContentAreaFilled(false);
		hoyo14.setBorderPainted(false);
		hoyo14.setBounds(313, 192, 78, 79);
		contentPane.add(hoyo14);

		lblJ2Name = new JLabel("");
		lblJ2Name.setBounds(54, 211, 190, 27);
		lblJ2Name.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ2Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ2Name.setForeground(Color.WHITE);
		contentPane.add(lblJ2Name);

		textController = new JTextArea();
		textController.setAlignmentY(1.0f);
		textController.setBorder(null);
		textController.setBounds(1, 1, 379, 43);
		textController.setEditable(false);
		textController.setForeground(Color.BLACK);
		contentPane.add(textController);

		JButton casaJ2 = new JButton("0");
		casaJ2.setBounds(668, 39, 78, 79);
		casaJ2.setOpaque(false);
		casaJ2.setForeground(Color.WHITE);
		casaJ2.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ2.setContentAreaFilled(false);
		casaJ2.setBorderPainted(false);
		contentPane.add(casaJ2);

		JButton casaJ1 = new JButton("0");
		casaJ1.setBounds(670, 350, 78, 79);
		casaJ1.setOpaque(false);
		casaJ1.setForeground(Color.WHITE);
		casaJ1.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ1.setContentAreaFilled(false);
		casaJ1.setBorderPainted(false);
		contentPane.add(casaJ1);

		JButton hoyo13 = new JButton("4");
		hoyo13.setBounds(313, 114, 78, 79);
		hoyo13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo13.setOpaque(false);
		hoyo13.setForeground(Color.WHITE);
		hoyo13.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo13.setContentAreaFilled(false);
		hoyo13.setBorderPainted(false);
		contentPane.add(hoyo13);

		JButton casaJ4 = new JButton("4");
		casaJ4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		casaJ4.setBounds(313, 350, 78, 79);
		casaJ4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		casaJ4.setOpaque(false);
		casaJ4.setForeground(Color.WHITE);
		casaJ4.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ4.setContentAreaFilled(false);
		casaJ4.setBorderPainted(false);
		contentPane.add(casaJ4);

		JButton hoyo11 = new JButton("4");
		hoyo11.setBounds(405, 34, 78, 79);
		hoyo11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo11.setOpaque(false);
		hoyo11.setForeground(Color.WHITE);
		hoyo11.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo11.setContentAreaFilled(false);
		hoyo11.setBorderPainted(false);
		contentPane.add(hoyo11);

		JButton hoyo7 = new JButton("4");
		hoyo7.setBounds(670, 114, 78, 79);
		hoyo7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo7.setOpaque(false);
		hoyo7.setForeground(Color.WHITE);
		hoyo7.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo7.setContentAreaFilled(false);
		hoyo7.setBorderPainted(false);
		contentPane.add(hoyo7);

		JButton hoyo9 = new JButton("4");
		hoyo9.setBounds(578, 33, 78, 79);
		hoyo9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo9.setOpaque(false);
		hoyo9.setForeground(Color.WHITE);
		hoyo9.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo9.setContentAreaFilled(false);
		hoyo9.setBorderPainted(false);
		contentPane.add(hoyo9);

		JButton hoyo10 = new JButton("4");
		hoyo10.setBounds(491, 34, 78, 79);
		hoyo10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo10.setOpaque(false);
		hoyo10.setForeground(Color.WHITE);
		hoyo10.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo10.setContentAreaFilled(false);
		hoyo10.setBorderPainted(false);
		contentPane.add(hoyo10);

		JButton hoyo6 = new JButton("4");
		hoyo6.setBounds(670, 192, 78, 79);
		hoyo6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo6.setOpaque(false);
		hoyo6.setForeground(Color.WHITE);
		hoyo6.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo6.setContentAreaFilled(false);
		hoyo6.setBorderPainted(false);
		contentPane.add(hoyo6);

		JButton casaJ3 = new JButton("4");
		casaJ3.setBounds(312, 36, 78, 79);
		casaJ3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		casaJ3.setOpaque(false);
		casaJ3.setForeground(Color.WHITE);
		casaJ3.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		casaJ3.setContentAreaFilled(false);
		casaJ3.setBorderPainted(false);
		contentPane.add(casaJ3);

		JButton hoyo5 = new JButton("4");
		hoyo5.setBounds(670, 270, 78, 79);
		hoyo5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo5.setOpaque(false);
		hoyo5.setForeground(Color.WHITE);
		hoyo5.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo5.setContentAreaFilled(false);
		hoyo5.setBorderPainted(false);
		contentPane.add(hoyo5);

		JButton hoyo3 = new JButton("4");
		hoyo3.setBounds(583, 350, 78, 79);
		hoyo3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo3.setOpaque(false);
		hoyo3.setForeground(Color.WHITE);
		hoyo3.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo3.setContentAreaFilled(false);
		hoyo3.setBorderPainted(false);
		contentPane.add(hoyo3);

		JButton hoyo2 = new JButton("4");
		hoyo2.setBounds(495, 349, 78, 79);
		hoyo2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo2.setOpaque(false);
		hoyo2.setForeground(Color.WHITE);
		hoyo2.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo2.setContentAreaFilled(false);
		hoyo2.setBorderPainted(false);
		contentPane.add(hoyo2);

		JButton hoyo1 = new JButton("4");
		hoyo1.setBounds(409, 350, 78, 79);
		hoyo1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hoyo1.setOpaque(false);
		hoyo1.setForeground(Color.WHITE);
		hoyo1.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		hoyo1.setContentAreaFilled(false);
		hoyo1.setBorderPainted(false);
		contentPane.add(hoyo1);

		JScrollPane scrollPane = new JScrollPane(textController);
		scrollPane.setAlignmentY(1.0f);
		scrollPane.setBounds(416, 244, 236, 44);
		contentPane.add(scrollPane);

		hoyos = List.of(hoyo1, hoyo2, hoyo3, casaJ1, hoyo5, hoyo6, hoyo7, casaJ2, hoyo9, hoyo10, hoyo11, casaJ3, hoyo13,
				hoyo14, hoyo15, casaJ4);

		for (int i = 0; i < hoyos.size(); i++) {
			hoyos.get(i).setName(Integer.toString(i + 1));
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
		lblTableroFondo.setIcon(new ImageIcon(getClass().getResource("/tablero_4v4_mancala_partida.png")));
		contentPane.add(lblTableroFondo);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});
	}

	@Override
	public void informar(String texto) {
		this.textController.append(texto + "\n");
		int posicionFinal = this.textController.getDocument().getLength();
		int desplazamiento = 1;
		int posicionVisible = Math.max(0, posicionFinal - desplazamiento);
		this.textController.setCaretPosition(posicionVisible);
	}

	@Override
	public void actualizarTablero(List<LadoTablero> ladosTablero) {
		int i = 0;
		List<JugadorLectura> jugadores = new LinkedList<JugadorLectura>();
		for (LadoTablero lado : ladosTablero) {
			List<Agujero> agujeros = lado.getAgujeros();
			for (Agujero agujero : agujeros) {
				hoyos.get(i).setText(Integer.toString(agujero.getHabas()));
				i++;
			}
			jugadores.add(lado.getJugador());
		}

		setJugadores(jugadores);

	}

	@Override
	public TableroPartidaListener getListener() {
		return listener;
	}

	@Override
	public void setListener(TableroPartidaListener listener) {
		this.listener = listener;
	}

	@Override
	public void setJugadores(List<JugadorLectura> jugadoresEnPartida) {
		this.lblJ1Name.setText(jugadoresEnPartida.get(0).getNombre());
		this.lblJ2Name.setText(jugadoresEnPartida.get(1).getNombre());
		this.lblJ3Name.setText(jugadoresEnPartida.get(2).getNombre());
		this.lblJ4Name.setText(jugadoresEnPartida.get(3).getNombre());
	}

	@Override
	public void inicializar() {
		this.textController.setText("");
	}
}
