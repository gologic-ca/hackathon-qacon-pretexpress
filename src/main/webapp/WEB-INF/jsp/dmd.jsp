<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>PretExpress - Demande ${d.id}</title><link rel="stylesheet" href="/css/px.css"/></head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>Demande #${d.id} <span class="st st-${d.statut}">${d.statutLib}</span></h2>
    <p>Membre : <a href="/mbr?id=${mbr.id}">${mbr.nomComplet}</a> (cote ${mbr.cote})</p>

    <table class="kv">
        <tr><th>Montant demande</th><td>${d.mntStr}</td></tr>
        <tr><th>Frais de dossier</th><td>${d.fraisStr}</td></tr>
        <tr><th>Duree</th><td>${d.duree} mois</td></tr>
        <tr><th>Taux annuel</th><td>${d.tauxStr}</td></tr>
        <tr><th>Paiement mensuel</th><td><b>${d.pmtStr}</b></td></tr>
        <tr><th>Total rembourse</th><td>${d.totalStr}</td></tr>
        <tr><th>Creee par</th><td>${d.crtBy} le ${d.crtDtStr}</td></tr>
        <tr><th>Soumise le</th><td>${d.souDtStr}</td></tr>
        <tr><th>Decision</th><td>${d.decBy} ${d.decDtStr}</td></tr>
        <tr><th>Motif</th><td>${d.motif}</td></tr>
    </table>

    <div class="acts">
        <c:if test="${d.statut == 'BRO'}">
            <form action="/dmd/act" method="post">
                <input type="hidden" name="id" value="${d.id}"/>
                <button name="act" value="SOU">Soumettre pour approbation</button>
            </form>
        </c:if>

        <%-- au-dessus de 25 000 $, seul un analyste peut decider --%>
        <c:if test="${d.statut == 'SOU' and (usr.ana or d.mnt <= 25000)}">
            <form action="/dmd/act" method="post">
                <input type="hidden" name="id" value="${d.id}"/>
                <button name="act" value="APP" class="ok">Approuver</button>
            </form>
            <form action="/dmd/act" method="post">
                <input type="hidden" name="id" value="${d.id}"/>
                <input type="text" name="motif" placeholder="Motif du refus"/>
                <button name="act" value="REF" class="ko">Refuser</button>
            </form>
        </c:if>

        <c:if test="${d.statut == 'APP'}">
            <form action="/dmd/act" method="post">
                <input type="hidden" name="id" value="${d.id}"/>
                <button name="act" value="DEC">Decaisser les fonds</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
