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
				texteOpt += '<option value="' + doc[i].lastChild.firstChild.nodeValue + '">' + doc[i].firstChild.nodeValue + "</option>";
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

	document.getElementById("heureRetMag").disabled = "disabled";
	document.getElementById("dateRetMag").disabled = "disabled";

});

document.addEventListener('DOMContentLoaded', (event) => {
    const cmdId = "${cmdId}";
    
    /*
    add colors of buttons
    */
    document.querySelectorAll('button.validerBtn').forEach(button => {
    	  const parentDiv = button.closest('div[ligne-cmd-ean]'); 
          const etat = parentDiv.getAttribute('ligne-cmd-etat');
        if (etat == "1") { //validée
            button.classList.add('btn-success');
            button.classList.remove('btn-warning');
        } else { //en cours
            button.classList.add('btn-warning');
            button.classList.remove('btn-success');
        }
    });
    /*
    when click button, change button color and get ean,etat
    */
    document.querySelectorAll('button.validerBtn').forEach(button => {
        const parentDiv = button.closest('div[ligne-cmd-ean]');
        const etat = parentDiv.getAttribute('ligne-cmd-etat');
        const ean = parentDiv.getAttribute('ligne-cmd-ean');

        button.addEventListener('click', (event) => {
            let etat = parentDiv.getAttribute('ligne-cmd-etat');
            etat = etat == "1" ? "0" : "1"; // get etat opposite
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
                        button.classList.add('btn-success');
                        button.classList.remove('btn-warning');
                    } else {
                        button.classList.add('btn-warning');
                        button.classList.remove('btn-success');
                    }
                }
            };
            var data = "cmdId="+cmdId+"&ean="+ean+"&etat="+etat;
            console.log(data);
            xhr.send(data);
        });
    });
});


/**
 * sort by date time (preparer cmd
 */
document.addEventListener('DOMContentLoaded', function() {
    function parseDateTime(dateString, timeString) {
        // Assuming date format is YYYY-MM-DD and time format is HH:MM:SS
        const longdate = dateString+"T"+timeString
        return new Date(longdate);
    }

    function sortTable(table, dateColumn, timeColumn, asc = true) {
        const rows = Array.from(table.querySelector('tbody').rows);
        rows.sort((rowA, rowB) => {
            const dateA = rowA.cells[dateColumn].innerText.trim();
            const timeA = rowA.cells[timeColumn].innerText.trim();
            const dateB = rowB.cells[dateColumn].innerText.trim();
            const timeB = rowB.cells[timeColumn].innerText.trim();
            const a = parseDateTime(dateA, timeA);
            const b = parseDateTime(dateB, timeB);
            console.log(dateA);
        	console.log(timeA);
        	console.log(a);
            return (a - b) * (asc ? 1 : -1);
        });

        rows.forEach(row => table.querySelector('tbody').appendChild(row));
    }

    const table = document.getElementById('tab-cmds');
    document.getElementById('sort-datetime').addEventListener('click', () => {
        sortTable(table, 0, 1);
    });
});

