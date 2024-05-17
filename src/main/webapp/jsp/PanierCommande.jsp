<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Commande" %>
<%@page import="model.ArticleCommande" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Panier Commande</title>
</head>
<body>
	<h1><a href="ServletPreparation?action=afficherCommandes">Afficher les commandes à préparer</a></h1>
	
	<table border=1>

<% 
ArrayList<Commande> cEnCours = (ArrayList<Commande>)request.getAttribute("cEnCours");
ArrayList<ArticleCommande> cmdD=(ArrayList<ArticleCommande>)request.getAttribute("cmdD");
if(cmdD!=null){
	cEnCours=null;
	out.println("<tr>"+
			"<th>EAN</th>"+
			"<th>LibelleArticle</th>"+
			"<th>Marque</th>"+
			"<th>IdRayon</th>"+
			"<th>Qte</th>"+
			"<th>PrixUnitaireArticle</th>"+
			"<th>PoidsArticle</th>"+
			"</tr>");
	for(ArticleCommande ac : cmdD){
		out.println("<tr><td>"+ ac.getEAN()+ 
				"</td><td>"+ ac.getLibelleArticle()+ 
				"</td><td>"+ ac.getMarque()+ 
				"</td><td>"+ ac.getIdRayon()+ 
				"</td><td>"+ ac.getQteCom()+ 
				"</td><td>"+ ac.getPrixUnitaireArticle()+ 
				"</td><td>"+ ac.getPoidsArticle()+ 
				"</td></tr>");
	}
}
if (cEnCours != null){
	out.println("<tr>"+
			"<th>DateRetrait</th>"+
			"<th>EtatCommande</th>"+
			"<th>IdCommande</th>"+
			"<th>IdMagasin</th>"+
			"<th>IdUtilisateur</th>"+
			"</tr>");
	
	for ( Commande c : cEnCours) {
		out.println("<tr><td>"+ c.getDateRetrait()+ 
				"</td><td>"+ c.getEtatCommande() + 
				"</td><td><a href='ServletPreparation?action="+c.getIdCommande()+"'>"+ c.getIdCommande() +
				"</a></td><td>"+ c.getIdMagasin() +
				"</td><td>"+ c.getIdUtilisateur()+
				"</td></tr>");
	}
}

%>

</table>
	
</body>
</html>