<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>

<TalleresLaPlata:layout pageName="reparaciones">
	<h2>
		<c:if test="${reparacion['new']}">Nueva </c:if>
		Reparación
	</h2>
	<form:form modelAttribute="reparacion" class="form-horizontal"
		id="add-reparacion-form">
		<div class="form-group has-feedback">
			<TalleresLaPlata:inputField label="Duracion" name="duracion" />
			<TalleresLaPlata:inputField label="Precio" name="precio" />
			<label for="tipoReparacion">Escoge un tipo de reparacion:</label> <select
				name="tipoReparacion" id="tipoReparacion">
				<c:forEach items="${tipoReparacion}" var="tipoReparacion">
					<option value="${tipoReparacion}"><c:out
							value="${tipoReparacion}" /></option>
				</c:forEach>
			</select>
		  <label for="cliente">Cliente:</label>
  	<select name="cliente.dni" id="cliente.dni"> 
  		<c:forEach items="${cliente}" var="cliente">
  		<option value="${cliente.dni}" ><c:out value="${cliente.nombre} ${cliente.apellidos}" /></option> 
    </c:forEach>
  </select> 
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