<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accedi</title>
<script src="./scripts/validate.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/registrazione.css">
</head>
<body>
	<%String errore =(String) request.getAttribute("errore"); %>
	<div id="form-container">
		<form id="formAccesso" action="Login" method="post" novalidate>
			<h2>Accedi al tuo account</h2>
			<div class="form-group">
				<label for="logName">Inserisci la tua email:</label><input
					type="email" name="email" id="logMail" required
					onchange="validaElem(this,document.getElementById('errorMail'),emailErrorMessage)"
					pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$">
				<div id="errorMail" class="error"></div>
			</div>
			<div class="form-group">
				<label for="logPass">Inserisci la tua password:</label><input
					type="password" name="password" id="logPass" required
					onchange="validaElem(this,document.getElementById('errorPw'),passwordErrorMessage)"
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$">
				<div id="errorPw" class="error"></div>
			</div>
			<input type="submit" value="Accedi" onclick="return validateLogin()">
		</form>
		<%if (errore!=null) {%>
				<div class="error.visible"><%=errore%></div>
			<%} %>
		
	</div>
</body>
</html>