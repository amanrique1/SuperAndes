/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class PromocionesVendidas {

private Long noFactura;
	
	private Long idPromocion;
	
	private int cantidadVendida;
	
	public PromocionesVendidas(Long pIdFactura,Long pCodigo, int pCantidad)
	{
		noFactura=pIdFactura;
		idPromocion=pCodigo;
		cantidadVendida=pCantidad;
	}
	public PromocionesVendidas()
	{	
	}

	public Long getNoFactura() {
		return noFactura;
	}

	public void setNoFactura(Long noFactura) {
		this.noFactura = noFactura;
	}

	public Long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	
}
