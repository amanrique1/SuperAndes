/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class EstantePromocion {
	
	private Long idPromocion;
	
	private Long idEstante;
	
	public EstantePromocion(Long pIdPromo,Long pIdEsta)
	{
		idPromocion=pIdPromo;
		idEstante=pIdEsta;
	}

	/**
	 * @return the idPromocion
	 */
	public Long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}

	/**
	 * @return the idEstante
	 */
	public Long getIdEstante() {
		return idEstante;
	}

	/**
	 * @param idEstante the idEstante to set
	 */
	public void setIdEstante(Long idEstante) {
		this.idEstante = idEstante;
	}

}
