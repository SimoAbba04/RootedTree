<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Accedi - RootedTree</title>
<link rel="stylesheet" href="./styles/form-style.css">
</head>
<body>
	<% String errore = (String) request.getAttribute("errore"); %>
	
	<div id="form-container">
		<a href="index.jsp" class="form-logo">
			<img src="./images/logo.svg" alt="RootedTree Logo">
		</a>
		
		<form id="formAccesso" action="Login" method="post" onsubmit="return validateLogin()" novalidate>
			<h2>Accedi al tuo account</h2>
			
			<div class="form-group">
				<label for="Femail">Email</label>
				<input type="email" name="email" id="Femail" required 
					   oninput="validaElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
					   pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$">
				<div id="errorEmail" class="error"></div>
			</div>
			
			<div class="form-group">
				<label for="FPassword">Password</label>
				<input type="password" name="password" id="FPassword" required 
					   oninput="validaElem(this, document.getElementById('errorPw'), passwordErrorMessage)"
					   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$">
				<div id="errorPw" class="error"></div>
			</div>
			
			<input type="submit" value="Accedi">
			
			<% if (errore != null) { %>
				<div class="error server-error visible"><%= errore %></div>
			<% } %>
		</form>
		
		<p class="form-switch">
			Non hai un account? <a href="register.jsp">Registrati ora</a>
		</p>
	</div>
	
	<script src="./scripts/form-validation.js"></script>
</body>
</html>
