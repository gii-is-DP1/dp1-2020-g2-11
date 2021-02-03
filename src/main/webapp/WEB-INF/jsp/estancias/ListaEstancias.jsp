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

<TalleresLaPlata:layout pageName="estancias">


	<h2>Estancias</h2>
	<table id="tablaEstancia" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Vehículo</th>
				<th style="width: 100px;">Fecha entrada</th>
				<th style="width: 100px;">Hora entrada</th>
				<th style="width: 100px;">Fecha salida</th>
				<th style="width: 100px;">Hora salida</th>
				<th style="width: 100px;">Duración</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${estancias}" var="estancia">
				<tr>
					<td><spring:url value="/vehiculo/{vehiculoId}"
							var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${estancia.vehiculo.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}"> <c:out
								value="${estancia.vehiculo.matricula}" /></a></td>
					<td><c:out value="${estancia.fechaEntrada}" /></td>
					<td><c:out value="${estancia.horaEntrada}" /></td>
					<td><c:out value="${estancia.fechaSalida}" /></td>
					<td><c:out value="${estancia.horaSalida}" /></td>
					<td><c:out value="${estancia.duracion}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</TalleresLaPlata:layout>