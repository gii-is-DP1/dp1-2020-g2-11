<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>

<TalleresLaPlata:layout pageName="pedidos">
<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaEntrada").datepicker({dateFormat: 'dd/mm/yy'});
            });
            $(function () {
                $("#fechaEmision").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script></jsp:attribute>
    <jsp:body>
	<h2>
		<c:if test="${pedido['new']}">Nuevo </c:if>Pedido
	</h2>
	<form:form modelAttribute="pedido" class="form-horizontal" id="add-pedido-form">
		<div class="form-group has-feedback">
			<TalleresLaPlata:inputField label="Fecha de Entrada del pedido al tayer (puede dejar el campo en blanco si no lo sabe)" name="fechaEntrada" />
			<TalleresLaPlata:inputField label="Fecha de Emision del pedido al proveedor" name="fechaEmision" />
			  <label for="proveedor">Escoge un proveedor:</label>
  			<select name="proveedor.id" id="proveedor.id	"> 
  			<c:forEach items="${proveedor}" var="proveedor">
 			  <option value="${proveedor.id}" ><c:out value="${proveedor.nombre}" /></option> 
    </c:forEach>
  </select> 
  <br><br>
            			  <label for="producto">Escoge un producto:</label>
  			<select name="producto.referencia" id="producto.referencia	"> 
  			<c:forEach items="${producto}" var="producto">
 			  <option value="${producto.referencia}" ><c:out value="${producto.nombre} ${producto.marca}" /></option> 
    </c:forEach>
  </select> 
  <br><br>
		</div>
		<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                    <c:when test="${revision['new']}">
                        <button class="btn btn-default" type="submit">Añadir</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar/Añadir</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
	</form:form>
	     </jsp:body>
	
</TalleresLaPlata:layout>