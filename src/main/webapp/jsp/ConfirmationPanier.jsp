
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:genericpage>
	<jsp:body>
        <main>
        
		<b>Choix du magasin de retrait</b><br />
		<select id="nomMagasin">
			<c:forEach var="mag" items="${requestScope.allMagasins}">
				<option>${mag.nomMagasin}</option>
			</c:forEach>
		
		</select>
		<input type="time" id="heureRetMag" name="retMag" min="09:00" max="18:00" required />
		<div id="HoraireMagasin"></div>
		
<!-- 		<b>Choix de l'heure de retrait du magasin concerné</b><br /> -->
<!-- 		<select id="heureRetMagasin"><option>-----</option></select> -->
		
        </main>
    </jsp:body>
</t:genericpage>
