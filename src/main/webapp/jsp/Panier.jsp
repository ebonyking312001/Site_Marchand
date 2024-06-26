
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:genericpage>
	<jsp:attribute name="header">
        <%@ include file="Header.jsp"%>
    </jsp:attribute>
	<jsp:attribute name="footer">
        <%@ include file="Footer.jsp"%>
    </jsp:attribute>
	<jsp:body>
        <main>
            <div class="container">
                <div class="cart">
                    <div class="cart-header">
                        <h2>Panier</h2>
                    </div>
                    <div class="cart-items">
                        <c:set var="totalPrice" value="0" />
                        <c:forEach var="article"
							items="${sessionScope.articleList}">
                            <div class="cart-item">
                                <img src="${article.vignetteArticle}"
									alt="Product" />
                                <div class="cart-item-info">
                                    <h3>${article.libelleArticle}</h3>
                                    <p>code: ${article.EAN}</p>
                                    <p>
                                        <c:if
											test="${article.promoArticle > 0}">
                                            <small
												class="fw-bold text-decoration-line-through">
                                                <fmt:formatNumber
													value="${article.prixUnitaireArticle}"
													minFractionDigits="1" maxFractionDigits="1" /> €
                                            </small>
                                            <small
												class="fw-bold text-danger">
                                                <svg
													xmlns="http://www.w3.org/2000/svg" height="14"
													width="12.25" viewBox="0 0 448 512">
                                                    <!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
                                                    <path fill="#ff0000"
														d="M0 80V229.5c0 17 6.7 33.3 18.7 45.3l176 176c25 25 65.5 25 90.5 0L418.7 317.3c25-25 25-65.5 0-90.5l-176-176c-12-12-28.3-18.7-45.3-18.7H48C21.5 32 0 53.5 0 80zm112 32a32 32 0 1 1 0 64 32 32 0 1 1 0-64z" />
                                                </svg>
                                                Promo : <fmt:formatNumber
													value="${article.promoArticle}" minFractionDigits="1"
													maxFractionDigits="1" /> €
                                            </small>
                                        </c:if>
                                        <c:if
											test="${article.promoArticle == 0}">
                                            <small
												class="text-body-secondary fw-bold">
                                                Prix/unité : <fmt:formatNumber
													value="${article.prixUnitaireArticle}"
													minFractionDigits="1" maxFractionDigits="1" /> €
                                            </small>
                                        </c:if>
                                    </p>
                                    <c:choose>
                                        <c:when
											test="${article.promoArticle == 0}">
                                            <c:set var="totalPrice"
												value="${totalPrice + (article.prixUnitaireArticle * article.quantite)}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="totalPrice"
												value="${totalPrice + (article.promoArticle * article.quantite)}" />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div
									class="col-md-3 col-lg-3 col-xl-2 d-flex">
                      <a
										class="btn btn-sm btn-secondary text-white byalphaRmPanier"
										style="margin-right: 5px;" data-idArt="${article.EAN}">-</a>
                      <div class="form-control form-control-sm">
                                    <input type="number" data-idArtQuantity="${article.EAN}_quantity"
											value="${article.quantite}" class="changeNbArt"
											data-idArt="${article.EAN}">
                                </div>
										<a
										class="btn btn-sm btn-secondary text-white byalphaAddPanier"
										style="margin-left: 5px;" data-idArt="${article.EAN}">+</a>
                    </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${sessionScope.articleList != null}">
                    <div class="total">
                        <p class="fw-bold my-2">Prix : <fmt:formatNumber
									value="${totalPrice}" minFractionDigits="1"
									maxFractionDigits="1" /> €</p>
                    </div>
                    <div class="row">
                    <div class="col-2">
                    <a href="ConfirmationPanierServlet?totalPrice=${totalPrice}"><button
										class="checkout-btn" id="validate_card">Valider le panier</button></a>
                    
                    </div>
                    <div class="col-2">
                    <button id="delete_card"
									class="checkout-btn btn btn-danger">Annuler le panier</button>
                    </div>
                    </div>
					</c:if>
					<c:if test="${sessionScope.articleList == null}">
					Vous n'avez rien dans le panier
					</c:if>
                    
                </div>
            </div>
        </main>
    </jsp:body>
</t:genericpage>
