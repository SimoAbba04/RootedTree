<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordine Confermato - RootedTree</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/error-page.css">
</head>
<body>
    <%@ include file="/common/navbar.jsp" %>

    <main class="main-content">
        <div class="error-container">
            <h1 class="error-title">Ordine Confermato!</h1>
            <p class="error-message">
                Grazie per il tuo acquisto! Il tuo ordine #${sessionScope.orderId} è stato ricevuto e verrà processato al più presto.
            </p>
            <a href="<%=request.getContextPath()%>/common/index.jsp" class="btn-primary">Torna alla Home</a>
        </div>
    </main>

    <%@ include file="/common/footer.jsp" %>
</body>
</html>
