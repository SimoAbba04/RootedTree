



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.LinkedList, java.util.List, java.util.Arrays, java.util.Collection, it.unisa.model.ProductDAO, it.unisa.model.ProductDTO, javax.sql.DataSource" %>

<%	ServletContext ct = getServletContext();
	DataSource ds =(DataSource) ct.getAttribute("DataSource");
	ProductDAO product = new ProductDAO(ds);
	Collection <ProductDTO> products = new LinkedList<ProductDTO>();
	products = product.doRetrieveAll("");
	int size = products.size();
%>


<div class="slideshow-container">
	<div class="mySlides fade">
		<a href = "#"> <img src="./images/immagine1.jpg" alt="Chi Siamo"></a>
	</div>
	<div class="mySlides fade">
		<a href="#"><img src="./images/immagine1.jpg"></a>
	</div>
	<div class="mySlides fade">
		<a href="#"><img src="./images/immagine1.jpg"></a>
	</div>
	<a class="prev" onclick="plusSlides(-1)">&#10094</a>
	<a class="next" onclick="plusSlides(1)">&#10095</a>
	<script src="./scripts/slider.js"></script>
</div>
<div class ="product-grid">
	<



</div>
