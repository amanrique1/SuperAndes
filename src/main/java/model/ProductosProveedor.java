/**
 * 
 */
package main.java.model;

import java.sql.Timestamp;

/**
 * @author Andres
 *
 */
public class ProductosProveedor {

	private String tipoProducto;
	
	private Long idPedido;
	
	private double precioUnidad;
	
	private int cantidadUnidades;
	
	private Long idProveedor;
	
	private String nombreProducto;
	
	private Timestamp fechaVencimiento;
	
	private double calificacionCalidad;
	
	public ProductosProveedor(String pTipo,Long pIdP,double pPrecioU,int pCanti,Long pIdProv,String pNombrePro,Timestamp pFechaV,double pCali)
	{
		
		tipoProducto=pTipo;
		idPedido=pIdP;
		precioUnidad=pPrecioU;
		cantidadUnidades=pCanti;
		idProveedor=pIdProv;
		nombreProducto=pNombrePro;
		fechaVencimiento=pFechaV;
		calificacionCalidad=pCali;
		
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

	/**
	 * @return the idPedido
	 */
	public Long getIdPedido() {
		return idPedido;
	}

	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * @return the precioUnidad
	 */
	public double getPrecioUnidad() {
		return precioUnidad;
	}

	/**
	 * @param precioUnidad the precioUnidad to set
	 */
	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	/**
	 * @return the cantidadUnidades
	 */
	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	/**
	 * @param cantidadUnidades the cantidadUnidades to set
	 */
	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	/**
	 * @return the idProveedor
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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

	/**
	 * @return the calificacionCalidad
	 */
	public double getCalificacionCalidad() {
		return calificacionCalidad;
	}

	/**
	 * @param calificacionCalidad the calificacionCalidad to set
	 */
	public void setCalificacionCalidad(double calificacionCalidad) {
		this.calificacionCalidad = calificacionCalidad;
	}
	
}
