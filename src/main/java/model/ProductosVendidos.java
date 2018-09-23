package main.java.model;

public class ProductosVendidos {

	private Long idFactura;
	
	private String codigoProducto;
	
	private int cantidad;
	
	public ProductosVendidos(Long pIdFactura,String pCodigo, int pCantidad)
	{
		idFactura=pIdFactura;
		codigoProducto=pCodigo;
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
