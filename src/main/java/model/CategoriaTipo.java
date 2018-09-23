/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class CategoriaTipo {

	private String tipo;

	private String metodoAlmacenamiento;

	private String categoria;

	public CategoriaTipo(String pTipo,String pMetodoAlmacenamiento,String pCategoria)
	{
		
		tipo=pTipo;
		metodoAlmacenamiento=pMetodoAlmacenamiento;
		categoria=pCategoria;
		
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

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
