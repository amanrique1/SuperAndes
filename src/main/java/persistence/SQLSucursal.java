package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;
import main.java.model.Sucursal;

public class SQLSucursal {

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
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarSucursal (PersistenceManager pm,String pNombre,String pCiudad,String pDireccion) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "( nombre, Ciudad, direccion) values (?, ?, ?)");
		q1.setParameters(pNombre,pCiudad,pDireccion);
		q1.executeUnique();
	}


	public String eliminarSucursalPorNombre (PersistenceManager pm, String nombre)
	{
		Query q1 = pm.newQuery(SQL, "SELECT identificador FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
		q1.setResultClass(String.class);
		String identificador=(String)q1.executeUnique();
		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
		q2.setParameters(nombre);
		q2.executeUnique();
		return identificador;
	}

	public void eliminarSucursalPorId (PersistenceManager pm, long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE idSucursal = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}

	public Sucursal darSucursalPorId(PersistenceManager pm, long identificador) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE idSucursal = ?");
		q1.setResultClass(Sucursal.class);
		q1.setParameters(identificador);
		Sucursal comp=(Sucursal)q1.executeUnique();
		return comp;
	}
	
	public Sucursal darSucursalPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
		q1.setResultClass(Sucursal.class);
		q1.setParameters(nombre);
		Sucursal comp=(Sucursal)q1.executeUnique();
		return comp;
	}


	public List<Sucursal> darSucusales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaSucursal());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();

	}


}
