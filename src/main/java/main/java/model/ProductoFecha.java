package main.java.model;

import java.sql.Timestamp;

public class ProductoFecha {
	
	private String codigoProducto;
	
	private Timestamp fecha;
	
	public ProductoFecha(String pcodigo,Timestamp pFecha)
	{
		codigoProducto=pcodigo;
		fecha=pFecha;
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
	
	

}
