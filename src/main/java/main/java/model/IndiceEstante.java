package main.java.model;

public class IndiceEstante {

	private long idEstante;
	
	private double capacidadVolumen;
	
	private String unidEstante;
	
	private int cantidad;
	
	private double cantidadVolumen;
	
	private String unidProducto;
	
	
	
	public IndiceEstante(long pIdEstante, double pCapacidadVolumen, String pUnidEstante, int pCantidad,double pCantidadVolumen, String pUnidProducto)
	{
		
		idEstante=pIdEstante;
		capacidadVolumen=pCapacidadVolumen;
		unidEstante=pUnidEstante;
		cantidad=pCantidad;
		cantidadVolumen=pCantidadVolumen;
		unidProducto=pUnidProducto;
		
	}



	/**
	 * @return the idEstante
	 */
	public long getIdEstante() {
		return idEstante;
	}



	/**
	 * @param idEstante the idEstante to set
	 */
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
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
	 * @return the unidEstante
	 */
	public String getUnidEstante() {
		return unidEstante;
	}



	/**
	 * @param unidEstante the unidEstante to set
	 */
	public void setUnidEstante(String unidEstante) {
		this.unidEstante = unidEstante;
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
		indice=indice/(darValorUnidad(unidEstante)*capacidadVolumen);
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
