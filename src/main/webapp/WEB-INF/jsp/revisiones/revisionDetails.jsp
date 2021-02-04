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

<TalleresLaPlata:layout pageName="revision"> 

	<h2>Información sobre la revision</h2>

	<table class="table table-striped">
		<tr>
			<th>Vehiculo</th>
			<td><b><c:out value="${revision.vehiculo.tipoVehiculo}" /></b></td>
		</tr>
		<tr>
			<th>Propietario</th>
			<td><c:out value="${revision.cliente.nombre} ${revision.cliente.apellidos}" /></td>
		</tr>
		<tr>
			<th>Fecha</th>
			<td><c:out value="${revision.fechaRevision}" /></td>
		</tr>
		<tr>
			<th>Duracion</th>
			<td><c:out value="${revision.duracion}" /></td>
		</tr>
		<tr>
			<th>Descripcion</th>
			<td><c:out value="${revision.descripcion}" /></td>
		</tr>
	</table>
	
	<spring:url value="/revision/delete/{revisionId}" var="revisionUrl">
							<spring:param name="revisionId" value="${revision.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(revisionUrl)}">Eliminar revision</a>

</TalleresLaPlata:layout>