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
                            <a href="AddListeCourse.jsp">
                                <button type="button"
									class="btn btn-success">
                                    <i class="fas fa-plus"></i> Ajouter une liste
                                </button>
                            </a>
                        </div>
                    	<div class="" id="formAdd">
							<div>
								<input type="text" name="zone" size="20" maxlength="20" /><span></span>
								<select id="nomTypeProduit">
									<option>-----</option>
<%-- 									<c:forEach var="mag" items="${requestScope.allMagasins}"> --%>
<%-- 										<option value="${mag.nomMagasin}">${mag.nomMagasin}</option> --%>
<%-- 									</c:forEach> --%>
								
								</select>
							</div>
							<div class="mt-2">
								<input type="button" value="+" id="plus" disabled />
							</div>
						</div>
<!--                         <div class="row"> -->
<%--                             <c:forEach var="liste" items="${listeCourses}"> --%>
<!--                                 <div class="col-md-4 col-lg-4 col-sm-12"> -->
<!--                                     <div class="card shadow-sm"> -->
<!--                                         <div class="card-body"> -->
<!--                                             <p class="card-text"> -->
<!--                                                 <a class="text-decoration-none text-primary fw-bold" -->
<%--                                                    href="DetailListeCourse.jsp?id=${liste.idListeCourse}"> --%>
<%--                                                     ${liste.nomListe} --%>
<!--                                                 </a> -->
<!--                                             </p> -->
<!--                                             <div class="d-flex justify-content-between align-items-center"> -->
<!--                                                 <div class="btn-group"> -->
<%--                                                     <a href="DetailListeCourse.jsp?id=${liste.idListeCourse}"> --%>
<!--                                                         <button type="button" class="btn btn-sm btn-outline-secondary">Voir les détails</button> -->
<!--                                                     </a> -->
<!--                                                 </div> -->
<%--                                                 <small class="text-muted">${liste.dateCreation}</small> --%>
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<%--                             </c:forEach> --%>
<!--                         </div> -->
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
