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
				<th style="width: 50px">Borrar</th>
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
					
					
					<td><spring:url value="/estancias/delete/{estanciaId}" var="estanciaUrl">
							<spring:param name="estanciaId" value="${estancia.id}" />
						</spring:url> <a class="glyphicon glyphicon-trash" href="${fn:escapeXml(estanciaUrl)}"></a></td>
					
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasAuthority('mecanico')">
	<a class="btn btn-default" href='<spring:url value="/estancia/new" htmlEscape="true"/>'>Añadir</a>
	</sec:authorize>
	<br />
</TalleresLaPlata:layout>