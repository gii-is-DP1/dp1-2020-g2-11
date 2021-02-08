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


						<td><spring:url value="/cita/delete/{citaId}" var="citasUrl">
								<spring:param name="citaId" value="${citas.id}" />
							</spring:url> <a class="glyphicon glyphicon-trash"
							href="${fn:escapeXml(citasUrl)}"></a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<sec:authorize access="hasAuthority('cliente')">
		<spring:url value="/cita/new" var="clienteUrl">
		</spring:url>
		<a class="btn btn-default" href="${fn:escapeXml(clienteUrl)}">Añadir</a>
	</sec:authorize>

	<br />

</TalleresLaPlata:layout>
