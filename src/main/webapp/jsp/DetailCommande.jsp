<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    .valid-row {
        background-color: green;
        color: white;
    }
    .selected {
        background-color: lightblue;
        color: white;
    }
    div[ligne-cmd-ean] {
        cursor: pointer;
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        let selectedEANs = [];

        document.querySelectorAll('button.validerBtn').forEach(button => {
            button.addEventListener('click', (event) => {
                const parentDiv = event.target.closest('div[ligne-cmd-ean]');
                const ean = parentDiv.getAttribute('ligne-cmd-ean');

                if (parentDiv.classList.contains('selected')) {
                    parentDiv.classList.remove('selected');
                    selectedEANs = selectedEANs.filter(item => item !== ean);
                } else {
                    parentDiv.classList.add('selected');
                    selectedEANs.push(ean);
                }
                
                button.textContent = button.textContent === 'Valider' ? 'Invalider' : 'Valider';
            });
        });

        document.getElementById('submitBtn').addEventListener('click', () => {
            const form = document.getElementById('eanForm');
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'selectedEANs';
            input.value = JSON.stringify(selectedEANs);
            form.appendChild(input);
            form.submit();
        });
    });
</script>

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
            <form id="eanForm" action="${pageContext.request.contextPath}/ServletPreparation" method="POST">
                <input type="hidden" name="cmdId" value="${cmdId}">
                <div class="row">
                    <c:forEach var="article" items="${articles}">
                        <div class="col-md-3 col-sm-6 mb-4 ${article.estValide ? 'valid-row' : ''}" ligne-cmd-ean="${article.EAN}">
                            <div class="container card shadow-sm">
                                <a href="?idArticle=${article.EAN}">
                                    <img src="${article.vignetteArticle}" class="card-img-top" alt="${article.libelleArticle}">
                                </a>
                                <h5 class="card-title">
                                    <a class="text-decoration-none text-primary fw-bold" href="?idArticle=${article.EAN}">
                                        ${article.libelleArticle}
                                    </a>
                                </h5>
                                <div class="card-body">
                                    <small>Quantité : ${article.qteCom }</small>
                                    
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
                                        <div class="mt-2 d-flex d-row">
                                            <button type="button" class="btn btn-success mt-2 validerBtn">Valider</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button type="button" id="submitBtn" class="btn btn-primary">Submit</button>
                <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/ServletPreparation">Revenir à la liste commandes</a>
            </form>
        </div>
    </jsp:body>
</t:genericpage>
