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

<TalleresLaPlata:layout pageName="vehiculos">

	<sec:authorize
		access="hasAuthority('admin') or hasAuthority('mecanico')">
           	<label for="tipoVehiculo">Buscar tipo de vehiculo:</label>
  			<select name="tipoVehiculo" id="tipoVehiculo"> 
  			<c:forEach items="${tipoVehiculo}" var="tipoVehiculo">
 			<option value="${tipoVehiculo}" ><c:out value="${tipoVehiculo}" /></option> 
    </c:forEach>
  </select> 	
  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Buscar</button>
		</div>
	</div>
	</sec:authorize>
	<h2>Vehiculos</h2>
	<table id="tablaVehiculo" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Matricula</th>
				<th style="width: 100px;">Tipo</th>
				<th style="width: 100px;">Fecha de fabricación</th>
				<th style="width: 100px;">Kilometraje</th>
				<sec:authorize
					access="hasAuthority('admin') or hasAuthority('mecanico')">
					<th style="width: 100px;">Propietario</th>
				</sec:authorize>
				<th style="width: 50px;">Editar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehiculos}" var="vehiculos">
				<tr>
					<td><spring:url value="/vehiculo/{vehiculoId}"
							var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculos.id}" />
						</spring:url> <a href="${fn:escapeXml(vehiculoUrl)}"><c:out
								value="${vehiculos.matricula}" /></a></td>

					<td><c:out value="${vehiculos.tipoVehiculo}" /></td>
					<td><c:out value="${vehiculos.fechaFabricacion}" /></td>
					<td><c:out value="${vehiculos.kilometraje}" /></td>


					<sec:authorize access="hasAuthority('admin')">
						<td><spring:url value="/cliente/{clienteId}" var="clienteUrl">
								<spring:param name="clienteId" value="${vehiculos.cliente.id}" />
							</spring:url> <a href="${fn:escapeXml(clienteUrl)}"><c:out
									value="${vehiculos.cliente.nombre} ${vehiculos.cliente.apellidos}" /></a></td>
					</sec:authorize>

					<sec:authorize access="hasAuthority('mecanico')">
						<td><c:out
								value="${vehiculos.cliente.nombre} ${vehiculos.cliente.apellidos}" /></td>
					</sec:authorize>
					<sec:authorize access="hasAuthority('cliente')">
					<td><spring:url value="/vehiculo/{vehiculoId}/edit"
							var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculos.id}" />
						</spring:url> <a class="glyphicon glyphicon-pencil"
						href="${fn:escapeXml(vehiculoUrl)}"></a></td>
						</sec:authorize>
						<sec:authorize access="hasAuthority('admin')">
					<td><spring:url value="/vehiculos/{vehiculoId}/edit"
							var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculos.id}" />
						</spring:url> <a class="glyphicon glyphicon-pencil"
						href="${fn:escapeXml(vehiculoUrl)}"></a></td>
						</sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasAuthority('cliente')">
		<a class="btn btn-default"
			href='<spring:url value="/vehiculo/new" htmlEscape="true"/>'>Añadir</a>
	</sec:authorize>


	<br />
</TalleresLaPlata:layout>