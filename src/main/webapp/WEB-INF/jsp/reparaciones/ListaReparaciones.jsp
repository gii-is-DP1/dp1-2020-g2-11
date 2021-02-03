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
			    <th style="width: 150px;">Precio</th>
				<th style="width: 150px;">Duracion</th>
				<th style="width: 150px;">Tipo Reparacion</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reparacion}" var="reparacion">
				<tr>
					<td><spring:url value="/reparaciones/{reparacionesId}" var="reparacionUrl">
							<spring:param name="reparacionTipo" value="${reparacion.tipoReparacion}" />
						</spring:url> <a href="${fn:escapeXml(reparacionUrl)}">
						<c:out value="${reparacion.tipoReparacion} " /></a></td>
					<td><c:out value="${reparacion.precio}" /></td>
					<td><c:out value="${reparacion.duracion}" /></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize> --%>

</TalleresLaPlata:layout>
