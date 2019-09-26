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

	private long numero;
	
	private Timestamp fecha;
	
	private String idComprador;
	
	private long idSucursal;
	
	public FacturasComprador()
	{
		numero=0;
		fecha=new Timestamp(System.currentTimeMillis());
		idComprador="";
		idSucursal=0;
		
		
	}
	
	public FacturasComprador(Long pId,Timestamp pFecha,String pIdPersona, long pIdSucursal)
	{
		numero=pId;
		fecha=pFecha;
		idComprador=pIdPersona;
		idSucursal=pIdSucursal;
		
	}
	
	
	
	/**
	 * @return the idComprador
	 */
	public String getIdComprador() {
		return idComprador;
	}



	/**
	 * @param idComprador the idComprador to set
	 */
	public void setIdComprador(String idComprador) {
		this.idComprador = idComprador;
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
	public long getNumero() {
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

	public String toString()
	{
		return numero+"";
	}

}
