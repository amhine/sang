<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Créer un Receveur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background: linear-gradient(135deg, #f8d7da 0%, #ffffff 100%); }
        .blood-drop { color: #dc3545; font-size: 2rem; }
        .form-card { border-left: 5px solid #dc3545; box-shadow: 0 4px 8px rgba(220, 53, 69, 0.1); }
        .section-header { position: relative; }
        .section-header::after { content: ''; position: absolute; bottom: -10px; left: 0; width: 50px; height: 3px; background: #dc3545; }
        .urgency-icon { color: #ffc107; }
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
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="text-danger mb-0">
                    <i class="bi bi-plus-circle-fill me-2 blood-drop"></i> Créer un Receveur
                </h2>
                <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-outline-danger">
                    <i class="bi bi-list-ul me-2"></i> Liste des Receveurs
                </a>
            </div>
            <div class="card form-card">
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/createReceveur" method="post">
                        <div class="row mb-4">
                            <div class="col-12">
                                <h4 class="text-danger section-header">
                                    <i class="bi bi-person-badge me-2"></i> Informations Personnelles
                                </h4>
                                <hr class="border-danger">
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Nom <i class="bi bi-person text-muted"></i></label>
                                <input type="text" name="nom" class="form-control" placeholder="Entrez le nom" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Prénom <i class="bi bi-person-badge-fill text-muted"></i></label>
                                <input type="text" name="prenom" class="form-control" placeholder="Entrez le prénom" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Téléphone <i class="bi bi-telephone text-muted"></i></label>
                                <input type="tel" name="telephone" class="form-control" pattern="^\d{10}$" title="10 chiffres" placeholder="Ex: 0612345678" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label fw-bold">CIN <i class="bi bi-card-text text-muted"></i></label>
                                <input type="text" name="cin" class="form-control" placeholder="Entrez le CIN" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Date de Naissance <i class="bi bi-calendar3 text-muted"></i></label>
                                <input type="date" name="dateNaissance" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Genre <i class="bi bi-gender-ambiguous text-muted"></i></label>
                                <select name="genre" class="form-select" required>
                                    <option value="">-- Sélectionner --</option>
                                    <option value="Homme">Homme</option>
                                    <option value="Femme">Femme</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Groupe Sanguin <i class="bi bi-droplet text-muted blood-drop"></i></label>
                                <select name="groupeSang" class="form-select" required>
                                    <option value="">-- Sélectionner --</option>
                                    <option value="A_PLUS">A+</option>
                                    <option value="A_MOINS">A-</option>
                                    <option value="B_PLUS">B+</option>
                                    <option value="B_MOINS">B-</option>
                                    <option value="AB_PLUS">AB+</option>
                                    <option value="AB_MOINS">AB-</option>
                                    <option value="O_PLUS">O+</option>
                                    <option value="O_MOINS">O-</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label fw-bold">Niveau d'Urgence <i class="bi bi-exclamation-triangle urgency-icon"></i></label>
                                <select name="urgence" class="form-select" required>
                                    <option value="">-- Sélectionner --</option>
                                    <option value="Critique">Critique</option>
                                    <option value="Urgent">Urgent</option>
                                    <option value="Normal">Normal</option>
                                </select>
                            </div>
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary px-5 py-2 me-3">
                                <i class="bi bi-save me-2"></i> Créer le Receveur
                            </button>
                            <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-outline-secondary px-5 py-2">
                                <i class="bi bi-x-circle me-2"></i> Annuler
                            </a>
                        </div>
                    </form>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger mt-3 d-flex align-items-center">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i> ${error}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>