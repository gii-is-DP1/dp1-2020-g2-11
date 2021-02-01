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

	<h2>Buscar factura</h2>
	<form:form modelAttribute="factura" action="/facturas" method="get"
		class="form-horizontal" id="buscador-facturas">
		<div class="form-group">
			<div class="control-group" id="cliente">
				<label class="col-sm-2 control-label">Cliente</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="cliente" size="30"
						maxlength="80" />
					<span class="help-inline"><form:errors path="*" /></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Buscar</button>
			</div>
		</div>
	</form:form>

	<h2>Todas las facturas</h2>
	<table id="tablaFactura" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Cliente</th>
				<th style="width: 100px;">FechaEmision</th>
				<th style="width: 100px">Precio</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="factura">
				<tr>
					<td><spring:url value="/facturas/{facturaId}" var="facturaUrl">
							<spring:param name="facturaId" value="${factura.id}" />
						</spring:url> <a href="${fn:escapeXml(facturaUrl)}"><c:out
								value="${factura.descripcion}" /></a></td>
					<td><c:out value="${factura.precio}" /></td>
					<td><c:out value="${factura.tipoPago}" /></td>
					<td><c:out value="${factura.fechaEmision}" /></td>
					<td><c:out value="${factura.pagado}" /></td>
					<td><c:out value="${factura.cliente}" /></td>
					<%-- <td><c:forEach var="pet" items="${owner.pets}">
							<c:out value="${pet.name} " />
						</c:forEach></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize> --%>

</TalleresLaPlata:layout>
