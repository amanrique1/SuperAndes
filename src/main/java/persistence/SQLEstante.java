package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Estante;


public class SQLEstante {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLTipoProducto sqlTipoProducto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarEstante (PersistenceManager pm,double pCapV,double pCapP,String pUniP,String pUniV,long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		if (sqlSucursal.darSucursalPorId(pm,pIdSuc)==null&&sqlTipoProducto.darTipoProducto(pm,pTipo)==null)
		{
		throw new Exception("Datos invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstantes() + "(capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, nivelReOrden, idSucursal, tipoProducto ) values (?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters( pCapV, pCapP, pUniP, pUniV, pIdSuc,  pNivel, pTipo);
		q1.executeUnique();
	}


	public void eliminarEstantePorId (PersistenceManager pm, long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstantes () + " WHERE idEstante = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}



	public List<Estante> darEstantesSucursal (PersistenceManager pm,long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaEstantes()+" WHERE idSucursal= ?");
		q.setResultClass(Estante.class);
		q.setParameters(idSucursal);
		List<Estante> estante= (List<Estante>) q.executeList();
		return estante;

	}


	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaEstantes());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();

	}

	public Estante darEstante (PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaEstantes()+" WHERE idEstante= ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();

	}

	
}
