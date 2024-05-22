<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const cmdId = "${cmdId}";
        document.querySelectorAll('div[ligne-cmd-ean]').forEach(div => {
            const etat = div.getAttribute('ligne-cmd-etat');
            if (etat === "1") {
                div.style.backgroundColor = "green";
                div.style.color = "white";
            } else {
                div.style.backgroundColor = "";
                div.style.color = "";
            }
        });

        document.querySelectorAll('button.validerBtn').forEach(button => {
            button.addEventListener('click', (event) => {
                const parentDiv = event.target.closest('div[ligne-cmd-ean]');
                const ean = parentDiv.getAttribute('ligne-cmd-ean');
                let etat = parentDiv.getAttribute('ligne-cmd-etat');
                etat = etat == "1" ? "0" : "1"; // Toggle state

                const xhr = new XMLHttpRequest();
                xhr.open("POST", `${pageContext.request.contextPath}/ServletPreparation`, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        console.log("Response received: ", xhr.responseText);
                        // Update the parent div and button based on the new state
                        parentDiv.setAttribute('ligne-cmd-etat', etat);
                        button.textContent = etat == "1" ? 'Validée' : 'En cours';
                        if (etat == "1") {
                            parentDiv.style.backgroundColor = "green";
                            parentDiv.style.color = "white";
                        } else {
                            parentDiv.style.backgroundColor = "";
                            parentDiv.style.color = "";
                        }
                    }
                };
                var data = "cmdId="+cmdId+"&ean="+ean+"&etat="+etat;
                console.log(data);
                xhr.send(data);
            });
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
            <div class="row">
                <c:forEach var="article" items="${articles}">
                    <div class="col-md-3 col-sm-6 mb-4"
                         ligne-cmd-ean="${article.EAN}" ligne-cmd-etat="${article.estValide ? '1' : '0'}">
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
                                    <div class="mt-2 d-flex d-row">
                                        <button type="button" class="btn btn-success mt-2 validerBtn">${article.estValide ? 'Validée' : 'En cours'}</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ServletPreparation">Revenir à la liste commandes</a>
        </div>
    </jsp:body>
</t:genericpage>