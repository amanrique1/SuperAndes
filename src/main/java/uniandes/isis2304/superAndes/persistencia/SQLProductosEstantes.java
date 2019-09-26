/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;
import main.java.model.ProductosEstante;
import main.java.model.ProductosMostrar;
import main.java.model.ProductosSucursal;
import main.java.model.ProductosVendidos;
import main.java.model.Sucursal;

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
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLEstante sqlEstante;
	
	private SQLProductosSucursal sqlProductosSucursal;
	
	private SQLProductosBodega sqlProductosBodega;

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductosEstantes (PersistenciaSuperAndes pp, SQLEstante pSqlEstante, SQLProductosSucursal pSqlProductosSucursal,SQLProductosBodega pSqlProductosBodega)
	{
		this.pp = pp;
		sqlEstante=pSqlEstante;
		sqlProductosSucursal=pSqlProductosSucursal;
		sqlProductosBodega=pSqlProductosBodega;
	}


	public void agregarProductosEstante (PersistenceManager pm,long idEstante,int cantidad, String idProductoSucursal) 
	{
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosEstante() + "(idEstante, cantidad, idProductoSucursal) values (?, ?, ?)");
		q1.setParameters(  idEstante,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}

	public long darIdEstantePorCodigoProductoSucursal (PersistenceManager pm, String codigoProductoSucursal) 
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT idEstante FROM " + pp.darTablaProductosEstante() + " WHERE idProductoSucursal = ?");
		q.setResultClass(Long.class);
		q.setParameters(codigoProductoSucursal);
		List<Long>estantes= (List<Long>)q.executeList();
		return estantes.get(0);
		
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
	
	public void verificarReorden(PersistenceManager pm,String idProductoSucursal,long idEstante)
	{
		int cantidad=darCantidadProductoSucursalDeEstante(pm, idProductoSucursal,idEstante);
		Query q = pm.newQuery(SQL, "SELECT nivelreorden FROM "+ pp.darTablaEstantes()+ " WHERE idEstante=?");
		q.setResultClass(double.class);
		q.setParameters(idEstante);
		double reorden=(double) q.executeUnique();
/*
		if(cantidad<reorden)
		{
			
			int cantAdicionar=Math.min(arg0, arg1)
		}
		*/
	}

	public void abastecerProductoSucursalDeEstanteEnCantidad (PersistenceManager pm,long idEstante,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante () + " SET cantidad=cantidad+? WHERE idProductoSucursal=? AND idEstante=?");
		q3.setParameters( cant ,idProductoSucursal,idEstante);
		q3.executeUnique();

	}
	
	public int darCantidadProductoSucursalDeEstante (PersistenceManager pm, String idProductoSucursal,long idEstante )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosEstante()+ " WHERE idProductoSucursal=? AND idEstante=?");
		
		q.setParameters(idProductoSucursal,idEstante);
		return (int) q.executeUnique();

	}
	
	public List<Long> darIdEstantePorProductoSucursal (PersistenceManager pm, String idProductoSucursal) 
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT idEstante FROM " + pp.darTablaProductosEstante() + " WHERE idProductoSucursal = ?");
		q.setResultClass(Long.class);
		q.setParameters(idProductoSucursal);
		return (List<Long>)q.executeList();
		
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
	
	
	public List<ProductosMostrar> darProductosMostrarDeEstante (PersistenceManager pm, long idEstante)
	{
		List<ProductosMostrar> productos=new ArrayList<ProductosMostrar>();
		Query q = pm.newQuery(SQL, "SELECT idProductoSUcursal FROM "+ pp.darTablaProductosEstante()+ " WHERE idEstante= ?");
		q.setParameters(idEstante+"");
		List<String> ids=(List<String>) q.executeList();
		q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosEstante()+ " WHERE idEstante= ?");
		q.setParameters(idEstante+"");
		
		List<BigDecimal> cants= (List<BigDecimal>) q.executeList();
		List<Integer> cantidades= new ArrayList<Integer>();

		for(BigDecimal cant:cants)
			cantidades.add(cant.intValue());
		
		
		for (int i=0;i<ids.size();i++)
			productos.add(new ProductosMostrar(ids.get(i), cantidades.get(i),idEstante));
		return productos;

	}
	
	public void actualizarListaProductosMostrarProductosEstante (PersistenceManager pm, List<ProductosMostrar> productos)
	{
		for(ProductosMostrar producto:productos)
		{
			Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaProductosEstante()+ " WHERE idProductoSucursal='"+producto.getIdProductoSucursal()+"'");		
			producto.setCantidad(Integer.parseInt(""+q.executeUnique()));
		}

	}	
	

	public List<ProductosEstante> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosEstante.class);
		q.setParameters(idSucursal);
		return (List<ProductosEstante>) q.executeList();

	}

	List<Productos> productos;
	
	public double[] darCantidadLleno (PersistenceManager pm, long idEstante,SQLProductos sqlProductos)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosEstante()+" WHERE idEstante=?");
		q.setResultClass(ProductosEstante.class);
		q.setParameters(idEstante);
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
	private double darValorUnidad(String unidad)
	{
		if(unidad.equalsIgnoreCase("mm3"))
				return Math.pow(1, -21);
		else if(unidad.equalsIgnoreCase("cm3")|unidad.equalsIgnoreCase("ml"))
		{
			return Math.pow(1, -18);
		}
		else if(unidad.equalsIgnoreCase("dm3")|unidad.equalsIgnoreCase("l"))
		{
			return Math.pow(1, -15);
		}
		else if(unidad.equalsIgnoreCase("m3"))
		{
			return Math.pow(1, -12);
		}
		else if(unidad.equalsIgnoreCase("m3"))
		{
			return Math.pow(1, -9);
		}
		else if(unidad.equalsIgnoreCase("dam3"))
		{
			return Math.pow(1, -6);
		}
		else if(unidad.equalsIgnoreCase("hm3"))
		{
			return Math.pow(1, -3);
		}
		else 
		{
			return 1;
		}
		
	}
}
