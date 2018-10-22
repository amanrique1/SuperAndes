/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductosSucursal;

/**
 * @author Andres
 *
 */
public class SQLProductosSucursal {
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLProductos sqlProductos;

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductosSucursal (PersistenciaSuperAndes pp,SQLSucursal sqlSucursal,SQLProductos sqlProductos)
	{
		this.pp = pp;
		this.sqlSucursal = sqlSucursal;
		this.sqlProductos = sqlProductos;


	}


	public void agregarProductosSucursal (PersistenceManager pm,long pIdSuc, double pPrecio,String pCodigo, String pIdProductoSucursal) throws Exception 
	{
		if(sqlSucursal.darSucursalPorId(pm,pIdSuc )==null||!sqlProductos.existeProducto(pm, pCodigo))
		{
			throw new Exception("Datos invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosSucursal() + "(idSucursal,precio,CodigoProducto,idProductoSucursal) values (?,?,?,?)");
		q1.setParameters( pIdSuc,  pPrecio, pCodigo,  pIdProductoSucursal  );
		q1.executeUnique();
	}


	public void eliminarProductosSucursalVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosSucursal () + " WHERE cantidadVendida = 0");
		q2.executeUnique();

	}
	
	public boolean existeProductoSucursal(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT idProductoSucursal FROM " + pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal ='"+identificador+"'");
		String comp=q1.executeUnique()+"";
		if(comp.equals(identificador))
			return true;
		return false;
	}
	
	public void descontarProductoSucursalEnCantidad (PersistenceManager pm,long idProductoSucursal, int cantidadDescontar, String codigo)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosSucursal () + " SET cantidad=cantidad-? WHERE idProductoSucursal=? AND CodigoProducto=?");
		q3.setParameters( cantidadDescontar ,idProductoSucursal, codigo);
		q3.executeUnique();

	}

	public void abastecerProductoSucursalEnCantidad (PersistenceManager pm,long idProductoSucursal, int cantidadDescontar, String codigo)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosSucursal () + " SET cantidad=cantidad+? WHERE idProductoSucursal=? AND CodigoProducto=?");
		q3.setParameters( cantidadDescontar ,idProductoSucursal, codigo);
		q3.executeUnique();

	}
	
	public ProductosSucursal darProductoSucursal (PersistenceManager pm, String idProductoSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal= ?");
		q.setResultClass(ProductosSucursal.class);
		q.setParameters(idProductoSucursal);
		return (ProductosSucursal) q.executeUnique();

	}
	
	public ProductosSucursal darPromocionSucursal (PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal= ?");
		q.setResultClass(ProductosSucursal.class);
		q.setParameters(idPromocion);
		return (ProductosSucursal) q.executeUnique();

	}

	public List<ProductosSucursal> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosSucursal()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosSucursal.class);
		q.setParameters(idSucursal);
		return (List<ProductosSucursal>) q.executeList();

	}
	
	
	
	
	


}
