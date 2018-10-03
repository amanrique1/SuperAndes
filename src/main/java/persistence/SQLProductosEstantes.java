/**
 * 
 */
package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductosEstante;
import main.java.model.ProductosVendidos;

/**
 * @author Andres
 *
 */
public class SQLProductosEstantes {
	
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLEstante sqlEstante;
	
	private SQLProductosSucursal sqlProductosSucursal;

	/* ********
	 * 			Métodos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductosEstantes (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarProductosEstante (PersistenceManager pm,long idEstante,int cantidad, String idProductoSucursal) throws Exception 
	{
		if(sqlEstante.darEstante(pm, idEstante)==null||sqlProductosSucursal.darProductoSucursal(pm, idProductoSucursal)==null)
		{
			throw new Exception("Datos invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosEstante() + "(idEstante, cantidad, idProductoSucursal) values (?, ?, ?)");
		q1.setParameters(  idEstante,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}


	public void eliminarProductosEstanteVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosEstante () + " WHERE cantidad = 0");
		q2.executeUnique();

	}
	
	public void descontarProductoSucursalDeEstanteEnCantidad (PersistenceManager pm,long idEstante,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante () + " SET cantidad=cantidad-? WHERE idEstante=? AND idProductoSucursal=?");
		q3.setParameters( cant,idEstante ,idProductoSucursal);
		q3.executeUnique();

	}

	public void abastecerProductoSucursalDeEstanteEnCantidad (PersistenceManager pm,long idEstante,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante () + " SET cantidad=cantidad+? WHERE idEstante=? AND idProductoSucursal=?");
		q3.setParameters( cant,idEstante ,idProductoSucursal);
		q3.executeUnique();

	}
	
	public int darCantidadProductoSucursalDeEstante (PersistenceManager pm, long idEstante, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosEstante()+ " WHERE idEstante= ? AND idProductoSucursal=?");
		q.setParameters(idEstante,idProductoSucursal);
		return (int) q.executeUnique();

	}
	
	
	public ProductosEstante darProductoEstante (PersistenceManager pm, long idEstante, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE idEstante=? AND idProductoSucursal=?");
		q.setResultClass(ProductosEstante.class);
		q.setParameters(idEstante,idProductoSucursal);
		return (ProductosEstante) q.executeUnique();

	}

	public List<ProductosEstante> darProductosEstante (PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosEstante()+ " WHERE idEstante= ?");
		q.setResultClass(ProductosEstante.class);
		q.setParameters(idEstante);
		return (List<ProductosEstante>) q.executeList();

	}
	
	
	
	

	public List<ProductosEstante> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosEstante.class);
		q.setParameters(idSucursal);
		return (List<ProductosEstante>) q.executeList();

	}
}
