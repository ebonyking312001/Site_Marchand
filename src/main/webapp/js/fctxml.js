/**
 * ============================================= Accueil jsp =============================================
 */
/**
 * Add article by Id
 */
function addArticleById(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to add the article to cart
	xhr.open("GET", "ServletPanier?idArticle=" + event, true);

	// Envoie de la requête.
	xhr.send();
}

/**
 * ============================================= Panier jsp =============================================
 */
/**
 * Delete articles from cart
 */
function deleteArticlesCart() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to delete articles from cart
	xhr.open("GET", "ServletPanier?action=deleteArticlesCart", true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			console.log("ok reload")
			location.reload();
		}
	};


	// Envoie de la requête.
	xhr.send();
}

/**
 * Loads after build of DOM
 */
document.addEventListener("DOMContentLoaded", () => {
	$('.byalpha').on('click',
		function(event) {
			addArticleById(event.target.dataset.idart);
		}
	);

	document.getElementById("delete_cart").addEventListener("click", deleteArticlesCart);

});
