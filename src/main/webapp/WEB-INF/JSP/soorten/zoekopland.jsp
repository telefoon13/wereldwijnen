<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="Wereld wijnen"/>
</head>
<body>
<vdab:menu/>
<h1>${land.naam}</h1>
<c:if test="${not empty land}">
<ul class="zonderbolletjes">|
    <c:forEach items="${land.soorten}" var="soort">
        <li><a href="/soorten/zoekopland.htm?id=${land.id}&soort=${soort.id}"> ${soort.naam} </a>|</li>
    </c:forEach>
</ul>
</c:if>
<h1>${soort.naam}</h1>
<c:if test="${not empty soort}">
    <ul class="zonderbolletjes2">
        <c:forEach items="${soort.wijnen}" var="wijn">
            <li>
                <a href="/wijnen/bestel.htm?id=${wijn.id}">${wijn.jaar}
                <c:forEach begin="1" end="${wijn.beoordeling}">
                    &#9733;
                </c:forEach>
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>