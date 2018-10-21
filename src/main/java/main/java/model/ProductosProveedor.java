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

	private long idPedido;

	private double precioUnidad;

	private int cantidadUnidades;

	private String idProveedor;

	private Timestamp fechaVencimiento;

	private double calificacionCalidad;

	public ProductosProveedor(String pCodigo,long pIdP,double pPrecioU,int pCanti,String pIdProv,Timestamp pFechaV,double pCali)
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
	public long getIdPedido() {
		return idPedido;
	}

	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(long idPedido) {
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
	public String getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
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
