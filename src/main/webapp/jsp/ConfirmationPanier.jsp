
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:genericpage>
	<jsp:body>
        <main>
        
        <b>Choix du magasin de retrait</b>
        <div class="row">
        <div class="col-2">
        <select id="nomMagasin">
		<option>-----</option>
			<c:forEach var="mag" items="${requestScope.allMagasins}">
				<option value="${mag.nomMagasin}">${mag.nomMagasin}</option>
			</c:forEach>
		
		</select>
        </div>
		<div class="col-2">
		<input type="time" id="heureRetMag" name="retMag" min="00:00"
						max="24:00" required />
		</div>
		
		<div id="HoraireMagasin"></div>
        </div>
		
		
<!-- 		<b>Choix de l'heure de retrait du magasin concerné</b><br /> -->
<!-- 		<select id="heureRetMagasin"><option>-----</option></select> -->

		<button class="checkout-btn" id="final_validation">Valider la commande</button>
		
        </main>
    </jsp:body>
</t:genericpage>
