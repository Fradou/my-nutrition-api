<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


Salut
User : <security:authentication property="principal.username"/>
Roles : <security:authentication property="principal.authorities"/>

<security:authorize access="hasRole('MANAGER')">
	<a href="${pageContext.request.contextPath}/securePage">Secure page</a>
</security:authorize>

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="Logout"/>
</form:form>
