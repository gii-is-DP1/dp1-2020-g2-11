<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="TalleresLaPlata" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<TalleresLaPlata:layout pageName="home">
<Body>
	<header>
        <nav>
            <ul>
                <li><a href="FormularioCliente.jsp">Iniciar sesion</a></li>
                <li><a href="Registro.jsp">Registrese</a></li>
            </ul>
        </nav>
	</header>
    <h2>BIENVENIDOS</h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/LogoTaller.jpg" var="logoTaller"/>
            <img src="${logoTaller}" width="300" height="200"/>
        </div>
    </div>
    </Body>
</TalleresLaPlata:layout>
