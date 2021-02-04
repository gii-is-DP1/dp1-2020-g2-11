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

<TalleresLaPlata:layout pageName="revisiones"> 

<h2>Todas las Revisiones</h2>
	<table id="tablaRevision" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Descripci�n</th>
				<th style="width: 100px;">Duraci�n</th>
				<th style="width: 100px">Fecha Revisi�n</th>
				<th style="width: 100px">Cliente</th>
				<th style="width: 100px">Matricula</th>
				<th style="width: 50px"></th>
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
						</spring:url> <a href="${fn:escapeXml(clienteUrl)}"><c:out
								value="${revision.cliente.dni}" /></a></td>
								
								
					<td><spring:url value="/vehiculo/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${revision.vehiculo.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}">
						   <c:out value="${revision.vehiculo.matricula}" /></a></td>




					<td><spring:url value="/revision/delete/{revisionId}" var="revisionUrl">
							<spring:param name="revisionId" value="${revision.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(revisionUrl)}">Eliminar revision</a></td>




				</tr>


			</c:forEach>
		</tbody>
	</table>
	<br />
</TalleresLaPlata:layout>