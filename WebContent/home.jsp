



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page
	import="java.util.LinkedList, java.util.List, java.util.Arrays, java.util.Collection, it.unisa.model.ProductDAO, it.unisa.model.ProductDTO, javax.sql.DataSource"%>

<%
ServletContext ct = getServletContext();
DataSource ds = (DataSource) ct.getAttribute("DataSource");
ProductDAO product = new ProductDAO(ds);
Collection<ProductDTO> products = new LinkedList<ProductDTO>();
products = product.doRetrieveLastFourItem();
pageContext.setAttribute("products", products);
%>


<div class="slideshow-container">
	<div class="mySlides fade">
		<a href="#"> <img src="./images/immagine1.jpg" alt="Chi Siamo"></a>
	</div>
	<div class="mySlides fade">
		<a href="#"><img src="./images/immagine1.jpg"></a>
	</div>
	<div class="mySlides fade">
		<a href="#"><img src="./images/immagine1.jpg"></a>
	</div>
	<a class="prev" onclick="plusSlides(-1)">&#10094</a> <a class="next"
		onclick="plusSlides(1)">&#10095</a>
	<script src="./scripts/slider.js"></script>
</div>
<div class="page-container">
	<h2 class="section-title">Novità</h2>
	<c:choose>
		<c:when test="${not empty products}">
			<div class="product-grid">
				<c:forEach var="product" items="${products}">
					<div class="product-card">
						<a href="ProductDetailServlet?id=${product.id}"> <img
							src="ProductImageServlet?id=${product.id}"
							alt="<c:out value='${product.nome}'/>" class="product-image">
						</a>
						<div class="product-info">
							<h3 class="product-name">
								<c:out value="${product.nome}" />
							</h3>
							<p class="product-price">
								<fmt:setLocale value="it_IT" />
								<fmt:formatNumber value="${product.prezzo}" type="currency"
									currencySymbol="&euro" />
							</p>
							<a href="ProductDetailServlet?id=${product.id}"
								class="btn-details">Vedi Dettagli</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p class="no-results">Nessun prodotto in evidenza.</p>
		</c:otherwise>
	</c:choose>
</div>

<div class="page-container">
	<h2 class="section-title">Esplora le Categorie</h2>

	<div class="category-container">

		<div class="category-item">
			<a href="SearchServlet?searchQuery=vaso" class="category-circle">
				<img src="./images/1.png"
				alt="Categoria Vasi">
			</a>
			<div class="category-label">Vasi</div>
		</div>

		<div id="translated-item" class="category-item">
			<a href="SearchServlet?searchQuery=bonsai" class="category-circle">
				<img src="./images/1.png"
				alt="Categoria Bonsai">
			</a>
			<div class="category-label">Bonsai</div>
		</div>

		<div class="category-item">
			<a href="SearchServlet?searchQuery=cura" class="category-circle">
				<img src="./images/1.png"
				alt="Categoria Cura">
			</a>
			<div class="category-label">Cura</div>
		</div>

	</div>
</div>
