
<header class="navbar">
    <div class="navbar-container">
        <div class="logo">
            <a href="index.jsp">
                <img src="<%=request.getContextPath()%>/images/logo.svg" alt="RootedTree Logo">
            </a>
        </div>
        <nav class="nav-links">
            <a href="products.jsp">Prodotti</a>
            <a href="discoverUs.jsp">Chi siamo</a>
        </nav>
        <div class="search-bar">
       		<form action="cercaProdotto.jsp" method="GET">
        	<input type="text" placeholder="Acero palmato">
        	<button type="submit"><img alt="cerca" src="./images/search.svg"></button>
        	</form>
        </div>
        
        <div class="icons">
            <a href="register.jsp">
                <img src="<%=request.getContextPath()%>/images/login.svg" alt="Login">
            </a>
            <a href="cart.jsp">
                <img src="<%=request.getContextPath()%>/images/cart.svg" alt="Cart">
            </a>
        </div>
    </div>
</header>