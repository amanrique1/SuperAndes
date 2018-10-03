package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductosBodega;
import main.java.model.ProductosEstante;

public class SQLProductosBodega {
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLProductosSucursal sqlProductosSucursal;
	
	private SQLBodegas sqlBodega;

	/* ********
	 * 			Métodos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductosBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarProductosBodega (PersistenceManager pm,long idBodega,int cantidad, String idProductoSucursal) throws Exception 
	{
		if(sqlBodega.darBodegaId(pm, idBodega)==null||sqlProductosSucursal.darProductoSucursal(pm, idProductoSucursal)==null)
		{
			throw new Exception("Datos invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosBodega() + "(idBodega, cantidad, idProductoSucursal) values (?, ?, ?)");
		q1.setParameters(  idBodega,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}


	public void eliminarProductosBodegaVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosBodega () + " WHERE cantidad = 0");
		q2.executeUnique();

	}
	
	public void descontarProductoSucursalDeBodegaEnCantidad (PersistenceManager pm,long idBodega,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosBodega () + " SET cantidad=cantidad-? WHERE idBodega=? AND idProductoSucursal=?");
		q3.setParameters( cant,idBodega ,idProductoSucursal);
		q3.executeUnique();

	}

	public void abastecerProductoSucursalDeBodegaEnCantidad (PersistenceManager pm,long idBodega,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosBodega () + " SET cantidad=cantidad+? WHERE idBodega=? AND idProductoSucursal=?");
		q3.setParameters( cant,idBodega ,idProductoSucursal);
		q3.executeUnique();

	}
	
	public ProductosBodega darProductoBodega (PersistenceManager pm, long idBodega, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosBodega()+ " WHERE idBodega=? AND idProductoSucursal=?");
		q.setResultClass(ProductosBodega.class);
		q.setParameters(idBodega,idProductoSucursal);
		return (ProductosBodega) q.executeUnique();

	}
	
	public int darCantidadProductoSucursalDeBogega (PersistenceManager pm, long idBodega, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosBodega()+ " WHERE idBodega= ? AND idProductoSucursal=?");
		q.setParameters(idBodega,idProductoSucursal);
		return (int) q.executeUnique();

	}

	public List<ProductosBodega> darProductosBodega (PersistenceManager pm, long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosBodega()+ " WHERE idBodega= ?");
		q.setResultClass(ProductosBodega.class);
		q.setParameters(idBodega);
		return (List<ProductosBodega>) q.executeList();

	}
	
	

	public List<ProductosBodega> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosBodega()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosBodega.class);
		q.setParameters(idSucursal);
		return (List<ProductosBodega>) q.executeList();

	}

	
}
