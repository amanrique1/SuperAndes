package uniandes.isis2304.superAndes.persistencia;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author ANDRES
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes persistencia;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaSuperAndes pp)
	{
		this.persistencia = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ persistencia.darSeqUniandes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
        
		Query qBodegas = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaBodegas());
		Query qCategorias = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaCategorias());
		Query qCompradores = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaCompradores());
		Query qEmpresas = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaEmpresas());
		Query qEstantes = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaEstantes());
		Query qFacturasCompradores = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaFacturasCompradores());
		Query qLotes = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaLotes());
		Query qPedido = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaPedido());
		Query qPersonas = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaPersonas());
		Query qProductos = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductos());
		Query qProductosBodega = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductosBodega());
		Query qProductosEstante = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductosEstante());
		Query qProductosProveedor = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductosProveedor());
		Query qProductosSucursal = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductosSucursal());
		Query qProductosVendidos = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProductosVendidos());
		Query qTipoProducto = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaTipoProducto());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaSucursal());
		Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaProveedor());
		Query qPromocionesVendidas = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaPromocionesVendidas());
		Query qPromociones = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaPromociones());
		
		
        long eqBodegas = (long) qBodegas.executeUnique ();
        long eqCategorias = (long) qCategorias.executeUnique ();
        long eqCompradores = (long) qCompradores.executeUnique ();
        long eqEmpresas = (long) qEmpresas.executeUnique ();
        long eqEstantes = (long) qEstantes.executeUnique ();
        long eqFacturasCompradores = (long) qFacturasCompradores.executeUnique ();
        long eqLotes = (long) qLotes.executeUnique ();
        long eqPedido = (long) qPedido.executeUnique ();
        long eqPersonas = (long) qPersonas.executeUnique ();
        long eqProductos = (long) qProductos.executeUnique ();
        long eqProductosBodega = (long) qProductosBodega.executeUnique ();
        long eqProductosEstante = (long) qProductosEstante.executeUnique ();
        long eqProductosProveedor = (long) qProductosProveedor.executeUnique ();
        long eqProductosSucursal = (long) qProductosSucursal.executeUnique ();
        long eqProductosVendidos = (long) qProductosVendidos.executeUnique ();
        long eqTipoProducto = (long) qTipoProducto.executeUnique ();
        long eqSucursal = (long) qSucursal.executeUnique ();
        long eqProveedor = (long) qProveedor.executeUnique ();
        long eqPromocionesVendidas = (long) qPromocionesVendidas.executeUnique ();
        long eqPromociones = (long) qPromociones.executeUnique ();
       
        return new long[] {eqBodegas,eqCategorias,eqCompradores,eqEmpresas,eqEstantes,eqFacturasCompradores,
        		eqLotes,eqPedido,eqPersonas,eqProductos,eqProductosBodega,eqProductosEstante,eqProductosProveedor,
        		eqProductosSucursal,eqProductosVendidos,eqTipoProducto,eqSucursal,eqProveedor,eqPromocionesVendidas,eqPromociones};
	}

}
