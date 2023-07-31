package ar.edu.unlu.mancala.vista.grafica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.mancala.vista.grafica.listener.Cerrable;

public class Reglas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cerrable listener;

	public Reglas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				listener.onCloseWindow();
			}
		});
	}

	public Cerrable getListener() {
		return listener;
	}

	public void setListener(Cerrable listener) {
		this.listener = listener;
	}
	
}
