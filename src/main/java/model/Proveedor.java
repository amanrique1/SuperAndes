/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class Proveedor {

	private String nit;
	
	private String nombre;
	
	private String correoElectronico;
	
	private String direccion;
	
	public Proveedor(String pNit,String pNombre,String pCorreo,String pDire)
	{
		
		nit=pNit;
		nombre=pNombre;
		correoElectronico=pCorreo;
		direccion=pDire;
		
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
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
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
