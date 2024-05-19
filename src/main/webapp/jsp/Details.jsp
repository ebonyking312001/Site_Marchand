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
        <main class=" ">
            <div class="container mt-5 ">
                <div class="card details-card p-0 shadow-sm">
                    <div class="row g-0">
                        <div class="col-md-6">
                        
                            <img class="img-fluid details-img p-5"
								src="${article.vignetteArticle}" alt="${article.libelleArticle}">
                            
                        </div>
                        <div
							class="col-md-6 d-flex align-items-center bg-light">
                            <div class="card-body p-5">
                                <h2>${article.libelleArticle}</h2>
                                <hr>
                                <h4 class="fs-3">Prix</h4>
                                <div class="col">
                                <c:if test="${article.promoArticle > 0}">
    <small class=" fw-bold text-decoration-line-through ">
         ${article.prixUnitaireArticle} €
    </small>
    <small class="fw-bold text-danger">
                <svg xmlns="http://www.w3.org/2000/svg" height="14"
												width="12.25" viewBox="0 0 448 512">
											<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
											<path fill="#ff0000"
													d="M0 80V229.5c0 17 6.7 33.3 18.7 45.3l176 176c25 25 65.5 25 90.5 0L418.7 317.3c25-25 25-65.5 0-90.5l-176-176c-12-12-28.3-18.7-45.3-18.7H48C21.5 32 0 53.5 0 80zm112 32a32 32 0 1 1 0 64 32 32 0 1 1 0-64z" /></svg>
    
        Promo : ${article.promoArticle} €
    
    </small>
</c:if>
<c:if test="${article.promoArticle == 0}">
    <small class="text-body-secondary fw-bold">
        Prix : ${article.prixUnitaireArticle} €
    </small>
</c:if>
                                </div>
                                <div class="col">
                                <input type="number"
										id="${article.EAN}_nbArticle" min="1" style="width: 3em"
										value="1">
                                <a
										class="btn btn-sm btn-secondary text-white byalpha"
										data-idArt="${article.EAN}">Ajouter au
											panier</a>
                                </div>
                                <hr>
                                <h4 class="product-title mt-4 mb-1">Description de l'article</h4>
                                <p class="product-description mb-4">
                                    ${article.descriptionLongueArticle}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</t:genericpage>
