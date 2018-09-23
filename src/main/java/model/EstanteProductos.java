package main.java.model;

public class EstanteProductos {

	private Long idEstante;
	
	private String codigoBarrasProducto;
	
	public  EstanteProductos(Long pId,String pCodigoBarras) {
		
		idEstante=pId;
		codigoBarrasProducto=pCodigoBarras;
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
	public String getCodigoBarrasProducto() {
		return codigoBarrasProducto;
	}

	/**
	 * @param codigoBarrasProducto the codigoBarrasProducto to set
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
	}
	
}
