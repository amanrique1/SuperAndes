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
public class FacturasComprador {

	private Long numero;
	
	private Timestamp fecha;
	
	private Long idCliente;
	
	private Long idSucursal;
	
	public FacturasComprador(Long pId,Timestamp pFecha,Long pIdPersona, Long pIdSucursal)
	{
		numero=pId;
		fecha=pFecha;
		idCliente=pIdPersona;
		idSucursal=pIdSucursal;
		
	}
	
	
	
	/**
	 * @return the idCliente
	 */
	public Long getIdCliente() {
		return idCliente;
	}



	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}



	/**
	 * @return the idSucursal
	 */
	public Long getIdSucursal() {
		return idSucursal;
	}



	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}



	/**
	 * @return the numero
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Long id) {
		this.numero = id;
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
	 * @return the idCliente
	 */
	public Long getIdPersona() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idCliente = idPersona;
	}


}
