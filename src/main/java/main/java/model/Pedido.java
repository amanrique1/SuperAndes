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

	private Long idPedido;
	
	private Timestamp fechaEntregaAcordada;
	
	private Timestamp fechaEntrega;
	
	private EstadoPedido estado;
	
	private Long idSucursal;
	
	private String idProveedor;
	
	public Pedido(Long pId,Timestamp pFechaP,Timestamp pFechaE,EstadoPedido pEstado,Long pIdSuc,String idProveedor2)
	{
		idPedido=pId;
		fechaEntregaAcordada=pFechaP;
		fechaEntrega=pFechaE;
		estado=pEstado;
		idSucursal=pIdSuc;
		idProveedor=idProveedor2;
	}
	public Pedido() {
	}
	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Timestamp getFechaEntregaAcordada() {
		return fechaEntregaAcordada;
	}

	public void setFechaEntregaAcordada(Timestamp fechaEntregaAcordada) {
		this.fechaEntregaAcordada = fechaEntregaAcordada;
	}

	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	
}
