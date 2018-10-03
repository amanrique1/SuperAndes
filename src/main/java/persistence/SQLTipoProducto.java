package main.java.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Categoria;
import main.java.model.Lote;
import main.java.model.TipoProducto;

public class SQLTipoProducto {
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLCategoria sqlCategoria;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLTipoProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarTipoProducto (PersistenceManager pm, String pTipo,String pMetodoAlmacenamiento,String pCategoria) throws Exception 
	{
		if(sqlCategoria.darCategoria(pm, pCategoria)==null)
		{
			throw new Exception("Categoria inexistente");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoProducto() + "(nombreTipo, metodoAlmacenamiento, nombreCategoria) values (?, ?, ?)");
		q1.setParameters( pTipo, pMetodoAlmacenamiento, pCategoria);
		q1.executeUnique();
	}



	public List<TipoProducto> darTiposProducto (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaTipoProducto());
		q.setResultClass(Lote.class);
		return (List<TipoProducto>) q.executeList();

	}

	public TipoProducto darTipoProducto (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaTipoProducto()+ " WHERE nombreTipo= ? ");
		q.setResultClass(Lote.class);
		q.setParameters(tipo);
		return (TipoProducto) q.executeUnique();

	}

	public void eliminarTipoProducto (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "DELETE * FROM "+ pp.darTablaTipoProducto()+ " WHERE nombreTipo= ?  ");
		q.setParameters(tipo);
		q.executeUnique();

	}

	
}
