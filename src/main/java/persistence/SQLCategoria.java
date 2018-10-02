package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Categoria;


public class SQLCategoria {
	
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
	public SQLCategoria (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarCategoria (PersistenceManager pm,String nombre,boolean perecedero) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCategorias() + "(nombre, perecedero) values (?, ?)");
		q1.setParameters( nombre, perecedero);
		q1.executeUnique();
	}


	public void eliminarCategoriaPorNombre (PersistenceManager pm, String nombre)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategorias () + " WHERE nombreCategoria = ?");
		q2.setParameters(nombre);
		q2.executeUnique();

	}


	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaCategorias());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();

	}


}
