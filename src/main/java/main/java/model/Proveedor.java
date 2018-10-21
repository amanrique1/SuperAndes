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
	
	private long telefono;
	
	public Proveedor(String pNit,String pNombre,String pCorreo,long pTel)
	{
		
		nit=pNit;
		nombre=pNombre;
		correoElectronico=pCorreo;
		telefono=pTel;
		
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
	 * @return the telefono
	 */
	public long getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	
}
