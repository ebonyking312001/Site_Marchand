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
        <div class="container">
            <h4 class="m-2">Articles à valider pour la commande ${cmdId} :</h4>
            <div class="row">
                <c:forEach var="article" items="${articles}">
                    <div class="col-3 col-sm-12 mb-4"
                         ligne-cmd-ean="${article.EAN}" ligne-cmd-etat="${article.estValide ? '1' : '0'}">
                        <div class="container card shadow-sm">
                            <a href="/Site_Marchand/?idArticle=${article.EAN}">
                                <img src="${article.vignetteArticle}" class="card-img-top" alt="${article.libelleArticle}">
                            </a>
                            <h5 class="card-title">
                                <a class="text-decoration-none text-primary fw-bold" href="/Site_Marchand/?idArticle=${article.EAN}">
                                    ${article.libelleArticle}
                                </a>
                            </h5>
                            <div class="card-body">
                                <small>Quantité : ${article.qteCom}</small>

                                <div class="text-center">
                                    <c:if test="${article.promoArticle > 0}">
                                        <small class="fw-bold text-decoration-line-through">
                                            ${article.prixUnitaireArticle} €
                                        </small>
                                        <small class="fw-bold text-danger">
                                            Promo : ${article.promoArticle} €
                                        </small>
                                    </c:if>
                                    <c:if test="${article.promoArticle == 0}">
                                        <small class="text-body-secondary fw-bold">
                                            Prix : ${article.prixUnitaireArticle} €
                                        </small>
                                    </c:if>
                                    <div class="mt-2 d-flex d-row w-100">
                                        <button type="button"  class="btn w-100 mt-2 validerBtn  btn-lg">${article.estValide ? 'Validée' : 'En cours'}</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="btn btn-primary btn-lg w-100" href="${pageContext.request.contextPath}/ServletPreparation">Revenir à la liste commandes</a>
        </div>
    </jsp:body>
</t:genericpage>