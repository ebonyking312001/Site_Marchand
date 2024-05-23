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
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
    }
    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }
    .commande {
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        margin-bottom: 20px;
    }
    .commande-info {
        margin-bottom: 10px;
    }
    .commande-info span {
        font-weight: bold;
    }
    .modifier-btn {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 8px 15px;
        text-decoration: none;
    }
    .modifier-btn:hover {
        background-color: #0056b3;
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
            <div class="container mt-5">
                <h1 class="mb-4">Mes Commandes</h1>
                <c:forEach var="commande" items="${commandes}">
    <div class="commande">
        <div class="commande-info">
            <span>ID Commande:</span> ${commande.idCommande}
        </div>
        <div class="commande-info">
            <span>Date de Retrait:</span> ${commande.dateRetrait}
        </div>
        <div class="commande-info">
            <span>État de la Commande:</span> ${commande.etatCommande}
        </div>
        <div class="commande-info">
            <span>Nom Magasin de retrait:</span> ${commande.getNomMagasin()}
        </div>
        <div class="commande-info">
            <span>Début du créneau:</span> ${commande.getDebutCreneau()}
        </div>
        <div class="commande-info">
            <span>Fin du créneau:</span> ${commande.getFinCreneau()}
        </div>
        <!-- Utilisation de flexbox pour aligner le bouton à droite -->
        <div style="display: flex; justify-content: flex-end;">
            <a href="ModifierCommande?id=${commande.idCommande}" class="modifier-btn">Modifier</a>
        </div>
    </div>
</c:forEach>

            </div>
        </jsp:body>
    </t:genericpage>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
