<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="cliente">

	<h2>Información sobre cliente</h2>

	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${cliente.nombre} ${cliente.apellidos}" /></b></td>
		</tr>
		<tr>
			<th>DNI</th>
			<td><c:out value="${cliente.dni}" /></td>
		</tr>
		<tr>
			<th>Teléfono</th>
			<td><c:out value="${cliente.telefono}" /></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${cliente.email}" /></td>
		</tr>

	</table>
	<spring:url value="/cliente/{clienteId}/edit" var="clienteUrl">
							<spring:param name="clienteId" value="${cliente.id}" />
						</spring:url> <a class="glyphicon glyphicon-pencil" href="${fn:escapeXml(clienteUrl)}"></a>
	
</petclinic:layout>