/**
 * 
 */
package main.java.model;

import java.util.LinkedList;

/**
 * @author Andres
 *
 */
public class Sucursal {

	private long idSucursal;
	
	private String nombre;
	
	private String ciudad;
	
	private String direccion;
	
	public Sucursal(long pId,String pNombre,String pCiudad,String pDireccion)
	{
		idSucursal=pId;
		nombre=pNombre;
		ciudad=pCiudad;
		direccion=pDireccion;
	}

	/**
	 * Constructor por defecto
	 */
	public Sucursal() 
	{
		this.idSucursal = 0;
		this.nombre = "";
		this.ciudad = "";
		this.direccion = "";
		
	} 
	
	/**
	 * @return the id
	 */
	public Long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdSucursal(Long id) {
		this.idSucursal = id;

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
