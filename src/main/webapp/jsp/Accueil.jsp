<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Article"
	import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Page d'accueil</h1>

	<table border="1">
		<tr>
			<th>Vignette</th>
			<th>Libelle</th>
			<th>Promotion</th>
			<th>Prix au Kg</th>
		</tr>

		<c:forEach var="art" items="${listeArt}">
			<tr>
				<td><a href="?idArticle=${art.EAN}">${art.vignetteArticle}</a></td>
				<td><a href="?idArticle=${art.EAN}">${art.libelleArticle}</a></td>
				<td>${art.promoArticle}</td>
				<td>${art.prixKgArticle}</td>
				<td><a href="ServletPanier?idArticle=${art.EAN}">Ajouter au panier</a></td>
			</tr>
		</c:forEach>

	</table>
	<br>

</body>
</html>