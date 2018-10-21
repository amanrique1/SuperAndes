/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.FacturasComprador;


/**
 * @author Andres
 *
 */
public class SQLFacturasComprador {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLCompradores sqlComprador;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLFacturasComprador (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarFacturasComprador (PersistenceManager pm, long id,Timestamp fecha, String idComprador, long idSucursal)  throws Exception 
	{
		if (sqlSucursal.darSucursalPorId(pm,idSucursal)==null||sqlComprador.darCompradorPorIdentificador(pm,idComprador)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFacturasCompradores() + "(numero,fecha, idComprador, idSucursal) values (?,?, ?, ?, ?)");
		q1.setParameters(  id,fecha,  idComprador,  idSucursal);
		q1.executeUnique();
	}


	public void eliminarFacturasCompradorPorNumero (PersistenceManager pm, long numero)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturasCompradores () + " WHERE numero = ?");
		q2.setParameters(numero);
		q2.executeUnique();

	}
	
	public List<FacturasComprador> darFacturaCompradorPorNumero (PersistenceManager pm, long numero)
	{

		Query q2 = pm.newQuery(SQL, "SELECT FROM " + pp.darTablaFacturasCompradores () + " WHERE numero = ?");
		q2.setResultClass(FacturasComprador.class);
		q2.setParameters(numero);
		return (List<FacturasComprador>)q2.executeUnique();

	}


	public List<FacturasComprador> darFacturasComprador (PersistenceManager pm, String idComprador)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idComprador= ?");
		q.setResultClass(FacturasComprador.class);
		q.setParameters(idComprador);
		return (List<FacturasComprador>) q.executeList();

	}

	public List<FacturasComprador> darFacturasSucursal (PersistenceManager pm, Long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(FacturasComprador.class);
		q.setParameters(idSucursal);
		return (List<FacturasComprador>) q.executeList();

	}

	public List<FacturasComprador> darFacturasFecha (PersistenceManager pm, Timestamp fecha)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE fecha= ?");
		q.setResultClass(FacturasComprador.class);
		q.setParameters(fecha);
		return (List<FacturasComprador>) q.executeList();

	}
	public List<FacturasComprador> darFacturasRangoFecha (PersistenceManager pm, Timestamp fechaIni,Timestamp fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE fecha>= ? AND fecha<= ?");
		q.setResultClass(FacturasComprador.class);
		q.setParameters(fechaIni,fechaFin);
		return (List<FacturasComprador>) q.executeList();

	}
	
	public List<FacturasComprador> darFacturasRangoFechaPersona (PersistenceManager pm, Timestamp fechaIni,Timestamp fechaFin, String cedula)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE fecha>= ? AND fecha<= ? AND idComprador= ?");
		q.setResultClass(FacturasComprador.class);
		q.setParameters(fechaIni,fechaFin,cedula);
		return (List<FacturasComprador>) q.executeList();

	}
}
