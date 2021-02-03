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

<TalleresLaPlata:layout pageName="citas">

	<h2>Buscar cita</h2>
	<form:form modelAttribute="cita" action="/citas" method="post"
		class="form-horizontal" id="buscador-citas">
		<div class="form-group">
			<div class="control-group" id="fechaCita">
				<label class="col-sm-2 control-label">FechaCita</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="fechaCita" size="30"
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

	<h2>Todas las citas</h2>
	<table id="tablaProveedor" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">FechaCita</th>
				<th style="width: 100px;">HoraCita</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${citas}" var="cita">
				<tr>
					<td><spring:url value="/cita/{citaID}" var="citaUrl">
							<spring:param name="citaId" value="${cita.id}" />
						</spring:url> <a href="${fn:escapeXml(citaUrl)}"><c:out
								value="${cita.fechaCita}" /></a></td>
					<td><c:out value="${cita.horaCita}" /></td>
					
					<%-- <td><c:forEach var="pet" items="${owner.pets}">
							<c:out value="${pet.name} " />
						</c:forEach></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />

</TalleresLaPlata:layout>
