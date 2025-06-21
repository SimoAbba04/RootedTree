<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>RootedTree</title>
  <link rel="stylesheet" href="./styles/navbar.css">
</head>
<body>

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

</body>
