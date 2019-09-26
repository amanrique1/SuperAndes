package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andres
 *
 */
public class Estante {

	private long idEstante;
	
	private double capacidadVolumen;
	
	private double capacidadPeso;
	
	private String unidadPeso;
	
	private String unidadVolumen;
	
	private double nivelReOrden;
	
	private long idSucursal;
	
	private String tipoProducto;
	
	private List<ProductosMostrar> productos;
	
	public Estante(long idEstante,long idSucursal,String unidadPeso,String unidadVolumen,double capacidadPeso,double capacidadVolumen, double nivelReOrden, String tipoProducto)
	{
		this.idEstante=idEstante;
		this.capacidadVolumen=capacidadVolumen;
		this.capacidadPeso=capacidadPeso;
		this.unidadPeso=unidadPeso;
		this.unidadVolumen=unidadVolumen;
		this.nivelReOrden=nivelReOrden;
		this.idSucursal=idSucursal;
		this.tipoProducto=tipoProducto;
		productos=new ArrayList<ProductosMostrar>();

	}

	public Estante(long pId, String pTipo)
	{
		idEstante=pId;
		tipoProducto=pTipo;
		productos=new ArrayList<ProductosMostrar>();
	}

	public Estante()
	{
		
		productos=new ArrayList<ProductosMostrar>();
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
	public Long getIdEstante() {
		return idEstante;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdEstante(Long id) {
		this.idEstante =id;
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
	public void setCapacidadVolumen(Float capacidadVolumen) {
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
	public void setCapacidadPeso(Float capacidadPeso) {
		this.capacidadPeso = capacidadPeso;
	}

	/**
	 * @return the unidadesPeso
	 */
	public String getUnidadPeso() {
		return unidadPeso;
	}

	/**
	 * @param unidadesPeso the unidadesPeso to set
	 */
	public void setUnidadPeso(String unidadesPeso) {
		this.unidadPeso = unidadesPeso;
	}

	/**
	 * @return the unidadesVolumen
	 */
	public String getUnidadVolumen() {
		return unidadVolumen;
	}

	/**
	 * @param unidadesVolumen the unidadesVolumen to set
	 */
	public void setUnidadVolumen(String unidadesVolumen) {
		this.unidadVolumen = unidadesVolumen;
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
	public void setNivelReOrden(Float cantidadReOrden) {
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
	
	public List<ProductosMostrar> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosMostrar> productos) {
		this.productos = productos;
	}
	
	public void agregarProducto(ProductosMostrar producto)
	{
		productos.add(producto);
	}

	public void deletaAll()
	{
		productos.clear();
	}

}
