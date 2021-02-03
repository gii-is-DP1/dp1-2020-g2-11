<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>

<TalleresLaPlata:layout pageName="productos">
    <h2>
        <c:if test="${producto['new']}">Nuevo </c:if> Producto
    </h2>
    <form:form modelAttribute="producto" class="form-horizontal" id="add-producto-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Nombre" name="nombre"/>
            <TalleresLaPlata:inputField label="Marca" name="marca"/>
            <TalleresLaPlata:inputField label="Stock" name="stock"/>
            <TalleresLaPlata:inputField label="Referencia" name="referencia"/>
            <TalleresLaPlata:inputField label="StockSeguridad" name="stockSeguridad"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${producto['new']}">
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