<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Donneurs</title>
    <style>
        table { border-collapse: collapse; width: 90%; margin: auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        h1 { text-align: center; }
    </style>
</head>
<body>
<h1>Liste des Donneurs</h1>

<c:if test="${empty donneurs}">
    <p style="text-align:center;">Aucun donneur trouvé.</p>
</c:if>

<c:if test="${not empty donneurs}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>CIN</th>
            <th>Téléphone</th>
            <th>Groupe Sanguin</th>
            <th>Disponibilité</th>
            <th>Informations Médicales</th>
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
                <td>${d.groupeSang}</td>
                <td>${d.statusDisponibilite}</td>
                <td>
                    <c:choose>
                        <c:when test="${d.medical != null}">
                            Hépatite B: ${d.medical.hepatiteB ? 'Oui' : 'Non'}<br/>
                            Hépatite C: ${d.medical.hepatiteC ? 'Oui' : 'Non'}<br/>
                            VIH: ${d.medical.vih ? 'Oui' : 'Non'}<br/>
                            Diabète: ${d.medical.diabete ? 'Oui' : 'Non'}<br/>
                            Grossesse: ${d.medical.grossesse ? 'Oui' : 'Non'}<br/>
                            Allaitement: ${d.medical.allaitement ? 'Oui' : 'Non'}
                        </c:when>
                        <c:otherwise>
                            Aucune information médicale
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>