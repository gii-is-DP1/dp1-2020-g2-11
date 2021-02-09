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
       
        <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaFabricacion").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script></jsp:attribute>
    <jsp:body>
    <h2>
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
  </select> 	<sec:authorize access="hasAuthority('admin')">
  
  			  <label for="cliente">Cliente:</label>
  	<select name="cliente.dni" id="cliente.dni"> 
  		<c:forEach items="${cliente}" var="cliente">
  		<option value="${cliente.dni}" ><c:out value="${cliente.dni}" /></option> 
    </c:forEach>
  </select> 
  </sec:authorize>
            <TalleresLaPlata:inputField label="Fecha de fabricacion" name="fechaFabricacion"/>
            <TalleresLaPlata:inputField label="Kilometraje" name="kilometraje"/>
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
         </jsp:body>
    
</TalleresLaPlata:layout>