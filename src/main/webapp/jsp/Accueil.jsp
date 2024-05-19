
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
				<c:forEach var="art" items="${listeArt}">

					<div class="col">
						<div class="card shadow-sm">
							<a href="?idArticle=${art.EAN}">
								<center>
													<img src="${art.vignetteArticle}" width='250' />
												</center>
							</a>
							<div class="card-body">
								<p class="card-text">
													<a class="text-decoration-none text-primary fw-bold"
												href="?idArticle=${art.EAN}">${art.libelleArticle}</a>
												</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
									<a href="?idArticle=${art.EAN}">
										<button type="button" class="btn btn-sm btn-outline-secondary">Plus de détails</button>
									</a>
									</div>
									<div class="row">
									<div class="col">
									<input type="number" id="${art.EAN}_nbArticle" min="1"
														style="width: 3em" value="1">
									<a
														class="btn btn-sm btn-secondary text-white rounded-left byalpha"
														style="margin-left: 5px;" data-idArt="${art.EAN}">Ajouter au
											panier</a>
												</div>
									</div>



								</div>
							</div>
																<div class="row text-center">
														<div class="col">
																																							<c:if
												test="${art.promoArticle > 0}">
    <small class=" fw-bold text-decoration-line-through ">
         ${art.prixUnitaireArticle} €
    </small>
    <small class="fw-bold text-danger">
                <svg xmlns="http://www.w3.org/2000/svg" height="14"
														width="12.25" viewBox="0 0 448 512">
																<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
																<path fill="#ff0000"
															d="M0 80V229.5c0 17 6.7 33.3 18.7 45.3l176 176c25 25 65.5 25 90.5 0L418.7 317.3c25-25 25-65.5 0-90.5l-176-176c-12-12-28.3-18.7-45.3-18.7H48C21.5 32 0 53.5 0 80zm112 32a32 32 0 1 1 0 64 32 32 0 1 1 0-64z" /></svg>
    
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
