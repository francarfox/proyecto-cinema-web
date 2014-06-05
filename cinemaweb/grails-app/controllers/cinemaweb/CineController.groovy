package cinemaweb

class CineController {
	static scaffold = true

    def create = {

        def data = [nombre: "",ubicacion: "",precioBase: "20", apertura: "08:00", cierre:"00:00"]
        def cine = null
        if(params.submit){
            data = params
            cine = new Cine(nombre: params.nombre, ubicacion: params.ubicacion, precioBase: params.float("precioBase"),apertura: params.apertura, cierre: params.cierre, foto: "default")
            if(cine.validate()){
                cine.save();
                redirect(action:"show", id: cine.id)
            }        
        }

        def model = [data: data, hours: this.getOpenCloseHours(), cine: cine, action: "create", id: "",back: [action:"index",id:""]]
        render(view: "create",model: model)
    }


    def edit = {
        def cine = Cine.get(params.id)
        def data = cine
        if (params.submit) {
            cine.nombre = params.nombre
            cine.ubicacion = params.ubicacion
            cine.precioBase = params.float("precioBase")
            cine.apertura = params.apertura
            cine.cierre = params.cierre
            if (cine.validate()) {
                cine.save();
                redirect(action:"show", id: cine.id)
            }
        }

        def model = [data: data, hours: this.getOpenCloseHours(), cine: cine, action: "edit", id: cine.id,back: [action:"show",id:cine.id]]
        render(view: "create",model: model)
    }

    
    def show = {
    	def cine = Cine.get(params.id)
    	//def comentarios = cine.obtenerComentarios()

    	//[cine: cine, comentarios: comentarios]
        [cine: cine]
    }


    def index = {
        def cines = Cine.list()

        [cines: cines]
    }

    def comentar = {
    	def usuario = Usuario.miUsuario()
    	def cine = Cine.get(params.id)

    	usuario.comentar(cine, params.mensaje)
    	redirect(action: "show", id:params.id)
    }


    //metodos auxiliares

    //devuelve el listado de horas de apertura y cierre para el pulldown
    private def getOpenCloseHours(){
        return ["00:00", "01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00",
                "12:00", "13:00","14:00", "15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"
        ]
    }
}
