<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Article"%>
<%@ page import="bd.ConnectionMySql"%>
<!DOCTYPE html>
<html>
<head>
<title>Catalogue des Articles</title>
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="p-5">
		<h1 class="mb-4">Catalogue des Articles</h1>
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<tr>
					<th>EAN</th>
					<th class="d-none">Vignette</th>
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
				<%
				try {
					ArrayList<Article> articles = ConnectionMySql.afficherArticle();
					for (Article article : articles) {
				%>
				<tr>
					<td><%=article.getEAN()%></td>
					<td class="d-none"><%=article.getVignetteArticle()%></td>
					<td><%=article.getPrixUnitaireArticle()%></td>
					<td><%=article.getNutriscoreArticle()%></td>
					<td><%=article.getLibelleArticle()%></td>
					<td><%=article.getPoidsArticle()%></td>
					<td><%=article.getPrixKgArticle()%></td>
					<td><%=article.getDescriptionCourteArticle()%></td>
					<td><%=article.getDescriptionLongueArticle()%></td>
					<td><%=article.getFournisseurArticle()%></td>
					<td><%=article.getMarque()%></td>
					<td><%=article.getPromoArticle()%></td>
					<td><%=article.getIdRayon()%></td>
					<td><%=article.getIdCategorie()%></td>
					<td><%=article.getIdTypeProduit()%></td>
				</tr>
				<%
				}
				} catch (Exception e) {
				out.println("<p class='text-danger'>Erreur : " + e.getMessage() + "</p>");
				}
				%>
			</tbody>
		</table>

		<!-- Formulaire d'importation -->
		<form action="CtrlGestionMarcServlet" method="POST"
			enctype="multipart/form-data" class="mt-4">
			<div class="form-group">
				<label for="file">Importer un fichier CSV</label> <input type="file"
					class="form-control-file" id="file" name="file" accept=".csv"
					required>
			</div>
			<button type="submit" class="btn btn-primary">Importer</button>
		</form>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
