<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Créer un Receveur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">➕ Créer un Receveur</h2>

    <form action="${pageContext.request.contextPath}/createReceveur" method="post">
        <h4 class="text-danger">Informations Personnelles</h4>
        <hr>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Nom :</label>
                <input type="text" name="nom" class="form-control" required>
            </div>
            <div class="col">
                <label class="form-label">Prénom :</label>
                <input type="text" name="prenom" class="form-control" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Téléphone :</label>
                <input type="text" name="telephone" class="form-control" required pattern="^\d{10}$" title="10 chiffres">
            </div>
            <div class="col">
                <label class="form-label">CIN :</label>
                <input type="text" name="cin" class="form-control" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Date de naissance :</label>
                <input type="date" name="dateNaissance" class="form-control" required>
            </div>
            <div class="col">
                <label class="form-label">Genre :</label>
                <select name="genre" class="form-select" required>
                    <c:forEach var="g" items="${genres}">
                        <option value="${g}">${g}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Groupe sanguin :</label>
                <select name="groupeSang" class="form-select" required>
                    <c:forEach var="gs" items="${groupesSang}">
                        <option value="${gs}">${gs}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Urgence :</label>
                <select name="urgence" class="form-select" required>
                    <c:forEach var="u" items="${urgences}">
                        <option value="${u}">${u}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Statut :</label>
                <select name="receveurStatus" class="form-select" required>
                    <c:forEach var="s" items="${statuses}">
                        <option value="${s}">${s}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary px-4">Créer</button>
            <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-secondary px-4">Annuler</a>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </form>
</div>
</body>
</html>
