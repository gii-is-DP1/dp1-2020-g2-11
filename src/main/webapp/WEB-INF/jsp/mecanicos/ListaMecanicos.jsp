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

<TalleresLaPlata:layout pageName="mecanicos">

	<h2>Buscar mecanico</h2>
	<form:form modelAttribute="cliente" action="/mecanicos" method="get"
		class="form-horizontal" id="buscador-mecanicos">
		<div class="form-group">
			<div class="control-group" id="nombre">
				<label class="col-sm-2 control-label">Nombre</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="nombre" size="30"
						maxlength="80" />
					<span class="help-inline"><form:errors path="*" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Buscar</button>
			</div>
		</div>
	</form:form>

	<h2>Todos los clientes</h2>
	<table id="tablaMecanico" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Id</th>
				<th style="width: 100px;">Nombre</th>
				<th style="width: 100px">Apellidos</th>
				<th style="width: 100px">DNI</th>
				<th style="width: 100px">Telefono</th>
				<th style="width: 100px">Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="mecanico">
				<tr>
					<td><spring:url value="/mecanicos/{mecanicoId}" var="mecanicoUrl">
							<spring:param name="mecanicoId" value="${mecanico.id}" />
						</spring:url> <a href="${fn:escapeXml(mecanicoUrl)}"><c:out
								value="${mecanico.nombre} ${mecanico.apellidos}" /></a></td>
					<td><c:out value="${mecanico.dni}" /></td>
					<td><c:out value="${mecanico.telefono}" /></td>
					<td><c:out value="${mecanico.email}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
</TalleresLaPlata:layout>
