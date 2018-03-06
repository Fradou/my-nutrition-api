<%@include file="../include/_init.jsp"%>

<body>

	<div>
		<h1>Enregistrment new user</h1>

		<form:form method="POST" action="register" modelAttribute="newUser">

			<label> Username <form:input path="username" />
			</label>
			<form:errors path="username" cssClass="error" />

			<label> Password <form:password path="password" />
			</label>
			<form:errors path="password" cssClass="error" />

			<label> email <form:input path="email" />
			</label>
			<form:errors path="email" cssClass="error" />

			<input type="submit" value="Gogogo" />
		</form:form>


	</div>


</body>