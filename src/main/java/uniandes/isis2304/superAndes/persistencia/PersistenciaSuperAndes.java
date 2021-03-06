package uniandes.isis2304.superAndes.persistencia;


import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import main.java.model.Bodega;
import main.java.model.CarritoProductos;
import main.java.model.Categoria;
import main.java.model.Comprador;
import main.java.model.Empresa;
import main.java.model.EstadoPedido;
import main.java.model.Estante;
import main.java.model.FacturasComprador;
import main.java.model.IndiceBodega;
import main.java.model.IndiceEstante;
import main.java.model.Lote;
import main.java.model.Pedido;
import main.java.model.Persona;
import main.java.model.ProductoRFC12;
import main.java.model.Productos;
import main.java.model.ProductosBodega;
import main.java.model.ProductosEstante;
import main.java.model.ProductosMostrar;
import main.java.model.ProductosProveedor;
import main.java.model.ProductosSucursal;
import main.java.model.ProductosVendidos;
import main.java.model.Promocion;
import main.java.model.PromocionesVendidas;
import main.java.model.Proveedor;
import main.java.model.ProveedorRF12;
import main.java.model.Sucursal;
import main.java.model.TipoProducto;
import main.java.model.TipoPromocion;
import main.java.model.UsuariosComprasRFC10;

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

	private SQLCarritoComprador sqlCarritoComprador;

	private SQLCarritoProductos sqlCarritoProductos;



	/* ****************************************************************
	 * 			Metodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	public PersistenciaSuperAndes ()
	{
		//		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");	
		crearClasesSQL ();
		pmf = JDOHelper.getPersistenceManagerFactory ("Parranderos");


		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("SecuenciaSuperAndes");
		tablas.add ("EMPRESAS");
		tablas.add ("PERSONAS");
		tablas.add ("COMPRADORES");
		tablas.add ("PRODUCTOS");
		tablas.add ("TIPOPRODUCTO");
		tablas.add ("CATEGORIA");
		tablas.add ("SUCURSAL");
		tablas.add ("PRODUCTOSSUCURSAL");
		tablas.add ("LOTE");
		tablas.add ("FACTURASCOMPRADOR");
		tablas.add ("ESTANTE");
		tablas.add ("PRODUCTOSESTANTE");
		tablas.add ("PRODUCTOSVENDIDOS");
		tablas.add ("BODEGAS");
		tablas.add ("PROMOCION");
		tablas.add ("PRODUCTOSBODEGA");
		tablas.add ("PROMOCIONESVENDIDAS");
		tablas.add ("PROVEEDOR");
		tablas.add ("PEDIDO");
		tablas.add ("PRODUCTOSPROVEEDOR");
		tablas.add("CARRITOPERSONAS");
		tablas.add("CARRITOPRODUCTOS");

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
		sqlCategoria=new SQLCategoria(this);
		sqlCompradores=new SQLCompradores(this);
		sqlPromocion=new SQLPromocion(this);
		sqlProveedor=new SQLProveedor(this);
		sqlSucursal=new SQLSucursal(this);
		sqlTipoProducto=new SQLTipoProducto(this, sqlCategoria);
		sqlEmpresas=new SQLEmpresas(this, sqlCompradores);
		sqlProductos=new SQLProductos(this, sqlTipoProducto);
		sqlProductosSucursal=new SQLProductosSucursal(this,sqlSucursal,sqlProductos);
		sqlPersonas=new SQLPersonas(this,sqlCompradores);
		sqlLote=new SQLLote(this, sqlProductos);
		sqlBodegas=new SQLBodegas(this, sqlSucursal, sqlTipoProducto);	
		sqlEstante=new SQLEstante(this, sqlSucursal, sqlTipoProducto);
		sqlFacturasComprador=new SQLFacturasComprador(this, sqlCompradores, sqlSucursal);
		sqlPedido=new SQLPedido(this, sqlProveedor, sqlSucursal);
		sqlPromocionesVendidas=new SQLPromocionesVendidas(this, sqlFacturasComprador, sqlProductosSucursal);
		sqlProductosBodega=new SQLProductosBodega(this, sqlProductosSucursal, sqlBodegas);
		sqlProductosEstantes=new SQLProductosEstantes(this, sqlEstante, sqlProductosSucursal,sqlProductosBodega);
		sqlProductosProveedor=new SQLProductosProveedor(this, sqlPedido, sqlProductos, sqlProveedor);
		sqlProductosVendidos=new SQLProductosVendidos(this, sqlFacturasComprador, sqlProductosSucursal);	
		sqlCarritoComprador=new SQLCarritoComprador(this, sqlCompradores);
		sqlCarritoProductos=new SQLCarritoProductos(this, sqlProductosSucursal, sqlCarritoComprador);

		sqlUtil=new SQLUtil(this);


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
	public String darTablaCarritoPersona()
	{
		return tablas.get (21);
	}
	public String darTablaCarritoProductos()
	{
		return tablas.get (22);
	}




	/**
	 * Transacci�n para el generador de secuencia de superAndes
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de Parranderos
	 */
	private long nextval (PersistenceManager pm,String nombreTabla, String nombreId) {
		Query q1 = pm.newQuery(SQL, "SELECT MAX("+nombreId+") FROM " + nombreTabla );
		BigDecimal bgdmal= (BigDecimal) q1.executeUnique();
		long rta;
		if(bgdmal==null)
			rta=0l;
		else 
			rta=bgdmal.longValue();
		return (rta+1);
	}




	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
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
	 * 			M�todos para manejar las Bodegas
	 *****************************************************************/

	public Bodega agregarBodega(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { 
			tx.begin();

			long id = nextval (pm,darTablaBodegas(),"idBodega");
			sqlBodegas.agregarBodega(pm, id,pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
			tx.commit();

			log.trace ("Inserci�n de bodega de la sucursal con id: " + pIdSuc);

			return new Bodega(id, pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);

		}catch (Exception e)
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

		try { 
			tx.begin();

			sqlBodegas.eliminarBodegaPorId(pm, idBodega);
			tx.commit();

		}catch (Exception e)
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
	 * 			M�todos para manejar las Categorias
	 *****************************************************************/

	public Categoria agregarCategoria(String nombre,boolean perecedero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { 
			tx.begin();

			sqlCategoria.agregarCategoria(pm, nombre, perecedero);
			tx.commit();

			log.trace ("Inserci�n de categoria con nombre: " + nombre);
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

		try { 
			tx.begin();

			sqlCategoria.eliminarCategoriaPorNombre(pm, nombre);
			tx.commit();

		}catch (Exception e)
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
	 * 			M�todos para manejar las empresas
	 *****************************************************************/

	public Empresa agregarEmpresa (String pNit, String pDireccion,String pNombre,String pCorreo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { 
			tx.begin();

			sqlEmpresas.agregarEmpresa(pm, pNit, pDireccion, pNombre, pCorreo);
			tx.commit();

			log.trace ("Inserci�n de empresa con identificador: " + pNit);


			return new Empresa(pNit, pNombre, pCorreo, pDireccion,0);

		}catch (Exception e)
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

		try { tx.begin();
		sqlEmpresas.eliminarEmpresaPorNombre(pm, nombreEmpresa);
		tx.commit();

		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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
		try { 
			tx.begin();
			sqlEmpresas.eliminarEmpresaPorNit(pm, nit);
			tx.commit();


		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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
	 * 			M�todos para manejar los Estantes
	 *****************************************************************/

	public Estante agregarEstante(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try 
		{ 
			tx.begin();
			long id = nextval (pm,darTablaEstantes(),"idEstante");
			sqlEstante.agregarEstante(pm, id,pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
			tx.commit();

			log.trace ("Inserci�n del estante de la sucursal con id: " + pIdSuc);


			return new Estante( id, pIdSuc, pUniP, pUniV, pCapP, pCapV,  pNivel,  pTipo);
		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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

		try { 
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

	public List<Estante> darEstantesPorSucursal (long idSucursal)
	{
		return sqlEstante.darEstantesPorSucursal(pmf.getPersistenceManager(),idSucursal);
	}


	public Estante darEstantePorId (long idEstante)
	{
		return sqlEstante.darEstante (pmf.getPersistenceManager(), idEstante);
	}

	/* ****************************************************************
	 * 			M�todos para manejar las FacturasComprador
	 *****************************************************************/

	public FacturasComprador agregarFacturasComprador ( Timestamp fecha, String idComprador, long idSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long id = nextval (pm,darTablaFacturasCompradores(),"numero");
		sqlFacturasComprador.agregarFacturasComprador(pm, id,fecha, idComprador, idSucursal);
		tx.commit();

		log.trace ("Inserci�n de factura a comprador con id: " + idComprador);
		return new FacturasComprador(id, fecha, idComprador, idSucursal);
		}catch (Exception e)
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

		try { tx.begin();
		sqlFacturasComprador.eliminarFacturasCompradorPorNumero(pm, numero);
		tx.commit();

		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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

	public  List<FacturasComprador> darFacturasNumero ( long numero)
	{
		return sqlFacturasComprador.darFacturaCompradorPorNumero(pmf.getPersistenceManager(), numero); 
	}

	public List<FacturasComprador> darFacturasFecha ( Timestamp fecha)
	{
		return sqlFacturasComprador.darFacturasFecha(pmf.getPersistenceManager(), fecha); 
	}

	public List<FacturasComprador> darFacturasRangoFecha ( String fechaIni,String fechaFin)
	{
		return sqlFacturasComprador.darFacturasRangoFecha(pmf.getPersistenceManager(), fechaIni, fechaFin); 
	}

	public List<FacturasComprador> darFacturasRangoFechaPersona ( String fechaIni,String fechaFin,String cedula)
	{
		return sqlFacturasComprador.darFacturasRangoFechaPersona(pmf.getPersistenceManager(), fechaIni, fechaFin, cedula); 
	}

	/* ****************************************************************
	 * 			M�todos para manejar los lotes
	 *****************************************************************/

	public Lote agregarLote ( String codigo, Timestamp fecha, int cantidad) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlLote.agregarLote(pm, codigo, fecha, cantidad);
		tx.commit();

		log.trace ("Inserci�n de lote del producto: " + codigo);

		return new Lote(codigo, fecha, cantidad);
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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

		try { tx.begin();
		sqlLote.eliminarLote(pm, codigo, fecha);
		tx.commit();


		}catch (Exception e)
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
	 * 			M�todos para manejar los Pedidos
	 *****************************************************************/

	public void  agregarPedido (String fechaEntregaAc,String fechaEntrega,EstadoPedido estado, long idSucursal, String idProveedor) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long id = nextval (pm,darTablaPedido(),"idPedido");
		sqlPedido.agregarPedido(pm, id,fechaEntregaAc, fechaEntrega, estado, idSucursal, idProveedor);
		tx.commit();

		log.trace ("Inserci�n de un pedido" );

		}
		catch (Exception e)
		{
			//	        	e.printStackTrace();
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

	public void  recibirPedido (long idPedido)  
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();

		sqlPedido.cambiarEstadoPedido(pm, EstadoPedido.ENTREGADO, idPedido);
		tx.commit();

		log.trace ("Recepcion de un pedido" );


		pm.close();
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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




	public void eliminarPedidoPorId ( Long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPedido.eliminarPedidoPorId(pm, id);
		tx.commit();


		}catch (Exception e)
		{
			//			        	e.printStackTrace();
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

	public List<Pedido> darPedidosEntregados ()
	{
		return sqlPedido.darPedidosEntregados(pmf.getPersistenceManager());
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
	 * 			M�todos para manejar las Personas
	 *****************************************************************/

	public Persona agregarPersona ( String pIdentificador,String pNombre,String pCorreo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPersonas.agregarPersona(pm, pIdentificador, pNombre, pCorreo);
		tx.commit();

		log.trace ("Inserci�n de una persona" );


		return new Persona(pIdentificador, pNombre, pCorreo,0);
		}catch (Exception e)
		{
			//				        	e.printStackTrace();
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

		try { tx.begin();
		sqlPersonas.eliminarPersonaPorNombre(pm, nombre);
		tx.commit();


		}catch (Exception e)
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

		try { tx.begin();
		sqlPersonas.eliminarPersonaPorCedula(pm, identificador);
		tx.commit();


		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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
	 * 			M�todos para manejar los Productos
	 *****************************************************************/

	public Productos agregarProducto (String codigo, String nombre, String marca, String presentacion, String unidadPeso, double cantidadPeso, String unidadVolumen,double cantidadVolumen,String tipoProducto)  
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductos.agregarProducto(pm, codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
		tx.commit();

		log.trace ("Inserci�n de un producto" );


		return new Productos( codigo, nombre, marca, presentacion, unidadPeso, cantidadPeso, unidadVolumen, cantidadVolumen, tipoProducto);
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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

		try { tx.begin();
		sqlProductos.eliminarProductoPorNombre(pm, nombre);
		tx.commit();

		}catch (Exception e)
		{
			//			        	e.printStackTrace();
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

		try { tx.begin();
		sqlProductos.eliminarProductoPorCodigo(pm, id);
		tx.commit();

		}catch (Exception e)
		{
			//				        	e.printStackTrace();
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

	public boolean existeProducto ( String identificador) 
	{
		return sqlProductos.existeProducto(pmf.getPersistenceManager(), identificador);
	}

	public List<Productos> darProductos ()
	{
		return sqlProductos.darProductos(pmf.getPersistenceManager()); 
	}


	public void actualizarListaProductosMostrarProducto (List<ProductosMostrar> productos)
	{
		sqlProductos.actualizarListaProductosMostrarProducto(pmf.getPersistenceManager(),productos); 
	}
	/* ****************************************************************
	 * 			M�todos para manejar las sucursales
	 *****************************************************************/

	public Sucursal agregarSucursal (String pNombre,String pCiudad,String pDireccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long id = nextval (pm,darTablaSucursal(),"idSucursal");

		sqlSucursal.agregarSucursal(pm, id,pNombre, pCiudad, pDireccion);
		tx.commit();

		log.trace ("Inserci�n de un producto" );

		return new Sucursal( id, pNombre, pCiudad, pDireccion);
		}
		catch (Exception e)
		{
			//					        	e.printStackTrace();
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

		try { tx.begin();
		sqlSucursal.eliminarSucursalPorNombre(pm, nombre);
		tx.commit();


		}catch (Exception e)
		{
			//						        	e.printStackTrace();
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

		try { tx.begin();
		sqlSucursal.eliminarSucursalPorId(pm, id);
		tx.commit();

		}
		catch (Exception e)
		{
			//							        	e.printStackTrace();
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


	public List<Sucursal> darSucursales ()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager()); 
	}


	/* ****************************************************************
	 * 			M�todos para manejar los TiposProductos
	 *****************************************************************/

	public TipoProducto agregarTipoProducto ( String pTipo,String pMetodoAlmacenamiento,String pCategoria) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlTipoProducto.agregarTipoProducto(pm, pTipo, pMetodoAlmacenamiento, pCategoria);
		tx.commit();

		log.trace ("Inserci�n del tipo: " + pTipo);

		return new TipoProducto(pTipo, pMetodoAlmacenamiento, pCategoria);
		}catch (Exception e)
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

		try { tx.begin();
		sqlTipoProducto.eliminarTipoProducto(pm, tipo);
		tx.commit();

		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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
	 * 			M�todos para manejar los ProductosBodega
	 *****************************************************************/

	public ProductosBodega agregarProductosBodega (long idBodega,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosBodega.agregarProductosBodega(pm, idBodega, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci�n de un producto a una bodega ");

		return new ProductosBodega(idBodega, cantidad, idProductoSucursal);
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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



	public void eliminarProductosBodegaVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosBodega.eliminarProductosBodegaVacios(pm);
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
	public void descontarProductoSucursalDeBodegaEnCantidad (long idBodega,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosBodega.descontarProductoSucursalDeBodegaEnCantidad(pm, idBodega, cant, idProductoSucursal);
		tx.commit();

		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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

	public void abastecerProductoSucursalDeBodegaEnCantidad (long idBodega,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosBodega.abastecerProductoSucursalDeBodegaEnCantidad(pm, idBodega, cant, idProductoSucursal);
		tx.commit();

		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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
	 * 			M�todos para manejar los ProductosEstante
	 *****************************************************************/

	public ProductosBodega agregarProductosEstante (long idBodega,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosEstantes.agregarProductosEstante(pm, idBodega, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci�n de un producto a un Estante ");


		return new ProductosBodega(idBodega, cantidad, idProductoSucursal);
		}catch (Exception e)
		{
			//			        	e.printStackTrace();
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


	public void actualizarListaProductosMostrarProductosEstante (List<ProductosMostrar> productos)
	{
		sqlProductosEstantes.actualizarListaProductosMostrarProductosEstante(pmf.getPersistenceManager(), productos);
	}

	public void eliminarProductosEstanteVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosEstantes.eliminarProductosEstanteVacios(pm);
		tx.commit();

		}
		catch (Exception e)
		{
			//				        	e.printStackTrace();
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
	public void descontarProductoSucursalDeEstanteEnCantidad (long idEstante,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosEstantes.descontarProductoSucursalDeEstanteEnCantidad(pm, idEstante, cant, idProductoSucursal);
		tx.commit();

		}
		catch (Exception e)
		{
			//					        	e.printStackTrace();
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

	public void abastecerProductoSucursalDeEstanteEnCantidad (int cant,String idProductoSucursal, long idEstante)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosEstantes.abastecerProductoSucursalDeEstanteEnCantidad(pm, idEstante,cant, idProductoSucursal);
		tx.commit();
		}catch (Exception e){
			//						        	e.printStackTrace();
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



	public List<ProductosEstante> darProductosEstanteSucursal ( long idSucursal)
	{
		return sqlProductosEstantes.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}


	public List<ProductosEstante> darProductosEstante ( long idBodega)
	{
		return sqlProductosEstantes.darProductosEstante(pmf.getPersistenceManager(), idBodega);
	}

	public ProductosEstante darProductoEstante ( long idEstante, String idProductoSucursal )
	{
		return sqlProductosEstantes.darProductoEstante(pmf.getPersistenceManager(), idEstante, idProductoSucursal);
	}


	public List<ProductosMostrar> darProductosMostrarDeEstante(long idEstante){
		return sqlProductosEstantes.darProductosMostrarDeEstante(pmf.getPersistenceManager(), idEstante);

	}

	/* ****************************************************************
	 * 			M�todos para manejar los ProductosProveedor
	 *****************************************************************/

	public ProductosProveedor agregarProductosProveedor (String pCodigo,long pIdP,double pPrecioU,int pCanti,String pIdProv,Timestamp pFechaV,double pCali) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosProveedor.agregarProductosProveedor(pm, pCodigo, pIdP, pPrecioU, pCanti, pIdProv, pFechaV, pCali);
		tx.commit();

		log.trace ("Inserci�n de un producto a un Estante ");


		return new ProductosProveedor(pCodigo, pIdP, pPrecioU, pCanti, pIdProv, pFechaV, pCali);
		}catch (Exception e)
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



	public void eliminarProductosProveedorVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosProveedor.eliminarProductosProveedorVacios(pm);
		tx.commit();
		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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
	 * 			M�todos para manejar los ProductosSucursal
	 *****************************************************************/

	public ProductosSucursal agregarProductosSucursal (long pIdSuc, double pPrecio,String pCodigo) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		String idProductoSucursal=pIdSuc+"-"+pCodigo;

		try { tx.begin();
		sqlProductosSucursal.agregarProductosSucursal(pm, pIdSuc, pPrecio, pCodigo, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci�n de un producto a uns Sucrusal ");

		return new ProductosSucursal(pIdSuc, pPrecio, pCodigo, idProductoSucursal);
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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



	public void eliminarProductosSucursalVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosSucursal.eliminarProductosSucursalVacios(pm);
		tx.commit();
		}catch (Exception e)
		{
			//			        	e.printStackTrace();
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

	public void descontarProductoSucursalEnCantidad (String idProductoSucursal, int cantidadDescontar)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosSucursal.descontarProductoSucursalEnCantidad(pm, idProductoSucursal, cantidadDescontar);
		tx.commit();
		}catch (Exception e)
		{
			//				        	e.printStackTrace();
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

	public void abastecerProductoSucursalEnCantidad (String idProductoSucursal, int cantidadDescontar)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosSucursal.abastecerProductoSucursalEnCantidad(pm, idProductoSucursal, cantidadDescontar);
		tx.commit();
		}catch (Exception e)
		{
			//					        	e.printStackTrace();
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

	public List<ProductosSucursal> darProductosSucursal ( long idSucursal)
	{
		return sqlProductosSucursal.darProductosSucursal(pmf.getPersistenceManager(), idSucursal);
	}


	public ProductosSucursal darProductoSucursal (String idProductoSucursal)
	{
		return sqlProductosSucursal.darProductoSucursal(pmf.getPersistenceManager(), idProductoSucursal);
	}

	public void actualizarListaProductosMostrarProductoSucursal(List<ProductosMostrar> productos)
	{
		sqlProductosSucursal.actualizarListaProductosMostrarProductoSucursal(pmf.getPersistenceManager(), productos);

	}
	public boolean existeProductoSucursal (String idProductoSucursal)
	{
		return sqlProductosSucursal.existeProductoSucursal(pmf.getPersistenceManager(), idProductoSucursal);
	}

	public ProductosSucursal darPromocionSucursal (PersistenceManager pm, long idPromocion)
	{
		return sqlProductosSucursal.darPromocionSucursal(pmf.getPersistenceManager(), idPromocion);
	}

	/* ****************************************************************
	 * 			M�todos para manejar los ProductosVendidos
	 *****************************************************************/

	public ProductosVendidos agregarProductosVendidos (long noFactura,int cantidad, String idProductoSucursal) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosVendidos.agregarProductosVendidos(pm, noFactura, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci�n de un producto vendido ");

		return new ProductosVendidos(noFactura, idProductoSucursal, cantidad);
		}catch (Exception e)
		{
			//						        	e.printStackTrace();
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



	public void eliminarProductosVendidosVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosVendidos.eliminarProductosVendidosVacios(pm);
		tx.commit();
		}catch (Exception e)
		{
			//							        	e.printStackTrace();
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

	public void disminuirProductosVendidosDeFacturaEnCantidad (long noFactura,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosVendidos.disminuirProductosVendidosDeFacturaEnCantidad(pm, noFactura, cant, idProductoSucursal);
		tx.commit();

		}catch (Exception e)
		{
			//								        	e.printStackTrace();
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

	public void aumentarProductosVendidosDeFacturaEnCantidad (long noFactura,int cant,String idProductoSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProductosVendidos.aumentarProductosVendidosDeFacturaEnCantidad(pm, noFactura, cant, idProductoSucursal);
		tx.commit();
		}catch (Exception e)
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

	public ProductosVendidos darProductoVendido ( long noFactura, String idProductoSucursal )
	{
		return sqlProductosVendidos.darProductoVendido(pmf.getPersistenceManager(), noFactura, idProductoSucursal);
	}

	public List<ProductosVendidos> darProductoVendidoFactura ( long noFactura )
	{
		return sqlProductosVendidos.darProductosVendidosDeFactura(pmf.getPersistenceManager(), noFactura);
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
	 * 			M�todos para manejar las promociones
	 *****************************************************************/

	public void agregarPromocion (String pDescripcion,String pFechaIni,String pFechaFin,int px, int py, TipoPromocion pTipo) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		long id = nextval (pm,darTablaPromociones(),"idPromocion");
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocion.agregarPromocion(pm, id,pDescripcion, pFechaIni, pFechaFin, px, py, pTipo);
		tx.commit();

		log.trace ("Inserci�n de un producto" );
		}catch (Exception e)
		{
			//	        	e.printStackTrace();
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


	public void eliminarPromocionPorId ( long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocion.eliminarPromocionPorId(pm, id);
		tx.commit();

		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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

	public void eliminarPromocionPorFecha ( )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocion.eliminarPromocionPorFecha(pm);
		tx.commit();
		}catch (Exception e)
		{
			//			        	e.printStackTrace();
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


	public List<Promocion> darPromociones ()
	{
		return sqlPromocion.darPromociones(pmf.getPersistenceManager()); 
	}

	/* ****************************************************************
	 * 			M�todos para manejar las PromocionesVendidos
	 *****************************************************************/

	public PromocionesVendidas agregarPromocionesVendidas (long noFactura,int cantidad, long idPromocion) throws Exception 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocionesVendidas.agregarPromocionesVendidas(pm, noFactura, cantidad, idPromocion);
		tx.commit();

		log.trace ("Inserci�n de un producto vendido ");

		return new PromocionesVendidas(noFactura, idPromocion, cantidad);
		}catch (Exception e)
		{
			//				        	e.printStackTrace();
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



	public void eliminarPromocionesVendidasVacios ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocionesVendidas.eliminarPromocionesVendidasVacios(pm);
		tx.commit();
		}catch (Exception e)
		{
			//					        	e.printStackTrace();
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

	public void disminuirPromocionesVendidasDeFacturaEnCantidad (long noFactura,int cant,long idPromocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocionesVendidas.disminuirPromocionesVendidasDeFacturaEnCantidad(pm, noFactura, cant, idPromocion);;
		tx.commit();
		}catch (Exception e)
		{
			//						        	e.printStackTrace();
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

	public void aumentarPromocionesVendidasDeFacturaEnCantidad (long noFactura,int cant,long idPromocion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlPromocionesVendidas.aumentarPromocionesVendidasDeFacturaEnCantidad(pm, noFactura, cant, idPromocion);
		tx.commit();
		}catch (Exception e)
		{
			//							        	e.printStackTrace();
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
	 * 			M�todos para manejar los Proveedores
	 *****************************************************************/

	public Proveedor agregarProveedor (String pNit,String pNombre,String pCorreo,long pTel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProveedor.agregarProveedor(pm, pNit, pNombre, pCorreo, pTel);
		tx.commit();

		log.trace ("Inserci�n de proveedor con identificador: " + pNit);

		return new Proveedor(pNit, pNombre, pCorreo, pTel);
		}catch (Exception e)
		{
			//								        	e.printStackTrace();
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



	public void eliminarProveedorPorNombre (String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlProveedor.eliminarProveedorPorNombre(pm, nombre);
		tx.commit();
		}catch (Exception e)
		{
			//									        	e.printStackTrace();
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

	public void eliminarProveedorPorNit ( String nit)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try { tx.begin();
		sqlProveedor.eliminarProveedorPorNit(pm, nit);
		tx.commit();
		}catch (Exception e)
		{
			//										        	e.printStackTrace();
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


	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}


	public Proveedor darProveedorPorNit ( String identificador) 
	{
		return sqlProveedor.darProveedorPorNit (pmf.getPersistenceManager(), identificador);
	}

	public boolean existeProveedor( String identificador) 
	{
		return sqlProveedor.existeProveedor (pmf.getPersistenceManager(), identificador);
	}

	public Proveedor darProveedorPorNombre ( String identificador) 
	{
		return sqlProveedor.darProveedorPorNombre (pmf.getPersistenceManager(), identificador);
	}

	public boolean existePersona ( String identificador) 
	{
		return sqlPersonas.existePersona(pmf.getPersistenceManager(), identificador);
	}

	public boolean existeEmpresa ( String nit) 
	{
		return sqlEmpresas.existeEmpresa (pmf.getPersistenceManager(), nit);
	}



	public Comprador darCompradorPorIdentificador (String identificador) {
		return sqlCompradores.darCompradorPorIdentificador(pmf.getPersistenceManager(), identificador);
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

	public void requerimiento3_1(String pNit, String pDireccion,String pNombre,String pCorreo)
	{
		agregarEmpresa(pNit, pDireccion, pNombre, pCorreo);
	}
	public void requerimiento3_2(String pIdentificador, String pDireccion,String pNombre,String pCorreo)
	{
		agregarPersona(pIdentificador, pNombre, pCorreo);
	}

	public Sucursal requerimiento4(String pNombre,String pCiudad,String pDireccion)
	{
		return agregarSucursal(pNombre, pCiudad, pDireccion);
	}

	public void requerimiento5(double pCapV,double pCapP,String pUniP,String pUniV,long pIdSuc, double pNivel, String pTipo) throws Exception
	{
		agregarBodega(pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
	}

	public void requerimiento6(double pCapV,double pCapP,String pUniP,String pUniV,Long pIdSuc, double pNivel, String pTipo) throws Exception
	{
		agregarEstante(pCapV, pCapP, pUniP, pUniV, pIdSuc, pNivel, pTipo);
	}
	public void requerimiento7(String pDescripcion,String pFechaIni,String pFechaFin,int px, int py, TipoPromocion pTipo) throws Exception 
	{
		agregarPromocion(pDescripcion, pFechaIni, pFechaFin, px, py, pTipo);
	}
	public void requerimiento8()
	{
		eliminarPromocionPorFecha();
		eliminarPromocionesVendidasVacios();
	}
	public void requerimiento9(String fechaEntregaAc,EstadoPedido estado, long idSucursal, String idProveedor) throws Exception
	{
		agregarPedido(fechaEntregaAc, null, estado, idSucursal, idProveedor);
	}
	public void requerimiento10(long idPedido) 
	{
		recibirPedido(idPedido);
	}
	public void requerimiento11(long noFactura,int cant,String idProductoSucursal)
	{
		disminuirProductosVendidosDeFacturaEnCantidad(noFactura, cant, idProductoSucursal);
	}

	public long requerimiento12(String docComprador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		long idCarrito=nextval(pm, darTablaCarritoPersona(), "idCarrito");
		try { tx.begin();
		sqlCarritoComprador.agregarCarrito(pm, idCarrito, docComprador);
		tx.commit();

		log.trace ("Insercion de un cliente a un carrito ");

		return idCarrito;
		}catch (Exception e)
		{
			//											        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
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

	public void requerimiento13(String idComprador,String idProducto,String nombre,int cantidad) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long idSuc=sqlSucursal.darIdSucursalPorNombre(pm, nombre);
		String idProductoSucursal=idProducto+"-"+idSuc;
		Long idCarrito=sqlCarritoComprador.darIdCarritoPorIdentificadorComprador(pm, idComprador).get(0);
		Long idEstante=sqlProductosEstantes.darIdEstantePorProductoSucursal(pm, idProductoSucursal).get(0);
		sqlProductosEstantes.descontarProductoSucursalDeEstanteEnCantidad(pm, idEstante, cantidad, idProductoSucursal);
		sqlCarritoProductos.agregarProductosAlCarrito(pm, idCarrito, cantidad, idProductoSucursal);
		tx.commit();

		log.trace ("Inserci�n de un cliente a un carrito ");
		}catch (Exception e)
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

	public void requerimiento14(String idComprador,String idProducto,String nombre,int cantidad) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long idSuc=sqlSucursal.darIdSucursalPorNombre(pm, nombre);
		String idProductoSucursal=idProducto+"-"+idSuc;
		Long idCarrito=sqlCarritoComprador.darIdCarritoPorIdentificadorComprador(pm, idComprador).get(0);
		Long idEstante=sqlProductosEstantes.darIdEstantePorProductoSucursal(pm, idProductoSucursal).get(0);
		sqlProductosEstantes.abastecerProductoSucursalDeEstanteEnCantidad(pm, idEstante, cantidad, idProductoSucursal);
		int cantidad2=sqlCarritoProductos.darCantidadProductosCarrito(pm, idCarrito, idProductoSucursal);
		if(cantidad==cantidad2)
		{
			sqlCarritoProductos.devolverProductoCarrito(pm, idCarrito, idProductoSucursal);
		}else
		{
			sqlCarritoProductos.devolverParteProductoCarrito(pm, idCarrito, idProductoSucursal, cantidad);
		}
		tx.commit();

		log.trace ("Inserci�n de un cliente a un carrito ");
		}catch (Exception e)
		{
			e.printStackTrace();
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


	public void eliminarCarritoPorCliente(String docComprador)
	{
		sqlCarritoComprador.eliminarCarritoPorCliente(pmf.getPersistenceManager(), docComprador);

	}
	public void requerimiento15(String docComprador, long idSucursal,long idCarrito,List<ProductosMostrar>productos) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		long numeroFactura=nextval(pm, darTablaFacturasCompradores(), "numero");
		System.out.println(numeroFactura);
		sqlFacturasComprador.agregarFacturasComprador(pm, numeroFactura, new Timestamp(System.currentTimeMillis()), docComprador, idSucursal);
		for(ProductosMostrar actual:productos)
		{
			sqlProductosVendidos.agregarProductosVendidos(pm, numeroFactura, actual.getCantidad(), actual.getIdProductoSucursal());
			sqlProductosSucursal.descontarProductoSucursalEnCantidad(pm, actual.getIdProductoSucursal(), actual.getCantidad());
			//			sqlProductosEstantes.verificarReorden(pm, actual.getIdProductoSucursal(),actual.getIdEstante());
			verificarReOrden(pm,actual);
		}
		sqlCarritoProductos.vaciarProductosCarrito(pm, idCarrito);
		sqlCarritoComprador.eliminarCarritoPorCliente(pm, docComprador);
		tx.commit();
		}catch (Exception e)
		{
			//		        	e.printStackTrace();
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

	public void verificarReOrden(PersistenceManager pm, ProductosMostrar productoComprado) {
		pm = pmf.getPersistenceManager();

		int cantTotal=sqlProductosSucursal.darCantidadProductoPorId(pm, productoComprado.getIdProductoSucursal());
		int cantBodega=sqlProductosBodega.darCantidadProductoSucursalEnBodegas(pm, productoComprado.getIdProductoSucursal());

		int cantEstante=cantTotal-cantBodega;
		if(cantEstante<=sqlEstante.darNivelReOrden(pm, productoComprado.getIdEstante()))
		{
			//			System.out.println("ccssf");

			//cantidad[0]=peso, cantidad[1]=volumen
			double[] cantidad=sqlProductosEstantes.darCantidadLleno(pm,productoComprado.getIdEstante(),sqlProductos);
			double[] porLlenar=sqlEstante.darPorLlenar(pm, productoComprado.getIdEstante(), cantidad);
			List<Productos> productos=sqlProductosEstantes.getProductos();
			//			for(Productos prod:productos)
			//				prod.setCantidad((int)(Math.random()*30));
			//			for(Productos prod:productos)
			//				System.out.println("cantSin:"+prod.getCantidad());
			ordenarInsercion(productos,true);
			//			for(Productos prod:productos)
			//			System.out.println("cant:"+prod.getCantidad());
			long idSucursal=Long.parseLong(""+productoComprado.getIdProductoSucursal().subSequence(0, productoComprado.getIdProductoSucursal().indexOf("-")));
			boolean termine=false;
			List<Long> idsBodegas=new ArrayList<Long>(); 

			List<Productos> aumentar=new ArrayList<Productos>();
			int noAgrego=0;
			System.out.println("tam: "+productos.size());
			for(int i=0;(i+1)<productos.size()&&!termine;i++)
			{

				aumentar.add(productos.get(i));
				Productos p2=productos.get(i+1);
				System.out.println(aumentar.get(aumentar.size()-1).getCantidad()<p2.getCantidad());

				while(aumentar.get(aumentar.size()-1).getCantidad()<p2.getCantidad()&&!termine)
				{
					noAgrego=0;
					for (int j = 0; j < aumentar.size()&&!termine; j++) {
						Productos actual=aumentar.get(j);
						if(porLlenar[0]-actual.getCantidadPeso()<0)
							noAgrego++;
						else
						{
							String idProductoSucursal=idSucursal+"-"+actual.getCodigoBarras();
							long idBod=sqlProductosBodega.descontarProductoSucursalDeBodegaEnCantidad(pm, 1, idProductoSucursal);
							boolean esta=false;
							for(int h=0;h<idsBodegas.size()&&!esta;h++)
								if(idsBodegas.get(h)==(idBod))
									esta=true;
							//								System.out.println("lo va  a tratar");

							if(!esta)
							{
								//									System.out.println("lo agrego");
								idsBodegas.add(idBod);
							}
							sqlProductosEstantes.abastecerProductoSucursalDeEstanteEnCantidad(pm, productoComprado.getIdEstante(), 1, idProductoSucursal);
							actual.setCantidad(actual.getCantidad()+1);
							porLlenar[0]=porLlenar[0]-actual.getCantidadPeso();
						}
					}

					if(noAgrego>=aumentar.size())
						termine=true;



					//						System.out.println("ids ");

					//						for(long id:idsBodegas)
					//							System.out.println("id: "+id);

				}
			}
			//				System.out.println("pre");

			for(int k=0;k<idsBodegas.size();k++)
			{
				//					System.out.println("a");
				productos.clear();
				cantidad=sqlProductosBodega.darCantidadLleno(pm,idsBodegas.get(k),sqlProductos);
				porLlenar=sqlBodegas.darPorLlenar(pm,idsBodegas.get(k), cantidad);
				if(porLlenar[0]>=porLlenar[1])
				{

					termine=true;
					List<ProductosBodega> productosBodega=sqlProductosBodega.darProductosBodega(pm,idsBodegas.get(k));
					for(int i=0;i<productosBodega.size()&&!termine;i++)
						//							if(productosBodega.get(k).getCantidad()<=sqlBodegas.darNivelReOrden(pm, idsBodegas.get(k)))
						if(true)
						{
							termine=false;
							productos=sqlProductosBodega.getProductos();
						}
					int[] cantidades=new int[productos.size()];
					for(int i=0;(i+1)<productos.size()&&!termine;i++)
					{
						//							System.out.println("b");

						aumentar.add(productos.get(i));
						Productos p2=productos.get(i+1);

						while(aumentar.get(aumentar.size()-1).getCantidad()<p2.getCantidad()&&!termine)
						{
							noAgrego=0;
							for (int j = 0; j < aumentar.size()&&!termine; j++) {
								Productos actual=aumentar.get(j);
								if(porLlenar[0]-actual.getCantidadPeso()<0)
									noAgrego++;
								else
								{
									cantidades[i]++;
									porLlenar[0]=porLlenar[0]-actual.getCantidadPeso();
								}
							}
							if(noAgrego>=aumentar.size())
								termine=true;	


						}
					}

					sqlProductosBodega.setProductosNull();

					for(int i=0;i<cantidades.length;i++)
					{
						long idPedido=nextval(pm, "PEDIDO", "idPEdido");
						//						sqlPedido.agregarPedido(pm, idPedido, null, null, null, null, "10101010");

					}





				}
			}




			sqlProductosEstantes.setProductosNull();
		}
	}


	private void ordenarInsercion(List<Productos> lista, boolean ascendente) {
		// TODO Complete segï¿½n la documentaciï¿½n

		if(ascendente == true){
			for( int i=1; i < lista.size(); i++){
				for(int j = i; j > 0 && lista.get(j-1).compareTo( lista.get(j)) > 0; j--){
					Productos temp = lista.get(j);
					lista.set(j, lista.get(j-1));
					lista.set(j-1, temp);
				}
			}
		}

		else if(ascendente == false){
			for( int i=1; i < lista.size(); i++){
				for(int j = i; j > 0 && lista.get(j-1).compareTo( lista.get(j)) < 0; j--){
					Productos temp = lista.get(j);
					lista.set(j, lista.get(j-1));
					lista.set(j-1, temp);
				}
			}
		}
	}

	public void requerimiento16(String docComprador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { tx.begin();
		sqlCarritoComprador.abandonarCarritoCliente(pm, docComprador);
		tx.commit();

		log.trace ("Inserci�n de un cliente a un carrito ");
		}catch (Exception e)
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

	public void requerimiento17() throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try {
			List<CarritoProductos>productos=sqlCarritoProductos.darProductosCarritoAvandonados(pm);
			sqlCarritoComprador.eliminarCarritoPorCliente(pm, null);
			tx.begin();
			if(productos!=null)
			{
				int veces=0;
				for(CarritoProductos actual:productos)
				{
					System.out.println("empezo");
					long idEstante=sqlProductosEstantes.darIdEstantePorProductoSucursal(pm,actual.getIdProductoSucursal()).get(0);
					System.out.println("ya casi");	
					sqlProductosEstantes.abastecerProductoSucursalDeEstanteEnCantidad(pm, idEstante,actual.getcantidadProducto(), actual.getIdProductoSucursal());
					System.out.println("estamos cerca");
					sqlCarritoProductos.devolverProductoCarrito(pm, actual.getIdCarrito(), actual.getIdProductoSucursal());
					System.out.println(veces+"");
					log.trace ("Recoleccion de carritos");

				}
			}
			tx.commit();
		}catch (Exception e)
		{
			e.printStackTrace();
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

	public void requerimiento19(long idPedido, long idBodega) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();

		try { 
			tx.begin();

			long idSucursal=sqlPedido.darIdSucursalPorIdPedido(pm, idPedido);
			sqlPedido.cambiarEstadoPedido(pm, EstadoPedido.ENTREGADO, idPedido);
			sqlPedido.recibirPedido(pm, new Timestamp(System.currentTimeMillis()), idPedido);
			List<ProductosProveedor>productos=sqlProductosProveedor.darProductosProveedorPedido(pm, idPedido);
			for(ProductosProveedor actual:productos)
			{
				String idProductoSucursal=idSucursal+"-"+actual.getCodigoProducto();
				sqlProductosBodega.abastecerProductoSucursalDeBodegaEnCantidad(pm, idBodega,actual.getCantidadUnidades(), idProductoSucursal);
			}
			tx.commit();

			log.trace ("Abastecimiento de bodega ");

		}catch (Exception e)
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

	public double requerimientoConsulta1(String fechaIni,String fechaFin)
	{
		List <FacturasComprador> facturas=darFacturasRangoFecha(fechaIni, fechaFin);
		double total=0;
		for(FacturasComprador actual:facturas)
		{
			List<ProductosVendidos> ventas=darProductoVendidoFactura(actual.getNumero());

			for(ProductosVendidos ventaActual:ventas)
			{
				total+=ventaActual.getCantidadVendida()*darProductoSucursal(ventaActual.getIdProductoSucursal()).getPrecio();
			}
		}
		return total;
	}

	public List<Promocion> requerimientoConsulta2()
	{		
		return sqlPromocion.darPromocionesMasVend(pmf.getPersistenceManager());
	}

	public List<IndiceBodega> requerimientoConsulta3_1()
	{
		List<IndiceBodega> req3= sqlBodegas.darOcupacion(pmf.getPersistenceManager());
		return req3;
	}

	public List<IndiceEstante> requerimientoConsulta3_2()
	{
		List<IndiceEstante> req3= sqlEstante.darOcupacion(pmf.getPersistenceManager());
		return req3;
	}

	public List<Productos> requerimientoConsulta4_1(double valMin,double valMax)
	{		
		return sqlProductosSucursal.darProductosRangoPrecio(pmf.getPersistenceManager(), valMin, valMax);
	}

	public List<Productos> requerimientoConsulta4_2(String fecha)
	{		
		return sqlProductos.darProductosFechaVencPost(pmf.getPersistenceManager(), fecha);
	}

	public List<Productos> requerimientoConsulta4_3(double valMin,double valMax)
	{		
		return sqlProductos.darProductosRangoPeso(pmf.getPersistenceManager(), valMin, valMax);
	}

	public List<Productos> requerimientoConsulta4_4(double valMin,double valMax)
	{		
		return sqlProductos.darProductosRangoVol(pmf.getPersistenceManager(), valMin, valMax);
	}

	public List<Productos> requerimientoConsulta4_5(String marca)
	{		
		return sqlProductos.darProductosMarca(pmf.getPersistenceManager(), marca);
	}

	public List<Productos> requerimientoConsulta4_6(String ciudad)
	{		
		return sqlProductos.darProductosCiudad(pmf.getPersistenceManager(), ciudad);
	}

	public List<Productos> requerimientoConsulta4_7(long sucursal)
	{		
		return sqlProductos.darProductosSucursal(pmf.getPersistenceManager(), sucursal);
	}

	public List<Productos> requerimientoConsulta4_8(String categoria)
	{		
		return sqlProductos.darProductosCategoria(pmf.getPersistenceManager(), categoria);
	}

	public List<Productos> requerimientoConsulta4_9(String tipo)
	{		
		return sqlProductos.darProductosTipo(pmf.getPersistenceManager(), tipo);
	}

	public List<Pedido> requerimientoConsulta5()
	{
		return darPedidosEntregados();
	}

	public List<FacturasComprador> requerimientoConsulta6(String fechaIni,String fechaFin,String cedula)
	{
		return darFacturasRangoFechaPersona(fechaIni, fechaFin, cedula);
	}

	public String requerimientoConsulta7_1(String tipo)
	{
		return sqlProductosVendidos.darMesMayorDemanda(pmf.getPersistenceManager(), tipo);
	}

	public String requerimientoConsulta7_2(String tipo)
	{
		return sqlProductosVendidos.darMesMenorDemanda(pmf.getPersistenceManager(), tipo);
	}

	public String requerimientoConsulta7_3(String tipo)
	{
		return sqlProductosVendidos.darMesMayorIngreso(pmf.getPersistenceManager(), tipo);
	}

	public List<Comprador> requerimientoConsulta8()
	{		
		return sqlCompradores.darClientesFrecuentes(pmf.getPersistenceManager());
	}

	public List<Productos> requerimientoConsulta9()
	{		
		return sqlProductos.darProductosPocaDemanda(pmf.getPersistenceManager());
	}
	public List<UsuariosComprasRFC10> RFC10Gerente(String idSucursal,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		return sqlCompradores.RFC10Gerente(pmf.getPersistenceManager(), idSucursal,fechaInicial, fechaFinal,  codigoProducto, criteriosOrden, ascendenciaCriterios);
	}
	public List<UsuariosComprasRFC10> RFC10Administrador(String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		return sqlCompradores.RFC10Administrador(pmf.getPersistenceManager(), fechaInicial, fechaFinal,  codigoProducto, criteriosOrden, ascendenciaCriterios);
	}
	
	public List<Comprador> RFC11Gerente(String idSucursal,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{
		return sqlCompradores.RFC11Gerente(pmf.getPersistenceManager(),idSucursal, fechaInicial, fechaFinal,  codigoProducto, criteriosOrden, ascendenciaCriterios);

	}
	public List<Comprador> RFC11Administrador(String idSucursal,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{
		return sqlCompradores.RFC11Administrador(pmf.getPersistenceManager(), fechaInicial, fechaFinal,  codigoProducto, criteriosOrden, ascendenciaCriterios);

	}
	
	public List<ProductoRFC12> RFC12MasVendido()
	{
		return sqlProductosVendidos.RFC12MasVendido(pmf.getPersistenceManager());
	}
	
	public List<ProductoRFC12> RFC12MenosVendido()
	{
		return sqlProductosVendidos.RFC12MenosVendido(pmf.getPersistenceManager());
	}

	public List<ProveedorRF12> RFC12MasSolicitado()
	{
		return sqlProveedor.RFC12MasSolicitado(pmf.getPersistenceManager());
	}
	
	public List<ProveedorRF12> RFC12MenosSolicitado()
	{
		return sqlProveedor.RFC12MenosSolicitado(pmf.getPersistenceManager());
	}
	
	public List<Comprador> RFC13BuenosClientesCompraCadaMes() {
		return sqlCompradores.RFC13BuenosClientesCompraCadaMes(pmf.getPersistenceManager());

	}
	
	public List<Comprador> RFC13BuenosClientesCompraProductosCostosos() {
		return sqlCompradores.RFC13BuenosClientesCompraCadaMes(pmf.getPersistenceManager());

	}
	public List<Comprador> RFC13BuenosClientesCompraTecnologiaHerramientas() {
		return sqlCompradores.RFC13BuenosClientesCompraCadaMes(pmf.getPersistenceManager());

	}


}
