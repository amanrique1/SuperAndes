/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosSucursal {
	
	private Long idSucursal;
	
	private double precio;
	
	private int cantidad;
	
	private String codigoProducto;
	
	private Long idPromocion;
	
	public ProductosSucursal(Long pIdSuc, double pPrecio,int pCantidad,String pCodigo, Long pIdPromo)
	{
	
		idSucursal=pIdSuc;
		precio=pPrecio;
		cantidad=pCantidad;
		codigoProducto=pCodigo;
		idPromocion=pIdPromo;
		
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
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	public Long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}

}
