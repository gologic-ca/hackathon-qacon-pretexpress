<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>PretExpress - File d'attente</title><link rel="stylesheet" href="/css/px.css"/></head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>Demandes en attente de decision</h2>
    <table>
        <tr><th>#</th><th>Membre</th><th>Montant</th><th>Soumise le</th><th>Par</th><th>Statut</th><th></th></tr>
        <c:forEach items="${dmds}" var="d">
            <tr>
                <td>${d.id}</td>
                <td>${d.mbrNom}</td>
                <td>${d.mntStr}</td>
                <td>${d.souDtStr}</td>
                <td>${d.crtBy}</td>
                <td><span class="st st-${d.statut}">${d.statutLib}</span></td>
                <td><a href="/dmd?id=${d.id}">Traiter</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty dmds}"><tr><td colspan="7">Aucune demande en attente</td></tr></c:if>
    </table>
</div>
</body>
</html>
