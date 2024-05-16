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
<div class="container">
		<div class="cart">
			<div class="cart-header">
				<h2>Panier</h2>
			</div>
			<div class="cart-items">


				<div class="cart-items">
				<c:set var="totalPrice" value="0" />
				
					<c:forEach var="article" items="${sessionScope.articleList}">
						<div class="cart-item">
							<img src="${article.vignetteArticle}" alt="Product" />
							<div class="cart-item-info">
								<h3>${article.libelleArticle}</h3>
								<p>code: ${article.EAN}</p>
								<p>prix: ${article.prixUnitaireArticle} e</p>
								    <c:set var="totalPrice" value="${totalPrice + article.prixUnitaireArticle}" />
								
							</div>
							<div class="cart-item-quantity">
								<input type="number" value="1" min="1">
							</div>
						</div>
					</c:forEach>

				</div>

				Ajouter d'autres éléments du panier ici
			</div>
			<div class="total">
			
							<p>Prix : ${totalPrice} e</p>
					
			
			</div>
			<button class="checkout-btn">Passer la Commande</button>
		</div>
	</div>

</main>
    </jsp:body>


</t:genericpage>

