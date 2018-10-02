/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosBodega {
	
	private String codigoProducto;
	
	private Long idBodega;
	
	private int cantidadProductos;
	
	public ProductosBodega(String pCodigo,Long pId, int pCant)
	{
		codigoProducto=pCodigo;
		idBodega=pId;
		cantidadProductos=pCant;
	}
	
	

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}



	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
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
