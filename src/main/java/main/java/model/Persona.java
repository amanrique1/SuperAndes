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

	public Persona(String pCedula,String pNombre,String pCorreo,double pPuntos)
	{
		super(pNombre, pCorreo, pCedula, pPuntos);
		cedula=pCedula;

	}
	public Persona()
	{
		super();
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
		setIdentificador(cedula);
		this.cedula = cedula;
	}

}
