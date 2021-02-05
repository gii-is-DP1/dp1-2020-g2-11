<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="revisiones">
    <h2>
        <c:if test="${revision['new']}">Nuevo </c:if> Revision
    </h2>
    <form:form modelAttribute="revision" class="form-horizontal" id="add-factura-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Descripcion" name="descripcion"/>
            <TalleresLaPlata:inputField label="Duracion" name="duracion"/>
            <TalleresLaPlata:inputField label="FechaRevision" name="fechaRevision"/>
            <TalleresLaPlata:inputField label="Cliente" name="cliente.dni"/>
            <TalleresLaPlata:inputField label="Matricula" name="vehiculo.matricula"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${revision['new']}">
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