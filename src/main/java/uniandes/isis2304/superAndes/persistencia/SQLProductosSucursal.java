/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;
import main.java.model.ProductosMostrar;
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
		q1.setResultClass(String.class);
		String comp=q1.executeUnique()+"";
		if(comp.equals(identificador))
			return true;
		return false;
	}
	
	public void descontarProductoSucursalEnCantidad (PersistenceManager pm,String idProductoSucursal, int cantidadDescontar)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosSucursal () + " SET cantidadProducto=cantidadProducto-? WHERE idProductoSucursal=?");
		q3.setParameters( cantidadDescontar ,idProductoSucursal);
		q3.executeUnique();
		

	}
	

	public void abastecerProductoSucursalEnCantidad (PersistenceManager pm,String idProductoSucursal, int cantidadDescontar)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosSucursal () + " SET cantidadProducto=cantidadProducto+? WHERE idProductoSucursal=?");
		q3.setParameters( cantidadDescontar ,idProductoSucursal);
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
	
	public List<Productos> darProductosRangoPrecio (PersistenceManager pm, double valMin,double valMax)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM (SELECT * FROM "+ pp.darTablaProductosSucursal()+" WHERE precio>= "+valMin+" AND precio <= "+valMax+") INNER JOIN "+ pp.darTablaProductos()+" ON codigoproducto=codigobarras");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
		
	}
	
	public void actualizarListaProductosMostrarProductoSucursal (PersistenceManager pm, List<ProductosMostrar> productos)
	{
		for(ProductosMostrar producto:productos)
		{
			Query q = pm.newQuery(SQL, "SELECT precio FROM "+ pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal='"+producto.getIdProductoSucursal()+"'");		
			producto.setPrecio(Double.parseDouble(""+q.executeUnique()));
			

			q = pm.newQuery(SQL, "SELECT idPromocion FROM "+ pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal='"+producto.getIdProductoSucursal()+"'");		
			try
			{
				BigInteger id=(BigInteger)q.executeUnique();
				producto.setIdPromocion(id.longValue());
			}
			catch(Exception e) {
				//no tiene promoción
			}
		}

	}	
	public List<String> darIdsProductosSucursalDeEstante (PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT idProductoSUcursal FROM "+ pp.darTablaProductosEstante()+ " WHERE idEstante= ?");
		q.setParameters(idEstante+"");
		return (List<String>) q.executeList();

	}
	
	public int darCantidadProductoPorId(PersistenceManager pm, String idProductoSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT cantidadProducto FROM "+ pp.darTablaProductosSucursal()+ " WHERE idProductoSucursal= ?");
		q.setParameters(idProductoSucursal);
		return ( (BigDecimal) q.executeUnique()).intValue();

	}


}
