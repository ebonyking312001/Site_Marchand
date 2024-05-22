<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Catalogue des Articles</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
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
					<th>Catégorie</th>
					<th>TypeProduit</th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th><select id="categoryFilter" class="form-control">
							<option value="">Toutes les catégories</option>
							<c:forEach var="category" items="${categories}">
								<option value="${category}">${category}</option>
							</c:forEach>
					</select></th>
					<th></th>
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
						<td>${article.getNomCategorie()}</td>
						<td>${article.idTypeProduit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="importCSV" method="POST" enctype="multipart/form-data"
			class="mt-4">
			<div class="form-group">
				<label for="file">Importer un fichier CSV</label> <input type="file"
					class="form-control-file" id="file" name="file" accept=".csv"
					required>
			</div>
			<button type="submit" class="btn btn-primary">Importer</button>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>

	<script>
		$(document).ready(function() {
			$('#tableArticles').DataTable({

				"paging" : true,
				"pagingType" : "simple_numbers",
				"pageLength" : 25,
				"ordering" : false,
				"searching" : true,
				"scrollX" : true,
				"scrollY" : "400px",
				"scrollCollapse" : true,
				"autoWidth" : true
			});
		});
	</script>

	<%
	if (request.getAttribute("articleExists") != null && (boolean) request.getAttribute("articleExists")) {
	%>
	<script type="text/javascript">
		alert("Impossible d'ajouter l'article car il est déjà présent en base de données.");
	</script>
	<%
	}
	%>


	<script>
		// Recuérer la liste déroulante des catégories
		const categoryFilter = document.getElementById('categoryFilter');

		// Ajouter un gestionnaire d'événements onchange
		categoryFilter.addEventListener('change', function() {
			const selectedCategory = categoryFilter.value; // Récupérer la catégorie sélectionnée

			// Parcourir toutes les lignes du tableau
			const tableRows = document
					.querySelectorAll('#tableArticles tbody tr');
			tableRows.forEach(function(row) {
				const categoryCell = row.querySelector('td:nth-child(14)'); // Cellule de la catégorie (14ème colonne)
				const category = categoryCell.textContent.trim(); // Récupérer le texte de la cellule de la catégorie

				if (selectedCategory === '' || category === selectedCategory) {
					row.style.display = '';
				} else {
					// Masquer la ligne si la catégorie ne correspond pas à la catégorie sélectionnée
					row.style.display = 'none';
				}
			});
		});
	</script>
</body>
</html>
