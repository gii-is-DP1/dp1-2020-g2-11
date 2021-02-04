<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vehiculo">

	<h2>Informaci�n sobre el vehiculo</h2>

	<table class="table table-striped">
		<tr>
			<th>Propietario</th>
			<td><b><c:out value="${vehiculo.cliente.nombre} ${vehiculo.cliente.apellidos}" /></b></td>
		</tr>
		<tr>
			<th>Matr�cula</th>
			<td><c:out value="${vehiculo.matricula}" /></td>
		</tr>
		<tr>
			<th>Tipo de veh�culo</th>
			<td><c:out value="${vehiculo.tipoVehiculo}" /></td>
		</tr>
		<tr>
			<th>Fecha de fabricaci�n</th>
			<td><c:out value="${vehiculo.fechaFabricacion}" /></td>
		</tr>
		
		<tr>
			<th>Kilometraje</th>
			<td><c:out value="${vehiculo.kilometraje}" /></td>
		</tr>

	</table>
</petclinic:layout>