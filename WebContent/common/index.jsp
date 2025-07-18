<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RootedTree - Bonsai Shop</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/rootedTreeFavicon.svg">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navbar.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/home.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/footer.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/result.css">
</head>
<body>
	<%@ include file="/common/navbar.jsp"%>
	<main class="main-content">
		<%@ include file="/common/home.jsp"%>
	</main>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
