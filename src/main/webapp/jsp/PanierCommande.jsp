<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Commande" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panier Commande</title>
</head>
<body>
	<h1><a href="ServletPreparation">Afficher les commandes à préparer</a></h1>
	
	<table border=1>
<tr>
	<th>DateRetrait</th>
	<th>EtatCommande</th>
	<th>IdCommande</th>
	<th>IdMagasin</th>
	<th>IdUtilisateur</th>
</tr>
<% 
ArrayList<Commande> cEnCours = (ArrayList<Commande>)request.getAttribute("cEnCours");
if (cEnCours != null){
	for ( Commande c : cEnCours) {
		out.println("<tr><td>"+ c.getDateRetrait()+ 
				"</td><td>"+ c.getEtatCommande() + 
				"</td><td>"+ c.getIdCommande() +
				"</td><td>"+ c.getIdMagasin() +
				"</td><td>"+ c.getIdUtilisateur()+
				"</td></tr>");
	}
}
%>
</table>
	
</body>
</html>