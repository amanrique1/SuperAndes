package main.java.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Bodega;
import main.java.model.Comprador;

public class SQLBodegas {

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
	public SQLBodegas (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarBodega (PersistenceManager pm,double pCapV,double pCapP,String pUniP,String pUniV,long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		if (sqlSucursal.darSucursalPorId(pm,pIdSuc)==null&&sqlTipoProducto.darTipoProducto(pm,pTipo)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodegas() + "(capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, nivelReOrden, idSucursal, tipoProducto ) values (?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters( pCapV, pCapP, pUniP, pUniV, pIdSuc,  pNivel, pTipo);
		q1.executeUnique();
	}


	public void eliminarBodegaPorId (PersistenceManager pm, long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodegas () + " WHERE idBodega = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}



	public List<Bodega> darBodegasSucursal (PersistenceManager pm,long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaBodegas()+" WHERE idSucursal= ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idSucursal);
		List<Bodega> bodega= (List<Bodega>) q.executeList();
		return bodega;

	}

	public Bodega darBodegaId (PersistenceManager pm,long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaBodegas()+" WHERE idBodega= ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idBodega);
		Bodega bodega= (Bodega) q.executeUnique();
		return bodega;

	}


	public List<Bodega> darBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaBodegas());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();

	}


}
