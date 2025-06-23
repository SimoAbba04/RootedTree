<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
</head>
<body>
	<div id ="form">
	<form action="Registration" method="post">
		<label for="nome">Nome</label>:<input type="text" name="nome"required>
		<label for="cognome">Cognome</label>:<input type="text" name="cognome"required>
		<label for="email">Email</label>:<input type="email" name="email"required>
		<label for="password">Password</label>:<input type="password" name="password"required>
		<label for="dataNascita">Data di nascita</label>:<input type="date" name="dataNascita"required>
		<input type ="submit" value= "invia">
	</form>
	</div>
	<div id = "messaggio"> Messaggio:<%=request.getAttribute("messaggio") %> </div>
</body>
</html>