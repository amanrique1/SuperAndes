/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosPromocion {

	private Long idPromocion;

	private String codigoBarrasProducto;

	private int cantidad;

	public ProductosPromocion (Long pIdPromocion,String pCodigo,int pCantidad)
	{
		idPromocion=pIdPromocion;
		codigoBarrasProducto=pCodigo;
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
