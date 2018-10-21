/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosSucursal {
	
	private long idSucursal;
	
	private double precio;
	
	private String codigoProducto;
	
	private long idPromocion;
	
	private String idProductoSucursal;
	
	public ProductosSucursal(long pIdSuc, double pPrecio,String pCodigo, long pIdPromo, String pIdProductoSucursal)
	{
	
		idSucursal=pIdSuc;
		precio=pPrecio;
		codigoProducto=pCodigo;
		idPromocion=pIdPromo;
		idProductoSucursal=pIdProductoSucursal;
		
	}

	/**
	 * @return the idSucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
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
	 * @return the idPromocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	/**
	 * @return the idProductoSucursal
	 */
	public String getIdProductoSucursal() {
		return idProductoSucursal;
	}

	/**
	 * @param idProductoSucursal the idProductoSucursal to set
	 */
	public void setIdProductoSucursal(String idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

	
	

}
