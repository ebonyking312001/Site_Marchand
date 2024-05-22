<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:genericpage>
	<jsp:attribute name="header">
      <%@ include file="Header.jsp"%>
    </jsp:attribute>
	<jsp:attribute name="footer">
      <%@ include file="Footer.jsp"%>
    </jsp:attribute>
	<jsp:body>
 <main>
	<div class="container mt-5">
		<h1 class="mb-4">
			les commandes à préparer
		</h1>
		<c:if test="${not empty cEnCours}">
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<tr>
					<th>Date Retrait</th>
					<th>Créneau Retrait</th>
					<th>Id Commande</th>
					<th>Nom Magasin</th>
					<th>IdUtilisateur</th>
					<th>Etat Commande</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${cEnCours}">
				<tr>
					<td>${c.dateRetrait}</td>
					<td>${c.debutCreneau}</td>
					<td><a class="btn btn-primary"
						href="${pageContext.request.contextPath}/CtrlDetailCommandeAdamServlet/${c.idCommande}">${c.idCommande}</a></td>
					<td>${c.nomMagasin}</td>
					<td>${c.idUtilisateur}</td>
					<td>${c.etatCommande}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</div>
</main>
    </jsp:body>
</t:genericpage>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
