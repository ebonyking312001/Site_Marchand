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
			<h1 class="fw-light p-3">Liste d'articles</h1>

			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

				<c:forEach var="art" items="${articlesTrouve}">

					<div class="col">
						<div class="card shadow-sm">
							<a href="JspAccueil?idArticle=${art.EAN}">
								<center><img src="${art.vignetteArticle}" width='250'/></center>
							</a>
							<div class="card-body">
								<p class="card-text">${art.libelleArticle}</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
									
										<a type="button" class="btn btn-sm btn-outline-secondary" href="JspAccueil?idArticle=${art.EAN}">View</a>
										<a class="btn btn-sm btn-primary text-white"
													href="ServletPanier?idArticle=${art.EAN}">Ajouter au
											panier</a>
									</div>
								<c:if test="${art.promoArticle > 0}">
    <small class=" fw-bold text-decoration-line-through ">
         ${art.prixUnitaireArticle} €
    </small>
    <small class="fw-bold text-danger">
                <svg xmlns="http://www.w3.org/2000/svg" height="14" width="12.25" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill="#ff0000" d="M0 80V229.5c0 17 6.7 33.3 18.7 45.3l176 176c25 25 65.5 25 90.5 0L418.7 317.3c25-25 25-65.5 0-90.5l-176-176c-12-12-28.3-18.7-45.3-18.7H48C21.5 32 0 53.5 0 80zm112 32a32 32 0 1 1 0 64 32 32 0 1 1 0-64z"/></svg>
    
        Promo : ${art.promoArticle} €
    
    </small>
</c:if>
<c:if test="${art.promoArticle == 0}">
    <small class="text-body-secondary fw-bold">
        Prix : ${art.prixUnitaireArticle} €
    </small>
</c:if>
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

