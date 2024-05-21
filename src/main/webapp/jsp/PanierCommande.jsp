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
	<style>
		.valid-row {
			background-color: green;
	        color: white;
		}
	     .selected {
	         background-color: green;
	         color: white;
	     }
	     tr[ligne-cmd-ean] {
            cursor: pointer;
        }
	 </style>
	 <script>
	     document.addEventListener('DOMContentLoaded', (event) => {
	         let selectedEANs = [];
	
	         document.querySelectorAll('tr[ligne-cmd-ean]').forEach(row => {
	             row.addEventListener('click', () => {
	                 const ean = row.getAttribute('ligne-cmd-ean');
	                 if (row.classList.contains('selected')) {
	                     row.classList.remove('selected');
	                     selectedEANs = selectedEANs.filter(item => item !== ean);
	                 } else {
	                     row.classList.add('selected');
	                     selectedEANs.push(ean);
	                 }
	             });
	         });
	
	         document.getElementById('submitBtn').addEventListener('click', () => {
	             const form = document.getElementById('eanForm');
	             const input = document.createElement('input');
	             input.type = 'hidden';
	             input.name = 'selectedEANs';
	             input.value = JSON.stringify(selectedEANs);
	             form.appendChild(input);
	             form.submit();
	         });
	     });
	 </script>
</head>
<body>
	<div class="container mt-5">
		<h1 class="mb-4">
			<a href="ServletPreparation?action=afficherCommandes"
				class="btn btn-primary">Afficher les commandes à préparer</a>
		</h1>
		<%
		ArrayList<Commande> cEnCours = (ArrayList<Commande>) request.getAttribute("cEnCours");
		ArrayList<ArticleCommande> cmdD = (ArrayList<ArticleCommande>) request.getAttribute("cmdD");
		// afficher details de commande
		if (cmdD != null) {
			cEnCours = null;
		%>
		<h2>Commande actuelle : ${ean}</h2>
		<form id="eanForm" action="ServletPreparation" method="POST">
			<input type="hidden" name="cmdId" value="${ean}">
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					
					<tr>
						<th>EAN</th>
						<th>LibelleArticle</th>
						<th>Marque</th>
						<th>IdRayon</th>
						<th>Qte</th>
						<th>PrixUnitaireArticle</th>
						<th>PoidsArticle</th>
						<th>Validée</th>
					</tr>
				</thead>
				<tbody>
						<%
						for (ArticleCommande ac : cmdD) {
						%>
						<tr ligne-cmd-ean=<%=ac.getEAN()%> class="<%= ac.isEstValide() ? "valid-row" : "" %>">
							<td><%=ac.getEAN()%></td>
							<td><%=ac.getLibelleArticle()%></td>
							<td><%=ac.getMarque()%></td>
							<td><%=ac.getIdRayon()%></td>
							<td><%=ac.getQteCom()%></td>
							<td><%=ac.getPrixUnitaireArticle()%></td>
							<td><%=ac.getPoidsArticle()%></td>
							<td><%= ac.isEstValide() ? "✅" : "❎" %></td>
						</tr>
	    		</form>
				<%
				}
				%>
				<button type="button" id="submitBtn">prêt</button>
				<%// afficher commandes en cours
				} else if (cEnCours != null) {
				%>
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
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
