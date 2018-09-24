/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class BodegaPromocion {

	private Long idPromocion;
	
	private Long idBodega;
	
	public BodegaPromocion(Long pIdPromo,Long pIdBode)
	{
		idBodega=pIdBode;
		idPromocion=pIdPromo;
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
	 * @return the idBodega
	 */
	public Long getIdBodega() {
		return idBodega;
	}

	/**
	 * @param idBodega the idBodega to set
	 */
	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}
	
}
