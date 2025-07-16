<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrati - RootedTree</title>
<link rel="stylesheet" href="./styles/form-style.css">
</head>
<body>

	<div id="form-container">
		<a href="index.jsp" class="form-logo"> <img
			src="./images/rootedTreeFavicon.svg" alt="RootedTree Logo">
		</a>

		<form id="formRegistrazione" action="RegistrationServlet"
			method="post" onsubmit="return validateRegistration()" novalidate>
			<h2>Crea il tuo Account</h2>

			<div class="form-group">
				<label for="Fnome">Nome</label> <input type="text" name="nome"
					id="Fnome" required
					oninput="validaElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)"
					pattern="^[A-Z][a-zA-Z\s]*$" autocomplete="on">
				<div id="errorName" class="error"></div>
			</div>

			<div class="form-group">
				<label for="Fcognome">Cognome</label> <input type="text"
					name="cognome" id="Fcognome" required
					oninput="validaElem(this, document.getElementById('errorSur'), nameOrLastnameErrorMessage)"
					pattern="^[A-Z][a-zA-Z\s]*$" autocomplete="on">
				<div id="errorSur" class="error"></div>
			</div>

			<div class="form-group">
				<label for="Femail">Email</label> <input type="email" name="email"
					id="Femail" required
					oninput="validaElem(this, document.getElementById('errorEmail'), emailErrorMessage)"
					pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$">
				<div id="errorEmail" class="error"></div>
			</div>

			<div class="form-group">
				<label for="FPassword">Password</label> <input type="password"
					name="password" id="FPassword" required
					oninput="validaElem(this, document.getElementById('errorPw'), passwordErrorMessage)"
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$"
					autocomplete="on">
				<div id="errorPw" class="error"></div>
			</div>

			<div class="form-group">
				<label for="FdataNascita">Data di nascita</label> <input type="date"
					name="dataNascita" id="FdataNascita" required
					oninput="validateAge(this, document.getElementById('errorDate'))">
				<div id="errorDate" class="error"></div>
			</div>

			<input type="submit" value="Registrati"
				onclick="return validateRegistration()">
		</form>
		<c:if test="${not empty successMessage}">
			<div class="notification success">
				<c:out value="${successMessage}" />
			</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="notification error">
				<c:out value="${errorMessage}" />
			</div>
		</c:if>
		<p class="form-switch">
			Hai già un account? <a href="login.jsp">Accedi</a>
		</p>
	</div>

	<script src="./scripts/formValidation.js"></script>
</body>
</html>