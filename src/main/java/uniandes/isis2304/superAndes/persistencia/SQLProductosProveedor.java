package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductosProveedor;


public class SQLProductosProveedor {
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLPedido sqlPedido;
	
	private SQLProveedor sqlProveedor;
	
	private SQLProductos sqlProductos;
	

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductosProveedor (PersistenciaSuperAndes pp,SQLPedido pSqlPedido, SQLProductos pSqlProductos,SQLProveedor pSqlProveedor)
	{
		this.pp = pp;
		sqlPedido=pSqlPedido;
		sqlProductos=pSqlProductos;
		sqlProveedor=pSqlProveedor;
	}


	public void agregarProductosProveedor (PersistenceManager pm,String pCodigo,long pIdP,double pPrecioU,int pCanti,String pIdProv,Timestamp pFechaV,double pCali) throws Exception
	{
		
		if(sqlProductos.darProductoPorCodigo(pm, pCodigo)==null||sqlPedido.darPedidoPorId(pm, pIdP)==null||sqlProveedor.darProveedorPorNit(pm, pIdProv)==null)
		{
			throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosProveedor() + "(CodigoProducto, idPedido, precioProveedor, idProveedor, fechaFencimiento, calificacionCalidad,cantidadUnidades) values (?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters(   pCodigo, pIdP, pPrecioU, pCanti, pIdProv, pFechaV, pCali);
		q1.executeUnique();
	}


	public void eliminarProductosProveedorVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosProveedor () + " WHERE cantidadUnidades = 0");
		q2.executeUnique();

	}
	
	
	public ProductosProveedor darProductoProveedor (PersistenceManager pm, String codigo, long idProveedor )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE CodigoProducto=? AND idProveedor=?");
		q.setResultClass(ProductosProveedor.class);
		q.setParameters(codigo,idProveedor);
		return (ProductosProveedor) q.executeUnique();

	}
	
	
	public List<ProductosProveedor> darProductosProveedor (PersistenceManager pm, long idProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosProveedor()+ " WHERE idProveedor= ?");
		q.setResultClass(ProductosProveedor.class);
		q.setParameters(idProveedor);
		return (List<ProductosProveedor>) q.executeList();

	}
	
	

	public List<ProductosProveedor> darProductosProveedorPedido (PersistenceManager pm, long idPedido)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosProveedor.class);
		q.setParameters(idPedido);
		return (List<ProductosProveedor>) q.executeList();

	}


}
