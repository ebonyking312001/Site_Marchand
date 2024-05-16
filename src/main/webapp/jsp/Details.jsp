<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Article"
	import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Label here</title>
</head>
<body>
	<h1>${article.libelleArticle}</h1>
	<div>${article.vignetteArticle}</div>

	<table border="1">
		<tr>
			<th>EAN</th>
			<th>Prix unitaire</th>
			<th>Nutriscore</th>
			<th>Poids</th>
			<th>Prix au Kg</th>
			<th>Description courte</th>
			<th>Description longue</th>
			<th>Fournisseur</th>
			<th>Marque</th>
		</tr>

		<tr>
			<td>${article.EAN}</td>
			<td>${article.prixUnitaireArticle}</td>
			<td>${article.nutriscoreArticle}</td>
			<td>${article.poidsArticle}</td>
			<td>${article.prixKgArticle}</td>
			<td>${article.descriptionCourteArticle}</td>
			<td>${article.descriptionLongueArticle}</td>
			<td>${article.fournisseurArticle}</td>
			<td>${article.marque}</td>
		</tr>

	</table>
	<br>

</body>
</html>