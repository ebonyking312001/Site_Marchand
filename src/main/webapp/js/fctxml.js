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
			location.reload();
		}
	};
	// Envoie de la requête.
	xhr.send();
}

/**
 * Add quantity of article by Id and reload page to get the number of articles updated
 */
function addArticleByIdFromCart(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to add the article from cart
	xhr.open("GET", "ServletPanier?idArticle=" + event, true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			location.reload();
		}
	};
	// Envoie de la requête.
	xhr.send();
}

/**
 * Remove quantity of article by Id and reload page to get the number of articles updated
 */
function rmArticleByIdFromCart(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to remove the article from cart
	xhr.open("GET", "ServletPanier?idArticleRm=" + event, true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
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
	
		$('.byalphaAddPanier').on('click',
		function(event) {
			addArticleByIdFromCart(event.target.dataset.idart);
		}
	);

	$('.byalphaRmPanier').on('click',
		function(event) {
			rmArticleByIdFromCart(event.target.dataset.idart);
		}
	);

	document.getElementById("delete_cart").addEventListener("click", deleteArticlesCart);

});
