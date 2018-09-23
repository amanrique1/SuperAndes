/**
 * 
 */
package main.java.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andres
 *
 */
public class Factura {

	private Long id;
	
	private Timestamp fecha;
	
	private Long idPersona;
	
	
	public Factura(Long pId,Timestamp pFecha,Long pIdPersona)
	{
		id=pId;
		fecha=pFecha;
		idPersona=pIdPersona;
		
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
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}


}
