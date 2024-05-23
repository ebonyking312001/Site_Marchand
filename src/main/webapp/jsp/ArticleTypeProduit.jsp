<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des Articles par Type Produit</title>
<style>
    ul {
        list-style-type: none;
        padding: 0;
    }
    .article-container {
        display: flex;
        align-items: center;
        border-bottom: 1px solid #ccc;
        padding: 10px 0;
        margin: 10px 0;
        width: 100%;
        max-width: 600px;
        background-color: #f9f9f9;
    }
    .article-details {
        flex: 1;
        padding: 0 10px;
    }
    .article-details h2 {
        margin: 0;
    }
    .article-details p {
        margin: 5px 0;
    }
    .article-image {
        max-width: 100px;
        margin-right: 20px;
    }
    .quantity-input {
        display: flex;
        align-items: center;
    }
    .quantity-input input {
        width: 50px;
        text-align: center;
    }
    .choose-button {
        margin-left: 10px;
        padding: 5px 10px;
        cursor: pointer;
    }
</style>
</head>
<body>
    <h1>Liste des Articles du type "${nomTypeProduit}"</h1>
    <ul>
        <c:forEach var="article" items="${articles}">
            <li>
                <div class="article-container">
                    <img src="${article.vignetteArticle}" alt="Vignette" class="article-image">
                    <div class="article-details">
                        <h2>${article.libelleArticle}</h2>
                        <p>Prix unitaire: ${article.prixUnitaireArticle} €</p>
                        <p>Marque: ${article.marque}</p>
                        <p>Promo: ${article.promoArticle}</p>
                    </div>
                    <div class="quantity-input">
                        <input type="number" value="1" min="1">
                        <button class="choose-button">Choisir</button>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
