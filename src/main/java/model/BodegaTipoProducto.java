/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class BodegaTipoProducto {

	private String tipoProducto;
	
	private Long idBodega;
	
	public BodegaTipoProducto(String pTipo,Long pIdBo)
	{
		tipoProducto=pTipo;
		idBodega=pIdBo;
	}

	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
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
