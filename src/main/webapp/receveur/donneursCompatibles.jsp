<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Donneurs Compatibles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-danger mb-4">🩸 Donneurs Compatibles pour ${receveur.nom} ${receveur.prenom}</h2>

    <c:if test="${empty donneursCompatibles}">
        <div class="alert alert-warning text-center">
            Aucun donneur compatible disponible.
        </div>
    </c:if>

    <c:if test="${not empty donneursCompatibles}">
        <table class="table table-striped table-bordered text-center align-middle">
            <thead class="table-danger">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Groupe Sanguin</th>
                <th>Disponibilité</th>
                <th>Poche</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="d" items="${donneursCompatibles}">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.nom}</td>
                    <td>${d.prenom}</td>
                    <td><span class="badge bg-danger">${d.groupeSang}</span></td>
                    <td>
                        <c:choose>
                            <c:when test="${d.statusDisponibilite == 'Disponible'}">
                                <span class="badge bg-success">Disponible</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-secondary">Indisponible</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>1</td>

                    <td>
                        <form action="${pageContext.request.contextPath}/DonationServlet" method="post" class="d-flex justify-content-center">
                            <input type="hidden" name="donneurId" value="${d.id}"/>
                            <input type="hidden" name="receveurId" value="${receveur.id}"/>
                            <button type="submit" class="btn btn-sm btn-success"
                                    <c:if test="${d.statusDisponibilite != 'Disponible'}">disabled</c:if>>
                                Associer
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-secondary mt-3">⬅️ Retour</a>
</div>
</body>
</html>