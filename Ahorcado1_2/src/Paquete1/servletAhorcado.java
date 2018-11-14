package Paquete1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utilidades.utilidades;

/**
 * Servlet implementation class servletAhorcado
 */
@WebServlet("/servletAhorcado")
public class servletAhorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession laSesion= request.getSession(true);

		String[] arrayPalabras = new String[] {"perro","gato","catarata","lapiz","boton","monedero","peyorativo","misantropia","hipofisis","hipotalamo","ventriculo","esclerosis","necrosis","isquemia"};
		String palabraSeleccionada=arrayPalabras[(int)(Math.random()*14)];
		int vidasRestantes = 6;
		int intentos = 0;
		String letrasUsadas="";
		
		System.out.println(palabraSeleccionada);
		String[] guiones = utilidades.convertirAGuiones(palabraSeleccionada); //ESTA ES LA PALABRA SELECCIONADA

		request.setAttribute("palabraSeleccionada",palabraSeleccionada);
		request.setAttribute("guiones", guiones); //ESTA ES LA PALABRA SELECCIONADA
		request.setAttribute("vidasRestantes", vidasRestantes);
		request.setAttribute("intentos", intentos);
		request.setAttribute("letrasUsadas", letrasUsadas);

		laSesion.setAttribute("palabraSeleccionada",palabraSeleccionada);
		laSesion.setAttribute("guiones", guiones); //ESTA ES LA PALABRA SELECCIONADA
		laSesion.setAttribute("vidasRestantes", vidasRestantes);
		laSesion.setAttribute("intentos", intentos);
		laSesion.setAttribute("letrasUsadas", letrasUsadas);


		String vista = "/paginaAhorcado.jsp"; //Referirse al JSP para pintar en la web
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession laSesion= request.getSession(true);

		String guiones[] = (String[]) laSesion.getAttribute("guiones");
		String letra = request.getParameter("letra");
		String palabraSeleccionada = (String) laSesion.getAttribute("palabraSeleccionada");
		int vidasRestantes = (int) laSesion.getAttribute("vidasRestantes");
		int intentos = (int) laSesion.getAttribute("intentos");
		String hasPerdido = "Has perdido!";
		String hasGanado = "Has ganado, Felicidades campeón!";
		String letrasUsadas = (String) laSesion.getAttribute("letrasUsadas");

		System.out.println(palabraSeleccionada + " " +letra);

		letrasUsadas = letrasUsadas + letra;
		
		
		
		if(palabraSeleccionada.contains(letra)) {
			for (int i = 0; i < palabraSeleccionada.length() ; i++) {
				if (Character.toString(palabraSeleccionada.charAt(i)).equals(letra)) {
					guiones[i] = letra;
					//lugar para cuando metes una letra correctamente
				}
			}
		}else {
			vidasRestantes--;
		}

		intentos++;

		String guiones2 = String.join("", guiones);

		
		laSesion.setAttribute("guiones", guiones);
		laSesion.setAttribute("vidasRestantes", vidasRestantes);
		laSesion.setAttribute("intentos", intentos);
		laSesion.setAttribute("letrasUsadas", letrasUsadas);

		request.setAttribute("palabraSeleccionada", palabraSeleccionada);
		request.setAttribute("guiones", guiones);
		request.setAttribute("vidasRestantes", vidasRestantes);
		request.setAttribute("intentos", intentos);
		request.setAttribute("letrasUsadas", letrasUsadas);

		if (vidasRestantes == 0) {
			request.setAttribute("hasPerdido", hasPerdido);
		}
		
		if (guiones2.equals(palabraSeleccionada)) {
			request.setAttribute("hasGanado", hasGanado);
		}

		String vista = "/paginaAhorcado.jsp"; //Referirse al JSP para pintar en la web
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request, response); 
	}
}
