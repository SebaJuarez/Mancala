package ar.edu.unlu.mancala.modelo;

public enum Posicion {
//  [  0 , 1,2,3,4,5,6 ,  7 , 8,9,10,11,12,13] ordinal del enum
	CASAJ2,A,B,C,D,E,F,CASAJ1,G,H, I, J, K, L;

	// compara posicion con un ordinal
	public static boolean compararPosicionConOrdinal(Posicion x, int p) {
		return (x.ordinal() == p);
	}
	
	//devuelve la posicion desde un ordinal
	public static Posicion getPosicionPorValor(int p) {
		  for(Posicion n : values()){
			  if(n.ordinal() == p) return n;
	        }
		  return null;
	}
	
	//devuelve la posicion opuesta del tablero a la posicion pasada por parametros
	public static Posicion contrario(Posicion p) {
		switch(p){
		case A :{
			return L;
		}
		case B :{
			return K;
		}
		case C:{
			return J;
		}
		case D :{
			return I;
		}
		case E :{
			return H;
		}
		case F :{
			return G;
		}
		case G :{
			return F;
		}
		case H :{
			return E;
		}
		case I :{
			return D;
		}
		case J :{
			return C;
		}
		case K :{
			return B;
		}
		case L :{
			return A;
		}
		default: {
			return p;
		}
		}
	}
	
	//devuelve la posicion opuesta del tablero a la posicion pasada por parametros
	public static Posicion getPosicionDeString(String p) {
		p = p.toUpperCase();
		switch(p){
		case "A" :{
			return A;
		}
		case "B" :{
			return B;
		}
		case "C":{
			return C;
		}
		case "D" :{
			return D;
		}
		case "E" :{
			return E;
		}
		case "F" :{
			return F;
		}
		case "G" :{
			return G;
		}
		case "H" :{
			return H;
		}
		case "I" :{
			return I;
		}
		case "J" :{
			return J;
		}
		case "K" :{
			return K;
		}
		case "L" :{
			return L;
		}
		default: {
			return null;
		}
		}
	}
	
}
