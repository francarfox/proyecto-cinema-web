package cinemaweb
import grails.converters.JSON

class CirculoController {

	static scaffold = true
	def fileService
	def circuloService

    def index() {
    	if (session.loggedUser == null){
			redirect(controller:'usuario' , action:'login')
		}
		else {
    		def circulos = this.circuloService.getListadoCirculos() 
    		def loggedUser = Usuario.get(session.loggedUser)
        	[circulos: circulos, loggedUser: loggedUser]
    	}
    }

    def create() {
    	if (session.loggedUser == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			render(view: "create")	
		}
	}

	def armar() {
        if(params.submit) {
            def errors = this.circuloService.create(session,params)
            if(!errors) {
            	//redirect(action:"show",id:params.circulo)
            	redirect(action:"index")
            } else {
            	render(view: "create", model:[errors:errors])
            }
        }
	}

	def delete() {
		this.circuloService.delete(params)
		return
	}

	def unirse() {
		def usuarioOnline = Usuario.get(session.loggedUser)
		def circulo = Circulo.get(params.id)

    	if (usuarioOnline == null){
			redirect(controller:'usuario' , action:'login')
		}
		else {
			if ( circulo.estaUsuario(usuarioOnline) ) {
				redirect(action: "show", id:circulo.id)
			}
			else {
				circulo.agregarUsuario(usuarioOnline)
				render(view: "show", model:[circulo:circulo, usuario:usuarioOnline])
			}
		}
	}

	def desunirse() {
		if (session.loggedUser == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			def circulo = Circulo.get(params.id)
			def loggedUser = Usuario.get(session.loggedUser)
			circulo.expulsarUsuario(loggedUser)
			redirect(action:"index")
		}
	}

	def eliminarusuario(Integer userid) {
		if (session.loggedUser == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			def circulo = Circulo.get(params.id)
			def usuarioAEliminar = Usuario.get(userid)
			circulo.expulsarUsuario(usuarioAEliminar)
			redirect(action:"show", id:circulo.id)
		}
	}

	def edit() {
		if (session.loggedUser == null){
			redirect(controller:'usuario' , action:'login' )
		}
		else {
			def circulo = Circulo.get(params.id)
			def loggedUser = Usuario.get(session.loggedUser)

			if (loggedUser.getUserId() == circulo.getAdministrador()) {
				[circulo:circulo]
			}
			else {
				render(view:"denegado")
			}
		}
	}

	def actualizar() {
		def circulo = Circulo.get(params.id)
		def loggedUser = Usuario.get(session.loggedUser)
		def errors =  this.circuloService.edit(circulo,params)
		if (!errors) {
			render(view: "show", model: [circulo:circulo, usuario:loggedUser])
		} else {
			render(view: "edit", model: [circulo:circulo,errors:errors])
		}
	}


	def show() {
		if (session.loggedUser == null) {
			redirect(controller:'usuario' , action:'login')
		} else {
			def circulo = Circulo.get(params.id)
			def loggedUser = Usuario.get(session.loggedUser)
			render(view:"show", model:[circulo:circulo, usuario:loggedUser])
		}
	}

	def comentar() {
		def loggedUser = Usuario.get(session.loggedUser)
    	def circulo = Circulo.get(params.id)

    	loggedUser.comentar(circulo, params.mensaje)
    	redirect(action: "show", id:params.id)
	}

	def listarusuarios() {
		def circulo = Circulo.get(params.id)
		[circulo:circulo]
	}

	def uploadPic() {
		def loggedUser = Usuario.get(session.loggedUser)
		def circulo = Circulo.get(params.id)

		def error = false
    	if (params.submit) {
        	error = this.circuloService.subirFoto(params.id, "foto",request.getFile("foto"), "/circulos-pics/")
        	if(!error){
        		redirect(action: "show", id:params.id)
        } else {
          		error = true
         	}
    	}

    	[circulo:circulo, error:error]
	}

	def eliminarcomentario() {
        def comentario = Comentario.get(params.comentarioid)
        def circulo = Circulo.get(params.id)
        def usuario = Usuario.get(session.loggedUser)

        if(session.loggedUserRol != "ADMIN"){
            redirect(controller: "usuario", action: "login") //tiene que ser admin para eliminar comentarios
        }else{
            usuario.eliminarComentario(comentario)
            circulo.eliminarComentario(comentario)
            comentario.delete()
            redirect(action:"listarcomentarios", controller:"usuario")
        }

    }

}
