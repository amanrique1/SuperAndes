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
	
	private int cantidadProductos;
	
	public ProductosBodega(long pId, int pCant,String pIdProductoSucursal)
	{
		idProductoSucursal=pIdProductoSucursal;
		idBodega=pId;
		cantidadProductos=pCant;
	}
	
	

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}



	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
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
