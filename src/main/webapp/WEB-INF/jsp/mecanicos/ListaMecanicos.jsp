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

<TalleresLaPlata:layout pageName="mecanicos">

	<h2>Todos los mecanicos</h2>
	<table id="tablaMecanico" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Id</th>
				<th style="width: 100px;">Nombre</th>
				<th style="width: 100px">Apellidos</th>
				<th style="width: 100px">DNI</th>
				<th style="width: 100px">Telefono</th>
				<th style="width: 100px">Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="mecanico">
				<tr>
					<td><spring:url value="/mecanicos/{mecanicoId}" var="mecanicoUrl">
							<spring:param name="mecanicoId" value="${mecanico.id}" />
						</spring:url> <a href="${fn:escapeXml(mecanicoUrl)}"><c:out
								value="${mecanico.nombre} ${mecanico.apellidos}" /></a></td>
					<td><c:out value="${mecanico.dni}" /></td>
					<td><c:out value="${mecanico.telefono}" /></td>
					<td><c:out value="${mecanico.email}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<spring:url value="/mecanicos/new" var="mecanicoUrl">
		</spring:url>
		<a class="btn btn-default" href="${fn:escapeXml(mecanicoUrl)}">Añadir</a>
	<br />
</TalleresLaPlata:layout>
