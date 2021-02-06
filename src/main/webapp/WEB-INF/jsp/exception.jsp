<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="error">

    <h2>Oupss error...</h2>
    
     <spring:url value="/resources/images/Error.jpg" var="logoTaller"/>
            <img src="${logoTaller}" width="300" height="200" />
	
    <p>${exception.message}</p>
  	
    <button type="submit" class="btn btn-default"><a href="javascript:history.back()"> Volver Atrás</a></button>

</TalleresLaPlata:layout>
