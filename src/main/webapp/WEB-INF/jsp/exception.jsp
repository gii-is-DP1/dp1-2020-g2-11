<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="error">

    <spring:url value="/resources/images/LogoTaller.jpg" var="LogoImage"/>
    <img src="${logoImage}"/>

    <h2>Algo va mal...</h2>

    <p>${exception.message}</p>

</TalleresLaPlata:layout>
