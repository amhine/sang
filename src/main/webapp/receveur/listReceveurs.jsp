<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Receveurs</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-danger">🩸 Liste des Receveurs</h2>
        <a href="${pageContext.request.contextPath}/createReceveur"
           class="btn btn-danger">
            ➕ Créer un Receveur
        </a>
    </div>

    <c:if test="${empty receveurs}">
        <div class="alert alert-warning text-center">
            Aucun receveur trouvé.
        </div>
    </c:if>

    <c:if test="${not empty receveurs}">
        <div class="card shadow-sm">
            <div class="card-body">
                <table class="table table-striped table-bordered align-middle text-center">
                    <thead class="table-danger">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>CIN</th>
                        <th>Téléphone</th>
                        <th>Genre</th>
                        <th>Groupe Sanguin</th>
                        <th>Urgence</th>
                        <th>Status</th>
                        <th>Disponibilité</th>
                        <th>Donneurs Associés</th>
                        <th>Voir Donneurs Compatibles</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="r" items="${receveurs}">
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.nom}</td>
                            <td>${r.prenom}</td>
                            <td>${r.cin}</td>
                            <td>${r.telephone}</td>
                            <td>${r.genre}</td>
                            <td><span class="badge bg-danger">${r.groupeSang}</span></td>
                            <td>
                                <c:choose>
                                    <c:when test="${r.urgence == 'Critique'}">
                                        <span class="badge bg-danger">Critique</span>
                                    </c:when>
                                    <c:when test="${r.urgence == 'Urgent'}">
                                        <span class="badge bg-warning text-dark">Urgent</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-success">Normal</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${r.receveurStatus == 'En_attente'}">
                                        <span class="badge bg-secondary">En attente</span>
                                    </c:when>
                                    <c:when test="${r.receveurStatus == 'Satisfait'}">
                                        <span class="badge bg-success">Satisfait</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-dark">Inconnu</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${r.receveurStatus != 'Satisfait'}">
                                        <span class="badge bg-success">Disponible</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">Indisponible</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${not empty r.donations}">
                                    <c:forEach var="d" items="${r.donations}">
                                        <span class="badge bg-info text-dark mb-1">
                                            ${d.donneur.nom} ${d.donneur.prenom}
                                        </span>
                                        <br/>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty r.donations}">
                                    <span class="text-muted">Aucun donneur</span>
                                </c:if>
                            </td>


                            <td>
                                <!-- Bouton pour voir les donneurs compatibles -->
                                <form action="${pageContext.request.contextPath}/DonationServlet" method="get" class="d-inline">
                                    <input type="hidden" name="receveurId" value="${r.id}"/>
                                    <button type="submit" class="btn btn-sm btn-success mb-1">
                                        🩸 Voir Donneurs Compatibles
                                    </button>
                                </form>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/editReceveur?id=${r.id}"
                                   class="btn btn-sm btn-primary mb-1">
                                    ✏️ Modifier
                                </a>
                                <a href="${pageContext.request.contextPath}/deleteReceveur?id=${r.id}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce receveur ?');">
                                    🗑️ Supprimer
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>