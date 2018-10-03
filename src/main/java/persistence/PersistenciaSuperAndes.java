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

import main.java.model.Bodega;
import main.java.model.Categoria;
import main.java.model.Comprador;
import main.java.model.Empresa;
import main.java.model.EstadoPedido;
import main.java.model.Estante;
import main.java.model.FacturasComprador;
import main.java.model.Lote;
import main.java.model.Pedido;
import main.java.model.Persona;
import main.java.model.Productos;
import main.java.model.Sucursal;
import main.java.model.TipoProducto;

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

		sqlBodegas=new SQLBodegas(this);
		sqlCategoria=new SQLCategoria(this);
		sqlCompradores=new SQLCompradores(this);
		sqlEmpresas=new SQLEmpresas(this);
		sqlEstante=new SQLEstante(this);
		sqlFacturasComprador=new SQLFacturasComprador(this);
		sqlLote=new SQLLote(this);
		sqlPedido=new SQLPedido(this);
		sqlPersonas=new SQLPersonas(this);
		sqlProductos=new SQLProductos(this);

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


	/* ****************************************************************
	 * 			M閠odos para manejar las Bodegas
	 *****************************************************************/

	public Bodega agregarBodega(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			sqlBodegas.agregarBodega(pm, pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
			tx.commit();

			log.trace ("Inserci髇 de bodega de la sucursal con id: " + pIdSuc);

			return new Bodega(id, pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarBodegaPorId (long idBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlBodegas.eliminarBodegaPorId(pm, idBodega);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Bodega> darBodegas ()
	{
		return sqlBodegas.darBodegas(pmf.getPersistenceManager());
	}


	public List<Bodega> darBodegasSucursal (long idSucursal)
	{
		return sqlBodegas.darBodegasSucursal(pmf.getPersistenceManager(), idSucursal); 
	}

	public Bodega darBodegaPorId (long idBodega)
	{
		return sqlBodegas.darBodegaId (pmf.getPersistenceManager(), idBodega);
	}


	/* ****************************************************************
	 * 			M閠odos para manejar las Categorias
	 *****************************************************************/

	public Categoria agregarCategoria(String nombre,boolean perecedero)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlCategoria.agregarCategoria(pm, nombre, perecedero);
			tx.commit();

			log.trace ("Inserci髇 de categoria con nombre: " + nombre);

			return new Categoria(nombre, perecedero);

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarCtageoriaPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlCategoria.eliminarCategoriaPorNombre(pm, nombre);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Categoria> darCategorias ()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las empresas
	 *****************************************************************/

	public Empresa agregarEmpresa (String pNit, String pDireccion,String pNombre,String pCorreo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlEmpresas.agregarEmpresa(pm, pNit, pDireccion, pNombre, pCorreo);
			tx.commit();

			log.trace ("Inserci髇 de empresa con identificador: " + pNit);

			return new Empresa(pNit, pNombre, pCorreo, pDireccion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarEmpresaPorNombre ( String nombreEmpresa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlEmpresas.eliminarEmpresaPorNombre(pm, nombreEmpresa);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public void eliminarEmpresaPorNit ( String nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlEmpresas.eliminarEmpresaPorNit(pm, nit);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Empresa> darEmpresas()
	{
		return sqlEmpresas.darEmpresas(pmf.getPersistenceManager());
	}


	public Empresa darEmpresaId (String idEmpresa)
	{
		return sqlEmpresas.darEmpresaPorNit (pmf.getPersistenceManager(), idEmpresa);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los Estantes
	 *****************************************************************/

	public Estante agregarEstante(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			sqlEstante.agregarEstante(pm, pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
			tx.commit();

			log.trace ("Inserci髇 del estante de la sucursal con id: " + pIdSuc);

			return new Estante(id, pCapV, pCapP, pUniV, pUniP, pNivel, pIdSuc, pTipo);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarEstantePorId ( long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlEstante.eliminarEstantePorId(pm, id);
			tx.commit();
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Estante> darEstantes ()
	{
		return sqlEstante.darEstantes(pmf.getPersistenceManager());
	}


	public List<Estante> darEstantesSucursal (long idSucursal)
	{
		return sqlEstante.darEstantesSucursal(pmf.getPersistenceManager(), idSucursal); 
	}

	public Estante darEstantePorId (long idEstante)
	{
		return sqlEstante.darEstante (pmf.getPersistenceManager(), idEstante);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las FacturasComprador
	 *****************************************************************/

	public FacturasComprador agregarFacturasComprador ( Timestamp fecha, String idComprador, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			sqlFacturasComprador.agregarFacturasComprador(pm, fecha, idComprador, idSucursal);
			tx.commit();

			log.trace ("Inserci髇 de factura a comprador con id: " + idComprador);

			return new FacturasComprador(id, fecha, idComprador, idSucursal);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarFacturasCompradorPorNombre ( long numero)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlFacturasComprador.eliminarFacturasCompradorPorNumero(pm, numero);
			tx.commit();
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<FacturasComprador> darFacturasComprador ( String idComprador)
	{
		return sqlFacturasComprador.darFacturasComprador(pmf.getPersistenceManager(),idComprador);
	}


	public  List<FacturasComprador> darFacturasSucursal ( long idSucursal)
	{
		return sqlFacturasComprador.darFacturasSucursal(pmf.getPersistenceManager(), idSucursal); 
	}

	public List<FacturasComprador> darFacturasFecha ( Timestamp fecha)
	{
		return sqlFacturasComprador.darFacturasFecha(pmf.getPersistenceManager(), fecha); 
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los lotes
	 *****************************************************************/

	public Lote agregarLote ( String codigo, Timestamp fecha, int cantidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlLote.agregarLote(pm, codigo, fecha, cantidad);
			tx.commit();

			log.trace ("Inserci髇 de lote del producto: " + codigo);

			return new Lote(codigo, fecha, cantidad);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}




	public void eliminarLote ( String codigo, Timestamp fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlLote.eliminarLote(pm, codigo, fecha);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Lote> darLotes()
	{
		return sqlLote.darLotes(pmf.getPersistenceManager());
	}


	public Lote darLote (String codigo , Timestamp fecha)
	{
		return sqlLote.darLote(pmf.getPersistenceManager(), codigo, fecha) ;
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los Pedidos
	 *****************************************************************/

	public Pedido  agregarPedido (Timestamp fechaEntregaAc,Timestamp fechaEntrega,EstadoPedido estado, Long idSucursal, Long idProveedor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			sqlPedido.agregarPedido(pm, fechaEntregaAc, fechaEntrega, estado, idSucursal, idProveedor);
			tx.commit();

			log.trace ("Inserci髇 de un pedido" );

			return new Pedido(id, fechaEntregaAc, fechaEntrega, estado, idSucursal, idProveedor);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarPedidoPorId ( Long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlPedido.eliminarPedidoPorId(pm, id);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<Pedido> darPedidos ()
	{
		return sqlPedido.darPedidos(pmf.getPersistenceManager());
	}


	public List<Pedido> darPedidosSucursal ( Long idSucursal)
	{
		return sqlPedido.darPedidosSucursal(pmf.getPersistenceManager(), idSucursal); 
	}

	public List<Pedido> darPedidosProveedor ( Long idProveedor)
	{
		return sqlPedido.darPedidosProveedor(pmf.getPersistenceManager(), idProveedor);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las Personas
	 *****************************************************************/

	public Persona agregarPersona ( String pIdentificador, String pDireccion,String pNombre,String pCorreo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlPersonas.agregarPersona(pm, pIdentificador, pNombre, pCorreo);
			tx.commit();

			log.trace ("Inserci髇 de una persona" );

			return new Persona(pIdentificador, pNombre, pCorreo);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarPersonaPorNombre ( String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlPersonas.eliminarPersonaPorNombre(pm, nombre);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public void eliminarPersonaPorCedula ( String identificador)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlPersonas.eliminarPersonaPorCedula(pm, identificador);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public Persona darPersonaPorCedula ( String identificador) 
	{
		return sqlPersonas.darPersonaPorCedula(pmf.getPersistenceManager(), identificador);
	}


	public List<Persona> darPersonas ()
	{
		return sqlPersonas.darPersonas(pmf.getPersistenceManager()); 
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los Productos
	 *****************************************************************/

	public Productos agregarProducto (String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProductos.agregarProducto(pm, codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
			tx.commit();

			log.trace ("Inserci髇 de un producto" );

			return new Productos( codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarProductoPorNombre ( String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProductos.eliminarProductoPorNombre(pm, nombre);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public void eliminarProductoPorCodigo ( String id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlProductos.eliminarProductoPorCodigo(pm, id);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public Productos darProductoPorCodigo ( String identificador) 
	{
		return sqlProductos.darProductoPorCodigo(pmf.getPersistenceManager(), identificador);
	}


	public List<Productos> darProductos ()
	{
		return sqlProductos.darProductos(pmf.getPersistenceManager()); 
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las sucursales
	 *****************************************************************/

	public Sucursal agregarSucursal (String pNombre,String pCiudad,String pDireccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			sqlSucursal.agregarSucursal(pm, pNombre, pCiudad, pDireccion);
			tx.commit();

			log.trace ("Inserci髇 de un producto" );

			return new Sucursal( id, pNombre, pCiudad, pDireccion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	public void eliminarSucursalPorNombre ( String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlSucursal.eliminarSucursalPorNombre(pm, nombre);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public void eliminarSucursalPorId ( long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlSucursal.eliminarSucursalPorId(pm, id);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public Sucursal  darSucursalPorId( long identificador) 
	{
		return sqlSucursal.darSucursalPorId(pmf.getPersistenceManager(), identificador);
	}
	
	public Sucursal  darSucursalPorNombre( String nombre) 
	{
		return sqlSucursal.darSucursalPorNombre(pmf.getPersistenceManager(), nombre);
	}


	public List<Sucursal> darSucusales ()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager()); 
	}


	/* ****************************************************************
	 * 			M閠odos para manejar los TiposProductos
	 *****************************************************************/

	public TipoProducto agregarTipoProducto ( String pTipo,String pMetodoAlmacenamiento,String pCategoria) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlTipoProducto.agregarTipoProducto(pm, pTipo, pMetodoAlmacenamiento, pCategoria);
			tx.commit();

			log.trace ("Inserci髇 del tipo: " + pTipo);

			return new TipoProducto(pTipo, pMetodoAlmacenamiento, pCategoria);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}




	public void eliminarTipoProducto ( String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlTipoProducto.eliminarTipoProducto(pm, tipo);
			tx.commit();

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public List<TipoProducto> darTiposProducto ()
	{
		return sqlTipoProducto.darTiposProducto(pmf.getPersistenceManager());
	}


	public TipoProducto darTipoProducto (String tipo)
	{
		return sqlTipoProducto.darTipoProducto(pmf.getPersistenceManager(), tipo);
	}


}
