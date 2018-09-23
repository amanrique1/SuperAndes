/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class TipoProducto {
	
	private String tipo;
	
	private String codigoBarras;
	
	public TipoProducto(String pTipo,String pCodigoBarras)
	{
		tipo=pTipo;
		codigoBarras=pCodigoBarras;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	
	
}
