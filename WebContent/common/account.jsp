<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Il Mio Account - RootedTree</title>
<link rel="stylesheet" href="./styles/index.css">
<link rel="stylesheet" href="./styles/navbar.css">
<link rel="stylesheet" href="./styles/footer.css">
<link rel="stylesheet" href="./styles/account.css">
<link rel="stylesheet" href="./styles/notification.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main class="main-content">
        <div class="account-container">
            <h1 class="page-title">Il Mio Account</h1>
            
            <c:if test="${not empty successMessage}"><div class="notification success">${successMessage}</div></c:if>
            <c:if test="${not empty errorMessage}"><div class="notification error">${errorMessage}</div></c:if>

            <section class="account-section">
                <h2>I Tuoi Dati</h2>
                <form id="profileForm" action="account" method="post" onsubmit="return validateProfileForm()">
                    <input type="hidden" name="action" value="updateProfile">
                    <div class="form-group">
                        <label for="Fnome">Nome</label>
                        <input type="text" id="Fnome" name="nome" value="<c:out value='${sessionScope.user.nome}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, nameOrLastnameErrorMessage)" pattern="^[A-Z][a-zA-Z\s]*$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="Fcognome">Cognome</label>
                        <input type="text" id="Fcognome" name="cognome" value="<c:out value='${sessionScope.user.cognome}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, nameOrLastnameErrorMessage)" pattern="^[A-Z][a-zA-Z\s]*$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="Femail">Email</label>
                        <input type="email" id="Femail" name="email" value="<c:out value='${sessionScope.user.email}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, emailErrorMessage)" pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="FdataNascita">Data di nascita</label>
                        <input type="date" id="FdataNascita" name="dataNascita" value="${sessionScope.user.dataNascita}" required 
                               oninput="validateAge(this, this.nextElementSibling)">
                        <div class="error"></div>
                    </div>
                    <h3 class="section-subtitle">Cambia Password</h3>
                    <p class="form-hint">Lascia i campi vuoti se non vuoi modificare la password.</p>
                    <div class="form-group">
                        <label for="FoldPassword">Vecchia Password</label>
                        <input type="password" id="FoldPassword" name="oldPassword">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="FnewPassword">Nuova Password</label>
                        <input type="password" id="FnewPassword" name="newPassword" 
                               oninput="validaElem(this, this.nextElementSibling, passwordErrorMessage)" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$">
                        <div class="error"></div>
                    </div>
                    <button type="submit" class="btn-primary">Salva Dati Profilo</button>
                </form>
            </section>

            <section class="account-section">
                <h2>Indirizzo di Spedizione</h2>
                <form id="addressForm" action="account" method="post" onsubmit="return validateAddressForm()">
                    <input type="hidden" name="action" value="updateAddress">
                    <input type="hidden" name="addressId" value="${address.id}">
                    
                    <div class="form-group">
                        <label for="stato">Stato</label>
                        <input type="text" id="stato" name="stato" value="<c:out value='${address.stato}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, stateErrorMessage)" pattern="^[A-Z][a-zA-Z\s']*$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="via">Via e Numero Civico</label>
                        <input type="text" id="via" name="via" value="<c:out value='${address.via}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, streetErrorMessage)" pattern="^[A-Za-z\s.,'0-9/]+$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="citta">Città</label>
                        <input type="text" id="citta" name="citta" value="<c:out value='${address.città}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, cityErrorMessage)" pattern="^[A-Z][a-zA-Z\s']*$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group-inline">
                        <div class="form-group">
                            <label for="provincia">Provincia</label>
                            <input type="text" id="provincia" name="provincia" value="<c:out value='${address.provincia}'/>" required 
                                   oninput="validaElem(this, this.nextElementSibling, provinceErrorMessage)" pattern="^[A-Z]{2}$">
                            <div class="error"></div>
                        </div>
                        <div class="form-group">
                            <label for="cap">CAP</label>
                            <input type="text" id="cap" name="cap" value="<c:out value='${address.CAP}'/>" required 
                                   oninput="validaElem(this, this.nextElementSibling, capErrorMessage)" pattern="^\d{5}$">
                            <div class="error"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="numeroTelefono">Numero di Telefono (opzionale)</label>
                        <input type="tel" id="numeroTelefono" name="numeroTelefono" value="<c:out value='${address.numeroTelefono}'/>" 
                               oninput="validaElem(this, this.nextElementSibling, phoneErrorMessage)" pattern="^\d{10}$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="descrizione">Descrizione (opzionale)</label>
                        <input type="text" id="descrizione" name="descrizione" value="<c:out value='${address.descrizione}'/>" placeholder="Es. Citofonare a Rossi">
                    </div>
                    <button type="submit" class="btn-primary">Salva Indirizzo</button>
                </form>
            </section>

            <section class="account-section">
                <h2>Metodo di Pagamento</h2>
                <form id="paymentForm" action="account" method="post" onsubmit="return validatePaymentForm()">
                    <input type="hidden" name="action" value="updatePayment">
                    <input type="hidden" name="paymentId" value="${payment.id}">
                    <div class="form-group">
                        <label for="titolare">Titolare Carta</label>
                        <input type="text" id="titolare" name="titolare" value="<c:out value='${payment.titolare}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, cardHolderErrorMessage)" pattern="^[A-Z][a-zA-Z\s]*$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group">
                        <label for="numeroCarta">Numero Carta</label>
                        <input type="text" id="numeroCarta" name="numeroCarta" value="<c:out value='${payment.numeroCarta}'/>" required 
                               oninput="validaElem(this, this.nextElementSibling, cardNumberErrorMessage)" pattern="^\d{16}$">
                        <div class="error"></div>
                    </div>
                    <div class="form-group-inline">
                        <div class="form-group">
                            <label for="dataScadenza">Data Scadenza</label>
                            <input type="date" id="dataScadenza" name="dataScadenza" value="${payment.dataScadenza}" required 
                                   oninput="validateExpiryDateField(this)">
                            <div class="error"></div>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV</label>
                            <input type="password" id="cvv" name="cvv" value="<c:out value='${payment.codiceSicurezza}'/>" required 
                                   oninput="validaElem(this, this.nextElementSibling, cvvErrorMessage)" pattern="^\d{3}$">
                            <div class="error"></div>
                        </div>
                    </div>
                    <button type="submit" class="btn-primary">Salva Metodo Pagamento</button>
                </form>
            </section>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
    <script src="./scripts/formValidation.js"></script>
</body>
</html>
