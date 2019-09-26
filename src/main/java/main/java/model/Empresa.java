package main.java.model;


public class Empresa extends Comprador
{
	
	
	private String nit;
	
	private String direccion;

	public Empresa(String pNit,String pNombre,String pCorreoElectronico,String pDireccion,double puntos){
		super(pNombre,pCorreoElectronico,pNit,puntos);
		nit=pNit;		
		direccion=pDireccion;
		
	}
	public Empresa(){
		super();
		
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		setIdentificador(nit);
		this.nit = nit;
	}

	
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}

