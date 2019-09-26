/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;

/**
 * @author Andres
 *
 */
public class SQLCarritoComprador {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLCompradores sqlComprador;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCarritoComprador (PersistenciaSuperAndes pp, SQLCompradores pSqlCompradores)
	{
		this.pp = pp;
		sqlComprador=pSqlCompradores;
	}
	
	
	public void agregarCarrito (PersistenceManager pm, long idCarrito, String idComprador)  
	{
//		if (sqlComprador.darCompradorPorIdentificador(pm,idComprador)==null)
//		{
//		throw new Exception("Datos invalidos");
//		}
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarritoPersona() + "(idComprador, idCarrito) values (?, ?)");
        q.setParameters(idComprador, idCarrito);
        q.executeUnique();
	}

	
	public void eliminarCarritoPorCliente (PersistenceManager pm, String idCliente)
	{
        Query q3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarritoPersona () + " WHERE idComprador is null");
        q3.setParameters(idCliente);
        q3.executeUnique();
	}

	public void abandonarCarritoCliente (PersistenceManager pm, String idCliente)
	{
        Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarritoPersona () + " SET idComprador= null WHERE idComprador = ?");
        q3.setParameters(idCliente);
        q3.executeUnique();
	}	

	public List<Long> darIdCarritoPorIdentificadorComprador (PersistenceManager pm, String idComprador) 
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT idCarrito FROM " + pp.darTablaCarritoPersona() + " WHERE idComprador = ?");
		q.setResultClass(Long.class);
		q.setParameters(idComprador);
		return (List<Long>)q.executeList();
		
	}
	
	public boolean existeCarrito (PersistenceManager pm, long idCarrito) 
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT idCarrito FROM " + pp.darTablaCarritoPersona() + " WHERE idCarrito = ?");
		q.setResultClass(String.class);
		q.setParameters(idCarrito);
		String carr= (String)q.executeUnique();
		if (carr==null)
				return false;
		else return true;
		
	}

	

}
