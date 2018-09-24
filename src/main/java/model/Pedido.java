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
	
	private double volumen;
	
	private double peso;
	
	private String unidadesPeso;
	
	private String unidadesVol;
	
	private Timestamp fechaPedido;
	
	private Timestamp fechaEntrega;
	
	private EstadoPedido estado;
	
	private Long idSucursal;
	
	private Long idProveedor;
	
	public Pedido(Long pId,double pVolumen,double pPeso,String pUnidadesP,String pUnidadesV,Timestamp pFechaP,Timestamp pFechaE,EstadoPedido pEstado,Long pIdSuc,Long pIdPro)
	{
		id=pId;
		volumen=pVolumen;
		peso=pPeso;
		unidadesPeso=pUnidadesP;
		unidadesVol=pUnidadesV;
		fechaPedido=pFechaP;
		fechaEntrega=pFechaE;
		estado=pEstado;
		idSucursal=pIdSuc;
		idProveedor=pIdPro;
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
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return the unidadesPeso
	 */
	public String getUnidadesPeso() {
		return unidadesPeso;
	}

	/**
	 * @param unidadesPeso the unidadesPeso to set
	 */
	public void setUnidadesPeso(String unidadesPeso) {
		this.unidadesPeso = unidadesPeso;
	}

	/**
	 * @return the unidadesVol
	 */
	public String getUnidadesVol() {
		return unidadesVol;
	}

	/**
	 * @param unidadesVol the unidadesVol to set
	 */
	public void setUnidadesVol(String unidadesVol) {
		this.unidadesVol = unidadesVol;
	}

	/**
	 * @return the fechaPedido
	 */
	public Timestamp getFechaPedido() {
		return fechaPedido;
	}

	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
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
	public Long getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
}
