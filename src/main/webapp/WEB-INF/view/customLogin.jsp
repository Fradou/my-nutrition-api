<%@include file="include/_init.jsp"%>

Hello

<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
	
	<c:if test="${param.error != null}">
		<i>Sorry, wrong login</i>
	</c:if>
	
	<c:if test="${param.logout != null}">
		<i>C'est ok vous �tes deco</i>
	</c:if>
	
	<p>
		<input name="username" placeholder="Login"/>
		<input type="password" name="password"/>
		<input type="submit" value="Connexion">
	</p>
	
	Nouveau ? <a href="<c:url value="register"/>">S'inscrire</a>
</form:form>