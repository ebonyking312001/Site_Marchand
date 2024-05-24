
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
			<div id="categorieContainer" >
			
			<div class="d-flex d-row py-3  align-items-center justify-content-between">
			<div class="d-flex d-row"><h2 class="fw-light  fw-bold">Catégories</h2>
				<c:if test="${idTypeProduitChoisi != null || idCategorieChoisi != null}">
			<a class="text-decoration-none p-2" href="?action=annuler">❌</a>
			</c:if></div>
			<div>
						<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">

<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill="#ffffff" d="M0 416c0 17.7 14.3 32 32 32l54.7 0c12.3 28.3 40.5 48 73.3 48s61-19.7 73.3-48L480 448c17.7 0 32-14.3 32-32s-14.3-32-32-32l-246.7 0c-12.3-28.3-40.5-48-73.3-48s-61 19.7-73.3 48L32 384c-17.7 0-32 14.3-32 32zm128 0a32 32 0 1 1 64 0 32 32 0 1 1 -64 0zM320 256a32 32 0 1 1 64 0 32 32 0 1 1 -64 0zm32-80c-32.8 0-61 19.7-73.3 48L32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l246.7 0c12.3 28.3 40.5 48 73.3 48s61-19.7 73.3-48l54.7 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-54.7 0c-12.3-28.3-40.5-48-73.3-48zM192 128a32 32 0 1 1 0-64 32 32 0 1 1 0 64zm73.3-64C253 35.7 224.8 16 192 16s-61 19.7-73.3 48L32 64C14.3 64 0 78.3 0 96s14.3 32 32 32l86.7 0c12.3 28.3 40.5 48 73.3 48s61-19.7 73.3-48L480 128c17.7 0 32-14.3 32-32s-14.3-32-32-32L265.3 64z"/></svg>
 </button>
  <ul class="dropdown-menu">
    <c:choose>
      <c:when test="${ordreChoisi == 'croissant'}">
        <li>
          <a class="dropdown-item bg-secondary text-white" href="?ordre=croissant">Prix/kg Croissant</a>
        </li>
        <li>
          <a class="dropdown-item" href="?ordre=decroissant">Prix/kg Décroissant</a>
        </li>
      </c:when>
      <c:when test="${ordreChoisi == 'decroissant'}">
        <li>
          <a class="dropdown-item" href="?ordre=croissant">Prix/kg Croissant</a>
        </li>
        <li>
          <a class="dropdown-item bg-secondary text-white" href="?ordre=decroissant">Prix/kg Décroissant</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a class="dropdown-item" href="?ordre=croissant">Prix/kg Croissant</a>
        </li>
        <li>
          <a class="dropdown-item" href="?ordre=decroissant">Prix/kg Décroissant</a>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>
			
			</div>
			</div>
			
		
				<p class="d-inline-flex gap-1">
	 				<c:forEach var="cat" items="${listeCat}"> 
	 					<c:choose>
                        <c:when test="${idCategorieChoisi != null && idCategorieChoisi == cat.idCategorie}">
                                <a class="card shadow-sm btn btn-secondary bg-secondary text-white" href="?idCategorie=${cat.idCategorie}">${cat.nomCategorie}</a>
                        </c:when>
                        <c:otherwise>
                                <a class="card shadow-sm btn btn-secondary" href="?idCategorie=${cat.idCategorie}">${cat.nomCategorie}</a>
                        </c:otherwise>
                    </c:choose>
					</c:forEach> 
				</p>
			</div>
			<div id=typeProduitContainer>
			<c:if test="${listeTypeProd != null}">
				<h2 class="fw-light py-3 fw-bold">Type Produit</h2>
					<div id="productTypeContainer">
						<p class="d-inline-flex gap-1">
								<c:forEach var="typeProd" items="${listeTypeProd}">
									<c:choose>
				                        <c:when test="${idTypeProduitChoisi != null && idTypeProduitChoisi == typeProd.idTypeProduit}">
				                            <a class="card shadow-sm btn btn-secondary bg-secondary text-white" href="?idTypeProd=${typeProd.idTypeProduit}&idCategorie=${typeProd.idCategorie}">${typeProd.nomTypeProduit}</a>
				                        </c:when>
				                        <c:otherwise>
				                            <a class="card shadow-sm btn btn-secondary" href="?idTypeProd=${typeProd.idTypeProduit}&idCategorie=${typeProd.idCategorie}">${typeProd.nomTypeProduit}</a>
				                        </c:otherwise>
				                    </c:choose>       
								</c:forEach>
						</p>
					</div>
			
			</c:if>
			<h2 class="fw-light py-3 fw-bold">Liste d'articles</h2>
			<p class="d-inline-flex gap-1">


			</p>

			<div class="row">
				<c:forEach var="art" items="${listeArt}">

					<div class="col-md-4  col-lg-4 col-sm-12">
						<div class="card shadow-sm">
							<a href="?idArticle=${art.EAN}">
								<center>
									<img src="${art.vignetteArticle}" width='250' />
								</center>
							</a>
							<div class="card-body">
								<p class="card-text">
													<a class="text-decoration-none text-primary fw-bold"
												href="?idArticle=${art.EAN}">${art.libelleArticle} <strong>${art.marque}</strong></a>
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
