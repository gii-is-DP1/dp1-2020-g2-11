<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<TalleresLaPlata:layout pageName="cliente">

	<h2>Find Clientes</h2>


	<form:form modelAttribute="cliente" action="/cliente" method="get"
		class="form-horizontal" id="search-cliente-form">
		<div class="form-group">
			<div class="control-group" id="nombre">
				<label class="col-sm-2 control-label">Nombre </label>
				<div class="col-sm-10">
					<form:input class="form-control" path="nombre" size="30"
						maxlength="80" />
					<span class="help-inline"><form:errors path="*" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Find Cliente</button>
			</div>
		</div>
	</form:form>

	<table id="tablaCliente" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 100px;">Apellidos</th>
				<th style="width: 100px;">DNI</th>
				<th style="width: 100px;">Teléfono</th>
				<th style="width: 100px;">Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="cliente">
				<tr>
		<%--  			<td><spring:url value="/clientes/{clienteId}" var="clienteUrl">
							<spring:param name="clienteId" value="${cliente.id}" />
						</spring:url> <a href="${fn:escapeXml(clienteUrl)}"></a></td>--%>
					<td><c:out value="${cliente.nombre} ${cliente.apellidos}" /></td>
					<td><c:out value="${cliente.apellidos}"/></td>
					<td><c:out value="${cliente.dni}" /></td>
					<td><c:out value="${cliente.telefono}" /></td>
					<td><c:out value="${cliente.email}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<%--    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/clientes/new" htmlEscape="true"/>'>Add Cliente</a>
	</sec:authorize>--%>

</TalleresLaPlata:layout>