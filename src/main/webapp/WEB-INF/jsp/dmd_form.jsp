<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>PretExpress - Nouvelle demande</title>
    <link rel="stylesheet" href="/css/px.css"/>
    <script>
        function valider() {
            var mnt = parseFloat(document.getElementById('mnt').value);
            if (isNaN(mnt) || mnt < 1000) {
                alert('Montant minimum : 1 000 $');
                return false;
            }
            if (mnt > 50000) {
                alert('Montant maximum : 50 000 $');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>Nouvelle demande : ${mbr.nomComplet}</h2>

    <c:if test="${not empty errs}">
        <div class="err">
            Demande non admissible :
            <ul><c:forEach items="${errs}" var="e"><li>${e}</li></c:forEach></ul>
        </div>
    </c:if>

    <form action="/dmd/new" method="post" onsubmit="return valider();" class="frm">
        <input type="hidden" name="mbrId" value="${mbr.id}"/>

        <label>Montant demande ($)</label>
        <input type="text" id="mnt" name="mnt" value="${mnt}"/>

        <label>Duree</label>
        <select name="duree">
            <c:forEach begin="12" end="84" step="12" var="n">
                <option value="${n}" ${n == duree ? 'selected' : ''}>${n} mois</option>
            </c:forEach>
        </select>

        <div>
            <button type="submit">Calculer et creer</button>
            <a href="/mbr?id=${mbr.id}">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
