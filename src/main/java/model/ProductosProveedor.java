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

	private String codigoProducto;

	private Long idPedido;

	private double precioUnidad;

	private int cantidadUnidades;

	private Long idProveedor;

	private Timestamp fechaVencimiento;

	private double calificacionCalidad;

	public ProductosProveedor(String pCodigo,Long pIdP,double pPrecioU,int pCanti,Long pIdProv,Timestamp pFechaV,double pCali)
	{

		codigoProducto=pCodigo;
		idPedido=pIdP;
		precioUnidad=pPrecioU;
		cantidadUnidades=pCanti;
		idProveedor=pIdProv;
		fechaVencimiento=pFechaV;
		calificacionCalidad=pCali;

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
