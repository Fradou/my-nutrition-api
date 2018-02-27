<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

Salut

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="Logout"/>
</form:form>