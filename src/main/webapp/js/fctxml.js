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
 * Loads after build of DOM
 * ============================================= Confirmation card jsp (lieu et heure de retrait)=============================================
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
				alert("Commande réalisée avec succès");
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

function calculateNewTotalPrice() {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	// get input points
	var pointsInput = parseInt(document.getElementById("pointsInput").value) || 0;
	// get discount amount
	var discount = Math.floor(pointsInput/10);
	// get loyalty points
	var pointsFideliteDispo = document.getElementById("pointsFideliteDispo").dataset.value;
	// get total price
	var totalPrice = parseFloat(document.getElementById("totalPrice").dataset.value) || 0.0;
	// get new total price 
	var newTotalPrice = totalPrice - discount;
	// get new pointsFideliteDispo
	var newPointsFideliteDispo = pointsFideliteDispo - pointsInput;
	// get bouton validation commande
	var button = document.getElementById("final_validation");
	
   	if (pointsInput > pointsFideliteDispo) {
        document.getElementById("errorPoints").innerHTML = "Points insuffisants";
        button.disabled = true;
        button.style.backgroundColor = "#ccc"; // Change background color to grey
   		button.style.cursor = "not-allowed"; // Change cursor to indicate it's unclickable
    } else if (newTotalPrice < 0) {
        document.getElementById("errorPoints").innerHTML = "Le prix total ne peut pas être inférieur à 0. Veuillez ajuster le montant des points.";
        button.disabled = true;
        button.style.backgroundColor = "#ccc"; // Change background color to grey
   		button.style.cursor = "not-allowed"; // Change cursor to indicate it's unclickable
    } else if (pointsInput < 0){
		document.getElementById("errorPoints").innerHTML = "La valeur saisie ne peut pas être inférieure à 0. Veuillez ajuster le montant des points.";
		button.disabled = true;
		button.style.backgroundColor = "#ccc"; // Change background color to grey
    	button.style.cursor = "not-allowed"; // Change cursor to indicate it's unclickable
	} else {
		document.getElementById("errorPoints").innerHTML = "";
		button.style.cursor = "pointer"; 
		button.style.backgroundColor = "#007bff"; 

		
		var eltPrice = document.getElementById("totalPrice");
		eltPrice.innerHTML = '<b>Prix total à payer : '+newTotalPrice.toFixed(1)+'€</b>';
		
		// envoyer pointsInput et newTotalPrice au servlet ConfirmationPanierServlet
        xhr.open("GET", "ConfirmationPanierServlet?pointsInput="+ pointsInput+"&newTotalPrice="+newTotalPrice, true);
		xhr.onload = function() {
			if (xhr.status === 200) {
				// get nouveau points de fidélité à gagner
				var doc = xhr.responseXML.getElementsByTagName("points");
				var texte = doc[0].firstChild.nodeValue;
				// update nouveau points de fidélité dans div addLoyaltyPoints
				var eltPoints = document.getElementById("addLoyaltyPoints");
				eltPoints.innerHTML = "Je cagnotte <b>"+texte+"</b> points";
				// update nouveau points de fidélité disponible
				var eltNewPoints = document.getElementById("pointsFideliteDispo");
				eltNewPoints.innerHTML = "Solde : "+ newPointsFideliteDispo;
				button.disabled = false;
			}
		};
		
    // Send the request
    xhr.send();
    }
	
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

	$('#delete_card').on('click', function() {
		deleteArticlesCart();
	});

	$('#nomMagasin').on('change', function() {
		getOpeningMagasin();
	});

	$('#final_validation').on('click', function() {
		confirmCard();
	});
	$('#decagnotter').on('click', function() {
		calculateNewTotalPrice();
	});
	document.getElementById("heureRetMag").disabled = "disabled";
	document.getElementById("dateRetMag").disabled = "disabled";
	
	

});
