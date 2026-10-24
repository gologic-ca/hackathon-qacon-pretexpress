<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>PretExpress - Membres</title><link rel="stylesheet" href="/css/px.css"/></head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>Membres</h2>
    <form action="/mbrs" method="get">
        <input type="text" name="q" value="${q}" placeholder="Nom ou no de membre"/>
        <button type="submit">Chercher</button>
    </form>
    <table>
        <tr><th>No</th><th>Nom</th><th>Membre depuis</th><th>Cote</th><th>Admissible</th><th></th></tr>
        <c:forEach items="${mbrs}" var="m">
            <tr>
                <td>${m.noMbr}</td>
                <td>${m.nomComplet} <c:if test="${m.typ == 'EMP'}"><span class="tag">EMP</span></c:if></td>
                <td>${m.dtAdhStr}</td>
                <td>${m.cote}</td>
                <td><span class="dot ${m.elig ? 'ok' : 'ko'}"></span></td>
                <td><a href="/mbr?id=${m.id}">Ouvrir</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
