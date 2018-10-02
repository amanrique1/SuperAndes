/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class Bodega {
	
	private Long id;
	
	private double capacidadVolumen;
	
	private double capacidadPeso;
	
	private String unidadesPeso;
	
	private String unidadesVolumen;
	
	private Long idSucursal;
	
	private double nivelReOrden;
	
	private String tipoProducto;
	
	public Bodega(Long pId,double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo)
	{
		id=pId;
		capacidadVolumen=pCapV;
		capacidadPeso=pCapP;
		unidadesPeso=pUniP;
		unidadesVolumen=pUniV;
		idSucursal=pIdSuc;
		nivelReOrden=pNivel;
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
	 * @return the nivelReOrden
	 */
	public double getNivelReOrden() {
		return nivelReOrden;
	}



	/**
	 * @param nivelReOrden the nivelReOrden to set
	 */
	public void setNivelReOrden(double nivelReOrden) {
		this.nivelReOrden = nivelReOrden;
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
