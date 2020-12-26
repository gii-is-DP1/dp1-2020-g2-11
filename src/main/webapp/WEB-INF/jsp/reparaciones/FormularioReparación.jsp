<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>

<TalleresLaPlata:layout pageName="reparaciones">
	<h2>
		<c:if test="${reparacion['new']}">Nueva </c:if>Reparación
	</h2>
	<form:form modelAttribute="reparacion" class="form-horizontal"
		id="add-reparacion-form">
		<div class="form-group has-feedback">
			<TalleresLaPlata:inputField label="Duracion" name="duracion" />
			<TalleresLaPlata:inputField label="Precio" name="precio" />
			<TalleresLaPlata:inputField label="TipoReparacion" name="tipoReparacion" />
		</div>
	</form:form>
</TalleresLaPlata:layout>