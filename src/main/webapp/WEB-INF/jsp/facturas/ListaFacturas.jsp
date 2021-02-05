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

<TalleresLaPlata:layout pageName="facturas">

	

	<h2>Todas las facturas</h2>
	<table id="tablaFactura" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Decripcion</th>
				<th style="width: 100px;">Precio</th>
				<th style="width: 100px">Tipo de Pago</th>
				<th style="width: 100px">Fecha</th>
				<th style="width: 100px">Pagado</th>
				<th style="width: 100px">Cliente</th>
				<th style="width: 100px"></th>
				<th style="width: 100px"></th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${facturas}" var="factura">
				<tr>
					<td><spring:url value="/facturas/{facturaId}" var="facturaUrl">
							<spring:param name="facturaId" value="${factura.id}" />
						</spring:url> <a href="${fn:escapeXml(facturaUrl)}"><c:out
								value="${factura.descripcion}" /></a></td>
					<td><c:out value="${factura.precio}" /></td>
					<td><c:out value="${factura.tipoPago}" /></td>
					<td><c:out value="${factura.fechaEmision}" /></td>
					<td><c:out value="${factura.pagado}" /></td>
					<td><c:out value="${factura.cliente.nombre}" /></td>
					
					
					<td><spring:url value="/cliente/{clienteId}/factura/{facturaId}/edit"
							var="facturaUrl">
							<spring:param name="clienteId" value="${factura.cliente.id}" />
							<spring:param name="facturaId" value="${factura.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(facturaUrl)}">Editar factura</a></td>
								
								
								<td><spring:url value="/factura/{facturaId}/delete" var="facturaUrl">
							<spring:param name="facturaId" value="${factura.id}" />
						</spring:url> <a class="btn btn-default" href="${fn:escapeXml(facturaUrl)}">Eliminar factura</a></td>
					<%-- <td><c:forEach var="pet" items="${owner.pets}">
							<c:out value="${pet.name} " />
						</c:forEach></td> --%>
						
						
				</tr>
			</c:forEach>
		</tbody>
	</table>
<a class="btn btn-default" href='<spring:url value="/factura/new" htmlEscape="true"/>'>Añadir</a>
	<br />
	<%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize> --%>

</TalleresLaPlata:layout>

