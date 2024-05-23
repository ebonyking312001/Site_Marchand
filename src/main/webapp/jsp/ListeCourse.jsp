<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Mes listes de courses</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<t:genericpage>
		<jsp:attribute name="header">
            <%@ include file="Header.jsp"%>
        </jsp:attribute>
		<jsp:attribute name="footer">
            <%@ include file="Footer.jsp"%>
        </jsp:attribute>
		<jsp:body>
            <main>
                <div class="album py-5 bg-body-tertiary">
                    <div class="container">
                        <h2 class="fw-light py-3 fw-bold">Mes listes de courses</h2>
                        <div class="d-flex justify-content-end mb-3">
                                <button type="button"
								class="btn btn-success" id="addL" data-toggle="modal"
								data-target="#ModalListeCourse">
                                    <i class="fas fa-plus"></i> Ajouter une liste
                                </button>
                        </div>
<!--                         <input class="plus_contenu" type="button" -->
<!-- 							value="+" id="plusLContenu" data-idMoreContent="plus_contenu" /> -->
<!--                         <div id="formListe"> -->
<!--                         <input type="text" id="nomListe" -->
<!-- 								style="width: 10em"> -->
<!--                     	<div class="" id="formAdd"> -->
<!-- 							<div class="mt-4"> -->
<!-- 								<select id="postit"> -->
<!-- 									<option>-----</option> -->
<!-- 								</select> -->
<!-- 								<select id="nomTypeProduit"> -->
<!-- 									<option>-----</option> -->
<!-- 								</select> -->
<!-- 								<input type="number" id="quantity" min="0" style="width: 3em"> -->
<!-- 							</div> -->
<!-- 							<div class="mt-2"> -->
<!-- 								<input type="button" value="+" id="plus_contenu" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="row text-right"> -->
<!-- 							<div class="col-2"> -->
<!-- 								<button id="delete_card" class="checkout-btn btn btn-danger">Ajouter au panier</button> -->
<!-- 							</div> -->
<!-- 							<div class="col-2"> -->
<!-- 								<button id="delete_card" class="checkout-btn btn btn-danger">Enregistrer la liste</button> -->
<!-- 							</div> -->
<!-- 							<div class="col-2"> -->
<!-- 								<button id="delete_card" class="checkout-btn btn btn-danger">Supprimer liste</button> -->
<!-- 							</div> -->
<!--                     	</div> -->
<!-- 						<hr> -->
<!-- 						</div> -->
<!--                         <div class="row"> -->
                            <c:forEach var="liste"
							items="${listeCourses}">
                                <div
								class="col-md-4 col-lg-4 col-sm-12 mt-5">
                                    <div class="card shadow-sm">
                                        <div class="card-body">
                                            <p class="card-text">
                                            <a data-toggle="modalC"
												data-id="${liste.idListe}" data-target="#idListeCreated"><button
													type="button" class="" id="addL" data-toggle="modal"
													data-target="#idListeCreated">${liste.nomListe}</button></a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                          
<!--                         </div> -->
                    </div>
                </div>
                
                <!-- Modal -->
<div class="modal fade" id="ModalListeCourse" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalCenterTitle"
					aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Nouvelle liste de courses</h5>
        <button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
            <label for="message-text"
										class="col-form-label lblTitleList">Nom de la liste :</label>
            <input type="text" class="form-control" id="message-titleList"></input>
            
            <label for="message-text"
										class="col-form-label lblTitleList">Choisissez les types de produits :</label>
										<div>
										 <c:forEach var="typeP" items="${typesProduits}">
                <input class="tpCheck" type="checkbox" id="${typeP.idTypeProduit}"
											name="tp" value="${typeP.idTypeProduit}" />
				<label for="horns">${typeP.nomTypeProduit}</label>
            </c:forEach>
										</div>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary"
									data-dismiss="modal">Fermer</button>
        <button type="button" id="plus_liste" class="btn btn-primary">Sauvegarder liste</button>
      </div>
    </div>
  </div>
</div>            
			
			</main>
        </jsp:body>
	</t:genericpage>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
