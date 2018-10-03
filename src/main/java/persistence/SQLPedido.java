package main.java.persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.EstadoPedido;
import main.java.model.Pedido;


public class SQLPedido {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLProveedor sqlProveedor;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarPedido (PersistenceManager pm,Timestamp fechaEntregaAc,Timestamp fechaEntrega,EstadoPedido estado, long idSucursal, long idProveedor) 
	{
		/**
		if (sqlSucursal.darSucursalPorId(pm,idSucursal)==null&&sqlProveedor.darProveedor(pm,idProveedor)==null)
		{
		throw new Exception("Datos invalidos");
		}
		*/
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPedido() + "(fechaEntregaAcordada, fechaEntrega, estado, idSucursal, idProveedor) values (?, ?, ?, ?, ?)");
		q1.setParameters(  fechaEntregaAc, fechaEntrega, estado.toString(),  idSucursal,  idProveedor);
		q1.executeUnique();
	}


	public void eliminarPedidoPorId (PersistenceManager pm, Long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido () + " WHERE idPedido = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}


	public List<Pedido> darPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPedido());
		q.setResultClass(Pedido.class);
		return (List<Pedido>) q.executeList();

	}
	
	public List<Pedido> darPedidosSucursal (PersistenceManager pm, Long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPedido()+ " WHERE idSucursal= ?");
		q.setResultClass(Pedido.class);
		q.setParameters(idSucursal);
		return (List<Pedido>) q.executeList();

	}
	public List<Pedido> darPedidosProveedor (PersistenceManager pm, Long idProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPedido()+" WHERE idProveedor= ?");
		q.setResultClass(Pedido.class);
		q.setParameters(idProveedor);
		return (List<Pedido>) q.executeList();

	}



}
