<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Non Trovata - RootedTree</title>
    <link rel="stylesheet" href="../styles/index.css">
    <link rel="stylesheet" href="../styles/navbar.css">
    <link rel="stylesheet" href="../styles/footer.css">
    <link rel="stylesheet" href="../styles/error-page.css"> <%-- Nuovo file CSS --%>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main class="main-content">
        <div class="error-container">
            <div class="error-code">404</div>
            <h1 class="error-title">Pagina Non Trovata</h1>
            <p class="error-message">
                La pagina che stai cercando non esiste o è stata spostata.
                Forse un piccolo bonsai si è perso lungo la via.
            </p>
            <a href="index.jsp" class="btn-primary">Torna alla Home</a>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
