<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="mecanicos">
    <h2>
        <c:if test="${mecanico['new']}">Nuevo </c:if> Mecanico
    </h2>
    <form:form modelAttribute="mecanico" class="form-horizontal" id="add-mecanico-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Nombre" name="nombre"/>
            <TalleresLaPlata:inputField label="Apellidos" name="apellidos"/>
            <TalleresLaPlata:inputField label="DNI" name="dni"/>
            <TalleresLaPlata:inputField label="Telefono" name="telefono"/>
            <TalleresLaPlata:inputField label="E-mail" name="email"/>
            <TalleresLaPlata:inputField label="Usuario" name="user.username"/>
            <TalleresLaPlata:inputField label="Contraseña"  name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${mecanico['new']}">
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