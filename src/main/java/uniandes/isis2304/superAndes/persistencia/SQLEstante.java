package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Estante;
import main.java.model.IndiceEstante;
import main.java.model.IndiceEstante;


public class SQLEstante {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;
	
	private SQLSucursal sqlSucursal;
	
	private SQLTipoProducto sqlTipoProducto;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarEstante (PersistenceManager pm,long id,double pCapV,double pCapP,String pUniP,String pUniV,long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		if (sqlSucursal.darSucursalPorId(pm,pIdSuc)==null||sqlTipoProducto.darTipoProducto(pm,pTipo)==null)
		{
		throw new Exception("Datos invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstantes() + "(idEstante,capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, nivelReOrden, idSucursal, tipoProducto ) values (?,?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters( id,pCapV, pCapP, pUniP, pUniV, pIdSuc,  pNivel, pTipo);
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
	public List<IndiceEstante> darOcupacion(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT idEstante,capacidadvolumen,x.unidadvolumen AS unidEstante,cantidad,prod.cantidadvolumen,prod.unidadvolumen AS unidProducto FROM("
				+ "SELECT idEstante,capacidadvolumen,a.unidadvolumen, cantidad, s.codigoproducto FROM(SELECT b.idEstante,b.capacidadvolumen,b.unidadvolumen, p.cantidad, p.idproductosucursal FROM "+pp.darTablaProductosEstante()+" p inner join "+pp.darTablaEstantes()+" b on p.idestante=b.idestante)a "
				+ "INNER JOIN "+pp.darTablaProductosSucursal()+" s on a.idproductosucursal=s.idproductosucursal) x INNER JOIN "+pp.darTablaProductos()+" prod on x.codigoproducto=prod.codigobarras");
		q.setResultClass(IndiceEstante.class);
		return (List<IndiceEstante>) q.executeList();
	}
	
}
