<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	
	<!-- SEO -->
    <meta name="description" content="My custom nutrition app.">
    <meta name="keywords" content="Nutrition">
    <meta name="author" content="Alexandre Fradet">
	<meta name="robots" content="all" />
	
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
</head>