package main.java.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;
import main.java.model.Promocion;
import main.java.model.TipoPromocion;

public class SQLPromocion {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarPromocion (PersistenceManager pm,long id,String pDescripcion,Timestamp pFechaIni,Timestamp pFechaFin,int px, int py, TipoPromocion pTipo) throws Exception 
	{


		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromociones() + "( idPromocion,x,y,fechaFinal,fechaInicial,tipoPromocion,descripcion) values (?, ?, ?, ?, ?, ?, )");
		q1.setParameters(  id,px,  py, pFechaFin,pFechaIni, pTipo,pDescripcion);
		q1.executeUnique();
	}


	

	public void eliminarPromocionPorId (PersistenceManager pm, long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromociones () + " WHERE idPromocion = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}

	public Promocion darPromocionPorId (PersistenceManager pm, long identificador) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromociones () + " WHERE idPromocion = ?");
		q1.setResultClass(Promocion.class);
		q1.setParameters(identificador);
		Promocion comp=(Promocion)q1.executeUnique();
		return comp;
	}

	public Promocion darPromocionPorDescripcion (PersistenceManager pm, String descripcion) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromociones () + " WHERE descripcion = ?");
		q1.setResultClass(Promocion.class);
		q1.setParameters(descripcion);
		Promocion comp=(Promocion)q1.executeUnique();
		return comp;
	}

	
	public List<Promocion> darPromociones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPromociones());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();

	}


}
