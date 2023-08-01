package ar.edu.unlu.mancala.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encriptador {

	 public static String encriptarContrasenia(String contrasenia) {
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
	            return Base64.getEncoder().encodeToString(hash);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null;
	        }
	 }

	 public static boolean verificarContrasenia(String contraseniaIngresada, String contraseniaAlmacenada) {
		 String contraseniaEncriptada = encriptarContrasenia(contraseniaIngresada);
	     return contraseniaEncriptada != null && contraseniaEncriptada.equals(contraseniaAlmacenada);
	 }
	
}
