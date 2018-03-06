<%@include file="../include/_init.jsp"%>


Le profile GG

<form:form action="profile" modelAttribute="currentUser" method="post">
	<form:hidden path="id" />
	<form:hidden path="username" />
	<label> firstName <form:input path="firstName" />
	</label>
	<form:errors path="firstName" cssClass="error" />

	<label> lastName <form:input path="lastName" />
	</label>
	<form:errors path="lastName" cssClass="error" />

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
	
	<input type="submit" value="Sauvegarder" />
</form:form>

<%@include file="../include/_logout.jsp"%>