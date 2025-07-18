<header class="navbar">
    <div class="navbar-container">
        <div class="logo">
            <a href="<%=request.getContextPath()%>/common/index.jsp">
                <img src="<%=request.getContextPath()%>/images/logo.svg" alt="RootedTree Logo">
            </a>
        </div>
        <nav class="nav-links">
            <a href="<%=request.getContextPath()%>/SearchServlet?searchQuery=">Prodotti</a>
            <a href="<%=request.getContextPath()%>/common/discoverUs.jsp">Chi siamo</a>
        </nav>
        <div class="search-bar">
       		<form action="<%=request.getContextPath()%>/SearchServlet" method="GET">
        	<input type="text" placeholder="Acero palmato" name="searchQuery">
        	<button type="submit"><img alt="cerca" src="<%=request.getContextPath()%>/images/search.svg"></button>
        	</form>
        </div>
        
        <div class="icons">
            <a href="<%=request.getContextPath()%>/common/login.jsp">
                <img src="<%=request.getContextPath()%>/images/login.svg" alt="Login">
            </a>
            <a href="<%=request.getContextPath()%>/common/cart.jsp">
                <img src="<%=request.getContextPath()%>/images/cart.svg" alt="Cart">
            </a>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/scripts/animation.js"></script>
</header>
