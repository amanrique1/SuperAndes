package main.java.model;

import java.io.Serializable;

public class Productos implements Comparable<Productos> {

	private String codigoBarras;
	
	private String nombre;
	
	private String marca;
	
	private String presentacion;
	
	private String unidadPeso;
	
	private double cantidadPeso;
	
	private double cantidadVolumen;
	
	private String unidadVolumen;
	
	private String tipoProducto;
	
	private int cantidad;
	
	public Productos(String codigoBarras, double cantidadPeso,double cantidadVolumen, int cantidad ) 
	{
		this.codigoBarras=codigoBarras;
		this.cantidadPeso=cantidadPeso;
		this.cantidadVolumen=cantidadVolumen;	
		this.cantidad=cantidad;

	}
	
	
	public Productos(String pCodigo,String pNombre,String pMarca,String pPresentacion, String pUnidadPeso, double pCantidadPeso,String pUnidadVol,double pVol,String pTipoProducto )
	{
		codigoBarras=pCodigo;
		nombre=pNombre;
		marca=pMarca;
		presentacion=pPresentacion;
		unidadPeso=pUnidadPeso;
		cantidadPeso=pCantidadPeso;
		cantidadVolumen=pVol;
		unidadVolumen=pUnidadVol;
		tipoProducto=pTipoProducto;
	}
	public Productos()
	{
	}
	

	public String getCodigoBarras() {
		return codigoBarras;
	}


	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getPresentacion() {
		return presentacion;
	}


	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}


	public String getUnidadPeso() {
		return unidadPeso;
	}


	public void setUnidadPeso(String unidadPeso) {
		this.unidadPeso = unidadPeso;
	}


	public double getCantidadPeso() {
		return cantidadPeso;
	}


	public void setCantidadPeso(double cantidadPeso) {
		this.cantidadPeso = cantidadPeso;
	}


	public double getCantidadVolumen() {
		return cantidadVolumen;
	}


	public void setCantidadVolumen(double cantidadVolumen) {
		this.cantidadVolumen = cantidadVolumen;
	}


	public String getUnidadVolumen() {
		return unidadVolumen;
	}


	public void setUnidadVolumen(String unidadVolumen) {
		this.unidadVolumen = unidadVolumen;
	}


	public String getTipoProducto() {
		return tipoProducto;
	}


	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public int compareTo(Productos prod) {
		if(cantidad>prod.getCantidad())
			return 1;
		else if(cantidad<prod.getCantidad())
			return -1;
		return 0;
	}
	public String toString( )
	{
		return codigoBarras;
	}

}
