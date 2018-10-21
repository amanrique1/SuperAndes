package uniandes.isis2304.superAndes.persistencia;

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
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLProveedor sqlProveedor;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarPedido (PersistenceManager pm,long id,Timestamp fechaEntregaAc,Timestamp fechaEntrega,EstadoPedido estado, long idSucursal, String idProveedor) throws Exception 
	{
		
		if (sqlSucursal.darSucursalPorId(pm,idSucursal)==null||sqlProveedor.darProveedorPorNit(pm,idProveedor)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPedido() + "(idPedido,fechaEntregaAcordada, fechaEntrega, estado, idSucursal, idProveedor) values (?, ?, ?, ?, ?)");
		q1.setParameters(  id,fechaEntregaAc, fechaEntrega, estado.toString(),  idSucursal,  idProveedor);
		q1.executeUnique();
	}
	
	public void recibirPedido (PersistenceManager pm,EstadoPedido estado, long idPedido) 
	{
		
		
		Query q1 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPedido() + " SET estado= ? WHERE idPedido= ?");
		q1.setParameters(  estado.toString(),idPedido);
		q1.executeUnique();
	}


	public void eliminarPedidoPorId (PersistenceManager pm, Long id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido () + " WHERE idPedido = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}
	
	public Pedido darPedidoPorId (PersistenceManager pm, Long id)
	{

		Query q2 = pm.newQuery(SQL, "SELECT FROM " + pp.darTablaPedido () + " WHERE idPedido = ?");
		q2.setResultClass(Pedido.class);
		q2.setParameters(id);
		return (Pedido)q2.executeUnique();

	}


	public List<Pedido> darPedidos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPedido());
		q.setResultClass(Pedido.class);
		return (List<Pedido>) q.executeList();

	}
	
	public List<Pedido> darPedidosEntregados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPedido()+" WHERE estado= ?");
		q.setResultClass(Pedido.class);
		q.setParameters(EstadoPedido.ENTREGADO.toString());
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
