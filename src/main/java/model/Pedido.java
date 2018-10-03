/**
 * 
 */
package main.java.model;

import java.sql.Timestamp;

/**
 * @author Andres
 *
 */
public class Pedido {

	private Long id;
	
	private Timestamp fechaEntregaAcordado;
	
	private Timestamp fechaEntrega;
	
	private EstadoPedido estado;
	
	private Long idSucursal;
	
	private String idProveedor;
	
	public Pedido(Long pId,Timestamp pFechaP,Timestamp pFechaE,EstadoPedido pEstado,Long pIdSuc,String idProveedor2)
	{
		id=pId;
		fechaEntregaAcordado=pFechaP;
		fechaEntrega=pFechaE;
		estado=pEstado;
		idSucursal=pIdSuc;
		idProveedor=idProveedor2;
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
	 * @return the fechaEntregaAcordado
	 */
	public Timestamp getFechaEntregaAcordado() {
		return fechaEntregaAcordado;
	}

	/**
	 * @param fechaEntregaAcordado the fechaEntregaAcordado to set
	 */
	public void setFechaEntregaAcordado(Timestamp fechaPedido) {
		this.fechaEntregaAcordado = fechaPedido;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the estado
	 */
	public EstadoPedido getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
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
	 * @return the idProveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
}
