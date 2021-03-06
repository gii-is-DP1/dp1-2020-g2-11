<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, cita or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'cliente'}"
						url="/cliente/find" title="cliente">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<span>Clientes</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize
					access="hasAuthority('admin') or hasAuthority('mecanico')">
					<petclinic:menuItem active="${name eq 'vehiculo'}"
						url="/vehiculos" title="vehiculo">
						<span class="glyphicon glyphicon-road" aria-hidden="true"></span>
						<span>Veh�culos</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize
					access="hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'vehiculo'}"
						url="/vehiculos/cliente" title="vehiculo">
						<span class="glyphicon glyphicon-road" aria-hidden="true"></span>
						<span>Mis Veh�culos</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize
					access="hasAuthority('admin') or hasAuthority('mecanico')">
					<petclinic:menuItem active="${name eq 'cita'}" url="/citas"
						title="cita">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>Citas</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'cita'}"
						url="/citas/cliente" title="cita">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<span>Mis Citas</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize
					access="hasAuthority('admin') or hasAuthority('mecanico')">
					<petclinic:menuItem active="${name eq 'estancia'}"
						url="/estancias" title="estancia">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						<span>Estancias</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'proveedores'}" url="/proveedores"
						title="proveedor">
						<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
						<span>Proveedores</span>
					</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'productos'}" url="/productos"
						title="producto">
						<span class="glyphicon glyphicon-barcode" aria-hidden="true"></span>
						<span>Productos</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin') or hasAuthority('mecanico')">
					<petclinic:menuItem active="${name eq 'reparaciones'}" url="/reparaciones"
						title="reparacion">
						<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
						<span>Reparacion</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'revisiones'}" url="/revisiones"
						title="revisiones">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						<span>Revisiones</span>
					</petclinic:menuItem>
				</sec:authorize>
				
					<sec:authorize access="hasAuthority('mecanico')">
					<petclinic:menuItem active="${name eq 'revisiones'}" url="/revisionesNoAsignadas"
						title="revisiones">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						<span>Revisiones por asignar</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'facturas'}" url="/factura/Actualizada"
						title="factura">
						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
						<span>Facturas</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('cliente')">
					<petclinic:menuItem active="${name eq 'facturas'}" url="/facturas/Actualizada/cliente"
						title="producto">
						<span class="glyphicon glyphicon-barcode" aria-hidden="true"></span>
						<span>Mis Facturas</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'pedidos'}" url="/pedidos"
						title="pedido">
						<span class="glyphicon glyphicon-gift" aria-hidden="true"></span>
						<span>Pedidos</span>
					</petclinic:menuItem>
				</sec:authorize>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Registro</a></li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>�
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li class="divider"></li>
							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
											<sec:authentication var="principal" property="principal" />
												<sec:authorize access="hasAnyAuthority('mecanico')">
												
												<a href="<c:url value="/revisiones/mecanico"/>" class="btn btn-primary btn-block">Mis revisiones</a>
											
													</sec:authorize>
													<sec:authentication var="principal" property="principal" />
												<sec:authorize access="hasAnyAuthority('admin')">
												
												<a href="<c:url value="/mecanicos"/>" class="btn btn-primary btn-block">Empleados</a>
												<a href="<c:url value="/facturasNoAbonadas"/>" class="btn btn-primary btn-block">Facturas no abonadas</a>
											
													</sec:authorize>	
													
														
											</p>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
