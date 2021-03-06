<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<TalleresLaPlata:layout pageName="citas">

	<h2>Buscar cita</h2>
	<form:form modelAttribute="cita" action="/citas" method="post"
		class="form-horizontal" id="buscador-citas">
		<div class="form-group">
			<div class="control-group" id="fechaCita">
				<label class="col-sm-2 control-label">FechaCita</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="fechaCita" size="30"
						maxlength="80" />
					<span class="help-inline"><form:errors path="*" /></span>
					<span id="fechaCita.errors">No existe cita con esa fecha </span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Buscar</button>
			</div>
		</div>
	</form:form>
</TalleresLaPlata:layout>