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

<TalleresLaPlata:layout pageName="reparaciones">

	

	<h2>Todas las reparaciones</h2>
	<table id="tablaReparacion" class="table table-striped">
		<thead>
			<tr>
			<th style="width: 150px;">Matricula</th>
			    <th style="width: 150px;">Tipo Reparacion</th>
				<th style="width: 150px;">Precio</th>
				<th style="width: 150px;">Duracion</th>
				<th style="width: 50px">Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reparaciones}" var="reparacion">
				<tr>
					<td><spring:url value="/vehiculo/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${reparacion.vehiculo.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}">
						<c:out value="${reparacion.vehiculo.matricula} " /></a></td>
					<td><c:out value="${reparacion.tipoReparacion} " /></td>
					<td><c:out value="${reparacion.precio}" /></td>
					<td><c:out value="${reparacion.duracion}" /></td>
					<td><spring:url value="/reparacion/{reparacionId}/edit" var="reparacionUrl">
							<spring:param name="reparacionId" value="${reparacion.id}" />
						</spring:url> <a class="glyphicon glyphicon-pencil" href="${fn:escapeXml(reparacionUrl)}"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<a class="btn btn-default" href='<spring:url value="/reparacion/new" htmlEscape="true"/>'>Añadir</a>
	<br/>
</TalleresLaPlata:layout>
