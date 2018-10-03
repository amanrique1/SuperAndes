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
import main.java.model.ProductosBodega;
import main.java.model.ProductosEstante;
import main.java.model.ProductosProveedor;
import main.java.model.ProductosSucursal;
import main.java.model.ProductosVendidos;
import main.java.model.Promocion;
import main.java.model.PromocionesVendidas;
import main.java.model.Proveedor;
import main.java.model.Sucursal;
import main.java.model.TipoProducto;
import main.java.model.TipoPromocion;

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

	public Bodega agregarBodega(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		long id = nextval ();
		sqlBodegas.agregarBodega(pm, id,pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
		tx.commit();

		log.trace ("Inserci髇 de bodega de la sucursal con id: " + pIdSuc);
		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Bodega(id, pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);



	}



	public void eliminarBodegaPorId (long idBodega) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlBodegas.eliminarBodegaPorId(pm, idBodega);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

		tx.begin();
		sqlCategoria.agregarCategoria(pm, nombre, perecedero);
		tx.commit();

		log.trace ("Inserci髇 de categoria con nombre: " + nombre);

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Categoria(nombre, perecedero);

	}



	public void eliminarCtageoriaPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlCategoria.eliminarCategoriaPorNombre(pm, nombre);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

		tx.begin();
		sqlEmpresas.agregarEmpresa(pm, pNit, pDireccion, pNombre, pCorreo);
		tx.commit();

		log.trace ("Inserci髇 de empresa con identificador: " + pNit);

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Empresa(pNit, pNombre, pCorreo, pDireccion);


	}



	public void eliminarEmpresaPorNombre ( String nombreEmpresa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlEmpresas.eliminarEmpresaPorNombre(pm, nombreEmpresa);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void eliminarEmpresaPorNit ( String nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		tx.begin();
		sqlEmpresas.eliminarEmpresaPorNit(pm, nit);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

	public Estante agregarEstante(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		long id = nextval ();
		sqlEstante.agregarEstante(pm, id,pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
		tx.commit();

		log.trace ("Inserci髇 del estante de la sucursal con id: " + pIdSuc);

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

		return new Estante(id, pCapV, pCapP, pUniV, pUniP, pNivel, pIdSuc, pTipo);

	}



	public void eliminarEstantePorId ( long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlEstante.eliminarEstantePorId(pm, id);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

	public FacturasComprador agregarFacturasComprador ( Timestamp fecha, String idComprador, long idSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		long id = nextval ();
		sqlFacturasComprador.agregarFacturasComprador(pm, id,fecha, idComprador, idSucursal);
		tx.commit();

		log.trace ("Inserci髇 de factura a comprador con id: " + idComprador);
		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new FacturasComprador(id, fecha, idComprador, idSucursal);



	}



	public void eliminarFacturasCompradorPorNombre ( long numero)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlFacturasComprador.eliminarFacturasCompradorPorNumero(pm, numero);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

	public Lote agregarLote ( String codigo, Timestamp fecha, int cantidad) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlLote.agregarLote(pm, codigo, fecha, cantidad);
		tx.commit();

		log.trace ("Inserci髇 de lote del producto: " + codigo);
		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Lote(codigo, fecha, cantidad);

	}




	public void eliminarLote ( String codigo, Timestamp fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlLote.eliminarLote(pm, codigo, fecha);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

	public Pedido  agregarPedido (Timestamp fechaEntregaAc,Timestamp fechaEntrega,EstadoPedido estado, Long idSucursal, String idProveedor) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		long id = nextval ();
		sqlPedido.agregarPedido(pm, id,fechaEntregaAc, fechaEntrega, estado, idSucursal, idProveedor);
		tx.commit();

		log.trace ("Inserci髇 de un pedido" );

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Pedido(id, fechaEntregaAc, fechaEntrega, estado, idSucursal, idProveedor);



	}



	public void eliminarPedidoPorId ( Long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlPedido.eliminarPedidoPorId(pm, id);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

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

		tx.begin();
		sqlPersonas.agregarPersona(pm, pIdentificador, pNombre, pCorreo);
		tx.commit();

		log.trace ("Inserci髇 de una persona" );
		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

		return new Persona(pIdentificador, pNombre, pCorreo);



	}



	public void eliminarPersonaPorNombre ( String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
			tx.begin();
			sqlPersonas.eliminarPersonaPorNombre(pm, nombre);
			tx.commit();

		
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		
	}

	public void eliminarPersonaPorCedula ( String identificador)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
			tx.begin();
			sqlPersonas.eliminarPersonaPorCedula(pm, identificador);
			tx.commit();

		
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		
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

	public Productos agregarProducto (String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		
			tx.begin();
			sqlProductos.agregarProducto(pm, codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
			tx.commit();

			log.trace ("Inserci髇 de un producto" );
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		
			return new Productos( codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
		
			
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
			sqlSucursal.agregarSucursal(pm, id,pNombre, pCiudad, pDireccion);
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

	/* ****************************************************************
	 * 			M閠odos para manejar los ProductosBodega
	 *****************************************************************/

	public ProductosBodega agregarProductosBodega (long idBodega,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosBodega.agregarProductosBodega(pm, idBodega, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci髇 de un producto a una bodega ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new ProductosBodega(idBodega, cantidad, idProductoSucursal);


	}



	public void eliminarProductosBodegaVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosBodega.eliminarProductosBodegaVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}
	public void descontarProductoSucursalDeBodegaEnCantidad (long idBodega,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosBodega.descontarProductoSucursalDeBodegaEnCantidad(pm, idBodega, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void abastecerProductoSucursalDeBodegaEnCantidad (long idBodega,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosBodega.abastecerProductoSucursalDeBodegaEnCantidad(pm, idBodega, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}



	public List<ProductosBodega> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		return sqlProductosBodega.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}


	public List<ProductosBodega> darProductosBodega (PersistenceManager pm, long idBodega)
	{
		return sqlProductosBodega.darProductosBodega(pmf.getPersistenceManager(), idBodega);
	}

	public ProductosBodega darProductoBodega (PersistenceManager pm, long idBodega, String idProductoSucursal )
	{
		return sqlProductosBodega.darProductoBodega(pmf.getPersistenceManager(), idBodega, idProductoSucursal);
	}

	public int darCantidadProductoBodega (PersistenceManager pm, long idBodega, String idProductoSucursal )
	{
		return sqlProductosBodega.darCantidadProductoSucursalDeBogega(pmf.getPersistenceManager(), idBodega, idProductoSucursal);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los ProductosEstante
	 *****************************************************************/

	public ProductosBodega agregarProductosEstante (long idBodega,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosEstantes.agregarProductosEstante(pm, idBodega, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci髇 de un producto a un Estante ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new ProductosBodega(idBodega, cantidad, idProductoSucursal);


	}



	public void eliminarProductosEstanteVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosEstantes.eliminarProductosEstanteVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}
	public void descontarProductoSucursalDeEstanteEnCantidad (long idEstante,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosEstantes.descontarProductoSucursalDeEstanteEnCantidad(pm, idEstante, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void abastecerProductoSucursalDeEstanteEnCantidad (long idEstante,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosEstantes.abastecerProductoSucursalDeEstanteEnCantidad(pm, idEstante, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}



	public List<ProductosEstante> darProductosEstanteSucursal (PersistenceManager pm, long idSucursal)
	{
		return sqlProductosEstantes.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}


	public List<ProductosEstante> darProductosEstante (PersistenceManager pm, long idBodega)
	{
		return sqlProductosEstantes.darProductosEstante(pmf.getPersistenceManager(), idBodega);
	}

	public ProductosEstante darProductoEstante (PersistenceManager pm, long idEstante, String idProductoSucursal )
	{
		return sqlProductosEstantes.darProductoEstante(pmf.getPersistenceManager(), idEstante, idProductoSucursal);
	}

	public int darCantidadProductoEstante (PersistenceManager pm, long idEstante, String idProductoSucursal )
	{
		return sqlProductosEstantes.darCantidadProductoSucursalDeEstante(pmf.getPersistenceManager(), idEstante, idProductoSucursal);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los ProductosProveedor
	 *****************************************************************/

	public ProductosProveedor agregarProductosProveedor (String pCodigo,long pIdP,double pPrecioU,int pCanti,String pIdProv,Timestamp pFechaV,double pCali) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosProveedor.agregarProductosProveedor(pm, pCodigo, pIdP, pPrecioU, pCanti, pIdProv, pFechaV, pCali);
		tx.commit();

		log.trace ("Inserci髇 de un producto a un Estante ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new ProductosProveedor(pCodigo, pIdP, pPrecioU, pCanti, pIdProv, pFechaV, pCali);


	}



	public void eliminarProductosProveedorVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosProveedor.eliminarProductosProveedorVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}



	public List<ProductosProveedor> darProductosProveedor ( long idProveedor)
	{
		return sqlProductosProveedor.darProductosProveedor(pmf.getPersistenceManager(), idProveedor);
	}


	public List<ProductosProveedor> darProductosProveedorPedido (long idPedido)
	{
		return sqlProductosProveedor.darProductosProveedorPedido(pmf.getPersistenceManager(), idPedido);
	}

	public ProductosProveedor darProductoProveedor ( String codigo, long idProveedor )
	{
		return sqlProductosProveedor.darProductoProveedor(pmf.getPersistenceManager(), codigo, idProveedor);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los ProductosSucursal
	 *****************************************************************/

	public ProductosSucursal agregarProductosSucursal (long pIdSuc, double pPrecio,String pCodigo, long pIdPromo, String pIdProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosSucursal.agregarProductosSucursal(pm, pIdSuc, pPrecio, pCodigo, pIdPromo, pIdProductoSucursal);
		tx.commit();

		log.trace ("Inserci髇 de un producto a uns Sucrusal ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new ProductosSucursal(pIdSuc, pPrecio, pCodigo, pIdPromo, pIdProductoSucursal);


	}



	public void eliminarProductosSucursalVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosSucursal.eliminarProductosSucursalVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void descontarProductoSucursalEnCantidad (long idProductoSucursal, int cantidadDescontar, String codigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosSucursal.descontarProductoSucursalEnCantidad(pm, idProductoSucursal, cantidadDescontar, codigo);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void abastecerProductoSucursalEnCantidad (long idProductoSucursal, int cantidadDescontar, String codigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosSucursal.abastecerProductoSucursalEnCantidad(pm, idProductoSucursal, cantidadDescontar, codigo);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public List<ProductosSucursal> darProductosSucursal ( long idSucursal)
	{
		return sqlProductosSucursal.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}


	public ProductosSucursal darProductoSucursal (String idProductoSucursal)
	{
		return sqlProductosSucursal.darProductoSucursal(pmf.getPersistenceManager(), idProductoSucursal);
	}

	public ProductosSucursal darPromocionSucursal (PersistenceManager pm, long idPromocion)
	{
		return sqlProductosSucursal.darPromocionSucursal(pmf.getPersistenceManager(), idPromocion);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los ProductosVendidos
	 *****************************************************************/

	public ProductosVendidos agregarProductosVendidos (long noFactura,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosVendidos.agregarProductosVendidos(pm, noFactura, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci髇 de un producto vendido ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new ProductosVendidos(noFactura, idProductoSucursal, cantidad);


	}



	public void eliminarProductosVendidosVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosVendidos.eliminarProductosVendidosVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void disminuirProductosVendidosDeFacturaEnCantidad (long noFactura,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosVendidos.disminuirProductosVendidosDeFacturaEnCantidad(pm, noFactura, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void aumentarProductosVendidosDeFacturaEnCantidad (long noFactura,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProductosVendidos.aumentarProductosVendidosDeFacturaEnCantidad(pm, noFactura, cant, idProductoSucursal);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public ProductosVendidos darProductoVendido ( long noFactura, String idProductoSucursal )
	{
		return sqlProductosVendidos.darProductoVendido(pmf.getPersistenceManager(), noFactura, idProductoSucursal);
	}

	public List<ProductosVendidos> darProductosVendidos ( long noFactura)
	{
		return sqlProductosVendidos.darProductosVendidos(pmf.getPersistenceManager(), noFactura);
	}

	public List<ProductosVendidos> darProductosVendidosSucursal (long idSucursal)
	{
		return sqlProductosVendidos.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las promociones
	 *****************************************************************/

	public Promocion agregarPromocion (String pDescripcion,Timestamp pFechaIni,Timestamp pFechaFin,int px, int py, TipoPromocion pTipo) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		long id = nextval ();
		Transaction tx=pm.currentTransaction();
		
			tx.begin();
			sqlPromocion.agregarPromocion(pm, id,pDescripcion, pFechaIni, pFechaFin, px, py, pTipo);
			tx.commit();

			log.trace ("Inserci髇 de un producto" );
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		
			return new Promocion(id, px, py, pFechaFin, pFechaIni, pTipo, pDescripcion);
		
			
	}


	public void eliminarPromocionPorId ( long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			sqlPromocion.eliminarPromocionPorId(pm, id);
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


	public Promocion darPromocionPorId ( long identificador) 
	{
		return sqlPromocion.darPromocionPorId(pmf.getPersistenceManager(), identificador);
	}
	
	public Promocion darPromocionPorDescripcion ( String descrip) 
	{
		return sqlPromocion.darPromocionPorDescripcion(pmf.getPersistenceManager(), descrip);
	}


	public List<Promocion> darPromociones (PersistenceManager pm)
	{
		return sqlPromocion.darPromociones(pmf.getPersistenceManager()); 
	}

	/* ****************************************************************
	 * 			M閠odos para manejar las PromocionesVendidos
	 *****************************************************************/

	public PromocionesVendidas agregarPromocionesVendidas (long noFactura,int cantidad, long idPromocion) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlPromocionesVendidas.agregarPromocionesVendidas(pm, noFactura, cantidad, idPromocion);
		tx.commit();

		log.trace ("Inserci髇 de un producto vendido ");

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new PromocionesVendidas(noFactura, idPromocion, cantidad);


	}



	public void eliminarPromocionesVendidasVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlPromocionesVendidas.eliminarPromocionesVendidasVacios(pm);
		tx.commit();



		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void disminuirPromocionesVendidasDeFacturaEnCantidad (long noFactura,int cant,long idPromocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlPromocionesVendidas.disminuirPromocionesVendidasDeFacturaEnCantidad(pm, noFactura, cant, idPromocion);;
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void aumentarPromocionesVendidasDeFacturaEnCantidad (long noFactura,int cant,long idPromocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlPromocionesVendidas.aumentarPromocionesVendidasDeFacturaEnCantidad(pm, noFactura, cant, idPromocion);
		tx.commit();

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public int darCantidadPromocionesVendidasDeFactura ( long noFactura, long idPromocion )
	{
		return sqlPromocionesVendidas.darCantidadPromocionesVendidasDeFactura(pmf.getPersistenceManager(), noFactura,idPromocion);
	}
	
	public PromocionesVendidas darProductoVendido (PersistenceManager pm, long noFactura, long idPromocion )
	{
		return sqlPromocionesVendidas.darProductoVendido(pm, noFactura, idPromocion);
	}

	public List<PromocionesVendidas> darPromocionesVendidas (PersistenceManager pm, long noFactura)
	{
		return sqlPromocionesVendidas.darPromocionesVendidas(pmf.getPersistenceManager(), noFactura);
	}

	public List<PromocionesVendidas> darPromocionesSucursal (PersistenceManager pm, long idSucursal)
	{
		return sqlPromocionesVendidas.darPromocionesSucursal(pmf.getPersistenceManager(), idSucursal);
	}

	/* ****************************************************************
	 * 			M閠odos para manejar los Proveedores
	 *****************************************************************/

	public Proveedor agregarProveedor (String pNit,String pNombre,String pCorreo,long pTel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProveedor.agregarProveedor(pm, pNit, pNombre, pCorreo, pTel);
		tx.commit();

		log.trace ("Inserci髇 de proveedor con identificador: " + pNit);

		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();
		return new Proveedor(pNit, pNombre, pCorreo, pTel);


	}



	public void eliminarProveedorPorNombre (String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		tx.begin();
		sqlProveedor.eliminarProveedorPorNombre(pm, nombre);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}

	public void eliminarProveedorPorNit ( String nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		tx.begin();
		sqlProveedor.eliminarProveedorPorNit(pm, nit);
		tx.commit();


		if (tx.isActive())
		{
			tx.rollback();
		}
		pm.close();

	}


	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}


	public Proveedor darProveedorPorNit ( String identificador) 
	{
		return sqlProveedor.darProveedorPorNit (pmf.getPersistenceManager(), identificador);
	}
	
	public Proveedor darProveedorPorNombre ( String identificador) 
	{
		return sqlProveedor.darProveedorPorNombre (pmf.getPersistenceManager(), identificador);
	}
	
	
	/** ****************************************************************
	 * 			Requerimientos funcionales
	 *****************************************************************/
public void requerimiento1(String pNit,String pNombre,String pCorreo,long pTel)
{
	agregarProveedor(pNit, pNombre, pCorreo, pTel);
}

public void requerimiento2(String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto) throws Exception
{
	
	agregarProducto(codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
}


}
