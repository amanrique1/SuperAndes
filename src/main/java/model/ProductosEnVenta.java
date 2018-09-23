package main.java.model;

public class ProductosEnVenta {

	private String codigoBarras;
	
	private String nombre;
	
	private String marca;
	
	private double precioUnidadMedida;
	
	private String presentacion;
	
	private int cantidadPresentacion;
	
	private String unidadMedida;
	
	private int volumen;
	
	private String unidadVolumen;
	
	private int cantidadReOrden;
	
	private String idLote;

	public ProductosEnVenta(String pCodigo,String pNombre,String pMarca,double pPrecio,int pCantidad,String pUnidad,int pVol,String pUnidadVol, int pCantR,String pIdLote)
	{
		codigoBarras=pCodigo;
		nombre=pNombre;
		marca=pMarca;
		precioUnidadMedida=pPrecio;
		cantidadPresentacion=pCantidad;
		unidadMedida=pUnidad;
		volumen=pVol;
		unidadVolumen=pUnidadVol;
		cantidadReOrden=pCantR;
		idLote=pIdLote;
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
	 * @return the precioUnidadMedida
	 */
	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}

	/**
	 * @param precioUnidadMedida the precioUnidadMedida to set
	 */
	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
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
	 * @return the cantidadPresentacion
	 */
	public int getCantidadPresentacion() {
		return cantidadPresentacion;
	}

	/**
	 * @param cantidadPresentacion the cantidadPresentacion to set
	 */
	public void setCantidadPresentacion(int cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the volumen
	 */
	public int getVolumen() {
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

	/**
	 * @return the cantidadReOrden
	 */
	public int getCantidadReOrden() {
		return cantidadReOrden;
	}

	/**
	 * @param cantidadReOrden the cantidadReOrden to set
	 */
	public void setCantidadReOrden(int cantidadReOrden) {
		this.cantidadReOrden = cantidadReOrden;
	}

	/**
	 * @return the idLote
	 */
	public String getIdLote() {
		return idLote;
	}

	/**
	 * @param idLote the idLote to set
	 */
	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
}
