<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="facturas">
    <h2>
        <c:if test="${factura['new']}">Nuevo </c:if> Factura
    </h2>
    <form:form modelAttribute="factura" class="form-horizontal" id="add-factura-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Descripcion" name="descripcion"/>
            <TalleresLaPlata:inputField label="Precio" name="precio"/>
            <TalleresLaPlata:inputField label="TipoPago" name="tipoPago"/>
            <TalleresLaPlata:inputField label="FechaEmision" name="fechaEmision"/>
            <TalleresLaPlata:inputField label="Pagado" name="pagado"/>
             <TalleresLaPlata:inputField label="Cliente" name="cliente.dni"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">A�adir</button>
            </div>
        </div>
    </form:form>
</TalleresLaPlata:layout>
