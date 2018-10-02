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
	
	private String nombreCategoria;
	
	public TipoProducto(String pTipo,String pCodigoBarras,String pCategoria)
	{
		tipo=pTipo;
		codigoBarras=pCodigoBarras;
		nombreCategoria=pCategoria;
	}

	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
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
