<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="mobileNav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <div class="overlay-content">
        <a href="<%=request.getContextPath()%>/SearchServlet?searchQuery=">Prodotti</a>
        <a href="<%=request.getContextPath()%>/common/discoverUs.jsp">Chi Siamo</a>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <a href="<%=request.getContextPath()%>common/account.jsp">Il Mio Account</a>
                <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="<%=request.getContextPath()%>/common/login.jsp">Login</a>
            </c:otherwise>
        </c:choose>
        <a href="<%=request.getContextPath()%>/common/cart.jsp">Carrello</a>
    </div>
</div>


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
        
        <div class="search-bar-container">
            <div class="search-bar">
                <form action="<%=request.getContextPath()%>/SearchServlet" method="GET" autocomplete="off">
                    <input type="text" placeholder="Acero..." name="searchQuery" id="search-input" 
                           onkeyup="showSuggestions(this.value, '<%=request.getContextPath()%>')">
                    <button type="submit"><img alt="cerca" src="<%=request.getContextPath()%>/images/search.svg"></button>
                </form>
            </div>
            <div id="suggestions-box"></div>
        </div>
        

        <div class="icons">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="<%=request.getContextPath()%>/AccountServlet">
                        <img src="<%=request.getContextPath()%>/images/login.svg" alt="Il Mio Account">
                    </a>
                    <a href="<%=request.getContextPath()%>/LogoutServlet">
                        <img src="<%=request.getContextPath()%>/images/logout.svg" alt="Logout">
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="<%=request.getContextPath()%>/common/login.jsp">
                        <img src="<%=request.getContextPath()%>/images/login.svg" alt="Login">
                    </a>
                </c:otherwise>
            </c:choose>
            <a href="<%=request.getContextPath()%>/common/cart.jsp">
                <img src="<%=request.getContextPath()%>/images/cart.svg" alt="Cart">
            </a>
        </div>
        <script src="<%=request.getContextPath()%>/scripts/animation.js"></script>
        <script src="<%=request.getContextPath()%>/scripts/ajax.js"></script>
        <div class="hamburger-icon" onclick="openNav()">
            &#9776;
        </div>
    </div>

</header>
