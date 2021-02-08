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
			
	<sec:authorize access="hasAuthority('admin')">
	<h2>Revisiones</h2>
	</sec:authorize>
	<sec:authorize access="hasAuthority('mecanico')">
	<h2>Mis revisiones asignadas</h2>
	</sec:authorize>
	
		
	<table id="tablaRevision" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Descripción</th>
				<th style="width: 100px;">Duración</th>
				<th style="width: 100px">Fecha Revisión</th>
				<th style="width: 100px">Cliente</th>
				<th style="width: 100px">Matricula</th>
				<th style="width: 50px">Borrar</th>	
			</tr>
		</thead>
		<tbody>
				<c:forEach items="${revisiones}" var="revision">
					<tr>
						<td><spring:url value="/revision/{revisionId}" var="revisionUrl">
						<spring:param name="revisionId" value="${revision.id}" />
						</spring:url> <a href="${fn:escapeXml(revisionUrl)}">
						<c:out value="${revision.descripcion}" /></a></td>
						<td><c:out value="${revision.duracion}" /></td>
						<td><c:out value="${revision.fechaRevision}" /></td>
						<td><spring:url value="/cliente/{clienteId}" var="clienteUrl">
						<spring:param name="clienteId" value="${revision.cliente.id}" />
						</spring:url> <a href="${fn:escapeXml(clienteUrl)}"><c:out value="${revision.cliente.dni}" /></a></td>					
						<td><spring:url value="/vehiculo/{vehiculoId}" var="vehiculoUrl">
						<spring:param name="vehiculoId" value="${revision.vehiculo.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}">
						<c:out value="${revision.vehiculo.matricula}" /></a></td>
						<td><spring:url value="/revision/asignar/{revisionId}" var="revisionUrl">
						<spring:param name="revisionId" value="${revision.id}" />
						</spring:url> <a class="glyphicon glyphicon-trash" href="${fn:escapeXml(revisionUrl)}"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasAuthority('mecanico')">
	<a class="btn btn-default" href='<spring:url value="/revision/new" htmlEscape="true"/>'>Añadir</a>
	</sec:authorize>
	<br />
</TalleresLaPlata:layout>