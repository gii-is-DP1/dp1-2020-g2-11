<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<TalleresLaPlata:layout pageName="citas">




 <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaCita").datepicker({dateFormat: 'dd/mm/yy'});
            });
            
        </script>
  </jsp:attribute>

    <jsp:body>

    <h2>
        <c:if test="${cita['new']}">Nueva </c:if> Cita
    </h2>
    <form:form modelAttribute="cita" class="form-horizontal" id="add-cita-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Fecha (posterior a hoy)" name="fechaCita" />
    El dia que selecciono no atendemos mas citas; por favor prueve otro dia.
            <TalleresLaPlata:inputField label="Hora" name="horaCita" />
            
<label for="vehiculo">Matricula de su coche:</label>
  	<select name="vehiculo.matricula" id="vehiculo.matricula"> 
  		<c:forEach items="${vehiculo}" var="vehiculo">
  		<option value="${vehiculo.matricula}" ><c:out value="${vehiculo.matricula}" /></option> 
    </c:forEach>
  </select> 
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cita['new']}">
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
