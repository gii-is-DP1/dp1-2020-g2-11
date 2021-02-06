<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<TalleresLaPlata:layout pageName="citas">

	<sec:authorize
		access="hasAuthority('admin') or hasAuthority('mecanico')">
		<h2>Buscar cita</h2>
		<form:form modelAttribute="cita" action="/citas" method="post"
			class="form-horizontal" id="buscador-citas">
			<div class="form-group">
				<div class="control-group" id="fechaCita">
					<label class="col-sm-2 control-label">Fecha de la Cita</label>
					<div class="col-sm-10">
						<form:input class="form-control" path="fechaCita" size="30"
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
	</sec:authorize>
	<h2>Todas las citas</h2>
	<table id="tablaProveedor" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Vehículo</th>
				<th style="width: 100px;">Fecha</th>
				<th style="width: 100px;">Hora</th>
				<sec:authorize
					access="hasAuthority('admin') or hasAuthority('mecanico')">
					<th style="width: 100px;">Dni del cliente</th>
					<th style="width: 50px;">Borrar</th>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${citas}" var="citas">
				<tr>
					<td><spring:url value="/vehiculo/{vehiculoId}"
							var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${citas.vehiculo.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}"> <c:out
								value="${citas.vehiculo.matricula} " /></a></td>
					<td><c:out value="${citas.fechaCita}" /></td>
					<td><c:out value="${citas.horaCita}" /></td>
					<sec:authorize
						access="hasAuthority('admin') or hasAuthority('mecanico')">
						<td><c:out value="${citas.cliente.dni}" /></td>
					

					<td><spring:url value="/cita/delete/{citaId}" var="citaUrl">
							<spring:param name="citaId" value="${citas.id}" />
						</spring:url> <a class="glyphicon glyphicon-trash"
						href="${fn:escapeXml(citasUrl)}"></a></td>
						</sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<sec:authorize
		access="hasAuthority('cliente')">
		<a class="btn btn-default"
			href='<spring:url value="/citas/new" htmlEscape="true"/>'>Añadir</a>
	</sec:authorize>

	<br />

</TalleresLaPlata:layout>
