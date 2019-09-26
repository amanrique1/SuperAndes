/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.CarritoProductos;
import main.java.model.ProductosVendidos;

/**
 * @author Andres
 *
 */
public class SQLCarritoProductos {

	
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLCarritoComprador sqlCarrito;
	
	private SQLProductosSucursal sqlProductosSucursal;

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCarritoProductos (PersistenciaSuperAndes pp, SQLProductosSucursal pSqlProductosSucursal, SQLCarritoComprador pSqlCarritoComprador)
	{
		this.pp = pp;
		sqlProductosSucursal=pSqlProductosSucursal;
		sqlCarrito=pSqlCarritoComprador;
	}


	public void agregarProductosAlCarrito (PersistenceManager pm,long idCarrito,int cantidad, String idProductoSucursal) throws Exception 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoProductos() + " (idCarrito, cantidadProducto, idProductoSucursal) VALUES ("+idCarrito+", "+cantidad+", '"+idProductoSucursal+"')");
//		q1.setParameters(  idCarrito,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}


	public void devolverProductoCarrito (PersistenceManager pm, long idCarrito,String codigoProducto)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoProductos () + " WHERE idProductoSucursal ='"+ codigoProducto+"' AND idCarrito="+idCarrito);
		q2.executeUnique();

	}
	
	public void devolverParteProductoCarrito (PersistenceManager pm, long idCarrito,String codigoProducto,int cantidad)
	{

		Query q2 = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarritoProductos () + " SET cantidadProducto=cantidadProducto-"+cantidad+" WHERE idProductoSucursal ='"+ codigoProducto+"' AND idCarrito="+idCarrito);
		q2.executeUnique();

	}
	
	public void vaciarProductosCarrito (PersistenceManager pm, long idCarrito)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoProductos () + " WHERE idCarrito="+idCarrito);
		q2.executeUnique();

	}
	
	public List<CarritoProductos> darProductosCarritoAvandonados(PersistenceManager pm)
	{
		Query q2 = pm.newQuery(SQL, "SELECT idProductoSucursal, cantidadProducto, prod.idCarrito FROM carritoproductos prod INNER JOIN carritopersonas p ON prod.idCarrito=p.idCarrito where idcomprador is  null");
		q2.setResultClass(CarritoProductos.class);
		 return (List<CarritoProductos>) q2.executeList();
	}
	

	public List<CarritoProductos> darProductosCarrito (PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "SELECT idProductoSucursal,cantidadProducto FROM "+ pp.darTablaCarritoProductos()+ " WHERE idCarrito= ?");
		q.setResultClass(CarritoProductos.class);
		q.setParameters(idCarrito);
		return (List<CarritoProductos>) q.executeList();

	}
	
	public int darCantidadProductosCarrito (PersistenceManager pm, long idCarrito,String idProductoSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT cantidadProducto FROM "+ pp.darTablaCarritoProductos()+ " WHERE idCarrito= ? AND idProductoSucursal=?");
		q.setResultClass(Integer.class);
		q.setParameters(idCarrito,idProductoSucursal);
		return (int) q.executeUnique();

	}
	
}
