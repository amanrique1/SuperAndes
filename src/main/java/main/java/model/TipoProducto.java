/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class TipoProducto {
	
	private String nombreTipo;
	
	private String metodoAlmacenamiento;
	
	private String nombreCategoria;
	
	public TipoProducto(String pTipo,String pMetodoAlmacenamiento,String pCategoria)
	{
		nombreTipo=pTipo;
		metodoAlmacenamiento=pMetodoAlmacenamiento;
		nombreCategoria=pCategoria;
	}
	public TipoProducto()
	{
		
	}


	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public String getMetodoAlmacenamiento() {
		return metodoAlmacenamiento;
	}

	public void setMetodoAlmacenamiento(String metodoAlmacenamiento) {
		this.metodoAlmacenamiento = metodoAlmacenamiento;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	
	
}
