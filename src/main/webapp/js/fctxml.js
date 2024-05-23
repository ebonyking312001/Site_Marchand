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
 * Add new content to liste de course
 */
function addContenuListeCourse(event) {
	// Objet XMLHttpRequest.
	//	var xhr = new XMLHttpRequest();

	var idPlus = event.target.dataset.idmorecontent;
	console.log(idPlus);

	var suggestions = document.getElementById("formAdd");
	var nodeBtnAdd = suggestions.lastElementChild;

	// Insert before the btn "+" a new input
	//	var newInput = '<div class="mt-4"><select id="postit"><option>-----</option></select><select id="nomTypeProduit"><option>-----</option></select><input type="number" id="quantity" min="1" style="width: 3em" ></div>';
	var newInput = '<div class="mt-4"><select id="postit"><option>-----</option></select><input type="number" id="quantity" min="0" style="width: 3em">';

	nodeBtnAdd.insertAdjacentHTML('beforebegin', newInput);

}

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
	//	xhr.open("GET", "ServletListeCourse?nomListeCourse=" + document.getElementById("message-titleList").value + "&listeIdTp=" + stringIdsTPs, true);

	document.getElementById("message-titleList").value = '';

	for (var i = 0; tpProdSelectionnes[i]; ++i) {
		if (tpProdSelectionnes[i].checked) {
			tpProdSelectionnes[i].checked = false;
		}
	};

	$('#ModalListeCourse').modal('hide');

}

/**
 * Get all products by product type
 */
function getProductsFromTPChoosed(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var idTp = event.target.dataset.idart;
	console.log(idTp);

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "ServletListeCourse?nomTP=" + document.getElementById("postit").value, true);

	var suggestions = document.getElementById("postit");

	xhr.onload = function() {
		// Si la requête http s'est bien passée.
		if (xhr.status === 200) {
			var doc = xhr.responseXML.getElementsByTagName("nArt");

			var newInput = '<select id="nomArt" data-idSelectArt=' + idTp + '_selectNArt><option>-----</option>';

			for (i = 0; i < doc.length; i++) {
				newInput += '<option value="' + docTPs[i].firstChild.nodeValue + '">' + docTPs[i].firstChild.nodeValue + "</option>";
			}

			newInput += '</select><input type="number" id="quantity_' + idTp + ' min="0" style="width: 3em">';

			suggestions.insertAdjacentHTML('afterend', newInput);

		}
	};
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

$('#plusLContenu').on('click', function(event) {
	addContenuListeCourse(event);
});

$('#plus_liste').on('click', function() {
	addListeCourse();
});

$('#postit').on('change', function(event) {
	getProductsFromTPChoosed(event);
});

$('#supprListe').on('click', function(event) {
	getProductsFromTPChoosed(event);
});

$('#envoyerListePanier').on('click', function(event) {
	getProductsFromTPChoosed(event);
});

$('#enregistrerListe').on('click', function(event) {
	getProductsFromTPChoosed(event);
});

$(document).on("click", ".modalC", function() {
	var listeId = $(this).data('id');
	$(".modal-body #listeId").val(listeId);
});

document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("heureRetMag").disabled = "disabled";
	document.getElementById("dateRetMag").disabled = "disabled";

});
