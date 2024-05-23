<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mes listes de courses</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa;
        }

        .album {
            background-color: #f8f9fa;
        }

        .card {
            border: none;
            transition: transform 0.3s ease-in-out;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card-text {
            font-size: 18px;
        }

        .list-group-item {
            border: none;
        }
    </style>
</head>
<body>
<t:genericpage>
    <jsp:attribute name="header">
        <%@ include file="Header.jsp"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <%@ include file="Footer.jsp"%>
    </jsp:attribute>
    <jsp:body>
        <main>
            <div class="album py-5">
                <div class="container">
                    <h2 class="fw-bold mb-5 text-center">Mes listes de courses</h2>
                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <c:forEach var="liste" items="${listeCourses}">
                            <div class="col">
                                <div class="card shadow-sm">
                                    <div class="card-body">
                                        <h5 class="card-title">${liste.nomListe}</h5>
                                        <hr>
                                        <p class="card-text mb-4">Types de produits :</p>
                                        <div class="list-group mb-4">
                                            <c:forEach var="contenuTypeProduit" items="${liste.contenuTypeProduit}">
                                                <a href="ListeArticlesParTypeProduit/${liste.idListe}/${contenuTypeProduit.idTypeProduit}" class="list-group-item list-group-item-action">${contenuTypeProduit.nomTypeProduit}</a>
                                            </c:forEach>
                                        </div>
                                        <p class="card-text mb-4">Articles :</p>
                                        <ul>
                                            <c:forEach var="contenuArticle" items="${liste.contenuArticle}">
                                                <li>${contenuArticle.libelleArticle} - ${contenuArticle.marque} - ${contenuArticle.quantite}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</t:genericpage>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
