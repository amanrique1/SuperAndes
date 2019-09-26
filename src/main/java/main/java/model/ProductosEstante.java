package main.java.model;

public class ProductosEstante {

	private Long idEstante;

	private String idProductoSucursal;

	private int cantidad;

	public  ProductosEstante(Long pId,String pCodigoBarras, int pCantidad) {

		idEstante=pId;
		idProductoSucursal=pCodigoBarras;
		cantidad=pCantidad;
	}

	public  ProductosEstante() {
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



	/**
	 * @return the idEstante
	 */
	public Long getIdEstante() {
		return idEstante;
	}

	/**
	 * @param idEstante the idEstante to set
	 */
	public void setIdEstante(Long idEstante) {
		this.idEstante = idEstante;
	}

	/**
	 * @return the codigoBarrasProducto
	 */
	public String getIdProductoSucursal() {
		return idProductoSucursal;
	}

	/**
	 * @param codigoBarrasProducto the codigoBarrasProducto to set
	 */
	public void setIdProductoSucursal(String idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

}
