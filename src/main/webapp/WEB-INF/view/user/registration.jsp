<%@include file="../include/_init.jsp"%>

<body>

	<div>
	<a href="register?locale=en">English </a>
		<h1><spring:message code="user.title.newUser.creation"/></h1>

		<form:form method="POST" action="register" modelAttribute="newUser">

			<label><spring:message code="user.label.username"/><form:input path="username" />
			</label>
			<form:errors path="username" cssClass="error" />

			<label> <spring:message code="user.label.password"/> <form:password path="password" />
			</label>
			<form:errors path="password" cssClass="error" />

			<label> <spring:message code="user.label.email"/> <form:input path="email" />
			</label>
			<form:errors path="email" cssClass="error" />

			<input type="submit" value="Gogogo" />
		</form:form>

	</div>


</body>