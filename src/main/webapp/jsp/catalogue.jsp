<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Liste des articles du catalogue</h1>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Pseudo</th>
				<th>Message</th>
			</tr>
		</thead>
		<tbody>
			<%
	try{
		//ArrayList<MessageDOR> messages = Bd.loadMessages();
		//for (MessageDOR message : messages) {
	%>
		                <tr>
		                	
		                </tr>
		                
	<%
		//}
	}
    catch (Exception e) {
    out.println("<p>Erreur : " + e.getMessage() + "</p>");
		}	
	%>
		</tbody>
	</table>
</body>
</html>