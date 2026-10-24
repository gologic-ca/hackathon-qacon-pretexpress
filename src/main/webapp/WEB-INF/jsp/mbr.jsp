<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>PretExpress - Fiche membre</title><link rel="stylesheet" href="/css/px.css"/></head>
<body>
<%@ include file="/WEB-INF/jsp/header.jspf" %>
<div class="box">
    <h2>${mbr.nomComplet} <small>no ${mbr.noMbr}</small></h2>

    <c:set var="ratio" value="${mbr.dettesMens / (mbr.revAnn / 12)}"/>
    <table class="kv">
        <tr><th>Membre depuis</th><td>${mbr.dtAdhStr}</td></tr>
        <tr><th>Type</th><td>${mbr.typ == 'EMP' ? 'Employe' : 'Regulier'}</td></tr>
        <tr><th>Revenu annuel</th><td>${mbr.revAnnStr}</td></tr>
        <tr><th>Dettes mensuelles</th><td>${mbr.dettesMensStr}</td></tr>
        <tr><th>Ratio d'endettement</th>
            <td class="${ratio > 0.40 ? 'warn' : ''}">
                <fmt:formatNumber value="${ratio}" type="percent" maxFractionDigits="1"/>
            </td></tr>
        <tr><th>Cote de credit</th><td>${mbr.cote}</td></tr>
    </table>

    <p><a class="btn" href="/dmd/new?mbrId=${mbr.id}">Nouvelle demande de pret</a></p>

    <h3>Demandes</h3>
    <table>
        <tr><th>#</th><th>Montant</th><th>Duree</th><th>Taux</th><th>Paiement</th><th>Statut</th><th></th></tr>
        <c:forEach items="${dmds}" var="d">
            <tr>
                <td>${d.id}</td>
                <td>${d.mntStr}</td>
                <td>${d.duree} mois</td>
                <td>${d.tauxStr}</td>
                <td>${d.pmtStr}</td>
                <td><span class="st st-${d.statut}">${d.statutLib}</span></td>
                <td><a href="/dmd?id=${d.id}">Voir</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty dmds}"><tr><td colspan="7">Aucune demande</td></tr></c:if>
    </table>
</div>
</body>
</html>
