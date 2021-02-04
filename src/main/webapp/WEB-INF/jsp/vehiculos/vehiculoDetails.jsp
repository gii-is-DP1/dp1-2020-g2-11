<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<TalleresLaPlata:layout pageName="vehiculo">

	<h2>Información sobre el vehiculo</h2>

	<table class="table table-striped">
		<sec:authorize access="hasAuthority('admin') or hasAuthority('mecanico')">
			<tr>
				<th>Propietario</th>
				<td><b><c:out
							value="${vehiculo.cliente.nombre} ${vehiculo.cliente.apellidos}" /></b></td>
			</tr>
		</sec:authorize>
		<tr>
			<th>Matrícula</th>
			<td><c:out value="${vehiculo.matricula}" /></td>
		</tr>
		<tr>
			<th>Tipo de vehículo</th>
			<td><c:out value="${vehiculo.tipoVehiculo}" /></td>
		</tr>
		<tr>
			<th>Fecha de fabricación</th>
			<td><c:out value="${vehiculo.fechaFabricacion}" /></td>
		</tr>

		<tr>
			<th>Kilometraje</th>
			<td><c:out value="${vehiculo.kilometraje}" /></td>
		</tr>
	</table>
	<sec:authorize access="hasAuthority('cliente') or hasAuthority('admin')">
		<spring:url value="/vehiculo/delete/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculo.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(vehiculoUrl)}">Eliminar</a>
	</sec:authorize>
	
	
</TalleresLaPlata:layout>