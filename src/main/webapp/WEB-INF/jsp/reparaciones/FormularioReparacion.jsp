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
			 <TalleresLaPlata:inputField label="Cliente" name="cliente.dni"/>
            <TalleresLaPlata:inputField label="Matricula" name="vehiculo.matricula"/>
		</div>
		<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${reparacion['new']}">
                        <button class="btn btn-default" type="submit">Añadir</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
	</form:form>
</TalleresLaPlata:layout>