<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Il Tuo Carrello - RootedTree</title>
<link rel="stylesheet" href="./styles/index.css">
<link rel="stylesheet" href="./styles/navbar.css">
<link rel="stylesheet" href="./styles/footer.css">
<link rel="stylesheet" href="./styles/cart.css">
<%-- Nuovo CSS per il carrello --%>
</head>
<body>
	<%@ include file="navbar.jsp"%>

	<main class="main-content">
		<div class="page-container">
			<h1 class="page-title">Il Tuo Carrello</h1>

			<c:choose>
				<c:when test="${not empty cart && not empty cart.items}">
					<div class="cart-grid">

						<div class="cart-header">Prodotto</div>
						<div class="cart-header">Prezzo</div>
						<div class="cart-header">Quantità</div>
						<div class="cart-header">Subtotale</div>
						<div class="cart-header"></div>


						<c:forEach var="item" items="${cart.items}">
							<div class="cart-item-image">
								<img src="ProductImageServlet?id=${item.product.id}"
									alt="${item.product.nome}"> <span><c:out
										value="${item.product.nome}" /></span>
							</div>
							<div class="cart-item-price">
								<fmt:formatNumber value="${item.product.prezzo}" type="currency"
									currencySymbol="€ " />
							</div>


							<div class="cart-item-quantity" data-label="Quantità">
								<form action="CartServlet" method="post" class="quantity-form">
									<input type="hidden" name="action" value="update"> <input
										type="hidden" name="productId" value="${item.product.id}">
									<input type="number" name="quantity" value="${item.quantity}"
										min="1" max="${item.product.disponibilità}"
										class="quantity-input">
									<button type="submit" class="btn-update">Aggiorna</button>
								</form>
							</div>


							<div class="cart-item-subtotal">
								<fmt:formatNumber value="${item.subtotal}" type="currency"
									currencySymbol="€ " />
							</div>
							<div class="cart-item-remove">
								<form action="CartServlet" method="post">
									<input type="hidden" name="action" value="remove"> <input
										type="hidden" name="productId" value="${item.product.id}">
									<button type="submit" class="btn-remove">Rimuovi</button>
								</form>
							</div>
						</c:forEach>
					</div>

					<div class="cart-summary">
						<h2>Riepilogo Ordine</h2>
						<div class="summary-line">
							<span>Totale Parziale</span> <span><fmt:formatNumber
									value="${cart.total}" type="currency" currencySymbol="€ " /></span>
						</div>
						<div class="summary-line total">
							<span>Totale</span> <span><fmt:formatNumber
									value="${cart.total}" type="currency" currencySymbol="€ " /></span>
						</div>
						<a href="checkout.jsp" class="btn-checkout">Procedi all'ordine</a>
					</div>
				</c:when>
				<c:otherwise>
					<p class="empty-cart">Il tuo carrello è vuoto.</p>
					<a href="index.jsp" class="btn-details">Torna allo shopping</a>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<%@ include file="footer.jsp"%>
</body>
</html>