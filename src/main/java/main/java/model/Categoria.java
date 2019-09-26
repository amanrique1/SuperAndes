/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class Categoria {

	private String nombreCategoria;
	
	private boolean perecedero;
	
	public Categoria(String pNombre,boolean pPerecedero)
	{
		nombreCategoria=pNombre;
		perecedero=pPerecedero;
	}

	public Categoria()
	{
	}

	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * @return the perecedero
	 */
	public boolean isPerecedero() {
		return perecedero;
	}

	/**
	 * @param perecedero the perecedero to set
	 */
	public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}
	/**
	 * @param perecedero the perecedero to set
	 */
	public void setPerecedero(String perecedero) {
		if(perecedero.equalsIgnoreCase("true"))
			this.perecedero = true;
		else
			this.perecedero = false;		
	}
	
}
