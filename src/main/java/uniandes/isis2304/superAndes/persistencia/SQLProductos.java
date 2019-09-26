/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductoFecha;
import main.java.model.Productos;
import main.java.model.ProductosMostrar;


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
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	private SQLTipoProducto sqlTipoProducto;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductos (PersistenciaSuperAndes pp,SQLTipoProducto pSqlTipoProducto)
	{
		this.pp = pp;
		sqlTipoProducto=pSqlTipoProducto;
	}


	public void agregarProducto (PersistenceManager pm, String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) 
	{
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

	public boolean existeProducto(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT codigoBarras FROM " + pp.darTablaProductos()+ " WHERE codigoBarras ='"+identificador+"'");
		String comp=q1.executeUnique()+"";
		if(comp.equals(identificador))
			return true;
		return false;
	}


	public List<Productos> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos());
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();

	}
	public List<Productos> darProductosFechaVencPost(PersistenceManager pm, String fecha)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM (SELECT * FROM "+ pp.darTablaLotes()+" WHERE FechaVnecimiento> "+ fecha+") INNER JOIN "+pp.darTablaProductos()+" ON codigoProducto=codigoBarras");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}

	public List<Productos> darProductosRangoPeso (PersistenceManager pm,double valMin,double valMax)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos()+" WHERE cantidadPeso>="+valMin+" cantidadPeso<="+valMax);
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();

	}

	public List<Productos> darProductosRangoVol (PersistenceManager pm,double valMin,double valMax)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos()+" WHERE cantidadVolumen>="+valMin+" cantidadVolumen<="+valMax);
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();

	}

	public List<Productos> darProductosMarca (PersistenceManager pm,String marca)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos()+" WHERE marca="+marca);
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();	
	}

	public List<Productos> darProductosCiudad(PersistenceManager pm, String ciudad)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM (SELECT * FROM "+ pp.darTablaSucursal()+" a INNER JOIN" +pp.darTablaProductosSucursal()+" b ON a.idsucursal=b.idsucursal WHERE ciudad="+ciudad+") INNER JOIN "+pp.darTablaProductos()+" ON codigoProducto=codigoBarras");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}

	public List<Productos> darProductosSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM (SELECT * FROM "+ pp.darTablaProductosSucursal()+" WHERE idSucursal="+idSucursal+") INNER JOIN "+pp.darTablaProductos()+" ON codigoProducto=codigoBarras");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}

	public List<Productos> darProductosCategoria(PersistenceManager pm, String categoria)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM (SELECT * FROM "+ pp.darTablaTipoProducto()+" WHERE nombreCategoria="+categoria+") t INNER JOIN "+pp.darTablaProductos()+" p ON t.nombretipo=p.tipoproducto");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}

	public List<Productos> darProductosTipo(PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras, nombre,marca,presentacion,unidadPeso,cantidadPeso, unidadVolumen,tipoproducto FROM "+pp.darTablaTipoProducto()+" INNER JOIN "+pp.darTablaProductos()+" ON nombretipo=tipoproducto");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}
	
	public void actualizarListaProductosMostrarProducto (PersistenceManager pm, List<ProductosMostrar> productos)
	{
		for(ProductosMostrar producto:productos)
		{
			String codigoBarras=producto.getIdProductoSucursal().substring(producto.getIdProductoSucursal().indexOf("-")+1);
			Query q = pm.newQuery(SQL, "SELECT nombre FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
			producto.setNombre(q.executeUnique()+"");
			
			q = pm.newQuery(SQL, "SELECT presentacion FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
			producto.setPresentacion(q.executeUnique()+"");
		}

	}
	
	public double darPesoPorIdProducto (PersistenceManager pm, String codigoBarras)
	{

		Query q = pm.newQuery(SQL, "SELECT cantidadPeso FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
		return Double.parseDouble(q.executeUnique()+"");

	}
	
	public double darVolumenPorIdProducto (PersistenceManager pm, String codigoBarras)
	{

		Query q = pm.newQuery(SQL, "SELECT cantidadVolumen FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
		return Double.parseDouble(q.executeUnique()+"");

	}
	
	public String darUnidadPesoPorIdProducto (PersistenceManager pm, String codigoBarras)
	{

		Query q = pm.newQuery(SQL, "SELECT unidadPeso FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
		return (q.executeUnique()+"");

	}
	
	public String darUnidadVolumenPorIdProducto (PersistenceManager pm, String codigoBarras)
	{

		Query q = pm.newQuery(SQL, "SELECT unidadVolumen FROM "+ pp.darTablaProductos()+ " WHERE codigoBarras='"+codigoBarras+"'");		
		return (q.executeUnique()+"");

	}
	

	public List<Productos> darProductosPocaDemanda(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "select codigoproducto,fechaentrega from"+pp.darTablaProveedor()+" p inner join "+pp.darTablaPedido()+" pe on p.idpedido=pe.idpedido " + 
				"group by codigoproducto,fechaentrega " + 
				"order by codigoproducto,fechaentrega");
		q.setResultClass(ProductoFecha.class);
		List<ProductoFecha>prod= (List<ProductoFecha>) q.executeList();
		List<Productos>productos=new LinkedList();
		ProductoFecha ant=prod.get(0);
		for(ProductoFecha act:prod)
		{
			int dias=(int) ((act.getFecha().getTime()-ant.getFecha().getTime())/86400000);
			if(ant.getCodigoProducto().equals(act.getCodigoProducto())&&dias>=60)
			{
				Query q2 = pm.newQuery(SQL, "select * from "+pp.darTablaProductos()+" where codigobarras="+act.getCodigoProducto());
				q.setResultClass(Productos.class);
				Productos producto= (Productos) q.executeUnique();
				productos.add(producto);
			}
		}
		return productos;
	}
	/**
	 * sql para encontrar los clientes frecuentes
	 * SELECT a1.idcomprador
FROM   (SELECT idcomprador
        FROM  (SELECT Count(idcomprador) AS vecesmes,
                      idcomprador
               FROM  (SELECT idcomprador
                      FROM   facturascomprador
                      WHERE  fecha < To_date('1/02/19', 'DD/MM/YY') and fecha > To_date('1/03/19', 'DD/MM/YY')
                      GROUP  BY idcomprador,
                                fecha)
               GROUP  BY idcomprador)
        WHERE  vecesmes > 1)a1
       inner join (SELECT idcomprador
                   FROM  (SELECT Count(idcomprador) AS vecesmes,
                                 idcomprador
                          FROM  (SELECT idcomprador
                                 FROM   facturascomprador
                                 WHERE  fecha < To_date('1/03/19', 'DD/MM/YY') and fecha > To_date('1/04/19', 'DD/MM/YY')
                                 GROUP  BY idcomprador,
                                           fecha)
                          GROUP  BY idcomprador)
                   WHERE  vecesmes >1) a2
               ON a1.idcomprador = a2.idcomprador  
	 */

}
