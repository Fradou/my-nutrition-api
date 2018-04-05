<%@include file="include/_init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Hello pour se loger :</h2>

<h3>Login with:</h3>
<p>
    <c:forEach var="url" items="${urls}">
    	<a href="${url.value}">${url.key}</a>
    </c:forEach>
</p>