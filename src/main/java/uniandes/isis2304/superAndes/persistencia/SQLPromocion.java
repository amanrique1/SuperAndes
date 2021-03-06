package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Productos;
import main.java.model.Promocion;
import main.java.model.TipoPromocion;
import main.java.model.VentaPromocion;

public class SQLPromocion {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarPromocion (PersistenceManager pm,long id,String pDescripcion,String pFechaIni,String pFechaFin,int px, int py, TipoPromocion pTipo) throws Exception 
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

	public void eliminarPromocionPorFecha (PersistenceManager pm)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromociones () + " WHERE fechaFinal < ?");
		q2.setParameters(timestamp);
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
	
	public List<Promocion> darPromocionesMasVend (PersistenceManager pm)
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM(SELECT * FROM (SELECT DISTINCT idPromocion, sum(CantidadVendida) AS CantVendida FROM "+ pp.darTablaPromocionesVendidas()+") ORDER BY CantVendidas ASC) WHERE rownum<21");
		q1.setResultClass(Promocion.class);
		List<VentaPromocion> ventas=(List<VentaPromocion>) q1.executeList();
		List<Promocion> promos=new LinkedList<>();
		for(VentaPromocion act:ventas)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPromociones()+" WHERE idPromocion="+act.getIdPromocion());
			q.setResultClass(Promocion.class);
			promos.add((Promocion) q.executeUnique());
		}
		return promos;		

	}


}
