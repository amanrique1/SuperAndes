/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class PromocionesVendidas {

private Long idFactura;
	
	private Long idPromocion;
	
	private int cantidad;
	
	public PromocionesVendidas(Long pIdFactura,Long pCodigo, int pCantidad)
	{
		idFactura=pIdFactura;
		idPromocion=pCodigo;
		cantidad=pCantidad;
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the codigoProducto
	 */
	public Long detIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setIdPromocion(Long codigoProducto) {
		this.idPromocion = codigoProducto;
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
}
