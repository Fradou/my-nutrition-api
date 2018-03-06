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

			<label> firstName <form:input path="firstName" />
			</label>
			<form:errors path="firstName" cssClass="error" />

			<label> lastName <form:input path="lastName" />
			</label>
			<form:errors path="lastName" cssClass="error" />

			<label> email <form:input path="email" />
			</label>
			<form:errors path="email" cssClass="error" />

			<label> Age <form:input path="age" /></label>
			<form:errors path="age" cssClass="error" />
			<label> weight <form:input path="weight" />

			</label>
			<form:errors path="weight" cssClass="error" />
			<label> height <form:input path="height" />
			</label>
			<form:errors path="height" cssClass="error" />

			<label> bmr <form:input path="bmr" />
			</label>
			<form:errors path="bmr" cssClass="error" />

			<label> tdee <form:input path="tdee" />
			</label>
			<form:errors path="tdee" cssClass="error" />

			<input type="submit" value="Gogogo" />
		</form:form>


	</div>


</body>