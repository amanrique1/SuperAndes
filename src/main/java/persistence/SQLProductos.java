/**
 * 
 */
package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;


/**
 * @author Andres
 *
 */
public class SQLProductos {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLTipoProducto sqlTipoProducto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductos (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarProducto (PersistenceManager pm, String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) throws Exception 
	{
		if (sqlTipoProducto.darTipoProducto(pm,tipoProducto)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductos() + "( CodigoBarras,  nombre,  marca,  presentacion,  unidadPeso,  cantidadPeso,  unidadVolumen, cantidadVolumen, TipoProducto) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters( codigo,  nombre,  marca,  presentacion,  unidadPeso,  cantidadPeso,  unidadVolumen, cantidadVolumen, tipoProducto);
	q1.executeUnique();
	}

	
	public void eliminarProductoPorNombre (PersistenceManager pm, String nombre)
	{
	 Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductos () + " WHERE nombre = ?");
        q2.setParameters(nombre);
        q2.executeUnique();
	}

	public void eliminarProductoPorCodigo (PersistenceManager pm, String id)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductos () + " WHERE CodigoBarras = ?");
        q2.setParameters(id);
        q2.executeUnique();
       
	}

	public Productos darProductoPorCodigo (PersistenceManager pm, String identificador) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductos () + " WHERE CodigoBarras = ?");
		q1.setResultClass(Productos.class);
		q1.setParameters(identificador);
		Productos comp=(Productos)q1.executeUnique();
		return comp;
	}

	
	public List<Productos> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos());
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
		
	}



}
