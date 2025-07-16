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
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main class="main-content">
        <div class="account-container">
            <h1 class="page-title">Il Mio Account</h1>
            
            <!-- Sezione 1: Dati del Profilo -->
            <section class="account-section">
                <h2>I Tuoi Dati</h2>
                <div class="form-group">
                    <label>Nome</label>
                    <input type="text" value="<c:out value='${sessionScope.user.nome}'/>" readonly>
                </div>
                <div class="form-group">
                    <label>Cognome</label>
                    <input type="text" value="<c:out value='${sessionScope.user.cognome}'/>" readonly>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" value="<c:out value='${sessionScope.user.email}'/>" readonly>
                </div>
            </section>

            <!-- Sezione 2: Indirizzo di Spedizione -->
            <section class="account-section">
                <h2>Indirizzo di Spedizione</h2>
                <form action="account" method="post">
                    <input type="hidden" name="action" value="updateAddress">
                    <div class="form-group">
                        <label for="via">Via e Numero Civico</label>
                        <input type="text" id="via" name="via" value="<c:out value='${address.via}'/>" placeholder="Es. Via Roma 10" required>
                    </div>
                    <div class="form-group">
                        <label for="citta">Città</label>
                        <input type="text" id="citta" name="citta" value="<c:out value='${address.città}'/>" required>
                    </div>
                    <div class="form-group-inline">
                        <div class="form-group">
                            <label for="provincia">Provincia</label>
                            <input type="text" id="provincia" name="provincia" value="<c:out value='${address.provincia}'/>" required>
                        </div>
                        <div class="form-group">
                            <label for="cap">CAP</label>
                            <input type="text" id="cap" name="cap" value="<c:out value='${address.CAP}'/>" required>
                        </div>
                    </div>
                    <button type="submit" class="btn-primary">Salva Indirizzo</button>
                </form>
            </section>

            <!-- Sezione 3: Metodo di Pagamento -->
            <section class="account-section">
                <h2>Metodo di Pagamento</h2>
                  <form action="account" method="post">
                    <input type="hidden" name="action" value="updatePayMethod">
                    <div class="form-group">
                        <label for="via">Via e Numero Civico</label>
                        <input type="text" id="via" name="via" value="<c:out value='${address.via}'/>" placeholder="Es. Via Roma 10" required>
                    </div>
                    <div class="form-group">
                        <label for="citta">Città</label>
                        <input type="text" id="citta" name="citta" value="<c:out value='${address.città}'/>" required>
                    </div>
                    <div class="form-group-inline">
                        <div class="form-group">
                            <label for="provincia">Provincia</label>
                            <input type="text" id="provincia" name="provincia" value="<c:out value='${address.provincia}'/>" required>
                        </div>
                        <div class="form-group">
                            <label for="cap">CAP</label>
                            <input type="text" id="cap" name="cap" value="<c:out value='${address.CAP}'/>" required>
                        </div>
                    </div>
                    <button type="submit" class="btn-primary">Salva Pagamento</button>
                </form>
            </section>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
