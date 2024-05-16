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
			<th>EAN</th>
			<th>Vignette</th>
			<th>Prix unitaire</th>
			<th>Nutriscore</th>
			<th>Libelle</th>
			<th>Poids</th>
			<th>Prix au Kg</th>
			<th>Description courte</th>
			<th>Description longue</th>
			<th>Fournisseur</th>
			<th>Marque</th>
		</tr>

		<c:forEach var="art" items="${listeArt}">
			<tr>
				<td>${art.EAN}</td>
				<td>${art.vignetteArticle}</td>
				<td>${art.prixUnitaireArticle}</td>
				<td>${art.nutriscoreArticle}</td>
				<td>${art.libelleArticle}</td>
				<td>${art.poidsArticle}</td>
				<td>${art.prixKgArticle}</td>
				<td>${art.descriptionCourteArticle}</td>
				<td>${art.descriptionLongueArticle}</td>
				<td>${art.fournisseurArticle}</td>
				<td>${art.marque}</td>
				<td><a href="ServletPanier?idArticle=${art.EAN}">Ajouter au
						panier</a></td>
			</tr>
		</c:forEach>

	</table>
	<br>

	<p>Cliquer pour revenir à la page d'accueil</p>

</body>
</html>