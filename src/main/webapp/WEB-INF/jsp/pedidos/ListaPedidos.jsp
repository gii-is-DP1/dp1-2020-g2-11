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

	<h2>Todos los pedidos</h2>
	<table id="tablaPedido" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Fecha Emisión</th>
				<th style="width: 150px;">Fecha Entrada</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pedidos}" var="pedido">
				<tr>
					<td><spring:url value="/pedidos/{pedidoId}" var="pedidoUrl">
							<spring:param name="pedidoId" value="${pedido.id}" />
						</spring:url> <a href="${fn:escapeXml(pedidoUrl)}"><c:out
								value="${pedido.fechaEmision} " /></a></td>
					<td><c:out value="${pedido.fechaEntrada}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize> --%>

</TalleresLaPlata:layout>
