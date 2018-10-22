package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
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
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarSucursal (PersistenceManager pm,long id,String pNombre,String pCiudad,String pDireccion) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "( idSucursal,nombre, Ciudad, direccion) values (?, ?, ?,?)");
		q1.setParameters(id,pNombre,pCiudad,pDireccion);
		q1.executeUnique();
	}


	public void eliminarSucursalPorNombre (PersistenceManager pm, String nombre)
	{
		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal () + " WHERE nombre = ?");
		q2.setParameters(nombre);
		q2.executeUnique();
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


	public List<Sucursal> darSucursales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaSucursal());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>)q.executeList();

	}


}
