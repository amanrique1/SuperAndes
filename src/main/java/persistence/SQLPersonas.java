/**
 * 
 */
package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;
import main.java.model.Empresa;
import main.java.model.Persona;

/**
 * @author Andres
 *
 */
public class SQLPersonas {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLCompradores sqlCompradores;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPersonas (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarPersona (PersistenceManager pm, String pIdentificador,String pNombre,String pCorreo) 
	{
		sqlCompradores.agregarComprador(pm, pIdentificador, pNombre, pCorreo);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonas() + "(Cedula) values (?)");
        q.setParameters(pIdentificador);
        q.executeUnique();
	}

	
	public void eliminarPersonaPorNombre (PersistenceManager pm, String nombre)
	{
		String Cedula=sqlCompradores.eliminarCompradorPorNombre(pm, nombre);
        Query q3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonas () + " WHERE Cedula = ?");
        q3.setParameters(Cedula);
        q3.executeUnique();
	}

	public void eliminarPersonaPorCedula (PersistenceManager pm, String identificador)
	{
		
       sqlCompradores.eliminarCompradorPorIdentificador(pm, identificador);
        Query q3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonas () + " WHERE Cedula = ?");
        q3.setParameters(identificador);
        q3.executeUnique();
	}

	public Persona darPersonaPorCedula (PersistenceManager pm, String identificador) 
	{
		
		Comprador comp=sqlCompradores.darCompradorPorIdentificador(pm, identificador);
		Persona pers= new Persona(comp.getIdentificador(), comp.getNombre(), comp.getCorreoElectronico());
		return pers;
	}

	public List<Persona> darPersonas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaCompradores()+" INNER JOIN "+pp.darTablaPersonas()+" ON identenficador=Cedula");
		q.setResultClass(Persona.class);
		return (List<Persona>) q.executeList();
		
	}
	
}
