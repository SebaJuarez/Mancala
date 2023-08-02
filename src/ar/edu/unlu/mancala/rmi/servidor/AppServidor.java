package ar.edu.unlu.mancala.rmi.servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import ar.edu.unlu.mancala.modelo.MancalaPartida;
import ar.edu.unlu.mancala.serializacion.services.JugadorService;
import ar.edu.unlu.mancala.serializacion.services.JugadorServiceImpl;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class AppServidor {

	public static void main(String[] args) throws RemoteException {
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(null,
				"Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
				JOptionPane.QUESTION_MESSAGE, null, ips.toArray(), null);
		String port = (String) JOptionPane.showInputDialog(null,
				"Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
				JOptionPane.QUESTION_MESSAGE, null, null, 8888);
		JugadorService service = new JugadorServiceImpl();
		MancalaPartida modelo = new MancalaPartida(service);
		Servidor servidor = new Servidor(ip, Integer.parseInt(port));
		try {
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (RMIMVCException e) {
			e.printStackTrace();
		}
	}

}
