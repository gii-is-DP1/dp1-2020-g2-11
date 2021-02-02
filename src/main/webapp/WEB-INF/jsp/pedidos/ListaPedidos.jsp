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

<TalleresLaPlata:layout pageName="pedidos">

	<h2>Buscar pedido</h2>
	<form:form modelAttribute="pedido" action="/pedidos" method="get"
		class="form-horizontal" id="buscador-pedidos">
		<div class="form-group">
			<div class="control-group" id="id">
				<label class="col-sm-2 control-label">FechaEmision</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="fechaEmision" size="30"
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

	<h2>Todos los pedidos</h2>
	<table id="tablaPedido" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">FechaEmision</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="pedido">
				<tr>
					<td><spring:url value="/pedidos/{pedidoId}" var="pedidoUrl">
							<spring:param name="pedidoId" value="${pedido.id}" />
						</spring:url> <a href="${fn:escapeXml(pedidoUrl)}"><c:out
								value="${pedido.fechaEmision} " /></a></td>
					<td><c:out value="${pedido.fechaEntrada}" /></td>
					
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
