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

<TalleresLaPlata:layout pageName="revisiones">
<h2>Buscar revisión</h2>
	<form:form modelAttribute="revision" action="/revisiones" method="get"
		class="form-horizontal" id="buscador-revisiones">
		<div class="form-group">
			<div class="control-group" id="nombre">
				<label class="col-sm-2 control-label">Fecha</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="fecha" size="30"
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


<h2>Todas las Revisiones</h2>
	<table id="tablaCliente" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Fecha</th>
				<th style="width: 100px;">Duración</th>
				<th style="width: 100px">Descripción</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="revision">
				<tr>
					<td><spring:url value="/revision/{revisionId}" var="revisionUrl">
							<spring:param name="revisionId" value="${revision.id}" />
						</spring:url> <a href="${fn:escapeXml(revisionUrl)}"><c:out
								value="${revision.fechaRevision}" /></a></td>
					<td><c:out value="${revision.duracion}" /></td>
					<td><c:out value="${revision.descripcion}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />


</TalleresLaPlata:layout>