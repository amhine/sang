<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Modifier Receveur</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">✏️ Modifier Receveur</h2>
    <form action="${pageContext.request.contextPath}/editReceveur" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${receveur.id}"/>

        <h4 class="text-danger">Informations Personnelles</h4>
        <hr>
        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Nom :</label>
                <input type="text" name="nom" class="form-control" value="${receveur.nom}" required>
            </div>
            <div class="col">
                <label class="form-label">Prénom :</label>
                <input type="text" name="prenom" class="form-control" value="${receveur.prenom}" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Téléphone :</label>
                <input type="text" name="telephone" class="form-control" value="${receveur.telephone}" required>
            </div>
            <div class="col">
                <label class="form-label">CIN :</label>
                <input type="text" name="cin" class="form-control" value="${receveur.cin}" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Date de naissance :</label>
                <input type="date" name="datenaissance" class="form-control" value="${receveur.dateNaissance}" required>
            </div>

        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Genre :</label>
                <select name="genre" class="form-select" required>
                    <c:forEach var="g" items="${genres}">
                        <option value="${g}" <c:if test="${receveur.genre == g}">selected</c:if>>${g}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Groupe sanguin :</label>
                <select name="groupesang" class="form-select" required>
                    <c:forEach var="gs" items="${groupesSang}">
                        <option value="${gs}" <c:if test="${receveur.groupeSang == gs}">selected</c:if>>${gs}</option>
                    </c:forEach>
                </select>
            </div>
        </div>




        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary px-4">Modifier</button>
            <a href="${pageContext.request.contextPath}/listReceveurs" class="btn btn-secondary px-4">Annuler</a>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </form>
</div>

</body>
</html>