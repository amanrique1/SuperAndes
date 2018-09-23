/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductoEnVentaSucursal {
	
	private Long idSucursal;
	
	private String codigoBarrasProducto;
	
	public ProductoEnVentaSucursal(Long pId,String pCodigo)
	{
		idSucursal=pId;
		codigoBarrasProducto=pCodigo;
	}

	/**
	 * @return the idSucursal
	 */
	public Long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @return the codigoBarrasProducto
	 */
	public String getCodigoBarrasProducto() {
		return codigoBarrasProducto;
	}

	/**
	 * @param codigoBarrasProducto the codigoBarrasProducto to set
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

}
