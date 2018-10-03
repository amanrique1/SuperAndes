package main.java.model;

import java.sql.Timestamp;

public class Promocion {

	private long id;
	
	private String descripcion;
	
	private Timestamp fechaInicial;
	
	private Timestamp fechaFinal;
	
	private int x;
	
	private int y;
	
	private TipoPromocion tipoPromocion;
	
	public Promocion( long pId, int px,  int py, Timestamp pFechaFin, Timestamp pFechaIni, TipoPromocion pTipo,String pDescripcion)
	{
		id=pId;
		fechaInicial=pFechaIni;
		fechaFinal=pFechaFin;
		x=px;
		y=py;
		tipoPromocion=pTipo;
		descripcion=pDescripcion;
	}
	
	

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}



	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}



	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}



	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}



	/**
	 * @return the tipoPromocion
	 */
	public TipoPromocion getTipoPromocion() {
		return tipoPromocion;
	}



	/**
	 * @param tipoPromocion the tipoPromocion to set
	 */
	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
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



	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}



	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	
	
}
