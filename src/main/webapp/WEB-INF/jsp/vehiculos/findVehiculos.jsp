<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="vehiculos">

    <h2>Find VehÝculo</h2>

    
    <form:form modelAttribute="vehiculo" action="/vehiculo" method="get" class="form-horizontal"
               id="buscador-vehiculo">
        <div class="form-group">
            <div class="control-group" id="matricula">
                <label class="col-sm-2 control-label">MatrÝcula </label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="matricula" size="30" maxlength="80"/>
                    <span class="help-inline"><form:errors path="*"/></span>
                    <span id="matricula.errors">No existe la matrÝcula </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Fhola</button>
            </div>
        </div>

    </form:form>

    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/vehiculo/new" htmlEscape="true"/>'>Add VehÝculo</a>
	</sec:authorize>
	
</petclinic:layout>