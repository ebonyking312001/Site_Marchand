<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <div class="row">
                        <c:forEach var="liste" items="${listeCourses}">
                            <div class="col-md-4 col-lg-4 col-sm-12">
                                <div class="card shadow-sm">
                                    <div class="card-body">
                                        <p class="card-text">
                                            <a class="text-decoration-none text-primary fw-bold"
                                               href="DetailListeCourse.jsp?id=${liste.idListeCourse}">
                                                ${liste.nomListe}
                                            </a>
                                        </p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="btn-group">
                                                <a href="DetailListeCourse.jsp?id=${liste.idListeCourse}">
                                                    <button type="button" class="btn btn-sm btn-outline-secondary">Voir les détails</button>
                                                </a>
                                            </div>
                                            <small class="text-muted">${liste.dateCreation}</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>
</t:genericpage>
