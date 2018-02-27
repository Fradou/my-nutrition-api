<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

Hello

<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
	<input name="username" placeholder="Login"/>
	<input type="password" name="password"/>
	<input type="submit" value="Connexion">
</form:form>