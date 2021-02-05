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

<TalleresLaPlata:layout pageName="cliente">

	
	<h2>Clientes</h2>
	<table id="tablaCliente" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 100px;">Apellidos</th>
				<th style="width: 100px;">DNI</th>
				<th style="width: 100px;">Teléfono</th>
				<th style="width: 100px;">Email</th>
				<th style="width: 50px;"></th>
				<th style="width: 50px;"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cliente}" var="cliente">
				<tr>
					<td><spring:url value="/cliente/{clienteId}" var="clienteUrl">
							<spring:param name="clienteId" value="${cliente.id}" />
						</spring:url> <a href="${fn:escapeXml(clienteUrl)}"><c:out
								value="${cliente.nombre}" /></a></td>
					<td><c:out value="${cliente.apellidos}" /></td>
					<td><c:out value="${cliente.dni}" /></td>
					<td><c:out value="${cliente.telefono}" /></td>
					<td><c:out value="${cliente.email}" /></td>
					<td><spring:url value="/cliente/{clienteId}/edit" var="clienteUrl">
							<spring:param name="clienteId" value="${cliente.id}" />
						</spring:url> <a href="${fn:escapeXml(clienteUrl)}">EditarCliente</a></td>
					<td><spring:url value="/cliente/delete/{clienteId}" var="clienteUrl">
					<spring:param name="clienteId" value="${cliente.id}" />
					</spring:url> <a class="btn btn-default" href="${fn:escapeXml(clienteUrl)}">Eliminar cliente</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
</TalleresLaPlata:layout>
