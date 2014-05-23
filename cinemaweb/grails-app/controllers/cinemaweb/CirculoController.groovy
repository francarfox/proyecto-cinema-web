package cinemaweb
import grails.converters.JSON

class CirculoController {

	static scaffold = true

    def index = {

    	def circulos = Circulo.list() 
        [circulos: circulos]
    }

    def indexusuario = {

    	def circulos = Circulo.list() 
        [circulos: circulos]
    }

    def create = {

    	def usuario = Usuario.get(params.id)

    	if (session.usuario == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			render(view: "create")	
		}
	}

	def armar = {

		def usuario = Usuario.get(params.id)

		String nombre = params.nombre
		String tags = params.tags
		Usuario administrador = session.usuario

		def circulo = new Circulo(nombre: nombre,tags: tags,administrador: administrador)

		if (circulo.validate()){
			//circulo.addToUsuarios(user)
			circulo.save()
			redirect(action:"show", id:circulo.id) 
			//render(view: "show", model:[circulo:circulo, messageV: "El circulo ${circulo.nombre} se ha creado correctamente."])
		}
		else {
			render(view: "create", model:[circulo:circulo, message: "ERROR: No se han ingresado los datos correctamente."])
		}
	}

	def delete = {

		def circulo = Circulo.get(params.id)
		circulo.delete()
		return
	}

	def unirse = {

		def usuario = Usuario.get(params.id)
		def circulo = Circulo.get(params.id)

    	if (session.usuario == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			if ( (circulo.estaUsuario(session.usuario)) || (session.usuario.userId == circulo.administrador.userId) ) { 
				redirect(action: "show", id:circulo.id)
			}
			else {
				circulo.addToUsuarios(session.usuario)
				render(view: "show", model: [circulo:circulo, messageV: "Se ha unido al circulo ${circulo.nombre} correctamente."])
			}
		}
	}

	def desunirse = {

		def usuario = Usuario.get(params.id)
		def circulo = Circulo.get(params.id)

		if (session.usuario == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			circulo.removeFromUsuarios(session.usuario) //NO FUNCAA!!!!
			/*JSON.use('deep')
        	render (circulo.usuarios) as JSON
        	return*/
			redirect(action:"index")
		}
	}

	def edit = {

		def usuario = Usuario.get(params.id)

		if (session.usuario == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			def circulo = Circulo.get(params.id)	
			[circulo:circulo]
		}
	}

	def actualizar = {

		def circulo = Circulo.get(params.id)

		circulo.nombre = params.nombre
		circulo.tags = params.tags
				
		if (circulo.validate()){
			circulo.save()
			render(view: "show", model: [circulo:circulo,messageV: "Los datos de su circulo han sido actualizados correctamente."])
		} else {
			render(view: "edit", model: [circulo:circulo,message: "ERROR: Los datos ingresados no son válidos."])
		}
	}


	def show = {

		def usuario = Usuario.get(params.id)

		if (session.usuario == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			def circulo = Circulo.get(params.id)
			if (session.usuario.userId == circulo.administrador.userId) {	
				render(view:"showAdmin", model:[circulo:circulo])
			}
			else {
				if ( (circulo.estaUsuario(session.usuario)) || (session.usuario.userId == circulo.administrador.userId) ) {
					[circulo:circulo]
				}
				else {
					render(view:"denegado")
					//redirect(action:"index") con mensaje de error
				}
			}
		}
	}
}
