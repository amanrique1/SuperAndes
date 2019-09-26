/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosBodega {
	
	private String idProductoSucursal;
	
	private long idBodega;
	
	private int cantidad;
	
	public ProductosBodega(long pId, int pCant,String pIdProductoSucursal)
	{
		idProductoSucursal=pIdProductoSucursal;
		idBodega=pId;
		cantidad=pCant;
	}
	
	public ProductosBodega()
	{
		
	}
	

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidad() {
		return cantidad;
	}



	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidad(int cantidadProductos) {
		this.cantidad = cantidadProductos;
	}



	
	/**
	 * @return the idBodega
	 */
	public long getIdBodega() {
		return idBodega;
	}

	/**
	 * @param idBodega the idBodega to set
	 */
	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
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
