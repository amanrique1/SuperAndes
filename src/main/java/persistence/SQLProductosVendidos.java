/**
 * 
 */
package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductosVendidos;

/**
 * @author Andres
 *
 */
public class SQLProductosVendidos {

	
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLFacturasComprador sqlFacturas;
	
	private SQLProductosSucursal sqlProductosSucursal;

	/* ********
	 * 			Métodos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductosVendidos (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarProductosVendidos (PersistenceManager pm,long noFactura,int cantidad, String idProductoSucursal) throws Exception 
	{
		if(sqlFacturas.darFacturaCompradorPorNumero(pm, noFactura)==null||sqlProductosSucursal.darProductoSucursal(pm, idProductoSucursal)==null)
		{
			throw new Exception("Datos Invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosVendidos() + "(noFactura, cantidad, idProductoSucursal) values (?, ?, ?)");
		q1.setParameters(  noFactura,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}


	public void eliminarProductosVendidosVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosVendidos () + " WHERE cantidad = 0");
		q2.executeUnique();

	}
	
	public void disminuirProductosVendidosDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosVendidos () + " SET cantidad=cantidad-? WHERE noFactura=? AND idProductoSucursal=?");
		q3.setParameters( cant,noFactura ,idProductoSucursal);
		q3.executeUnique();

	}

	public void aumentarProductosVendidosDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosVendidos () + " SET cantidad=cantidad+? WHERE noFactura=? AND idProductoSucursal=?");
		q3.setParameters( cant,noFactura ,idProductoSucursal);
		q3.executeUnique();

	}
	
	public int darCantidadProductosVendidosDeFactura (PersistenceManager pm, long noFactura, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaFacturasCompradores()+ " WHERE noFactura=? AND idProductoSucursal=?");
		q.setParameters(noFactura,idProductoSucursal);
		return (int) q.executeUnique();

	}
	
	public List<ProductosVendidos> darProductosVendidosDeFactura (PersistenceManager pm, long noFactura )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE noFactura=? ");
		q.setParameters(noFactura);
		return (List<ProductosVendidos>) q.executeUnique();

	}
	
	public ProductosVendidos darProductoVendido (PersistenceManager pm, long noFactura, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE noFactura=? AND idProductoSucursal=?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(noFactura,idProductoSucursal);
		return (ProductosVendidos) q.executeUnique();

	}

	public List<ProductosVendidos> darProductosVendidos (PersistenceManager pm, long noFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE noFactura= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(noFactura);
		return (List<ProductosVendidos>) q.executeList();

	}
	
	

	public List<ProductosVendidos> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(idSucursal);
		return (List<ProductosVendidos>) q.executeList();

	}
	public List<ProductosVendidos> darProductosSucursalNumero (PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE numero= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(numero);
		return (List<ProductosVendidos>) q.executeList();

	}
}
