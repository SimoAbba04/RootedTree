<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Il Mio Account - Rooted Tree</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/account.css">
</head>
<body>
    <%@ include file="navbar.jsp"%>

    <div class="account-container">
        <aside class="account-sidebar">
            <h3>Il mio account</h3>
            <nav>
                <ul>
                    <li><a href="#">Pannello di controllo</a></li>
                    <li class="active"><a href="#">Informazioni account</a></li>
                    <li><a href="#">Indirizzo di fatturazione</a></li>
                    <li><a href="#">Indirizzo di spedizione</a></li>
                    <li><a href="#">I miei ordini</a></li>
                    <li><a href="#">La mia wishlist</a></li>
                    <li><a href="#">Il mio carrello</a></li>
                    <li><a href="LogoutServlet">Esci</a></li>
                </ul>
            </nav>
        </aside>

        <main class="account-content">
            <h1>Modifica Informazioni Account</h1>

            <form action="UpdateAccount" method="post" class="account-form">
                <section class="form-section">
                    <h2>Informazioni account</h2>
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="nome">Nome: *</label>
                            <input type="text" id="nome" name="nome" value="${user.nome}" required>
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome: *</label>
                            <input type="text" id="cognome" name="cognome" value="${user.cognome}" required>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail: *</label>
                            <input type="email" id="email" name="email" value="${user.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Cellulare:</label>
                            <input type="tel" id="telefono" name="telefono" value="${user.telefono}">
                        </div>
                    </div>
                </section>
                
                <section class="form-section">
                    <h2>Cambia password</h2>
                    <p>Lasciare i campi vuoti per non modificare la password attuale.</p>
                    <div class="form-grid">
                         <div class="form-group">
                            <label for="current-password">Password attuale: *</label>
                            <input type="password" id="current-password" name="currentPassword">
                        </div>
                        <div class="form-group">
                            <label for="new-password">Nuova password:</label>
                            <input type="password" id="new-password" name="newPassword">
                        </div>
                        <div class="form-group">
                            <label for="confirm-password">Conferma nuova password:</label>
                            <input type="password" id="confirm-password" name="confirmPassword">
                        </div>
                    </div>
                </section>
                
                <div class="form-actions">
                    <span>* Campi obbligatori</span>
                    <button type="submit" class="btn-salva">Salva</button>
                </div>
            </form>
        </main>
    </div>

    <%@ include file="footer.jsp"%>
</body>
</html>