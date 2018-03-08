<%@include file="../include/_init.jsp"%>


<h1><spring:message code="user.title.profileEdition"/></h1>

<form:form action="profile" modelAttribute="currentUser" method="post">
	<form:hidden path="id" />
	<form:hidden path="username" />
	<label> <spring:message code="user.label.firstName"/> <form:input path="firstName" />
	</label>
	<form:errors path="firstName" cssClass="error" />

	<label> <spring:message code="user.label.lastName"/> <form:input path="lastName" />
	</label>
	<form:errors path="lastName" cssClass="error" />

	<label> <spring:message code="user.label.age"/> <form:input path="age" /></label>
	<form:errors path="age" cssClass="error" />

	<label> <spring:message code="user.label.weight"/> <form:input path="weight" />
	</label>
	<form:errors path="weight" cssClass="error" />

	<label> <spring:message code="user.label.height"/> <form:input path="height" />
	</label>
	<form:errors path="height" cssClass="error" />

	<label> <spring:message code="user.label.bmr.s"/> <form:input path="bmr" />
	</label>
	<form:errors path="bmr" cssClass="error" />

	<label> <spring:message code="user.label.tdee.s"/> <form:input path="tdee" />
	</label>
	<form:errors path="tdee" cssClass="error" />
	
	<input type="submit" value="<spring:message code="general.form.submit.save"/>" />
</form:form>

<%@include file="../include/_logout.jsp"%>