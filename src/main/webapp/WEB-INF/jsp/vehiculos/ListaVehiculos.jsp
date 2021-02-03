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

<TalleresLaPlata:layout pageName="vehiculo">

	
	<h2>Vehiculos</h2>
	<table id="tablaVehiculo" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Matricula</th>
				<th style="width: 100px;">Tipo</th>
				<th style="width: 150px;">Fecha de fabricación</th>
				<th style="width: 100px;">Kilometraje</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehiculos}" var="vehiculos">
				<tr>
					<td><spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculos.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculosUrl)}"><c:out
								value="${vehiculos.matricula}" /></a></td>
					<td><c:out value="${vehiculos.tipoVehiculo}" /></td>
					<td><c:out value="${vehiculos.fechaFabricacion}" /></td>
					<td><c:out value="${vehiculos.kilometraje}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	
</TalleresLaPlata:layout>