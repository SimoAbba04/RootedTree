<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Catalogo</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/adminDashboard.css">
</head>
<body>
    <div class="admin-table-container">
        <h1>Gestione Catalogo Prodotti</h1>
        <p>Cerca un prodotto per modificarlo o cancellarlo, oppure aggiungine uno nuovo.</p>
        
        <div class="admin-search-bar">
            <form action="<%=request.getContextPath()%>/SearchServlet" method="GET">
                <input type="hidden" name="source" value="admin">
                
                <input type="text" name="searchQuery" placeholder="Cerca prodotto per nome...">
                <button type="submit" class="admin-button">Cerca</button>
            </form>
        </div>
        
        <div class="admin-actions">
            <a href="#" class="admin-button">Aggiungi Nuovo Prodotto</a>
            <a href="<%=request.getContextPath()%>/admin/dashboard" class="admin-button">Torna alla Dashboard</a>
        </div>

        <table class="product-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome Prodotto</th>
                    <th>Prezzo</th>
                    <th>Stock</th>
                    <th>Azioni</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.id}</td>
                        <td><c_out value="${product.nome}" /></td>
                        <td>€ ${product.prezzo}</td>
                        <td>${product.disponibilità}</td>
                        <td class="action-links">
                            <a href="#">Modifica</a> | 
                            <a href="#">Cancella</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>