<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Commande"%>
<%@ page import="model.ArticleCommande"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panier Commande</title>
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h1 class="mb-4">
			<a href="ServletPreparation?action=afficherCommandes"
				class="btn btn-primary">Afficher les commandes à préparer</a>
		</h1>

		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<%
				ArrayList<Commande> cEnCours = (ArrayList<Commande>) request.getAttribute("cEnCours");
				ArrayList<ArticleCommande> cmdD = (ArrayList<ArticleCommande>) request.getAttribute("cmdD");
				if (cmdD != null) {
					cEnCours = null;
				%>
				<tr>
					<th>EAN</th>
					<th>LibelleArticle</th>
					<th>Marque</th>
					<th>IdRayon</th>
					<th>Qte</th>
					<th>PrixUnitaireArticle</th>
					<th>PoidsArticle</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (ArticleCommande ac : cmdD) {
				%>
				<tr>
					<td><%=ac.getEAN()%></td>
					<td><%=ac.getLibelleArticle()%></td>
					<td><%=ac.getMarque()%></td>
					<td><%=ac.getIdRayon()%></td>
					<td><%=ac.getQteCom()%></td>
					<td><%=ac.getPrixUnitaireArticle()%></td>
					<td><%=ac.getPoidsArticle()%></td>
				</tr>
				<%
				}
				} else if (cEnCours != null) {
				%>
				<tr>
					<th>DateRetrait</th>
					<th>EtatCommande</th>
					<th>IdCommande</th>
					<th>IdMagasin</th>
					<th>IdUtilisateur</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Commande c : cEnCours) {
				%>
				<tr>
					<td><%=c.getDateRetrait()%></td>
					<td><%=c.getEtatCommande()%></td>
					<td><a
						href="ServletPreparation?action=<%=c.getIdCommande()%>"><%=c.getIdCommande()%></a></td>
					<td><%=c.getIdMagasin()%></td>
					<td><%=c.getIdUtilisateur()%></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
