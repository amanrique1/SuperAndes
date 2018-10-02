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
public class Persona extends Comprador {

	private String cedula;

	public Persona(String pCedula,String pNombre,String pCorreo)
	{
		super(pNombre, pCorreo, "CEDULA", 0);
		cedula=pCedula;

	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
