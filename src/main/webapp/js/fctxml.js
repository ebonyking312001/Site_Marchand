/**
 * ============================================= Accueil jsp, Details jsp, Chercher jsp =============================================
 */

/**
 * Add article by Id with some quantity
 */
function addArticleByIdWithQuantity(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	var nbArticlesToAdd = document.getElementById(event + "_nbArticle");

	// URL to add the article to cart
	xhr.open("GET", "ServletPanier?idArticle=" + event + "&quantity=" + nbArticlesToAdd.value, true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			alert("Ajouté au panier avec succès");
		}
	};

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
 * Sets the quantity of articles when modifying directly the value from the input
 */
function onKeyupQuantityArt(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	var idArticle = event.target.dataset.idart;
	var nbArticlesToAdd = event.target.value;

	if (nbArticlesToAdd == "") {
		nbArticlesToAdd = 0;
	}
	xhr.open("GET", "ServletPanier?action=changeArt&idArticle=" + idArticle + "&quantity=" + nbArticlesToAdd, true);

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
 * ============================================= Pop-up jsp (lieu et heure de retrait) =============================================
 */

/**
 * Opens pop-up to chose lieu de retrait and heure de retrait
 */
function openPopUpChoice_Mag_Hr() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "ConfirmationPanierServlet", true);


	//	width = window.screen.width;
	//	height = window.screen.height;
	//	mywindow = window.open("GET", "ConfirmationPanierServlet?", "Confirmation du magasin et l''heure de retrait",
	//		"location=0,status=1,scrollbars=1,resizable=1,menubar=0,toolbar=no,width="
	//		+ width + ",height=" + height);
	//	mywindow.moveTo(0, 0);
	//	mywindow.focus();

	// Envoie de la requête.
	xhr.send();
}

function getOpeningMagasin() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "ConfirmationPanierServlet?nomMagasin=" + document.getElementById("nomMagasin").value, true);

	xhr.onload = function() {
		if (xhr.status === 200) {
			var doc = xhr.responseXML.getElementsByTagName("hrOuvFer");
			var texte = "";

			for (i = 0; i < doc.length; i++) {
				texte += '<hof>' + doc[i].firstChild.nodeValue + "</hof></br>";
			}

			var elt = document.getElementById("HoraireMagasin");
			elt.innerHTML = texte;
		}
	};

	// Envoie de la requête.
	xhr.send();
}

/**
 * ============================================= After loading DOM =============================================
 */
document.addEventListener("DOMContentLoaded", () => {

	$('.byalpha').on('click',
		function(event) {
			addArticleByIdWithQuantity(event.target.dataset.idart);
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

	$('.changeNbArt').on('keyup',
		function(event) {
			onKeyupQuantityArt(event);
		}
	);

	document.getElementById("validate_card").addEventListener("click", openPopUpChoice_Mag_Hr);

	document.getElementById("delete_card").addEventListener("click", deleteArticlesCart);

	document.getElementById("nomMagasin").addEventListener("change", l_citations);

});
