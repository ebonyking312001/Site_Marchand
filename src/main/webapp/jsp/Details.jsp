<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Article"
	import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Details</title>
</head>
<body>
	<h1>${article.libelleArticle}</h1>
	<div>${article.vignetteArticle}</div>
	<br>
	<table border="1">
	    <tr>
	        <th>EAN</th>
	        <td>${article.EAN}</td>
	    </tr>
	    <tr>
	        <th>Prix unitaire</th>
	        <td>${article.prixUnitaireArticle}</td>
	    </tr>
	    <tr>
	        <th>Nutriscore</th>
	        <td>${article.nutriscoreArticle}</td>
	    </tr>
	    <tr>
	        <th>Poids</th>
	        <td>${article.poidsArticle}</td>
	    </tr>
	    <tr>
	        <th>Prix au Kg</th>
	        <td>${article.prixKgArticle}</td>
	    </tr>
	    <tr>
	        <th>Description courte</th>
	        <td>${article.descriptionCourteArticle}</td>
	    </tr>
	    <tr>
	        <th>Description longue</th>
	        <td>${article.descriptionLongueArticle}</td>
	    </tr>
	    <tr>
	        <th>Fournisseur</th>
	        <td>${article.fournisseurArticle}</td>
	    </tr>
	    <tr>
	        <th>Marque</th>
	        <td>${article.marque}</td>
	    </tr>
	</table>
	<br>

</body>
</html>