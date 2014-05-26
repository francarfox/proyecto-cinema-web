<html>
<head>
	<title>Crear Usuario · CinemaWeb</title>
	<meta name="layout" content="bootstrap-main"/>
</head>
<body>
	<div class="container">

		<g:if test="${message}">
	    	<div id="message_error">${message}</div>
	    </g:if>
		<g:hasErrors bean="${usuario}">
			<div id="message_error">
				<g:renderErrors bean="${usuario}" as="list" />
			</div>
		</g:hasErrors>

		<div class="col-md-5">
			<g:form action="registrar">
				<h2 style="text-align:center;">Datos Usuario</h2>
				<div class="form-group">
					<label for="userId">Usuario *</label>
					<g:field type="text" class="form-control" name="userId" />
				</div>
				<div class="form-group">
					<label for="password">Password *</label>
					<g:passwordField class="form-control" name="password"/>
				</div>
				<div class="form-group">
					<label for="passwordV">Confirmar Password *</label>
					<g:passwordField class="form-control" name="passwordV"/>
				</div>
				<br />
				<h2 style="text-align:center;">Datos Personales</h2>
				<div class="form-group">
					<label for="nombre">Nombre *</label>
					<g:field type="text" class="form-control" name="nombre" />
				</div>
				<div class="form-group">
					<label for="apellido">Apellido *</label>
					<g:field type="text" class="form-control" name="apellido" />
				</div>
				<!--<div class="form-group">
					<label for="foto">Foto</label>
					<input type="file" class="form-control" name="foto" />
				</div>-->
				<div class="form-group">
					<label for="email">Email *</label>
					<g:field type="text" class="form-control" name="email" />
				</div>
				<div class="form-group">
					<label for="localidad">Localidad</label>
					<g:field type="text" class="form-control" name="localidad" />
				</div>
				<div class="form-group">
					<label for="pais">Pais</label>
					<g:countrySelect name="pais" class="form-control" noSelection="['':'Selecciona tu pais']"/>
				</div>
				<br />
				<g:submitButton class="btn btn-lg btn-login btn-block" name="Registrarme"/>
			</g:form>		
		</div>
	</div>
</body>
</html>