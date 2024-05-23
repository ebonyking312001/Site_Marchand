<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:genericpage>
    <jsp:attribute name="header">
        <%@ include file="Header.jsp"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <%@ include file="Footer.jsp"%>
    </jsp:attribute>
    <jsp:body>
        <style>
            .form-signin {
                max-width: 330px;
                padding: 1rem;
            }
            .form-signin .form-floating:focus-within {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
            .error-message {
                color: red;
                margin-bottom: 10px;
            }
        </style>
        <main class="form-signin w-100 m-auto border border-1 mt-2">
            <form action="<c:url value='/CtrlAuthentification' />" method="post">
                <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>

                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com" required>
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">Remember me</label>
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
            </form>
        </main>
    </jsp:body>
</t:genericpage>
