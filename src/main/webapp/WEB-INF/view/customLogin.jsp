<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Hello

<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
	
	<c:if test="${param.error != null}">
		<i>Sorry, wrong login</i>
	</c:if>
	
	<p>
		<input name="username" placeholder="Login"/>
		<input type="password" name="password"/>
		<input type="submit" value="Connexion">
	</p>
</form:form>