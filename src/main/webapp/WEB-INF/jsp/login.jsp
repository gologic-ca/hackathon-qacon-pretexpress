<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>PretExpress - Connexion</title><link rel="stylesheet" href="/css/px.css"/></head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>Connexion</h2>
    <p>Choisir un utilisateur :</p>
    <form action="/login" method="post">
        <select name="code">
            <c:forEach items="${usrs}" var="u">
                <option value="${u.code}">${u.nom} - ${u.role == 'ANA' ? 'Analyste credit' : 'Conseiller'}</option>
            </c:forEach>
        </select>
        <button type="submit">Entrer</button>
    </form>
</div>
</body>
</html>
