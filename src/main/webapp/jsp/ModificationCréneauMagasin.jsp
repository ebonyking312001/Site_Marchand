<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier Commande</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        select, button {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: #fff;
            box-sizing: border-box;
        }
        option {
            padding: 10px;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .date-retrait {
            text-align: center;
            font-size: 18px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Modifier Commande "${commande.idCommande}"</h1>
        <form id="modifierForm" action="ModifierCommande" method="post">
            <input type="hidden" name="idCommande" value="${commande.idCommande}" />
            <div class="date-retrait">${commande.getDateRetrait()}</div>
            <label for="idMagasin">Magasin de retrait :</label>
            <select name="idMagasin" id="idMagasin">
                <c:forEach var="magasin" items="${magasins}">
                    <option value="${magasin.idMagasin}" ${magasin.nomMagasin == nomMagasinCommande ? 'selected' : ''}>
                        ${magasin.nomMagasin}
                    </option>
                </c:forEach>
            </select>

            <label for="idCreneau">Créneau de retrait :</label>
            <select name="idCreneau" id="idCreneau">
                <c:forEach var="creneau" items="${creneaux}">
                    <option value="${creneau.idCreneau}" ${creneau.debutCreneau == DebutCreneauCommande && creneau.finCreneau == FinCreneauCommande ? 'selected' : ''}>
                        ${creneau.debutCreneau} - ${creneau.finCreneau}
                    </option>
                </c:forEach>
            </select>
            
            <button type="button" onclick="confirmModification()">Modifier</button>
        </form>
    </div>

    <script>
        function confirmModification() {
            if (confirm("Êtes-vous sûr de vouloir modifier le créneau et le magasin de retrait ?")) {
                document.getElementById("modifierForm").submit();
            }
        }
    </script>
</body>
</html>
