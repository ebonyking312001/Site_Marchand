<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Liste des Articles par Type Produit</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        color: #333;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    h1 {
        text-align: center;
        color: #444;
        margin-top: 20px;
    }
    ul {
        list-style-type: none;
        padding: 0;
        margin: 20px 0;
        max-width: 800px;
        width: 100%;
    }
    .article-container {
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        border-bottom: 1px solid #ddd;
        padding: 15px;
        background-color: #fff;
        margin-bottom: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .article-image {
        flex-shrink: 0;
        width: 100px;
        height: auto;
        margin-right: 20px;
    }
    .article-details {
        flex-grow: 1;
        padding-right: 15px;
    }
    .article-details h2 {
        margin: 0 0 10px 0;
        font-size: 1.5em;
        color: #222;
    }
    .article-details p {
        margin: 5px 0;
        color: #666;
    }
    .quantity-input {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        justify-content: space-between;
    }
    .quantity-input label {
        margin-bottom: 5px;
        font-size: 0.9em;
        color: #555;
    }
    .quantity-input input[type="number"] {
        width: 60px;
        text-align: center;
        margin-bottom: 10px;
    }
    .choose-button {
        padding: 7px 15px;
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
        border-radius: 3px;
        transition: background-color 0.3s;
    }
    .choose-button:hover {
        background-color: #0056b3;
    }
    .hidden-input {
        display: none;
    }
</style>
</head>
<body>
    <h1>Liste des Articles du type "<c:out value='${nomTypeProduit}'/>"</h1>
    <ul>
        <c:forEach var="article" items="${articles}">
            <form name="ajoutArticle" action="${pageContext.request.contextPath}/CtrlChoixArticleListe" method="get">
                <li>
                    <div class="article-container">
                        <img src="<c:out value='${article.vignetteArticle}'/>" alt="Image de <c:out value='${article.libelleArticle}'/>" class="article-image">
                        <div class="article-details">
                            <h2><c:out value='${article.libelleArticle}'/></h2>
                            <p>Prix unitaire: <c:out value='${article.prixUnitaireArticle}'/> &euro;</p>
                            <p>Marque: <c:out value='${article.marque}'/></p>
                  
                            <input type="hidden" name="idListe" value="<c:out value='${idListe}'/>" class="hidden-input">
                            <input type="hidden" name="idTypeProduit" value="<c:out value='${idTypeProduit}'/>" class="hidden-input">
                            <input type="hidden" name="EAN" value="<c:out value='${article.EAN}'/>" class="hidden-input">
                        </div>
                        <div class="quantity-input">
                            <label for="quantityInput_${article.EAN}">Quantité</label>
                            <input name="quantite" type="number" value="1" min="1" id="quantityInput_${article.EAN}">
                            <button type="submit" name="submitAll" class="choose-button">Choisir</button>
                        </div>
                    </div>
                </li>
            </form>
        </c:forEach>
    </ul>
</body>
</html>
