<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Donneurs</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-danger">🩸 Liste des Donneurs</h2>
        <a href="${pageContext.request.contextPath}/createDonneur"
           class="btn btn-danger">
            ➕ Créer un Donneur
        </a>
    </div>

    <c:if test="${empty donneurs}">
        <div class="alert alert-warning text-center">
            Aucun donneur trouvé.
        </div>
    </c:if>

    <c:if test="${not empty donneurs}">
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
                        <th>Groupe Sanguin</th>
                        <th>Disponibilité</th>
                        <th>Informations Médicales</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${donneurs}">
                        <tr>
                            <td>${d.id}</td>
                            <td>${d.nom}</td>
                            <td>${d.prenom}</td>
                            <td>${d.cin}</td>
                            <td>${d.telephone}</td>
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
                            <td class="text-start">
                                <c:choose>
                                    <c:when test="${d.medical != null}">
                                        <ul class="mb-0">
                                            <li>Hépatite B : <b>${d.medical.hepatiteB ? 'Oui' : 'Non'}</b></li>
                                            <li>Hépatite C : <b>${d.medical.hepatiteC ? 'Oui' : 'Non'}</b></li>
                                            <li>VIH : <b>${d.medical.vih ? 'Oui' : 'Non'}</b></li>
                                            <li>Diabète : <b>${d.medical.diabete ? 'Oui' : 'Non'}</b></li>
                                            <li>Grossesse : <b>${d.medical.grossesse ? 'Oui' : 'Non'}</b></li>
                                            <li>Allaitement : <b>${d.medical.allaitement ? 'Oui' : 'Non'}</b></li>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <em>Aucune information médicale</em>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/edit?id=${d.id}"
                                   class="btn btn-sm btn-primary mb-1">
                                    ✏️ Modifier
                                </a>
                                <a href="${pageContext.request.contextPath}/delete?id=${d.id}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce donneur ?');">
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
