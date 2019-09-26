package main.java.model;


public class ProductosVendidos {

	private long noFactura;
	
	private int cantidadVendida;
	
	private String idProductoSucursal;
	

	
	public ProductosVendidos()
	{
		noFactura=0;
		idProductoSucursal="";
		cantidadVendida=0;
	}
	public ProductosVendidos(long pIdFactura,String pIdProductoSucursal, int pCantidad)
	{
		noFactura=pIdFactura;
		idProductoSucursal=pIdProductoSucursal;
		cantidadVendida=pCantidad;
	}
	/**
	 * @return the noFactura
	 */
	public long getNoFactura() {
		return noFactura;
	}
	/**
	 * @param noFactura the noFactura to set
	 */
	public void setNoFactura(long noFactura) {
		this.noFactura = noFactura;
	}
	/**
	 * @return the cantidadVendida
	 */
	public int getCantidadVendida() {
		return cantidadVendida;
	}
	/**
	 * @param cantidadVendida the cantidadVendida to set
	 */
	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
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
