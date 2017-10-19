<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
    <h1>WERELDWIJNEN</h1>
    <ul>
        <c:forEach var="land" items="${sessionlanden}">
            <li><a href="/soorten/zoekopland.htm?id=${land.id}"><img src="../../images/${land.id}.png"></a> </li>
        </c:forEach>
    </ul>
    <c:if test="${not empty basket}">
        <br>
        <a href="/mandje.htm"><img src="../../images/mandje.png"></a>
    </c:if>
</nav>