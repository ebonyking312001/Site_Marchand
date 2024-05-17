<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
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

	<div class="album py-5 bg-body-tertiary ">
		<div class="container">
			<h2 class="fw-light py-3 fw-bold">Liste d'articles</h2>

			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

				<c:forEach var="art" items="${articlesTrouve}">

					<div class="col">
						<div class="card shadow-sm">
							<img src="${art.vignetteArticle}"/>
							<div class="card-body">
								<p class="card-text">${art.libelleArticle}</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
										<a class="btn btn-sm btn-primary text-white"
													href="ServletPanier?idArticle=${art.EAN}">Ajouter au
											panier</a>
									</div>
									<small class="text-body-secondary">${art.prixUnitaireArticle}</small>
								</div>
							</div>
						</div>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>

</main>
    </jsp:body>


</t:genericpage>

