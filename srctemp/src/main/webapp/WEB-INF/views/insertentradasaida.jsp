<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align = "center">
 	<h1>Registro de entrada ou de saída</h1>
 	<form action="<%= request.getContextPath() %>/inserir" method="post">
 	 <table style = "with: 80%">
 	  <tr>
 	   <td>Codigo de transacao</td>
 	   <td><input type="text" name="codigo_transacao" /></td>
 	   </tr>
 	   <tr>
 	   <td>Codigo do produto</td>
 	   <td><input type="text" name="codigo_produto" /></td>
 	   </tr>
 	   <tr>
 	   <td>Quantidade</td>
 	   <td><input type="text" name="quantidade" /></td>
 	   </tr>
 	   <tr>
 	   <td>Tipo (e: entrada, s: saída)</td>
 	   <td><input type="text" name="tipo" /></td>
 	   </tr>
	  </table>
	  <input type="submit" name="Submit" />
 	
 	
 	</form>
 
 </div>
</body>
</html>