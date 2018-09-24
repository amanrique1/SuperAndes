/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class BodegaProductos {
	
	private String codigoProducto;
	
	private Long idBodega;
	
	public BodegaProductos(String pCodigo,Long pId)
	{
		codigoProducto=pCodigo;
		idBodega=pId;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
