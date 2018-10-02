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
	 * Logger para escribir la traza de la ejecuci贸n
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

	private SQLPersonas sqlPersonas;

	private SQLCompradores sqlCompradores;

	private SQLProductos sqlProductos;

	private SQLTipoProducto sqlTipoProducto;

	private SQLCategoria sqlCategoria;
	
	private SQLSucursal sqlSucursal;

	private SQLProductosSucursal sqlProductosSucursal;
	
	private SQLLote sqlLote;
	
	private SQLFacturasComprador sqlFacturasComprador;

	private SQLEstante sqlEstante;
	
	private SQLProductosEstantes sqlProductosEstantes;
	
	private SQLProductosVendidos sqlProductosVendidos;

	private SQLBodegas sqlBodegas;
	
	private SQLPromocion sqlPromocion;

	private SQLProductosBodega sqlProductosBodega;
	
	private SQLPromocionesVendidas sqlPromocionesVendidas;
	
	private SQLProveedor sqlProveedor;
	
	private SQLPedido sqlPedido;

	private SQLProductosProveedor sqlProductosProveedor;



	/* ****************************************************************
	 * 			Metodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patr贸n SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SecuenciaSuperAndes");
		tablas.add ("Empresas");
		tablas.add ("Personas");
		tablas.add ("Compradores");
		tablas.add ("Productos");
		tablas.add ("TipoProducto");
		tablas.add ("Categorias");
		tablas.add ("Sucursal");
		tablas.add ("ProductosSucursal");
		tablas.add ("Lotes");
		tablas.add ("FacturasCompradores");
		tablas.add ("Estantes");
		tablas.add ("ProductosEstante");
		tablas.add ("ProductosVendidos");
		tablas.add ("Bodegas");
		tablas.add ("Promociones");
		tablas.add ("productosBodega");
		tablas.add ("PromocionesVendidas");
		tablas.add ("Proveedor");
		tablas.add ("Pedido");
		tablas.add ("ProductosProveedor");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr贸n SINGLETON
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
	 * @return Retorna el 煤nico objeto PersistenciaParranderos existente - Patr贸n SINGLETON
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
	 * @return Retorna el 煤nico objeto PersistenciaParranderos existente - Patr贸n SINGLETON
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
	 * Cierra la conexi贸n con la base de datos
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

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqUniandes ()
	{
		return tablas.get (0);
	}

	public String darTablaEmpresas()
	{
		return tablas.get (1);
	}

	public String darTablaPersonas()
	{
		return tablas.get (2);
	}

	public String darTablaCompradores()
	{
		return tablas.get (3);
	}
	
	public String darTablaProductos()
	{
		return tablas.get (4);
	}
	
	public String darTablaTipoProducto()
	{
		return tablas.get (5);
	}
	
	public String darTablaCategorias()
	{
		return tablas.get (6);
	}
	public String darTablaSucursal()
	{
		return tablas.get (7);
	}
	
	public String darTablaProductosSucursal()
	{
		return tablas.get (8);
	}
	public String darTablaLotes()
	{
		return tablas.get (9);
	}
	public String darTablaFacturasCompradores()
	{
		return tablas.get (10);
	}
	public String darTablaEstantes()
	{
		return tablas.get (11);
	}
	public String darTablaProductosEstante()
	{
		return tablas.get (12);
	}
	public String darTablaProductosVendidos()
	{
		return tablas.get (13);
	}
	public String darTablaBodegas()
	{
		return tablas.get (14);
	}
	public String darTablaPromociones()
	{
		return tablas.get (15);
	}
	public String darTablaProductosBodega()
	{
		return tablas.get (16);
	}
	public String darTablaPromocionesVendidas()
	{
		return tablas.get (17);
	}
	public String darTablaProveedor()
	{
		return tablas.get (18);
	}
	public String darTablaPedido()
	{
		return tablas.get (19);
	}
	public String darTablaProductosProveedor()
	{
		return tablas.get (20);
	}




	/**
	 * Transacci髇 para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicaci髇
	 * @return El siguiente n鷐ero del secuenciador de Parranderos
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec韋ico del problema encontrado
	 * @param e - La excepci髇 que ocurrio
	 * @return El mensaje de la excepci髇 JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
}
