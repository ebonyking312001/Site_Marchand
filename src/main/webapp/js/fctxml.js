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
		if (xhr.status === 200) {
			var doc = xhr.responseXML.getElementsByTagName("int");
			var texte = doc[0].firstChild.nodeValue;

			var elt = document.getElementById("intNbArtCard");
			elt.innerHTML = texte;
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
			var doc = xhr.responseXML.getElementsByTagName("int");
			var texte = doc[0].firstChild.nodeValue;

			var elt = document.getElementById("intNbArtCard");
			elt.innerHTML = texte;
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

	// Envoie de la requête.
	xhr.send();
}

/**
 * ============================================= Confirmation card jsp (lieu et heure de retrait) =============================================
 */

function getOpeningMagasin() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "ConfirmationPanierServlet?nomM=" + document.getElementById("nomMagasin").value, true);

	xhr.onload = function() {
		if (xhr.status === 200) {
			// Heure d'ouverture
			var doc = xhr.responseXML.getElementsByTagName("hof");
			var texte = doc[0].firstChild.nodeValue;

			const words = doc[0].firstChild.nodeValue.split(' ');
			document.getElementById("heureRetMag").setAttribute("min", words[0]);
			document.getElementById("heureRetMag").setAttribute("max", words[2]);

			var elt = document.getElementById("HoraireMagasin");
			elt.innerHTML = texte;

			// Crénaux
			var doc = xhr.responseXML.getElementsByTagName("hr");
			var texteOpt = "";

			for (i = 0; i < doc.length; i++) {
				texteOpt += '<option value="' + doc[i].firstChild.nodeValue + '">' + doc[i].firstChild.nodeValue + "</option>";
			}

			var elt = document.getElementById("heureRetMag");
			elt.innerHTML = texteOpt;

			document.getElementById("heureRetMag").disabled = false;
			document.getElementById("dateRetMag").disabled = false;
		}
	};

	// Envoie de la requête.
	xhr.send();
}

function confirmCard() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	if (document.getElementById("dateRetMag").value < new Date().getDate()) {
		alert("La date est déjà passée");
	}
	else {
		// Requête au serveur avec les paramètres éventuels.
		xhr.open("GET", "ConfirmationPanierServlet?action=confirmCard&nomM=" + document.getElementById("nomMagasin").value + "&dtRet=" + document.getElementById("dateRetMag").value + "&hRet=" + document.getElementById("heureRetMag").value, true);

		xhr.onload = function() {
			if (xhr.status === 200) {
				alert(("Commande réalisée avec succès").normalize("NFD").replace(/[\u0300-\u036f]/g, ""));
				location.href = "/Site_Marchand/";
				var doc = xhr.responseXML.getElementsByTagName("int");
				var texte = doc[0].firstChild.nodeValue;

				var elt = document.getElementById("intNbArtCard");
				elt.innerHTML = texte;

			}
		};

		// Envoie de la requête.
		xhr.send();
	}
}

/**
 * ============================================= Listes de course =============================================
 */

/**
 * Add new liste de course
 */
function addListeCourse() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	var tpProdSelectionnes = document.getElementsByClassName("tpCheck");

	let stringIdsTPs = '';

	for (var i = 0; tpProdSelectionnes[i]; ++i) {
		if (tpProdSelectionnes[i].checked) {
			stringIdsTPs += String(tpProdSelectionnes[i].value) + "_";
		}
	}

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "ServletListeCourse?nomListeCourse=" + document.getElementById("message-titleList").value + "&listeIdTp=" + stringIdsTPs, true);

	document.getElementById("message-titleList").value = '';

	for (var i = 0; tpProdSelectionnes[i]; ++i) {
		if (tpProdSelectionnes[i].checked) {
			tpProdSelectionnes[i].checked = false;
		}
	};

	//	$('#ModalListeCourse').modal('hide');

	// Envoie de la requête.
	xhr.send();

}

/**
 * Delete liste course by Id
 */
function deleteListeCoursebyId(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to remove the article from cart
	xhr.open("GET", "ServletListeCourse?idListeCourse=" + event, true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			//location.reload();
			console.log("ok");
			$("#content").load(location.href + " #" + event + "_div>*", "");
		}
	};
	// Envoie de la requête.
	xhr.send();
}

/**
 * Add articles to card
 */
function addArticlesToCardFromListe(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// URL to remove the article from cart
	xhr.open("GET", "ServletListeCourse?action=addToCardFromListe&idListeCourse=" + event, true);

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			alert(("Les articles on été ajoutés au panier").normalize("NFD").replace(/[\u0300-\u036f]/g, ""));
			var doc = xhr.responseXML.getElementsByTagName("int");
			var texte = doc[0].firstChild.nodeValue;

			var elt = document.getElementById("intNbArtCard");
			elt.innerHTML = texte;
		}
	};
	// Envoie de la requête.
	xhr.send();
}

/**
 * ============================================= After loading DOM =============================================
 */

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

$('#delete_card').on('click', function() {
	deleteArticlesCart();
});

$('#nomMagasin').on('change', function() {
	getOpeningMagasin();
});

$('#final_validation').on('click', function() {
	confirmCard();
});

$('#plus_liste').on('click', function() {
	addListeCourse();
});

$('.supprListe').on('click',
	function(event) {
		deleteListeCoursebyId(event.target.dataset.idlisterm);
	}
);

$('.addToCardFromL').on('click',
	function(event) {
		addArticlesToCardFromListe(event.target.dataset.idlisteaddcard);
	}
);

$('#envoyerListePanier').on('click', function(event) {
	getProductsFromTPChoosed(event);
});

$('#enregistrerListe').on('click', function(event) {
	getProductsFromTPChoosed(event);
});

document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("heureRetMag").disabled = "disabled";
	document.getElementById("dateRetMag").disabled = "disabled";

});
