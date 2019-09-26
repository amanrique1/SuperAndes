package main.java.model;

import java.sql.Timestamp;

public class Promocion {

	private long idPromocion;
	
	private String descripcion;
	
	private Timestamp fechaInicial;
	
	private Timestamp fechaFinal;
	
	private int x;
	
	private int y;
	
	private TipoPromocion tipoPromocion;
	
	public Promocion( long pId, int px,  int py, Timestamp pFechaFin, Timestamp pFechaIni, TipoPromocion pTipo,String pDescripcion)
	{
		idPromocion=pId;
		fechaInicial=pFechaIni;
		fechaFinal=pFechaFin;
		x=px;
		y=py;
		tipoPromocion=pTipo;
		descripcion=pDescripcion;
	}

	public Promocion()
	{
		
	}
	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TipoPromocion getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}
	
	


	
	
}
