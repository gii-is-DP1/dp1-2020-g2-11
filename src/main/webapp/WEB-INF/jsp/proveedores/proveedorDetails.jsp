<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<TalleresLaPlata:layout pageName="proveedor">

	<h2>Información sobre el proveedor</h2>

	<table class="table table-striped">
			<tr>
				<th>Nombre del proveedor</th>
				<td><b><c:out
							value="${proveedor.nombre}" /></b></td>
			</tr>

		<tr>
			<th>Telefono</th>
			<td><c:out value="${proveedor.telefono}" /></td>
		</tr>
		<tr>
			<th>Direccion</th>
			<td><c:out value="${proveedor.direccion}" /></td>
		</tr>
		<tr>
			<th>Correo electronico</th>
			<td><c:out value="${proveedor.email}" /></td>
		</tr>

	</table>
	<sec:authorize access="hasAuthority('admin')">
	 <td><spring:url value="/proveedor/{proveedorId}/oculta" var="proveedorUrl">
							<spring:param name="proveedorId" value="${proveedor.id}" />
						</spring:url> <a class="glyphicon glyphicon-trash" href="${fn:escapeXml(proveedorUrl)}"></a></td>
	</sec:authorize>
</TalleresLaPlata:layout>