package main.java.model;

/**
 * @author Andres
 *
 */
public class Estante {

	private Long id;
	
	private double capacidadVolumen;
	
	private double capacidadPeso;
	
	private String unidadesPeso;
	
	private String unidadesVolumen;
	
	private double nivelReOrden;
	
	private Long idSucursal;
	
	private String tipoProducto;
	
	public Estante(Long pId,double pCapVol,double pCapPeso,String pUnidVol,String pUnidPeso, double pCantidad,Long pIdSucursal, String pTipo)
	{
		id=pId;
		capacidadVolumen=pCapVol;
		capacidadPeso=pCapPeso;
		unidadesPeso=pUnidPeso;
		unidadesVolumen=pUnidVol;
		nivelReOrden=pCantidad;
		idSucursal=pIdSucursal;
		tipoProducto=pTipo;
	}

	
	
	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}



	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
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
	 * @return the capacidadVolumen
	 */
	public double getCapacidadVolumen() {
		return capacidadVolumen;
	}

	/**
	 * @param capacidadVolumen the capacidadVolumen to set
	 */
	public void setCapacidadVolumen(double capacidadVolumen) {
		this.capacidadVolumen = capacidadVolumen;
	}

	/**
	 * @return the capacidadPeso
	 */
	public double getCapacidadPeso() {
		return capacidadPeso;
	}

	/**
	 * @param capacidadPeso the capacidadPeso to set
	 */
	public void setCapacidadPeso(double capacidadPeso) {
		this.capacidadPeso = capacidadPeso;
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
	 * @return the unidadesVolumen
	 */
	public String getUnidadesVolumen() {
		return unidadesVolumen;
	}

	/**
	 * @param unidadesVolumen the unidadesVolumen to set
	 */
	public void setUnidadesVolumen(String unidadesVolumen) {
		this.unidadesVolumen = unidadesVolumen;
	}

	/**
	 * @return the nivelReOrden
	 */
	public double getNivelReOrden() {
		return nivelReOrden;
	}

	/**
	 * @param nivelReOrden the nivelReOrden to set
	 */
	public void setNivelReOrden(int cantidadReOrden) {
		this.nivelReOrden = cantidadReOrden;
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
	
	
}
