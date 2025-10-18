<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Donneurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background: linear-gradient(135deg, #f8d7da 0%, #ffffff 100%); }
        .blood-drop { color: #dc3545; font-size: 2rem; }
        .donor-card { border-left: 5px solid #dc3545; transition: transform 0.2s; }
        .donor-card:hover { transform: translateY(-5px); box-shadow: 0 8px 16px rgba(220, 53, 69, 0.15); }
        .blood-badge { font-size: 0.9em; }
        .status-available { background-color: #d4edda !important; color: #155724; }
        .status-unavailable { background-color: #f8d9d9 !important; color: #721c24; }
        .medical-list { font-size: 0.85em; max-height: 100px; overflow-y: auto; }
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
                    <a class="nav-link active" href="${pageContext.request.contextPath}/listDonneurs">
                        <i class="bi bi-people me-1"></i> Donneurs
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listReceveurs">
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
            <i class="bi bi-list-nested me-2 blood-drop"></i> Liste des Donneurs
        </h2>
        <a href="${pageContext.request.contextPath}/createDonneur" class="btn btn-danger">
            <i class="bi bi-plus-circle me-2"></i> Créer un Donneur
        </a>
    </div>

    <c:if test="${empty donneurs}">
        <div class="alert alert-warning text-center d-flex align-items-center justify-content-center">
            <i class="bi bi-exclamation-triangle me-2"></i> Aucun donneur trouvé. <a href="${pageContext.request.contextPath}/createDonneur" class="ms-2 btn btn-outline-warning btn-sm">Ajoutez-en un !</a>
        </div>
    </c:if>

    <c:if test="${not empty donneurs}">
        <div class="row g-4">
            <c:forEach var="d" items="${donneurs}">
                <div class="col-lg-6 col-xl-4">
                    <div class="card donor-card h-100">
                        <div class="card-header bg-light d-flex justify-content-between align-items-center">
                            <h6 class="mb-0 text-danger fw-bold">
                                <i class="bi bi-person-circle me-1"></i> ${d.nom} ${d.prenom}
                            </h6>
                            <span class="badge blood-badge bg-danger">${d.groupeSang}</span>
                        </div>
                        <div class="card-body">
                            <ul class="list-unstyled mb-3">
                                <li class="mb-1"><i class="bi bi-card-text text-muted me-2"></i> CIN: ${d.cin}</li>
                                <li class="mb-1"><i class="bi bi-telephone text-muted me-2"></i> Tél: ${d.telephone}</li>
                                <li class="mb-1"><i class="bi bi-calendar3 text-muted me-2"></i> Naissance: ${d.dateNaissance}</li>
                                <li class="mb-1"><i class="bi bi-ui-checks text-muted me-2"></i> Poids: ${d.poids} kg</li>
                            </ul>
                            <div class="mb-3">
                                <span class="badge <c:choose><c:when test="${d.statusDisponibilite == 'Disponible'}">status-available</c:when><c:otherwise>status-unavailable</c:otherwise></c:choose> fs-6 px-3 py-2">
                                    <i class="bi bi-${d.statusDisponibilite == 'Disponible' ? 'check-circle-fill' : 'x-circle-fill'} me-1"></i> ${d.statusDisponibilite}
                                </span>
                            </div>
                            <c:choose>
                                <c:when test="${d.medical != null}">
                                    <div class="medical-list">
                                        <small class="text-muted fw-bold mb-1 d-block">Santé:</small>
                                        <ul class="mb-0 small">
                                            <li><i class="bi bi-check-lg text-success me-1"></i> Hépatite B: ${d.medical.hepatiteB ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                            <li><i class="bi bi-check-lg text-success me-1"></i> Hépatite C: ${d.medical.hepatiteC ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                            <li><i class="bi bi-check-lg text-success me-1"></i> VIH: ${d.medical.vih ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                            <li><i class="bi bi-check-lg text-success me-1"></i> Diabète: ${d.medical.diabete ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                            <li><i class="bi bi-check-lg text-success me-1"></i> Grossesse: ${d.medical.grossesse ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                            <li><i class="bi bi-check-lg text-success me-1"></i> Allaitement: ${d.medical.allaitement ? '<span class="text-danger">Oui</span>' : 'Non'}</li>
                                        </ul>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <small class="text-muted">Aucune information médicale disponible.</small>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="card-footer bg-transparent">
                            <div class="d-flex gap-2">
                                <a href="${pageContext.request.contextPath}/editDonneur?id=${d.id}" class="btn btn-sm btn-outline-primary flex-fill">
                                    <i class="bi bi-pencil me-1"></i> Modifier
                                </a>
                                <a href="${pageContext.request.contextPath}/delete?id=${d.id}" class="btn btn-sm btn-outline-danger flex-fill" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce donneur ?');">
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