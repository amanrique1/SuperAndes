package main.java.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Lote;


public class SQLLote {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLProductos sqlProductos;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLLote (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarLote (PersistenceManager pm, String codigo, Timestamp fecha, int cantidad) throws Exception 
	{
		if(sqlProductos.darProductoPorCodigo(pm, codigo)==null)
		{
			throw new Exception("Codigo inexistente");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLotes() + "(codigo, fecha, nombre) values (?, ?, ?)");
		q1.setParameters( codigo, fecha, cantidad);
		q1.executeUnique();
	}



	public List<Lote> darLotes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaLotes());
		q.setResultClass(Lote.class);
		return (List<Lote>) q.executeList();

	}

	public Lote darLote (PersistenceManager pm, String codigo, Timestamp fecha)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaLotes()+ " WHERE codigoProducto= ? AND FechaVencimiento= ? ");
		q.setResultClass(Lote.class);
		q.setParameters(codigo, fecha);
		return (Lote) q.executeUnique();

	}

	public void eliminarLote (PersistenceManager pm, String codigo, Timestamp fecha)
	{
		Query q = pm.newQuery(SQL, "DELETE * FROM "+ pp.darTablaLotes()+ " WHERE codigoProducto= ? AND FechaVencimiento= ? ");
		q.setParameters(codigo, fecha);
		q.executeUnique();

	}


}
