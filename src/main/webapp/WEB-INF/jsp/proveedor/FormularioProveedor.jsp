<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="proveedores">
    <h2>
        <c:if test="${proveedor['new']}">Nuevo </c:if> Proveedor
    </h2>
    <form:form modelAttribute="proveedor" class="form-horizontal" id="add-proveedor-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Nombre" name="nombre"/>
            <TalleresLaPlata:inputField label="Telefono" name="telefono"/>
            <TalleresLaPlata:inputField label="Direccion" name="direccion"/>
            <TalleresLaPlata:inputField label="Email" name="email"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${proveedor['new']}">
                        <button class="btn btn-default" type="submit">Registrarse</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</TalleresLaPlata:layout>
