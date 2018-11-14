package Utilidades;

public class utilidades {

	public static String[] convertirAGuiones(String palabraSeleccionada) {
		String[] guiones =new String[palabraSeleccionada.length()];
		
		for (int i = 0; i < palabraSeleccionada.length(); i++) {
			guiones[i] = "_ ";
		}
		return guiones;
		
	}
}
