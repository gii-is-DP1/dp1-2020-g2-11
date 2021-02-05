<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<TalleresLaPlata:layout pageName="citas">
    <h2>
        <c:if test="${estancia['new']}">Nuevo </c:if> Estancia
    </h2>
    <form:form modelAttribute="estancia" class="form-horizontal" id="add-estancia-form">
        <div class="form-group has-feedback">
            <TalleresLaPlata:inputField label="Fecha de entrada" name="fechaEntrada" />
            <TalleresLaPlata:inputField label="Hora de entrada" name="horaEntrada" />
            <TalleresLaPlata:inputField label="Dia de salida" name="fechaSalida" />
            <TalleresLaPlata:inputField label="Hora de salida" name="horaSalida" />
            <TalleresLaPlata:inputField label="Duracion de la estancia (en minutos)" name="duracion" />
            <TalleresLaPlata:inputField label="Matricula" name="vehiculo.matricula" />
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Añadir</button>    
            </div>
        </div>
    </form:form>
</TalleresLaPlata:layout>
