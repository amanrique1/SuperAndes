package main.java.persistence;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PersistenciaSuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private static PersistenciaSuperAndes instance;


	private PersistenceManagerFactory pmf;


	private List <String> tablas;


	private SQLUtil sqlUtil;

	private SQLEmpresas sqlEmpresas;

	private SQLClientesPersonas sqlClientesPersonas;

	private SQLFacturas sqlFacturas;

	private SQLFacturasSucursal sqlFacturasSucursal;

	private SQLProductosVendidos sqlProductosVendidos;

	private SQLProductosEnVenta sqlProductosEnVenta;

	private SQLCategoriaTipo sqlCategoriaTipo;

	private SQLCategoria sqlCategoria;

	private SQLLote sqlLote;

	private SQLTipoProducto sqlTipoProducto;

	private SQLProductosEnVentaSucursal sqlProductosEnVentaSucursal;

	private SQLEstanteProductos sqlEstanteProductos;

	private SQLProductosPromocion sqlProductosPromocion;

	private SQLSucursal sqlSucursal;

	private SQLEstante SQLEstante;

	private SQLEstanteTiposProductos sqlEstanteTiposProductos; 

	private SQLPromocion sqlPromocion;

	private SQLBodegas sqlBodegas;

	private SQLEstantePromociones SQLEstantePromociones;

	private SQLBodegaPromocion SQLBodegaPromocion;

	private SQLBodegaTipoProducto sqlBodegaTipoProducto;

	private SQLPedido sqlPedido;

	private SQLBodegaProductos sqlBodegaProductos;

	private SQLProveedor sqlProveedor;

	private SQLProductosPreveedor sqlProductosPreveedor;



	/* ****************************************************************
	 * 			Metodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SecuenciaSuperAndes");
		tablas.add ("Empresas");
		tablas.add ("ClientesPersona");
		tablas.add ("Facturas");
		tablas.add ("FacturasSucursal");
		tablas.add ("ProductosVendidos");
		tablas.add ("ProductosEnVenta");
		tablas.add ("CategoriaTipo");
		tablas.add ("Categoria");
		tablas.add ("Lote");
		tablas.add ("TipoProducto");
		tablas.add ("ProductosEnVentaSucursal");
		tablas.add ("EstanteProductos");
		tablas.add ("ProductosPromocion");
		tablas.add ("Sucursal");
		tablas.add ("Estante");
		tablas.add ("EstanteTiposProductos");
		tablas.add ("Promocion");
		tablas.add ("Bodegas");
		tablas.add ("EstantePromociones");
		tablas.add ("BodegaPromocion");
		tablas.add ("BodegaTipoProducto");
		tablas.add ("ProductosEnVentaSucursal");
		tablas.add ("Pedido");
		tablas.add ("Proveedor");
		tablas.add ("ProductosProveedor");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		
	}
}
