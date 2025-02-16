package cinemaweb

import grails.transaction.Transactional

class CineService extends DomainService{

    @Transactional
	public def getDomainInstance(String id=""){
		return (id) ? Cine.get(id) : new Cine()
	}

	@Transactional
	public def create(def attributes){
		def domain = this.getDomainInstance()
		this.submitDomainAttributes(domain,attributes)
	}

	@Transactional
	public def edit(String id, def attributes){
		def domain = this.getDomainInstance(id)
		this.submitDomainAttributes(domain,attributes)
	}

	@Transactional
	public def getCine(String id){
		return this.getDomainInstance(id)
	}

	@Transactional
	public def getListadoCines() {
		return Cine.list(sort:"nombre")
	}

	@Transactional
	public def eliminarCine(cineID){
		def cine = Cine.get(cineID)
		
		//borro comentarios
		cine.comentarios.each{
			cine.eliminarComentario(it)
			it.delete()
		}
		
		cine.delete()
	}

	@Transactional
	public def getPromociones(cine){
		return cine.getPromos()
	}

	@Transactional
	public def getOpenCloseHours(){
		return Cine.getOpenCloseHours()
	}
}
