package model;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;



public class Categoria
{
	
	private String nombreCategoria;


	
	private boolean perecedero;

	
	
	private Date fechaVencimiento;

	
	
	private ArrayList<TipoProducto> tiposProducto;

	public Categoria(String pNombre, Boolean pPerecedero, Date pFecha){
	nombreCategoria=pNombre;
	perecedero=pPerecedero;
	fechaVencimiento=pFecha;
	tiposProducto=new ArrayList<TipoProducto>();
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}


	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	public boolean isPerecedero() {
		return perecedero;
	}


	public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public ArrayList<TipoProducto> getTipoProducto() {
		return tiposProducto;
	}


	public void setTipoProducto(ArrayList<TipoProducto> tipoProducto) {
		this.tiposProducto = tipoProducto;
	}
	
	public void addTipoProducto(TipoProducto tipoProducto) {
		tiposProducto.add(tipoProducto);
	}




}

