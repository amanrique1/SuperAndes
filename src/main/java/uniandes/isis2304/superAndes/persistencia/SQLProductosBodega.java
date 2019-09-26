package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;
import main.java.model.ProductosBodega;
import main.java.model.ProductosEstante;

public class SQLProductosBodega {
	
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLProductosSucursal sqlProductosSucursal;
	
	private SQLBodegas sqlBodega;

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductosBodega (PersistenciaSuperAndes pp,SQLProductosSucursal pSqlProductosSucursal,SQLBodegas pSqlBodega)
	{
		this.pp = pp;
		sqlProductosSucursal=pSqlProductosSucursal;
		sqlBodega=pSqlBodega;
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
	public long descontarProductoSucursalDeBodegaEnCantidad (PersistenceManager pm,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosBodega () + " SET cantidad=cantidad-? WHERE idProductoSucursal=?");
		q3.setParameters( cant ,idProductoSucursal);
		q3.executeUnique();
		q3 = pm.newQuery(SQL, "SELECT idBodega FROM " + pp.darTablaProductosBodega () + " WHERE idProductoSucursal=?");
		q3.setParameters( idProductoSucursal);
		return ((BigDecimal)q3.executeUnique()).longValue();


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

	
	public int darCantidadProductoSucursalEnBodegas (PersistenceManager pm, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosBodega()+ " WHERE idProductoSucursal=?");
		q.setParameters(idProductoSucursal);
		int cantBodega=0;
		for(BigDecimal n:(List<BigDecimal>) q.executeList())
			cantBodega+=n.intValue();
		return cantBodega;

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

	List<Productos> productos;
	
	public double[] darCantidadLleno (PersistenceManager pm, long idBodega,SQLProductos sqlProductos)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosBodega()+" WHERE idBodega=?");
		q.setResultClass(ProductosBodega.class);
		q.setParameters(idBodega);
		double volumen=0;
		double peso=0;
		productos=new ArrayList<Productos>();
		for(ProductosEstante productosEstante:(List<ProductosEstante>)q.executeList())
		{
			String codigoBarras=productosEstante.getIdProductoSucursal().substring(productosEstante.getIdProductoSucursal().indexOf("-")+1);
			int cantidad=productosEstante.getCantidad();
			double pPeso=(sqlProductos.darPesoPorIdProducto(pm, codigoBarras));
			if(sqlProductos.darUnidadPesoPorIdProducto(pm, codigoBarras).equalsIgnoreCase("g"))
				pPeso=pPeso/1000;			
			peso+=pPeso*cantidad;
			
			double pVolumen=(sqlProductos.darVolumenPorIdProducto(pm, codigoBarras));

			if(sqlProductos.darUnidadVolumenPorIdProducto(pm, codigoBarras).equalsIgnoreCase("ml"))
				pVolumen=volumen/1000;
			volumen+=pVolumen*cantidad;
			
			productos.add(new Productos(codigoBarras,pPeso,pVolumen,cantidad));
		}
		double[] cants=new double[2];
		cants[0]=peso;
		cants[1]=volumen;
		return cants;
	}
	public List<Productos> getProductos(){
		return productos;
	}
	public void setProductosNull()
	{
		productos=null;
	}
	
}
