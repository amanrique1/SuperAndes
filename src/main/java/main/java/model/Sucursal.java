/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class Sucursal {

	private long id;
	
	private String nombre;
	
	private String ciudad;
	
	private String direccion;
	
	public Sucursal(long pId,String pNombre,String pCiudad,String pDireccion)
	{
		id=pId;
		nombre=pNombre;
		ciudad=pCiudad;
		direccion=pDireccion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
