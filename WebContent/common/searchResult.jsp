<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risultati Ricerca - RootedTree</title>
    <link rel="stylesheet" href="../styles/index.css">
    <link rel="stylesheet" href="../styles/navbar.css">
    <link rel="stylesheet" href="../styles/footer.css">
    <link rel="stylesheet" href="../styles/result.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <div class="main-content">
        <div class="page-container">
            <h1 class="page-title">
                <c:choose>
                    <c:when test="${not empty pageTitle}">
                      <c:out value="${pageTitle}" />
                    </c:when>
                </c:choose>
            </h1>

            <c:choose>
                <c:when test="${not empty products}">
                    <div class="product-grid">
                        <c:forEach var="product" items="${products}">
                            <div class="product-card">
                                <a href="ProductDetailServlet?id=${product.id}">
                                    <img src="ProductImageServlet?id=${product.id}" alt="<c:out value='${product.nome}'/>" class="product-image">
                                </a>
                                <div class="product-info">
                                    <h3 class="product-name"><c:out value="${product.nome}" /></h3>
                                    <p class="product-price">
                                        <fmt:setLocale value="it_IT"/>
                                        <fmt:formatNumber value="${product.prezzo}" type="currency" currencySymbol="€ "/>
                                    </p>
                                    <a href="ProductDetailServlet?id=${product.id}" class="btn-details">Vedi Dettagli</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="no-results">Nessun prodotto trovato. Prova a cercare un altro termine.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
 
</body>
</html>
