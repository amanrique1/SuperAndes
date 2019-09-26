package main.java.model;

public class ProductosMostrar {

	
	
	private int cantidad;
	
	private double precio;
	
	private long idPromocion;

	private String nombre;
	
	private String presentacion;

	private String idProductoSucursal;
	
	private long idEstante;



	public ProductosMostrar(String idProductoSucursal,int cantidad, long idEstante) {
		this.idProductoSucursal=idProductoSucursal;
		
		this.cantidad=cantidad;
		
		this.idPromocion=-1l;
		
		this.idEstante=idEstante;


	}
	
	
	public ProductosMostrar(ProductosMostrar prod) {
		this.idProductoSucursal=prod.getIdProductoSucursal();
				
		this.precio=prod.getPrecio();
		
		this.nombre=prod.getNombre();
		
		this.idPromocion=prod.getIdPromocion();
		
		this.presentacion=prod.getPresentacion();
		
		this.idEstante=prod.getIdEstante();

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
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
	 * @return the idPromocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
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
	
	public boolean tienePromocion()
	{
		return idPromocion!=-1l;
	}
	/**
	 * @return the idProductoSucursal
	 */
	public String getIdProductoSucursal() {
		return idProductoSucursal;
	}

	/**
	 * @param idProductoSucursal the idProductoSucursal to set
	 */
	public void setIdProductoSucursal(String idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}
	public String getPresentacion() {
		return presentacion;
	}


	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}



	public long getIdEstante() {
		return idEstante;
	}


	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}
	
	public String toString()
	{
		return "cantidad: "+cantidad+"  precio: "+precio+"  idPromocion: "+idPromocion+"  nombre: "
				+nombre+"  presentacion: "+presentacion+"  idProductoSucursal: "+idProductoSucursal+"  idEstante: "+idEstante;
	}

}
