<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="Mandje"/>
</head>
<body>
<vdab:menu/>
<h1>Mandje</h1>

<c:if test="${empty bestelbonnummer and empty basket}">
    <h3>Je hebt hier niks meer te zoeken ;)</h3>
</c:if>

<c:if test="${not empty bestelbonnummer}">
    <h2>Je mandje is bevestigd als bestelbon <b>${bestelbonnummer}</b></h2>
</c:if>

<c:if test="${not empty basket}">
<table>
    <thead>
    <tr>
        <th>Wijn</th>
        <th>Prijs</th>
        <th>Aantal</th>
        <th>Totaal</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${basket}" var="wijn">
        <tr>
            <th>${wijn.key.soortid.landid.naam} ${wijn.key.soortid.naam} ${wijn.key.jaar}</th>
            <th>&euro; <fmt:formatNumber value="${wijn.key.prijs}" minFractionDigits='2' maxFractionDigits='2'/></th>
            <th>${wijn.value}</th>
            <th>&euro; <fmt:formatNumber value='${wijn.key.prijs * wijn.value}' minFractionDigits='2' maxFractionDigits='2'/></th>
        </tr>
        <c:set var="totaal" value="${totaal + (wijn.key.prijs * wijn.value)}"/>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <th colspan="2"></th>
        <th>Totaal :</th>
        <th>&euro; <fmt:formatNumber value='${totaal}' minFractionDigits='2' maxFractionDigits='2'/></th>
    </tr>
    </tfoot>
</table>
    <br><br>
    <form method="post">
    <label>Naam<span>${fouten.naam}</span>
    <input type="text" name="naam" value="${param.naam}" required autofocus></label>

    <label>Straat<span>${fouten.straat}</span>
        <input type="text" name="straat" value="${param.straat}" required></label>

    <label>Huisnummer<span>${fouten.huisnummer}</span>
        <input type="text" name="huisnummer" value="${param.huisnummer}" required></label>

    <label>Postcode<span>${fouten.postcode}</span>
        <input type="text" name="postcode" value="${param.postcode}" required></label>

    <label>Gemeente<span>${fouten.gemeente}</span>
        <input type="text" name="gemeente" value="${param.gemeente}" required></label>

    <label><span>${fouten.afhalenOpsturen}</span>
        <input type='radio' name='afhalenOpsturen' value='0'${param.geslacht=='0' ? 'checked' : ''}>Afhalen in onze winkel</label>
        <br>
    <label>
        <input type='radio' name='afhalenOpsturen' value='1'${param.geslacht=='1' ? 'checked' : ''}>Opsturen naar uw adres</label>

    <input type='submit' value='Bestellen'>
    </form>
</c:if>
</body>
</html>