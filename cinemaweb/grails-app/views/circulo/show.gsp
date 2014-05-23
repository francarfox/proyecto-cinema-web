<html>
<head>
	<title>Ver Circulo - CinemaWeb</title>
	<meta name="layout" content="bootstrap-main"/>
</head>
<body>
	<div class="container">
		
			<g:if test="${message}">
	    		<div id="message_error">${message}</div>
	        </g:if>
	        <g:if test="${messageV}">
		    	<div id="message_val">${messageV}</div>
		    </g:if>
	        <g:hasErrors bean="${circulo}">
	            <div id="message_error">
	                <g:renderErrors bean="${circulo}" as="list" />
	            </div>
	        </g:hasErrors>

		<div class="col-md-8" style="margin-left:20px;">
			<h1>Circulo ${circulo.nombre}</h1>

			<div>
				<p>Nombre: ${circulo.nombre}</p>
				<p>Tags: ${circulo.tags}</p>
				<br />
				<hr style="border-top: 1px solid black;">
				<p> Comentarios: </p>
				<br />
				<hr style="border-top: 1px solid black;">
			</div>
			<br />

			<br />
			<g:form action="desunirse" id="${circulo.id}">
				<g:submitButton name="Desunirme"/>
			</g:form>
			<br />
			<g:form action="index">
				<g:submitButton name="Volver"/>
			</g:form>
		</div>
	</div>
</body>
</html>