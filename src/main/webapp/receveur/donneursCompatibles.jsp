<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Donneurs Compatibles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background: linear-gradient(135deg, #f8d7da 0%, #ffffff 100%); }
        .blood-drop { color: #dc3545; font-size: 2rem; }
        .compatible-card { border-left: 5px solid #28a745; transition: transform 0.2s; }
        .compatible-card:hover { transform: translateY(-5px); box-shadow: 0 8px 16px rgba(40, 167, 69, 0.15); }
        .blood-badge { font-size: 0.9em; }
        .status-available { background-color: #d4edda !important; color: #155724; }
        .status-unavailable { background-color: #f8d9d9 !important; color: #721c24; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-white" href="index.jsp">
            <i class="bi bi-droplet-fill me-2 blood-drop"></i> Banque de Sang
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listDonneurs">
                        <i class="bi bi-people me-1"></i> Donneurs
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/listReceveurs">
                        <i class="bi bi-heart-fill me-1"></i> Receveurs
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-danger">
            <i class="bi bi-link-45deg me-2 blood-drop"></i> Donneurs Compatibles pour ${receveur.nom} ${receveur.prenom}
        </h2>
        <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-outline-danger">
            <i class="bi bi-arrow-left me-2"></i> Retour aux Receveurs
        </a>
    </div>

    <c:if test="${empty donneursCompatibles}">
        <div class="alert alert-warning text-center d-flex align-items-center justify-content-center">
            <i class="bi bi-exclamation-triangle me-2"></i> Aucun donneur compatible disponible.
            <a href="${pageContext.request.contextPath}/listDonneurs" class="ms-3 btn btn-outline-warning btn-sm">
                <i class="bi bi-people me-1"></i> Voir Tous les Donneurs
            </a>
        </div>
    </c:if>

    <c:if test="${not empty donneursCompatibles}">
        <div class="row g-4">
            <c:forEach var="d" items="${donneursCompatibles}">
                <div class="col-lg-6 col-xl-4">
                    <div class="card compatible-card h-100">
                        <div class="card-header bg-light d-flex justify-content-between align-items-center">
                            <h6 class="mb-0 text-success fw-bold">
                                <i class="bi bi-person-check me-1"></i> ${d.nom} ${d.prenom}
                            </h6>
                            <span class="badge blood-badge bg-success">${d.groupeSang}</span>
                        </div>
                        <div class="card-body text-center">
                            <ul class="list-unstyled mb-3">
                                <li class="mb-2"><i class="bi bi-telephone text-muted me-2"></i> Tél: ${d.telephone}</li>
                                <div class="mb-3">
                                    <span class="badge <c:choose><c:when test="${d.statusDisponibilite == 'Disponible'}">status-available</c:when><c:otherwise>status-unavailable</c:otherwise></c:choose> fs-6 px-3 py-2">
                                        <i class="bi bi-${d.statusDisponibilite == 'Disponible' ? 'check-circle-fill' : 'x-circle-fill'} me-1"></i> ${d.statusDisponibilite}
                                    </span>
                                </div>
                            </ul>
                            <p class="text-muted mb-3">Poche disponible: <strong>1</strong></p>
                            <form action="${pageContext.request.contextPath}/DonationServlet" method="post" class="d-inline">
                                <input type="hidden" name="donneurId" value="${d.id}"/>
                                <input type="hidden" name="receveurId" value="${receveur.id}"/>
                                <button type="submit" class="btn btn-success px-4 py-2 w-100"
                                        <c:if test="${d.statusDisponibilite != 'Disponible'}">disabled</c:if>>
                                    <i class="bi bi-heart-fill me-2"></i> Associer ce Donneur
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-secondary">
            <i class="bi bi-arrow-left me-2"></i> Retour à la Liste des Receveurs
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>