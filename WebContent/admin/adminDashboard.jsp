<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pannello Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/adminDashboard.css">
</head>
<body>
    <div class="admin-container">
        <h1>Benvenuto, <c:out value="${sessionScope.user.nome}" />!</h1>
        <p>Seleziona un'operazione dal pannello di controllo.</p>
        
        <div class="admin-actions">
            <a href="<%=request.getContextPath()%>/admin/product-management" class="admin-button">Gestisci Catalogo Prodotti</a>
            <a href="<%=request.getContextPath()%>/admin/order-view" class="admin-button">Visualizza Ordini Clienti</a>
            <a href="<%=request.getContextPath()%>/LogoutServlet" class="admin-button logout">Logout</a>
        </div>
    </div>
</body>
</html>
