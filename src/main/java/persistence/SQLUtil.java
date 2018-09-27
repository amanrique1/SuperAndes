package main.java.persistence;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
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
        Query q = pm.newQuery(SQL, "SELECT "+ persistencia.darSeqParranderos () + ".nextval FROM DUAL");
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
	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qGustan = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaGustan ());          
        Query qSirven = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaSirven ());
        Query qVisitan = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaVisitan ());
        Query qBebida = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaBebida ());
        Query qTipoBebida = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaTipoBebida ());
        Query qBebedor = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaBebedor ());
        Query qBar = pm.newQuery(SQL, "DELETE FROM " + persistencia.darTablaBar ());

        long gustanEliminados = (long) qGustan.executeUnique ();
        long sirvenEliminados = (long) qSirven.executeUnique ();
        long visitanEliminadas = (long) qVisitan.executeUnique ();
        long bebidasEliminadas = (long) qBebida.executeUnique ();
        long tiposBebidaEliminados = (long) qTipoBebida.executeUnique ();
        long bebedoresEliminados = (long) qBebedor.executeUnique ();
        long baresEliminados = (long) qBar.executeUnique ();
        return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
        		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
	}

}