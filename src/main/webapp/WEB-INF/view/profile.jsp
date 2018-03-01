<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


Le profile GG

<form:form action="profile" modelAttribute="currentUser" method="post">
	<form:hidden path="id"/>
	<form:hidden path="username"/>
	<form:input type="number" path="height"/>
	<form:input type="number" path="age"/>
	<input type="submit" value="Sauvegarder"/>
</form:form>