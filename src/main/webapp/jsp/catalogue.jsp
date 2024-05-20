<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catalogue des Articles</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
</head>
<body>
<div class="p-5">
    <h1 class="mb-4">Catalogue des Articles</h1>
    <table class="table table-bordered table-hover" id="tableArticles">
        <thead class="thead-dark">
        <tr>
            <th>EAN</th>
            <th>Vignette</th>
            <th>Prix Unitaire</th>
            <th>Nutriscore</th>
            <th>Libellé</th>
            <th>Poids</th>
            <th>Prix par Kg</th>
            <th>Description Courte</th>
            <th>Description Longue</th>
            <th>Fournisseur</th>
            <th>Marque</th>
            <th>Promo Article</th>
            <th>Id Rayon</th>
            <th>Categorie</th>
            <th>TypeProduit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr>
                <td>${article.EAN}</td>
                <td><img src="${article.vignetteArticle}" width="100"></td>
                <td>${article.prixUnitaireArticle}</td>
                <td>${article.nutriscoreArticle}</td>
                <td>${article.libelleArticle}</td>
                <td>${article.poidsArticle}</td>
                <td>${article.prixKgArticle}</td>
                <td>${article.descriptionCourteArticle}</td>
                <td>${article.descriptionLongueArticle}</td>
                <td>${article.fournisseurArticle}</td>
                <td>${article.marque}</td>
                <td>${article.promoArticle}</td>
                <td>${article.idRayon}</td>
                <td>${article.idCategorie}</td>
                <td>${article.idTypeProduit}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="importCSV" method="POST" enctype="multipart/form-data" class="mt-4">
        <div class="form-group">
            <label for="file">Importer un fichier CSV</label>
            <input type="file" class="form-control-file" id="file" name="file" accept=".csv" required>
        </div>
        <button type="submit" class="btn btn-primary">Importer</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>

<script>
    $(document).ready(function() {
        $('#tableArticles').DataTable({
            // Options de configuration
            "paging": true, // Activation de la pagination
            "ordering": true, // Activation du tri par colonne
            "searching": true // Activation de la recherche
        });
    });
</script>

<% if (request.getAttribute("articleExists") != null && (boolean) request.getAttribute("articleExists")) { %>
        <script type="text/javascript">
            alert("Impossible d'ajouter l'article car il est déjà présent en base de données.");
        </script>
    <% } %>
</body>
</html>
