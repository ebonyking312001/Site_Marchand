<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Article"
    import="java.util.ArrayList"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Page d'accueil</h1>

	<table border = "1">
	    <tr>
	    <th>EAN</th>
	    <th>Vignette</th>
	    <th>Prix unitaire</th>
	    <th>Nutriscore</th>
	    <th>Libelle</th>
	    <th>Poids</th>
	    <th>Prix au Kg</th>
	    <th>Description courte</th>
	    <th>Description longue</th>
	    <th>Fournisseur</th>
	    <th>Marque</th>
	    </tr>
	    
	   	<% 
	    ArrayList<MessageDor> listeMD = (ArrayList<MessageDor>) request.getAttribute("listMD");
	    for (MessageDor md : listeMD) { 
	    %>
	        <tr>
	            <td><%= md.getNum() %></td>
	            <td><%= md.getPseudo() %></td>
	            <td><%= md.getTexte() %></td>
	        </tr>
	    <% } %>

    </table>
 <br>
 
 <p>Cliquer pour revenir à la page d'accueil</p>
    
</body>
</html>