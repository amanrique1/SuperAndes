package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Proveedor;

public class SQLProveedor {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarProveedor (PersistenceManager pm,String pNit,String pNombre,String pCorreo,long pTel) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor() + "(NIT, nombre, correoElectronico, telefono) values (?, ?, ?, ?)");
		q1.setParameters(pNit,pNombre,pCorreo,pTel);
	q1.executeUnique();
	}

	
	public void eliminarProveedorPorNombre (PersistenceManager pm, String nombre)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
        q2.setParameters(nombre);
        q2.executeUnique();
	}

	public void eliminarProveedorPorNit (PersistenceManager pm, String nit)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE NIT = ?");
        q2.setParameters(nit);
        q2.executeUnique();
       
	}

	public Proveedor darProveedorPorNit (PersistenceManager pm, String identificador) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE NIT = ?");
		q1.setResultClass(Proveedor.class);
		q1.setParameters(identificador);
		Proveedor comp=(Proveedor)q1.executeUnique();
		return comp;
	}
	
	public Proveedor darProveedorPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
		q1.setResultClass(Proveedor.class);
		q1.setParameters(nombre);
		Proveedor comp=(Proveedor)q1.executeUnique();
		return comp;
	}

	
	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
		
	}


}
