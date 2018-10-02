/**
 * 
 */
package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;
import main.java.model.Empresa;

/**
 * @author Andres
 *
 */
public class SQLCompradores {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLCompradores (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarComprador (PersistenceManager pm, String pNit,String pNombre,String pCorreo) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCompradores() + "(identificador, nombre, correoElectronico, puntos) values (?, ?, ?, ?)");
		q1.setParameters(pNit, pNombre, pCorreo,0);
	q1.executeUnique();
	}

	
	public String eliminarCompradorPorNombre (PersistenceManager pm, String nombre)
	{
		Query q1 = pm.newQuery(SQL, "SELECT identificador FROM " + pp.darTablaCompradores () + " WHERE nombre = ?");
		q1.setResultClass(String.class);
		String identificador=(String)q1.executeUnique();
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompradores () + " WHERE nombre = ?");
        q2.setParameters(nombre);
        q2.executeUnique();
        return identificador;
	}

	public void eliminarCompradorPorIdentificador (PersistenceManager pm, String id)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
        q2.setParameters(id);
        q2.executeUnique();
       
	}

	public Comprador darCompradorPorIdentificador (PersistenceManager pm, String identificador) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
		q1.setResultClass(Comprador.class);
		q1.setParameters(identificador);
		Comprador comp=(Comprador)q1.executeUnique();
		return comp;
	}

	
	public List<Comprador> darCompradores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaCompradores());
		q.setResultClass(Comprador.class);
		return (List<Comprador>) q.executeList();
		
	}


}
