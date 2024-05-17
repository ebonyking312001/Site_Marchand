<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Article"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Panier</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	max-width: 800px;
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

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
</head>
<body>
	<div class="container">
		<div class="cart">
			<div class="cart-header">
				<h2>Panier</h2>
			</div>
			<div class="cart-items">


				<div class="cart-items">
					<c:forEach var="article" items="${sessionScope.articleList}">
						<div class="cart-item">
							<img src="https://via.placeholder.com/150" alt="Product">
							<div class="cart-item-info">
								<h3>${article.libelleArticle}</h3>
								<p>${article.EAN}</p>
								<p>${article.poidsArticle}</p>
							</div>
							<div class="cart-item-quantity">
								<input type="number" value="${article.quantite}" min="0">
							</div>
						</div>
					</c:forEach>

				</div>

				Ajouter d'autres éléments du panier ici
			</div>
			<div class="total">
				<p>Total: $19.99</p>
			</div>
			<button class="checkout-btn">Passer la Commande</button>
		</div>
	</div>

	<script type="text/JavaScript" src="js/fctxml.js"></script>
</body>
</html>
