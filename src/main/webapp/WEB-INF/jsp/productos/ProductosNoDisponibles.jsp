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

<TalleresLaPlata:layout pageName="productosNoDisponibles">



	<h2>Todos los productos no disponibles</h2>
	<table id="tablaProducto" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Nombre</th>
				<th style="width: 150px;">Marca</th>
				<th style="width: 150px">Stock</th>
				<th style="width: 150px">Referencia</th>
				<th style="width: 150px">StockSeguridad</th>
				<th style="width: 50px">A�adir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${producto}" var="producto">
				<tr>

					<td><c:out value="${producto.nombre}" /></td>
					<td><c:out value="${producto.marca}" /></td>
					<td><c:out value="${producto.stock}" /></td>
					<td><c:out value="${producto.referencia}" /></td>
					<td><c:out value="${producto.stockSeguridad}" /></td>

					<td><spring:url value="/productos/devuelve/{productoId}"
							var="productoUrl">
							<spring:param name="productoId" value="${producto.id}" />
						</spring:url> <a class="glyphicon glyphicon-plus"
						href="${fn:escapeXml(productoUrl)}"></a></td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
</TalleresLaPlata:layout>