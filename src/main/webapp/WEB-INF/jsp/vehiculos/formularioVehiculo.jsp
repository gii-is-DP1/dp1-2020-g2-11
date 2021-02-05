<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="vehiculos">
    <h2>
        <c:if test="${vehiculo['new']}">Nuevo </c:if> Vehiculo
    </h2>
    <form:form modelAttribute="vehiculo" class="form-horizontal" id="add-producto-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Matricula" name="matricula"/>
            <TalleresLaPlata:inputField label="Tipo" name="tipoVehiculo"/>
            <TalleresLaPlata:inputField label="Fecha de fabricacion" name="fechaFabricacion"/>
            <TalleresLaPlata:inputField label="Kilometraje" name="kilometraje"/>
             <TalleresLaPlata:inputField label="Dni Cliente" name="cliente.dni"/>
            <c:out
								value="${vehiculos.matricula}" />
								
					<c:out value="${vehiculos.tipoVehiculo}" />
					<c:out value="${vehiculos.fechaFabricacion}" />
					<c:out value="${vehiculos.kilometraje}" />
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vehiculo['new']}">
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