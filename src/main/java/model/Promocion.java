package main.java.model;

import java.sql.Timestamp;

public class Promocion {

	private Long id;
	
	private double precioPromocion;
	
	private Timestamp fechaInicial;
	
	private Timestamp fechaFinal;
	
	public Promocion(Long pId,double pPrecio,Timestamp pFechaIni,Timestamp pFechaFin)
	{
		id=pId;
		precioPromocion=pPrecio;
		fechaInicial=pFechaIni;
		fechaFinal=pFechaFin;
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
	 * @return the precioPromocion
	 */
	public double getPrecioPromocion() {
		return precioPromocion;
	}

	/**
	 * @param precioPromocion the precioPromocion to set
	 */
	public void setPrecioPromocion(double precioPromocion) {
		this.precioPromocion = precioPromocion;
	}

	/**
	 * @return the fechaInicial
	 */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
}
