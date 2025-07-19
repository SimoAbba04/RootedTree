<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore del Server - RootedTree</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/error-page.css"> 
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main class="main-content">
        <div class="error-container">
            <div class="error-code">500</div>
            <h1 class="error-title">Oops! Qualcosa è andato storto.</h1>
            <p class="error-message">
                Stiamo riscontrando un problema tecnico. Il nostro team è già al lavoro per potare i rami secchi e sistemare tutto.
            </p>
            <a href="<%=request.getContextPath()%>/common/index.jsp" class="btn-primary">Torna alla Home</a>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
