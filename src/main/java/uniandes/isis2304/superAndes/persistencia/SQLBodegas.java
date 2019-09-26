package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Bodega;
import main.java.model.Comprador;
import main.java.model.Estante;
import main.java.model.IndiceBodega;
import main.java.model.Productos;
import main.java.model.ProductosBodega;
import main.java.model.ProductosEstante;

public class SQLBodegas {

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
	public SQLBodegas (PersistenciaSuperAndes pp,SQLSucursal pSqlSucursal,SQLTipoProducto pSqlTipoProducto)
	{
		this.pp = pp;
		sqlSucursal=pSqlSucursal;
		sqlTipoProducto=pSqlTipoProducto;
	}


	public void agregarBodega (PersistenceManager pm,long id,double pCapV,double pCapP,String pUniP,String pUniV,long pIdSuc, double pNivel, String pTipo) throws Exception 
	{
		if (sqlSucursal.darSucursalPorId(pm,pIdSuc)==null||sqlTipoProducto.darTipoProducto(pm,pTipo)==null)
		{
		throw new Exception("Datos invalidos");
		}
		
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodegas() + "(idBodega,capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, nivelReOrden, idSucursal, tipoProducto ) values (?,?, ?, ?, ?, ?, ?, ?)");
		q1.setParameters( id,pCapV, pCapP, pUniP, pUniV, pIdSuc,  pNivel, pTipo);
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
	
	public int darNivelReOrden (PersistenceManager pm, long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT nivelReOrden FROM "+ pp.darTablaBodegas()+" WHERE idBodega= ?");
		q.setParameters(idBodega);
		return ((BigDecimal)q.executeUnique()).intValue();

	}


	public List<Bodega> darBodegas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaBodegas());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();

	}
	public List<IndiceBodega> darOcupacion(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT idBodega,capacidadvolumen,x.unidadvolumen AS unidBodega,cantidad,prod.cantidadvolumen,prod.unidadvolumen AS unidProducto FROM("
				+ "SELECT idBodega,capacidadvolumen,a.unidadvolumen, cantidad, s.codigoproducto FROM(SELECT b.idBodega,b.capacidadvolumen,b.unidadvolumen, p.cantidad, p.idproductosucursal FROM "+pp.darTablaProductosBodega()+" p inner join "+pp.darTablaBodegas()+" b on p.idbodega=b.idbodega)a "
				+ "INNER JOIN "+pp.darTablaProductosSucursal()+" s on a.idproductosucursal=s.idproductosucursal) x INNER JOIN "+pp.darTablaProductos()+" prod on x.codigoproducto=prod.codigobarras");
		q.setResultClass(IndiceBodega.class);
		return (List<IndiceBodega>) q.executeList();
	}
	public double[] darPorLlenar (PersistenceManager pm, long idBodega,double[] cantidad)
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaBodegas()+" WHERE idBodega= ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idBodega);
		Bodega bodega=(Bodega)q.executeUnique(); 
		
		
		double[] porLlenar= new double[2];
		System.out.println("Capacidad peso: "+bodega.getCapacidadPeso()+bodega.getUnidadPeso());
		if(bodega.getUnidadPeso().equalsIgnoreCase("kg"))
			porLlenar[0]=bodega.getCapacidadPeso()-cantidad[0];
		else
			porLlenar[0]=(bodega.getCapacidadPeso()/1000)-cantidad[0];
		
		System.out.println("Capacidad volumen: "+bodega.getCapacidadVolumen()+bodega.getUnidadVolumen());
		if(bodega.getUnidadVolumen().equalsIgnoreCase("l"))
			porLlenar[1]=bodega.getCapacidadVolumen()-cantidad[1];
		else
			porLlenar[1]=(bodega.getCapacidadVolumen()/1000)-cantidad[1];
		return porLlenar;

	}


}
