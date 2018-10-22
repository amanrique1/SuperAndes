package main.java.model;

public class IndiceBodega {

	private long idBodega;
	
	private double capacidadVolumen;
	
	private String unidBodega;
	
	private int cantidad;
	
	private double cantidadVolumen;
	
	private String unidProducto;
	
	
	
	public IndiceBodega(long pIdBodega, double pCapacidadVolumen, String pUnidBodega, int pCantidad,double pCantidadVolumen, String pUnidProducto)
	{
		
		idBodega=pIdBodega;
		capacidadVolumen=pCapacidadVolumen;
		unidBodega=pUnidBodega;
		cantidad=pCantidad;
		cantidadVolumen=pCantidadVolumen;
		unidProducto=pUnidProducto;
		
	}



	/**
	 * @return the idBodega
	 */
	public long getIdBodega() {
		return idBodega;
	}



	/**
	 * @param idBodega the idBodega to set
	 */
	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
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
	 * @return the unidBodega
	 */
	public String getUnidBodega() {
		return unidBodega;
	}



	/**
	 * @param unidBodega the unidBodega to set
	 */
	public void setUnidBodega(String unidBodega) {
		this.unidBodega = unidBodega;
	}



	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}



	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * @return the cantidadVolumen
	 */
	public double getCantidadVolumen() {
		return cantidadVolumen;
	}



	/**
	 * @param cantidadVolumen the cantidadVolumen to set
	 */
	public void setCantidadVolumen(double cantidadVolumen) {
		this.cantidadVolumen = cantidadVolumen;
	}



	/**
	 * @return the unidProducto
	 */
	public String getUnidProducto() {
		return unidProducto;
	}



	/**
	 * @param unidProducto the unidProducto to set
	 */
	public void setUnidProducto(String unidProducto) {
		this.unidProducto = unidProducto;
	}

	public double darIndiceAlmacenamiento() 
	{
		double indice=100*darValorUnidad(unidProducto)*cantidad*cantidadVolumen;
		indice=indice/(darValorUnidad(unidBodega)*capacidadVolumen);
		return indice;
	}
	
	private double darValorUnidad(String unidad)
	{
		if(unidad.equalsIgnoreCase("mm3"))
				return Math.pow(1, -21);
		else if(unidad.equalsIgnoreCase("cm3")|unidad.equalsIgnoreCase("ml"))
		{
			return Math.pow(1, -18);
		}
		else if(unidad.equalsIgnoreCase("dm3")|unidad.equalsIgnoreCase("l"))
		{
			return Math.pow(1, -15);
		}
		else if(unidad.equalsIgnoreCase("m3"))
		{
			return Math.pow(1, -12);
		}
		else if(unidad.equalsIgnoreCase("m3"))
		{
			return Math.pow(1, -9);
		}
		else if(unidad.equalsIgnoreCase("dam3"))
		{
			return Math.pow(1, -6);
		}
		else if(unidad.equalsIgnoreCase("hm3"))
		{
			return Math.pow(1, -3);
		}
		else 
		{
			return 1;
		}
		
	}
	
}
