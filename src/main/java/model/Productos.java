package main.java.model;

public class Productos {

	private String codigoBarras;
	
	private String nombre;
	
	private String marca;
	
	private String presentacion;
	
	private String unidadPeso;
	
	private double cantidadPeso;
	
	private double volumen;
	
	private String unidadVolumen;
	
	private String tipoProducto;
	
	

	/**
	 * @return the unidadPeso
	 */
	public String getUnidadPeso() {
		return unidadPeso;
	}

	/**
	 * @param unidadPeso the unidadPeso to set
	 */
	public void setUnidadPeso(String unidadPeso) {
		this.unidadPeso = unidadPeso;
	}

	/**
	 * @return the cantidadPeso
	 */
	public double getCantidadPeso() {
		return cantidadPeso;
	}

	/**
	 * @param cantidadPeso the cantidadPeso to set
	 */
	public void setCantidadPeso(double cantidadPeso) {
		this.cantidadPeso = cantidadPeso;
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

	public Productos(String pCodigo,String pNombre,String pMarca,String pPresentacion, String pUnidadPeso, double pCantidadPeso,String pUnidadVol,double pVol,String pTipoProducto )
	{
		codigoBarras=pCodigo;
		nombre=pNombre;
		marca=pMarca;
		presentacion=pPresentacion;
		unidadPeso=pUnidadPeso;
		cantidadPeso=pCantidadPeso;
		volumen=pVol;
		unidadVolumen=pUnidadVol;
		tipoProducto=pTipoProducto;
	}
	
	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	
	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	
	/**
	 * @return the unidadPeso
	 */
	public String getUnidadMedida() {
		return unidadPeso;
	}

	/**
	 * @param unidadPeso the unidadPeso to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadPeso = unidadMedida;
	}

	/**
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return the unidadVolumen
	 */
	public String getUnidadVolumen() {
		return unidadVolumen;
	}

	/**
	 * @param unidadVolumen the unidadVolumen to set
	 */
	public void setUnidadVolumen(String unidadVolumen) {
		this.unidadVolumen = unidadVolumen;
	}

}
