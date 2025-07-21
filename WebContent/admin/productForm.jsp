<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Gestione Prodotto</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/adminDashboard.css">
</head>
<body>
    <div class="admin-table-container">
        <h1>
            <c:choose>
                <c:when test="${not empty product}">Modifica Prodotto</c:when>
                <c:otherwise>Aggiungi Nuovo Prodotto</c:otherwise>
            </c:choose>
        </h1>
        
        <form action="<%=request.getContextPath()%>/admin/productControlServlet" method="post" enctype="multipart/form-data">
            <c:if test="${not empty product}">
                <input type="hidden" name="id" value="${product.id}">
            </c:if>

            <div class="form-group">
                <label for="nome">Nome Prodotto</label>
                <input type="text" id="nome" name="nome" value="<c:out value='${product.nome}'/>" required>
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <textarea id="descrizione" name="descrizione" rows="4" required><c:out value='${product.descrizione}'/></textarea>
            </div>
            <div class="form-group">
                <label for="prezzo">Prezzo (€)</label>
                <input type="number" id="prezzo" name="prezzo" value="${product.prezzo}" required>
            </div>
            <div class="form-group">
                <label for="stock">Stock (Quantità)</label>
                <input type="number" id="stock" name="stock" value="${product.disponibilità}" required>
            </div>
            <div class="form-group">
                <label for="categoria">Categoria</label>
                <select id="categoria" name="categoria" required>
                    <option value="bonsai" ${product.categoria == 'bonsai' ? 'selected' : ''}>Bonsai</option>
                    <option value="vaso" ${product.categoria == 'vaso' ? 'selected' : ''}>Vaso</option>
                    <option value="cura" ${product.categoria == 'cura' ? 'selected' : ''}>Cura</option>
                </select>
            </div>
            <div class="form-group">
                <label for="immagine">Immagine Prodotto</label>
                <input type="file" id="immagine" name="immagine" accept="image/png, image/jpeg">
                <c:if test="${not empty product}">
                    <p class="form-hint">Lasciare vuoto per non modificare l'immagine corrente.</p>
                </c:if>
            </div>

            <div class="admin-actions">
                <input type="submit" class="admin-button">Salva Prodotto</input>
                <a href="<%=request.getContextPath()%>/SearchServlet?source=admin" class="admin-button">Annulla</a>
            </div>
        </form>
    </div>
</body>
</html>