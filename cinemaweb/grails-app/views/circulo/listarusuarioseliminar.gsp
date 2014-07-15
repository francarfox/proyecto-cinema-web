<html>
<head>
	<title>Listado de usuarios - CinemaWeb</title>
	<meta name="layout" content="bootstrap-main"/>
</head>
<body>
	<div class="container">

	    <div class="row">
			<div class="col-md-8">
				<h1>Usuarios del círculo ${circulo.getNombre()}</h1>
			</div>
			<div class="col-md-4" style="margin-top:40px;">
				<g:form action="#" class="form-inline" style="float:right">
					<g:field class="form-control form-busqueda" type="text" placeholder="Buscar Circulos" name="busquedacirculo" />
					<g:submitButton class="btn btn-lg btn-buscar" name="Buscar"/>
				</g:form>
			</div>
		</div>
			
		<div class="col-md-8" style="margin-top:20px;">
			<g:form action="desunirse" id="${circulo.id}">
				<g:if test="${circulo.usuarios != null && circulo.usuarios.size() != 1}">
					<g:each in="${circulo.usuarios}" var="usuario">
						<g:if test="${usuario.getUserId() != circulo.getAdministrador()}">
							<div class="col-md-2">
								<p>FOTO</p>
								<br />
								<p><strong><g:link controller="usuario" action="show" id="${usuario.id}">${usuario.getUserId()}</g:link></strong></p>
								<g:checkBox name="listaeliminar" value="${usuario.id}" />
							</div>
						</g:if>
					</g:each>
					<div class="col-md-12">
						<br />
						<g:submitButton class="btn btn-lg btn-medium btn-crearcirculo" name="Eliminar"/>
					</div>
				</g:if>
				<g:else>
					<h4 style="margin-left:20px;">No hay usuarios unidos para eliminar</h4>
				</g:else>
			</g:form>
		</div>

		
	</div>
</body>
</html>