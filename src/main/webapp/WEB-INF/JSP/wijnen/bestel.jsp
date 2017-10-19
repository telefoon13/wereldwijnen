<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="Wereld wijnen"/>
</head>
<body>
<vdab:menu/>
<h1>Wijn aan mandje toevoegen</h1>

<a href="/soorten/zoekopland.htm?id=${wijn.soortid.landid.id}&soort=${wijn.soortid.id}">Terug naar overzicht</a>

<dl>
    <dt>Land</dt>
    <dd>${wijn.soortid.landid.naam}</dd>
<br>
    <dt>Soort</dt>
    <dd>${wijn.soortid.naam}</dd>
    <br>
    <dt>Jaar</dt>
    <dd>${wijn.jaar}</dd>
    <br>
    <dt>Beoordeling</dt>
    <dd><c:forEach begin="1" end="${wijn.beoordeling}">
        &#9733;
    </c:forEach></dd>
    <br>
    <dt>Prijs</dt>
    <dd>&euro; <fmt:formatNumber value='${wijn.prijs}' minFractionDigits='2' maxFractionDigits='2'/></dd>
    <br>
    <form method="post">
        <label>Aantal Flessen
            <span>${fouten.aantal}</span>
            <input name='aantal' <c:if test="${basket ne null}">
                   value="${basket[wijn]}"
            </c:if> required autofocus type='number' min='1'>
            <input type="hidden" value="${wijn.id}" name="wijnid"></label>
        <input type='submit' value='Toevoegen aan winkelwagentje'>
    </form>
        </label>
    </form>

</dl>

</body>
</html>