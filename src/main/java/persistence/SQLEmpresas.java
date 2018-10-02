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
public class SQLEmpresas {
	
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
	public SQLEmpresas (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarEmpresa (PersistenceManager pm, String pNit, String pDireccion,String pNombre,String pCorreo) 
	{
		sqlCompradores.agregarComprador(pm, pNit, pNombre, pCorreo);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpresas() + "(NIT, direccion) values (?, ?)");
        q.setParameters(pNit, pDireccion);
        q.executeUnique();
	}

	
	public void eliminarEmpresaPorNombre (PersistenceManager pm, String nombreEmpresa)
	{
		String nit=sqlCompradores.eliminarCompradorPorNombre(pm, nombreEmpresa);
        Query q3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresas () + " WHERE NIT = ?");
        q3.setParameters(nit);
        q3.executeUnique();
	}

	public void eliminarEmpresaPorNit (PersistenceManager pm, String idEmpresa)
	{
		
       sqlCompradores.eliminarCompradorPorIdentificador(pm, idEmpresa);
        Query q3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresas () + " WHERE NIT = ?");
        q3.setParameters(idEmpresa);
        q3.executeUnique();
	}

	public Empresa darEmpresaPorNit (PersistenceManager pm, String idEmpresa) 
	{
		
		Comprador comp=sqlCompradores.darCompradorPorIdentificador(pm, idEmpresa);
		Query q = pm.newQuery(SQL, "SELECT direccion FROM " + pp.darTablaEmpresas () + " WHERE NIT = ?");
		q.setResultClass(String.class);
		q.setParameters(idEmpresa);
		String direccion=(String)q.executeUnique();
		Empresa emp= new Empresa(comp.getIdentificador(), comp.getNombre(), comp.getCorreoElectronico(), direccion);
		return emp;
	}

	public List<Empresa> darEmpresas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaCompradores()+" INNER JOIN "+pp.darTablaEmpresas()+" ON identenficador=NIT");
		q.setResultClass(Empresa.class);
		return (List<Empresa>) q.executeList();
		
	}

}
