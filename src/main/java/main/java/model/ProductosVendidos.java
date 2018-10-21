package main.java.model;

public class ProductosVendidos {

	private long idFactura;
	
	private String idProductoSucursal;
	
	private int cantidad;
	
	public ProductosVendidos(long pIdFactura,String pIdProductoSucursal, int pCantidad)
	{
		idFactura=pIdFactura;
		idProductoSucursal=pIdProductoSucursal;
		cantidad=pCantidad;
	}

	/**
	 * @return the idFactura
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getIdProductoSucursal() {
		return idProductoSucursal;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setIdProductoSucursalProducto(String codigoProducto) {
		this.idProductoSucursal = codigoProducto;
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
