package main.java.model;

public class VentaPromocion {

private Long idPromocion;
	
	private int cantidad;
	
	public VentaPromocion(Long pIdPromocion,int pCantidad)
	{
		idPromocion=pIdPromocion;
		cantidad=pCantidad;
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
