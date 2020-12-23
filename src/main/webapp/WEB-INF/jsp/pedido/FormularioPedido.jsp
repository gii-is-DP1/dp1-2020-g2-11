<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>

<TalleresLaPlata:layout pageName="pedidos">
	<h2>
		<c:if test="${pedido['new']}">Nuevo </c:if>Pedido
	</h2>
	<form:form modelAttribute="pedido" class="form-horizontal"
		id="add-pedido-form">
		<div class="form-group has-feedback">
			<TalleresLaPlata:inputField label="FechaEntrada" name="fechaEntrada" />
			<TalleresLaPlata:inputField label="FechaEmision" name="fechaEmision" />
		</div>
	</form:form>
</TalleresLaPlata:layout>