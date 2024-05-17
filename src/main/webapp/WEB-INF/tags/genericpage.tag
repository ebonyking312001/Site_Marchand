<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>

<html>
<head>
<style type="text/css">
.cart {
	display: flex;
	flex-direction: column;
}

.cart-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ddd;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

.cart-header h2 {
	margin: 0;
	font-size: 24px;
}

.cart-items {
	display: flex;
	flex-direction: column;
}

.cart-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
}

.cart-item img {
	width: 100px;
	height: auto;
	margin-right: 20px;
}

.cart-item-info {
	flex-grow: 1;
}

.cart-item h3 {
	margin: 0;
}

.cart-item p {
	margin: 5px 0;
}

.total {
	margin-top: 20px;
}

.total p {
	margin: 5px 0;
}

.checkout-btn {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
}

.checkout-btn:hover {
	background-color: #0056b3;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

</head>
<body>
	<div id="pageheader">
		<jsp:invoke fragment="header" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="pagefooter">
		<jsp:invoke fragment="footer" />
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script type="text/JavaScript">
		
	<%@include file="/js/fctxml.js"%>
		
	</script>
</body>
</html>