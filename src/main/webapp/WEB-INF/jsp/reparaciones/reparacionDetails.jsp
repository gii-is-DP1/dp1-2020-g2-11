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

<TalleresLaPlata:layout pageName="reparacion"> 

	<h2>Información sobre la reparacion</h2>

	<table class="table table-striped">
		<tr>
			<th>Vehiculo</th>
			<td><b><c:out value="${reparacion.vehiculo.tipoVehiculo}" /></b></td>
		</tr>
		<tr>
			<th>Propietario</th>
			<td><c:out value="${reparacion.cliente.nombre} ${reparacion.cliente.apellidos}" /></td>
		</tr>
		<tr>
			<th>Fecha</th>
			<td><c:out value="${reparacion.fechaRevision}" /></td>
		</tr>
		<tr>
			<th>Duracion</th>
			<td><c:out value="${reparacion.duracion}" /></td>
		</tr>
		<tr>
			<th>Descripcion</th>
			<td><c:out value="${reparacion.descripcion}" /></td>
		</tr>
	</table>
	
	<spring:url value="/reparacion/delete/{reparacionId}" var="reparacionUrl">
							<spring:param name="reparacionId" value="${reparacion.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(reparacionUrl)}">Eliminar reparacion</a>

</TalleresLaPlata:layout>