/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;


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
	public SQLProductos (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarProducto (PersistenceManager pm, String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) throws Exception 
	{
		if (sqlTipoProducto.darTipoProducto(pm,tipoProducto)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
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

	
	public List<Productos> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductos());
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
		
	}
	public List<Productos> darProductosFechaVencPost(PersistenceManager pm, Timestamp fecha)
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
	
	}
