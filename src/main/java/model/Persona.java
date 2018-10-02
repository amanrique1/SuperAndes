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

	private Long cedula;

	public Persona(Long pCedula,String pNombre,String pCorreo,double pPuntos)
	{
		super(pNombre, pCorreo, "CEDULA", 0);
		cedula=pCedula;

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

}
