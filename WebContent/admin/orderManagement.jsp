<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Gestione Ordini</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/adminDashboard.css">
</head>
<body>
	<div class="admin-container">
		<h1>Gestione Ordini Clienti</h1>
		<p>Visualizza e filtra gli ordini effettuati sul sito.</p>

		<div class="filter-section">
			<form action="<%=request.getContextPath()%>/admin/OrderServlet"
				method="GET" class="filter-form">
				<label for="userEmail">Filtra per Email Cliente:</label> <input
					type="email" name="userEmail" id="userEmail"
					placeholder="cliente@email.com">
				<button type="submit" class="admin-button">Cerca</button>
			</form>
			<form action="<%=request.getContextPath()%>/admin/OrderServlet"
				method="GET" class="filter-form">
				<label for="startDate">Da:</label> <input type="date"
					name="startDate" id="startDate"> <label for="endDate">A:</label>
				<input type="date" name="endDate" id="endDate">
				<button type="submit" class="admin-button">Filtra per Data</button>
			</form>
		</div>

		<div class="admin-actions">
			<a href="<%=request.getContextPath()%>/admin/OrderServlet"
				class="admin-button">Mostra Tutti gli Ordini</a> <a
				href="<%=request.getContextPath()%>/admin/adminDashboard.jsp"
				class="admin-button">Torna alla Dashboard</a>
		</div>

		<div class="order-list">
			<c:choose>
				<c:when test="${not empty orders}">
					<c:forEach var="order" items="${orders}">
						<div class="order-item">
							<div class="order-header">
								<span><strong>Ordine #${order.id}</strong> (Cliente ID:
									${order.idAccount})</span> <span>${order.dataOrdine}</span> <span>€
									${order.totale}</span> <span class="order-status">${order.stato}</span>
							</div>
							<div class="order-details">
								<c:forEach var="detail" items="${order.details}">
									<div class="detail-line">
										<span>${detail.qta} x <c:out
												value="${detail.nomeProdotto}" /></span> <span>€
											${detail.prezzoUnitario * detail.qta}</span>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p class="no-results">Nessun ordine trovato con i criteri
						selezionati.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>