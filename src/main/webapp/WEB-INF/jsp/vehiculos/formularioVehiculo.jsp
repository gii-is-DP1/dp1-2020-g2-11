<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<TalleresLaPlata:layout pageName="vehiculos">
    <h2>
    <c:out value="${confirmacion}"/>
        <c:if test="${vehiculo['new']}">Nuevo </c:if> Vehiculo
    </h2>
    <form:form modelAttribute="vehiculo" class="form-horizontal" id="add-producto-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Matricula" name="matricula"/>
            
           			  <label for="tipoVehiculo">Escoge un tipo de vehiculo:</label>
  			<select name="tipoVehiculo" id="tipoVehiculo"> 
  			<c:forEach items="${tipoVehiculo}" var="tipoVehiculo">
 			  <option value="${tipoVehiculo}" ><c:out value="${tipoVehiculo}" /></option> 
    </c:forEach>
  </select> 
            <TalleresLaPlata:inputField label="Fecha de fabricacion" name="fechaFabricacion"/>
            <TalleresLaPlata:inputField label="Kilometraje" name="kilometraje"/>
            <sec:authorize access="hasAuthority('admin')">
             <TalleresLaPlata:inputField label="Dni Cliente" name="cliente.dni"/>
             </sec:authorize>
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