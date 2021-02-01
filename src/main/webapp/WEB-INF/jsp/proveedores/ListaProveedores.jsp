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

<TalleresLaPlata:layout pageName="proveedores">

	<h2>Buscar proveedor</h2>
	<form:form modelAttribute="proveedor" action="/proveedores" method="get"
		class="form-horizontal" id="buscador-proveedores">
		<div class="form-group">
			<div class="control-group" id="nombre">
				<label class="col-sm-2 control-label">Nombre</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="nombre" size="30"
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

	<h2>Todos los proveedores</h2>
	<table id="tablaProveedor" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 100px;">Telefono</th>
				<th style="width: 100px">Direccion</th>
				<th style="width: 100px">Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="proveedor">
				<tr>
					<td><spring:url value="/proveedor/{proveedorID}" var="proveedorUrl">
							<spring:param name="proveedorId" value="${proveedor.id}" />
						</spring:url> <a href="${fn:escapeXml(proveedorUrl)}"><c:out
								value="${proveedor.nombre}" /></a></td>
					<td><c:out value="${proveedor.telefono}" /></td>
					<td><c:out value="${proveedor.direccion}" /></td>
					<td><c:out value="${proveedor.email}" /></td>
					<%-- <td><c:forEach var="pet" items="${owner.pets}">
							<c:out value="${pet.name} " />
						</c:forEach></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />

</TalleresLaPlata:layout>
