<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="estancias">

    <h2>Find Estancias</h2>

    
    <form:form modelAttribute="estancia" action="/estancia" method="get" class="form-horizontal"
               id="search-estancia-form">
        <div class="form-group">
            <div class="control-group" id="fechaEntrada">
                <label class="col-sm-2 control-label">Fecha Entrada </label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="fechaEntrada" size="30" maxlength="80"/>
                    <span class="help-inline"><form:errors path="*"/></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Find Estancias</button>
            </div>
        </div>

    </form:form>

    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/estancia/new" htmlEscape="true"/>'>A�adir Estancia</a>
	</sec:authorize>
	
</petclinic:layout>