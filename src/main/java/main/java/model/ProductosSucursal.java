/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class ProductosSucursal {
	
	private long idSucursal;
	
	private double precio;
	
	private String codigoProducto;
	
	private long idPromocion;
	
	private String idProductoSucursal;
	
	private int cantidadProducto;
	
	public ProductosSucursal(long pIdSuc, double pPrecio,String pCodigo, String pIdProductoSucursal)
	{
	
		idSucursal=pIdSuc;
		precio=pPrecio;
		codigoProducto=pCodigo;
		idProductoSucursal=pIdProductoSucursal;
		
	}
	public ProductosSucursal()
	{	
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public String getIdProductoSucursal() {
		return idProductoSucursal;
	}

	public void setIdProductoSucursal(String idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	

	
	

}
