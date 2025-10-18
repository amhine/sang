<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Receveurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background: linear-gradient(135deg, #f8d7da 0%, #ffffff 100%); }
        .blood-drop { color: #dc3545; font-size: 2rem; }
        .receiver-card { border-left: 5px solid #dc3545; transition: transform 0.2s; }
        .receiver-card:hover { transform: translateY(-5px); box-shadow: 0 8px 16px rgba(220, 53, 69, 0.15); }
        .blood-badge { font-size: 0.9em; }
        .urgency-critique { background-color: #f8d7da !important; color: #721c24; }
        .urgency-urgent { background-color: #fff3cd !important; color: #856404; }
        .urgency-normal { background-color: #d4edda !important; color: #155724; }
        .status-waiting { background-color: #e2e3e5 !important; color: #383d41; }
        .status-satisfied { background-color: #d4edda !important; color: #155724; }
        .avail-available { background-color: #d4edda !important; color: #155724; }
        .avail-unavailable { background-color: #f8d9d9 !important; color: #721c24; }
        .donor-badges { max-height: 80px; overflow-y: auto; }
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
            <i class="bi bi-list-nested me-2 blood-drop"></i> Liste des Receveurs
        </h2>
        <a href="${pageContext.request.contextPath}/createReceveur" class="btn btn-danger">
            <i class="bi bi-plus-circle me-2"></i> Créer un Receveur
        </a>
    </div>

    <c:if test="${empty receveurs}">
        <div class="alert alert-warning text-center d-flex align-items-center justify-content-center">
            <i class="bi bi-exclamation-triangle me-2"></i> Aucun receveur trouvé.
            <a href="${pageContext.request.contextPath}/createReceveur" class="ms-2 btn btn-outline-warning btn-sm">
                <i class="bi bi-plus-circle me-1"></i> Ajoutez-en un !
            </a>
        </div>
    </c:if>

    <c:if test="${not empty receveurs}">
        <div class="row g-4">
            <c:forEach var="r" items="${receveurs}">
                <div class="col-lg-6 col-xl-4">
                    <div class="card receiver-card h-100">
                        <div class="card-header bg-light d-flex justify-content-between align-items-center">
                            <h6 class="mb-0 text-danger fw-bold">
                                <i class="bi bi-person-heart me-1"></i> ${r.nom} ${r.prenom}
                            </h6>
                            <span class="badge blood-badge bg-danger">${r.groupeSang}</span>
                        </div>
                        <div class="card-body">
                            <ul class="list-unstyled mb-3">
                                <li class="mb-1"><i class="bi bi-card-text text-muted me-2"></i> CIN: ${r.cin}</li>
                                <li class="mb-1"><i class="bi bi-telephone text-muted me-2"></i> Tél: ${r.telephone}</li>
                                <li class="mb-1"><i class="bi bi-calendar3 text-muted me-2"></i> Naissance: ${r.dateNaissance}</li>
                                <li class="mb-1"><i class="bi bi-gender-ambiguous text-muted me-2"></i> Genre: ${r.genre}</li>
                            </ul>
                            <div class="row mb-3 g-2">
                                <div class="col-6">
                                    <span class="badge <c:choose><c:when test="${r.urgence == 'Critique'}">urgency-critique</c:when><c:when test="${r.urgence == 'Urgent'}">urgency-urgent</c:when><c:otherwise>urgency-normal</c:otherwise></c:choose> w-100">Urgence: ${r.urgence}</span>
                                </div>
                                <div class="col-6">
                                    <span class="badge <c:choose><c:when test="${r.receveurStatus == 'En_attente'}">status-waiting</c:when><c:when test="${r.receveurStatus == 'Satisfait'}">status-satisfied</c:when><c:otherwise>bg-dark</c:otherwise></c:choose> w-100">Status: ${r.receveurStatus}</span>
                                </div>
                            </div>
                            <div class="mb-3">
                                <span class="badge <c:choose><c:when test="${r.receveurStatus != 'Satisfait'}">avail-available</c:when><c:otherwise>avail-unavailable</c:otherwise></c:choose> fs-6 px-3 py-2">
                                    <i class="bi bi-${r.receveurStatus != 'Satisfait' ? 'check-circle-fill' : 'x-circle-fill'} me-1"></i> ${r.receveurStatus != 'Satisfait' ? 'Disponible' : 'Indisponible'}
                                </span>
                            </div>
                            <c:if test="${not empty r.donations}">
                                <div class="donor-badges mb-3">
                                    <small class="text-muted fw-bold mb-1 d-block">Donneurs Associés:</small>
                                    <c:forEach var="d" items="${r.donations}">
                                        <span class="badge bg-info text-dark me-1 mb-1 d-inline-block">
                                            ${d.donneur.nom} ${d.donneur.prenom}
                                        </span>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${empty r.donations}">
                                <small class="text-muted">Aucun donneur associé.</small>
                            </c:if>
                        </div>
                        <div class="card-footer bg-transparent">
                            <div class="d-grid gap-2">
                                <form action="${pageContext.request.contextPath}/DonationServlet" method="get" class="mb-2">
                                    <input type="hidden" name="receveurId" value="${r.id}"/>
                                    <button type="submit" class="btn btn-outline-success">
                                        <i class="bi bi-link-45deg me-1"></i> Voir Donneurs Compatibles
                                    </button>
                                </form>
                                <a href="${pageContext.request.contextPath}/editReceveur?id=${r.id}" class="btn btn-outline-primary">
                                    <i class="bi bi-pencil me-1"></i> Modifier
                                </a>
                                <a href="${pageContext.request.contextPath}/deleteReceveur?id=${r.id}" class="btn btn-outline-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce receveur ?');">
                                    <i class="bi bi-trash me-1"></i> Supprimer
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>