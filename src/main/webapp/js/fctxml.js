
/**
 * Add article by Id
 */
function addArticleById(event) {
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	console.log("event : " + event);
	xhr.open("GET", "ServletPanier?idArticle=" + event, true);

	// Nothing to do
	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	//	xhr.onload = function() {
	//		// Si la requête http s'est bien passée.
	//		if (xhr.status === 200) {
	//			
	//		}
	//	};

	// Envoie de la requête.
	xhr.send();
}

/**
 * Loads after build of DOM
 */
document.addEventListener("DOMContentLoaded", () => {
	$('.byalpha').on('click',
		//		{
		//			'idArt': function(element) {
		//				console.log("function element : " + $(element));
		//				console.log($(element).data('idArt'));
		//				return $(element).data('idArt');
		//			}
		//		},
		function(event) {
			//			console.log(event);
			//			console.log(event.target.dataset.idart);
			addArticleById(event.target.dataset.idart);
		}
	);

});
