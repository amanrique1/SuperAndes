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
	
	private String metodoAlmacenamiento;
	
	private String nombreCategoria;
	
	public TipoProducto(String pTipo,String pMetodoAlmacenamiento,String pCategoria)
	{
		tipo=pTipo;
		metodoAlmacenamiento=pMetodoAlmacenamiento;
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
	 * @return the metodoAlmacenamiento
	 */
	public String getMetodoAlmacenamiento() {
		return metodoAlmacenamiento;
	}

	/**
	 * @param metodoAlmacenamiento the metodoAlmacenamiento to set
	 */
	public void setMetodoAlmacenamiento(String metodoAlmacenamiento) {
		this.metodoAlmacenamiento = metodoAlmacenamiento;
	}

	
	
}
