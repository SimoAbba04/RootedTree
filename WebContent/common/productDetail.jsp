<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${product.nome}" /> - RootedTree</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/show-product.css"> 
</head>
<body>
    <%@ include file="/common/navbar.jsp" %>
    <main class="main-content">
        <c:if test="${not empty product}">
            <div class="detail-container">
                <div class="product-image-container">
                    <img src="<%=request.getContextPath()%>/ProductImageServlet?id=${product.id}" alt="<c:out value='${product.nome}'/>">
                </div>
                <div class="product-details-container">
                    <h1 class="detail-name"><c:out value="${product.nome}" /></h1>
                    <p class="detail-description"><c:out value="${product.descrizione}" /></p>
                    <p class="detail-price">
                        <fmt:setLocale value="it_IT"/>
                        <fmt:formatNumber value="${product.prezzo}" type="currency" currencySymbol="€ "/>
                    </p>
                    <p class="detail-availability">
                        Disponibilità: 
                        <c:choose>
                            <c:when test="${product.disponibilità > 0}">
                                <span class="in-stock">In magazzino (${product.disponibilità} pz.)</span>
                            </c:when>
                            <c:otherwise>
                                <span class="out-of-stock">Esaurito</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <c:if test="${product.disponibilità > 0}">
                        <form action="<%=request.getContextPath()%>/CartServlet" method="post" class="cart-form">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="productId" value="${product.id}">
                            <label for="quantity">Quantità:</label>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.disponibilità}" class="quantity-input">
                            <button type="submit" class="btn-add-to-cart">Aggiungi al Carrello</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </c:if>
    </main>
    <%@ include file="/common/footer.jsp" %>
    <script src="<%=request.getContextPath()%>/scripts/animation.js"></script>
</body>
</html>
