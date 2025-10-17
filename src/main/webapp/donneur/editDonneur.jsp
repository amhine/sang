<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modifier Donneur</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">✏️ Modifier Donneur</h2>
    <form action="${pageContext.request.contextPath}/editDonneur" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${donneur.id}"/>

        <h4 class="text-danger">Informations Personnelles</h4>
        <hr>
        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Nom :</label>
                <input type="text" name="nom" class="form-control" value="${donneur.nom}" required>
            </div>
            <div class="col">
                <label class="form-label">Prénom :</label>
                <input type="text" name="prenom" class="form-control" value="${donneur.prenom}" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Téléphone :</label>
                <input type="text" name="telephone" class="form-control" value="${donneur.telephone}" required>
            </div>
            <div class="col">
                <label class="form-label">CIN :</label>
                <input type="text" name="cin" class="form-control" value="${donneur.cin}" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Date de naissance :</label>
                <input type="date" name="datenaissance" class="form-control" value="${donneur.dateNaissance}" required>
            </div>
            <div class="col">
                <label class="form-label">Poids (kg) :</label>
                <input type="number" name="poids" class="form-control" value="${donneur.poids}" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Genre :</label>
                <select name="genre" class="form-select" required>
                    <c:forEach var="g" items="${genres}">
                        <option value="${g}" <c:if test="${donneur.genre == g}">selected</c:if>>${g}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Groupe sanguin :</label>
                <select name="groupesang" class="form-select" required>
                    <c:forEach var="gs" items="${groupesSang}">
                        <option value="${gs}" <c:if test="${donneur.groupeSang == gs}">selected</c:if>>${gs}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <h4 class="mt-4 text-danger">Informations Médicales</h4>
        <hr>
        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Hépatite B :</label>
                <input type="checkbox" name="hepatiteB" ${donneur.medical.hepatiteB ? 'checked' : ''}>
            </div>
            <div class="col">
                <label class="form-label">Hépatite C :</label>
                <input type="checkbox" name="hepatiteC" ${donneur.medical.hepatiteC ? 'checked' : ''}>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">VIH :</label>
                <input type="checkbox" name="vih" ${donneur.medical.vih ? 'checked' : ''}>
            </div>
            <div class="col">
                <label class="form-label">Diabète :</label>
                <input type="checkbox" name="diabete" ${donneur.medical.diabete ? 'checked' : ''}>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Grossesse :</label>
                <input type="checkbox" name="grossesse" ${donneur.medical.grossesse ? 'checked' : ''}>
            </div>
            <div class="col">
                <label class="form-label">Allaitement :</label>
                <input type="checkbox" name="allaitement" ${donneur.medical.allaitement ? 'checked' : ''}>
            </div>
        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary px-4">Modifier</button>
            <a href="${pageContext.request.contextPath}/listDonneurs" class="btn btn-secondary px-4">Annuler</a>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </form>
</div>

</body>
</html>