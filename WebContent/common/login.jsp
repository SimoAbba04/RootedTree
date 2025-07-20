<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Accedi - RootedTree</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/form-style.css">
</head>
<body>
	<div id="form-container">
		<a href="<%=request.getContextPath()%>/common/index.jsp" class="form-logo"> <img
			src="<%=request.getContextPath()%>/images/rootedTreeFavicon.svg" alt="RootedTree Logo">
		</a>
		<form id="formAccesso" action="<%=request.getContextPath()%>/LoginServlet" method="post"
			onsubmit="return validateLogin()" novalidate>
			<h2>Accedi al tuo account</h2>
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
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$">
				<div id="errorPw" class="error"></div>
			</div>
			<input type="submit" value="Accedi" onclick="return validateLogin()">
		</form>
		<c:if test="${not empty errorMessage }">
			<div class="error server-error visible">
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
		<p class="form-switch">
			Non hai un account? <a href="<%=request.getContextPath()%>/common/register.jsp">Registrati ora</a>
		</p>
	</div>
	<script src="<%=request.getContextPath()%>/scripts/formValidation.js"></script>
</body>
</html>
