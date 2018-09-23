/**
 * 
 */
package main.java.model;

import java.sql.Timestamp;

/**
 * @author Andres
 *
 */
public class Lote {

	private Long id;
	
	private Timestamp fechaVencimiento;
	
	public Lote(Long pId,Timestamp pFecha)
	{
		id=pId;
		fechaVencimiento=pFecha;
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
	 * @return the fechaVencimiento
	 */
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
}
