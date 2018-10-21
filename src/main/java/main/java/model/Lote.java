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

	private String codigoProducto;
	
	private Timestamp fechaVencimiento;
	
	private int cantidadProductos;
	
	public Lote(String pCodigo ,Timestamp pFecha, int pCantidad)
	{
		codigoProducto=pCodigo;
		fechaVencimiento=pFecha;
		cantidadProductos=pCantidad;
	}


	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}


	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}


	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}


	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
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
