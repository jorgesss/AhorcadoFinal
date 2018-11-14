<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String[] guiones = (String[]) request.getAttribute("guiones");
    	String palabraSeleccionada = (String) request.getAttribute("palabraSeleccionada");
    	String textoGuiones = "";
    	int vidasRestantes = (int) request.getAttribute("vidasRestantes");
    	int intentos = (int) request.getAttribute("intentos");
    	String hasPerdido = (String) request.getAttribute("hasPerdido");
    	String hasGanado = (String) request.getAttribute("hasGanado");
    	String letrasUsadas = (String) request.getAttribute("letrasUsadas");
    	
    	for(int i=0;i<guiones.length;i++){
    		textoGuiones = textoGuiones+guiones[i];
    	}
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina Ahorcado</title>
</head>
<body>
<form action="servletAhorcado" method="post">
Palabra secreta: <%=textoGuiones %><input type="text" name="letra" id="letra" size="1" maxlength="1">
<br/>
Vidas Restantes: <%=vidasRestantes %>
<br/>
Intentos: <%=intentos %>
<br/>
	<%if(hasPerdido != null){ %>
		<%=hasPerdido %>
	<%}%>
<br/>
	<%if(hasGanado != null){ %>
		<%=hasGanado %>
	<%}%>
<br/>
Letras Usadas: <%=letrasUsadas %>
<br/>
<%if(vidasRestantes <= 0){ %>
	<a href="http://localhost:8080/Ahorcado1/servletAhorcado">Volver a intentar</a>
<%} %>
<%if(hasGanado != null){ %>
	<a href="http://localhost:8080/Ahorcado1/servletAhorcado">Volver a intentar</a>
<%} %>
<input type="submit"  value="Enviar">
</form>
</body>
</html>