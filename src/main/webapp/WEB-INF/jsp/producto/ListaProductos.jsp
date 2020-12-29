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

<TalleresLaPlata:layout pageName="productos">

	<h2>Buscar producto</h2>
	<form:form modelAttribute="producto" action="/productos" method="get"
		class="form-horizontal" id="buscador-productos">
		<div class="form-group">
			<div class="control-group" id="marca">
				<label class="col-sm-2 control-label">Marca</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="marca" size="30"
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

	<h2>Todos los productos</h2>
	<table id="tablaProducto" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 150px;">Marca</th>
				<th style="width: 150px">Stock</th>
				<th style="width: 150px">Referencia</th>
				<th style="width: 150px">StockSeguridad</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="producto">
				<tr>
					<td><spring:url value="/producto/{productoReferencia}" var="productoUrl">
							<spring:param name="productoReferencia" value="${producto.referencia}" />
						</spring:url> <a href="${fn:escapeXml(productoUrl)}"><c:out
								value="${producto.nombre}" /></a></td>
					<td><c:out value="${producto.marca}" /></td>
					<td><c:out value="${producto.stock}" /></td>
					<td><c:out value="${producto.referencia}" /></td>
					<td><c:out value="${producto.stockSeguridad}" /></td>
					<%-- <td><c:forEach var="pet" items="${owner.pets}">
							<c:out value="${pet.name} " />
						</c:forEach></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />

</TalleresLaPlata:layout>
