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

<TalleresLaPlata:layout pageName="facturas">



	<h2>Todas las facturas</h2>
	<table id="tablaFactura" class="table table-striped">
		<thead>
			<tr>
				<sec:authorize access="hasAuthority('admin')">
					<th style="width: 100px">Cliente</th>
				</sec:authorize>
				<th style="width: 150px;">Decripcion</th>
				<th style="width: 100px;">Precio</th>
				<th style="width: 100px">Tipo de Pago</th>
				<th style="width: 100px">Fecha</th>
				<th style="width: 100px">Pagado</th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${facturas}" var="factura">
				<tr>
					<sec:authorize access="hasAuthority('admin')">
						<td><spring:url value="/cliente/{clienteId}" var="clienteUrl">
								<spring:param name="clienteId" value="${factura.cliente.id}" />
							</spring:url> <a href="${fn:escapeXml(clienteUrl)}"> <c:out
									value="${factura.cliente.dni}" /></a></td>
					</sec:authorize>
					<td><c:out value="${factura.descripcion}" />
					<td><c:out value="${factura.precio}" /></td>
					<td><c:out value="${factura.tipoPago}" /></td>
					<td><c:out value="${factura.fechaEmision}" /></td>
					<td><c:out value="${factura.pagado}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default"
			href='<spring:url value="/factura/new" htmlEscape="true"/>'>Añadir</a>
	</sec:authorize>
	<br />
	<%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize> --%>

</TalleresLaPlata:layout>

