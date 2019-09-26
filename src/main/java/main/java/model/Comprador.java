/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class Comprador {
	
	protected String nombre;
	
	protected String correoElectronico;
	
	protected String identificador;
	
	protected double puntos;

	public Comprador(String pNombre, String pCorreoElectronico, String pIdentificador, double pPuntos)
	{
		nombre=pNombre;
		correoElectronico=pCorreoElectronico;
		identificador=pIdentificador;
		puntos=pPuntos;
	}
	public Comprador()
	{
	
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
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the puntos
	 */
	public double getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	
	public String toString()
	{
		return identificador;
	}

}
