<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati - Rooted Tree</title>
<script src="./scripts/registerValidation.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/registrazione.css">
</head>
<body>

    <div id="form-container">
        <form id="formRegistrazione" action="Registration" method="post" novalidate>
            <h2>Crea il tuo Account</h2>
            
           
            <div class="form-group">
                <label for="Fnome">Nome</label>
                <input type="text" name="nome" id="Fnome" required onchange="validaElem(this, document.getElementById('errorName'), nameOrLastnameErrorMessage)" pattern="^[A-Z][a-zA-Z\s]*$">
                <div id="errorName" class="error"></div>
            </div>
            
            <div class="form-group">
                <label for="Fcognome">Cognome</label>
                <input type="text" name="cognome" id="Fcognome" required onchange="validaElem(this, document.getElementById('errorSur'), nameOrLastnameErrorMessage)" pattern="^[A-Z][a-zA-Z\s]*$">
                <div id="errorSur" class="error"></div>
            </div>

            <div class="form-group">
                <label for="Femail">Email</label>
                <input type="email" name="email" id="Femail" required onchange="validaElem(this, document.getElementById('errorEmail'), emailErrorMessage)" pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$">
                <div id="errorEmail" class="error"></div>
            </div>

            <div class="form-group">
                <label for="FPassword">Password</label>
                <input type="password" name="password" id="FPassword" required onchange="validaElem(this, document.getElementById('errorPw'), passwordErrorMessage)" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$">
                <div id="errorPw" class="error"></div>
            </div>

            <div class="form-group">
                <label for="FdataNascita">Data di nascita</label>
                <input type="date" name="dataNascita" id="FdataNascita" required>
            </div>
            
            <input type="submit" value="Registrati" onclick="return validate()">
        </form>
    </div>

</body>
</html>