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
        <div class="container mt-5 orders-container ">
            <h1 class="mb-4">Les Commandes à Préparer</h1>
            <c:if test="${not empty cEnCours}">
    <div class="mb-3">
        <button id="sort-datetime" class="btn btn-secondary">Sort by Date and Time</button>
    </div>
    <div id="tab-cmds" class="row orders-container">
        <c:forEach var="c" items="${cEnCours}">
            <div class="id-div-commande col-6 col-sm-12 col-lg-4 mb-4">
                <div class="card h-100">
                    <div
									class="card-header d-flex justify-content-between">
                        <span class="order-date">${c.dateRetrait}</span>
                        <span class="order-time">${c.debutCreneau}</span>
                    </div>
                    <div class="card-body">
                        <a class="btn btn-primary btn-lg w-100 mb-2"
										href="${pageContext.request.contextPath}/CtrlDetailCommandeAdamServlet/${c.idCommande}">Commande #${c.idCommande}</a>
                        <p class="card-text">Magasin: ${c.nomMagasin}</p>
                        <p class="card-text">Utilisateur: ${c.idUtilisateur}</p>
                        <p class="card-text">État: ${c.etatCommande}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>
            
        </div>
    </jsp:body>
</t:genericpage>

