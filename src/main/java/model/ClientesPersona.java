/**
 * 
 */
package main.java.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andres
 *
 */
public class ClientesPersona {

	private Long cedula;

	private String nombre;

	private String correoElectronico;

	private double puntos;

	public ClientesPersona(Long pCedula,String pNombre,String pCorreo,double pPuntos)
	{
		cedula=pCedula;
		nombre=pNombre;
		correoElectronico=pCorreo;
		puntos=pPuntos;
		
	}

	/**
	 * @return the cedula
	 */
	public Long getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(Long cedula) {
		this.cedula = cedula;
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
	 * @return the puntos
	 */
	public double getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	

}
