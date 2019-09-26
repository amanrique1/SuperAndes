package main.java.model;

public class CarritoProductos {

	
	private String idProductoSucursal;
	
	private int cantidadProducto;
	
	private long idCarrito;
	
	public CarritoProductos()
	{		
	}
	
	public CarritoProductos(String pIdProductoSucursal, int pcantidadProducto, long pIdCarrito)
	{
		idProductoSucursal=pIdProductoSucursal;
		cantidadProducto=pcantidadProducto;
		idCarrito=pIdCarrito;
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
	 * @return the cantidadProducto
	 */
	public int getcantidadProducto() {
		return cantidadProducto;
	}

	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setcantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}


	/**
	 * @return the idCarrito
	 */
	public Long getIdCarrito() {
		return idCarrito;
	}


	/**
	 * @param idCarrito the idCarrito to set
	 */
	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}
}
