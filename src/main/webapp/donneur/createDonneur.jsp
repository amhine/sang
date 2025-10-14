<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Création d'un Donneur</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4 text-danger">🩸 Formulaire de Donneur</h2>

    <form action="${pageContext.request.contextPath}/donneur" method="post" class="card p-4 shadow-sm">

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
                <input type="text" name="telephone" class="form-control"
                       pattern="^0[5-7][0-9]{8}$" placeholder="Ex: 0612345678" required>
            </div>
            <div class="col">
                <label class="form-label">CIN :</label>
                <input type="text" name="cin" class="form-control" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Date de Naissance :</label>
                <input type="date" name="datenaissance" class="form-control" required>
            </div>
            <div class="col">
                <label class="form-label">Poids (kg) :</label>
                <input type="number" name="poids" class="form-control" min="40" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Genre :</label>
                <select name="genre" class="form-select" required>
                    <option value="">-- Sélectionner --</option>
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                </select>
            </div>

            <div class="col">
                <label class="form-label">Groupe Sanguin :</label>
                <select name="groupesang" class="form-select" required>
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
        </div>

        <h4 class="mt-4 text-danger">Informations Médicales</h4>
        <hr>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Hépatite B :</label>
                <select name="hepatiteB" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Hépatite C :</label>
                <select name="hepatiteC" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">VIH :</label>
                <select name="vih" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Diabète :</label>
                <select name="diabete" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Grossesse :</label>
                <select name="grossesse" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
            <div class="col">
                <label class="form-label">Allaitement :</label>
                <select name="allaitement" class="form-select">
                    <option value="false" selected>Non</option>
                    <option value="true">Oui</option>
                </select>
            </div>
        </div>

        <div class="text-center mt-4">
            <button type="submit" class="btn btn-danger px-4">Enregistrer</button>
            <a href="${pageContext.request.contextPath}/listDonneurs"
               class="btn btn-secondary px-4">Annuler</a>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
</div>

</body>
</html>
