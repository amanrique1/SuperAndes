package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Estante;
import main.java.model.IndiceEstante;
import main.java.model.Sucursal;
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
	public SQLEstante (PersistenciaSuperAndes pp,SQLSucursal pSqlSucursal,SQLTipoProducto pSqlTipoProducto)
	{
		this.pp = pp;
		sqlSucursal=pSqlSucursal;
		sqlTipoProducto=pSqlTipoProducto;
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



	public List<String> darTipos(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT tipoProducto FROM " + pp.darTablaEstantes () + " WHERE idSucursal = ?");
		q1.setParameters(identificador);
		List<String> tipos= (List<String>) q1.executeList();
		
		return tipos;
	}

	public List<Long> darIds(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT idEstante FROM " + pp.darTablaEstantes () + " WHERE idSucursal = ?");
		q1.setParameters(identificador);
		List<BigDecimal> ids1= (List<BigDecimal>) q1.executeList();
		List<Long> ids= new ArrayList<Long>();

		for(BigDecimal id:ids1)
			ids.add(id.longValue());
		
		
		return ids;
	}
	public List<Estante> darEstantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ESTANTE");
		q.setResultClass(Estante.class);
		List<Estante> estante= (List<Estante>) q.executeList();
		return estante;

	}
	public List<Estante> darEstantesPorSucursal (PersistenceManager pm,long idSucursal)
	{
		List<Estante> estantes= new ArrayList<Estante>();
		List<String> tipos=darTipos(pm, idSucursal+"");
		List<Long> ids=darIds(pm, idSucursal+"");
		
		for(int i=0;i<ids.size();i++)
			estantes.add(new Estante(ids.get(i),tipos.get(i)));
		
		return estantes;

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
	
	
	public int darNivelReOrden (PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT nivelReOrden FROM "+ pp.darTablaEstantes()+" WHERE idEstante= ?");
		q.setParameters(idEstante);
		return ((BigDecimal)q.executeUnique()).intValue();

	}

	
	public double[] darPorLlenar (PersistenceManager pm, long idEstante,double[] cantidad)
	{
		
		
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaEstantes()+" WHERE idEstante= ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		Estante estante=(Estante)q.executeUnique(); 
		
		
		double[] porLlenar= new double[2];
		System.out.println("Capacidad peso: "+estante.getCapacidadPeso()+estante.getUnidadPeso());
		if(estante.getUnidadPeso().equalsIgnoreCase("kg"))
			porLlenar[0]=estante.getCapacidadPeso()-cantidad[0];
		else
			porLlenar[0]=(estante.getCapacidadPeso()/1000)-cantidad[0];
		
		System.out.println("Capacidad volumen: "+estante.getCapacidadVolumen()+estante.getUnidadVolumen());
		if(estante.getUnidadVolumen().equalsIgnoreCase("l"))
			porLlenar[1]=estante.getCapacidadVolumen()-cantidad[1];
		else
			porLlenar[1]=(estante.getCapacidadVolumen()/1000)-cantidad[1];
		return porLlenar;

	}
	
}
